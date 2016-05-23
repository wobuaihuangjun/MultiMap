package com.xtc.map.search;

import android.support.annotation.NonNull;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.StreetNumber;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xtc.map.ConvertUtil;
import com.xtc.map.MapLatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索服务数据转换
 * <p/>
 * Created by hzj on 2016/5/19.
 */
public class ConvertGeoCode {

    /**
     * 转换为百度地理编码参数
     *
     * @param option 地理编码参数
     * @return 百度地理编码参数
     */
    public static GeoCodeOption convertToBdGeoCodeOption(@NonNull CodeOption option) {
        return new GeoCodeOption()
                .address(option.address)
                .city(option.city);
    }

    /**
     * 转换为高德地理编码参数
     *
     * @param option 地理编码参数
     * @return 高德地理编码参数
     */
    public static GeocodeQuery convertToGdGeocodeQuery(@NonNull CodeOption option) {
        return new GeocodeQuery(option.address, option.city);
    }

    /**
     * 转换为百度逆地理编码参数
     *
     * @param option 逆地理编码参数
     * @return 百度逆地理编码参数
     */
    public static ReverseGeoCodeOption convertToBdReGeoCodeOption(@NonNull ReCodeOption option) {
        return new ReverseGeoCodeOption()
                .location(ConvertUtil.convertToBdLatLng(option.location));
    }

    /**
     * 转换为高德逆地理编码参数
     *
     * @param option 逆地理编码参数
     * @return 高德逆地理编码参数
     */
    public static RegeocodeQuery convertToGdReGeoCodeOption(@NonNull ReCodeOption option) {
        if (option.location == null) {
            return null;
        }
        LatLonPoint latLonPoint = new LatLonPoint(option.location.getLatitude(), option.location.getLongitude());
        return new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
    }

    /**
     * 转换百度地理编码结果
     *
     * @param result 百度地理编码结果
     * @return 地理编码结果
     */
    public static CodeResult convertBdGeoCodeResult(GeoCodeResult result) {
        CodeResult codeResult = new CodeResult();
        if (result == null) {
            codeResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            return codeResult;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            codeResult.errorCode = MapSearchResult.NO_ERROR;
            codeResult.address = result.getAddress();
            codeResult.mapLatLng = ConvertUtil.convertBdLatLng(result.getLocation());
        } else {
            codeResult.errorCode = MapSearchResult.ERROR;
        }
        return codeResult;
    }

    /**
     * 转换高德地理编码结果
     *
     * @param result 高德地理编码结果
     * @param rCode  高德地理编码错误码
     * @return 地理编码结果
     */
    public static CodeResult convertGdGeoCodeResult(GeocodeResult result, int rCode) {
        CodeResult codeResult = new CodeResult();
        if (rCode != 1000) {
            codeResult.errorCode = MapSearchResult.ERROR;
            return codeResult;
        }
        if (result == null || result.getGeocodeAddressList() == null
                || result.getGeocodeAddressList().size() <= 0) {
            codeResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            return codeResult;
        }
        codeResult.errorCode = MapSearchResult.NO_ERROR;
        GeocodeAddress address = result.getGeocodeAddressList().get(0);
        if (address != null) {
            codeResult.address = address.getFormatAddress();
            LatLonPoint latLonPoint = address.getLatLonPoint();
            if (latLonPoint != null) {
                codeResult.mapLatLng = new MapLatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
            }
        }
        return codeResult;
    }

    /**
     * 转换百度逆地理编码结果
     *
     * @param result 百度逆地理编码结果
     * @return 逆地理编码结果
     */
    public static ReCodeResult convertBdReCodeResult(ReverseGeoCodeResult result) {
        ReCodeResult reCodeResult = new ReCodeResult();
        if (result == null) {
            reCodeResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            return reCodeResult;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            reCodeResult.errorCode = MapSearchResult.NO_ERROR;

            reCodeResult.address = result.getAddress();
            reCodeResult.mapLatLng = ConvertUtil.convertBdLatLng(result.getLocation());
            reCodeResult.businessCircle = result.getBusinessCircle();

            List<PoiInfo> poiInfos = result.getPoiList();
            if (poiInfos != null) {
                List<MapPoiItem> mapPoiItems = new ArrayList<>();
                for (PoiInfo poiInfo : poiInfos) {
                    mapPoiItems.add(convertBdPoiInfo(poiInfo));
                }
                reCodeResult.poiList = mapPoiItems;
            }

            ReverseGeoCodeResult.AddressComponent address = result.getAddressDetail();
            if (address != null) {
                reCodeResult.province = address.province;
                reCodeResult.city = address.city;
                reCodeResult.district = address.district;
                reCodeResult.street = address.street;
                reCodeResult.streetNumber = address.streetNumber;
            }
        } else {
            reCodeResult.errorCode = MapSearchResult.ERROR;
        }
        return reCodeResult;
    }

