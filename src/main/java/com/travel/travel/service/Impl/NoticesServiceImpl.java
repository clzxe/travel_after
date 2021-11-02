package com.travel.travel.service.Impl;

import com.travel.travel.entity.Notices;
import com.travel.travel.repository.NoticesMapper;
import com.travel.travel.service.NoticesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticesServiceImpl implements NoticesService {
    @Resource
    private NoticesMapper noticesMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer noticeId) {
        boolean flag=false;
        if(noticesMapper.deleteByPrimaryKey(noticeId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Notices record) {
        boolean flag=false;
        if(noticesMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Notices record) {
        return false;
    }

    @Override
    public Notices selectByPrimaryKey(Integer noticeId) {
        return noticesMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Notices record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Notices record) {
        boolean flag=false;
        if(noticesMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Notices> getNotices() {
        return noticesMapper.getNotices();
    }

    @Override
    public Integer getAllCount(String noticeTitle) {
        return noticesMapper.getAllCount(noticeTitle);
    }

    @Override
    public List<Notices> getAllNotices(String noticeTitle, Integer current, Integer size) {
        return noticesMapper.getAllNotices(noticeTitle,current,size);
    }
}
