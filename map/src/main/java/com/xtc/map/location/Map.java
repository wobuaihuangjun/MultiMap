package com.xtc.map.location;

import android.location.Location;
import android.view.MotionEvent;

import com.amap.api.maps.model.Polyline;
import com.xtc.map.LatLng;
import com.xtc.map.status.MapStatus;
import com.xtc.map.overlay.Marker;
import com.xtc.map.Poi;

/**
 * 地图事件接口
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class Map {

    public interface OnPOIClickListener {
        void onPOIClick(Poi var1);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent var1);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng var1);
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng var1);
    }

    public interface OnMapStatusChangeListener {
        void onMapStatusChange(MapStatus var1);

        void onMapStatusChangeFinish(MapStatus var1);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker var1);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline var1);
    }

    public interface OnMarkerDragListener {
        void onMarkerDragStart(Marker var1);

        void onMarkerDrag(Marker var1);

        void onMarkerDragEnd(Marker var1);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker var1);
    }

    public interface CancelableCallback {
        void onFinish();

        void onCancel();
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location var1);
    }

}
