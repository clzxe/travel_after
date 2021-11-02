package com.travel.travel.service.Impl;

import com.travel.travel.entity.ScenicType;
import com.travel.travel.repository.ScenicTypeMapper;
import com.travel.travel.repository.ScenicsMapper;
import com.travel.travel.service.ScenicTYpeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScenicTYpeServiceImpl implements ScenicTYpeService {
    @Resource
    private ScenicTypeMapper scenicTypeMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer typeId) {
        return false;
    }

    @Override
    public boolean insert(ScenicType record) {
        return false;
    }

    @Override
    public boolean insertSelective(ScenicType record) {
        return false;
    }

    @Override
    public ScenicType selectByPrimaryKey(Integer typeId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(ScenicType record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(ScenicType record) {
        return false;
    }

    @Override
    public List<ScenicType> getTypeAllNav() {
        return scenicTypeMapper.getTypeAllNav();
    }
}
