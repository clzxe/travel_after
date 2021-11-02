package com.travel.travel.repository;

import com.travel.travel.entity.ScenicType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ScenicTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(ScenicType record);

    int insertSelective(ScenicType record);

    ScenicType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(ScenicType record);

    int updateByPrimaryKey(ScenicType record);

    List<ScenicType> getTypeAllNav();
}