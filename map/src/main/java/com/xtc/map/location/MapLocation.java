package com.xtc.map.location;

/**
 * 定位结果实体
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapLocation {
    /**
     * 定位失败
     */
    public static final int FAIL = 0;

    /**
     * 定位成功
     */
    public static final int SUCCESS = 1;

    /**
     * 定位结果类型：GPS定位结果 通过设备GPS定位模块返回的定位结果
     */
    public static final int LOCATION_TYPE_GPS = 1;

    /**
     * 定位结果类型：Wifi定位结果 属于网络定位，定位精度相对基站定位会更好
     */
    public static final int LOCATION_TYPE_WIFI = 4;
    /**
     * 定位结果类型：基站定位结果 属于网络定位
     */
    public static final int LOCATION_TYPE_CELL = 5;
    /**
     * 定位结果类型： 离线定位结果
     */
    public static final int LOCATION_TYPE_OFFLINE = 7;
    /**
     * 定位结果类型：前次定位结果 网络定位请求低于1秒、或两次定位之间设备位置变化非常小时返回，设备位移通过传感器感知
     */
    public static final int LOCATION_TYPE_SAME_REQ = 2;

    private int locationType;//定位结果来源

    private String locationDetail;//定位信息描述

    private float accuracy;//精度，单位：米

    private String address;//地址

    private String poiName;//兴趣点名称

    private String adCode;//区域编码

    private String country;//国家名称

    private String province;//省的名称

    private String city;//城市名称

    private String cityCode;//城市编码

    private String district;//县/区名称

    private String street;//街道名称

    private String streetNum;//门牌号

    private double latitude;//纬度
    private double longitude;//经度

    private int satellites;//卫星数量, 仅在GPS定位时有效

    private float speed;//当前速度 单位：米/秒 仅在AMapLocation.getProvider()是gps时有效

    private double altitude;//海拔高度 单位：米 仅在AMapLocation.getProvider()是gps时有效

    private float bearing;//方向角 单位：度 仅在AMapLocation.getProvider()是gps时有效

    private int errorCode;//错误码

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSatellites() {
        return satellites;
    }

    public void setSatellites(int satellites) {
        this.satellites = satellites;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "MapLocation{" +
                "locationType=" + locationType +
                ", locationDetail='" + locationDetail + '\'' +
                ", accuracy=" + accuracy +
                ", address='" + address + '\'' +
                ", poiName='" + poiName + '\'' +
                ", adCode='" + adCode + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", satellites=" + satellites +
                ", speed=" + speed +
                ", altitude=" + altitude +
                ", bearing=" + bearing +
                ", errorCode=" + errorCode +
                '}';
    }
}
