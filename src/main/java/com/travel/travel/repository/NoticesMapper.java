package com.travel.travel.repository;

import com.travel.travel.entity.Notices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NoticesMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notices record);

    int insertSelective(Notices record);

    Notices selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notices record);

    int updateByPrimaryKey(Notices record);

    List<Notices> getNotices();

    Integer  getAllCount(@Param("noticeTitle")String noticeTitle);

    List<Notices> getAllNotices(@Param("noticeTitle")String noticeTitle, @Param("current")Integer current, @Param("size")Integer size);
}