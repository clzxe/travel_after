package com.travel.travel.service;

import com.travel.travel.entity.Line_group;
import com.travel.travel.entity.Strategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyService {
    boolean deleteByPrimaryKey(Integer strategyId);

    boolean insert(Strategy record);

    boolean insertSelective(Strategy record);

    Strategy selectByPrimaryKey(Integer strategyId);

    boolean updateByPrimaryKeySelective(Strategy record);

    boolean updateByPrimaryKeyWithBLOBs(Strategy record);

    boolean updateByPrimaryKey(Strategy record);

    List<Strategy> getBest_strategy();

    List<Strategy> getBestLimitSix();

    List<Strategy> getBestLimitFour();

    List<Strategy> getAllStrategy(@Param("strategyName")String strategyName, @Param("scenicId")Integer scenicId, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllStrategyCount(@Param("strategyName")String strategyName, @Param("scenicId")Integer scenicId);

    List<Strategy> getStrategyInit(@Param("strategyName")String strategyName, @Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getStrategyInitCount(@Param("strategyName")String strategyName,@Param("sortType")Integer sortType);

    Integer getAllStrategyCountUserId( @Param("userId")Integer userId);

    List<Strategy> getStrategyInitUp(@Param("scenicName")String scenicName,@Param("userId")Integer userId,@Param("start")String start,@Param("end")String end, @Param("current")Integer current, @Param("size")Integer size);

    Integer getStrategyInitUpCount(@Param("scenicName")String scenicName,@Param("userId")Integer userId,@Param("start")String start,@Param("end")String end);
}


