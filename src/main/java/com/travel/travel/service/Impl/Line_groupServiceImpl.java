package com.travel.travel.service.Impl;

import com.travel.travel.entity.Line_group;
import com.travel.travel.repository.Line_groupMapper;
import com.travel.travel.service.Line_groupService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Line_groupServiceImpl implements Line_groupService {
    @Resource
    private Line_groupMapper line_groupMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        boolean flag=false;
        if(line_groupMapper.deleteByPrimaryKey(id)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateGroupPeople(Integer type, Integer id,Integer linePeople) {
        boolean flag=false;
        if(line_groupMapper.updateGroupPeople(type, id,linePeople)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean deleteByAuto() {
        boolean flag=false;
        if(line_groupMapper.deleteByAuto()>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Line_group record) {
        boolean flag=false;
        if(line_groupMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Line_group record) {
        return false;
    }

    @Override
    public Line_group selectByPrimaryKey(Integer id) {
        return line_groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Line_group record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Line_group record) {
        boolean flag=false;
        if(line_groupMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Integer getAllCount(String lineName) {
        return line_groupMapper.getAllCount(lineName);
    }

    @Override
    public List<Line_group> getAllLines(String lineName, Integer current, Integer size) {
        return line_groupMapper.getAllLines(lineName,current,size);
    }

    @Override
    public List<Line_group> getLineInit(String scenicName, Integer cityId, Integer themeId,Integer sortType, Integer current, Integer size) {
        return line_groupMapper.getLineInit(scenicName,cityId,themeId,sortType,current,size);
    }

    @Override
    public Integer getLineInitCount(String scenicName, Integer cityId,Integer themeId, Integer sortType) {
        return line_groupMapper.getLineInitCount(scenicName,cityId,themeId,sortType);
    }

    @Override
    public List<Line_group> selectByCityIdAndLineId(Integer cityId, Integer lineId) {
        return line_groupMapper.selectByCityIdAndLineId(cityId,lineId);
    }

    @Override
    public Line_group getLineOne(Integer id) {
        return line_groupMapper.getLineOne(id);
    }

    @Override
    public List<Line_group> getLineOneByLineId(Integer id) {
        return line_groupMapper.getLineOneByLineId(id);
    }

    @Override
    public List<Line_group> getAllLinesTheme(Integer themeId) {
        return line_groupMapper.getAllLinesTheme(themeId);
    }

    @Override
    public List<Line_group> getLineHotLimitSix() {
        return line_groupMapper.getLineHotLimitSix();
    }

    @Override
    public List<Line_group> getLineHotLimitFour() {
        return line_groupMapper.getLineHotLimitFour();
    }

    @Override
    public List<Line_group> getLineNewLimitFour() {
        return line_groupMapper.getLineNewLimitFour();
    }

    @Override
    public List<Line_group> selectByStartTime(Integer leaderId, Integer type, Integer current, Integer size) {
        return line_groupMapper.selectByStartTime(leaderId, type, current, size);
    }

    @Override
    public Integer selectByStartTimeCount(Integer leaderId, Integer type) {
        return line_groupMapper.selectByStartTimeCount(leaderId, type);
    }


}
