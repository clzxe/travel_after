package com.travel.travel.service.Impl;

import com.travel.travel.entity.Line_group;
import com.travel.travel.entity.Strategy;
import com.travel.travel.repository.StrategyMapper;
import com.travel.travel.service.StrategyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StrategyServiceImpl implements StrategyService {
    @Resource
    private StrategyMapper strategyMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer strategyId) {
        boolean flag=false;
        if(strategyMapper.deleteByPrimaryKey(strategyId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Strategy record) {
        boolean flag=false;
        if(strategyMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Strategy record) {
        return false;
    }

    @Override
    public Strategy selectByPrimaryKey(Integer strategyId) {
        return strategyMapper.selectByPrimaryKey(strategyId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Strategy record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKeyWithBLOBs(Strategy record) {
        boolean flag=false;
        if(strategyMapper.updateByPrimaryKeyWithBLOBs(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateByPrimaryKey(Strategy record) {
        return false;
    }

    @Override
    public List<Strategy> getBest_strategy() {
        return strategyMapper.getBest_strategy();
    }

    @Override
    public List<Strategy> getBestLimitSix() {
        return strategyMapper.getBestLimitSix();
    }

    @Override
    public List<Strategy> getBestLimitFour() {
        return strategyMapper.getBestLimitFour();
    }

    @Override
    public List<Strategy> getAllStrategy(String strategyName, Integer scenicId, Integer current, Integer size) {
        return strategyMapper.getAllStrategy(strategyName,scenicId,current,size);
    }

    @Override
    public Integer getAllStrategyCount(String strategyName, Integer scenicId) {
        return strategyMapper.getAllStrategyCount(strategyName,scenicId);
    }

    @Override
    public List<Strategy> getStrategyInit(String strategyName, Integer sortType, Integer current, Integer size) {
        return strategyMapper.getStrategyInit(strategyName,sortType,current,size);
    }

    @Override
    public Integer getStrategyInitCount(String strategyName, Integer sortType) {
        return strategyMapper.getStrategyInitCount(strategyName,sortType);
    }

    @Override
    public Integer getAllStrategyCountUserId(Integer userId) {
        return strategyMapper.getAllStrategyCountUserId(userId);
    }

    @Override
    public List<Strategy> getStrategyInitUp(String scenicName, Integer userId, String start, String end, Integer current, Integer size) {
        return strategyMapper.getStrategyInitUp(scenicName, userId, start, end, current, size);
    }

    @Override
    public Integer getStrategyInitUpCount(String scenicName, Integer userId, String start, String end) {
        return strategyMapper.getStrategyInitUpCount(scenicName, userId, start, end);
    }
}
