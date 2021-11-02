package com.travel.travel.service.Impl;

import com.travel.travel.entity.Classes;
import com.travel.travel.repository.ClassesMapper;
import com.travel.travel.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer classId) {
        boolean flag=false;
        if(classesMapper.deleteByPrimaryKey(classId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Classes record) {
        boolean flag=false;
        if(classesMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Classes record) {
        return false;
    }

    @Override
    public Classes selectByPrimaryKey(Integer classId) {
        return classesMapper.selectByPrimaryKey(classId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Classes record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Classes record) {
        boolean flag=false;
        if(classesMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Classes> getAllClasses(String className, Integer current, Integer size) {
        return classesMapper.getAllClasses(className,current,size);
    }

    @Override
    public Integer getAllCount(String className) {
        return classesMapper.getAllCount(className);
    }

    @Override
    public List<Classes> getAll() {
        return classesMapper.getAll();
    }
}
