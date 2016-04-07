package com.ztiany.android.operation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.android.base.ui.BaseActivity;
import com.android.base.utils.java.Checker;
import com.ztiany.android.R;

import java.util.List;

public class FragmentOperationActivity extends BaseActivity {

    private static final String TAG = FragmentOperationActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;


    private static final int mLayoutId = R.id.act_operation_container_fl;

    private RadioGroup mRadioGroup;
    private CheckBox mCheckBox;


    private boolean mAddToStack;

    private Fragment mCurrentFragment;

    private Opt1Fragment mOpt1Fragment;
    private Opt2Fragment mOpt2Fragment;
    private Opt3Fragment mOpt3Fragment;
    private Opt4Fragment mOpt4Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_operation);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mOpt1Fragment = (Opt1Fragment) mFragmentManager.findFragmentByTag(Opt1Fragment.class.getName());
            mOpt2Fragment = (Opt2Fragment) mFragmentManager.findFragmentByTag(Opt2Fragment.class.getName());
            mOpt3Fragment = (Opt3Fragment) mFragmentManager.findFragmentByTag(Opt3Fragment.class.getName());
            mOpt4Fragment = (Opt4Fragment) mFragmentManager.findFragmentByTag(Opt4Fragment.class.getName());
        }

        if (mOpt1Fragment == null) {
            mOpt1Fragment = new Opt1Fragment();
        }
        if (mOpt2Fragment == null) {
            mOpt2Fragment = new Opt2Fragment();
        }
        if (mOpt3Fragment == null) {
            mOpt3Fragment = new Opt3Fragment();
        }
        if (mOpt4Fragment == null) {
            mOpt4Fragment = new Opt4Fragment();
        }

        mCheckBox = findView(R.id.act_opt_cb);
        mRadioGroup = findView(R.id.act_opt_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.act_opt1_rbtn) {
                    mCurrentFragment = mOpt1Fragment;
                } else if (checkedId == R.id.act_opt2_rbtn) {
                    mCurrentFragment = mOpt2Fragment;
                } else if (checkedId == R.id.act_opt3_rbtn) {
                    mCurrentFragment = mOpt3Fragment;
                } else if (checkedId == R.id.act_opt4_rbtn) {
                    mCurrentFragment = mOpt4Fragment;
                }
            }
        });
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAddToStack = isChecked;
            }
        });
    }


    public void add(View view) {
        FragmentTransaction add = mFragmentManager.beginTransaction()
                .add(mLayoutId, mCurrentFragment, mCurrentFragment.getClass().getName());
        if (mAddToStack) {
            add.addToBackStack(mCurrentFragment.getClass().getName());
        }
        add.commit();
    }

    public void remove(View view) {
        mFragmentManager.beginTransaction()
                .remove(mCurrentFragment)
                .commit();
    }

    public void attach(View view) {
        //attach应该在detach之后调用，重新创建视图连接到用户界面
        mFragmentManager.beginTransaction()
                .attach(mCurrentFragment)
                .commit();
    }


    public void detach(View view) {
        mFragmentManager.beginTransaction()
                .detach(mCurrentFragment)
                .commit();
    }

    public void show(View view) {
        mFragmentManager.beginTransaction()
                .show(mCurrentFragment)
                .commit();
    }


    public void hide(View view) {
        mFragmentManager.beginTransaction()
                .hide(mCurrentFragment)
                .commit();
    }


    public void replace(View view) {
        FragmentTransaction replace = mFragmentManager.beginTransaction()
                .replace(mLayoutId, mCurrentFragment, mCurrentFragment.getClass().getName());
        if (mAddToStack) {
            replace.addToBackStack(mCurrentFragment.getClass().getName());
        }
        replace.commit();
    }

    public void printInfo(View view) {

        List<Fragment> fragments = mFragmentManager.getFragments();
        if (!Checker.isEmpty(fragments)) {
            for (Fragment fragment : fragments) {
                Log.d(TAG, "fragment->" + fragment);
            }
        }
    }

    public void clear(View view) {
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (!Checker.isEmpty(fragments)) {
            for (Fragment fragment : fragments) {
                mFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }
    }


}
