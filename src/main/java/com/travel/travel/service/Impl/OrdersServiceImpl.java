package com.travel.travel.service.Impl;

import com.travel.travel.entity.OrderResultMap;
import com.travel.travel.entity.Orders;
import com.travel.travel.repository.OrdersMapper;
import com.travel.travel.service.OrdersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;
    @Override
    public boolean deleteByPrimaryKey(String orderId) {
       boolean flag=false;
       if(ordersMapper.deleteByPrimaryKey(orderId)>0){
           flag=true;
       }
       return flag;
    }

    @Override
    public boolean deleteByPrimaryKeyUser(String orderId) {
        boolean flag=false;
        if(ordersMapper.deleteByPrimaryKeyUser(orderId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insert(Orders record) {
        boolean flag=false;
        if(ordersMapper.insert(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean insertSelective(Orders record) {
        return false;
    }

    @Override
    public Orders selectByPrimaryKey(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(Orders record) {
        return false;
    }

    @Override
    public boolean updateByPrimaryKey(Orders record) {
        boolean flag=false;
        if(ordersMapper.updateByPrimaryKey(record)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Orders> getAllOrders(String orderName, Integer searchType, Integer current, Integer size) {
        return ordersMapper.getAllOrders(orderName,searchType,current,size);
    }

    @Override
    public Integer getAllOrdersCount(String orderName, Integer searchType) {
        return ordersMapper.getAllOrdersCount(orderName,searchType);
    }

    @Override
    public boolean updateOrdersStatus(String orderId, Integer orderStatus) {
        boolean flag=false;
        if(ordersMapper.updateOrdersStatus(orderId, orderStatus)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Orders> getAllOrdersUp(String orderName, Integer userId, Integer searchType, @Param("orderStatus")Integer orderStatus, String start, String end, Integer current, Integer size) {
        return ordersMapper.getAllOrdersUp(orderName, userId, searchType,orderStatus, start, end, current, size);
    }

    @Override
    public Integer getAllOrdersCountUp(String orderName, Integer userId, Integer searchType, @Param("orderStatus")Integer orderStatus,String start, String end) {
        return ordersMapper.getAllOrdersCountUp(orderName, userId, searchType,orderStatus, start, end);
    }

    @Override
    public List<OrderResultMap> getGroupCount() {
        return ordersMapper.getGroupCount();
    }

    @Override
    public List<OrderResultMap> getCityCount() {
        return ordersMapper.getCityCount();
    }

    @Override
    public List<OrderResultMap> getScenicCount() {
        return ordersMapper.getScenicCount();
    }

    @Override
    public List<OrderResultMap> getLineCount() {
        return ordersMapper.getLineCount();
    }

    @Override
    public List<OrderResultMap> getOrderDayWeekCount() {
        return ordersMapper.getOrderDayWeekCount();
    }

    @Override
    public Integer getOrderDayONeCount() {
        return ordersMapper.getOrderDayONeCount();
    }

    @Override
    public Integer getOrderDayToDayCount() {
        return ordersMapper.getOrderDayToDayCount();
    }

    @Override
    public List<OrderResultMap> getOrderSixMonth() {
        return ordersMapper.getOrderSixMonth();
    }

    @Override
    public List<OrderResultMap> getOrderOneYear(String year) {
        return ordersMapper.getOrderOneYear(year);
    }

    @Override
    public List<OrderResultMap> getOrderCountOneYearMonth(String year) {
        return ordersMapper.getOrderCountOneYearMonth(year);
    }

    @Override
    public List<OrderResultMap> getOrderCountOneSeason(String year) {
        return ordersMapper.getOrderCountOneSeason(year);
    }

    @Override
    public List<OrderResultMap> getOrderOneSeason(String year) {
        return ordersMapper.getOrderOneSeason(year);
    }

    @Override
    public boolean autoOrderDelete() {
        boolean flag=false;
        if(ordersMapper.autoOrderDelete()>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean autoOrderAccept() {
        boolean flag=false;
        if(ordersMapper.autoOrderAccept()>0){
            flag=true;
        }
        return flag;
    }
}
