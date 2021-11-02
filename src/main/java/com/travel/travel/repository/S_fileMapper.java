package com.travel.travel.repository;

import com.travel.travel.entity.S_file;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface S_fileMapper {
    int deleteByPrimaryKey(Integer fileScenicId);

    int insert(S_file record);

    int insertSelective(S_file record);

    S_file selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(S_file record);

    int updateByPrimaryKey(S_file record);

    List<S_file> getFilesByScenicId(Integer fileScenicId);
}