package com.travel.travel.service.Impl;

import com.travel.travel.entity.Travel_time;
import com.travel.travel.repository.Travel_timeMapper;
import com.travel.travel.service.Travel_timeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class Travel_timeServiceImpl implements Travel_timeService {
    @Resource
    private Travel_timeMapper travel_timeMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer travelTimeId) {
        boolean flag=false;
        if(travel_timeMapper.deleteByPrimaryKey(travelTimeId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Travel_time record) {
        boolean flag=false;
        if(travel_timeMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Travel_time record) {
        return false;
    }

    @Override
    public Travel_time selectByPrimaryKey(Integer travelTimeId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Travel_time record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Travel_time record) {
        boolean flag=false;
        if(travel_timeMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Travel_time> selectByLineId(Integer travelTimeLineId) {
        return travel_timeMapper.selectByLineId(travelTimeLineId);
    }
}
