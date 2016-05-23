package com.xtc.map.overlay;

import android.support.annotation.NonNull;

import com.amap.api.services.core.PoiItem;
import com.xtc.map.MapManager;
import com.xtc.map.search.PoiSearchResult;

import java.util.List;

/**
 * Poi图层类。
 * <p/>
 * Created by hzj on 2016/5/23.
 */
public class PoiOverlay {

    private MapManager mapManager;

    private PoiSearchResult poiResult;

    private com.amap.api.maps.overlay.PoiOverlay gdPoiOverlay;
    private com.xtc.map.baidu.overlayutil.PoiOverlay bdPoiOverlay;

    public PoiOverlay(@NonNull MapManager mapManager, @NonNull PoiSearchResult poiResult) {
        this.mapManager = mapManager;
        this.poiResult = poiResult;
        init();
    }

    private void init() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_AMAP) {
            List<PoiItem> poiItems = null;
            if (poiResult.getGdResult() != null) {
                poiItems = poiResult.getGdResult().getPois();
            }
            gdPoiOverlay = new com.amap.api.maps.overlay.PoiOverlay(
                    mapManager.getGdMap(), poiItems);
        } else {
            bdPoiOverlay = new com.xtc.map.baidu.overlayutil.PoiOverlay(mapManager.getBdMap());
            bdPoiOverlay.setData(poiResult.getBdResult());
        }
    }

    /**
     * 将所有Overlay 添加到地图上
     */
    public void addToMap() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_AMAP) {
            gdPoiOverlay.addToMap();
        } else {
            bdPoiOverlay.addToMap();
        }
    }

    /**
     * 将所有Overlay 从 地图上消除
     */
    public void removeFromMap() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_AMAP) {
            gdPoiOverlay.removeFromMap();
        } else {
            bdPoiOverlay.removeFromMap();
        }
    }

    /**
     * 缩放地图，使所有Overlay都在合适的视野内
     * <p>
     * 注： 该方法只对Marker类型的overlay有效
     * </p>
     */
    public void zoomToSpan() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_AMAP) {
            gdPoiOverlay.zoomToSpan();
        } else {
            bdPoiOverlay.zoomToSpan();
        }
    }
}
