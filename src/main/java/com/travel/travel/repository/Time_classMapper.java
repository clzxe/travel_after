package com.travel.travel.repository;

import com.travel.travel.entity.Time_class;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Time_classMapper {
    int deleteByPrimaryKey(Integer timeClassId);

    int insert(Time_class record);

    int insertSelective(Time_class record);

    Time_class selectByPrimaryKey(Integer timeClassId);

    int updateByPrimaryKeySelective(Time_class record);

    int updateByPrimaryKey(Time_class record);
}