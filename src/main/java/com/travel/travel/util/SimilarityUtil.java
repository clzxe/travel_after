package com.travel.travel.util;

import com.hankcs.hanlp.HanLP;
import com.travel.travel.entity.Lines;
import com.travel.travel.entity.Scenics;
import com.travel.travel.service.LinesService;
import com.travel.travel.service.ScenicsService;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查找相似算法
 */

public class SimilarityUtil {

    @Resource
    private ScenicsService scenicsService=SpringUtils.getApplicationContext().getBean(ScenicsService.class);
    @Resource
    private LinesService linesService=SpringUtils.getApplicationContext().getBean(LinesService.class);


    public  List<Lines> getSimilarityLines(Integer lineId,Integer cityId,String lineName){
        System.out.println("1008"+lineId);
        List<Lines> linesList=linesService.getLineNotLineId(lineId,cityId);
        for (int i=0;i<linesList.size();i++){

            Lines lines=linesList.get(i);
            double seem=getSimilarity(lineName,lines.getLineTitle());
            System.out.println("-----------------------------:"+seem);
            linesList.get(i).setWeight(seem);
        }
        linesList=LinesListSort(linesList);
        List<Lines> linesList2=new ArrayList<>();
        for (int i=0;i<linesList.size();i++){
            if(i>4){
                break;
            }
            Lines lines=linesList.get(i);
            linesList2.add(lines);
        }
        return linesList2;
    }

    public  List<Lines> LinesListSort(List<Lines> linesList){
        Collections.sort(linesList, new Comparator<Lines>() {
            @Override
            public int compare(Lines o1, Lines o2) {
                if(o1.getWeight()>o2.getWeight()){
                    return -1;
                }
                if(o1.getWeight()==o2.getWeight()){
                    return 0;
                }
                else {
                    return 1;
                }

            }
        });
        return linesList;
    }
    public  List<Scenics> getSimilarityScenic(Integer scenicId,Integer cityId, String scenicName){
        List<Scenics> scenicList=scenicsService.getScenicNotScenicId(scenicId,cityId);
        for (int i=0;i<scenicList.size();i++){
            Scenics lines=scenicList.get(i);
            double seem=getSimilarity(scenicName,lines.getScenicName());
            scenicList.get(i).setSeem(seem);
        }
        scenicList=ScenicListSort(scenicList);
        List<Scenics> linesList2=new ArrayList<>();
        for (int i=0;i<scenicList.size();i++){
            if(i>4){
                break;
            }
            Scenics lines=scenicList.get(i);
            linesList2.add(lines);
        }
        return linesList2;
    }

    public  List<Scenics> ScenicListSort(List<Scenics> scenicList){
        Collections.sort(scenicList, new Comparator<Scenics>() {
            @Override
            public int compare(Scenics o1, Scenics o2) {
                if(o1.getSeem()>o2.getSeem()){
                    return -1;
                }
                if(o1.getSeem()==o2.getSeem()){
                    return 0;
                }
                else {
                    return 1;
                }

            }
        });
        return scenicList;
    }
    /**
     * 获得两个句子的相似度
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public  double getSimilarity(String sentence1, String sentence2) {
        List<String> sent1Words = getSplitWords(sentence1);
        System.out.println(sent1Words);
        List<String> sent2Words = getSplitWords(sentence2);
        System.out.println(sent2Words);
        List<String> allWords = mergeList(sent1Words, sent2Words);

        int[] statistic1 = statistic(allWords, sent1Words);
        int[] statistic2 = statistic(allWords, sent2Words);

        double dividend = 0;
        double divisor1 = 0;
        double divisor2 = 0;
        for (int i = 0; i < statistic1.length; i++) {
            dividend += statistic1[i] * statistic2[i];
            divisor1 += Math.pow(statistic1[i], 2);
            divisor2 += Math.pow(statistic2[i], 2);
        }

        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));
    }

    private  int[] statistic(List<String> allWords, List<String> sentWords) {
        int[] result = new int[allWords.size()];
        for (int i = 0; i < allWords.size(); i++) {
            result[i] = Collections.frequency(sentWords, allWords.get(i));
        }
        return result;
    }

    private  List<String> mergeList(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result.stream().distinct().collect(Collectors.toList());
    }

    private  List<String> getSplitWords(String sentence) {
        // 去除掉html标签
        sentence = Jsoup.parse(sentence.replace("&nbsp;","")).body().text();
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
    }
}
