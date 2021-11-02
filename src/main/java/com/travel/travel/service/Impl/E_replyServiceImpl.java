package com.travel.travel.service.Impl;

import com.travel.travel.entity.E_reply;
import com.travel.travel.repository.E_replyMapper;
import com.travel.travel.service.E_replyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class E_replyServiceImpl implements E_replyService {
    @Resource
    private E_replyMapper e_replyMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer replyId) {
        boolean flag=false;
        if(e_replyMapper.deleteByPrimaryKey(replyId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(E_reply record) {
        boolean flag=false;
        if(e_replyMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(E_reply record) {
        return false;
    }

    @Override
    public E_reply selectByPrimaryKey(Integer replyId) {
        return e_replyMapper.selectByPrimaryKey(replyId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(E_reply record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(E_reply record) {
        boolean flag=false;
        if(e_replyMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<E_reply> getReplyByCommentId(Integer commentId) {
        return e_replyMapper.getReplyByCommentId(commentId);
    }

    @Override
    public boolean deleteByCommentId(Integer commentId) {
        boolean flag=false;
        if(e_replyMapper.deleteByCommentId(commentId)>0){
            flag=true;
        }
        return flag;
    }
}
