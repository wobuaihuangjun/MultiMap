package com.xtc.map.overlay;

/**
 * 边框类，可以给圆、多边形设置一个边框
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class Stroke {

    int color;//边框的颜色
    int strokeWidth;//边框的宽度， 默认为 5， 单位：像素

    public Stroke(int color, int strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
    }
}
