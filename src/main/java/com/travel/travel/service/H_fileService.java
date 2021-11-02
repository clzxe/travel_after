package com.travel.travel.service;

import com.travel.travel.entity.H_file;

import java.util.List;

public interface H_fileService {
    boolean deleteByPrimaryKey(Integer fileId);

    boolean insert(H_file record);

    boolean insertSelective(H_file record);

    H_file selectByPrimaryKey(Integer fileId);

    boolean updateByPrimaryKeySelective(H_file record);

    boolean updateByPrimaryKey(H_file record);

    List<H_file> getFilesBylineId(Integer lineId);
}
