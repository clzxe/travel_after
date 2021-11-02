package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.*;
import com.travel.travel.util.MyUtil;
import com.travel.travel.util.QiniuCloudUtil;
import com.travel.travel.util.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/travel/line")
public class LineController {
    @Resource
    private ClassesService classesService;
    @Resource
    private CityService cityService;
    @Resource
    private ProvinceService provinceService;
    @Resource
    private LinesService linesService;
    @Resource
    private ThemesService themesService;
    @Resource
    private ScenicsService scenicsService;
    @Resource
    private Travel_timeService travel_timeService;
    @Resource
    private H_fileService h_fileService;
    @Resource
    private HotelsService hotelsService;
    @Resource
    private Hotel_roomsService hotel_roomsService;
    @CrossOrigin
    @GetMapping(value = "/getThemes")
    public Object getLineInti(){
        Map<String,Object> map =new HashMap<>(4);

        List<Scenics> scenic=scenicsService.getAll();
        List<Classes> classesList=classesService.getAll();
        List<Province> provinceList=provinceService.getAllProvince();
        List<Hotels> hotelList=hotelsService.getAll();
        map.put("hotelList",hotelList);
        map.put("classList",classesList);
        map.put("scenic",scenic);
        map.put("provinceList",provinceList);

        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getTimes")
    public Object getLineTime(Integer lineId){
        Map<String,Object> map =new HashMap<>(4);

        List<Travel_time> travel_times=travel_timeService.selectByLineId(lineId);
        map.put("travel_times",travel_times);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getLineList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Lines> lineList;
        Integer total=0;
        Integer pageCount=0;
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        lineList=linesService.getAllLines(searchTitle,10*(pageIndex-1),size);
        for(int i=0;i<=lineList.size()-1;i++){
            Lines lines=lineList.get(i);
            if(lines.getIsIncludeHotel()==1){
                lineList.get(i).setHotel(hotelsService.selectByPrimaryKey(lines.getHotelId()));
                lineList.get(i).setHotel_room(hotel_roomsService.selectByPrimaryKey(lines.getRoomId()));
            }
            else {
                lineList.get(i).setHotel(new Hotels());
                lineList.get(i).setHotel_room(new Hotel_rooms());
            }
            String[] arr=lines.getLineScenic().split(",");
            List<Scenics> scenicList=new ArrayList<Scenics>();
            if(arr.length!=0){
                for(String scecnicId:arr){
                    scenicList.add(scenicsService.selectByPrimaryKey(Integer.parseInt(scecnicId)));
                }
            }
            else {
                scenicList.add(new Scenics());
            }
            lineList.get(i).setScenicList(scenicList);

        }
        total=linesService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("lineList",lineList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        return map;
    }
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveScenic(Lines lines, HttpSession session, MultipartFile headFile, MultipartFile[] file) throws IOException {

        Map<String,Object> map = new HashMap<>(4);
        String path="";
        String realPath="E:\\作业\\毕设\\projectImage\\line";
        String originalName ="";
        lines.setLineCreatTime(new Date());
        if(headFile!=null){
            originalName = headFile.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            byte[] bytes = headFile.getBytes();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, ImageName);
                map.put("message","OK");
                lines.setLinePhoto(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            if(!linesService.insert(lines)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        StringBuilder errorList = new StringBuilder();
        H_file sFile=new H_file();
        sFile.setLineId(lines.getLineId());
        for (MultipartFile multipartFile : file) {


            String originalFilename = multipartFile.getOriginalFilename();
            //随机生成文件名
            String fileName = MyUtil.getNewFileName(originalName);
            byte[] bytes = multipartFile.getBytes();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, fileName);
                map.put("message","OK");
                sFile.setFilePath(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            File dest = new File(realPath ,fileName);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            sFile.setCreateTime(new Date());
            try {
                multipartFile.transferTo(dest);
                map.put("message","添加成功");
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("ERROR", e.getMessage());

            }
            if(!h_fileService.insert(sFile)){
                errorList.append("\n图片").append(originalFilename).append("上传失败");
                map.put("errorList",errorList);
            }


        }
        session.setAttribute("lineId",lines.getLineId());
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/insert_time")
    public Object  insertTime(Travel_time travel_time, HttpSession session){
        Map<String,Object> map = new HashMap<>(4);
        String line= session.getAttribute("lineId").toString();
        travel_time.setTravelTimeLineId((Integer) session.getAttribute("lineId"));
        if(travel_timeService.insert(travel_time)){
            map.put("ERROR", "系统响应错误");
        }
        else {
            map.put("SUCCESS", "添加成功");
        }

        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/update_time")
    public Object  updateTime(Travel_time travel_time, HttpSession session){
        Map<String,Object> map = new HashMap<>(4);
        if(travel_time.getTravelTimeId()==null||travel_time.getTravelTimeId()==0){
            if(travel_timeService.insert(travel_time)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        else {
            if(travel_timeService.updateByPrimaryKey(travel_time)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }


        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateScenic(Lines lines, HttpSession session, MultipartFile headFile,MultipartFile[] file) throws IOException {

        Map<String,Object> map = new HashMap<>(4);
        String path="";
        String realPath="E:\\作业\\毕设\\projectImage\\line";
        String originalName ="";
        if(headFile!=null){
            originalName = headFile.getOriginalFilename();
            assert originalName != null;
            String ImageName = MyUtil.getNewFileName(originalName);

            byte[] bytes = headFile.getBytes();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String url = qiniuUtil.put64image(bytes, ImageName);
                map.put("message","OK");
                lines.setLinePhoto(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            if(!linesService.updateByPrimaryKey(lines)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        else {
            if(!linesService.updateByPrimaryKey(lines)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        if(file!=null&&file.length!=0){
            StringBuilder errorList = new StringBuilder();
            H_file sFile=new H_file();
            sFile.setLineId(lines.getLineId());
            for (MultipartFile multipartFile : file) {


                String originalFilename = multipartFile.getOriginalFilename();
                //随机生成文件名
                String fileName = MyUtil.getNewFileName(originalName);
                byte[] bytes = multipartFile.getBytes();

                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, fileName);
                    map.put("message","OK");
                    sFile.setFilePath(url);
                    map.put("link", url);
                    System.out.println("'cccccccccccccc'"+url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File dest = new File(realPath ,fileName);
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                sFile.setCreateTime(new Date());
                try {
                    multipartFile.transferTo(dest);
                    map.put("message","添加成功");
                    //  path+=feedPicture+",";
                } catch (Exception e) {
                    map.put("ERROR", e.getMessage());

                }
                if(!h_fileService.insert(sFile)){
                    errorList.append("\n图片").append(originalFilename).append("上传失败");
                    map.put("errorList",errorList);
                }


            }
        }

        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/del")
    public String delLine(@RequestParam("lineId")String lineId){
        if(lineId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(linesService.deleteByPrimaryKey(Integer.parseInt(lineId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }
    @CrossOrigin
    @PostMapping(value = "/delTime")
    public String delTime(@RequestParam("travelTimeId")String travelTimeId){
        if(travelTimeId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(travel_timeService.deleteByPrimaryKey(Integer.parseInt(travelTimeId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    @CrossOrigin
    @PostMapping(value = "/delFile")
    public Object delLineImage(@RequestParam("fileId")String fileId,@RequestParam("lineId")String lineId){
        Map<String,Object> map =new HashMap<>();
        if(fileId==null){
            map.put("message","系统异常，请重新选择");
        }
        else {
            if(h_fileService.deleteByPrimaryKey(Integer.parseInt(fileId))){
                map.put("message","OK");
            }
            else {
                map.put("message","系统响应错误");
            }
        }
        List<H_file> ImageList=h_fileService.getFilesBylineId(Integer.parseInt(lineId));
        map.put("ImageList",ImageList);
        return map;
    }
    @CrossOrigin
    @GetMapping(value = "/getline")
    public Object getLineView(@RequestParam("lineId")String lineId){
        Map<String,Object> map =new HashMap<>();
        List<Themes> themes=null;
        List<Travel_time> travelTimes=null;
        List<H_file> ImageList=h_fileService.getFilesBylineId(Integer.parseInt(lineId));
        Lines lines=linesService.selectByPrimaryKey(Integer.parseInt(lineId));
        themes=themesService.getThemes();
        travelTimes=travel_timeService.selectByLineId(Integer.parseInt(lineId));
        map.put("themes",themes);
        map.put("line",lines);
        map.put("ImageList",ImageList);
        map.put("travelTimes",travelTimes);
        return map;
    }


    @CrossOrigin
    @GetMapping(value = "/getImage")
    public Object getScenicImage(@RequestParam("lineId")String lineId){
        Map<String,Object> map =new HashMap<>(4);
        List<H_file> ImageList = null;
        if(lineId!=null&&!lineId.equals("")){
            ImageList=h_fileService.getFilesBylineId(Integer.parseInt(lineId));


        }
        map.put("ImageList",ImageList);
        return map;
    }
    @CrossOrigin
    @GetMapping(value = "/getLines")
    public Object updateLine(@RequestParam("lineId")String lineId){
        Map<String,Object> map =new HashMap<>(7);
        List<Themes> themes=null;
        List<Travel_time> travelTimes=null;
        List<H_file> ImageList=h_fileService.getFilesBylineId(Integer.parseInt(lineId));
        Lines lines=linesService.selectByPrimaryKey(Integer.parseInt(lineId));
        themes=themesService.getThemes();
        travelTimes=travel_timeService.selectByLineId(Integer.parseInt(lineId));
        Integer rowNum=travelTimes.get(travelTimes.size()-1).getRowNum();
        List<Scenics> scenic=scenicsService.getAll();
        List<Classes> classesList=classesService.getAll();
        List<Province> provinceList=provinceService.getAllProvince();
        List<Hotels> hotelList=hotelsService.getAll();
        if(lines.getIsIncludeHotel()==1){
            lines.setHotel(hotelsService.selectByPrimaryKey(lines.getHotelId()));
            lines.setHotel_room(hotel_roomsService.selectByPrimaryKey(lines.getRoomId()));
        }
        else {
            lines.setHotel(new Hotels());
            lines.setHotel_room(new Hotel_rooms());
        }
        String[] arr=lines.getLineScenic().split(",");
        List<Scenics> scenicList=new ArrayList<Scenics>();
        if(arr.length!=0){
            for(String scecnicId:arr){
                scenicList.add(scenicsService.selectByPrimaryKey(Integer.parseInt(scecnicId)));
            }
        }
        else {
            scenicList.add(new Scenics());
        }

        map.put("hotelList",hotelList);
        map.put("classList",classesList);
        map.put("scenic",scenic);
        map.put("scenicList",scenicList);
        map.put("provinceList",provinceList);
        map.put("themes",themes);
        map.put("line",lines);
        map.put("ImageList",ImageList);
        map.put("travelTimes",travelTimes);
        map.put("rowNum",rowNum);
        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getTime")
    public Object getTimes(@RequestParam("lineId")String lineId){
        Map<String,Object> map =new HashMap<>(3);
        List<Travel_time> travelTimes=travel_timeService.selectByLineId(Integer.parseInt(lineId));
        Integer rowNum=travelTimes.size();
        map.put("travelTimes",travelTimes);
        map.put("rowNum",rowNum);
        return map;
    }



}
