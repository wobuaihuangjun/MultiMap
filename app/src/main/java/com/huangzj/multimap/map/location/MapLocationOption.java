package com.huangzj.multimap.map.location;

/**
 * 配置定位SDK各配置参数，比如定位模式、定位时间间隔、坐标系类型等
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapLocationOption {

    private Integer locationMode;//当前的定位模式

    private String coorType;// 坐标类型，百度地图有效

    private Boolean gpsFirst;//是否优先返回GPS定位信息

    private Boolean mockEnable;//Gps是否允许模拟GPS true:允许； false:不允许，默认为false

    private Boolean isIgnoreKillProcess;//是否退出定位进程 true:不退出进程； false:退出进程，默认为true

    private Boolean isNeedAddress; //是否需要地址信息，默认为无地址

    private Boolean isNeedLocationDescribe;//是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充

    private Boolean isNeedLocationPoiList;//是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据，百度地图有效

    private Boolean isNeedDeviceDirect;//在网络定位时，是否需要设备方向 true:需要 ; false:不需要。百度地图有效

    private Boolean locationNotify;//位置改变通知，百度地图有效

    private Boolean openGps;//是否打开gps进行定位

    private Integer scanSpan;//扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效

    private Integer timeOut;//网络超时时间

    private Boolean onceLocation;//是否只定位一次

    private Boolean wifiActiveScan;//设置是否主动刷新WIFI,默认值：true 主动刷新

    public Boolean getWifiActiveScan() {
        return wifiActiveScan;
    }

    public void setWifiActiveScan(Boolean wifiActiveScan) {
        this.wifiActiveScan = wifiActiveScan;
    }

    public Boolean getOnceLocation() {
        return onceLocation;
    }

    public void setOnceLocation(Boolean onceLocation) {
        this.onceLocation = onceLocation;
    }

    public Integer getLocationMode() {
        return locationMode;
    }

    public void setLocationMode(Integer locationMode) {
        this.locationMode = locationMode;
    }

    public String getCoorType() {
        return coorType;
    }

    public void setCoorType(String coorType) {
        this.coorType = coorType;
    }

    public Boolean getGpsFirst() {
        return gpsFirst;
    }

    public void setGpsFirst(Boolean gpsFirst) {
        this.gpsFirst = gpsFirst;
    }

    public Boolean getMockEnable() {
        return mockEnable;
    }

    public void setMockEnable(Boolean mockEnable) {
        this.mockEnable = mockEnable;
    }

    public Boolean getIgnoreKillProcess() {
        return isIgnoreKillProcess;
    }

    public void setIgnoreKillProcess(Boolean ignoreKillProcess) {
        isIgnoreKillProcess = ignoreKillProcess;
    }

    public Boolean getNeedAddress() {
        return isNeedAddress;
    }

    public void setNeedAddress(Boolean needAddress) {
        isNeedAddress = needAddress;
    }

    public Boolean getNeedLocationDescribe() {
        return isNeedLocationDescribe;
    }

    public void setNeedLocationDescribe(Boolean needLocationDescribe) {
        isNeedLocationDescribe = needLocationDescribe;
    }

    public Boolean getNeedLocationPoiList() {
        return isNeedLocationPoiList;
    }

    public void setNeedLocationPoiList(Boolean needLocationPoiList) {
        isNeedLocationPoiList = needLocationPoiList;
    }

    public Boolean getNeedDeviceDirect() {
        return isNeedDeviceDirect;
    }

    public void setNeedDeviceDirect(Boolean needDeviceDirect) {
        isNeedDeviceDirect = needDeviceDirect;
    }

    public Boolean getLocationNotify() {
        return locationNotify;
    }

    public void setLocationNotify(Boolean locationNotify) {
        this.locationNotify = locationNotify;
    }

    public Boolean getOpenGps() {
        return openGps;
    }

    public void setOpenGps(Boolean openGps) {
        this.openGps = openGps;
    }

    public Integer getScanSpan() {
        return scanSpan;
    }

    public void setScanSpan(Integer scanSpan) {
        this.scanSpan = scanSpan;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public interface LocationMode {
        int Hight_Accuracy = 0;
        int Battery_Saving = 1;
        int Device_Sensors = 2;
    }
}
