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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/hotel")
public class HotelsController {
    @Resource
    private HotelsService hotelsService;
    @Resource
    private Hotel_roomsService hotel_roomsService;
    @Resource
    private CityService cityService;
    @Resource
    private EvaluatesService evaluatesService;
    @Resource
    private E_replyService eReplyService;
    /**
     * 添加
     */
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveHotel(Hotels hotels, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,String> map = new HashMap<>(5);
        String realPath="E:\\作业\\毕设\\projectImage\\hotel";
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
                hotels.setHotelImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }


            if(!hotelsService.insert(hotels)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }
            try {
                headImg.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
        }


        
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateHotel(Hotels hotels, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,String> map = new HashMap<>(5);
        String realPath="E:\\作业\\毕设\\projectImage\\hotel";
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
                hotels.setHotelImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }

            if(!hotelsService.updateByPrimaryKey(hotels)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }
            try {
                headImg.transferTo(targetFile);
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("message", e.getMessage());
            }
        }
        else{
            if(!hotelsService.updateByPrimaryKey(hotels)){
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
    @PostMapping(value = "/list")
    public Object getHotelList(@RequestParam("searchTitle") String searchTitle,
                                  @RequestParam("searchScenic") Integer searchScenic,
                                  @RequestParam("pageIndex")Integer pageIndex,
                                  @RequestParam("size")Integer size ){
        List<Hotels> hotelsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=size;
        hotelsList=hotelsService.getAllHotels(searchTitle,searchScenic,10*(pageIndex-1),size);
        total=hotelsService.getAllHotelsCount(searchTitle,searchScenic);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("hotelList",hotelsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("searchScenic",searchScenic);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delStrategy(@RequestParam("hotelId")String hotelId){
        if(hotelId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(hotelsService.deleteByPrimaryKey(Integer.parseInt(hotelId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }
    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del_room")
    public String delRoom(@RequestParam("hotelRoomId")String hotelRoomId){
        if(hotelRoomId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(hotel_roomsService.deleteByPrimaryKey(Integer.parseInt(hotelRoomId))){
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
    @GetMapping("/gethotel")
    public Object getStrategyByStrategyId(@RequestParam("hotelId")String hotelId) {
        Map<String,Object> map= new HashMap<>(3);
        if(hotelId==null||hotelId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Hotels hotels=hotelsService.selectByPrimaryKey(Integer.parseInt(hotelId));
            map.put("message","OK");
            map.put("hotel",hotels);
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/save_room")
    public Object saveHotelRoom(Hotel_rooms hotels, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,String> map = new HashMap<>(5);
        String realPath="E:\\作业\\毕设\\travel_up\\static\\uploadFiles";
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
                hotels.setHotelRoomImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!hotel_roomsService.insert(hotels)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }

        }



        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update_room")
    public Object updateHotelRoom(Hotel_rooms hotels, HttpSession session, MultipartFile headImg) throws IOException {

        Map<String,String> map = new HashMap<>(5);
        String realPath="E:\\作业\\毕设\\travel_up\\static\\uploadFiles";
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
                hotels.setHotelRoomImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!hotel_roomsService.insert(hotels)){
                map.put("message", "系统响应错误");
            }
            else {
                map.put("message", "添加成功");
            }
        }
        else{
            if(!hotel_roomsService.updateByPrimaryKey(hotels)){
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
    @GetMapping(value = "/room_list")
    public Object getHotelRoomList(@RequestParam("hotelId")String hotelId ){
        Map<String,Object> map= new HashMap<>(3);
        if(hotelId==null||hotelId.equals("")){
            map.put("message","ERROR");
        }
        else{
            List<Hotel_rooms> hotelRoomList=hotel_roomsService.getRoomByHotelId(Integer.parseInt(hotelId));
            map.put("message","OK");
            map.put("hotelRoomList",hotelRoomList);
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getHotelRoom")
    public Object getRoomByRoomId(@RequestParam("hotelRoomId")String hotelRoomId) {
        Map<String,Object> map= new HashMap<>(3);
        if(hotelRoomId==null||hotelRoomId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Hotel_rooms hotels=hotel_roomsService.selectByPrimaryKey(Integer.parseInt(hotelRoomId));
            map.put("message","OK");
            map.put("hotel_rooms",hotels);
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/nav_hotel_init")
    public Object getHotelInit() {
        Map<String,Object> map= new HashMap<>(7);
        List<City> cityList=cityService.getCities();
        List<Map<String,Object>> sortChild=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>(3);
        map1.put("name","全部");
        map1.put("value",0);
        map1.put("active",true);
        sortChild.add(map1);
        for (City city:cityList) {
             map1=new HashMap<>(3);
            map1.put("name",city.getCityName());
            map1.put("value",city.getCityId());
            map1.put("active",false);
            sortChild.add(map1);
        }
        map.put("sortChild",sortChild);
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/hotel_list")
    public Object getHotelListUp(@RequestParam("searchTitle") String searchTitle,
                               @RequestParam("cityId") Integer cityId,
                                 @RequestParam("sortType") Integer sortType,
                               @RequestParam("pageIndex")Integer pageIndex,
                               @RequestParam("size")Integer size ){
        List<Hotels> hotelsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        hotelsList=hotelsService.getHotelInit(searchTitle,cityId,sortType,size*(pageIndex-1),size);
        if(sortType==1){
            total=16;
        }
        else {
            total=hotelsService.getHotelInitCount(searchTitle,cityId,sortType);
        }
        if(hotelsList==null||hotelsList.size()==0){
            hotelsList=hotelsService.getHotelInit(searchTitle,cityId,0,size*(pageIndex-1),size);
            total=hotelsService.getHotelInitCount(searchTitle,cityId,0);
        }
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("hotelList",hotelsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("cityId",cityId);
        map.put("sortType",sortType);
        return map;
    }

    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/hotel_view")
    public Object getScenicView(@RequestParam("hotelId")String hotelId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(hotelId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Hotels hotels = hotelsService.selectByPrimaryKey(Integer.parseInt(hotelId));
            List<Hotel_rooms> hotelRoomsList=hotel_roomsService.getRoomByHotelId(Integer.parseInt(hotelId));
            List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(hotels.getHotelId(),2,0,size*(pageIndex-1),size);
            total=evaluatesService.getAllEvaluatesByLineIdCount(hotels.getHotelId(),2,0);
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            for(int i=0;i<evaluateList.size();i++){
                Evaluates evaluates=evaluateList.get(i);
                List<E_reply> eReplyList=new ArrayList<E_reply>();
                eReplyList=eReplyService.getReplyByCommentId(evaluates.getEvaluateId());
                evaluateList.get(i).setReplyList(eReplyList);
            }
            Users users= (Users) session.getAttribute("userSession");
            if(users==null){
                users=new Users();
            }
            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("hotel", hotels);
            map.put("evaluateList", evaluateList);
            map.put("hotelRoomList", hotelRoomsList);
            map.put("user", users);
        }
        return map;
    }


}
