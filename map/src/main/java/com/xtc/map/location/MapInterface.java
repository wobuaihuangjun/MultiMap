package com.xtc.map.location;

import android.location.Location;
import android.view.MotionEvent;

import com.amap.api.maps.model.Polyline;
import com.xtc.map.MapLatLng;
import com.xtc.map.MapPoi;
import com.xtc.map.overlay.MapMarker;
import com.xtc.map.status.MapCamera;

/**
 * 地图事件接口
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapInterface {

    public interface OnPOIClickListener {
        void onPOIClick(MapPoi var1);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent var1);
    }

    public interface OnMapClickListener {
        void onMapClick(MapLatLng var1);
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(MapLatLng var1);
    }

    public interface OnMapStatusChangeListener {
        void onMapStatusChange(MapCamera var1);

        void onMapStatusChangeFinish(MapCamera var1);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(MapMarker var1);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline var1);
    }

    public interface OnMarkerDragListener {
        void onMarkerDragStart(MapMarker var1);

        void onMarkerDrag(MapMarker var1);

        void onMarkerDragEnd(MapMarker var1);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(MapMarker var1);
    }

    public interface CancelableCallback {
        void onFinish();

        void onCancel();
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location var1);
    }

}
