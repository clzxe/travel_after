package com.travel.travel.service.Impl;

import com.travel.travel.entity.Hotel_rooms;
import com.travel.travel.repository.Hotel_roomsMapper;
import com.travel.travel.service.Hotel_roomsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class Hotel_roomsServiceImpl implements Hotel_roomsService {
    @Resource
    private Hotel_roomsMapper hotel_roomsMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer hotelRoomId) {
        boolean flag=false;
        if(hotel_roomsMapper.deleteByPrimaryKey(hotelRoomId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Hotel_rooms record) {
        boolean flag=false;
        if(hotel_roomsMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Hotel_rooms record) {
        return false;
    }

    @Override
    public Hotel_rooms selectByPrimaryKey(Integer hotelRoomId) {
        return hotel_roomsMapper.selectByPrimaryKey(hotelRoomId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Hotel_rooms record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Hotel_rooms record) {
        boolean flag=false;
        if(hotel_roomsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Hotel_rooms> getRoomByHotelId(Integer hotelId) {
        return hotel_roomsMapper.getRoomByHotelId(hotelId);
    }
}
