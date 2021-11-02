package com.travel.travel.repository;

import com.travel.travel.entity.Themes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ThemesMapper {
    int deleteByPrimaryKey(Integer themeId);

    int insert(Themes record);

    int insertSelective(Themes record);

    Themes selectByPrimaryKey(Integer themeId);

    int updateByPrimaryKeySelective(Themes record);

    int updateByPrimaryKey(Themes record);

    List<Themes> getThemes();

    List<Themes> getAllThemes(@Param("themeName")String themeName, @Param("current")Integer current, @Param("size")Integer size);

    Integer  getAllCount(@Param("themeName")String themeName);

    List<Themes> getThemesByCassId (Integer classId);

    int deleteByCassId(Integer classId);

    List<Themes> getThemesAllNav ();
}