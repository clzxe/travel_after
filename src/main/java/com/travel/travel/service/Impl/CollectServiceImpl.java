package com.travel.travel.service.Impl;

import com.travel.travel.entity.Collect;
import com.travel.travel.repository.CollectMapper;
import com.travel.travel.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectMapper collectMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer collectId) {
        boolean flag=false;
        if(collectMapper.deleteByPrimaryKey(collectId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean saveByPrimaryKey(Integer collectId) {
        boolean flag=false;
        if(collectMapper.saveByPrimaryKey(collectId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Collect record) {
        boolean flag=false;
        if(collectMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Collect record) {
        return false;
    }

    @Override
    public Collect selectByPrimaryKey(Integer collectId) {
        return collectMapper.selectByPrimaryKey(collectId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Collect record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Collect record) {
        boolean flag=false;
        if(collectMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateByCollect(Integer collectId) {
        boolean flag=false;
        if(collectMapper.updateByCollect(collectId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Collect selectByUserIdAndScenicId(Integer userId, Integer scenicId, Integer collectType) {
        return collectMapper.selectByUserIdAndScenicId(userId,scenicId,collectType);
    }

    @Override
    public List<Collect> selectByUserIdList(Integer userId) {
        return collectMapper.selectByUserIdList(userId);
    }

    @Override
    public Integer selectByUserIdListCount(Integer userId) {
        return collectMapper.selectByUserIdListCount(userId);
    }

    @Override
    public Integer getCollectCount(Integer scenicId, Integer collectType) {
        return collectMapper.getCollectCount(scenicId, collectType);
    }

    @Override
    public List<Collect> getAllCollectUp(String searchTitle, Integer userId, String start, String end, Integer orderType, Integer current, Integer size) {
        return collectMapper.getAllCollectUp(searchTitle, userId, start, end, orderType, current, size);
    }

    @Override
    public Integer getCollectCountUp(String searchTitle, Integer userId, String start, String end, Integer orderType) {
        return collectMapper.getCollectCountUp(searchTitle, userId, start, end, orderType);
    }
}
