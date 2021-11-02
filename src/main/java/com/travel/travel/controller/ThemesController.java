package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.ClassesService;
import com.travel.travel.service.Line_groupService;
import com.travel.travel.service.LinesService;
import com.travel.travel.service.ThemesService;
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
@RequestMapping("travel/theme")
public class ThemesController {
    @Resource
    private ThemesService themesService;
    @Resource
    private ClassesService classesService;
    @Resource
    private LinesService linesService;

    @Resource
    private Line_groupService lineGroupService;

    /**
     * 添加
     */
    @Value("${absoluteImgPath}")
    String absoluteImgPath;
    @Value("${sonImgPath}")
    String sonImgPath;
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveThemes(Themes themes, HttpSession session, MultipartFile themePhoto){

        Map<String,String> map = new HashMap<>(2);

        if(!themesService.insert(themes)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateThemes(Themes themes, HttpSession session, MultipartFile themePhoto){

        Map<String,String> map = new HashMap<>(2);

        if(!themesService.updateByPrimaryKey(themes)){
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
    @PostMapping(value = "/class_list")
    public Object getClassList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){

        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>(4);
        Integer pageSize=size;
        List<Classes> classList=classesService.getAllClasses(searchTitle,10*(pageIndex-1),size);
        total=classesService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("classList",classList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getThemelList(@RequestParam("searchTitle") String searchTitle,
                               @RequestParam("pageIndex")Integer pageIndex,
                               @RequestParam("size")Integer size ){
        List<Themes> themesList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;
        Map<String,Object> map =new HashMap<>(4);
        Integer pageSize=size;
        themesList=themesService.getAllThemes(searchTitle,10*(pageIndex-1),size);
        total=themesService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("themeList",themesList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delThemes(@RequestParam("themeId")String themeId){
        if(themeId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(themesService.deleteByPrimaryKey(Integer.parseInt(themeId))){
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
    @GetMapping("/gettheme")
    public Object getThemeByThemeId(@RequestParam("themeId")String themeId) {
        Map<String,Object> map= new HashMap<>(4);
        if(themeId==null||themeId.equals("")){
            map.put("message","ERROR");
        }
        else{
            Themes theme=themesService.selectByPrimaryKey(Integer.parseInt(themeId));
            List<Classes> classList=classesService.getAll();
            map.put("classList",classList);
            map.put("message","OK");
            map.put("theme",theme);
        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @PostMapping("/getThemClass")
    public Object getThemeByClassId(@RequestParam("classId")String classId) {
        Map<String,Object> map= new HashMap<>(4);
        if(classId==null||classId.equals("")){
            map.put("message","ERROR");
        }
        else{
            List<Themes> themesList=themesService.getThemesByCassId(Integer.parseInt(classId));

            map.put("message","OK");
            map.put("themeList",themesList);

        }
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @GetMapping("/nav_theme")
    public Object getThemePage() {
        Map<String,Object> map= new HashMap<>(11);
        List<Themes> themesList1=themesService.getThemesByCassId(1);
        List<Themes> themesList2=themesService.getThemesByCassId(2);
        List<Themes> themesList3=themesService.getThemesByCassId(3);
        List<Themes> themesList4=themesService.getThemesByCassId(4);
        List<Themes> themesList5=themesService.getThemesByCassId(5);


        map.put("themesList1",themesList1);
        map.put("themesList2",themesList2);
        map.put("themesList3",themesList3);
        map.put("themesList4",themesList4);
        map.put("themesList5",themesList5);
        List<Line_group> linesList1=lineGroupService.getAllLinesTheme(themesList1.get(0).getThemeId());
        List<Line_group> linesList2=lineGroupService.getAllLinesTheme(themesList2.get(0).getThemeId());
        List<Line_group> linesList3=lineGroupService.getAllLinesTheme(themesList3.get(0).getThemeId());
        List<Line_group> linesList4=lineGroupService.getAllLinesTheme(themesList4.get(0).getThemeId());
        List<Line_group> linesList5=lineGroupService.getAllLinesTheme(themesList5.get(0).getThemeId());
        map.put("linesList1",linesList1);
        map.put("linesList2",linesList2);
        map.put("linesList3",linesList3);
        map.put("linesList4",linesList4);
        map.put("linesList5",linesList5);
        return map;
    }

    /**
     * 根据ID获取Strategy对象
     */
    @CrossOrigin
    @PostMapping("/line_list")
    public Object getThemeLineList(@RequestParam("themeId")Integer themeId) {
        Map<String,Object> map= new HashMap<>(11);

        List<Line_group> linesList1=lineGroupService.getAllLinesTheme(themeId);
        map.put("linesList",linesList1);
        return map;
    }
}
