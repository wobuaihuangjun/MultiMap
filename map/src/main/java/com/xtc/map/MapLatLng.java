package com.xtc.map;

/**
 * 地理坐标基本数据结构
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapLatLng {

    double latitude;//纬度

    double longitude;//经度

    public MapLatLng() {

    }

    /**
     * @param latitude  纬度
     * @param longitude 经度
     */
    public MapLatLng(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public MapLatLng latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MapLatLng longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "MapLatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