    /**
     * 转换高德逆地理编码结果
     *
     * @param result 高德逆地理编码结果
     * @param rCode  高德逆地理编码错误码
     * @return 逆地理编码结果
     */
    public static ReCodeResult convertGdReCodeResult(RegeocodeResult result, int rCode) {
        ReCodeResult reCodeResult = new ReCodeResult();
        if (rCode != 1000) {
            reCodeResult.errorCode = MapSearchResult.ERROR;
            return reCodeResult;
        }
        if (result == null || result.getRegeocodeAddress() == null) {
            reCodeResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            return reCodeResult;
        }
        reCodeResult.errorCode = MapSearchResult.NO_ERROR;
        RegeocodeAddress address = result.getRegeocodeAddress();
        if (address != null) {
            reCodeResult.address = address.getFormatAddress();
            reCodeResult.province = address.getProvince();
            reCodeResult.city = address.getCity();
            reCodeResult.district = address.getDistrict();

            List<BusinessArea> businessAreas = address.getBusinessAreas();
            if (businessAreas != null && businessAreas.size() > 0) {
                reCodeResult.businessCircle = businessAreas.get(0).getName();
            }

            List<PoiItem> poiInfos = address.getPois();
            if (poiInfos != null) {
                List<MapPoiItem> mapPoiItems = new ArrayList<>();
                for (PoiItem poiItem : poiInfos) {
                    mapPoiItems.add(convertGdPoiItem(poiItem));
                }
                reCodeResult.poiList = mapPoiItems;
            }

            StreetNumber street = address.getStreetNumber();
            if (street != null) {
                reCodeResult.street = street.getStreet();
                reCodeResult.streetNumber = street.getNumber();

                LatLonPoint latLonPoint = street.getLatLonPoint();
                if (latLonPoint != null) {
                    reCodeResult.mapLatLng = new MapLatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                }
            }
        }
        return reCodeResult;
    }

    /**
     * 转换百度PoiInfo
     *
     * @param poiInfo 百度PoiInfo
     * @return MapPoiItem
     */
    public static MapPoiItem convertBdPoiInfo(PoiInfo poiInfo) {
        if (poiInfo == null) {
            return null;
        }
        MapPoiItem mapPoiItem = new MapPoiItem();
        mapPoiItem.address = poiInfo.address;
        mapPoiItem.city = poiInfo.city;
        mapPoiItem.location = ConvertUtil.convertBdLatLng(poiInfo.location);
        mapPoiItem.name = poiInfo.name;
        mapPoiItem.phoneNum = poiInfo.phoneNum;
        mapPoiItem.postCode = poiInfo.postCode;
        mapPoiItem.uid = poiInfo.uid;
        return mapPoiItem;
    }

    /**
     * 转换高德PoiItem
     *
     * @param poiItem 高德PoiItem
     * @return MapPoiItem
     */
    public static MapPoiItem convertGdPoiItem(PoiItem poiItem) {
        if (poiItem == null) {
            return null;
        }
        MapPoiItem mapPoiItem = new MapPoiItem();
        mapPoiItem.address = poiItem.getSnippet();
        mapPoiItem.city = poiItem.getCityName();
        LatLonPoint latLonPoint = poiItem.getLatLonPoint();
        if (latLonPoint != null) {
            mapPoiItem.location = new MapLatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
        }
        mapPoiItem.name = poiItem.getTitle();
        mapPoiItem.phoneNum = poiItem.getTel();
        mapPoiItem.postCode = poiItem.getPostcode();
        return mapPoiItem;
    }
}
