package com.android.base.app;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 19:53      <br/>
 * Description：Presenter基本行为
 *  应当遵守一个基本准则：一个Fragment对于一个视图，一个视图对应一个Presenter，一个复杂界面可以又多个Fragment和Presenter组成。
 */
public interface IPresenter {



    /**
     * 视图已经完全可见
     */
    void onStart();


    /**
     * 被暂停了
     */
    void onPause();

    /**
     * 完全销毁
     */
    void onDestroy();

}
