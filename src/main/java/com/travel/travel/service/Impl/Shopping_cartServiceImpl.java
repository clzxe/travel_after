package com.travel.travel.service.Impl;

import com.travel.travel.entity.Shopping_cart;
import com.travel.travel.repository.Shopping_cartMapper;
import com.travel.travel.service.Shopping_cartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Shopping_cartServiceImpl implements Shopping_cartService {
    @Resource
    private Shopping_cartMapper shoppingCartMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer scId) {
        boolean flag=false;
        if(shoppingCartMapper.deleteByPrimaryKey(scId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Shopping_cart record) {
        boolean flag=false;
        if(shoppingCartMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Shopping_cart record) {
        return false;
    }

    @Override
    public Shopping_cart selectByPrimaryKey(Integer scId) {
        return shoppingCartMapper.selectByPrimaryKey(scId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Shopping_cart record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Shopping_cart record) {
        boolean flag=false;
        if(shoppingCartMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Shopping_cart> getAllCars(Integer userId, Integer current, Integer size) {
        return shoppingCartMapper.getAllCars(userId, current, size);
    }

    @Override
    public Integer getAllCarsCount(Integer userId) {
        return shoppingCartMapper.getAllCarsCount(userId);
    }

    @Override
    public boolean updateCount(Shopping_cart record) {
        boolean flag=false;
        if(shoppingCartMapper.updateCount(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Long> getAllCarsO( @Param("userId")Integer userId) {
        return shoppingCartMapper.getAllCarsO(userId);
    }
}
