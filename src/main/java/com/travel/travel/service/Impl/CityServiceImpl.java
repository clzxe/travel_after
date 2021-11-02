package com.travel.travel.service.Impl;

import com.travel.travel.entity.City;
import com.travel.travel.entity.Themes;
import com.travel.travel.repository.CityMapper;
import com.travel.travel.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer cityId) {
        boolean flag=false;
        if(cityMapper.deleteByPrimaryKey(cityId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(City record) {
        boolean flag=false;
        if(cityMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(City record) {
        return false;
    }

    @Override
    public City selectByPrimaryKey(Integer cityId) {
        return cityMapper.selectByPrimaryKey(cityId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(City record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(City record) {
        boolean flag=false;
        if(cityMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<City> getCities() {
        return cityMapper.getCities();
    }

    @Override
    public City getCitieByName(String cityName) {
        return cityMapper.getCitieByName(cityName);
    }

    @Override
    public List<City> getCitiesByProvinceId(Integer provinceId) {
        return cityMapper.getCitiesByProvinceId(provinceId);
    }

    @Override
    public Integer getAllCount(String cityName) {
        return cityMapper.getAllCount(cityName);
    }

    @Override
    public List<City> getAllCity(String cityName, Integer current, Integer size) {
        return cityMapper.getAllCity(cityName,current,size);
    }
}
