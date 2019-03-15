package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * 给定经过节点数量filter
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class ExactlyStopPathFilter extends PathFilter {
    private int exactlyStopNum;

    public ExactlyStopPathFilter(int exactlyStopNum) {
        this.exactlyStopNum = exactlyStopNum;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getPathStopNum() <= exactlyStopNum;
    }

    @Override
    public boolean pathMatch(RoutePath path, String to, List<RoutePath> matchPaths) {
        return super.pathMatch(path, to, matchPaths) && path.getPathStopNum() == exactlyStopNum;
    }
}
