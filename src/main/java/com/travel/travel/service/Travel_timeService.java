package com.travel.travel.service;


import com.travel.travel.entity.Travel_time;

import java.util.List;

public interface Travel_timeService {
    boolean deleteByPrimaryKey(Integer travelTimeId);

    boolean insert(Travel_time record);

    boolean insertSelective(Travel_time record);

    Travel_time selectByPrimaryKey(Integer travelTimeId);

    boolean updateByPrimaryKeySelective(Travel_time record);

    boolean updateByPrimaryKey(Travel_time record);

    List<Travel_time> selectByLineId(Integer travelTimeLineId);
}
