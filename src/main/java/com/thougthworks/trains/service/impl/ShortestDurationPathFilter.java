package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-20.
 */
public class ShortestDurationPathFilter extends PathFilter {
    private Integer minDuration = Integer.MAX_VALUE;

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalDuration() < minDuration;
    }

    @Override
    public boolean pathMatch(RoutePath path, String to, List<RoutePath> matchPaths) {
        boolean basePathMatch = super.pathMatch(path, to, matchPaths);
        int pathDuration = path.getTotalDuration();
        if (basePathMatch && pathDuration < this.minDuration) {
            this.minDuration = pathDuration;
            matchPaths.clear();
            return true;
        }
        return false;
    }
}
