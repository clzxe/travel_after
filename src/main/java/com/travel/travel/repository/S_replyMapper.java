package com.travel.travel.repository;

import com.travel.travel.entity.E_reply;
import com.travel.travel.entity.S_reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface S_replyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(S_reply record);

    int insertSelective(S_reply record);

    S_reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(S_reply record);

    int updateByPrimaryKey(S_reply record);

    List<S_reply> getReplyByCommentId(Integer commentId);

    int deleteByCommentId(Integer commentId);
}