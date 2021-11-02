package com.travel.travel.service;

import com.travel.travel.entity.UserAction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserActionService {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(UserAction record);

    boolean insertSelective(UserAction record);

    UserAction selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(UserAction record);

    boolean updateByPrimaryKey(UserAction record);

    List<UserAction> selectAll();

    List<UserAction> selectByUserId(@Param("userId")Integer userId);

    UserAction selectByScore(@Param("userId")Integer userId, @Param("scenicId")Integer scenicId);

    boolean updateByScore(@Param("userId")Integer userId,@Param("scenicId")Integer scenicId,@Param("score")Integer score);
}
