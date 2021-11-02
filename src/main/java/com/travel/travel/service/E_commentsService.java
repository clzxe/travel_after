package com.travel.travel.service;

import com.travel.travel.entity.E_comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface E_commentsService {
    boolean deleteByPrimaryKey(Integer commentId);

    boolean insert(E_comments record);

    boolean insertSelective(E_comments record);

    E_comments selectByPrimaryKey(Integer commentId);

    boolean updateByPrimaryKeySelective(E_comments record);

    boolean updateByPrimaryKey(E_comments record);

    Integer  getAllCount(@Param("commentContent")String commentContent, @Param("searchType")Integer searchType);

    List<E_comments> getAllE_comments(@Param("commentContent")String commentContent, @Param("searchType")Integer searchType, Integer current, Integer size );

}
