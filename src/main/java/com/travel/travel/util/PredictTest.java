package com.travel.travel.util;

import com.travel.travel.entity.Lines;
import com.travel.travel.entity.User_action;
import com.travel.travel.entity.Users;
import com.travel.travel.service.*;
import org.apache.mahout.cf.taste.common.TasteException;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

import org.apache.mahout.cf.taste.impl.model.file.*;

import org.apache.mahout.cf.taste.impl.neighborhood.*;

import org.apache.mahout.cf.taste.impl.recommender.*;

import org.apache.mahout.cf.taste.impl.similarity.*;

import org.apache.mahout.cf.taste.model.*;

import org.apache.mahout.cf.taste.recommender.*;

import org.apache.mahout.cf.taste.similarity.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredictTest {

    @Resource
    private UsersService usersService=SpringUtils.getApplicationContext().getBean(UsersService.class);

    @Resource
    private Shopping_cartService shoppingCartService=SpringUtils.getApplicationContext().getBean(Shopping_cartService.class);

    @Resource
    private LinesService linesService=SpringUtils.getApplicationContext().getBean(LinesService.class);

    /**
     *
     * @param uid
     * @return lineList
     * 猜你喜欢，用户协同过滤算法
     */
    public List<Lines> RecommendTopic(Long uid,int CountSize){
        List<Long> userList;
        userList=usersService.getAllUsersO();
        int N=userList.size();
//        System.out.println("用户数量："+N);
        Long[][] sparseMatrix=new Long[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                sparseMatrix[i][j]=(long)0;
        }
        Map<Long, Integer> userItemLength = new HashMap<>();//存储每一个用户对应的不同物品总数  eg: A 3
        Map<Long, Set<Long>> itemUserCollection = new HashMap<>();//建立物品到用户的倒排表 eg: a A B
        Set<Long> items = new HashSet<>();//辅助存储物品集合
        Map<Long, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射:user->id
        Map<Integer, Long> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射:id->user

        for(int i=0;i<N;i++){//依次处理N个用户
            Long user=userList.get((int)i);
            List<Long> itemlist=shoppingCartService.getAllCarsO(user.intValue());
//            System.out.println("UserID:"+user+" 收藏："+itemlist);
            userItemLength.put(user,itemlist.size());
            //用户ID与稀疏矩阵建立对应关系
            userID.put(user,i);
            idUser.put(i,user);

            //建立物品--用户倒排表
            for(int j=0;j<itemlist.size();j++){
                Long topic=itemlist.get(j);
                if(items.contains(topic)){//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(topic).add(user);
                }else{//否则创建对应物品--用户集合映射
                    items.add(topic);
                    //创建物品--用户倒排关系
                    itemUserCollection.put(topic,new HashSet<Long>());
                    itemUserCollection.get(topic).add(user);
                }
            }
        }
        //输出倒排关系表
//        System.out.println("输出倒排关系表：\n"+itemUserCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Map.Entry<Long, Set<Long>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<Long, Set<Long>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Set<Long> commonUsers=iterator.next().getValue();
            for(Long user_u:commonUsers){
                for(Long user_v:commonUsers){
                    if(user_u==user_v){
                        continue;
                    }
                    //计算用户u与用户v都有正反馈的物品总数
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)]+=1;
                }
            }
        }
        //计算用户之间的相似度【余弦相似性】
        Integer recommendUserId = userID.get(uid);
        for(int j=0;j<sparseMatrix.length;j++){
            if(j!=recommendUserId){
                System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"
                        +sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
            }
        }

        //计算指定用户recommendUser的物品推荐度
        Map<Long,Double> itemRecommendDegree=new HashMap<>();//topic->推荐度
        for(Long item:items){//遍历每一件物品
            //得到购买当前物品的所有用户集合
            Set<Long> users=itemUserCollection.get(item);
            //如果被推荐用户没有购买当前物品，则进行推荐度计算
            if(!users.contains(uid)){
                double RecommendDegree = 0.0;
                for(Long user:users){
                    //推荐度计算
                    RecommendDegree+=sparseMatrix[userID.get(uid)][userID.get(user)]/Math.sqrt(userItemLength.get(uid)*userItemLength.get(user));
                }
                itemRecommendDegree.put(item,RecommendDegree);
            }
        }
        System.out.println("TopicID,推荐度\n"+itemRecommendDegree);
        //取最大的5个
        if(itemRecommendDegree.size()<CountSize){
            List<Long> list = itemRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, itemRecommendDegree.size());
            List<Long> list1=linesService.getLineHotLimitSix();
            for(int i=0;i<list1.size();i++){
                if(list.size()==CountSize)
                    break;
                if(list.contains(list1.get(i)))
                    continue;
                list.add(list1.get(i));
            }
            System.out.println(list);
            return linesService.findByTopicidIn(list);
        }else {
            List<Long> list = itemRecommendDegree.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .map(entry -> entry.getKey()).collect(Collectors.toList())
                    .subList(0, CountSize);
            System.out.println(list);
            return linesService.findByTopicidIn(list);
        }

    }


}
