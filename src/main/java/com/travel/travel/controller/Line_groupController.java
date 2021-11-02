package com.travel.travel.controller;

import com.travel.travel.entity.*;
import com.travel.travel.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/travel/line_group")
public class Line_groupController {
    @Resource
    private LinesService linesService;
    @Resource
    private GroupsService groupsService;
    @Resource
    private Line_groupService line_groupService;
    @Resource
    private LeadersService leadersService;
    @Resource
    private ProvinceService provinceService;
    @Resource
    private HotelsService hotelsService;
    @Resource
    private Hotel_roomsService hotel_roomsService;
    @Resource
    private EvaluatesService evaluatesService;
    @Resource
    private E_replyService eReplyService;
    @Resource
    private Travel_timeService travel_timeService;
    @Resource
    private H_fileService hFileService;
    @Resource
    private ScenicsService scenicsService;
    @CrossOrigin
    @GetMapping(value = "/init")
    public Object getLineGroupInti(){
        Map<String,Object> map =new HashMap<>(5);
        List<Lines> lineList=null;
        List<Groups> groupList=null;
        lineList=linesService.getAll();
        groupList=groupsService.getAll();
        List<Province> provinceList=provinceService.getAllProvince();
        map.put("provinceList",provinceList);
        map.put("lineList",lineList);
        map.put("groupList",groupList);

        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/update_init")
    public Object UpdateLineGroupInti(Integer line_groupId){
        Map<String,Object> map =new HashMap<>(5);
        List<Lines> lineList=null;
        List<Groups> groupList=null;
        lineList=linesService.getAll();
        groupList=groupsService.getAll();
        Line_group line_group=line_groupService.selectByPrimaryKey(line_groupId);
        Groups groups=groupsService.selectByPrimaryKey(line_group.getGroupId());
        Lines lines=linesService.selectByPrimaryKey(line_group.getLineId());
        Leaders leaders=leadersService.selectByPrimaryKey(line_group.getLeaderId());
        List<Province> provinceList=provinceService.getAllProvince();
        map.put("provinceList",provinceList);
        map.put("lineList",lineList);
        map.put("groupList",groupList);
        map.put("line_group",line_group);
        map.put("groups",groups);
        map.put("lines",lines);
        map.put("leaders",leaders);
        return map;
    }

    @CrossOrigin
    @GetMapping(value = "/getgroup")
    public Object getLeaderGroupInti(@RequestParam("groupId")String groupId){
        Map<String,Object> map =new HashMap<>(5);
        List<Leaders> leaderList=null;
       Groups group=null;
        if(groupId==null){
            map.put("message","error");
            map.put("ERROR","系统异常，请重新选择");
        }
        else {
            map.put("message","OK");
            leaderList=leadersService.getLeaderByGroupId(Integer.parseInt(groupId));
            group=groupsService.selectByPrimaryKey(Integer.parseInt(groupId));
            map.put("leaderList",leaderList);
            map.put("group",group);
        }

        return map;
    }


    @CrossOrigin
    @GetMapping(value = "/getline_group")
    public Object getLineGroupView(@RequestParam("line_groupId")String line_groupId){
        Map<String,Object> map =new HashMap<>(5);

        if(line_groupId==null){
            map.put("message","error");
            map.put("ERROR","系统异常，请重新选择");
        }
        else {
            map.put("message","OK");
            Line_group line_group=line_groupService.selectByPrimaryKey(Integer.parseInt(line_groupId));
            map.put("line_group",line_group);
        }

        return map;
    }
    @CrossOrigin
    @PostMapping(value = "/Wxgetline_group")
    public Object WXgetLineGroupView(@RequestParam("line_groupId")String line_groupId){
        Map<String,Object> map =new HashMap<>(5);

        if(line_groupId==null){
            map.put("message","error");
            map.put("ERROR","系统异常，请重新选择");
        }
        else {
            map.put("message","OK");
            Line_group line_group=line_groupService.selectByPrimaryKey(Integer.parseInt(line_groupId));
            map.put("line_group",line_group);
        }

        return map;
    }

    /**
     * 添加
     */
    @CrossOrigin
    @PostMapping(value = "/save")
    public Object saveGroups(Line_group groups, HttpSession session){

        Map<String,String> map = new HashMap<>();
        groups.setCreateTime(new Date());
        if(!line_groupService.insert(groups)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "OK");
        }
        return map;
    }
    /**
     * 修改
     */
    @CrossOrigin
    @PostMapping(value = "/update")
    public Object updateGroups(Line_group groups, HttpSession session){

        Map<String,String> map = new HashMap<>();
        groups.setCreateTime(new Date());
        if(!line_groupService.updateByPrimaryKey(groups)){
            map.put("message", "系统响应错误");
        }
        else {
            map.put("message", "添加成功");
        }
        return map;
    }
    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    public Object getGroupsList(@RequestParam("searchTitle") String searchTitle,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size ){
        List<Line_group> line_groupList=null;
        Integer total=0;
        int pageCount=0;
        Map<String,Object> map =new HashMap<>(5);
        Integer pageSize=pageIndex;
        line_groupList=line_groupService.getAllLines(searchTitle,10*(pageIndex-1),size);
        total=line_groupService.getAllCount(searchTitle);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("line_groupList",line_groupList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        return map;
    }

    /**
     * 删除攻略（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/del")
    public String delGroups(@RequestParam("line_groupId")String line_groupId){
        if(line_groupId==null){
            return "系统异常，请重新选择";
        }
        else {
            if(line_groupService.deleteByPrimaryKey(Integer.parseInt(line_groupId))){
                return "OK";
            }
            else {
                return "系统响应错误";
            }
        }
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/line_list")
    public Object getHotelListUp(@RequestParam("searchTitle") String searchTitle,
                                 @RequestParam("cityId") Integer cityId,
                                 @RequestParam("themeId") Integer themeId,
                                 @RequestParam("sortType") Integer sortType,
                                 @RequestParam("pageIndex")Integer pageIndex,
                                 @RequestParam("size")Integer size ){
        List<Line_group> lineGroupList;
        Integer total=0;
        int pageCount=0;
        int scenicId=0;

        Map<String,Object> map =new HashMap<>(7);
        Integer pageSize=size;
        lineGroupList=line_groupService.getLineInit(searchTitle,cityId,themeId,sortType,size*(pageIndex-1),size);
        if(sortType==1){
            total=16;
        }
        else {
            total=line_groupService.getLineInitCount(searchTitle,cityId,themeId,sortType);
        }
        if(lineGroupList==null||lineGroupList.size()==0){
            lineGroupList=line_groupService.getLineInit(searchTitle,cityId,themeId,0,size*(pageIndex-1),size);
            total=line_groupService.getLineInitCount(searchTitle,cityId,themeId,0);
        }

        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }

        map.put("lineGroupList",lineGroupList);
        map.put("total",total);
        map.put("pageCount",pageCount);
        map.put("searchTitle",searchTitle);
        map.put("cityId",cityId);
        map.put("themeId",themeId);
        map.put("sortType",sortType);
        return map;
    }

    /**
     * 查看路线（根据Id）
     */
    @CrossOrigin
    @PostMapping(value = "/line_view")
    public Object getLineGroups(@RequestParam("lineId")String line_groupId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(line_groupId==null){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Lines line=linesService.selectByPrimaryKey(Integer.parseInt(line_groupId));
            List<Line_group> lineGroupList=line_groupService.getLineOneByLineId(line.getLineId());

            Line_group lineGroup = lineGroupList.get(0);

            List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(lineGroup.getLineId(),1,0,size*(pageIndex-1),size);
            total=evaluatesService.getAllEvaluatesByLineIdCount(lineGroup.getLineId(),1,0);
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            if(evaluateList==null){
                evaluateList=new ArrayList<>();
            }
            else {
                for(int i=0;i<evaluateList.size();i++){
                    Evaluates evaluates=evaluateList.get(i);
                    List<E_reply> eReplyList=new ArrayList<E_reply>();
                    eReplyList=eReplyService.getReplyByCommentId(evaluates.getEvaluateId());
                    evaluateList.get(i).setReplyList(eReplyList);
                }
            }

            if(lineGroup.getIsIncludeHotel()==1){
                lineGroup.setHotel(hotelsService.selectByPrimaryKey(lineGroup.getHotelId()));
                lineGroup.setHotel_room(hotel_roomsService.selectByPrimaryKey(lineGroup.getRoomId()));
            }
            else {
                lineGroup.setHotel(new Hotels());
                lineGroup.setHotel_room(new Hotel_rooms());
            }

            List<Map<String,Object>> travelDays=new ArrayList<>();
            Map<String,Object> map1=new HashMap<>(3);
            if(lineGroupList.size()!=0){
                for (Line_group line_group:lineGroupList) {
                    if(line_group.getId()!=null){
                        map1=new HashMap<>(3);
                        map1.put("id",line_group.getId());
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        map1.put("calendar",sdf.format(line_group.getLineStartTime()));
                        map1.put("price",line_group.getLinePrice());
                        travelDays.add(map1);
                    }

                }
            }

            List<Travel_time> travelTimes=travel_timeService.selectByLineId(lineGroup.getLineId());
            List<H_file> ImageList=hFileService.getFilesBylineId(lineGroup.getLineId());

            Users users= (Users) session.getAttribute("userSession");
            if(users==null){
                users=new Users();
            }

            String[] arr=line.getLineScenic().split(",");
            List<Scenics> scenicList=new ArrayList<Scenics>();
            if(arr.length!=0){
                for(String scecnicId:arr){
                    scenicList.add(scenicsService.selectByPrimaryKey(Integer.parseInt(scecnicId)));
                }
            }
            else {
                scenicList.add(new Scenics());
            }
            map.put("lineGroup", lineGroup);
            map.put("ImageList",ImageList);
            map.put("pageCount", pageCount);
            map.put("total", total);
            map.put("lineGroupList", lineGroupList);
            map.put("travelTimes", travelTimes);
            map.put("evaluateList", evaluateList);
            map.put("scenicList", scenicList);
            map.put("users", users);
            map.put("travelDays", travelDays);
        }
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/line_sim")
    public Object getLineSim(@RequestParam("lineName") String lineName){

        List<Line_group> lineGroupList;

        Map<String,Object> map =new HashMap<>(7);
        lineGroupList=line_groupService.getLineHotLimitSix();

        map.put("lineGroupList",lineGroupList);

        return map;
    }

    /**
     * 查看路线（根据Id）
     */

    @CrossOrigin
    @PostMapping(value = "/get_line_view")
    public Object getLineGroupsView(@RequestParam("lineId")String line_groupId,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size,
                                HttpSession session){
        Map<String,Object> map =new HashMap<>(6);
        Integer total=0;
        int pageCount=0;
        Integer pageSize=size;
        if(line_groupId==null||line_groupId.equals("")){
            map.put("message", "系统异常，请重新选择");
        }
        else {
            Line_group lineGroup = line_groupService.getLineOne(Integer.parseInt(line_groupId));

            List<Evaluates> evaluateList=evaluatesService.getAllEvaluatesByLineId(lineGroup.getId(),1,0,size*(pageIndex-1),size);
            total=evaluatesService.getAllEvaluatesByLineIdCount(lineGroup.getId(),1,0);
            if(total%pageSize==0){
                pageCount=total/pageSize;
            }
            else {
                pageCount=total/pageSize+1;
            }
            if(evaluateList==null){
                evaluateList=new ArrayList<>();
            }
            else {
                for(int i=0;i<evaluateList.size();i++){
                    Evaluates evaluates=evaluateList.get(i);
                    List<E_reply> eReplyList=new ArrayList<E_reply>();
                    eReplyList=eReplyService.getReplyByCommentId(evaluates.getEvaluateId());
                    evaluateList.get(i).setReplyList(eReplyList);
                }
            }

            if(lineGroup.getIsIncludeHotel()==1){
                lineGroup.setHotel(hotelsService.selectByPrimaryKey(lineGroup.getHotelId()));
                lineGroup.setHotel_room(hotel_roomsService.selectByPrimaryKey(lineGroup.getRoomId()));
            }
            else {
                lineGroup.setHotel(new Hotels());
                lineGroup.setHotel_room(new Hotel_rooms());
            }

            map.put("lineGroup", lineGroup);
            map.put("pageCount", pageCount);
            map.put("total", total);
            map.put("evaluateList", evaluateList);
        }
        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/line_list_hot")
    public Object getLineHot(){
        List<Line_group> lineGroupList;

        Map<String,Object> map =new HashMap<>(7);
        lineGroupList=line_groupService.getLineHotLimitSix();

        map.put("lineGroupList",lineGroupList);

        return map;
    }

    /**
     * 获取攻略列表（多条件筛选）
     */
    @CrossOrigin
    @PostMapping(value = "/leader_list")
    public Object getLineLeader(@RequestParam("leaderId")Integer leaderId,
                                @RequestParam("type")Integer type,
                                @RequestParam("pageIndex")Integer pageIndex,
                                @RequestParam("size")Integer size){
        List<Line_group> lineGroupList;
        Integer total=0;
        int pageCount=0;
        int pageSize=size;

        Map<String,Object> map =new HashMap<>(7);

        lineGroupList=line_groupService.selectByStartTime(leaderId,type,size*(pageIndex-1),size);
        total=line_groupService.selectByStartTimeCount(leaderId, type);
        if(total%pageSize==0){
            pageCount=total/pageSize;
        }
        else {
            pageCount=total/pageSize+1;
        }
        map.put("lineGroupList",lineGroupList);
        map.put("type",type);
        map.put("leaderId",leaderId);
        map.put("pageCount",pageCount);
        map.put("total",total);
        return map;
    }
}
