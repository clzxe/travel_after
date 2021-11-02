package com.travel.travel.service;

import com.travel.travel.entity.S_reply;

import java.util.List;

public interface S_replyService {
    boolean deleteByPrimaryKey(Integer replyId);

    boolean insert(S_reply record);

    boolean insertSelective(S_reply record);

    S_reply selectByPrimaryKey(Integer replyId);

    boolean updateByPrimaryKeySelective(S_reply record);

    boolean updateByPrimaryKey(S_reply record);

    List<S_reply> getReplyByCommentId(Integer commentId);

    boolean deleteByCommentId(Integer commentId);
}
