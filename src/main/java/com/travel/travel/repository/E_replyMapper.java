package com.travel.travel.repository;

import com.travel.travel.entity.E_reply;
import com.travel.travel.entity.Leaders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface E_replyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(E_reply record);

    int insertSelective(E_reply record);

    E_reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(E_reply record);

    int updateByPrimaryKey(E_reply record);

    List<E_reply> getReplyByCommentId(Integer evaluateId);

    int deleteByCommentId(Integer evaluateId);

}