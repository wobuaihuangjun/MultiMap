package com.xtc.map.search;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.xtc.map.ConvertUtil;
import com.xtc.map.MapManager;

import java.util.ArrayList;
import java.util.List;

/**
 * POI检索接口
 * Created by hzj on 2016/5/20.
 */
public class MapPoiSearch {

    private Context context;
    private int currentMapType;

    private com.baidu.mapapi.search.poi.PoiSearch bdSearch;

    private OnPoiSearchListener listener;

    /**
     * 创建Poi搜索服务
     * <p/>
     * 地图切换后需要重新创建
     *
     * @param context
     * @param currentMapType 当前地图类型
     */
    public MapPoiSearch(Context context, int currentMapType) {
        this.context = context;
        this.currentMapType = currentMapType;
        init();
    }

    private void init() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdSearch = com.baidu.mapapi.search.poi.PoiSearch.newInstance();
        } else {
//            gdSearch = new com.amap.api.services.poisearch.PoiSearch(context);
        }
    }

    /**
     * 设置查询监听接口
     *
     * @param listener 查询监听接口
     */
    public void setOnPoiSearchListener(@NonNull OnPoiSearchListener listener) {
        this.listener = listener;
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdSearch.setOnGetPoiSearchResultListener(bdListener);
        }
    }

    /**
     * 关键字检索
     *
     * @param option 请求参数
     */
    public void searchKeyWord(@NonNull PoiKeySearchOption option) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdSearch.searchInCity((new PoiCitySearchOption())
                    .city(option.city)
                    .keyword(option.keyWord)
                    .pageCapacity(option.pageSize)
                    .pageNum(option.pageNumber));
        } else {
            PoiSearch.Query query = new PoiSearch.Query(option.keyWord, option.searchType, option.city);
            // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索城市（空字符串代表全国）
            query.setPageSize(option.pageSize);// 设置每页最多返回多少条poiitem
            query.setPageNum(option.pageNumber);// 设置查第一页

            PoiSearch poiSearch = new PoiSearch(context, query);
            poiSearch.setOnPoiSearchListener(gdListener);
            poiSearch.searchPOIAsyn();
        }
    }

    /**
     * 周边检索
     *
     * @param option 请求参数
     */
    public void searchNearby(PoiAroundSearchOption option) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdSearch.searchNearby(new PoiNearbySearchOption()
                    .location(ConvertUtil.convertToBdLatLng(option.location))
                    .keyword(option.keyWord)
                    .radius(option.radius)
                    .pageCapacity(option.pageSize)
                    .pageNum(option.pageNumber));// 发起附近检索请求

        } else {
            PoiSearch.Query query = new PoiSearch.Query(option.keyWord, option.searchType, option.city);
            // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索城市（空字符串代表全国）
            query.setPageSize(option.pageSize);// 设置每页最多返回多少条poiitem
            query.setPageNum(option.pageNumber);// 设置查第一页

            PoiSearch poiSearch = new PoiSearch(context, query);
            if (option.location != null) {
                LatLonPoint center = new LatLonPoint(option.location.getLatitude(), option.location.getLongitude());
                poiSearch.setBound(new PoiSearch.SearchBound(center, option.radius, true));
                // 设置搜索区域为以lp点为圆心，其周围5000米范围
                poiSearch.setOnPoiSearchListener(gdListener);
                poiSearch.searchPOIAsyn();// 异步搜索
            }
        }
    }

    /**
     * 释放该poi查询对象
     */
    public void destroy() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdSearch.destroy();
        }
    }

    OnGetPoiSearchResultListener bdListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(com.baidu.mapapi.search.poi.PoiResult result) {
            if (listener == null) {
                return;
            }
            PoiSearchResult poiResult = new PoiSearchResult();
            poiResult.bdResult = result;
            poiResult.resultType = PoiSearchResult.BD_RESULT;
            if (result == null) {
                poiResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            } else if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                poiResult.errorCode = MapSearchResult.NO_ERROR;
                poiResult.pageCount = result.getTotalPageNum();
                List<PoiInfo> poiInfos = result.getAllPoi();
                if (poiInfos != null) {
                    List<MapPoiItem> mapPoiItems = new ArrayList<>();
                    for (PoiInfo poiInfo : poiInfos) {
                        mapPoiItems.add(ConvertGeoCode.convertBdPoiInfo(poiInfo));
                    }
                    poiResult.poiList = mapPoiItems;
                }
            } else {
                poiResult.errorCode = MapSearchResult.ERROR;
            }

//            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
//                // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
//            }

            listener.onPoiItemSearched(poiResult);
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult result) {
            if (result.error != SearchResult.ERRORNO.NO_ERROR) {
                //"抱歉，未找到结果"
            } else {
//                result.getName();
//                result.getAddress();
            }
        }
    };

    PoiSearch.OnPoiSearchListener gdListener = new PoiSearch.OnPoiSearchListener() {
        @Override
        public void onPoiItemSearched(PoiItem arg0, int arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPoiSearched(PoiResult result, int rcode) {
            if (listener == null) {
                return;
            }
            PoiSearchResult poiResult = new PoiSearchResult();
            poiResult.gdResult = result;
            poiResult.resultType = PoiSearchResult.GD_RESULT;
            if (rcode != 1000) {
                poiResult.errorCode = MapSearchResult.ERROR;
            } else if (result == null || result.getQuery() == null) {
                poiResult.errorCode = MapSearchResult.RESULT_NOT_FOUND;
            } else {
                poiResult.errorCode = MapSearchResult.NO_ERROR;
                poiResult.pageCount = result.getPageCount();
                List<PoiItem> poiInfos = result.getPois();
                if (poiInfos != null) {
                    List<MapPoiItem> mapPoiItems = new ArrayList<>();
                    for (PoiItem poiItem : poiInfos) {
                        mapPoiItems.add(ConvertGeoCode.convertGdPoiItem(poiItem));
                    }
                    poiResult.poiList = mapPoiItems;
                }
            }
//            List<SuggestionCity> suggestionCities = poiResult
//                    .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
            listener.onPoiItemSearched(poiResult);
        }
    };

}
