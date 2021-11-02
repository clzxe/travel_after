package com.travel.travel.service.Impl;

import com.travel.travel.entity.Input_money;
import com.travel.travel.repository.Input_moneyMapper;
import com.travel.travel.service.Input_MoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class Input_MoneyServiceImpl implements Input_MoneyService {
   @Resource
   private Input_moneyMapper inputMoneyMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer inId) {
        return false;
    }

    @Override
    public boolean insert(Input_money record) {
        boolean flag=false;
        if(inputMoneyMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Input_money record) {
        return false;
    }

    @Override
    public Input_money selectByPrimaryKey(Integer inId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Input_money record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Input_money record) {
        return false;
    }

    @Override
    public List<Input_money> selectByUserId(Integer userId) {
        return inputMoneyMapper.selectByUserId(userId);
    }
}
