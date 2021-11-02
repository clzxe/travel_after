package com.travel.travel.task;

import com.travel.travel.service.Line_groupService;
import com.travel.travel.service.OrdersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务
    @Resource
    private OrdersService ordersService;
    @Resource
    private Line_groupService lineGroupService;
    @Scheduled(cron = "0/60 * * * * ?")
    private void configureTasks() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("执行静态定时任务时间: " +sdf.format(new Date()));
        ordersService.autoOrderDelete();
    }

    @Scheduled(cron = "0 0 12 * * ?")
    private void autoTasks() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("执行静态定时任务时间: " +sdf.format(new Date()));
        ordersService.autoOrderAccept();
    }
    @Scheduled(cron = "0 0 0 * * ?")
    private void autoTasksLineGroup() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("执行静态定时任务时间: " +sdf.format(new Date()));
        lineGroupService.deleteByAuto();
    }
}
