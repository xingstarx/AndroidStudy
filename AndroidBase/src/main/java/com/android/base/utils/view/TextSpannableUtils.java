package com.android.base.utils.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.text.style.TabStopSpan;

import java.util.Arrays;

/**
 * @author Ztiany
 *         email 1169654504@qq.com & ztiany3@gmail.com
 *         date 2015-12-29 11:38
 *         description
 *         vsersion
 */
public class TextSpannableUtils {

    private TextSpannableUtils() {
        throw new AssertionError();
    }

    private static final String HTML_BR = "<br/>";



    public static String getHtmlFontText(String prefix, String formatText, String suffix, String color) {
        return prefix + "<font color=\"" + color + "\">" + formatText + "</font>" + suffix;
    }

    /**
     * @param content            需要修饰的内容
     * @param backgroundColor    设置的背景色
     * @param textColor          设置的文字色
     * @param needBackgroundText 需要设置背景色的文字(必须包含在content中)
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getBackgroundSpan(String content, @ColorInt int backgroundColor, @ColorInt int textColor, String needBackgroundText) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        int indexYou = content.indexOf(needBackgroundText);
        ssb.setSpan(new BackgroundColorSpan(backgroundColor), indexYou, indexYou + needBackgroundText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(textColor), indexYou, indexYou + needBackgroundText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }


    /**
     * @param content            需要修饰的内容
     * @param backgroundColor    设置的背景色
     * @param textColor          设置的文字色
     * @param needBackgroundText 需要设置背景色的文字(必须包含在content中)
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getRoundBackgroundSpan(String content, @ColorInt int backgroundColor, @ColorInt int textColor, int padding, String needBackgroundText) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        int indexYou = content.indexOf(needBackgroundText);
        ssb.setSpan(new RoundedBackgroundSpan(backgroundColor, textColor, padding), indexYou, indexYou + needBackgroundText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }


    /**
     * @param content     内容文本
     * @param prefixColor 着色的前缀文本
     * @param suffixColor 前缀颜色
     * @param prefixText  着色的后缀文本
     * @param suffixText  后缀颜色
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getColorTextSpan(String content, @ColorInt int prefixColor, @ColorInt int suffixColor, String prefixText, String suffixText) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        int indexPrefix = content.indexOf(prefixText);
        int indexSuffix = content.indexOf(suffixText);


        ssb.setSpan(new ForegroundColorSpan(prefixColor), indexPrefix, indexPrefix + prefixText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(suffixColor), indexSuffix, indexSuffix + suffixText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }


    /**
     * 获取一个多行显示的文本
     *
     * @param offset 文本左边偏移量
     * @param lines  字符串数组，一个表示一行
     * @return SpannableStringBuilder
     */
    public static SpannableStringBuilder getMultiLineSpan(int offset, String... lines) {

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (String s : lines) {
            ssb.append("\t").append(s).append(" ");
            ssb.append("\n");
        }
        ssb.setSpan(new TabStopSpan.Standard(offset), 0, ssb.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return ssb;
    }


    /**
     * 获取一个多行显示的文本
     *
     * @param lines 字符串数组，一个表示一行
     * @return SpannableStringBuilder
     */
    public static Spanned getMultiLineSpan(String... lines) {

        StringBuilder sb = new StringBuilder();
        int temp = 0;
        int size = lines.length;
        for (String s : lines) {
            sb.append(s);
            if (++temp < size)
                sb.append(HTML_BR);
        }
        return Html.fromHtml(sb.toString());
    }


    /**
     * 获取字符串的缩放文本
     *
     * @param maxLength 最长显示多少
     * @param replace   超出文本的替换文本
     * @param content   内容
     * @return
     */
    public static String getAbbreviationsString(int maxLength, String replace, String content) {
        if (TextUtils.isEmpty(content)) {
            return content;
        }
        char[] chars = content.toCharArray();
        int length = chars.length;
        if (length > maxLength) {
            chars = Arrays.copyOf(chars, maxLength);
            return new String(chars).concat(replace);
        }

        return content;
    }

    /**
     * 圆角背景Span
     */
    private static class RoundedBackgroundSpan extends ReplacementSpan {
        private int mPadding = 0;
        private int mBackgroundColor;
        private int mTextColor;

        public RoundedBackgroundSpan(int backgroundColor, int textColor, int padding) {
            super();
            mBackgroundColor = backgroundColor;
            mTextColor = textColor;
            mPadding = padding;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            return (int) (mPadding + paint.measureText(text.subSequence(start, end).toString()) + mPadding);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            float width = paint.measureText(text.subSequence(start, end).toString());
            RectF rect = new RectF(x, top, x + width + (2 * mPadding), bottom);
            paint.setColor(mBackgroundColor);
            canvas.drawRoundRect(rect, 10, 10, paint);
            paint.setColor(mTextColor);
            canvas.drawText(text, start, end, x + mPadding, y, paint);
        }
    }
}

