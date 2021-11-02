package com.travel.travel.repository;

import com.travel.travel.entity.Classes;
import com.travel.travel.entity.Themes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClassesMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getAllClasses(@Param("className")String className, @Param("current")Integer current, @Param("size")Integer size);

    Integer  getAllCount(@Param("className")String className);

    List<Classes> getAll();
}