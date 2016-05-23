package com.xtc.map.search;

import com.amap.api.services.poisearch.PoiResult;

import java.util.List;

/**
 * poi搜索结果
 * <p/>
 * Created by hzj on 2016/5/23.
 */
public class PoiSearchResult extends MapSearchResult {

    /**
     * 高德Poi搜索结果
     */
    public static final int GD_RESULT = 0;
    /**
     * 百度Poi搜索结果
     */
    public static final int BD_RESULT = 1;

    List<MapPoiItem> poiList;

//    List<CityInfo>

    int pageCount;

    com.baidu.mapapi.search.poi.PoiResult bdResult;

    PoiResult gdResult;

    int resultType;

    /**
     * 搜索结果类型
     *
     * @return 0：高德；1：百度
     */
    public int getResultType() {
        return resultType;
    }

    /**
     * 获取高德Poi查询结果
     *
     * @return com.amap.api.services.poisearch.PoiResult
     */
    public PoiResult getGdResult() {
        return gdResult;
    }

    /**
     * 获取百度Poi查询结果
     *
     * @return com.baidu.mapapi.search.poi.PoiResult
     */
    public com.baidu.mapapi.search.poi.PoiResult getBdResult() {
        return bdResult;
    }

    /**
     * 返回当前页所有POI结果
     * <p/>
     * 如果当前页无POI结果，返回长度为零的list。
     *
     * @return 该页所有POI结果
     */
    public List<MapPoiItem> getPoiList() {
        return poiList;
    }

    /**
     * 返回该结果的总页数
     *
     * @return 该结果的总页数。
     */
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return "PoiSearchResult{" +
                "poiList=" + poiList +
                ", pageCount=" + pageCount +
                '}';
    }
}
