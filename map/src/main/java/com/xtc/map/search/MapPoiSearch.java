package com.xtc.map.search;

import android.content.Context;

import com.baidu.mapapi.search.poi.PoiSearch;
import com.xtc.map.MapManager;

/**
 * POI检索接口
 * Created by hzj on 2016/5/20.
 */
public class MapPoiSearch {

    private Context context;
    private int currentMapType;

    private PoiSearch bdSearch;
    private com.amap.api.services.poisearch.PoiSearch gdSearch;

    private OnCoderResultListener listener;

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
            bdSearch = PoiSearch.newInstance();
        } else {
//            gdSearch = new com.amap.api.services.poisearch.PoiSearch(context);
        }
    }

}
