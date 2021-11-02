package com.travel.travel.service.Impl;

import com.travel.travel.entity.User_action;
import com.travel.travel.repository.User_actionMapper;
import com.travel.travel.service.User_actionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class User_actionServiceImpl implements User_actionService {
    @Resource
    private User_actionMapper user_actionMapper;
    @Override
    public List<User_action> selectAll() {
        return user_actionMapper.selectAll();
    }


    @Override
    public List<User_action> selectAllUser(Integer userId) {
        return user_actionMapper.selectAllUser(userId);
    }


}
