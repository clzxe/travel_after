package com.travel.travel.controller;

import com.travel.travel.entity.Groups;
import com.travel.travel.entity.Leaders;
import com.travel.travel.entity.Scenics;
import com.travel.travel.service.GroupsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/group")
public class GroupsController {
    @Resource
    private GroupsService groupsService;
    /**
     * 查询所有的景点
     */
    @CrossOrigin
    @GetMapping("/getGroup")
    public Object getAllGroups() {
        Map<String,Object> map= new HashMap<>();

        List<Groups> groupList =groupsService.getAll();
        map.put("groupList",groupList);
        return map;
    }

    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveGroups(Groups groups, HttpSession session){

        Map<String,String> map = new HashMap<>();
        groups.setGroupCeatetime(new Date());
        if(!groupsService.insert(groups)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }
    /**
     * 修改
     */
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateGroups(Groups groups, HttpSession session){

        Map<String,String> map = new HashMap<>();

        if(!groupsService.updateByPrimaryKey(groups)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getGroupsList(@RequestParam("searchTitle") String searchTitle,
                                 @RequestParam("pageIndex")Integer pageIndex,
                                 @RequestParam("size")Integer size ){
        List<Groups> groupsList;
        Integer total=0;
        int pageCount=0;
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        groupsList=groupsService.getAllGroups(searchTitle,10*(pageIndex-1),size);
        total=groupsService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("groupList",groupsList);
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
    public String delGroups(@RequestParam("groupId")String groupId){
        if(groupId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(groupsService.deleteByPrimaryKey(Integer.parseInt(groupId))){
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
    @GetMapping("/getgroup")
    public Object getGroupsByGroupsId(@RequestParam("groupId")String groupId) {
        Map<String,Object> map= new HashMap<>(5);
        if(groupId==null||groupId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Groups groups=groupsService.selectByPrimaryKey(Integer.parseInt(groupId));
            map.put("message","OK");
            map.put("group",groups);
        }
        return map;
    }
}
