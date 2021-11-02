package com.travel.travel.repository;

import com.travel.travel.entity.Input_money;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
@Mapper
public interface Input_moneyMapper {
    int deleteByPrimaryKey(Integer inId);

    int insert(Input_money record);

    int insertSelective(Input_money record);

    Input_money selectByPrimaryKey(Integer inId);

    int updateByPrimaryKeySelective(Input_money record);

    int updateByPrimaryKey(Input_money record);

    List<Input_money> selectByUserId(@Param("userId") Integer userId);
}