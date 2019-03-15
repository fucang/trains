package com.thougthworks.trains.service;

import com.thougthworks.trains.modules.GraphPoint;
import com.thougthworks.trains.modules.RoutePath;

import java.util.List;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-14.
 */
public abstract class PathFilter {
    abstract public boolean passFilter(final RoutePath path, final List<RoutePath> matchPaths);

    public boolean pathMatch(RoutePath path, String to, List<RoutePath> matchPaths) {
        GraphPoint lastPoint = path.getRoutePathLastPoint();
        return lastPoint != null && to.equals(lastPoint.getEnd());
    }
}
