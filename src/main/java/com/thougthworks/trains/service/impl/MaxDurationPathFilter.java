package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-20.
 */
public class MaxDurationPathFilter extends PathFilter {
    private int maxDuration;

    public MaxDurationPathFilter(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalDuration() <= maxDuration;
    }
}
