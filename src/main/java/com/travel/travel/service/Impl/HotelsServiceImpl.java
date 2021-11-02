package com.travel.travel.service.Impl;

import com.travel.travel.entity.Hotels;
import com.travel.travel.repository.HotelsMapper;
import com.travel.travel.service.HotelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelsServiceImpl implements HotelsService {
    @Resource
    private HotelsMapper hotelsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer hotelId) {
        boolean flag=false;
        if(hotelsMapper.deleteByPrimaryKey(hotelId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Hotels record) {
        boolean flag=false;
        if(hotelsMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Hotels record) {
        return false;
    }

    @Override
    public Hotels selectByPrimaryKey(Integer hotelId) {
        return hotelsMapper.selectByPrimaryKey(hotelId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Hotels record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Hotels record) {
        boolean flag=false;
        if(hotelsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Hotels> getAllHotels(String hotelName, Integer hotelScenic, Integer current, Integer size) {
        return hotelsMapper.getAllHotels(hotelName,hotelScenic,current,size);
    }

    @Override
    public Integer getAllHotelsCount(String hotelName, Integer hotelScenic) {
        return hotelsMapper.getAllHotelsCount(hotelName,hotelScenic);
    }

    @Override
    public List<Hotels> getAll() {
        return hotelsMapper.getAll();
    }

    @Override
    public List<Hotels> getHotelInit(String hotelName, Integer cityId, Integer sortType, Integer current, Integer size) {
        return hotelsMapper.getHotelInit(hotelName,cityId,sortType,current,size);
    }

    @Override
    public Integer getHotelInitCount(String hotelName, Integer cityId, Integer sortType) {
        return hotelsMapper.getHotelInitCount(hotelName,cityId,sortType);
    }
}
