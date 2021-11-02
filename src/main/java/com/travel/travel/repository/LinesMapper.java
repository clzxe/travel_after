package com.travel.travel.repository;

import com.travel.travel.entity.Lines;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinesMapper {
    int deleteByPrimaryKey(Integer lineId);

    int insert(Lines record);

    int insertSelective(Lines record);

    Lines selectByPrimaryKey(Integer lineId);

    int updateByPrimaryKeySelective(Lines record);

    int updateByPrimaryKey(Lines record);

    List<Lines> getHot_line();

    List<Lines> getNew_line();

    List<Lines> getAll();

    List<Long> getLineHotLimitSix();
    Integer  getAllCount(@Param("lineName")String lineName);

    List<Lines> getAllLines(@Param("lineName")String lineName, @Param("current") Integer current, @Param("size")Integer size);

    List<Lines> getAllLinesTheme(@Param("themeId")Integer themeId);

    List<Lines> getLineNotLineId(@Param("lineId")Integer lineId,@Param("cityId")Integer cityId);

    List<Lines> getLineThemeHotLimitSix(@Param("lineId")Integer lineId,@Param("themeId")Integer themeId,@Param("num")Integer num);
}