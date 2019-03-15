package com.thougthworks.trains.utils;

import com.thougthworks.trains.modules.Graphics;
import org.apache.commons.lang3.StringUtils;

/**
 * 构建地图
 * Created by zhuxiaoxiang.zhu on 19-3-13.
 */
public class GraphBuilder {
    public static Graphics buildRoutePath(String inputStrGraph) {
        if (StringUtils.isBlank(inputStrGraph)) {
            return null;
        }
        String[] graphPoints = StringUtils.split(inputStrGraph, ",");
        Graphics graphics = new Graphics();
        for (int i = 0; i < graphPoints.length; i++) {
            String point = graphPoints[i].trim();
            String from = point.substring(0, 1);
            String to = point.substring(1, 2);
            Integer weight = Integer.valueOf(point.substring(2));
            graphics.addPoint(from, to, weight);
        }
        return graphics;
    }
}
