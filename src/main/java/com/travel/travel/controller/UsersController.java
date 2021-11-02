package com.travel.travel.controller;


import com.travel.travel.entity.Input_money;
import com.travel.travel.entity.Users;
import com.travel.travel.service.Input_MoneyService;
import com.travel.travel.service.UsersService;
import com.travel.travel.util.MyUtil;
import com.travel.travel.util.QiniuCloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    @Resource
    private Input_MoneyService inputMoneyService;

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String sender;


    @CrossOrigin
    @GetMapping(value = "/userExits")
    public Object judgeUserExits(HttpSession session){
        Map<String,Object> map =new HashMap<>(3);
        Users users= (Users) session.getAttribute("userSession");
        map.put("user",users);
        if(users==null){
            map.put("message","nologin");
        }
        else{
            map.put("message","login");
        }
        return map;
    }
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/add")
    public Object insertUsers(Users users, @RequestParam("verCode") String verCode ,MultipartFile file){

        Map<String,String> map = new HashMap<>(5);
        Users users1=new Users();
        Users users2=new Users();
        Users users3=new Users();
        if(!users.getUserMail().equals("")){
            users1=usersService.findUserByMail(users.getUserMail());
            if(users1!=null){
                map.put("message","用户已存在！") ;
            }
        }
        if(!users.getUserAccount().equals("")){
            users2=usersService.findUserByAccount(users.getUserAccount());
            if(users2!=null){
                map.put("message","用户已存在！") ;
            }
        }
        if(!users.getUserTel().equals("")){
            users3=usersService.findUserByTel(users.getUserTel());
            if(users3!=null){
                map.put("message","用户已存在！") ;
            }
        }

        String realPath="E:\\作业\\毕设\\travel_up\\static\\uploadFiles";
        if(file!=null){
            String originalName = file.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            String filePath=sonImgPath+ImageName;
            users.setUserHead(filePath);

            try {
                file.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
            map.put("message",usersService.insert(users,verCode)) ;
        }
        else{
            users.setUserHead(sonImgPath+"girl.png");
            map.put("message",usersService.insert(users,verCode)) ;
        }

        return map;

    }
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateUsers(Users users, @RequestParam("verCode") String verCode ,MultipartFile file,HttpSession session) throws IOException {
        Map<String,String> map = new HashMap<>(5);
        Users users1=new Users();
        Users users2=new Users();
        Users users3=new Users();
        if(!users.getUserMail().equals("")){
            users1=usersService.findUserByMail(users.getUserMail());
            if(users1!=null){
                map.put("message","用户已存在！") ;
            }
        }
        if(!users.getUserAccount().equals("")){
            users2=usersService.findUserByAccount(users.getUserAccount());
            if(users2!=null){
                map.put("message","用户已存在！") ;
            }
        }
        if(!users.getUserTel().equals("")){
            users3=usersService.findUserByTel(users.getUserTel());
            if(users3!=null){
                map.put("message","用户已存在！") ;
            }
        }

        String realPath="E:\\作业\\毕设\\projectImage\\user";
        QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
        if(file!=null){
            String originalName = file.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            byte[] bytes = file.getBytes();


            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, ImageName);
                map.put("message","OK");
                users.setUserHead(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }

            try {
                file.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
            map.put("message",usersService.updateByPrimaryKey(users,verCode)) ;
            Users users4=usersService.selectByPrimaryKey(users.getUserId());
            session.setAttribute("userSession",users4);

        }
        else{
            users.setUserHead(qiniuUtil.returnUrl()+"girl.png");
            map.put("message",usersService.updateByPrimaryKey(users,verCode)) ;
        }

        return map;

    }
    /**
     * 发送验证码到指定邮箱
     */
    @CrossOrigin
    @GetMapping("/verCode")
    public Object verCode(String receiver) {
        return usersService.getCode(sender, mailSender, receiver);
    }
    /**
     * 根据用户ID获取User对象
     */
    @CrossOrigin
    @GetMapping("/getuser")
    public Object getUserByUserId(String userId) {
        Map<String,Object> map= new HashMap<>();
        if(userId==null||userId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Users user=usersService.selectByPrimaryKey(Integer.parseInt(userId));
            map.put("message","OK");
            map.put("user",user);
        }
        return map;
    }
    /**
     * 根据用户ID获取User对象
     */
    @CrossOrigin
    @GetMapping("/WXgetuser")
    public Object WXgetUserByUserId(String userId) {
        Map<String,Object> map= new HashMap<>();
        if(userId==null||userId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Users user=usersService.selectByPrimaryKey(Integer.parseInt(userId));
            map.put("message","OK");
            map.put("user",user);
        }
        return map;
    }

    /**
     * 根据用户ID获取User对象
     */
    @CrossOrigin
    @GetMapping("/getEmail")
    public Object getUserByEmail(String userMail) {
        Map<String,Object> map= new HashMap<>();
        if(userMail==null||userMail.equals("")){
            map.put("message","ERROR");
        }
        else{
            Users user=usersService.findUserByMail(userMail);
            if(user==null){
                map.put("message","ERROR");
            }
            else {
                map.put("message","OK");
            }

            map.put("user",user);
        }
        return map;
    }
    /**
     * 根据用户ID获取User对象
     */
    @CrossOrigin
    @GetMapping("/get_user")
    public Object getUserInSession(HttpSession session) {
        Map<String,Object> map= new HashMap<>();

            Users user= (Users) session.getAttribute("userSession");
          //  map.put("message","OK");
        if(user!=null){
            Users user2=usersService.selectByPrimaryKey(user.getUserId());
            map.put("message","OK");
            map.put("user",user2);
        }
        else {
            //Users user2=usersService.selectByPrimaryKey(user.getUserId());
            map.put("message","OK");
            map.put("user",user);
        }


        return map;
    }
    /**
     * 获取用户列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getuserList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Users> userList;
        Integer total=0;
        Integer pageCount=0;
        Map<String,Object> map =new HashMap<>();
        Integer pageSize=size;
        userList=usersService.getAllUsers(searchTitle,size*(pageIndex-1),size);
        total=usersService.getAllUsersCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("userList",userList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("pageIndex",pageIndex);
        map.put("searchTitle",searchTitle);
        return map;
    }

    /**
     * 获取用户列表（lineId）
     */
    @CrossOrigin
    @PostMapping(value = "/line_list")
    public Object getuserListLineId(@RequestParam("lineId") Integer lineId ,
                                    @RequestParam("pageIndex")Integer pageIndex,
                                    @RequestParam("size")Integer size){

        Map<String,Object> map =new HashMap<>();


        List<Users> userList;
        Integer total=0;
        Integer pageCount=0;
        Integer pageSize=size;
        userList=usersService.getAllUsersLeader(lineId, pageIndex, size);
        total=usersService.getAllUsersLeaderCount(lineId);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("userList",userList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("pageIndex",pageIndex);

        return map;
    }

    /**
     * 获取用户充值记录（userId）
     */
    @CrossOrigin
    @GetMapping(value = "/getRecord")
    public Object getuserRecord(@RequestParam("userId") Integer userId ){
        List<Input_money> moneyList;
        Map<String,Object> map =new HashMap<>();
        moneyList=inputMoneyService.selectByUserId(userId);

        map.put("moneyList",moneyList);

        return map;
    }

    /**
     * 删除用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String deluser(@RequestParam("userId")String userId){
        if(userId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(usersService.deleteByPrimaryKey(Integer.parseInt(userId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 删除用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/updateAmount")
    public String deluser(@RequestParam("amount")String amount,HttpSession session){
        if(amount==null){
            return "系统异常，请重新选择";
        }
        else {
            Users users= (Users) session.getAttribute("userSession");
            if(usersService.updateUserAmount(users.getUserId(),Double.parseDouble(amount))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    @CrossOrigin
    @PostMapping(value = "/updateAmountOrder")
    public String deluser(@RequestParam("amount")String amount,@RequestParam("orderId")String orderId,HttpSession session){

        if(amount==null){
            return "系统异常，请重新选择";
        }
        else {
            Users users= (Users) session.getAttribute("userSession");
            if(usersService.updateUserAmount(users.getUserId(),Double.parseDouble(amount))){

                System.out.println(amount);
                Input_money inputMoney=new Input_money();
                inputMoney.setAmount(Double.parseDouble(amount));
                inputMoney.setCreateTime(new Date());
                inputMoney.setOrderId(orderId);
                inputMoney.setUserId(users.getUserId());
               if(inputMoneyService.insert(inputMoney));{
                    return "OK";
                }

            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 删除用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/wx_updateAmount")
    public String deluser(@RequestParam("amount")String amount,
                          @RequestParam("userId") Integer userId,
                          HttpSession session){
        if(amount==null){
            return "系统异常，请重新选择";
        }
        else {
            Users users= (Users) session.getAttribute("userSession");
            if(usersService.updateUserAmount(userId,Double.parseDouble(amount))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 删除用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/update_password")
    public String updatePassword(@RequestParam("userId")String userId,@RequestParam String password){
        if(userId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(usersService.updateUserPassword(Integer.parseInt(userId),password)){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 封锁用户（根据userId）
     */
    @CrossOrigin
    @PostMapping(value = "/lock")
    public String lockUser(@RequestParam("userId")String userId,@RequestParam("isLock")String isLock){
        if(userId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(usersService.updateUserLock(Integer.parseInt(userId),Integer.parseInt(isLock))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

}
