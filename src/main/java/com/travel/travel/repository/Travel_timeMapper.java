package com.travel.travel.repository;

import com.travel.travel.entity.Travel_time;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Travel_timeMapper {
    int deleteByPrimaryKey(Integer travelTimeId);

    int insert(Travel_time record);

    int insertSelective(Travel_time record);

    Travel_time selectByPrimaryKey(Integer travelTimeId);

    int updateByPrimaryKeySelective(Travel_time record);

    int updateByPrimaryKey(Travel_time record);

    List<Travel_time> selectByLineId(Integer travelTimeLineId);
}