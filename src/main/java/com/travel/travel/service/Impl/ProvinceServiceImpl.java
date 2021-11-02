package com.travel.travel.service.Impl;

import com.travel.travel.entity.Province;
import com.travel.travel.repository.ProvinceMapper;
import com.travel.travel.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Resource
    private ProvinceMapper provinceMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer provinceId) {
        return false;
    }

    @Override
    public boolean insert(Province record) {
        return false;
    }

    @Override
    public boolean insertSelective(Province record) {
        return false;
    }

    @Override
    public Province selectByPrimaryKey(Integer provinceId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Province record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Province record) {
        return false;
    }

    @Override
    public List<Province> getAllProvince() {
        return provinceMapper.getAllProvince();
    }

    @Override
    public List<Province> getAllProvinceByType(Integer typeId) {
        return provinceMapper.getAllProvinceByType(typeId);
    }

    @Override
    public Integer getAllCount(String provinceName) {
        return provinceMapper.getAllCount(provinceName);
    }

    @Override
    public List<Province> getAllProvinceByName(String provinceName, Integer current, Integer size) {
        return provinceMapper.getAllProvinceByName(provinceName,current,size);
    }
}
