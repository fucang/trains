package com.thougthworks.trains.modules;

import com.thougthworks.trains.service.PathFilter;

import java.util.*;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-13.
 */
public class Graphics {
    private final Map<String, Set<GraphPoint>> graphMap = new HashMap<>();

    /**
     * 向图中添加节点
     * @param from 起点  不能为空
     * @param to 终点 可以为空  因为可能存在孤立节点
     * @param weight 起点到终点的距离
     * @return 添加成功true  否则false
     */
    public boolean addPoint(String from, String to, Integer weight) {
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

    /**
     * 得到两个节点之间的直接距离
     * @param from
     * @param to
     * @return
     */
    public int getGraphPointDistance(String from, String to) {
        if (from == null || to == null) {
            return -1;
        }
        Set<GraphPoint> graphPoints = graphMap.getOrDefault(from, new HashSet<>());
        for (GraphPoint point : graphPoints) {
            if (to.equals(point.getEnd())) {
                return point.getWeight();
            }
        }
        return -1;
    }

    /**
     * 获取从from到to的所有符合filter筛选的路径
     * @param from
     * @param to
     * @param filter
     * @return
     */
    public List<RoutePath> getAllPaths(String from, String to, PathFilter filter) {
        List<RoutePath> paths = new ArrayList<>();
        if (!graphMap.containsKey(from) || !graphMap.containsKey(to)) {
            return paths;
        }
        Set<GraphPoint> graphPoints = graphMap.get(from);
        for (GraphPoint point : graphPoints) {
            RoutePath path = new RoutePath();
            path.addPathPoint(point);
            search(path, to, filter, paths);
        }
        return paths;
    }

    /**
     * 深度优先搜索
     * @param path
     * @param to
     * @param filter
     * @return
     */
    private List<RoutePath> search(RoutePath path, String to, PathFilter filter, List<RoutePath> paths) {
        if (filter.passFilter(path, paths)) {  // 每种类别的题的filter不同
            if (filter.pathMatch(path, to, paths)) {
                paths.add(RoutePath.clonePath(path));
            }
            GraphPoint lastPoint = path.getRoutePathLastPoint();
            if (lastPoint != null) {
                Set<GraphPoint> graphPoints = graphMap.get(lastPoint.getEnd());
                for (GraphPoint point : graphPoints) {
                    path.addPathPoint(point);
                    search(path, to, filter, paths);
                }
            }

        }
        // 去除最后一个不满足条件的
        path.removeLastPoint();
        return paths;
    }
}
