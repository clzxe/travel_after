package com.travel.travel.service;

import com.travel.travel.entity.Input_money;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Input_MoneyService {
    boolean deleteByPrimaryKey(Integer inId);

    boolean insert(Input_money record);

    boolean insertSelective(Input_money record);

    Input_money selectByPrimaryKey(Integer inId);

    boolean updateByPrimaryKeySelective(Input_money record);

    boolean updateByPrimaryKey(Input_money record);

    List<Input_money> selectByUserId(@Param("userId") Integer userId);
}
