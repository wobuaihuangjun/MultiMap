package com.xtc.map.search;

import android.content.Context;

import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xtc.map.MapManager;

/**
 * 地理编码查询接口
 * <p/>
 * Created by hzj on 2016/5/17.
 */
public class CoderSearch {

    private Context context;
    private int currentMapType;

    private GeoCoder mSearch;
    private GeocodeSearch geocoderSearch;

    private OnCoderResultListener listener;

    /**
     * 创建地图搜索服务
     * <p/>
     * 地图切换后需要重新创建
     *
     * @param context
     * @param currentMapType 当前地图类型
     */
    public CoderSearch(Context context, int currentMapType) {
        this.context = context;
        this.currentMapType = currentMapType;
        init();
    }

    private void init() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            mSearch = GeoCoder.newInstance();
        } else {
            geocoderSearch = new GeocodeSearch(context);
        }
    }

    /**
     * 设置查询结果监听者
     *
     * @param listener 监听者
     */
    public void setOnGetGeoCodeResultListener(OnCoderResultListener listener) {
        this.listener = listener;
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            mSearch.setOnGetGeoCodeResultListener(bdlistener);
        } else {
            geocoderSearch.setOnGeocodeSearchListener(gdlistener);
        }
    }

    /**
     * 发起地理编码(地址信息->经纬度)请求
     *
     * @param option 请求参数
     */
    public void geocode(CodeOption option) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            mSearch.geocode(ConvertGeoCode.convertToBdGeoCodeOption(option));
        } else {
            geocoderSearch.getFromLocationNameAsyn(ConvertGeoCode.convertToGdGeocodeQuery(option));
        }
    }

    /**
     * 发起反地理编码请求(经纬度->地址信息)
     *
     * @param option 请求参数
     */
    public void reGeoCode(ReCodeOption option) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            mSearch.reverseGeoCode(ConvertGeoCode.convertToBdReGeoCodeOption(option));
        } else {
            geocoderSearch.getFromLocationAsyn(ConvertGeoCode.convertToGdReGeoCodeOption(option));
        }
    }

    /**
     * 释放该地理编码查询对象
     */
    public void destroy() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            mSearch.destroy();
        }
    }

    OnGetGeoCoderResultListener bdlistener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (listener != null) {
                listener.onGeocodeSearched(ConvertGeoCode.convertBdGeoCodeResult(result));
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (listener != null) {
                listener.onRegeocodeSearched(ConvertGeoCode.convertBdReCodeResult(result));
            }
        }
    };

    GeocodeSearch.OnGeocodeSearchListener gdlistener = new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onGeocodeSearched(GeocodeResult result, int rCode) {
            if (listener != null) {
                listener.onGeocodeSearched(ConvertGeoCode.convertGdGeoCodeResult(result, rCode));
            }
        }

        @Override
        public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
            if (listener != null) {
                listener.onRegeocodeSearched(ConvertGeoCode.convertGdReCodeResult(result, rCode));
            }
        }
    };
}
