package com.travel.travel.controller;

import com.travel.travel.entity.City;
import com.travel.travel.entity.Province;
import com.travel.travel.service.CityService;
import com.travel.travel.service.ProvinceService;
import com.travel.travel.util.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/city")
public class CityController {
    @Resource
    private CityService cityService;
    @Resource
    private ProvinceService provinceService;
    /**
     * 添加
     */
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object savecitys(City city, HttpSession session){

        Map<String,String> map = new HashMap<>(3);
        if(!cityService.insert(city)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updatecitys(City city, HttpSession session){

        Map<String,String> map = new HashMap<>(3);
        if(!cityService.updateByPrimaryKey(city)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getcitylList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<City> cityList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>(4);
        Integer pageSize=size;
        cityList=cityService.getAllCity(searchTitle,10*(pageIndex-1),size);
        total=cityService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("cityList",cityList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("pageIndex",pageIndex);
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/province_list")
    public Object getProvinceList(@RequestParam("searchTitle") String searchTitle,
                               @RequestParam("pageIndex")Integer pageIndex,
                               @RequestParam("size")Integer size ){

        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>(4);
        Integer pageSize=size;
        List<Province> provinceList=provinceService.getAllProvinceByName(searchTitle,10*(pageIndex-1),size);
        total=provinceService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("provinceList",provinceList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("pageIndex",pageIndex);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delcitys(@RequestParam("cityId")String cityId){
        if(cityId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(cityService.deleteByPrimaryKey(Integer.parseInt(cityId))){
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
    @GetMapping("/getcity")
    public Object getcityBycityId(@RequestParam("cityId")String cityId) {
        Map<String,Object> map= new HashMap<>();
        if(cityId==null||cityId.equals("")){
            map.put("message","ERROR");
        }
        else{
            City city=cityService.selectByPrimaryKey(Integer.parseInt(cityId));
            List<Province> provinceList=provinceService.getAllProvince();
            map.put("provinceList",provinceList);
            map.put("message","OK");
            map.put("city",city);
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/get_city_province")
    public Object getCityByProvinceId(@RequestParam("provinceId")String provinceId) {
        Map<String,Object> map= new HashMap<>(4);
        if(provinceId==null||provinceId.equals("")){
            map.put("message","ERROR");
        }
        else{
            List<City> cityList=cityService.getCitiesByProvinceId(Integer.parseInt(provinceId));
            List<Province> provinceList=provinceService.getAllProvince();
            map.put("provinceList",provinceList);
            map.put("message","OK");
            map.put("cityList",cityList);
        }
        return map;
    }


    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/getProvince")
    public Object getProvince() {
        Map<String,Object> map= new HashMap<>();

            List<Province> provinceList=provinceService.getAllProvince();
            map.put("message","OK");
            map.put("provinceList",provinceList);

        return map;
    }
}
