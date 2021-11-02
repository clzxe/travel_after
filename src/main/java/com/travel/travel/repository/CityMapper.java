package com.travel.travel.repository;

import com.travel.travel.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    List<City> getCities();

    City getCitieByName(String cityName);


    List<City> getCitiesByProvinceId(@Param("provinceId")Integer provinceId);

    Integer  getAllCount(@Param("cityName")String cityName);

    List<City> getAllCity(@Param("cityName")String cityName, @Param("current")Integer current, @Param("size")Integer size);
}