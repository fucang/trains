package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * 最大经过节点次数filter
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class MaxStopPathFilter extends PathFilter {
    private int maxStopNum;

    public MaxStopPathFilter(int maxStopNum) {
        this.maxStopNum = maxStopNum;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return path.getPathStopNum() <= maxStopNum;
    }
}
