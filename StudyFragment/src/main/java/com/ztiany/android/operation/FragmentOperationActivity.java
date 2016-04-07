package com.ztiany.android.operation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.android.base.ui.BaseActivity;
import com.android.base.utils.java.Checker;
import com.ztiany.android.R;

import java.lang.reflect.Field;
import java.util.List;

public class FragmentOperationActivity extends BaseActivity {

    private static final String TAG = FragmentOperationActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;


    private static final int mLayoutId = R.id.act_operation_container_fl;


    private boolean mAddToStack;
    private boolean mUseAnim;
    private boolean mUseAllAnim;

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

        CheckBox checkBox = findView(R.id.act_opt_to_stack_cb);
        CheckBox checkBoxAnim = findView(R.id.act_opt_anim_cb);
        CheckBox checkBoxAllAnim = findView(R.id.act_opt_all_anim_cb);
        RadioGroup radioGroup = findView(R.id.act_opt_rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAddToStack = isChecked;
            }

        });

        checkBoxAnim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUseAnim = isChecked;
                if (mUseAnim) {
                    mUseAllAnim = false;
                }
                Log.d(TAG, "mUseAnim:" + mUseAnim);
                Log.d(TAG, "mUseAllAnim:" + mUseAllAnim);
            }
        });

        checkBoxAllAnim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUseAllAnim = isChecked;
                if (mUseAllAnim) {
                    mUseAnim = false;
                }
                Log.d(TAG, "mUseAnim:" + mUseAnim);
                Log.d(TAG, "mUseAllAnim:" + mUseAllAnim);
            }
        });


        String classPath = "android.support.v4.app.FragmentManagerImpl";
        try {
            Class<?> aClass = Class.forName(classPath);
            Field debug = aClass.getDeclaredField("DEBUG");
            debug.setAccessible(true);
            Log.d(TAG, "debug:" + debug);
            try {
                debug.setBoolean(null, false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        boolean destroyed = mFragmentManager.isDestroyed();
        Log.d(TAG, "destroyed:" + destroyed);
    }


    public void add(View view) {
        FragmentTransaction add = mFragmentManager.beginTransaction();

        if (mUseAllAnim) {
            add.setCustomAnimations(R.anim.anim_in, R.anim.anim_out, R.anim.anim_bottom_in, R.anim.anim_bottom_out);
        }
        if (mUseAnim) {
            add.setCustomAnimations(R.anim.anim_in, R.anim.anim_out);
        }

        add.add(mLayoutId, mCurrentFragment, mCurrentFragment.getClass().getName());
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
        FragmentTransaction replace = mFragmentManager.beginTransaction();


        if (mUseAllAnim) {
            replace.setCustomAnimations(R.anim.anim_in, R.anim.anim_out, R.anim.anim_bottom_in, R.anim.anim_bottom_out);

        }
        if (mUseAnim) {
            replace.setCustomAnimations(R.anim.anim_in, R.anim.anim_out);
        }
        replace.replace(mLayoutId, mCurrentFragment, mCurrentFragment.getClass().getName());
        if (mAddToStack) {
            replace.addToBackStack(mCurrentFragment.getClass().getName());
        }
        replace.commit();
    }

    public void popStack(View view) {
        int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
        final String[] strings = new String[backStackEntryCount];

        for (int i = 0; i < backStackEntryCount; i++) {
            FragmentManager.BackStackEntry backStackEntryAt = mFragmentManager.getBackStackEntryAt(i);
            strings[i] = backStackEntryAt.getName();
        }
        new AlertDialog.Builder(this)
                .setTitle("Select Name")
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFragmentManager.popBackStack(strings[which], FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        dialog.dismiss();
                    }
                })
                .show();


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
                if (fragment != null)
                    mFragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "mFragmentManager.isDestroyed():" + mFragmentManager.isDestroyed());
    }
}
