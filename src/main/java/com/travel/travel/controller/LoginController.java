package com.travel.travel.controller;

import com.alibaba.fastjson.JSONArray;
import com.travel.travel.entity.*;
import com.travel.travel.service.*;
import com.travel.travel.util.GetUserIP;
import com.travel.travel.util.Item_CF;
import com.travel.travel.util.PredictTest;
import com.travel.travel.util.SimilarityUtil;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.travel.travel.util.GetUserIP.readJsonFromUrl;

@RestController
@RequestMapping("/travel")
public class LoginController {
    @Resource
    private UsersService usersService;
    @Resource
    private AdminService adminService;
    @Resource
    private ScenicsService scenicsService;
    @Resource
    private LinesService linesService;
    @Resource
    private StrategyService strategyService;
    @Resource
    private CityService cityService;
    @Resource
    private ThemesService themesService;
    @Resource
    private NoticesService noticesService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private HotelsService hotelsService;
    @Resource
    private LeadersService leadersService;
    @Resource
    private Line_groupService lineGroupService;
    @Resource
    private Shopping_cartService shoppingCartService;
    @Resource
    private User_actionService userActionService;
    /*@CrossOrigin
    @RequestMapping(value = "/login1")
    public String Login(HttpSession session){
        System.out.println("1234556");
        return "OK";
    }*/
    @CrossOrigin
    @PostMapping(value = "/login")
   /* public String UserLogin(HttpSession session){
        System.out.println("1234556");
        return "OK";
    }*/
   public String UserLogin(@RequestParam("userAccount") String userAccount,@RequestParam("userPassword") String userPassword, HttpSession session){
        Users users=usersService.loginByMailAndPassword(userAccount);

        if(users==null){
            return "用户名错误";
        }
        else {
            if(!users.getUserPassword().equals(userPassword)){
                return "密码错误";
            }
            else{
                if(users.getIsDelete()==1){
                    return "账户不存在";
                }
                else if(users.getIsLock()==1){
                    return "账号已被封";
                }
                else {
                    session.setAttribute("userSession",users);
                    return "OK";
                }
            }
        }
    }

