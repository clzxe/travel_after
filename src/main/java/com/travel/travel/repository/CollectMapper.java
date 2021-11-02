package com.travel.travel.repository;

import com.travel.travel.entity.Collect;
import com.travel.travel.entity.Evaluates;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int saveByPrimaryKey(Integer collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int updateByCollect(Integer collectId);

    Collect selectByUserIdAndScenicId(@Param("userId")Integer userId,@Param("scenicId")Integer scenicId,@Param("collectType")Integer collectType);

    List<Collect> selectByUserIdList(@Param("userId")Integer userId);

    Integer selectByUserIdListCount (@Param("userId")Integer userId);

    Integer getCollectCount (@Param("scenicId")Integer scenicId,@Param("collectType")Integer collectType);

    List<Collect> getAllCollectUp(@Param("searchTitle")String searchTitle,  @Param("userId")Integer userId, @Param("start")String start, @Param("end")String end, @Param("collectType")Integer orderType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getCollectCountUp(@Param("searchTitle")String searchTitle,  @Param("userId")Integer userId, @Param("start")String start, @Param("end")String end, @Param("collectType")Integer orderType);
}