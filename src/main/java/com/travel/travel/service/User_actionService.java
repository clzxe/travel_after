package com.travel.travel.service;

import com.travel.travel.entity.User_action;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface User_actionService {
    List<User_action> selectAll();

    List<User_action> selectAllUser(@Param("userId") Integer userId);

}