    @CrossOrigin
    @PostMapping(value = "/wx_login")
   /* public String UserLogin(HttpSession session){
        System.out.println("1234556");
        return "OK";
    }*/
    public Object WXUserLogin(@RequestParam("userAccount") String userAccount,@RequestParam("userPassword") String userPassword, HttpSession session){
        Map<String, Object> map = new HashMap<>(7);
        Users users=usersService.loginByMailAndPassword(userAccount);

        if(users==null){
            map.put("message","用户名错误") ;
        }
        else {
            if(!users.getUserPassword().equals(userPassword)){
                map.put("message","密码错误") ;
                //return "密码错误";
            }
            else{
                if(users.getIsDelete()==1){
                    map.put("message","账户不存在") ;
                    //return "账户不存在";
                }
                else if(users.getIsLock()==1){
                    map.put("message","账号已被封") ;
                    //return "账号已被封";
                }
                else {
                    //session.setAttribute("userSession",users);
                    map.put("users",users) ;
                    map.put("message","OK") ;
                    //return "OK";
                }
            }
        }
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/admin_login")
    public String AdminLogin(@RequestParam("adminAccount") String adminAccount, String adminPassword, HttpSession session){
        Admins users=adminService.selectByAdminAccount(adminAccount);
        if(users==null){
            return "用户名错误";
        }
        else {
            if(!users.getAdminPassword().equals(adminPassword)){
                return "密码错误";
            }
            else{

                session.setAttribute("adminSession",users);
                return "OK";
            }
        }

    }
    @CrossOrigin
    @PostMapping(value = "/leader_login")
    public String LeaderLogin(@RequestParam("adminAccount") String adminAccount, String adminPassword, HttpSession session){
        Leaders users=leadersService.selectByAccount(adminAccount);
        if(users==null){
            return "用户名错误";
        }
        else {
            if(!users.getLeaderPassword().equals(adminPassword)){
                return "密码错误";
            }
            else{

                session.setAttribute("leaderSession",users);
                return "OK";
            }
        }

    }
    @CrossOrigin
    @PostMapping(value = "/index")
    public Map<String, Object> getMap(HttpServletRequest request,HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>(7);
        List<Scenics> hot_scenic=scenicsService.getScenicLimitSix(6);
        List<Line_group> hot_line=lineGroupService.getLineHotLimitFour();
        List<Line_group> new_line=lineGroupService.getLineNewLimitFour();
        List<Strategy> best_strategy=strategyService.getBestLimitFour();
        Users users= (Users) session.getAttribute("userSession");

        System.out.println("请输入用户希望推荐的用户id :  ");
        List<Scenics> likeList=new ArrayList<>();
        if(users==null||users.getUserId()==0){
            likeList=scenicsService.getScenicLimitSix(6);
        }
        else {
            Item_CF predictTest=new Item_CF();
            likeList=predictTest.ItemRecommmd(users.getUserId(),6);//猜你喜欢

        }
        List<Scenics> scenicList=scenicsService.getScenicLimitSix(6);

        new GetUserIP();
        String IP= GetUserIP.getIp(request);
        JSONArray json = readJsonFromUrl("http://freeapi.ipip.net/"+IP);
        List<Notices> notices=noticesService.getNotices();
        map.put("hot_scenic", hot_scenic);
        map.put("likeList", likeList);
        map.put("hot_line", hot_line);
        map.put("new_line", new_line);
        map.put("best_strategy", best_strategy);
        map.put("notices", notices);
        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/admin/init")
    public Object adminIndex(HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int pnum=linesService.getAllCount(null);
        int onum=ordersService.getAllOrdersCount(null,0);
        int usernum=usersService.getAllUsersCount(null);
        int cusnum=hotelsService.getAllHotelsCount(null,null);

        map.put("pnum", pnum);
        map.put("onum", onum);
        map.put("cusnum", cusnum);
        map.put("usernum", usernum);
        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/loginOut")
    public String  LoginOut(HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
       session.setAttribute("userSession",null);
        session.setAttribute("adminSession",null);
        session.setAttribute("leaderSession",null);
        map.put("message", "OK");
        return "OK";
    }

    @CrossOrigin
    @GetMapping(value = "/wx/init")
    public Object WxIndex(HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<Scenics> scenicList=scenicsService.getScenicLimitSix(6);
        List<Line_group> lineGroupList=lineGroupService.getLineHotLimitSix();
        List<Strategy> strategyList=strategyService.getBestLimitSix();
        List<Notices> noticesList=noticesService.getNotices();
        map.put("scenicList", scenicList);
        map.put("lineGroupList", lineGroupList);
        map.put("strategyList", strategyList);
        map.put("noticeList", noticesList);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/line_tui")//猜你喜欢
    public Object tuijianIndex(HttpServletRequest request,@RequestParam("searchId") Integer searchId,
                               @RequestParam("searchName") String searchName,
                               @RequestParam("cityId") Integer cityId,
                               @RequestParam("scenicId") String scenicId,
                               @RequestParam("themeId") String themeId, @RequestParam("userId") Integer userId) throws IOException, TasteException {
        Map<String, Object> map = new HashMap<>();
        System.out.println("请输入用户希望推荐的用户id :  ");
        List<Scenics> likeList=new ArrayList<>();
        if(userId==null||userId==0){
            likeList=scenicsService.getScenicLimitSix(4);
        }
        else {
            Item_CF predictTest=new Item_CF();
            likeList=predictTest.ItemRecommmd(userId,4);//猜你喜欢
        }
        String[] arr=themeId.split(",");
        List<Scenics> themeList=new ArrayList<Scenics>();
        for(String scenicArry:arr){
            Scenics scenics=scenicsService.selectByPrimaryKey(Integer.parseInt(scenicArry));
            String  type=scenics.getScenicType();
            String [] typearr=type.split(",");
            for(String typeName:typearr){
               themeList=scenicsService.getAllUsersType(searchId,typeName,4);

            }
        }

        SimilarityUtil similarityUtil=new SimilarityUtil();
        List<Lines> similarityList=similarityUtil.getSimilarityLines(searchId,0,searchName);
        String[] scenicO=scenicId.split(",");
        List<Scenics> scenicsList=new ArrayList<>();
        for(String scenicB:scenicO){
             scenicsList=scenicsService.getScenicCityLimitFive(Integer.parseInt(scenicB),cityId);
        }
        for(int i=0;i<scenicsList.size();i++){
            for(String scenicB:scenicO){
               if(scenicsList.get(i).getScenicId()==Integer.parseInt(scenicB)){
                   scenicsList.remove(i);
                   i--;
               }
            }
        }
      //  List<Scenics> scenicList=scenicsService.getScenicCityLimitFive(scenicId,cityId);
        map.put("likeList",likeList);//猜你喜欢
        map.put("similarityList",similarityList);//猜你喜欢
        map.put("themeList",themeList);//猜你喜欢
        map.put("cityScenicList",scenicsList);//猜你喜欢
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/scenic_tui")//猜你喜欢
    public Object ScenictuijianIndex(HttpServletRequest request,
                                     @RequestParam("searchId") Integer searchId,
                                     @RequestParam("searchName") String searchName,
                                     @RequestParam("cityId") Integer cityId,
                                     @RequestParam("themeId") String themeId,
                                     @RequestParam("userId") Integer userId) throws IOException, TasteException {
        Map<String, Object> map = new HashMap<>();
        System.out.println("请输入用户希望推荐的用户id :  ");
        List<Scenics> likeList=new ArrayList<>();
        if(userId==null||userId==0){
            likeList=scenicsService.getScenicLimitSix(4);
        }
        else {
            Item_CF predictTest=new Item_CF();
            likeList=predictTest.ItemRecommmd(userId,4);//猜你喜欢
        }
        String[] arr=themeId.split(",");
        List<Scenics> themeList=new ArrayList<Scenics>();

        if(arr.length==0){
            themeList=scenicsService.getAllUsersType(searchId,null,4);
        }
        else {
            for(String typeName:arr){
                themeList=scenicsService.getAllUsersType(searchId,typeName,4);

            }
        }

        SimilarityUtil similarityUtil=new SimilarityUtil();
        List<Scenics> similarityList=similarityUtil.getSimilarityScenic(searchId,0,searchName);
        List<Scenics> scenicList=scenicsService.getScenicCityLimitFive(searchId,cityId);
        map.put("likeList",likeList);//猜你喜欢
        map.put("similarityList",similarityList);//猜你喜欢
        map.put("themeList",themeList);//猜你喜欢
        map.put("cityScenicList",scenicList);//猜你喜欢
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @GetMapping(value = "/try")
    public Object try22() throws IOException {
        List<Scenics> scenicsList;

        Map<String,Object> map =new HashMap<>(7);
        Item_CF item_cf=new Item_CF();
        //item_cf.ItemRecommmd();

        map.put("scenicList",item_cf.ItemRecommmd(2,4));

        return map;
    }

}
