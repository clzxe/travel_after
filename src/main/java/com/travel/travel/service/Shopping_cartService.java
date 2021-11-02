package com.travel.travel.service;

import com.travel.travel.entity.Shopping_cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Shopping_cartService {
    boolean deleteByPrimaryKey(Integer scId);

    boolean insert(Shopping_cart record);

    boolean insertSelective(Shopping_cart record);

    Shopping_cart selectByPrimaryKey(Integer scId);

    boolean updateByPrimaryKeySelective(Shopping_cart record);

    boolean updateByPrimaryKey(Shopping_cart record);

    List<Shopping_cart> getAllCars(@Param("userId")Integer userId, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllCarsCount(@Param("userId")Integer userId);

    boolean updateCount(Shopping_cart record);

    List<Long> getAllCarsO(@Param("userId")Integer userId);
}
