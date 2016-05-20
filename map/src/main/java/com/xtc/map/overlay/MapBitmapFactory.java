package com.xtc.map.overlay;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 创建BitmapDescriptor 对象
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapBitmapFactory {

    public MapBitmapFactory() {
    }

    public static MapBitmap fromAsset(Context var1, String var0) {
        if (var1 == null) {
            return null;
        } else {
            try {
                AssetManager var2 = var1.getAssets();
                InputStream var3 = null;
                var3 = var2.open(var0);
                Bitmap var4 = BitmapFactory.decodeStream(var3);
                MapBitmap var5 = null;
                var3.close();
                var5 = fromBitmap(var4);
                return var5;
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        }
    }


    public static MapBitmap fromBitmap(Bitmap var0) {
        if (var0 == null) {
            return null;
        } else {
            MapBitmap var1 = new MapBitmap(var0);
            return var1;
        }
    }

    public static MapBitmap fromFile(Context var1, String var0) {
        if (var0 != null && !var0.equals("")) {
            try {
                if (var1 != null) {
                    FileInputStream var2 = var1.openFileInput(var0);
                    Bitmap var3 = BitmapFactory.decodeStream(var2);
                    var2.close();
                    if (var3 != null) {
                        MapBitmap var4 = fromBitmap(var3);
                        return var4;
                    }
                }
            } catch (FileNotFoundException var5) {
                var5.printStackTrace();
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }

    public static MapBitmap fromPath(String var0) {
        Bitmap var1 = BitmapFactory.decodeFile(var0);
        if (var1 != null && var1 != null) {
            MapBitmap var2 = fromBitmap(var1);
            return var2;
        } else {
            return null;
        }
    }

    public static MapBitmap fromResource(Context var1, int var0) {
        if (var1 != null) {
            Bitmap var2 = BitmapFactory.decodeResource(var1.getResources(), var0);
            if (var2 == null) {
                return null;
            } else {
                MapBitmap var3 = fromBitmap(var2);
                return var3;
            }
        } else {
            return null;
        }
    }

    public static MapBitmap fromView(View var0) {
        if (var0 == null) {
            return null;
        } else {
            var0.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            var0.layout(0, 0, var0.getMeasuredWidth(), var0.getMeasuredHeight());
            var0.buildDrawingCache();
            Bitmap var1 = var0.getDrawingCache();
            MapBitmap var2 = fromBitmap(var1);
            var0.destroyDrawingCache();
            return var2;
        }
    }

}
