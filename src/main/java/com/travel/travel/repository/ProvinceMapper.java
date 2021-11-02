package com.travel.travel.repository;

import com.travel.travel.entity.City;
import com.travel.travel.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    List<Province> getAllProvince();

    List<Province> getAllProvinceByType(@Param("typeId")Integer typeId);

    Integer  getAllCount(@Param("provinceName")String provinceName);

    List<Province> getAllProvinceByName(@Param("provinceName")String provinceName, @Param("current")Integer current, @Param("size")Integer size);
}