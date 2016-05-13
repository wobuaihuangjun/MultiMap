package com.xtc.map;

/**
 * 地图上的一个自带Poi点
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class Poi {

    public LatLng position;
    public String name;

    public Poi() {
    }

    @Override
    public String toString() {
        return "Poi{" +
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }
}
