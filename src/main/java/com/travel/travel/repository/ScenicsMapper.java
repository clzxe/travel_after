package com.travel.travel.repository;

import com.travel.travel.entity.Hotels;
import com.travel.travel.entity.Scenics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ScenicsMapper {
    int deleteByPrimaryKey(Integer scenicId);

    int insert(Scenics record);

    int insertSelective(Scenics record);

    Scenics selectByPrimaryKey(Integer scenicId);

    int updateByPrimaryKeySelective(Scenics record);

    int updateByPrimaryKey(Scenics record);

    List<Scenics> getTurn_scenic();

    List<Scenics> getScenicLimitSix(@Param("num")Integer num);

    List<Scenics> getAll();

    List<Scenics> getAllUsersType(@Param("scenicId")Integer scenicId,@Param("typeId")String typeId,@Param("num")Integer num);

    Integer  getAllCount(@Param("scenicName")String scenicName);

    List<Scenics> getAllScenic(@Param("scenicName")String scenicName, @Param("current")Integer current, @Param("size")Integer size);

    List<Scenics> getScenicInit(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId, @Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size,@Param("typeId")String typeId,@Param("people")String  people,@Param("season")String  season,@Param("areaId")Integer areaId,@Param("priceUp")String priceUp,@Param("priceDown")String priceDown);

    Integer getScenicInitCount(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId,@Param("sortType")Integer sortType,@Param("typeId")String typeId,@Param("people")String  people,@Param("season")String  season,@Param("areaId")Integer areaId,@Param("priceUp")String priceUp,@Param("priceDown")String priceDown);

    List<Scenics> getScenicNotScenicId(@Param("scenicId")Integer scenicId,@Param("cityId")Integer cityId);

    List<Scenics> getScenicCityLimitFive(@Param("scenicId")Integer scenicId,@Param("cityId")Integer cityId);
}