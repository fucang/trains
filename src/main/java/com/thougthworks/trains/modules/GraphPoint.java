package com.thougthworks.trains.modules;

/**
 * Created by zhuxiaoxiang.zhu on 19-3-13.
 * 节点实体类
 */
public class GraphPoint {
    private String start;
    private String end;
    private int weight;

    public GraphPoint() {
    }

    public GraphPoint(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphPoint graphPoint = (GraphPoint) o;

        if (start != null ? !start.equals(graphPoint.start) : graphPoint.start != null) return false;
        return end != null ? end.equals(graphPoint.end) : graphPoint.end == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
