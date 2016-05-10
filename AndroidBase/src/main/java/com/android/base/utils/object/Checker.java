package com.android.base.utils.object;

import java.util.Collection;
import java.util.Map;

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2015-11-11 11:39
 *         description 对象检查工具
 *         vsersion
 */
public class Checker {

    private Checker() {

    }

    public static final   boolean isEmpty(Collection<?> data) {
        return data == null ||  data.size() == 0;
    }

    public static final boolean isNull(Object o) {
        return o == null;
    }

    public static final boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }


    public static final <T> boolean isEmpty(T[] t) {
        return t == null || t.length == 0;
    }



}
