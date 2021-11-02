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
@RequestMapping("/travel/scenic")
public class ScenicController {
    @Resource
    private ScenicsService scenicsService;
    @Resource
    private S_fileService sFileService;
    @Resource
    private ThemesService themesService;
    @Resource
    private ProvinceService provinceService;
    @Resource
    private CityService cityService;
    @Resource
    private ClassesService classesService;
    @Resource
    private StrategyService strategyService;
    @Resource
    private CollectService collectService;
    @Resource
    private UserActionService userActionService;
    @Resource
    private ScenicTYpeService scenicTYpeService;
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getScenicList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Scenics> scenicsList;
        Integer total=0;
        Integer pageCount=0;
        Map<String,Object> map =new HashMap<>(4);
        Integer pageSize=size;
        scenicsList=scenicsService.getAllScenic(searchTitle,10*(pageIndex-1),size);
        total=scenicsService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("scenicList",scenicsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveScenic(Scenics scenics, HttpSession session, MultipartFile headFile,MultipartFile[] file) throws IOException {

        Map<String,Object> map = new HashMap<>(4);
        String path="";
        String realPath="E:\\作业\\毕设\\projectImage\\scenic";
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
                scenics.setScenicImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            if(!scenicsService.insert(scenics)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
       StringBuilder errorList = new StringBuilder();
        S_file sFile=new S_file();
        sFile.setFileScenicId(scenics.getScenicId());
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
            try {
                multipartFile.transferTo(dest);
                map.put("message","添加成功");
                //  path+=feedPicture+",";
            } catch (Exception e) {
                map.put("ERROR", e.getMessage());

            }
            if(!sFileService.insert(sFile)){
                errorList.append("\n图片").append(originalFilename).append("上传失败");
                map.put("errorList",errorList);
            }


        }
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateScenic(Scenics scenics, HttpSession session, MultipartFile headFile,MultipartFile[] file) throws IOException {

        Map<String,Object> map = new HashMap<>(4);
        String path="";
        String realPath="E:\\作业\\毕设\\projectImage\\scenic";
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
                scenics.setScenicImg(url);
                map.put("link", url);
                System.out.println("'cccccccccccccc'"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File targetFile = new File(realPath ,ImageName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();

            }
            if(!scenicsService.updateByPrimaryKey(scenics)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        else {
            if(!scenicsService.updateByPrimaryKey(scenics)){
                map.put("ERROR", "系统响应错误");
            }
            else {
                map.put("SUCCESS", "添加成功");
            }
        }
        if(file!=null&&file.length!=0){
            StringBuilder errorList = new StringBuilder();
            S_file sFile=new S_file();
            sFile.setFileScenicId(scenics.getScenicId());
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
                try {
                    multipartFile.transferTo(dest);
                    map.put("message","添加成功");
                    //  path+=feedPicture+",";
                } catch (Exception e) {
                    map.put("ERROR", e.getMessage());

                }
                if(!sFileService.insert(sFile)){
                    errorList.append("\n图片").append(originalFilename).append("上传失败");
                    map.put("errorList",errorList);
                }


            }
        }

        return map;
    }
    @CrossOrigin
    @GetMapping(value = "/getThemes")
    public Object getScenicAddInti(){
        Map<String,Object> map =new HashMap<>(4);
      // List<Themes> themes=null;
       List<Province> provinceList=null;
      /* themes=themesService.getThemes();
       List<Classes> classList=classesService.getAll();*/
       provinceList=provinceService.getAllProvince();
        List<ScenicType> scenicTypeList=scenicTYpeService.getTypeAllNav();
        map.put("scenicTypeList",scenicTypeList);
        map.put("provinceList",provinceList);
        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getCityList")
    public Object getScenicCitys(@RequestParam("provinceId")String provinceId){

        Map<String,Object> map =new HashMap<>(5);
        List<City> cityList=null;
        if(provinceId==null||provinceId.equals("")){
            map.put("Message","error");
            map.put("ERROR","没有选择Id");
        }
        else {
            map.put("Message","Ok");
            cityList=cityService.getCitiesByProvinceId(Integer.parseInt(provinceId));
            map.put("cityList",cityList);
        }

        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getScenicCity")
    public Object getScenicCitysO(@RequestParam("cityId")String cityId){

        Map<String,Object> map =new HashMap<>(5);
        List<Scenics> scenicList=null;
        if(cityId==null||cityId.equals("")){
            map.put("Message","error");
            map.put("ERROR","没有选择Id");
        }
        else {
            map.put("Message","Ok");
            scenicList=scenicsService.getScenicNotScenicId(0,Integer.parseInt(cityId));
            map.put("scenicList",scenicList);
        }

        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getTypeList")
    public Object getScenicType(){

        Map<String,Object> map =new HashMap<>(4);
        // List<Themes> themes=null;
        List<ScenicType> scenicTypeList=null;
      /* themes=themesService.getThemes();
       List<Classes> classList=classesService.getAll();*/
        scenicTypeList=scenicTYpeService.getTypeAllNav();
        map.put("scenicTypeList",scenicTypeList);
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delScenic(@RequestParam("scenicId")String scenicId){
        if(scenicId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(scenicsService.deleteByPrimaryKey(Integer.parseInt(scenicId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    @CrossOrigin
    @GetMapping(value = "/getScenic")
    public Object getScenicUpdateInti(@RequestParam("scenicId")Integer scenicId){
        Map<String,Object> map =new HashMap<>(4);
        List<Themes> themes=null;
        List<Province> provinceList=null;
        Scenics scenics=scenicsService.selectByPrimaryKey(scenicId);
        List<S_file> ImageList=sFileService.getFilesByScenicId(scenicId);
        map.put("ImageList",ImageList);
        provinceList=provinceService.getAllProvince();
        List<ScenicType> scenicTypeList=null;
      /* themes=themesService.getThemes();
       List<Classes> classList=classesService.getAll();*/
        scenicTypeList=scenicTYpeService.getTypeAllNav();
        map.put("scenicTypeList",scenicTypeList);
        map.put("scenic",scenics);

        map.put("provinceList",provinceList);
        return map;
    }


    @CrossOrigin
    @GetMapping(value = "/getImage")
    public Object getScenicImage(@RequestParam("scenicId")String scenicId){
        Map<String,Object> map =new HashMap<>(4);
        List<S_file> ImageList = null;
        if(scenicId!=null&&!scenicId.equals("")){
            ImageList=sFileService.getFilesByScenicId(Integer.parseInt(scenicId));

           
        }
        map.put("ImageList",ImageList);
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/delFile")
    public Object delScenicImage(@RequestParam("fileId")String fileId){
        Map<String,Object> map =new HashMap<>(2);
        if(fileId==null){
            map.put("message","系统异常，请重新选择");
        }
        else {
            if(sFileService.deleteByPrimaryKey(Integer.parseInt(fileId))){
                map.put("message","OK");
            }
            else {
                map.put("message","系统响应错误");
            }
        }
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/scenic_list")
    public Object getHotelListUp(@RequestParam("searchTitle") String searchTitle,
                                 @RequestParam("cityId") Integer cityId,
                                 @RequestParam("sortType") Integer sortType,
                                 @RequestParam("pageIndex")Integer pageIndex,
                                 @RequestParam("size")Integer size ){
        List<Scenics> scenicsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        scenicsList=scenicsService.getScenicInit(searchTitle,cityId,sortType,size*(pageIndex-1),size,null,null,null,0,null,null);
        if(sortType==1){
            total=16;
        }
        else {
            total=scenicsService.getScenicInitCount(searchTitle,cityId,sortType,null,null,null,0,null,null);
        }
        if(scenicsList==null||scenicsList.size()==0){
            scenicsList=scenicsService.getScenicInit(searchTitle,cityId,0,size*(pageIndex-1),size,null,null,null,0,null,null);
            total=scenicsService.getScenicInitCount(searchTitle,cityId,0,null,null,null,0,null,null);
        }
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("scenicList",scenicsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("cityId",cityId);
        map.put("sortType",sortType);
        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/scenic_list_more")
    public Object getHotelListUpMOre(@RequestParam("searchTitle") String searchTitle,
                                     @RequestParam("cityId") Integer cityId,
                                     @RequestParam("typeId") String typeId,
                                     @RequestParam("people") String people,
                                     @RequestParam("season") String season,
                                     @RequestParam("areaId") Integer areaId,
                                     @RequestParam("priceUp") String priceUp,
                                     @RequestParam("priceDown") String priceDown,
                                     @RequestParam("sortType") Integer sortType,
                                     @RequestParam("pageIndex")Integer pageIndex,
                                     @RequestParam("size")Integer size ){
        List<Scenics> scenicsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        scenicsList=scenicsService.getScenicInit(searchTitle,cityId,sortType,size*(pageIndex-1),size,typeId,people,season,areaId,priceUp,priceDown);
        if(sortType==1){
            total=16;
        }
        else {
            total=scenicsService.getScenicInitCount(searchTitle,cityId,sortType,typeId,people,season,areaId,priceUp,priceDown);
        }
        if(scenicsList==null||scenicsList.size()==0){
            scenicsList=scenicsService.getScenicInit(searchTitle,cityId,0,size*(pageIndex-1),size,typeId,people,season,areaId,priceUp,priceDown);
            total=scenicsService.getScenicInitCount(searchTitle,cityId,0,typeId,people,season,areaId,priceUp,priceDown);
        }
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("scenicList",scenicsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("cityId",cityId);
        map.put("typeId",typeId);
        map.put("people",people);
        map.put("season",season);
        map.put("areaId",areaId);
        map.put("priceUp",priceUp);
        map.put("priceDown",priceDown);
        map.put("sortType",sortType);
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/scenic_list2")
    public Object getHotelListUp2(String searchTitle,
                                 Integer cityId,
                                 Integer sortType,
                                 Integer pageIndex,
                                 @RequestParam("size")Integer size ){
        List<Scenics> scenicsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        scenicsList=scenicsService.getScenicInit(searchTitle,cityId,sortType,size*(pageIndex-1),size,null,null,null,0,null,null);
        if(sortType==1){
            total=16;
        }
        else {
            total=scenicsService.getScenicInitCount(searchTitle,cityId,sortType,null,null,null,0,null,null);
        }
        if(scenicsList==null||scenicsList.size()==0){
            scenicsList=scenicsService.getScenicInit(searchTitle,cityId,0,size*(pageIndex-1),size,null,null,null,0,null,null);
            total=scenicsService.getScenicInitCount(searchTitle,cityId,0,null,null,null,0,null,null);
        }
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("scenicList",scenicsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("cityId",cityId);
        map.put("sortType",sortType);
        return map;
    }
    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/wx_scenic_init")
    public Object getCityInit() {
        Map<String,Object> map= new HashMap<>(7);
        List<Province> provinceList=provinceService.getAllProvince();
        List<Map<String,Object>> sortChild=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>(3);
        Map<String,Object> map2=new HashMap<>(3);
        List<Map<String,Object>> map3=new ArrayList<>();

        for (Province city:provinceList) {
            List<City> citys=cityService.getCitiesByProvinceId(city.getProvinceId());
            map1=new HashMap<>(3);
            map1.put("name",city.getProvinceName());
            map1.put("id",city.getProvinceId());
            map3=new ArrayList<>();
            for (City city2:citys) {
                map2=new HashMap<>(3);
                map2.put("name",city2.getCityName());
                map2.put("id",city2.getCityId());
                map3.add(map2);
            }
            map1.put("children",map3);
            sortChild.add(map1);
        }
        map.put("cityList",sortChild);
        return map;
    }
    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/nav_scenic_init")
    public Object getHotelInit() {
        Map<String,Object> map= new HashMap<>(7);
        List<Province> provinceList=provinceService.getAllProvince();
        List<Map<String,Object>> sortChild=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>(3);
        map1.put("name","全部");
        map1.put("value",0);
        map1.put("active",true);
        sortChild.add(map1);
        for (Province city:provinceList) {
            map1=new HashMap<>(3);
            map1.put("name",city.getProvinceName());
            map1.put("value",city.getProvinceId());
            map1.put("active",false);
            sortChild.add(map1);
        }
        map.put("sortChild",sortChild);
        List<Themes> themesList =themesService.getThemesAllNav();
        List<Map<String,Object>> sortChild2=new ArrayList<>();
        Map<String,Object> map2=new HashMap<>(3);
        map2.put("name","全部");
        map2.put("value",0);
        map2.put("active",true);
        sortChild2.add(map2);
        for (Themes city:themesList) {
            map2=new HashMap<>(3);
            map2.put("name",city.getThemeName());
            map2.put("value",city.getThemeId());
            map2.put("active",false);
            sortChild2.add(map2);
        }
        map.put("sortChild2",sortChild2);
        return map;
    }
    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/nav_scenic_init_more")
    public Object getScenicInit() {
        Map<String,Object> map= new HashMap<>(7);
        List<Province> provinceList=provinceService.getAllProvinceByType(0);
        List<Map<String,Object>> sortChild=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>(3);
        map1.put("name","全部");
        map1.put("value",0);
        map1.put("active",true);
        sortChild.add(map1);
        for (Province city:provinceList) {
            map1=new HashMap<>(3);
            map1.put("name",city.getProvinceName());
            map1.put("value",city.getProvinceId());
            map1.put("active",false);
            sortChild.add(map1);
        }
        map.put("sortChild",sortChild);
        List<ScenicType> scenicTypeList =scenicTYpeService.getTypeAllNav();
        List<Map<String,Object>> sortChild2=new ArrayList<>();
        Map<String,Object> map2=new HashMap<>(3);
        map2.put("name","全部");
        map2.put("value",null);
        map2.put("id",0);
        map2.put("active",true);
        sortChild2.add(map2);
        int i=0;
        for (ScenicType city:scenicTypeList) {
            i++;
            map2=new HashMap<>(3);
            map2.put("name",city.getTypeName());
            map2.put("value",city.getTypeName());
            map2.put("id",i);
            map2.put("active",false);
            sortChild2.add(map2);
        }
        map.put("sortChild2",sortChild2);
        return map;
    }
    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getThemClass")
    public Object getScenicInitType() {
        Map<String,Object> map= new HashMap<>(7);

        List<ScenicType> scenicTypeList =scenicTYpeService.getTypeAllNav();

        map.put("scenicTypeList",scenicTypeList);
        return map;
    }
    @CrossOrigin
    @GetMapping("/nav_scenic_province")
    public Object getScenicInit(@RequestParam("areaId")Integer areaId) {
        Map<String,Object> map= new HashMap<>(7);
        List<Province> provinceList=provinceService.getAllProvinceByType(areaId);
        List<Map<String,Object>> sortChild=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>(3);
        map1.put("name","全部");
        map1.put("value",0);
        map1.put("active",true);
        sortChild.add(map1);
        for (Province city:provinceList) {
            map1=new HashMap<>(3);
            map1.put("name",city.getProvinceName());
            map1.put("value",city.getProvinceId());
            map1.put("active",false);
            sortChild.add(map1);
        }
        map.put("sortChild",sortChild);
        return map;
    }
    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/strategy_list")
    public Object getStratteg(@RequestParam("scenicId")String scenicId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(scenicId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Scenics scenics = scenicsService.selectByPrimaryKey(Integer.parseInt(scenicId));
            List<Strategy> strategyList=strategyService.getAllStrategy(null,scenics.getScenicId(),size*(pageIndex-1),size);
            total=strategyService.getAllStrategyCount(null,scenics.getScenicId());
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("strategyList", strategyList);
        }
        return map;
    }
    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/scenic_view")
    public Object getScenicView(@RequestParam("scenicId")String scenicId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(scenicId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Scenics scenics = scenicsService.selectByPrimaryKey(Integer.parseInt(scenicId));
            List<Strategy> strategyList=strategyService.getAllStrategy(null,scenics.getScenicId(),size*(pageIndex-1),size);
            total=strategyService.getAllStrategyCount(null,scenics.getScenicId());
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            Integer countCollect=collectService.getCollectCount(scenics.getScenicId(),1);
            Users users= (Users) session.getAttribute("userSession");
            map.put("countCollect", countCollect);
            if(users==null){
                users=new Users();
            }
            List<S_file> ImageList=sFileService.getFilesByScenicId(scenics.getScenicId());
            map.put("ImageList",ImageList);
            map.put("total",total);
            map.put("pageCount",pageCount);
            map.put("scenic", scenics);

            map.put("strategyList", strategyList);
            map.put("users", users);
        }
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/scenic_list_hot")
    public Object getLineHot(){
        List<Scenics> scenicsList;

        Map<String,Object> map =new HashMap<>(7);
        scenicsList=scenicsService.getScenicLimitSix(6);

        map.put("scenicList",scenicsList);

        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/timeOut_view")
    public Object try22(@RequestParam("userId")Integer userId,@RequestParam("scenicId")Integer scenicId){

        Map<String,Object> map =new HashMap<>(7);

        if(userActionService.selectByScore(userId,scenicId)==null){
            UserAction userAction=new UserAction();
            userAction.setUserId(userId);
            userAction.setScenicId(scenicId);
            userAction.setScore(1);
            if(userActionService.insert(userAction)){
                map.put("message", "OK");
            }
            else {
                map.put("message", "ERROR");
            }
        }
        else {
            if(userActionService.updateByScore(userId,scenicId,1)){
                map.put("message", "OK");
            }
            else {
                map.put("message", "ERROR");
            }
        }

        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @PostMapping("/wx_getTypeScenic")
    public Object getThemeByClassId(@RequestParam("typeName")String typeName) {
        Map<String,Object> map= new HashMap<>(4);
        String[] arr=typeName.split(",");
        List<Scenics> themeList=new ArrayList<Scenics>();
        if(arr.length==0){
            themeList=scenicsService.getAllUsersType(0,typeName,0);
        }
        else {
            for(String typeName2:arr){
                List<Scenics> scenics=scenicsService.getAllUsersType(0,typeName2,0);
                themeList.addAll(scenics);
            }
        }
        map.put("themeList",themeList);//猜你喜欢

        return map;
    }
}
