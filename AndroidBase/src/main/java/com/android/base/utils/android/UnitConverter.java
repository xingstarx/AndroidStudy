package com.android.base.utils.android;


import com.android.base.utils.BaseUtils;

/**
 * UnitConverter helps to convert dp or sp size into pixel.
 *
 * @author Leonardo Taehwan Kim
 */
public class UnitConverter {

    // No Instance
    private UnitConverter() {
    }

    public static float dpToPx(float dp) {
        return dp * BaseUtils.getDisplayMetrics().density;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * BaseUtils.getDisplayMetrics().density + 0.5f);
    }

    public static float pxToDp(float px) {
        return px / BaseUtils.getDisplayMetrics().density;
    }

    public static int pxToDp(int px) {
        return (int) (px / BaseUtils.getDisplayMetrics().density + 0.5f);
    }

    public static float spToPx(float sp) {
        return sp * BaseUtils.getDisplayMetrics().scaledDensity;
    }

    public static int spToPx(int sp) {
        return (int) (sp * BaseUtils.getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static float pxToSp(float px) {
        return px / BaseUtils.getDisplayMetrics().scaledDensity;
    }

    public static int pxToSp(int px) {
        return (int) (px / BaseUtils.getDisplayMetrics().scaledDensity + 0.5f);
    }
}