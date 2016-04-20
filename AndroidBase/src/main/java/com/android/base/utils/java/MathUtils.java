package com.android.base.utils.java;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-20 23:07      <br/>
 * Description：
 */
public class MathUtils {
    private MathUtils() {

    }


    /**
     * 角度 转换 弧度
     * @param angle 角度
     * @return 弧度
     */
    public static float angleToRadian(float angle) {
        return (float) (angle * (Math.PI / 180));
    }

    /**
     *  弧度 转换 角度
     * @param radian 弧度
     * @return 角度
     */
    public static float radianToAngle(float radian) {
        return (float) (radian * (180 / Math.PI));
    }
}
