package com.travel.travel.service.Impl;

import com.travel.travel.entity.Groups;
import com.travel.travel.repository.GroupsMapper;
import com.travel.travel.service.GroupsService;
import org.apache.catalina.mbeans.GroupMBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {
    @Resource
    private GroupsMapper groupsMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer groupId) {
        boolean flag=false;
        if(groupsMapper.deleteByPrimaryKey(groupId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Groups record) {
        boolean flag=false;
        if(groupsMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Groups record) {
        return false;
    }

    @Override
    public Groups selectByPrimaryKey(Integer groupId) {
        return groupsMapper.selectByPrimaryKey(groupId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Groups record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Groups record) {
        boolean flag=false;
        if(groupsMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Groups> getAll() {
        return groupsMapper.getAll();
    }

    @Override
    public Integer getAllCount(String groupName) {
        return groupsMapper.getAllCount(groupName);
    }

    @Override
    public List<Groups> getAllGroups(String groupName, Integer current, Integer size) {
        return groupsMapper.getAllGroups(groupName,current,size);
    }
}
