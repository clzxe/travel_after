package com.travel.travel.service;

import com.travel.travel.entity.Groups;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupsService {

    boolean deleteByPrimaryKey(Integer groupId);

    boolean insert(Groups record);

    boolean insertSelective(Groups record);

    Groups selectByPrimaryKey(Integer groupId);

    boolean updateByPrimaryKeySelective(Groups record);

    boolean updateByPrimaryKey(Groups record);

    List<Groups> getAll();

    Integer  getAllCount(@Param("groupName")String groupName);

    List<Groups> getAllGroups(@Param("groupName")String groupName, @Param("current")Integer current, @Param("size")Integer size);
}
