package com.xtc.map.search;

import com.xtc.map.MapLatLng;

/**
 * 地理编码的搜索结果
 * <p/>
 * Created by hzj on 2016/5/17.
 */
public class CodeResult extends MapSearchResult {

    String address;

    MapLatLng mapLatLng;

    public String getAddress() {
        return address;
    }

    public MapLatLng getMapLatLng() {
        return mapLatLng;
    }

    @Override
    public String toString() {
        return "CodeResult{" +
                "address='" + address + '\'' +
                ", mapLatLng=" + mapLatLng +
                '}';
    }
}
