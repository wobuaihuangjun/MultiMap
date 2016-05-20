package com.xtc.map.search;

/**
 * 地理编码/反地理编码结果
 * <p/>
 * Created by hzj on 2016/5/17.
 */
public interface OnCoderResultListener {

    void onGeocodeSearched(CodeResult codeResult);

    void onRegeocodeSearched(ReCodeResult reCodeResult);
}
