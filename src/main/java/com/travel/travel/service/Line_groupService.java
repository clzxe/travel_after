package com.travel.travel.service;

import com.travel.travel.entity.Line_group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Line_groupService {
    boolean deleteByPrimaryKey(Integer id);

    boolean updateGroupPeople(@Param("type")Integer type,@Param("id")Integer id,@Param("linePeople")Integer linePeople);

    boolean deleteByAuto();

    boolean insert(Line_group record);

    boolean insertSelective(Line_group record);

    Line_group selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Line_group record);

    boolean updateByPrimaryKey(Line_group record);

    Integer  getAllCount(@Param("lineName")String lineName);

    List<Line_group> getAllLines(@Param("lineName")String lineName, Integer current, Integer size );

    List<Line_group> getLineInit(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId,@Param("themeId")Integer themeId, @Param("sortType")Integer sortType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getLineInitCount(@Param("scenicName")String scenicName, @Param("cityId")Integer cityId,@Param("themeId")Integer themeId,@Param("sortType")Integer sortType);

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
