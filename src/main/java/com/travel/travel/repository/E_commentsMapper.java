package com.travel.travel.repository;

import com.travel.travel.entity.E_comments;
import com.travel.travel.entity.Lines;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface E_commentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(E_comments record);

    int insertSelective(E_comments record);

    E_comments selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(E_comments record);

    int updateByPrimaryKey(E_comments record);

    Integer  getAllCount(@Param("commentContent")String commentContent,@Param("searchType")Integer searchType);

    List<E_comments> getAllE_comments(@Param("commentContent")String commentContent,@Param("searchType")Integer searchType, Integer current, Integer size );


}