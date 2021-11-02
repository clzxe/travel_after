package com.travel.travel.repository;

import com.travel.travel.entity.OrderResultMap;
import com.travel.travel.entity.Orders;
import com.travel.travel.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int deleteByPrimaryKeyUser(String orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int autoOrderDelete();

    int autoOrderAccept();

    List<Orders> getAllOrders(@Param("orderName")String orderName, @Param("searchType")Integer searchType, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllOrdersCount(@Param("orderName")String orderName, @Param("searchType")Integer searchType);

    Integer updateOrdersStatus(@Param("orderId")String orderId,@Param("orderStatus")Integer orderStatus);

    List<Orders> getAllOrdersUp(@Param("orderName")String orderName,@Param("userId")Integer userId, @Param("searchType")Integer searchType, @Param("orderStatus")Integer orderStatus,@Param("start")String start,@Param("end")String end, @Param("current")Integer current, @Param("size")Integer size);

    Integer getAllOrdersCountUp(@Param("orderName")String orderName,@Param("userId")Integer userId, @Param("searchType")Integer searchType,@Param("orderStatus")Integer orderStatus,@Param("start")String start,@Param("end")String end);

    List<OrderResultMap> getGroupCount();

    List<OrderResultMap> getCityCount();

    List<OrderResultMap> getScenicCount();

    List<OrderResultMap> getLineCount();

    List<OrderResultMap> getOrderDayWeekCount();

    List<OrderResultMap> getOrderSixMonth();

    List<OrderResultMap> getOrderOneYear(@Param("year")String year);

    List<OrderResultMap> getOrderCountOneYearMonth(@Param("year")String year);

    List<OrderResultMap> getOrderCountOneSeason(@Param("year")String year);

    List<OrderResultMap> getOrderOneSeason(@Param("year")String year);

    Integer getOrderDayONeCount();

    Integer getOrderDayToDayCount();
}