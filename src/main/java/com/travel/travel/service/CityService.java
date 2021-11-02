package com.travel.travel.service;

import com.travel.travel.entity.City;
import com.travel.travel.entity.Themes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityService {
    boolean deleteByPrimaryKey(Integer cityId);

    boolean insert(City record);

    boolean insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    boolean updateByPrimaryKeySelective(City record);

    boolean updateByPrimaryKey(City record);

    List<City> getCities();

    City getCitieByName(String cityName);


    List<City> getCitiesByProvinceId(@Param("provinceId")Integer provinceId);

    Integer  getAllCount(@Param("cityName")String cityName);

    List<City> getAllCity(@Param("cityName")String cityName, @Param("current")Integer current, @Param("size")Integer size);
}
