package com.travel.travel.repository;

import com.travel.travel.entity.E_file;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface E_fileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(E_file record);

    int insertSelective(E_file record);

    E_file selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(E_file record);

    int updateByPrimaryKey(E_file record);
}