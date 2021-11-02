package com.travel.travel.service.Impl;

import com.travel.travel.entity.Lines;
import com.travel.travel.repository.LinesMapper;
import com.travel.travel.service.LinesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class LinesServiceImpl implements LinesService {
    @Resource
    private LinesMapper linesMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer lineId) {
        boolean flag=false;
        if(linesMapper.deleteByPrimaryKey(lineId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Lines record) {
        boolean flag=false;
        if(linesMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Lines record) {
        return false;
    }

    @Override
    public Lines selectByPrimaryKey(Integer lineId) {
        return linesMapper.selectByPrimaryKey(lineId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Lines record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Lines record) {
        boolean flag=false;
        if(linesMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Lines> getHot_line() {
        return linesMapper.getHot_line();
    }

    @Override
    public List<Lines> getNew_line() {
        return linesMapper.getNew_line();
    }

    @Override
    public List<Lines> getAll() {
        return linesMapper.getAll();
    }

    @Override
    public List<Long> getLineHotLimitSix( ) {

        return linesMapper.getLineHotLimitSix();
    }

    @Override
    public List<Lines> findByTopicidIn(List<Long> list) {
        List<Lines> linesList=new ArrayList<>();
        for(Long lineId :list){
            Lines lines=linesMapper.selectByPrimaryKey(lineId.intValue());
            linesList.add(lines);
        }
        return linesList;
    }

    @Override
    public Integer getAllCount(String lineName) {
        return linesMapper.getAllCount(lineName);
    }

    @Override
    public List<Lines> getAllLines(@Param("lineName")String lineName, @Param("current") Integer current, @Param("size")Integer size) {
        return linesMapper.getAllLines(lineName,current,size);
    }

    @Override
    public List<Lines> getAllLinesTheme(Integer themeId) {
        return linesMapper.getAllLinesTheme(themeId);
    }

    @Override
    public List<Lines> getLineNotLineId(Integer lineId, Integer cityId) {
        return linesMapper.getLineNotLineId(lineId, cityId);
    }

    @Override
    public List<Lines> getLineThemeHotLimitSix(Integer lineId, Integer themeId,Integer num) {
        return linesMapper.getLineThemeHotLimitSix(lineId, themeId,num);
    }


}
