package com.thougthworks.trains.service.impl;

import com.thougthworks.trains.modules.RoutePath;
import com.thougthworks.trains.service.PathFilter;

import java.util.List;

/**
 * 给定路线filter
 * Created by zhuxiaoxiang.zhu on 19-3-15.
 */
public class ContainsPathFilter extends PathFilter {
    private final RoutePath containsPath;

    public ContainsPathFilter(final RoutePath paths) {
        this.containsPath = paths;
    }

    @Override
    public boolean passFilter(RoutePath path, List<RoutePath> matchPaths) {
        return containsPath != null && matchPaths.isEmpty() && containsPath.startWithPath(path);
    }
}
