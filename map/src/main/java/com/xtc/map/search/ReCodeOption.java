package com.xtc.map.search;

import com.xtc.map.MapLatLng;

/**
 * 逆地理编码请求参数
 * Created by hzj on 2016/5/17.
 */
public class ReCodeOption {

    MapLatLng location;

    public ReCodeOption location(MapLatLng location) {
        this.location = location;
        return this;
    }
}
