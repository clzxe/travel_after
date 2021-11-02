package com.travel.travel.service;

import com.travel.travel.entity.Lines;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinesService {
    boolean deleteByPrimaryKey(Integer lineId);

    boolean insert(Lines record);

    boolean insertSelective(Lines record);

    Lines selectByPrimaryKey(Integer lineId);

    boolean updateByPrimaryKeySelective(Lines record);

    boolean updateByPrimaryKey(Lines record);

    List<Lines> getHot_line();
    List<Lines> getNew_line();

    List<Lines> getAll();

    List<Long> getLineHotLimitSix();

    List<Lines> findByTopicidIn( List<Long> list);

    Integer  getAllCount(@Param("lineName")String lineName);

    List<Lines> getAllLines(String lineName, Integer current,Integer size);

    List<Lines> getAllLinesTheme(@Param("themeId")Integer themeId);

    List<Lines> getLineNotLineId(@Param("lineId")Integer lineId,@Param("cityId")Integer cityId);

    List<Lines> getLineThemeHotLimitSix(@Param("lineId")Integer lineId,@Param("themeId")Integer themeId,@Param("num")Integer num);
}
