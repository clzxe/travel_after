package com.travel.travel.repository;

import com.travel.travel.entity.Hotels;
import com.travel.travel.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface HotelsMapper {
    int deleteByPrimaryKey(Integer hotelId);

    int insert(Hotels record);

    int insertSelective(Hotels record);

    Hotels selectByPrimaryKey(Integer hotelId);

    int updateByPrimaryKeySelective(Hotels record);

    int updateByPrimaryKey(Hotels record);

    List<Hotels> getAllHotels(@Param("hotelName")String hotelName, @Param("hotelScenic")Integer hotelScenic, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllHotelsCount(@Param("hotelName")String hotelName, @Param("hotelScenic")Integer hotelScenic);

    List<Hotels> getAll();

    List<Hotels> getHotelInit(@Param("hotelName")String hotelName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getHotelInitCount(@Param("hotelName")String hotelName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType);
}