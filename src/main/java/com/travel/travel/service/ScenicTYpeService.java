package com.travel.travel.service;

import com.travel.travel.entity.ScenicType;

import java.util.List;

public interface ScenicTYpeService {
    boolean deleteByPrimaryKey(Integer typeId);

    boolean insert(ScenicType record);

    boolean insertSelective(ScenicType record);

    ScenicType selectByPrimaryKey(Integer typeId);

    boolean updateByPrimaryKeySelective(ScenicType record);

    boolean updateByPrimaryKey(ScenicType record);

    List<ScenicType> getTypeAllNav();
}
