package com.travel.travel.controller;

import com.travel.travel.entity.S_comments;
import com.travel.travel.entity.E_reply;
import com.travel.travel.entity.S_reply;
import com.travel.travel.service.S_commentsService;
import com.travel.travel.service.E_replyService;
import com.travel.travel.service.S_replyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/s_comment")
public class S_commentsController {
    @Resource
    private S_commentsService s_commentsService;
    @Resource
    private S_replyService s_replyService;

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object gets_commentsList(@RequestParam("searchTitle") String searchTitle,
                                    @RequestParam("pageIndex")String pageIndex,
                                    @RequestParam("size")String size ){
        List<S_comments> s_commentsList;
        Integer total=0;
        int pageCount=0;
        int s_commentType=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        Map<String,Object> map =null;
        Integer pageSize=Integer.parseInt(size);
        s_commentsList=s_commentsService.getAllS_comments(searchTitle,pageIndex1,size1);
        total=s_commentsService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("s_commentList",s_commentsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String dels_comments(@RequestParam("s_commentId")String s_commentId){
        if(s_commentId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(s_commentsService.deleteByPrimaryKey(Integer.parseInt(s_commentId))){
                if(s_replyService.deleteByCommentId(Integer.parseInt(s_commentId))){
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
     * 根据ID获取s_comments对象
     */
    @CrossOrigin
    @GetMapping("/getReplyList")
    public Object gets_commentsBys_commentId(@RequestParam("s_commentId")String s_commentId) {
        Map<String,Object> map= new HashMap<>();
        if(s_commentId==null||s_commentId.equals("")){
            map.put("message","ERROR");
        }
        else{
            List<S_reply> replyList=s_replyService.getReplyByCommentId(Integer.parseInt(s_commentId));
            map.put("message","OK");
            map.put("replyList",replyList);
        }
        return map;
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
            if(s_replyService.deleteByPrimaryKey(Integer.parseInt(replyId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/reply_save")
    public Object saveGroups(S_reply e_reply, HttpSession session){

        Map<String,String> map = new HashMap<>();
        e_reply.setCreateTime(new Date());
        //  List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
        if(!s_replyService.insert(e_reply)){
            map.put("message", "OK");
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }
}
