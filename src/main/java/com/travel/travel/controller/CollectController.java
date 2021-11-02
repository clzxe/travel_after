package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.CollectService;
import com.travel.travel.service.UserActionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("travel/collect")
public class CollectController {

    @Resource
    private CollectService collectService;
    @Resource
    private UserActionService userActionService;

    @CrossOrigin
    @PostMapping(value = "/user_list")
    public Object getEvaluatesList(@RequestParam("searchTitle") String searchTitle,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("collectType") Integer collectType,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size , HttpSession session){
        List<Collect> collectList;
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
        collectList=collectService.getAllCollectUp(searchTitle,users.getUserId(),start,end,collectType,10*(pageIndex1-1),size1);
        total=collectService.getCollectCountUp(searchTitle,users.getUserId(),start,end,collectType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("collectList",collectList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("collectType",collectType);
        map.put("searchTitle",searchTitle);
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/wx_user_list")
    public Object getEvaluatesList(@RequestParam("searchTitle") String searchTitle,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("collectType") Integer collectType,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("userId") Integer userId,
                                   @RequestParam("size")String size , HttpSession session){
        List<Collect> collectList;
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
        collectList=collectService.getAllCollectUp(searchTitle,userId,start,end,collectType,10*(pageIndex1-1),size1);
        total=collectService.getCollectCountUp(searchTitle,userId,start,end,collectType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("collectList",collectList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("collectType",collectType);
        map.put("searchTitle",searchTitle);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveCollect(Collect collect, HttpSession session){

        Map<String,String> map = new HashMap<>();
        collect.setCreateTime(new Date());
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
        if(!collectService.insert(collect)){
            if(collect.getCollectType()==1){
                if(userActionService.selectByScore(collect.getUserId(),collect.getScenicStrategy())==null){
                    UserAction userAction=new UserAction();
                    userAction.setUserId(collect.getUserId());
                    userAction.setScenicId(collect.getScenicStrategy());
                    userAction.setScore(3);
                    if(userActionService.insert(userAction)){
                        map.put("message", "OK");

                    }
                }
                else {
                    if(userActionService.updateByScore(collect.getUserId(),collect.getScenicStrategy(),3)){
                        map.put("message", "OK");

                    }
                }
            }
            else {
                map.put("message", "OK");
            }
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public Object delReply(@RequestParam("collectId")String collectId){
        Map<String,Object> map= new HashMap<>(2);
        if(collectId==null||collectId.equals("")){
            map.put("message","系统响应错误");
        }
        else {
            if(collectService.deleteByPrimaryKey(Integer.parseInt(collectId))){
               map.put("message","OK");
            }
            else {
                map.put("message","系统响应错误");
            }
        }
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object Update(@RequestParam("collectId")String collectId){
        Map<String,Object> map= new HashMap<>(2);
        if(collectId==null||collectId.equals("")){
            map.put("message","系统响应错误");
        }
        else {
            if(collectService.saveByPrimaryKey(Integer.parseInt(collectId))){
                map.put("message","OK");
            }
            else {
                map.put("message","系统响应错误");
            }
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @PostMapping("/get_collect")
    public Object getHotelInit(@RequestParam("userId")Integer userId,@RequestParam("scenicStrategyId")Integer scenicStrategyId,@RequestParam("collectType")Integer collectType ,HttpSession session) {
        Map<String,Object> map= new HashMap<>(7);
       Collect collect=collectService.selectByUserIdAndScenicId(userId,scenicStrategyId,collectType);
        map.put("collect",collect);
        Integer countCollect=collectService.getCollectCount(scenicStrategyId,collectType);
        Users users= (Users) session.getAttribute("userSession");
        map.put("countCollect", countCollect);
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @PostMapping("/get_collect_count")
    public Object getHotelInitCount(@RequestParam("scenicStrategyId")Integer scenicStrategyId,@RequestParam("collectType")Integer collectType ,HttpSession session) {
        Map<String,Object> map= new HashMap<>(7);

        Integer countCollect=collectService.getCollectCount(scenicStrategyId,collectType);
        Users users= (Users) session.getAttribute("userSession");
        map.put("countCollect", countCollect);
        return map;
    }
}
