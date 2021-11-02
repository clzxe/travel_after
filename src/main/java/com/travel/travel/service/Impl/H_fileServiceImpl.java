package com.travel.travel.service.Impl;

import com.travel.travel.entity.H_file;
import com.travel.travel.repository.H_fileMapper;
import com.travel.travel.repository.S_fileMapper;
import com.travel.travel.service.H_fileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class H_fileServiceImpl implements H_fileService {
    @Resource
    private H_fileMapper h_fileMapper;
    public boolean deleteByPrimaryKey(Integer fileId) {
        boolean flag=false;
        if(h_fileMapper.deleteByPrimaryKey(fileId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(H_file record) {
        boolean flag=false;
        if(h_fileMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(H_file record) {
        return false;
    }

    @Override
    public H_file selectByPrimaryKey(Integer fileId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(H_file record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(H_file record) {
        boolean flag=false;
        if(h_fileMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<H_file> getFilesBylineId(Integer lineId) {
        return h_fileMapper.getFilesBylineId(lineId);
    }
}
