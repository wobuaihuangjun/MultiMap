package com.xtc.multimap.map;

/**
 * 地理坐标基本数据结构
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class LatLng {

    public double latitude;//纬度

    public double longitude;//经度

    public LatLng() {

    }

    public LatLng(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
