package com.travel.travel.repository;

import com.travel.travel.entity.User_action;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface User_actionMapper {
    int insert(User_action record);

    int insertSelective(User_action record);

    List<User_action> selectAll();

    List<User_action> selectAllUser(@Param("userId") Integer userId);



}