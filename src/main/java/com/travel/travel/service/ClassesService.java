package com.travel.travel.service;

import com.travel.travel.entity.Classes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesService {

    boolean deleteByPrimaryKey(Integer classId);

    boolean insert(Classes record);

    boolean insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classId);

    boolean updateByPrimaryKeySelective(Classes record);

    boolean updateByPrimaryKey(Classes record);

    List<Classes> getAllClasses(@Param("className")String className, @Param("current")Integer current, @Param("size")Integer size);

    Integer  getAllCount(@Param("className")String className);

    List<Classes> getAll();
}
