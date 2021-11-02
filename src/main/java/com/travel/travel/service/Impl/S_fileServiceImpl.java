package com.travel.travel.service.Impl;

import com.travel.travel.entity.S_file;
import com.travel.travel.repository.S_fileMapper;
import com.travel.travel.service.S_fileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class S_fileServiceImpl implements S_fileService {
    @Resource
    private S_fileMapper s_fileMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer fileScenicId) {
        boolean flag=false;
        if(s_fileMapper.deleteByPrimaryKey(fileScenicId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(S_file record) {
        boolean flag=false;
        if(s_fileMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(S_file record) {
        return false;
    }

    @Override
    public S_file selectByPrimaryKey(Integer fileId) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(S_file record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(S_file record) {
        return false;
    }

    @Override
    public List<S_file> getFilesByScenicId(Integer scenicId) {
        return s_fileMapper.getFilesByScenicId(scenicId);
    }
}
