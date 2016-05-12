package com.huangzj.multimap.map.location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationConfiguration;

/**
 * 定位信息转换
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class ConvertLocation {

    /**
     * 转换高德定位结果
     */
    public static MapLocation convertAMapLocation(AMapLocation aMapLocation) {
        if (aMapLocation == null || aMapLocation.getErrorCode() != AMapLocation.LOCATION_SUCCESS) {
            return null;
        }
        MapLocation mapLocation = new MapLocation();
        mapLocation.setLocationType(aMapLocation.getLocationType());
        mapLocation.setLocationDetail(aMapLocation.getLocationDetail());
        mapLocation.setAccuracy(aMapLocation.getAccuracy());
        mapLocation.setAddress(aMapLocation.getAddress());
        mapLocation.setPoiName(aMapLocation.getPoiName());
        mapLocation.setAdCode(aMapLocation.getAdCode());
        mapLocation.setCountry(aMapLocation.getCountry());
        mapLocation.setProvince(aMapLocation.getProvince());
        mapLocation.setCity(aMapLocation.getCity());
        mapLocation.setCityCode(aMapLocation.getCityCode());
        mapLocation.setDistrict(aMapLocation.getDistrict());
        mapLocation.setStreet(aMapLocation.getStreet());
        mapLocation.setStreetNum(aMapLocation.getStreetNum());
        mapLocation.setLatitude(aMapLocation.getLatitude());
        mapLocation.setLongitude(aMapLocation.getLongitude());
        mapLocation.setSatellites(aMapLocation.getSatellites());
        mapLocation.setSpeed(aMapLocation.getSpeed());
        mapLocation.setAltitude(aMapLocation.getAltitude());
        mapLocation.setBearing(aMapLocation.getBearing());

        mapLocation.setErrorCode();
        return mapLocation;
    }

    /**
     * 转换百度定位结果
     */
    public static MapLocation convertBaiduLocation(BDLocation bdLocation) {
        if (bdLocation == null) {
            return null;
        }
        MapLocation mapLocation = new MapLocation();
        mapLocation.setLocationType(bdLocation.getLocType());
        mapLocation.setLocationDetail(bdLocation.getLocationDescribe());
        mapLocation.setAccuracy(bdLocation.getRadius());
        mapLocation.setAddress(bdLocation.getAddrStr());

        // TODO: 2016/5/12 需要调整
//        mapLocation.setPoiName(bdLocation.getPoiList());

        mapLocation.setCountry(bdLocation.getCountry());
        mapLocation.setProvince(bdLocation.getProvince());
        mapLocation.setCity(bdLocation.getCity());
        mapLocation.setCityCode(bdLocation.getCityCode());
        mapLocation.setDistrict(bdLocation.getDistrict());
        mapLocation.setStreet(bdLocation.getStreet());
        mapLocation.setStreetNum(bdLocation.getStreetNumber());
        mapLocation.setLatitude(bdLocation.getLatitude());
        mapLocation.setLongitude(bdLocation.getLongitude());
        mapLocation.setSatellites(bdLocation.getSatelliteNumber());
        mapLocation.setSpeed(bdLocation.getSpeed());
        mapLocation.setAltitude(bdLocation.getAltitude());

        mapLocation.setErrorInfo();TypeGpsLocation  TypeNetWorkLocation TypeOffLineLocation
        return mapLocation;
    }

    /**
     * 转换定位配置参数为高德参数
     */
    public static AMapLocationClientOption convertToAMapLocationOption(MapLocationOption option) {
        AMapLocationClientOption aMapLocationOption = new AMapLocationClientOption();
        if (option == null) {
            return aMapLocationOption;
        }
        Integer mode = option.getLocationMode();
        if (mode != null) {
            if (mode == MapLocationOption.LocationMode.Battery_Saving) {
                aMapLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            } else if (mode == MapLocationOption.LocationMode.Hight_Accuracy) {
                aMapLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            } else {
                aMapLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
            }
        }

        if (option.getGpsFirst() != null) {
            aMapLocationOption.setGpsFirst(option.getGpsFirst());
        }
        if (option.getMockEnable() != null) {
            aMapLocationOption.setMockEnable(option.getMockEnable());
        }
        if (option.getIgnoreKillProcess() != null) {
            aMapLocationOption.setKillProcess(!option.getIgnoreKillProcess());
        }
        if (option.getNeedAddress() != null) {
            aMapLocationOption.setNeedAddress(option.getNeedAddress());
        }
        if (option.getTimeOut() != null) {
            aMapLocationOption.setHttpTimeOut(option.getTimeOut());
        }
        if (option.getScanSpan() != null) {
            aMapLocationOption.setInterval(option.getScanSpan());
        }
        if (option.getOnceLocation() != null) {
            aMapLocationOption.setOnceLocation(option.getOnceLocation());
        }
        if (option.getWifiActiveScan() != null) {
            aMapLocationOption.setWifiActiveScan(option.getWifiActiveScan());
        }
        return aMapLocationOption;
    }

    /**
     * 转换定位配置参数为百度参数
     */
    public static LocationClientOption convertToBaiduLocationOption(MapLocationOption option) {
        LocationClientOption baiduLocationOption = new LocationClientOption();
        if (option == null) {
            return baiduLocationOption;
        }

        Integer mode = option.getLocationMode();
        if (mode != null) {
            if (mode == MapLocationOption.LocationMode.Battery_Saving) {
                baiduLocationOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
            } else if (mode == MapLocationOption.LocationMode.Hight_Accuracy) {
                baiduLocationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            } else {
                baiduLocationOption.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
            }
        }

        if (option.getMockEnable() != null) {
            baiduLocationOption.setEnableSimulateGps(option.getMockEnable());
        }
        if (option.getIgnoreKillProcess() != null) {
            baiduLocationOption.setIgnoreKillProcess(!option.getIgnoreKillProcess());
        }
        if (option.getNeedAddress() != null) {
            boolean value = option.getNeedAddress();
            baiduLocationOption.setIsNeedAddress(value);
            baiduLocationOption.setIsNeedLocationDescribe(value);
            baiduLocationOption.setIsNeedLocationPoiList(value);
            baiduLocationOption.setNeedDeviceDirect(value);
            baiduLocationOption.setIsNeedAltitude(value);
        }
        if (option.getTimeOut() != null) {
            baiduLocationOption.setTimeOut(option.getTimeOut());
        }
        if (option.getScanSpan() != null) {
            baiduLocationOption.setScanSpan(option.getScanSpan());
        }
        if (option.getOpenGps() != null) {
            baiduLocationOption.setOpenGps(option.getOpenGps());
        }
        if (option.getCoorType() != null) {
            baiduLocationOption.setCoorType(option.getCoorType());
        }
        if (option.getLocationNotify() != null) {
            baiduLocationOption.setLocationNotify(option.getLocationNotify());
        }
        return baiduLocationOption;
    }
}
