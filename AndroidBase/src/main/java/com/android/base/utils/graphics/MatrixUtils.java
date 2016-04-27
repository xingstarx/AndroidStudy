package com.android.base.utils.graphics;

import android.graphics.Matrix;

/**
 * author Ztiany                                                                        <br/>
 * email 1169654504@qq.com & ztiany3@gmail.com           <br/>
 * date 2016-04-27 12:53                                                       <br/>
 * description                                                                             <br/>
 * version
 */
public class MatrixUtils {
    private MatrixUtils() {

    }

    public static void transformByCenter(Matrix matrix ,float centerX, float centerY) {
        matrix.postTranslate(centerX, centerY);
        matrix.preTranslate(-centerX, -centerY);
    }
}
