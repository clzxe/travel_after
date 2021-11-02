package com.travel.travel.repository;

import com.travel.travel.entity.E_comments;
import com.travel.travel.entity.S_comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface S_commentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(S_comments record);

    int insertSelective(S_comments record);

    S_comments selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(S_comments record);

    int updateByPrimaryKey(S_comments record);

    Integer  getAllCount(@Param("commentContent")String commentContent);

    List<S_comments> getAllS_comments(@Param("commentContent")String commentContent, @Param("current")Integer current, @Param("size")Integer size );

    Integer  getAllCountById(@Param("strategyId")Integer strategyId);

    List<S_comments> getAllS_commentsById(@Param("strategyId")Integer strategyId, @Param("current")Integer current, @Param("size")Integer size );
}