package com.travel.travel.repository;

import com.travel.travel.entity.Line_group;
import com.travel.travel.entity.Lines;
import com.travel.travel.entity.Scenics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Line_groupMapper {
    int deleteByPrimaryKey(Integer id);

    int updateGroupPeople(@Param("type")Integer type,@Param("id")Integer id,@Param("linePeople")Integer linePeople);

    int deleteByAuto();

    int insert(Line_group record);

    int insertSelective(Line_group record);

    Line_group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Line_group record);

    int updateByPrimaryKey(Line_group record);

    Integer  getAllCount(@Param("lineName")String lineName);

    List<Line_group> getAllLines(@Param("lineName")String lineName, @Param("current")Integer current, @Param("size")Integer size );

    List<Line_group> getLineInit(@Param("lineName")String lineName, @Param("cityId")Integer cityId,@Param("themeId")Integer themeId, @Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getLineInitCount(@Param("lineName")String lineName, @Param("cityId")Integer cityId,@Param("themeId")Integer themeId,@Param("sortType")Integer sortType);

    List<Line_group> selectByCityIdAndLineId(@Param("cityId")Integer cityId, @Param("lineId")Integer lineId);

    Line_group getLineOne(Integer id);

    List<Line_group> getLineOneByLineId(@Param("id")Integer id);

    List<Line_group> getAllLinesTheme(@Param("themeId")Integer themeId);

    List<Line_group> getLineHotLimitSix();

    List<Line_group> getLineHotLimitFour();

    List<Line_group> getLineNewLimitFour();

    List<Line_group> selectByStartTime(@Param("leaderId")Integer leaderId,@Param("type")Integer type, @Param("current")Integer current, @Param("size")Integer size);

    Integer selectByStartTimeCount(@Param("leaderId")Integer leaderId,@Param("type")Integer type);
}