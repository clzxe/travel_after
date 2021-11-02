package com.travel.travel.service.Impl;

import com.travel.travel.entity.Leaders;
import com.travel.travel.repository.LeadersMapper;
import com.travel.travel.service.LeadersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeadersServiceImpl implements LeadersService {
    @Resource
    private LeadersMapper leadersMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer leaderId) {
        boolean flag=false;
        if(leadersMapper.deleteByPrimaryKey(leaderId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Leaders record) {
        boolean flag=false;
        if(leadersMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Leaders record) {
        return false;
    }

    @Override
    public Leaders selectByPrimaryKey(Integer leaderId) {
        return leadersMapper.selectByPrimaryKey(leaderId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Leaders record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Leaders record) {
        boolean flag=false;
        if(leadersMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Leaders> getLeaderByGroupId(Integer groupId) {
        return leadersMapper.getLeaderByGroupId(groupId);
    }

    @Override
    public Integer getAllCount(String leaderName) {
        return leadersMapper.getAllCount(leaderName);
    }

    @Override
    public Leaders selectByAccount(String leaderAccount) {
        return leadersMapper.selectByAccount(leaderAccount);
    }

    @Override
    public List<Leaders> getAllLeaders(String leaderName, Integer current, Integer size) {
        return leadersMapper.getAllLeaders(leaderName,current,size);
    }

    @Override
    public boolean updatePassword(Integer leaderId, String leaderPassword) {
        boolean flag=false;
        if(leadersMapper.updatePassword(leaderId, leaderPassword)>0){
            flag=true;
        }
        return flag;
    }
}
