package com.travel.travel.repository;

import com.travel.travel.entity.UserAction;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserActionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAction record);

    int insertSelective(UserAction record);

    UserAction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAction record);

    int updateByPrimaryKey(UserAction record);

    List<UserAction> selectAll();

    UserAction selectByScore(@Param("userId")Integer userId,@Param("scenicId")Integer scenicId);

    List<UserAction> selectByUserId(@Param("userId")Integer userId);

    int updateByScore(@Param("userId")Integer userId,@Param("scenicId")Integer scenicId,@Param("score")Integer score);


}