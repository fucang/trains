package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-20.
 */
public class MaxTimeTakedPathFilter extends PathFilter {
    private Integer maxTimeTaked;

    public MaxTimeTakedPathFilter(Integer maxTimeTaked) {
        this.maxTimeTaked = maxTimeTaked;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalDuration() < maxTimeTaked;
    }
}
