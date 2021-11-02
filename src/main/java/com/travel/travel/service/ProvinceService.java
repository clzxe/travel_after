package com.travel.travel.service;

import com.travel.travel.entity.Province;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProvinceService {
    boolean deleteByPrimaryKey(Integer provinceId);

    boolean insert(Province record);

    boolean insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    boolean updateByPrimaryKeySelective(Province record);

    boolean updateByPrimaryKey(Province record);

    List<Province> getAllProvince();

    List<Province> getAllProvinceByType(@Param("typeId")Integer typeId);

    Integer  getAllCount(@Param("provinceName")String provinceName);

    List<Province> getAllProvinceByName(@Param("provinceName")String provinceName, @Param("current")Integer current, @Param("size")Integer size);
}
