package com.xtc.map.search;

import com.xtc.map.MapLatLng;

/**
 * poi信息类
 * Created by hzj on 2016/5/19.
 */
public class MapPoiItem {

    String address;//poi地址信息
    String city;//poi所在城市

    MapLatLng location;//poi坐标, 当ePoiType为2或4时，pt 为空

    String name;//poi名称

    String phoneNum;//poi电话信息

    String postCode;//poi邮编

//    public boolean hasCaterDetails;//poi点是否有美食类详情页面
//
//    public boolean isPano;//poi点附近是否有街景，可使用uid检索全景组件的全景数据

//
//    public int type;//poi类型，0：普通点，1：公交站，2：公交线路，3：地铁站，4：地铁线路,
//
    public String uid;//poi id 如果为isPano为true，可用此参数 调用街景组件PanoramaService类的requestPanoramaWithPoiUId方法检索街景数据


    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public MapLatLng getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "MapPoiItem{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", location=" + location +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", postCode='" + postCode + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
