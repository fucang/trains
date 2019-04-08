package com.thougthworks.trains.utils;

import com.google.common.base.Splitter;
import com.thougthworks.trains.modules.GraphPoint;
import com.thougthworks.trains.service.GraphicService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 构建地图
 * Created by zhuxiaoxiang.zhu on 19-3-13.
 */
public class GraphBuilder {
    public static GraphicService buildRoutePath(String inputStrGraph) {
        if (StringUtils.isBlank(inputStrGraph)) {
            return null;
        }
//        List<String> list = Arrays.asList(StringUtils.split(inputStrGraph, ","));
        List<String> graphPoints = Splitter.on(",").splitToList(inputStrGraph);
        Map<String, Set<GraphPoint>> graphMap = new HashMap<>();
        GraphicService graphics = new GraphicService(graphMap);
        graphPoints.forEach(item -> {
            String point = item.trim();
            String from = point.substring(0, 1);
            String to = point.substring(1, 2);
            Integer weight = Integer.valueOf(point.substring(2));
            addPoint(graphMap, from, to, weight);
        });
        return graphics;
    }

    /**
     * 向图中添加节点
     *
     * @param graphMap
     * @param from 起点  不能为空
     * @param to 终点 可以为空  因为可能存在孤立节点
     * @param weight 起点到终点的距离
     * @return 添加成功true  否则false
     */
    public static boolean addPoint(Map<String, Set<GraphPoint>> graphMap, String from, String to, Integer weight) {
        if (from == null) {
            return false;
        }
        GraphPoint graphPoint = new GraphPoint(from, to, weight);
        Set<GraphPoint> graphPoints = graphMap.get(from);
        if (graphPoints == null) { // 之前没有添加过的起点
            graphPoints = new HashSet<>();
            graphMap.put(from, graphPoints);
        }
        return graphPoints.add(graphPoint);
    }
}
