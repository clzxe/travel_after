package com.travel.travel.repository;

import com.travel.travel.entity.Groups;
import com.travel.travel.entity.Leaders;
import com.travel.travel.entity.Scenics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GroupsMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);

    List<Groups> getAll();

    Integer  getAllCount(@Param("groupName")String groupName);

    List<Groups> getAllGroups(@Param("groupName")String groupName, @Param("current")Integer current, @Param("size")Integer size);
}