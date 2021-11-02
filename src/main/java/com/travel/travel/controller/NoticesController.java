package com.travel.travel.controller;

import com.travel.travel.entity.Notices;
import com.travel.travel.service.NoticesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/notice")
public class NoticesController {
    @Resource
    private NoticesService noticesService;

    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveNotices(Notices notices, HttpSession session){

        Map<String,String> map = new HashMap<>(3);
        notices.setCreateTime(new Date());
        if(!noticesService.insert(notices)){
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
    public Object updateNotices(Notices notices, HttpSession session){

        Map<String,String> map = new HashMap<>(3);

        if(!noticesService.updateByPrimaryKey(notices)){
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
    public Object getNoticesList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Notices> noticesList;
        Integer total=0;
        int pageCount=0;
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        noticesList=noticesService.getAllNotices(searchTitle,10*(pageIndex-1),size);
        total=noticesService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("noticeList",noticesList);
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
    public String delnotices(@RequestParam("noticeId")String noticeId){
        if(noticeId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(noticesService.deleteByPrimaryKey(Integer.parseInt(noticeId))){
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
    @GetMapping("/getnotice")
    public Object getnoticesBynoticesId(@RequestParam("noticeId")String noticeId) {
        Map<String,Object> map= new HashMap<>(3);
        if(noticeId==null||noticeId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Notices notices=noticesService.selectByPrimaryKey(Integer.parseInt(noticeId));
            map.put("message","OK");
            map.put("notice",notices);
        }
        return map;
    }
}
