package com.travel.travel.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCancelModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.travel.travel.config.AlipayConfig;
import com.travel.travel.config.AlipayProperties;
import com.travel.travel.config.WebSocket;
import com.travel.travel.entity.*;
import com.travel.travel.service.*;
import com.travel.travel.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Controller
@Slf4j
@RestController()
@RequestMapping("travel/order")
public class OrdersController {
    @Resource
    private OrdersService ordersService;
    @Resource
    private UsersService usersService;
    @Resource
    private Shopping_cartService shoppingCartService;
    @Resource
    private HotelsService hotelsService;
    @Resource
    private LinesService linesService;
    @Resource
    private Input_MoneyService inputMoneyService;
    @Resource
    private Line_groupService lineGroupService;
    @Resource
    private UserActionService userActionService;
    /**
     * 获取攻略列表（多条件筛选）
     */


    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayProperties alipayProperties;
    @Autowired
    private WebSocket webSocket;


    @CrossOrigin
    @PostMapping(value = "/createQR")
    public Object send(@RequestParam("amount") String amount,
                       @RequestParam("product") String product, HttpSession session) throws Exception {

        Map<String,Object> map=new HashMap<>(5);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest(); //创建API对应的request类
        SnowFlake snowflakeManager = new SnowFlake(0L,0L);//雪花算法用于生成唯一订单号
        String orderId = String.valueOf(snowflakeManager.nextValue());
        //orders.setOrderId(orderId);
        AlipayTradePrecreateModel model=new AlipayTradePrecreateModel();//支付所需的参数集合
        model.setOutTradeNo(orderId);                   //商户订单号
        model.setProductCode("FACE_TO_FACE_PAYMENT"); //销售产品码
        model.setSubject(product);                    //订单标题
        model.setTotalAmount(amount);                  //订单总金额
        request.setBizModel(model);
        request.setReturnUrl(AlipayConfig.return_url);//支付成功后的回调函数
        request.setNotifyUrl(AlipayConfig.notify_url);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);//创建请求

