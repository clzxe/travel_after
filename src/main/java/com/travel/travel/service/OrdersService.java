package com.travel.travel.service;

import com.travel.travel.entity.OrderResultMap;
import com.travel.travel.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersService {
    boolean deleteByPrimaryKey(String orderId);

    boolean deleteByPrimaryKeyUser(String orderId);

    boolean insert(Orders record);

    boolean insertSelective(Orders record);

    Orders selectByPrimaryKey(String orderId);

    boolean updateByPrimaryKeySelective(Orders record);

    boolean updateByPrimaryKey(Orders record);

    List<Orders> getAllOrders(@Param("orderName")String orderName, @Param("searchType")Integer searchType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllOrdersCount(@Param("orderName")String orderName, @Param("searchType")Integer searchType);

    boolean updateOrdersStatus(@Param("orderId")String orderId,@Param("orderStatus")Integer orderStatus);

    List<Orders> getAllOrdersUp(@Param("orderName")String orderName,@Param("userId")Integer userId, @Param("searchType")Integer searchType,@Param("orderStatus")Integer orderStatus,@Param("start")String start,@Param("end")String end, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllOrdersCountUp(@Param("orderName")String orderName,@Param("userId")Integer userId, @Param("searchType")Integer searchType,@Param("orderStatus")Integer orderStatus,@Param("start")String start,@Param("end")String end);

    List<OrderResultMap> getGroupCount();

    List<OrderResultMap> getCityCount();

    List<OrderResultMap> getScenicCount();

    List<OrderResultMap> getLineCount();

    List<OrderResultMap> getOrderDayWeekCount();

    Integer getOrderDayONeCount();

    Integer getOrderDayToDayCount();

    List<OrderResultMap> getOrderSixMonth();

    List<OrderResultMap> getOrderOneYear(@Param("year")String year);

    List<OrderResultMap> getOrderCountOneYearMonth(@Param("year")String year);

    List<OrderResultMap> getOrderCountOneSeason(@Param("year")String year);

    List<OrderResultMap> getOrderOneSeason(@Param("year")String year);

    boolean autoOrderDelete();

    boolean autoOrderAccept();
}
