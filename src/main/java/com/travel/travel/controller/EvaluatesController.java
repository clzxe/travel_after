package com.travel.travel.controller;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.travel.travel.entity.*;
import com.travel.travel.service.E_replyService;
import com.travel.travel.service.EvaluatesService;
import com.travel.travel.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("travel/evaluates")
public class EvaluatesController {
    @Resource
    private EvaluatesService evaluatesService;
    @Resource
    private  E_replyService e_replyService;
    @Resource
    private E_replyService eReplyService;
    @Resource
    private OrdersService ordersService;
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getEvaluatesList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("level") String level,
                                @RequestParam("pageIndex")String pageIndex,
                                @RequestParam("size")String size ){
        List<Evaluates> evaluatesList;
        Integer total=0;
        int pageCount=0;
        int evaluateLevel=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        if(level!=null){
            evaluateLevel=Integer.parseInt(level);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        evaluatesList=evaluatesService.getAllEvaluates(searchTitle,evaluateLevel,size1*(pageIndex1-1),size1);
        total=evaluatesService.getAllEvaluatesCount(searchTitle,evaluateLevel);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("evaluateList",evaluatesList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("level",level);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/user_list")
    public Object getEvaluatesList(@RequestParam("orderName") String orderName,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("orderType") Integer orderType,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size ,HttpSession session){
        List<Evaluates> evaluatesList;
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
        evaluatesList=evaluatesService.getAllEvaluatesUp(orderName,level,users.getUserId(),start,end,orderType,10*(pageIndex1-1),size1);
        total=evaluatesService.getAllEvaluatesCountUp(orderName,level,users.getUserId(),start,end,orderType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("evaluateList",evaluatesList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("orderType",orderType);
        map.put("level",level);
        map.put("orderName",orderName);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/wx_user_list")
    public Object WxgetEvaluatesList(@RequestParam("orderName") String orderName,
                                   @RequestParam("startTime") String start,
                                     @RequestParam("userId") Integer userId,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("orderType") Integer orderType,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size ,HttpSession session){
        List<Evaluates> evaluatesList;
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
        evaluatesList=evaluatesService.getAllEvaluatesUp(orderName,level,userId,start,end,orderType,10*(pageIndex1-1),size1);
        total=evaluatesService.getAllEvaluatesCountUp(orderName,level,userId,start,end,orderType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("evaluateList",evaluatesList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("orderType",orderType);
        map.put("level",level);
        map.put("orderName",orderName);
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/hotel_list")
    public Object getEvaluatesListUp(@RequestParam("hotelId")Integer hotelId,
                                   @RequestParam("level") Integer level,
                                   @RequestParam("pageIndex")Integer pageIndex,
                                   @RequestParam("size")Integer size ){
        List<Evaluates> evaluatesList;
        Integer total=0;
        int pageCount=0;

        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=size;
        List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotelId,2,level,size*(pageIndex-1),size);
        total=evaluatesService.getAllEvaluatesByLineIdCount(hotelId,2,level);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        for(int i=0;i<evaluateList.size();i++){
            Evaluates evaluates=evaluateList.get(i);
            List<E_reply> eReplyList=new ArrayList<E_reply>();
            eReplyList=eReplyService.getReplyByCommentId(evaluates.getEvaluateId());
            evaluateList.get(i).setReplyList(eReplyList);
        }

        map.put("evaluateList",evaluateList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("level",level);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delEvaluates(@RequestParam("evaluateId")String evaluateId){
        if(evaluateId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(evaluatesService.deleteByPrimaryKey(Integer.parseInt(evaluateId))){
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
    @PostMapping(value = "/del_reply")
    public String delReply(@RequestParam("replyId")String replyId){
        if(replyId==null||replyId.equals("")){
            return "系统异常，请重新选择";
        }
        else {
            if(e_replyService.deleteByPrimaryKey(Integer.parseInt(replyId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 根据ID获取evaluates对象
     */
    @CrossOrigin
    @GetMapping("/getevaluate")
    public Object getEvaluatesByEvaluateId(@RequestParam("evaluateId")String evaluateId) {
        Map<String,Object> map= new HashMap<>();
        if(evaluateId==null||evaluateId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Evaluates evaluate=evaluatesService.selectByPrimaryKey(Integer.parseInt(evaluateId));
            map.put("message","OK");
            map.put("evaluate",evaluate);
        }
        return map;
    }

    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/reply_save")
    public Object saveGroups(E_reply e_reply, HttpSession session){

        Map<String,String> map = new HashMap<>();
        e_reply.setCreateTime(new Date());
      //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
        if(!e_replyService.insert(e_reply)){
            map.put("message", "OK");
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveEvaluates(Evaluates evaluates, HttpSession session){

        Map<String,String> map = new HashMap<>();
        evaluates.setCreateTime(new Date());
        Users users= (Users) session.getAttribute("userSession");
        evaluates.setUserId(users.getUserId());
        /*EmojiConverter emojiConverter = EmojiConverter.getInstance();
        evaluates.setEvaluateContent( emojiConverter.toAlias(evaluates.getEvaluateContent()));//将聊天内容进行转义
        System.out.println(evaluates.getEvaluateContent());*/
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
       try{
           if(evaluatesService.insert(evaluates)){
               if (ordersService.updateOrdersStatus(evaluates.getOrderId(),2)) {
                   map.put("message", "OK");
                   // log.info("插入成功");

               } else {
                   map.put("message", "ERROR");
               }

           }
           else {
               map.put("message", "ERROR");
           }
       }
       catch (Exception e){
          System.out.println(e.getMessage());
       }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/wx_save")
    public Object WXsaveEvaluates(Evaluates evaluates, HttpSession session){

        Map<String,String> map = new HashMap<>();
        evaluates.setCreateTime(new Date());
        //Users users= (Users) session.getAttribute("userSession");
        //evaluates.setUserId(users.getUserId());
        /*EmojiConverter emojiConverter = EmojiConverter.getInstance();
        evaluates.setEvaluateContent( emojiConverter.toAlias(evaluates.getEvaluateContent()));//将聊天内容进行转义
        System.out.println(evaluates.getEvaluateContent());*/
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
        if(evaluatesService.insert(evaluates)){
            if (ordersService.updateOrdersStatus(evaluates.getOrderId(),2)) {
                map.put("message", "OK");
                // log.info("插入成功");

            } else {
                map.put("message", "ERROR");
            }
            map.put("message", "OK");
        }
        else {
            map.put("message", "ERROR");
        }
        return map;
    }


}
