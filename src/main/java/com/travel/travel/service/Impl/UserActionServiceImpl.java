package com.travel.travel.service.Impl;

import com.travel.travel.entity.UserAction;
import com.travel.travel.repository.UserActionMapper;
import com.travel.travel.service.UserActionService;
import com.travel.travel.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserActionServiceImpl implements UserActionService {
    @Resource
    private UserActionMapper userActionMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return false;
    }

    @Override
    public boolean insert(UserAction record) {
        boolean flag=false;
        if(userActionMapper.insert(record)>0){
            flag=true;
        }
       return flag;
    }

    @Override
    public boolean insertSelective(UserAction record) {
        return false;
    }

    @Override
    public UserAction selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(UserAction record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(UserAction record) {
        return false;
    }

    @Override
    public List<UserAction> selectAll() {
        return userActionMapper.selectAll();
    }

    @Override
    public List<UserAction> selectByUserId(Integer userId) {
        return userActionMapper.selectByUserId(userId);
    }

    @Override
    public UserAction selectByScore(Integer userId, Integer scenicId) {
        return userActionMapper.selectByScore(userId, scenicId);
    }

    @Override
    public boolean updateByScore(Integer userId, Integer scenicId,Integer score) {
        boolean flag=false;
        if(userActionMapper.updateByScore(userId, scenicId,score)>0){
            flag=true;
        }
        return flag;
    }
}
