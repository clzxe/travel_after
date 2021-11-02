package com.travel.travel.service;

import com.travel.travel.entity.Notices;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticesService {
    boolean deleteByPrimaryKey(Integer noticeId);

    boolean insert(Notices record);

    boolean insertSelective(Notices record);

    Notices selectByPrimaryKey(Integer noticeId);

    boolean updateByPrimaryKeySelective(Notices record);

    boolean updateByPrimaryKey(Notices record);

    List<Notices> getNotices();

    Integer  getAllCount(@Param("noticeTitle")String noticeTitle);

    List<Notices> getAllNotices(@Param("noticeTitle")String noticeTitle, @Param("current")Integer current, @Param("size")Integer size);
}
