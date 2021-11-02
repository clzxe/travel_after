package com.travel.travel.repository;

import com.travel.travel.entity.Groups;
import com.travel.travel.entity.Leaders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LeadersMapper {
    int deleteByPrimaryKey(Integer leaderId);

    int insert(Leaders record);

    int insertSelective(Leaders record);

    Leaders selectByPrimaryKey(Integer leaderId);

    int updateByPrimaryKeySelective(Leaders record);

    int updateByPrimaryKey(Leaders record);

    List<Leaders> getLeaderByGroupId(Integer groupId);

    Integer  getAllCount(@Param("leaderName")String leaderName);

    List<Leaders> getAllLeaders(@Param("leaderName")String leaderName, @Param("current")Integer current, @Param("size")Integer size);

    Leaders selectByAccount(@Param("leaderAccount")String leaderAccount);

    int updatePassword(@Param("leaderId")Integer leaderId,@Param("leaderPassword")String leaderPassword);

}