package com.ztiany.customview.customer.ovalmenu;

import android.view.View;

import com.ztiany.InjectFragment;
import com.ztiany.customview.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-24 2:14      <br/>
 * Description：
 */
public class OvalMenuFragment extends InjectFragment {


    @Bind(R.id.frag_oval_menu)
    protected OvalMenu mOvalMenu;


    @Override
    protected int layoutId() {
        return R.layout.fragment_oval_menu;
    }


    @OnClick(value = {
            R.id.frag_oval_menu_add_long_btn,
            R.id.frag_oval_menu_sub_long_btn,
            R.id.frag_oval_menu_add_short_btn,
            R.id.frag_oval_menu_sub_short_btn,
            R.id.frag_oval_menu_hide_show_btn,
    })
    public void perform(View view) {
        switch (view.getId()) {
            case R.id.frag_oval_menu_add_long_btn: {
                mOvalMenu.setAAxisPercentByHalfWidth(mOvalMenu.getAAxisPercentByHalfWidth() + 0.1F);
                break;
            }
            case R.id.frag_oval_menu_sub_long_btn: {
                mOvalMenu.setAAxisPercentByHalfWidth(mOvalMenu.getAAxisPercentByHalfWidth() - 0.1F);
                break;
            }
            case R.id.frag_oval_menu_add_short_btn: {
                mOvalMenu.setBAxisPercentByAAxis(mOvalMenu.getBAxisPercentByAAxis() + 0.1F);

                break;
            }
            case R.id.frag_oval_menu_sub_short_btn: {
                mOvalMenu.setBAxisPercentByAAxis(mOvalMenu.getBAxisPercentByAAxis() - 0.1F);

                break;
            }
            case R.id.frag_oval_menu_hide_show_btn: {
                mOvalMenu.setDebug(!mOvalMenu.isDebug());
                break;
            }
        }


    }
}
