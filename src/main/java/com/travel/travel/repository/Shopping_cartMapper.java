package com.travel.travel.repository;

import com.travel.travel.entity.Orders;
import com.travel.travel.entity.Shopping_cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Shopping_cartMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(Shopping_cart record);

    int insertSelective(Shopping_cart record);

    Shopping_cart selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(Shopping_cart record);

    int updateByPrimaryKey(Shopping_cart record);

    List<Shopping_cart> getAllCars(@Param("userId")Integer userId, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllCarsCount(@Param("userId")Integer userId);

    int updateCount(Shopping_cart record);

    List<Long> getAllCarsO(@Param("userId")Integer userId);
}