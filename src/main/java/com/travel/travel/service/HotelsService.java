package com.travel.travel.service;

import com.travel.travel.entity.Hotels;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelsService {
    boolean deleteByPrimaryKey(Integer hotelId);

    boolean insert(Hotels record);

    boolean insertSelective(Hotels record);

    Hotels selectByPrimaryKey(Integer hotelId);

    boolean updateByPrimaryKeySelective(Hotels record);

    boolean updateByPrimaryKey(Hotels record);

    List<Hotels> getAllHotels(@Param("hotelName")String hotelName, @Param("hotelScenic")Integer hotelScenic, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllHotelsCount(@Param("hotelName")String hotelName, @Param("hotelScenic")Integer hotelScenic);

    List<Hotels> getAll();

    List<Hotels> getHotelInit(@Param("hotelName")String hotelName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getHotelInitCount(@Param("hotelName")String hotelName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType);
}
