package com.travel.travel.service;

import java.util.List;

public interface S_fileService {
    boolean deleteByPrimaryKey(Integer fileScenicId);

    boolean insert(com.travel.travel.entity.S_file record);

    boolean insertSelective(com.travel.travel.entity.S_file record);

    com.travel.travel.entity.S_file selectByPrimaryKey(Integer fileId);

    boolean updateByPrimaryKeySelective(com.travel.travel.entity.S_file record);

    boolean updateByPrimaryKey(com.travel.travel.entity.S_file record);

    List<com.travel.travel.entity.S_file> getFilesByScenicId(Integer scenicId);
}
