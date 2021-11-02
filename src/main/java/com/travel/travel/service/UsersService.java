package com.travel.travel.service;

import com.travel.travel.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

public interface UsersService {
    boolean deleteByPrimaryKey(Integer userId);

    String insert(Users record,String verCode);

    boolean insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    boolean updateByPrimaryKeySelective(Users record);

    String updateByPrimaryKey(Users record,String verCode);

    Users loginByMailAndPassword(String userAccount);

    Users findUserByMail(String userMail);
    Users findUserByAccount(String userAccount);
    Users findUserByTel(String userTel);

    List<Users> getAllUsers(@Param("userAccount")String userAccount, @Param("current")Integer current, @Param("size")Integer size);

    boolean updateUserLock(Integer userId,Integer isLock);

    /**
     * 发送验证码到指定邮箱
     * @param sender 发送地址
     * @param mailSender spring自带
     * @param receiver 接受地址
     */
    Object getCode(String sender, JavaMailSenderImpl mailSender, String receiver);

    Integer getAllUsersCount(@Param("userAccount")String userAccount);


    boolean updateUserAmount(@Param("userId")Integer userId,@Param("userAmount")Double userAmount);

    boolean updateUserPassword(@Param("userId")Integer userId,@Param("userPassword")String userPassword);

    List<Long> getAllUsersO();

    List<Users> getAllUsersLineId(@Param("lineId")Integer lineId);

    List<Users> getAllUsersLeader(@Param("lineId")Integer lineId, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllUsersLeaderCount(@Param("lineId")Integer lineId);
}
