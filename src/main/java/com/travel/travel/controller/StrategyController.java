package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.*;
import com.travel.travel.util.MyUtil;
import com.travel.travel.util.QiniuCloudUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("travel/strategy")
public class StrategyController {
    @Resource
    private StrategyService strategyService;
    @Resource
    private ScenicsService scenicsService;
    @Resource
    private S_commentsService sCommentsService;
    @Resource
    private S_replyService sReplyService;
    @Resource
    private CollectService collectService;
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getStrategyList(@RequestParam("searchTitle") String searchTitle,
                              @RequestParam("searchScenic") Integer searchScenic,
                              @RequestParam("pageIndex")Integer pageIndex,
                              @RequestParam("size")Integer size ){
        List<Strategy> strategyList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        strategyList=strategyService.getAllStrategy(searchTitle,searchScenic,size*(pageIndex-1),size);
        total=strategyService.getAllStrategyCount(searchTitle,searchScenic);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("strategyList",strategyList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("pageIndex",pageIndex);
        map.put("searchScenic",searchScenic);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delStrategy(@RequestParam("strategyId")String strategyId){
        if(strategyId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(strategyService.deleteByPrimaryKey(Integer.parseInt(strategyId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getstrategy")
    public Object getStrategyByStrategyId(@RequestParam("strategyId")String strategyId) {
        Map<String,Object> map= new HashMap<>();
        if(strategyId==null||strategyId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Strategy strategy=strategyService.selectByPrimaryKey(Integer.parseInt(strategyId));
            map.put("message","OK");
            map.put("strategy",strategy);
        }
        return map;
    }

    /**
     * 查询所有的景点
     */
    @CrossOrigin
    @GetMapping("/getScenic")
    public Object getAllScenic() {
        Map<String,Object> map= new HashMap<>();

            List<Scenics> scenic =scenicsService.getAll();
            map.put("scenicList",scenic);
        return map;
    }

    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/upload_img")
    public Object saveHotel(MultipartFile Img) throws IOException {
        String realPath="E:\\作业\\毕设\\projectImage\\strategy";
        Map<String,String> map = new HashMap<>(5);
        if(Img!=null){
            String originalName = Img.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);
            try {
                byte[] bytes = Img.getBytes();

                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, ImageName);
                    map.put("message","OK");
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
                    Img.transferTo(targetFile);
                    //  path+=feedPicture+",";
                } catch (Exception e) {
                    map.put("message", e.getMessage());
                }
            } catch (IOException e) {
                map.put("message","文件上传发生异常!");

            }
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/upload_img2")
    public Object uploadImg(@RequestParam MultipartFile image, HttpServletRequest request) {
        Map<String,String> map = new HashMap<>(5);
        if (image.isEmpty()) {
            map.put("message","文件为空，请重新上传");
            return map;
        }

        try {
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, imageName);
                map.put("message","OK");
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        } catch (IOException e) {
            map.put("message","文件上传发生异常!");
            return map;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveStrategy(Strategy strategy, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,Object> map = new HashMap<>(5);
       // String realPath="E:\\作业\\毕设\\travel_up\\static\\uploadFiles";
        if(headImg!=null){
            String originalName = headImg.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            byte[] bytes = headImg.getBytes();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, ImageName);
                map.put("message","OK");
                strategy.setStrategyImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String realPath="E:\\作业\\毕设\\projectImage\\strategy";
            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            try {
                headImg.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
            strategy.setCreateTime(new Date());
            strategy.setUpdateTime(strategy.getCreateTime());

            if(!strategyService.insert(strategy)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
                map.put("strategyId", strategy.getStrategyId());
            }

        }



        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateStrategy(Strategy strategy, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,String> map = new HashMap<>(5);
        //String realPath="E:\\作业\\毕设\\travel_up\\static\\uploadFiles";
        Users user= (Users) session.getAttribute("userSession");
        strategy.setUpdateTime(new Date());
        if(headImg!=null){
            String originalName = headImg.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            byte[] bytes = headImg.getBytes();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, ImageName);
                map.put("message","OK");
                strategy.setStrategyImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String realPath="E:\\作业\\毕设\\projectImage\\strategy";
            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            try {
                headImg.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
            //strategy.setCreateTime();
            strategy.setUpdateTime(new Date());
            if(!strategyService.updateByPrimaryKey(strategy)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }
        }
        else{
            if(!strategyService.updateByPrimaryKey(strategy)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }
        }



        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/strategy_list")
    public Object getHotelListUp(@RequestParam("searchTitle") String searchTitle,
                                 @RequestParam("sortType") Integer sortType,
                                 @RequestParam("pageIndex")Integer pageIndex,
                                 @RequestParam("size")Integer size ){
        List<Strategy> strategyList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        strategyList=strategyService.getStrategyInit(searchTitle,sortType,size*(pageIndex-1),size);
        if(sortType==1){
            total=16;
        }
        else {
            total=strategyService.getStrategyInitCount(searchTitle,sortType);
        }
        if(strategyList==null||strategyList.size()==0){
            strategyList=strategyService.getStrategyInit(searchTitle,0,size*(pageIndex-1),size);
            total=strategyService.getStrategyInitCount(searchTitle,0);
        }
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("strategyList",strategyList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("sortType",sortType);
        return map;
    }


    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/strategy_view")
    public Object getStrategyView(@RequestParam("strategyId")String strategyId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(strategyId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Strategy strategy = strategyService.selectByPrimaryKey(Integer.parseInt(strategyId));
            List<S_comments> sCommentsList=sCommentsService.getAllS_commentsById(strategy.getStrategyId(),size*(pageIndex-1),size);
            total=sCommentsService.getAllCountById(strategy.getStrategyId());
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            if(sCommentsList==null){
                sCommentsList=new ArrayList<>();
            }
            for(int i=0;i<sCommentsList.size();i++){
                S_comments evaluates=sCommentsList.get(i);
                List<S_reply> sReplyList=new ArrayList<S_reply>();
                sReplyList=sReplyService.getReplyByCommentId(evaluates.getCommentId());
                sCommentsList.get(i).setsReplyList(sReplyList);
            }
             Users users= (Users) session.getAttribute("userSession");
            Integer countStrategy=strategyService.getAllStrategyCountUserId(strategy.getUserId());
            Integer countCollect=collectService.getCollectCount(strategy.getStrategyId(),2);
            Collect collect=new Collect();
            if(users!=null){
              collect =collectService.selectByUserIdAndScenicId(users.getUserId(),strategy.getStrategyId(),2);
            }


            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("strategy", strategy);
            map.put("sCommentsList", sCommentsList);
            map.put("users", users);
            map.put("countStrategy", countStrategy);
            map.put("countCollect", countCollect);
            map.put("collect", collect);

        }
        return map;
    }

    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/wx_strategy_view")
    public Object getStrategyView(@RequestParam("strategyId")String strategyId,
                                  @RequestParam("pageIndex")Integer pageIndex,
                                  @RequestParam("userId") Integer userId,
                                  @RequestParam("size")Integer size,
                                  HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(strategyId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Strategy strategy = strategyService.selectByPrimaryKey(Integer.parseInt(strategyId));
            List<S_comments> sCommentsList=sCommentsService.getAllS_commentsById(strategy.getStrategyId(),size*(pageIndex-1),size);
            total=sCommentsService.getAllCountById(strategy.getStrategyId());
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            if(sCommentsList==null){
                sCommentsList=new ArrayList<>();
            }
            for(int i=0;i<sCommentsList.size();i++){
                S_comments evaluates=sCommentsList.get(i);
                List<S_reply> sReplyList=new ArrayList<S_reply>();
                sReplyList=sReplyService.getReplyByCommentId(evaluates.getCommentId());
                sCommentsList.get(i).setsReplyList(sReplyList);
            }
            Users users= (Users) session.getAttribute("userSession");
            Integer countStrategy=strategyService.getAllStrategyCountUserId(strategy.getUserId());
            Integer countCollect=collectService.getCollectCount(strategy.getStrategyId(),2);
            Collect collect=new Collect();
            if(users!=null){
                collect =collectService.selectByUserIdAndScenicId(userId,strategy.getStrategyId(),2);
            }


            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("strategy", strategy);
            map.put("sCommentsList", sCommentsList);
            map.put("users", users);
            map.put("countStrategy", countStrategy);
            map.put("countCollect", countCollect);
            map.put("collect", collect);

        }
        return map;
    }

    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/comment_list")
    public Object getStrategyList(@RequestParam("strategyId")String strategyId,
                                  @RequestParam("pageIndex")Integer pageIndex,
                                  @RequestParam("size")Integer size,
                                  HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(strategyId==null||strategyId.equals("")){
            map.put("message", "系统异常，请重新选择");
        }
        else {
           // Strategy strategy = strategyService.selectByPrimaryKey(Integer.parseInt(strategyId));
            List<S_comments> sCommentsList=sCommentsService.getAllS_commentsById(Integer.parseInt(strategyId),size*(pageIndex-1),size);
            total=sCommentsService.getAllCountById(Integer.parseInt(strategyId));
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            for(int i=0;i<sCommentsList.size();i++){
                S_comments evaluates=sCommentsList.get(i);
                List<S_reply> sReplyList=new ArrayList<S_reply>();
                sReplyList=sReplyService.getReplyByCommentId(evaluates.getCommentId());
                sCommentsList.get(i).setsReplyList(sReplyList);
            }
            // Users users= (Users) session.getAttribute("userSession");

            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("sCommentsList", sCommentsList);

        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/user_list")
    public Object getEvaluatesList(@RequestParam("searchTitle") String searchTitle,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size , HttpSession session){
        List<Strategy> strategyList;
        Integer total=0;
        int pageCount=0;
        int evaluateLevel=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        Users users= (Users) session.getAttribute("userSession");
        strategyList =strategyService.getStrategyInitUp(searchTitle,users.getUserId(),start,end,10*(pageIndex1-1),size1);
        total=strategyService.getStrategyInitUpCount(searchTitle,users.getUserId(),start,end);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("strategyList",strategyList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("users",users);
        map.put("searchTitle",searchTitle);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/wx_user_list")
    public Object getEvaluatesList(@RequestParam("searchTitle") String searchTitle,
                                   @RequestParam("startTime") String start,
                                   @RequestParam("endTime") String end,
                                   @RequestParam("userId") Integer userId,
                                   @RequestParam("pageIndex")String pageIndex,
                                   @RequestParam("size")String size , HttpSession session){
        List<Strategy> strategyList;
        Integer total=0;
        int pageCount=0;
        int evaluateLevel=0;
        int pageIndex1=0;
        int size1=0;
        if(pageIndex!=null&&size!=null){
            pageIndex1=Integer.parseInt(pageIndex);
            size1=Integer.parseInt(size);
        }
        Map<String,Object> map =new HashMap<>(6);
        Integer pageSize=Integer.parseInt(size);
        Users users= (Users) session.getAttribute("userSession");
        strategyList =strategyService.getStrategyInitUp(searchTitle,userId,start,end,10*(pageIndex1-1),size1);
        total=strategyService.getStrategyInitUpCount(searchTitle,userId,start,end);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("strategyList",strategyList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("startTime",start);
        map.put("endTime",end);
        map.put("users",users);
        map.put("searchTitle",searchTitle);
        return map;
    }

}
