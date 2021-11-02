package com.travel.travel.controller;

import com.travel.travel.entity.Classes;
import com.travel.travel.service.ClassesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("travel/class")
public class ClassesController {
    @Resource
    private ClassesService classesService;
    /**
     * 添加
     */
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveclasss(Classes classs, HttpSession session){

        Map<String,String> map = new HashMap<>();

        if(!classesService.insert(classs)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateclasss(Classes classs, HttpSession session){

        Map<String,String> map = new HashMap<>();

        if(!classesService.updateByPrimaryKey(classs)){
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
    public Object getclasslList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Classes> classsList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>();
        Integer pageSize=size;
        classsList=classesService.getAllClasses(searchTitle,10*(pageIndex-1),size);
        total=classesService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("classList",classsList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delclasss(@RequestParam("classId")String classId){
        if(classId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(classesService.deleteByPrimaryKey(Integer.parseInt(classId))){
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
    @GetMapping("/getclass")
    public Object getclassByclassId(@RequestParam("classId")String classId) {
        Map<String,Object> map= new HashMap<>();
        if(classId==null||classId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Classes classes=classesService.selectByPrimaryKey(Integer.parseInt(classId));
            map.put("message","OK");
            map.put("class",classes);
        }
        return map;
    }
    
}
