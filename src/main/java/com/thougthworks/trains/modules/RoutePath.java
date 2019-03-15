package com.thougthworks.trains.modules;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径实体类
 * Created by zhuxiaoxiang.zhu on 19-3-14.
 */
public class RoutePath {
    private List<GraphPoint> routePaths = new ArrayList<>();
    private int totalWeight;

    public RoutePath() {
    }

    public RoutePath(String... nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            GraphPoint point = new GraphPoint(nodes[i], nodes[i + 1], -1);
            routePaths.add(point);
        }
    }

    public RoutePath(final List<GraphPoint> routePaths, int totalWeight) {
        this.routePaths = routePaths;
        this.totalWeight = totalWeight;
    }

    public RoutePath(final RoutePath otherPaths) {
        this.routePaths.addAll(otherPaths.getRoutePaths());
        this.totalWeight = otherPaths.getTotalWeight();
    }

    public List<GraphPoint> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<GraphPoint> routePaths) {
        this.routePaths = routePaths;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public boolean addPathPoint(final GraphPoint point) {
        if (!checkIsLegal(point)) {
            return false;
        }
        boolean addRet = routePaths.add(point);
        if (addRet) {
            totalWeight += point.getWeight();
        }
        return addRet;
    }

    private boolean checkIsLegal(final GraphPoint point) {
        GraphPoint lastPoint = getRoutePathLastPoint();
        return point != null && (lastPoint == null || lastPoint.getEnd().equals(point.getStart()));
    }

    public GraphPoint getRoutePathLastPoint() {
        if (CollectionUtils.isNotEmpty(routePaths)) {
            return routePaths.get(routePaths.size() - 1);
        }
        return null;
    }

    public static RoutePath clonePath(RoutePath path) {
        return new RoutePath(path);
    }

    public void removeLastPoint() {
        if (CollectionUtils.isNotEmpty(routePaths)) {
            GraphPoint remove = routePaths.remove(routePaths.size() - 1);
            this.totalWeight -= remove.getWeight();
        }
    }

    public boolean startWithPath(RoutePath path) {
        List<GraphPoint> thatPath = path.getRoutePaths();
        int thisPathSize = this.routePaths.size();
        for (int i = 0; i < thatPath.size(); i++) {
            if (i >= thisPathSize || !routePaths.get(i).equals(thatPath.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int getPathStopNum() {
        return this.routePaths.size();
    }
}
