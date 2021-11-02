package com.travel.travel.service;

import com.travel.travel.entity.S_comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface S_commentsService {
    boolean deleteByPrimaryKey(Integer commentId);

    boolean insert(S_comments record);

    boolean insertSelective(S_comments record);

    S_comments selectByPrimaryKey(Integer commentId);

    boolean updateByPrimaryKeySelective(S_comments record);

    boolean updateByPrimaryKey(S_comments record);

    Integer  getAllCount(@Param("commentContent")String commentContent);

    List<S_comments> getAllS_comments(@Param("commentContent")String commentContent, Integer current, Integer size );

    Integer  getAllCountById(@Param("strategyId")Integer strategyId);

    List<S_comments> getAllS_commentsById(@Param("strategyId")Integer strategyId, Integer current, Integer size );
}
