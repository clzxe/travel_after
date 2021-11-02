package com.travel.travel.service.Impl;

import com.travel.travel.entity.Themes;
import com.travel.travel.repository.ThemesMapper;
import com.travel.travel.service.ThemesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThemesServiceImpl implements ThemesService {
    @Resource
    private ThemesMapper themesMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer themeId) {
        boolean flag=false;
        if(themesMapper.deleteByPrimaryKey(themeId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Themes record) {
        boolean flag=false;
        if(themesMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Themes record) {
        return false;
    }

    @Override
    public Themes selectByPrimaryKey(Integer themeId) {
        return themesMapper.selectByPrimaryKey(themeId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Themes record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Themes record) {
        boolean flag=false;
        if(themesMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Themes> getThemes() {
        return themesMapper.getThemes();
    }

    @Override
    public List<Themes> getAllThemes(String themeName, Integer current, Integer size) {
        return themesMapper.getAllThemes(themeName,current,size);
    }

    @Override
    public Integer getAllCount(String themeName) {
        return themesMapper.getAllCount(themeName);
    }

    @Override
    public List<Themes> getThemesByCassId(Integer classId) {
        return themesMapper.getThemesByCassId(classId);
    }

    @Override
    public boolean deleteByCassId(Integer classId) {
        boolean flag=false;
        if(themesMapper.deleteByCassId(classId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Themes> getThemesAllNav() {
        return themesMapper.getThemesAllNav();
    }
}
