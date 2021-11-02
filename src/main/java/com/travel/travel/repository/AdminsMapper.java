package com.travel.travel.repository;

import com.travel.travel.entity.Admins;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminsMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admins record);

    int insertSelective(Admins record);

    Admins selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admins record);

    int updateByPrimaryKey(Admins record);

    Admins selectByAdminAccount(String adminAccount);
}