        if (response.isSuccess()) {
            System.out.println("调用成功");
            map.put("code",response.getQrCode());//返回二维码
            map.put("orderId",orderId);//返回二维码

        } else {
            System.out.println("调用失败");
            map.put("message","ERROR");
        }
        return map;
    }

    @RequestMapping("/cancel")
    public String cancel() throws Exception{  //取消订单，支付超时、支付结果未知是可撤销，超过24小时不可撤销
        AlipayTradeCancelModel model=new AlipayTradeCancelModel();
        model.setOutTradeNo("300");

        AlipayTradeCancelRequest request=new AlipayTradeCancelRequest();
        request.setBizModel(model);

        AlipayTradeCancelResponse response=alipayClient.execute(request);
        return response.getBody();
    }

    @RequestMapping("/notify")
    public void notify(HttpServletRequest request,HttpSession session) throws Exception{   //trade_success状态下异步通知接口
        Users user= (Users) session.getAttribute("userSession");
        if (check(request.getParameterMap())){
            System.out.println(request.getParameter("trade_status"));
            System.out.println("异步通知 "+ Instant.now());
                webSocket.sendMessage("true");

        }else {
            System.out.println("验签失败");
        }
    }

    @RequestMapping("/return")
    public String returnUrl(HttpServletRequest request) throws Exception{  //订单支付成功后同步返回地址
        if (check(request.getParameterMap())){
            webSocket.sendMessage("true");

            return "success";
        }else {
            return "false";
        }
    }

    private boolean check(Map<String,String[]> requestParams) throws Exception{  //对return、notify参数进行验签
        Map<String,String> params = new HashMap<>();

        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
            System.out.println(name+" ==> "+valueStr);
        }
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        return AlipaySignature.rsaCheckV1(params,  AlipayConfig.alipay_public_key,
                AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
    }
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getOrdersList(@RequestParam("searchTitle") String searchTitle,
                                  @RequestParam("searchType") String searchType,
                                  @RequestParam("pageIndex")Integer pageIndex,
                                  @RequestParam("size")Integer size ){
        List<Orders> ordersList;
        Integer total=0;
        int pageCount=0;
        int orderType=0;
        if(searchType!=null){
            orderType=Integer.parseInt(searchType);
        }
        Map<String,Object> map =new HashMap<>();
        Integer pageSize=size;
        ordersList=ordersService.getAllOrders(searchTitle,orderType,size*(pageIndex-1),size);
        total=ordersService.getAllOrdersCount(searchTitle,orderType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("orderList",ordersList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("pageIndex",pageIndex);
        map.put("searchTitle",searchTitle);
        map.put("searchType",searchType);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delOrders(@RequestParam("orderId")String orderId){
        if(orderId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(ordersService.deleteByPrimaryKey(orderId)){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del_user")
    public String delOrdersUser(@RequestParam("orderId")String orderId){
        if(orderId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(ordersService.deleteByPrimaryKeyUser(orderId)){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 根据ID获取orders对象
     */
    @CrossOrigin
    @GetMapping("/getorders")
    public Object getOrdersByorderId(@RequestParam("orderId")String orderId) {
        Map<String,Object> map= new HashMap<>();
        if(orderId==null||orderId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Orders order=ordersService.selectByPrimaryKey(orderId);
            map.put("message","OK");
            map.put("order",order);
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveOrder2(Orders orders,@RequestParam("totaltime") Integer totaltime,@RequestParam("scenicId") String scenicId) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        SnowFlake snowflakeManager = new SnowFlake(0L, 0L);
        String orderId = String.valueOf(snowflakeManager.nextValue());
        orders.setOrderId(orderId);
        orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        orders.setOrderStatus(0);
        Calendar cal=Calendar.getInstance();
        cal.setTime(orders.getInDate());
        cal.add(Calendar.DATE,totaltime-1);
        Date date=cal.getTime();
        orders.setLeaveDate(date);
        log.info(orderId);
        if (ordersService.insert(orders)) {
            if(orders.getOrderType()==1){

                if(lineGroupService.updateGroupPeople(1,orders.getLineOrHotelId(),orders.getOrderCount())){
                    String[] arr =scenicId.split(",");
                    for(String scenic:arr){
                        if(userActionService.selectByScore(orders.getUserId(),Integer.parseInt(scenic))==null){
                            UserAction userAction=new UserAction();
                            userAction.setUserId(orders.getUserId());
                            userAction.setScenicId(Integer.parseInt(scenic));
                            userAction.setScore(5);
                            if(userActionService.insert(userAction)){
                                map.put("message", "OK");
                                map.put("orders", orders);
                                log.info("插入成功");

                            }
                        }
                        else {
                            if(userActionService.updateByScore(orders.getUserId(),Integer.parseInt(scenic),5)){
                                map.put("message", "OK");
                                map.put("orders", orders);
                                log.info("插入成功");

                            }
                        }
                    }

                }

            }


        } else {
            map.put("message", "ERROR");
        }
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/save_car")
    public Object saveOrder(Orders orders,@RequestParam("scId")Integer scId,@RequestParam("totaltime") Integer totaltime) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        SnowFlake snowflakeManager = new SnowFlake(0L, 0L);
        String orderId = String.valueOf(snowflakeManager.nextValue());
        orders.setOrderId(orderId);
        orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        orders.setOrderStatus(0);
        Calendar cal=Calendar.getInstance();
        cal.setTime(orders.getInDate());
        cal.add(Calendar.DATE,totaltime-1);
        Date date=cal.getTime();
        orders.setLeaveDate(date);
        log.info(orderId);
        if (ordersService.insert(orders)) {
            if(shoppingCartService.deleteByPrimaryKey(scId)){
                map.put("message", "OK");
                map.put("orders", orders);
                log.info("插入成功");
            }else {
                map.put("message", "ERROR");
            }

        } else {
            map.put("message", "ERROR");
        }
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateOrder(Orders orders) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        SnowFlake snowflakeManager = new SnowFlake(0L, 0L);
        String orderId = String.valueOf(snowflakeManager.nextValue());
        orders.setOrderId(orderId);
        orders.setCreatTime(new Date());
        orders.setUpdateTime(orders.getCreatTime());
        if (ordersService.updateByPrimaryKey(orders)) {
            map.put("message", "OK");

            log.info("插入成功");

        } else {
            map.put("message", "ERROR");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/change_status")
    public Object changgeStatus(@RequestParam("orderId")String orderId,@RequestParam("status")Integer status) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());

        if (ordersService.updateOrdersStatus(orderId,status)) {

            map.put("message", "OK");
            log.info("插入成功");

        } else {
            map.put("message", "ERROR");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/cancel_pay")
    public Object CancelPay(Orders orders,@RequestParam("status")Integer status) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());

        if (ordersService.updateOrdersStatus(orders.getOrderId(),status)) {

           if(orders.getOrderType()==1){
               if(lineGroupService.updateGroupPeople(2,orders.getLineOrHotelId(),orders.getOrderCount())){
                   map.put("message", "OK");
                   log.info("插入成功");
               }
           }


        } else {
            map.put("message", "ERROR");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/cancel_order")
    public Object CancelOrder(Orders orders,@RequestParam("status")Integer status,
                              HttpSession session) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());
        Users users= (Users) session.getAttribute("userSession");
        assert users!=null;
        if (ordersService.updateOrdersStatus(orders.getOrderId(),status)) {
            if(orders.getOrderType()==1){
                if(lineGroupService.updateGroupPeople(2,orders.getLineOrHotelId(),orders.getOrderCount())&&usersService.updateUserAmount(users.getUserId(),orders.getOrderTotalPrice())){
                    map.put("message", "OK");
                    log.info("插入成功");
                }
            }

        } else {
            map.put("message", "ERROR");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/pay")
    public Object Pay(Orders orders) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());

        if (usersService.updateUserAmount(orders.getUserId(),0-orders.getOrderTotalPrice())) {
            if (ordersService.updateOrdersStatus(orders.getOrderId(),2)) {
                map.put("message", "OK");
                log.info("插入成功");

            } else {
                map.put("message", "ERROR");
            }

        } else {
            map.put("message", "ERROR");
        }
        return map;
    }



    @CrossOrigin
    @PostMapping(value = "/pay2")
    public Object Pay(String[] orderId,@RequestParam("amount")String amount,
                      HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());
        int i=0;
        for(String oid:orderId){
            if (ordersService.updateOrdersStatus(oid,2)) {

                log.info("插入成功");
                i++;

            } else {
                map.put("message", "ERROR");
            }

        }
        if(i>=orderId.length){
            Users users= (Users) session.getAttribute("userSession");
            if (usersService.updateUserAmount(users.getUserId(),0-Double.parseDouble(amount))) {
                map.put("message", "OK");

            } else {
                map.put("message", "ERROR");
            }
        }


        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/wx_pay2")
    public Object Pay(String[] orderId,@RequestParam("amount")String amount,
                      @RequestParam("userId") Integer userId,
                      HttpSession session) throws Exception {


        Map<String, Object> map = new HashMap<>(5);
        //orders.setCreatTime(new Date());
        int i=0;
        for(String oid:orderId){
            if (ordersService.updateOrdersStatus(oid,2)) {

                log.info("插入成功");
                i++;

            } else {
                map.put("message", "ERROR");
            }

        }
        if(i>=orderId.length){
            Users users= (Users) session.getAttribute("userSession");
            if (usersService.updateUserAmount(userId,0-Double.parseDouble(amount))) {
                map.put("message", "OK");

            } else {
                map.put("message", "ERROR");
            }
        }


        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/user_list")
    public Object getEvaluatesList(@RequestParam("orderName") String orderName,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("searchType") Integer searchType,
                                   @RequestParam("orderStatus") Integer orderStatus,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size ,HttpSession session){
        List<Orders> ordersList;
        Integer total=0;
        int pageCount=0;
        int evaluateLevel=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        Users users= (Users) session.getAttribute("userSession");
        ordersList=ordersService.getAllOrdersUp(orderName,users.getUserId(),searchType,orderStatus,start,end,size1*(pageIndex1-1),size1);
        total=ordersService.getAllOrdersCountUp(orderName,users.getUserId(),searchType,orderStatus,start,end);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("orderList",ordersList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("searchType",searchType);
        map.put("orderName",orderName);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/wx_user_list")
    public Object WXgetEvaluatesList(@RequestParam("orderName") String orderName,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("userId") Integer userId,
                                   @RequestParam("searchType") Integer searchType,
                                   @RequestParam("orderStatus") Integer orderStatus,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size ,HttpSession session){
        List<Orders> ordersList;
        Integer total=0;
        int pageCount=0;
        int evaluateLevel=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        Users users= (Users) session.getAttribute("userSession");
        ordersList=ordersService.getAllOrdersUp(orderName,userId,searchType,orderStatus,start,end,10*(pageIndex1-1),size1);
        total=ordersService.getAllOrdersCountUp(orderName,userId,searchType,orderStatus,start,end);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("orderList",ordersList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("searchType",searchType);
        map.put("orderName",orderName);
        return map;
    }

    /**
     * 根据ID获取orders对象
     */
    @CrossOrigin
    @GetMapping("/chart_init")
    public Object OrderChart() {
        Map<String,Object> map= new HashMap<>();
        Map<String,Object> map1=new HashMap<>(3);
        //Map<String,Object> digitalData2= new HashMap<>();
        List<OrderResultMap> group=ordersService.getGroupCount();

        List<Map<String,Object>> digitalData=new ArrayList<>();
        List<Map<String,Object>> digitalData2=new ArrayList<>();
        List<String> chart2Text=new ArrayList<>();
        List<String> chart3Text=new ArrayList<>();
        for (OrderResultMap city:group) {
            map1=new HashMap<>(2);
            map1.put("value",city.getCount());
            map1.put("name",city.getGroupName());
            chart2Text.add(city.getGroupName());
            digitalData.add(map1);
        }
        List<OrderResultMap> week=ordersService.getOrderDayWeekCount();
        List<Integer> digitalData1=new ArrayList<>();
        List<String> xAxisText1=new ArrayList<>();
        for (OrderResultMap city:week) {
            digitalData1.add(city.getCount());
            xAxisText1.add(city.getDays());

        }
        List<OrderResultMap> citys=ordersService.getCityCount();
        for (OrderResultMap city:citys) {
            map1=new HashMap<>(2);
            map1.put("value",city.getCount());
            map1.put("name",city.getCityName());
            chart3Text.add(city.getCityName());
            digitalData2.add(map1);
        }
        int lineNum=linesService.getAllCount(null);
        int totalOrder=ordersService.getAllOrdersCount(null,0);
        int userNum=usersService.getAllUsersCount(null);
        int hotelNum=hotelsService.getAllHotelsCount(null,null);
        int todayOrder=ordersService.getOrderDayToDayCount();
        int yesOrder=ordersService.getOrderDayONeCount();
        List<OrderResultMap> scenicList=ordersService.getScenicCount();
        List<OrderResultMap> lineList=ordersService.getLineCount();
        map.put("scenicList",scenicList);
        map.put("lineList",lineList);
        map.put("lineNum",lineNum);
        map.put("totalOrder",totalOrder);
        map.put("userNum",userNum);
        map.put("hotelNum",hotelNum);
        map.put("todayOrder",todayOrder);
        map.put("yesOrder",yesOrder);
        map.put("digitalData",digitalData);
        map.put("digitalData2",digitalData2);
        map.put("chart3Text",chart3Text);
        map.put("chart2Text",chart2Text);
        map.put("digitalData1",digitalData1);
        map.put("xAxisText1",xAxisText1);
        map.put("message","OK");
        return map;
    }

    /**
     * 根据ID获取orders对象
     */
    @CrossOrigin
    @GetMapping("/chart_order_init")
    public Object OrderChartInit(@RequestParam("year") String year) {
        Map<String,Object> map= new HashMap<>();
        Map<String,Object> map1=new HashMap<>(3);
        //Map<String,Object> digitalData2= new HashMap<>();


        List<Map<String,Object>> digitalData2=new ArrayList<>();//年月销售额
        List<String> left2Text=new ArrayList<>();


        List<OrderResultMap> yearSum=ordersService.getOrderOneYear(year);//年月销售额
        for (OrderResultMap city:yearSum) {
            map1=new HashMap<>(2);
            map1.put("value",city.getCount());
            map1.put("name",city.getDays());
            left2Text.add(city.getDays());
            digitalData2.add(map1);

        }
        List<Integer> digitalData=new ArrayList<>();//年月销售量
        List<String> leftText=new ArrayList<>();
        List<OrderResultMap> yearCount=ordersService.getOrderCountOneYearMonth(year);//年月销售量
        for (OrderResultMap city:yearCount) {
           // map1=new HashMap<>(2);
          //  map1.put("value",city.getCount());
          //  map1.put("name",city.getDays());
            leftText.add(city.getDays());
            digitalData.add(city.getCount());
        }

        List<Map<String,Object>> digitalData3=new ArrayList<>();//年季度销售量
        List<String> left3Text=new ArrayList<>();
        List<OrderResultMap> seaSonCount=ordersService.getOrderCountOneSeason(year);//年季度销售量
        for (OrderResultMap city:seaSonCount) {
            map1=new HashMap<>(2);
            map1.put("value",city.getCount());
            map1.put("name",city.getDays());
            left3Text.add(city.getDays());
            digitalData3.add(map1);
        }

        List<Map<String,Object>> digitalData4=new ArrayList<>();//年季度销售额
        List<String> left4Text=new ArrayList<>();
        List<OrderResultMap> seaSonSum=ordersService.getOrderCountOneSeason(year);//年季度销售额
        for (OrderResultMap city:seaSonSum) {
            map1=new HashMap<>(2);
            map1.put("value",city.getCount());
            map1.put("name",city.getDays());
            left4Text.add(city.getDays());
            digitalData4.add(map1);
        }

        map.put("digitalData",digitalData);
        map.put("digitalData2",digitalData2);
        map.put("left2Text",left2Text);
        map.put("leftText",leftText);
        map.put("digitalData3",digitalData3);
        map.put("digitalData4",digitalData4);
        map.put("left3Text",left3Text);
        map.put("left4Text",left4Text);

        map.put("message","OK");
        return map;
    }
}
