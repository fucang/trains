package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * 最长距离filter
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class MaxDistancePathFilter extends PathFilter {
    private int maxDistance;

    public MaxDistancePathFilter(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalWeight() < maxDistance;
    }
}
