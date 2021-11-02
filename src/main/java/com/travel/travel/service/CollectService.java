package com.travel.travel.service;

import com.travel.travel.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {
    boolean deleteByPrimaryKey(Integer collectId);

    boolean saveByPrimaryKey(Integer collectId);

    boolean insert(Collect record);

    boolean insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    boolean updateByPrimaryKeySelective(Collect record);

    boolean updateByPrimaryKey(Collect record);

    boolean updateByCollect(Integer collectId);

    Collect selectByUserIdAndScenicId(@Param("userId")Integer userId, @Param("scenicId")Integer scenicId, @Param("collectType")Integer collectType);

    List<Collect> selectByUserIdList(@Param("userId")Integer userId);

    Integer selectByUserIdListCount (@Param("userId")Integer userId);

    Integer getCollectCount (@Param("scenicId")Integer scenicId,@Param("collectType")Integer collectType);

    List<Collect> getAllCollectUp(@Param("searchTitle")String searchTitle,  @Param("userId")Integer userId, @Param("start")String start, @Param("end")String end, @Param("collectType")Integer orderType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getCollectCountUp(@Param("searchTitle")String searchTitle,  @Param("userId")Integer userId, @Param("start")String start, @Param("end")String end, @Param("collectType")Integer orderType);
}
