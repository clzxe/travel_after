package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.Line_groupService;
import com.travel.travel.service.ScenicsService;
import com.travel.travel.service.Shopping_cartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("travel/car")
public class CarController {
    @Resource
    private Shopping_cartService shoppingCartService;
    @Resource
    private Line_groupService lineGroupService;
    @Resource
    private ScenicsService scenicsService;
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getOrdersList(@RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ,
                                HttpSession session){
        List<Shopping_cart> shoppingCartList;
        Integer total=0;
        int pageCount=0;
        Users users= (Users) session.getAttribute("userSession");
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        shoppingCartList=shoppingCartService.getAllCars(users.getUserId(),size*(pageIndex-1),size);
        total=shoppingCartService.getAllCarsCount(users.getUserId());
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        if(shoppingCartList==null||shoppingCartList.size()==0){
            for(int i=0;i<shoppingCartList.size();i++){
                Line_group lineGroup=lineGroupService.selectByPrimaryKey(shoppingCartList.get(i).getLineGroupId());
                String[] arr=lineGroup.getLineScenic().split(",");
                Double scenicPrice=0.0;
                if(arr.length!=0){
                    for(String scecnicId:arr){
                        Scenics scenics=scenicsService.selectByPrimaryKey(Integer.parseInt(scecnicId));
                        scenicPrice+=scenics.getScenicPrice();
                    }
                }

                shoppingCartList.get(i).setScenicPrice(scenicPrice);
            }
        }
        map.put("shoppingCartList",shoppingCartList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/wx_list")
    public Object getOrdersList(@RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("size")Integer size ,
                                HttpSession session){
        List<Shopping_cart> shoppingCartList;
        Integer total=0;
        int pageCount=0;
        Users users= (Users) session.getAttribute("userSession");
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        shoppingCartList=shoppingCartService.getAllCars(userId,size*(pageIndex-1),size);
        total=shoppingCartService.getAllCarsCount(userId);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        if(shoppingCartList==null||shoppingCartList.size()==0){
            for(int i=0;i<shoppingCartList.size();i++){
                Line_group lineGroup=lineGroupService.selectByPrimaryKey(shoppingCartList.get(i).getLineGroupId());
                String[] arr=lineGroup.getLineScenic().split(",");
                Double scenicPrice=0.0;
                if(arr.length!=0){
                    for(String scecnicId:arr){
                        Scenics scenics=scenicsService.selectByPrimaryKey(Integer.parseInt(scecnicId));
                        scenicPrice+=scenics.getScenicPrice();
                    }
                }

                shoppingCartList.get(i).setScenicPrice(scenicPrice);
            }
        }
        map.put("shoppingCartList",shoppingCartList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveCars(Shopping_cart shoppingCart, HttpSession session){

        Map<String,String> map = new HashMap<>();
        //evaluates.setCreateTime(new Date());
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
       // Shopping_cart car=shoppingCartService.selectByPrimaryKey()
        //这里要增加一个判断获取原有的...或者在前台添加
        if(shoppingCartService.insert(shoppingCart)) {

            map.put("message", "OK");
        }
        else {
            map.put("message", "添加失败");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateCars(Shopping_cart shoppingCart, HttpSession session){

        Map<String,String> map = new HashMap<>();
        //evaluates.setCreateTime(new Date());
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
        if(!shoppingCartService.updateCount(shoppingCart)) {

            map.put("message", "OK");
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
    public String delEvaluates(@RequestParam("carId")String carId){
        if(carId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(shoppingCartService.deleteByPrimaryKey(Integer.parseInt(carId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }


}
