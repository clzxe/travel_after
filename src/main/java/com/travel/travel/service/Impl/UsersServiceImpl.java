package com.travel.travel.service.Impl;

import com.travel.travel.entity.Users;
import com.travel.travel.repository.UsersMapper;
import com.travel.travel.service.UsersService;
import com.travel.travel.util.SendEmailUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    /**
     * 验证码
     */
    private String code;
    /**
     * 发送时间
     */
    private Date sendTime;
    @Override
    public boolean deleteByPrimaryKey(Integer userId) {
        boolean flag=false;
        if(usersMapper.deleteByPrimaryKey(userId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public String insert(Users record,String verCode) {

        Date date = new Date();
        //判断验证码
        if (SendEmailUtil.getMinute(sendTime, date) > 5) {
            return "验证码已经失效！！！";
        }
        if (!verCode.equals(code)) {
            return "验证码不正确！！！";
        }
        if(usersMapper.insert(record)>0){
            code = null;
            return "OK";
        }
        else{
            return "响应错误";
        }

    }

    @Override
    public boolean insertSelective(Users record) {
        return false;
    }

    @Override
    public Users selectByPrimaryKey(Integer userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Users record) {
        return false;
    }

    @Override
    public String updateByPrimaryKey(Users record,String verCode) {
        boolean flag=false;
        Date date = new Date();
        //判断验证码
        if (SendEmailUtil.getMinute(sendTime, date) > 5) {
            return "验证码已经失效！！！";
        }
        if (!verCode.equals(code)) {
            return "验证码不正确！！！";
        }
        if(usersMapper.updateByPrimaryKey(record)>0){
            code = null;
            return "OK";
        }
        else{
            return "响应错误";
        }
    }

    @Override
    public Users loginByMailAndPassword(String userAccount) {
        return usersMapper.loginByMailAndPassword(userAccount);
    }

    @Override
    public Users findUserByMail(String userMail) {
        return usersMapper.findUserByMail(userMail);
    }

    @Override
    public Users findUserByAccount(String userAccount) {
        return usersMapper.findUserByAccount(userAccount);
    }

    @Override
    public Users findUserByTel(String userTel) {
        return usersMapper.findUserByTel(userTel);
    }

    @Override
    public List<Users> getAllUsers(String userAccount, Integer current, Integer size) {
        return usersMapper.getAllUsers(userAccount,current,size);
    }

    @Override
    public boolean updateUserLock(Integer userId, Integer isLock) {
        boolean flag=false;
        if(usersMapper.updateUserLock(userId,isLock)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public Object getCode(String sender, JavaMailSenderImpl mailSender, String receiver) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");//设置邮件标题
        code = SendEmailUtil.generateVerCode();
        sendTime = new Date();
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）"); //设置邮件正文
        message.setFrom(sender);//发件人
        message.setTo(receiver);//收件人
        mailSender.send(message);//发送邮件
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("response","OK");
        return map;

    }

    @Override
    public Integer getAllUsersCount(String userAccount) {
        return usersMapper.getAllUsersCount(userAccount);
    }

    @Override
    public boolean updateUserAmount(Integer userId, Double userAmount) {
        boolean flag=false;
        if(usersMapper.updateUserAmount(userId,userAmount)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateUserPassword(Integer userId, String userPassword) {
        boolean flag=false;
        if(usersMapper.updateUserPassword(userId,userPassword)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Long> getAllUsersO() {
        return usersMapper.getAllUsersO();
    }

    @Override
    public List<Users> getAllUsersLineId(Integer lineId) {
        return usersMapper.getAllUsersLineId(lineId);
    }

    @Override
    public List<Users> getAllUsersLeader(Integer lineId, Integer current, Integer size) {
        return usersMapper.getAllUsersLeader(lineId, current, size);
    }

    @Override
    public Integer getAllUsersLeaderCount(Integer lineId) {
        return usersMapper.getAllUsersLeaderCount(lineId);
    }

}
