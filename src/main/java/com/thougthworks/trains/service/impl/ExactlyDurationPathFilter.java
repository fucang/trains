package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-20.
 */
public class ExactlyDurationPathFilter extends PathFilter {
    private int exactlyDurationNum;

    public ExactlyDurationPathFilter(int exactlyDurationNum) {
        this.exactlyDurationNum = exactlyDurationNum;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getTotalDuration() <= exactlyDurationNum;
    }

    @Override
    public boolean pathMatch(RoutePath path, String to, List<RoutePath> matchPaths) {
        return super.pathMatch(path, to, matchPaths) && path.getTotalDuration() == exactlyDurationNum;
    }
}
