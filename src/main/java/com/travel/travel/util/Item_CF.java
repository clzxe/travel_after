package com.travel.travel.util;

import com.travel.travel.entity.Scenics;
import com.travel.travel.entity.UserAction;
import com.travel.travel.service.LinesService;
import com.travel.travel.service.ScenicsService;
import com.travel.travel.service.UserActionService;
import org.omg.PortableInterceptor.INACTIVE;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Item_CF {
    static Map<Integer, Integer> itemIDMap = new HashMap<Integer,Integer>();//产品ID列表  产品-id
    static Map<Integer,Integer> idToItemMap = new HashMap<Integer,Integer>();//产品ID转产品原名称 id-产品
    static Map<Integer, HashMap<Integer, Integer>> itemMap = new HashMap<>(); //针对每个产品，存储所有用户对该产品的评分

    static Map<Integer,Integer> userIDMap = new HashMap<Integer,Integer>();//用户ID列表
    static Map<Integer,Integer> idToUserMap = new HashMap<Integer,Integer>();//用户ID转用户原名称
    static Map<Integer,HashMap<Integer,Integer>> userMap = new HashMap<Integer,HashMap<Integer, Integer>>(); //针对每个用户，记录用户对于产品的评分
    static double[][] simMatrix; //产品之间的相似矩阵
    static int TOP_K = 25;  //选择的相似item的数量
    static int TOP_N = 20;  //定义最长推荐列表
    @Resource
    private UserActionService userActionService=SpringUtils.getApplicationContext().getBean(UserActionService.class);
    @Resource
    private ScenicsService scenicsService=SpringUtils.getApplicationContext().getBean(ScenicsService.class);
    public List<Scenics> ItemRecommmd(Integer userId,int Re_NO) throws IOException {
        TOP_N=Re_NO;
        readUI();
        item_similarity();
        List<Integer> scenicId=new ArrayList<Integer>();
        scenicId=recommend(userId);
        return scenicsService.findByTopicidIn(scenicId);
    }

    //读取用户UI交互
    public   void readUI() throws IOException{

        List<UserAction> userActionList=userActionService.selectAll();


        int itemId = 0; //产品计数
        int userId = 0;//用户计数
        for (UserAction userAction:userActionList) {
            if(!itemIDMap.containsKey(userAction.getScenicId())) {
                HashMap<Integer, Integer> currentUserMap = new HashMap<>();//存入当前的用户评分
                currentUserMap.put(userAction.getUserId(), userAction.getScore());	//用户-评分
                itemMap.put(userAction.getScenicId(), currentUserMap); //在itemMap中存入产品-评分
                itemIDMap.put(userAction.getScenicId(), itemId);
                idToItemMap.put(itemId, userAction.getScenicId());
                itemId ++;
            }else {  //如果已经存在，进行Map更新
                HashMap<Integer, Integer> currentUserMap = itemMap.get(userAction.getScenicId()); //获取已有产品所包含的评分
                currentUserMap.put(userAction.getUserId(), userAction.getScore());//加入新的用户-评分
                itemMap.put(userAction.getScenicId(), currentUserMap);
            }

            //如果不包含当前的用户，存入map中
            if(!userMap.containsKey(userAction.getUserId())) {

                userIDMap.put(userAction.getUserId(), userId);
                idToUserMap.put(userId, userAction.getUserId());

                userId++;
                //新建Map用于存储当前用户的评分列表
                HashMap<Integer, Integer> curentUserMap = new HashMap<Integer, Integer>();
                //将当前用户评分加入当前评分列表中
                curentUserMap.put(userAction.getScenicId(), userAction.getScore());
                userMap.put(userAction.getUserId(), curentUserMap);
            }else { //如果已存在当前用户，将该用户先前的评分拿出来，再加入新的评分
                HashMap<Integer, Integer> curentUserMap = userMap.get(userAction.getUserId());
                curentUserMap.put(userAction.getScenicId(), userAction.getScore());
                userMap.put(userAction.getUserId(), curentUserMap);
            }
        }

    }


    //获取产品之间的相似性
    public static void item_similarity() {
        //初始化用户相似矩阵
        simMatrix = new double[itemMap.size()][itemMap.size()];
        int itemCount = 0;
        //循环每个产品计算相似性:Jaccard 相似性
        for(Map.Entry<Integer, HashMap<Integer, Integer>> itemEntry_1 : itemMap.entrySet()) {
            System.out.println("计算"+itemCount);
            //获取为当前产品评分的所有用户
            Set<Integer> ratedUserSet_1 = new HashSet<>();
            for(Map.Entry<Integer, Integer> userEntry : itemEntry_1.getValue().entrySet()) {
                //将已评分用户存入set集合中
                ratedUserSet_1.add(userEntry.getKey());
            }

            int ratedUserSize_1 = ratedUserSet_1.size();//第一个产品所有评论数

            //循环其他产品
            for(Map.Entry<Integer, HashMap<Integer, Integer>> itemEntry_2 : itemMap.entrySet()) {
                //首先判断第二个产品的id是否大于第一个，是的话再进行计算，避免重复计算
                if(itemIDMap.get(itemEntry_2.getKey())>itemIDMap.get(itemEntry_1.getKey())) {
                    //同样获取为当前产品评分的所有用户
                    Set<Integer> ratedUserSet_2 = new HashSet<>();
                    for(Map.Entry<Integer, Integer> userEntry : itemEntry_2.getValue().entrySet()) {
                        ratedUserSet_2.add(userEntry.getKey());
                    }
                    //通过jaccard相似度计算产品相似度

                    int ratedUserSize_2 = ratedUserSet_2.size();//第二个产品所有评论数
                    int sameUerSize = interCount(ratedUserSet_1,ratedUserSet_2); //取两个集合的交集的数量

                    double similarity = sameUerSize/(Math.sqrt(ratedUserSize_1*ratedUserSize_2));
                    //把相似性存入相似矩阵中
                    simMatrix[itemIDMap.get(itemEntry_1.getKey())][itemIDMap.get(itemEntry_2.getKey())] = similarity;
                    simMatrix[itemIDMap.get(itemEntry_2.getKey())][itemIDMap.get(itemEntry_1.getKey())] = similarity;
                }
            }
            itemCount++;

//			for (int i = 0; i < simMatrix.length; i++) {
//				for (int j = 0; j < simMatrix.length; j++) {
//					System.out.print(simMatrix[i][j]+" ");
//				}
//				System.out.println();
//			}
        }
    }

    //根据产品的相似性进行推荐
    public   List<Integer> recommend(Integer userId) throws IOException{
        String resultFile = "data//topicAttack_101_ItemCF_result.txt";

        //根据item相似度获取每个item最相似的TOP_K个产品
        Map<Integer, HashSet<Integer>> nearestItemMap = new HashMap<>();

        for(int i = 0;i<itemMap.size();i++) {
            Map<Integer, Double> simMap = new HashMap<>();
            for(int j = 0;j<itemMap.size();j++) {
                simMap.put(j,simMatrix[i][j]);
            }

            //对产品相似性进行排序
            simMap = sortMapByValues(simMap);

            int simItemCount = 0;
            HashSet<Integer> nearestItemSet = new HashSet<>();
            for(Map.Entry<Integer, Double> entry : simMap.entrySet()) {
                if(simItemCount<TOP_K) {
                    nearestItemSet.add(entry.getKey()); //获取相似itemID存入集合中
                    simItemCount++;
                }else
                    break;
            }
            //相似物品结果存入map中
            nearestItemMap.put(i,nearestItemSet);
        }
        System.out.println("为用户"+userId+"推荐");
        //获取当前用户所有评论过的产品
        HashSet<Integer> currentUserSet = new HashSet<>();
        Map<Integer,Double> preRatingMap = new HashMap<Integer,Double>();
        List<UserAction> userActionList=userActionService.selectByUserId(userId);
       /* for(UserAction userAction:userActionList) {
            currentUserSet.add(userAction.getScenicId()); //将该用户评论过的产品以产品id的形式存入集合中
        }*/
        for(Map.Entry<Integer,Integer> entry4: userMap.get(userId).entrySet()) {
            currentUserSet.add(itemIDMap.get(entry4.getKey())); //将该用户评论过的产品以产品id的形式存入集合中
        }

        //循环每个产品
        for(int j = 0;j<itemMap.size();j++) {
            double preRating = 0;
            double sumSim = 0;

            //首先判断用户购买的列表中是否包含当前商品，如果包含直接跳过
            if(currentUserSet.contains(j))
                continue;

            //判断当前产品的近邻中是否包含这个产品
            Set<Integer> interSet = interSet(currentUserSet, nearestItemMap.get(j));//获取当前用户的购买列表与产品相似品的交集

            //如果交集为空，则该产品预测评分为0
            if(!interSet.isEmpty()) {
                try{
                    for(int item :interSet) {
                        sumSim += simMatrix[j][item];
                        preRating += simMatrix[j][item]* userMap.get(userId).get(idToItemMap.get(item));

                    }
                }catch (Exception e){
                    System.out.println("worong");
                }


                if(sumSim != 0) {
                    preRating = preRating/sumSim; //如果相似性之和不为0计算得分，否则得分为0
                }else
                    preRating = 0;
            }else  //如果交集为空的话，直接评分为0
                preRating = 0;
            preRatingMap.put(idToItemMap.get(j), preRating);
        }
        preRatingMap = sortMapByValues(preRatingMap);
        //推荐TOP_N个产品
        List<Integer> scenicId = new ArrayList<Integer>();
        int recCount = 0;
        for(Map.Entry<Integer, Double> entry : preRatingMap.entrySet()) {
            if(recCount < TOP_N) {
                System.out.println("景点"+entry.getKey() + " ");
                scenicId.add(entry.getKey());
                recCount ++;

            }
        }

        return scenicId;
    }

    //求两个集合交集
    public static int interCount(Set<Integer> set_a, Set<Integer> set_b) {
        int samObj = 0;
        for(Object obj:set_a) {
            if(set_b.contains(obj))
                samObj++;
        }
        return samObj;
    }
    //求两个集合交集的数量
    public static Set<Integer> interSet(Set<Integer> set_a, Set<Integer> set_b) {
        Set<Integer> tempSet = new HashSet<>();
        for(Object obj:set_a) {
            if(set_b.contains(obj))
                tempSet.add((Integer) obj);
        }
        return tempSet;
    }

    //对map进行从大到小排序
    public static <K extends Comparable, V extends Comparable> Map<K, V> sortMapByValues(Map<K, V> aMap) {
        HashMap<K, V> finalOut = new LinkedHashMap<>();
        aMap.entrySet().stream().sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).collect(Collectors.toList())
                .forEach(ele -> finalOut.put(ele.getKey(), ele.getValue()));
        return finalOut;
    }
}
