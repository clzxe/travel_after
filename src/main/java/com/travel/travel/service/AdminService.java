package com.travel.travel.service;

import com.travel.travel.entity.Admins;

import java.util.List;

public interface AdminService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admins
     *
     * @mbg.generated
     */
    boolean deleteByPrimaryKey(Integer adminId);

    boolean insert(Admins record);

    boolean insertSelective(Admins record);

    Admins selectByPrimaryKey(Integer adminId);

    boolean updateByPrimaryKeySelective(Admins record);

    boolean updateByPrimaryKey(Admins record);

    Admins selectByAdminAccount(String adminAccount);
}
