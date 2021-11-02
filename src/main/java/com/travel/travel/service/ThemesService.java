package com.travel.travel.service;

import com.travel.travel.entity.Themes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemesService {
    boolean deleteByPrimaryKey(Integer themeId);

    boolean insert(Themes record);

    boolean insertSelective(Themes record);

    Themes selectByPrimaryKey(Integer themeId);

    boolean updateByPrimaryKeySelective(Themes record);

    boolean updateByPrimaryKey(Themes record);

    List<Themes> getThemes();

    List<Themes> getAllThemes(@Param("themeName")String themeName, @Param("current")Integer current, @Param("size")Integer size);

    Integer  getAllCount(@Param("themeName")String themeName);

    List<Themes> getThemesByCassId (Integer classId);

    boolean deleteByCassId(Integer classId);

    List<Themes> getThemesAllNav ();
}
