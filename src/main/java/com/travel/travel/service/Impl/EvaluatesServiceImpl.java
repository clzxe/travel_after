package com.travel.travel.service.Impl;

import com.travel.travel.entity.Evaluates;
import com.travel.travel.repository.EvaluatesMapper;
import com.travel.travel.service.EvaluatesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvaluatesServiceImpl implements EvaluatesService {
    @Resource
    private EvaluatesMapper evaluatesMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer evaluateId) {
        boolean flag=false;
        if(evaluatesMapper.deleteByPrimaryKey(evaluateId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Evaluates record) {
        boolean flag=false;
        if(evaluatesMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Evaluates record) {
        return false;
    }

    @Override
    public Evaluates selectByPrimaryKey(Integer evaluateId) {
        return evaluatesMapper.selectByPrimaryKey(evaluateId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Evaluates record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Evaluates record) {
        boolean flag=false;
        if(evaluatesMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Evaluates> getAllEvaluates(String evaluateContent, Integer evaluateLevel, Integer current, Integer size) {
        return evaluatesMapper.getAllEvaluates(evaluateContent,evaluateLevel,current,size);
    }

    @Override
    public Integer getAllEvaluatesCount(String evaluateContent, Integer evaluateLevel) {
        return evaluatesMapper.getAllEvaluatesCount(evaluateContent,evaluateLevel);
    }

    @Override
    public List<Evaluates> getAllEvaluatesByLineId(Integer lineId, Integer type,Integer level ,Integer current, Integer size) {
        return evaluatesMapper.getAllEvaluatesByLineId(lineId, type,level, current, size);
    }

    @Override
    public Integer getAllEvaluatesByLineIdCount(Integer lineId, Integer type,Integer level) {
        return evaluatesMapper.getAllEvaluatesByLineIdCount(lineId, type,level);
    }

    @Override
    public List<Evaluates> getAllEvaluatesUp(String orderName, Integer evaluateLevel,@Param("userId")Integer userId, String start, String end, Integer orderType, Integer current, Integer size) {
        return evaluatesMapper.getAllEvaluatesUp(orderName, evaluateLevel,userId, start, end, orderType, current, size);
    }

    @Override
    public Integer getAllEvaluatesCountUp(String orderName, Integer evaluateLevel, @Param("userId")Integer userId, String start, String end, Integer orderType) {
        return evaluatesMapper.getAllEvaluatesCountUp(orderName,evaluateLevel,userId, start, end, orderType);
    }


}
