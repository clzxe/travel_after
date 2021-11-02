package com.travel.travel.service;

import com.travel.travel.entity.E_reply;

import java.util.List;

public interface E_replyService {
    boolean deleteByPrimaryKey(Integer replyId);

    boolean insert(E_reply record);

    boolean insertSelective(E_reply record);

    E_reply selectByPrimaryKey(Integer replyId);

    boolean updateByPrimaryKeySelective(E_reply record);

    boolean updateByPrimaryKey(E_reply record);

    List<E_reply> getReplyByCommentId(Integer commentId);

    boolean deleteByCommentId(Integer commentId);
}
