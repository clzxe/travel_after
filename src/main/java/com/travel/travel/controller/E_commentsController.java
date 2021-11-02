package com.travel.travel.controller;

import com.travel.travel.entity.E_comments;
import com.travel.travel.entity.E_reply;
import com.travel.travel.service.E_commentsService;
import com.travel.travel.service.E_replyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/e_comment")
public class E_commentsController {
    @Resource
    private E_commentsService e_commentsService;
    @Resource
    private E_replyService e_replyService;

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object gete_commentsList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("level") String level,
                                @RequestParam("pageIndex")String pageIndex,
                                @RequestParam("size")String size ){
        List<E_comments> e_commentsList;
        Integer total=0;
        int pageCount=0;
        int e_commentType=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        if(level!=null){
            e_commentType=Integer.parseInt(level);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        e_commentsList=e_commentsService.getAllE_comments(searchTitle,e_commentType,10*(pageIndex1-1),size1);
        total=e_commentsService.getAllCount(searchTitle,e_commentType);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("e_commentList",e_commentsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("level",level);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String dele_comments(@RequestParam("e_commentId")String e_commentId){
        if(e_commentId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(e_commentsService.deleteByPrimaryKey(Integer.parseInt(e_commentId))){
               if(e_replyService.deleteByCommentId(Integer.parseInt(e_commentId))){
                   return "OK";
               }
               else {
                   return "系统响应错误";
               }
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 根据ID获取e_comments对象
     */
    @CrossOrigin
    @GetMapping("/getReplyList")
    public Object gete_commentsBye_commentId(@RequestParam("e_commentId")String e_commentId) {
        Map<String,Object> map= new HashMap<>();
        if(e_commentId==null||e_commentId.equals("")){
            map.put("message","ERROR");
        }
        else{
            List<E_reply> replyList=e_replyService.getReplyByCommentId(Integer.parseInt(e_commentId));
            map.put("message","OK");
            map.put("replyList",replyList);
        }
        return map;
    }
}
