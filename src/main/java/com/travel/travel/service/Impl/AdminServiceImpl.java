package com.travel.travel.service.Impl;

import com.travel.travel.entity.Admins;
import com.travel.travel.repository.AdminsMapper;
import com.travel.travel.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminsMapper adminsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer adminId) {
        return false;
    }

    @Override
    public boolean insert(Admins record) {
        return false;
    }

    @Override
    public boolean insertSelective(Admins record) {
        return false;
    }

    @Override
    public Admins selectByPrimaryKey(Integer adminId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Admins record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Admins record) {
        return false;
    }

    @Override
    public Admins selectByAdminAccount(String adminAccount) {
        return adminsMapper.selectByAdminAccount(adminAccount);
    }
}
