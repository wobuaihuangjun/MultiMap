package com.xtc.map;

/**
 * 地图上的一个自带Poi点
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapPoi {

    public MapLatLng position;
    public String name;

    public MapPoi() {
    }

    @Override
    public String toString() {
        return "MapPoi{" +
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }
}
