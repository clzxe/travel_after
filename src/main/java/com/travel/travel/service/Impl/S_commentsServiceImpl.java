package com.travel.travel.service.Impl;

import com.travel.travel.entity.S_comments;
import com.travel.travel.repository.S_commentsMapper;
import com.travel.travel.service.S_commentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class S_commentsServiceImpl implements S_commentsService {
    @Resource
    private S_commentsMapper s_commentsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer commentId) {
        boolean flag=false;
        if(s_commentsMapper.deleteByPrimaryKey(commentId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(S_comments record) {
        boolean flag=false;
        if(s_commentsMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(S_comments record) {
        return false;
    }

    @Override
    public S_comments selectByPrimaryKey(Integer commentId) {
        return s_commentsMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(S_comments record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(S_comments record) {
        boolean flag=false;
        if(s_commentsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Integer getAllCount(String commentContent) {
        return s_commentsMapper.getAllCount(commentContent);
    }

    @Override
    public List<S_comments> getAllS_comments(String commentContent, Integer current, Integer size) {
        return s_commentsMapper.getAllS_comments(commentContent,current,size);
    }

    @Override
    public Integer getAllCountById(Integer strategyId) {
        return s_commentsMapper.getAllCountById(strategyId);
    }

    @Override
    public List<S_comments> getAllS_commentsById(Integer strategyId, Integer current, Integer size) {
        return s_commentsMapper.getAllS_commentsById(strategyId, current, size);
    }
}
