package com.travel.travel.service;

import com.travel.travel.entity.Leaders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeadersService {
    boolean deleteByPrimaryKey(Integer leaderId);

    boolean insert(Leaders record);

    boolean insertSelective(Leaders record);

    Leaders selectByPrimaryKey(Integer leaderId);

    boolean updateByPrimaryKeySelective(Leaders record);

    boolean updateByPrimaryKey(Leaders record);

    List<Leaders> getLeaderByGroupId(Integer groupId);

    Integer  getAllCount(@Param("leaderName")String leaderName);

    Leaders selectByAccount(@Param("leaderAccount")String leaderAccount);

    List<Leaders> getAllLeaders(@Param("leaderName")String leaderName, @Param("current")Integer current, @Param("size")Integer size);

    boolean updatePassword(@Param("leaderId")Integer leaderId,@Param("leaderPassword")String leaderPassword);
}
