package com.travel.travel.service.Impl;

import com.travel.travel.entity.Lines;
import com.travel.travel.entity.Scenics;
import com.travel.travel.repository.ScenicsMapper;
import com.travel.travel.service.ScenicsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ScenicsServiceImpl implements ScenicsService {
    @Resource
    private ScenicsMapper scenicsMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer scenicId) {
        boolean flag=false;
        if(scenicsMapper.deleteByPrimaryKey(scenicId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Scenics record) {
        boolean flag=false;
        if(scenicsMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Scenics record) {
        return false;
    }

    @Override
    public Scenics selectByPrimaryKey(Integer scenicId) {
        return scenicsMapper.selectByPrimaryKey(scenicId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Scenics record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Scenics record) {
        boolean flag=false;
        if(scenicsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Scenics> getAll() {
        return scenicsMapper.getAll();
    }

    @Override
    public Integer getAllCount(String scenicName) {
        return scenicsMapper.getAllCount(scenicName);
    }

    @Override
    public List<Scenics> getTurn_scenic() {
        return scenicsMapper.getTurn_scenic();
    }

    @Override
    public List<Scenics> getScenicLimitSix(Integer num) {
        return scenicsMapper.getScenicLimitSix(num);
    }

    @Override
    public List<Scenics> getAllUsersType(Integer scenicId, String typeId, Integer num) {
        return scenicsMapper.getAllUsersType(scenicId, typeId,num);
    }

    @Override
    public List<Scenics> getScenicNotScenicId(Integer scenicId, Integer cityId) {
        return scenicsMapper.getScenicNotScenicId(scenicId, cityId);
    }

    @Override
    public List<Scenics> getScenicCityLimitFive(Integer scenicId, Integer cityId) {
        return scenicsMapper.getScenicCityLimitFive(scenicId, cityId);
    }


    @Override
    public List<Scenics> getAllScenic(String scenicName, Integer current, Integer size) {
        return scenicsMapper.getAllScenic(scenicName,current,size);
    }

    @Override
    public List<Scenics> getScenicInit(String scenicName, Integer cityId, Integer sortType, Integer current, Integer size, String typeId, String people, String season, Integer areaId, String priceUp, String priceDown) {
        return scenicsMapper.getScenicInit(scenicName, cityId, sortType, current, size, typeId, people, season, areaId, priceUp, priceDown);
    }

    @Override
    public Integer getScenicInitCount(String scenicName, Integer cityId, Integer sortType, String typeId, String people, String season, Integer areaId, String priceUp, String priceDown) {
        return scenicsMapper.getScenicInitCount(scenicName, cityId, sortType, typeId, people, season, areaId, priceUp, priceDown);
    }


    @Override
    public List<Scenics> findByTopicidIn(List<Integer> list) {
        List<Scenics> scenicsList=new ArrayList<>();
        for(Integer scenicId :list){
            Scenics scenics=scenicsMapper.selectByPrimaryKey(scenicId);
            scenicsList.add(scenics);
        }
        return scenicsList;
    }
}
