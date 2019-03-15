package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * 最短路径filter
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class ShortestPathFilter extends PathFilter {
    private int shortestPathWeight = Integer.MAX_VALUE;

    public ShortestPathFilter() {
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalWeight() < shortestPathWeight;
    }

    @Override
    public boolean pathMatch(RoutePath path, String to, List<RoutePath> matchPaths) {
        boolean pathMatch = super.pathMatch(path, to, matchPaths) && path.getTotalWeight() < shortestPathWeight;
        if (pathMatch) {
            matchPaths.clear();
            shortestPathWeight = path.getTotalWeight();
        }
        return pathMatch;
    }
}
