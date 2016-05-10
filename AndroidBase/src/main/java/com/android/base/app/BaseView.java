package com.android.base.app;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 19:55      <br/>
 * Description：视图抽象
 */
public interface BaseView<P extends IPresenter> {

    void setPresenter(P presenter);
}
