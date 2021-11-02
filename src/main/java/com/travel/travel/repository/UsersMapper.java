package com.travel.travel.repository;

import com.travel.travel.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    public Users loginByMailAndPassword(@Param("userAccount") String userAccount);

    Users findUserByMail(@Param("userMail")String userMail);

    Users findUserByAccount(@Param("userAccount")String userAccount);

    Users findUserByTel(@Param("userTel")String userTel);

    List<Users> getAllUsers(@Param("userAccount")String userAccount, @Param("current")Integer current, @Param("size")Integer size);

   Integer getAllUsersCount(@Param("userAccount")String userAccount);

    int updateUserLock(@Param("userId")Integer userId,@Param("isLock")Integer isLock);

    int updateUserAmount(@Param("userId")Integer userId,@Param("userAmount")Double userAmount);

    int updateUserPassword(@Param("userId")Integer userId,@Param("userPassword")String userPassword);

    List<Users> getAllUsersLeader(@Param("lineId")Integer lineId, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllUsersLeaderCount(@Param("lineId")Integer lineId);

    List<Long> getAllUsersO();
    List<Users> getAllUsersLineId(@Param("lineId")Integer lineId);
}