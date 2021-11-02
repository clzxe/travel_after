package com.travel.travel.controller;

import com.travel.travel.entity.Groups;
import com.travel.travel.entity.Hotels;
import com.travel.travel.entity.Leaders;
import com.travel.travel.service.GroupsService;
import com.travel.travel.service.LeadersService;
import com.travel.travel.util.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/leader")
public class LeadersController {
    @Resource
    private LeadersService leadersService;
    @Resource
    private GroupsService groupsService;
    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveLeaders(Leaders leaders, HttpSession session){

        Map<String,String> map = new HashMap<>();

        if(!leadersService.insert(leaders)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }
    /**
     * 修改
     */
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateLeaders(Leaders leaders, HttpSession session){

        Map<String,String> map = new HashMap<>();

        if(!leadersService.updateByPrimaryKey(leaders)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getLeadersList(@RequestParam("searchTitle") String searchTitle,
                               @RequestParam("pageIndex")Integer pageIndex,
                               @RequestParam("size")Integer size ){
        List<Leaders> leadersList;
        Integer total=0;
        int pageCount=0;
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=size;
        leadersList=leadersService.getAllLeaders(searchTitle,10*(pageIndex-1),size);
        total=leadersService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("leadersList",leadersList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delLeaders(@RequestParam("leaderId")String leaderId){
        if(leaderId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(leadersService.deleteByPrimaryKey(Integer.parseInt(leaderId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getleader")
    public Object getLeadersByLeadersId(@RequestParam("leaderId")String leaderId) {
        Map<String,Object> map= new HashMap<>();
        if(leaderId==null||leaderId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Leaders leaders=leadersService.selectByPrimaryKey(Integer.parseInt(leaderId));
            List<Groups> groupList=groupsService.getAll();
            map.put("message","OK");
            map.put("leaders",leaders);
            map.put("groupList",groupList);
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getLeader")
    public Object getLeadersBySession(HttpSession session) {
        Map<String,Object> map= new HashMap<>();
        Leaders leaders= (Leaders) session.getAttribute("leaderSession");
        if(leaders==null){
            leaders=new Leaders();
        }
      //  List<Groups> groupList=groupsService.getAll();
        map.put("message","OK");
        map.put("leader",leaders);
     //   map.put("groupList",groupList);
        return map;
    }

    /**
     * 删除用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/update_password")
    public String updatePassword(@RequestParam("leaderId")String leaderId,@RequestParam String password){
        if(leaderId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(leadersService.updatePassword(Integer.parseInt(leaderId),password)){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }


}
