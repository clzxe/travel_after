package com.travel.travel.repository;

import com.travel.travel.entity.Evaluates;
import com.travel.travel.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface EvaluatesMapper {
    int deleteByPrimaryKey(Integer evaluateId);

    int insert(Evaluates record);

    int insertSelective(Evaluates record);

    Evaluates selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(Evaluates record);

    int updateByPrimaryKey(Evaluates record);

    List<Evaluates> getAllEvaluates(@Param("evaluateContent")String evaluateContent, @Param("evaluateLevel")Integer evaluateLevel, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllEvaluatesCount(@Param("evaluateContent")String evaluateContent, @Param("evaluateLevel")Integer evaluateLevel);

    List<Evaluates> getAllEvaluatesByLineId(@Param("lineId")Integer lineId, @Param("type")Integer type,@Param("level")Integer level, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllEvaluatesByLineIdCount(@Param("lineId")Integer lineId, @Param("type")Integer type,@Param("level")Integer level);

    List<Evaluates> getAllEvaluatesUp(@Param("orderName")String orderName, @Param("evaluateLevel")Integer evaluateLevel,@Param("userId")Integer userId,@Param("start")String start,@Param("end")String end,@Param("orderType")Integer orderType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllEvaluatesCountUp(@Param("orderName")String orderName, @Param("evaluateLevel")Integer evaluateLevel,@Param("userId")Integer userId,@Param("start")String start,@Param("end")String end,@Param("orderType")Integer orderType);
}