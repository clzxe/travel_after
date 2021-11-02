package com.travel.travel.repository;

import com.travel.travel.entity.H_file;
import com.travel.travel.entity.S_file;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface H_fileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(H_file record);

    int insertSelective(H_file record);

    H_file selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(H_file record);

    int updateByPrimaryKey(H_file record);

    List<H_file> getFilesBylineId(Integer lineId);
}