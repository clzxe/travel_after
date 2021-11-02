package com.travel.travel.service.Impl;

import com.travel.travel.entity.E_comments;
import com.travel.travel.repository.E_commentsMapper;
import com.travel.travel.service.E_commentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class E_commentsServiceImpl implements E_commentsService {
    @Resource
    private E_commentsMapper e_commentsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer commentId) {
        boolean flag=false;
        if(e_commentsMapper.deleteByPrimaryKey(commentId)>0){
            flag=true;
        }
        return false;
    }

    @Override
    public boolean insert(E_comments record) {
        boolean flag=false;
        if(e_commentsMapper.insert(record)>0){
            flag=true;
        }
        return false;
    }

    @Override
    public boolean insertSelective(E_comments record) {
        return false;
    }

    @Override
    public E_comments selectByPrimaryKey(Integer commentId) {
        return e_commentsMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(E_comments record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(E_comments record) {
        boolean flag=false;
        if(e_commentsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return false;
    }

    @Override
    public Integer getAllCount(String commentContent, Integer searchType) {
        return e_commentsMapper.getAllCount(commentContent,searchType);
    }

    @Override
    public List<E_comments> getAllE_comments(String commentContent, Integer searchType, Integer current, Integer size) {
        return e_commentsMapper.getAllE_comments(commentContent,searchType,current,size);
    }
}
