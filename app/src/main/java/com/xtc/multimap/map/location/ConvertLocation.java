package com.xtc.multimap.map.location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.List;

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
        if (aMapLocation == null) {
            return null;
        }
        MapLocation mapLocation = new MapLocation();

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

        mapLocation.setLocationDetail(aMapLocation.getLocationDetail());

        int locType = aMapLocation.getLocationType();
        if (locType == AMapLocation.LOCATION_TYPE_CELL) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_CELL);
        } else if (locType == AMapLocation.LOCATION_TYPE_GPS) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_GPS);
        } else if (locType == AMapLocation.LOCATION_TYPE_WIFI) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_WIFI);
        } else if (locType == AMapLocation.LOCATION_TYPE_SAME_REQ) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_SAME_REQ);
        } else if (locType == AMapLocation.LOCATION_TYPE_OFFLINE
                || locType == AMapLocation.LOCATION_TYPE_FIX_CACHE) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_OFFLINE);
        } else {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_OFFLINE);
        }

        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            // 定位成功
            mapLocation.setErrorCode(MapLocation.SUCCESS);
        } else {
            mapLocation.setErrorCode(MapLocation.FAIL);
        }


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
        mapLocation.setAccuracy(bdLocation.getRadius());
        mapLocation.setAddress(bdLocation.getAddrStr());
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

        mapLocation.setLocationDetail(bdLocation.getLocationDescribe());

        List<Poi> poiList = bdLocation.getPoiList();
        if (poiList != null && poiList.size() > 0) {
            mapLocation.setPoiName(poiList.get(0).getName());
        }

        int locType = bdLocation.getLocType();
        if (locType == BDLocation.TypeGpsLocation) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_GPS);
        } else if (locType == BDLocation.TypeNetWorkLocation) {
            String netLocationType = bdLocation.getNetworkLocationType();
            if (netLocationType == null) {
                // 没有获取到定位结果类型
            } else if (netLocationType.contains("wf")) {
                mapLocation.setLocationType(MapLocation.LOCATION_TYPE_WIFI);
            } else if (netLocationType.contains("cl")) {
                mapLocation.setLocationType(MapLocation.LOCATION_TYPE_CELL);
            } else if (netLocationType.contains("ll")) {
                mapLocation.setLocationType(MapLocation.LOCATION_TYPE_GPS);
            } else {
                // 未知的网络定位类型
            }
        } else if (locType == BDLocation.TypeOffLineLocation
                || locType == BDLocation.TypeCacheLocation) {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_OFFLINE);
        } else {
            mapLocation.setLocationType(MapLocation.LOCATION_TYPE_OFFLINE);
        }

        if (BDLocation.TypeGpsLocation != bdLocation.getLocType()
                || BDLocation.TypeNetWorkLocation != bdLocation.getLocType()
                || BDLocation.TypeOffLineLocation != bdLocation.getLocType()) {
            // 定位成功
            mapLocation.setErrorCode(MapLocation.SUCCESS);
        } else {
            mapLocation.setErrorCode(MapLocation.FAIL);
        }

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
