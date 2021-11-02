package com.travel.travel.service;

import com.travel.travel.entity.Scenics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScenicsService {
    boolean deleteByPrimaryKey(Integer scenicId);

    boolean insert(Scenics record);

    boolean insertSelective(Scenics record);

    Scenics selectByPrimaryKey(Integer scenicId);

    boolean updateByPrimaryKeySelective(Scenics record);

    boolean updateByPrimaryKey(Scenics record);

    List<Scenics> getAll();

    Integer  getAllCount(@Param("scenicName")String scenicName);

    List<Scenics> getTurn_scenic();

    List<Scenics> getScenicLimitSix(@Param("num")Integer num);

    List<Scenics> getAllUsersType(@Param("scenicId")Integer scenicId,@Param("typeId")String typeId,@Param("num")Integer num);

     List<Scenics> findByTopicidIn(List<Integer> list);

    List<Scenics> getScenicNotScenicId(@Param("scenicId")Integer scenicId,@Param("cityId")Integer cityId);

    List<Scenics> getScenicCityLimitFive(@Param("scenicId")Integer scenicId,@Param("cityId")Integer cityId);

    List<Scenics> getAllScenic(@Param("scenicName")String scenicName, @Param("current")Integer current, @Param("size")Integer size);

    List<Scenics> getScenicInit(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId, @Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size,@Param("typeId")String typeId,@Param("people")String  people,@Param("season")String  season,@Param("areaId")Integer areaId,@Param("priceUp")String priceUp,@Param("priceDown")String priceDown);

    Integer getScenicInitCount(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType,@Param("typeId")String typeId,@Param("people")String  people,@Param("season")String  season,@Param("areaId")Integer areaId,@Param("priceUp")String priceUp,@Param("priceDown")String priceDown);
}
