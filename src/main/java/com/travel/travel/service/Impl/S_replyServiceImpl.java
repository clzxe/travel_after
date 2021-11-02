package com.travel.travel.service.Impl;

import com.travel.travel.entity.S_reply;
import com.travel.travel.repository.S_replyMapper;
import com.travel.travel.service.S_replyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class S_replyServiceImpl implements S_replyService {
    @Resource
    private S_replyMapper s_replyMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer replyId) {
        boolean flag=false;
        if(s_replyMapper.deleteByPrimaryKey(replyId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(S_reply record) {
        boolean flag=false;
        if(s_replyMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(S_reply record) {
        return false;
    }

    @Override
    public S_reply selectByPrimaryKey(Integer replyId) {
        return s_replyMapper.selectByPrimaryKey(replyId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(S_reply record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(S_reply record) {
        boolean flag=false;
        if(s_replyMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<S_reply> getReplyByCommentId(Integer commentId) {
        return s_replyMapper.getReplyByCommentId(commentId);
    }

    @Override
    public boolean deleteByCommentId(Integer commentId) {
        boolean flag=false;
        if(s_replyMapper.deleteByCommentId(commentId)>0){
            flag=true;
        }
        return flag;
    }
}
