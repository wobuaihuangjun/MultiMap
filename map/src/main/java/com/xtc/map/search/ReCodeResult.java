package com.xtc.map.search;

import com.xtc.map.MapLatLng;

import java.util.List;

/**
 * 逆地理编码的搜索结果
 * <p/>
 * Created by hzj on 2016/5/19.
 */
public class ReCodeResult extends MapSearchResult {

    String address;//简要地址信息

    MapLatLng mapLatLng;//位置坐标

    String province;//省份名称
    String city;//城市名称
    String district;//区县名称
    String street;//街道名称
    String streetNumber;//门牌号码

    String businessCircle;//位置所属商圈名称

    List<MapPoiItem> poiList;//位置附近的POI信息

    public String getAddress() {
        return address;
    }

    public MapLatLng getMapLatLng() {
        return mapLatLng;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getBusinessCircle() {
        return businessCircle;
    }

    public List<MapPoiItem> getPoiList() {
        return poiList;
    }

    @Override
    public String toString() {
        return "ReCodeResult{" +
                "address='" + address + '\'' +
                ", mapLatLng=" + mapLatLng +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", businessCircle='" + businessCircle + '\'' +
                ", poiList=" + poiList +
                '}';
    }
}
