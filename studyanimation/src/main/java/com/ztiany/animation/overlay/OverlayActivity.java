package com.ztiany.animation.overlay;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.ztiany.animation.R;

/**
 * API 18 View的Overlay demo ，更加方便的实现越界动画
 * @see {http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0130/2384.html}
 */
public class OverlayActivity extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        findViews();
        initListener();
    }

    private void findViews() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
    }

    private void initListener() {

        mButton1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                final ViewGroup contentContainer = (ViewGroup) mButton1.getParent().getParent();
                contentContainer.getOverlay().add(mButton1);

                ObjectAnimator anim = ObjectAnimator.ofFloat(mButton1, "translationY", 0F,contentContainer.getHeight(),0F);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(mButton1, "rotation", 0, 360);


                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        contentContainer.getOverlay().remove(mButton1);
                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        contentContainer.getOverlay().remove(mButton1);
                    }
                });
                AnimatorSet set = new AnimatorSet();

                set.setDuration(2000);
                anim.setRepeatCount(3);
                rotate.setRepeatCount(3);
                anim.setRepeatMode(Animation.REVERSE);
                rotate.setRepeatMode(Animation.REVERSE);
                set.playTogether(anim, rotate);
                set.start();
            }
        });


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup container = (ViewGroup) mButton2.getParent().getParent();
                ObjectAnimator anim = ObjectAnimator.ofFloat(mButton2, "translationY", -container.getHeight());
                anim.setDuration(2000);
                anim.start();
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mButton3, "alpha", 1f, 0f);
                fadeOut.setDuration(500);


                final ViewGroup container = (ViewGroup) mButton2.getParent();
                final ObjectAnimator anim = ObjectAnimator.ofFloat(mButton3, "translationY", -container.getHeight() * 2);
                anim.setDuration(2000);

                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) { }

                    @Override
                    public void onAnimationRepeat(Animator animation) { }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        container.getOverlay().remove(mButton3);
                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        container.getOverlay().remove(mButton3);
                    }
                });

                fadeOut.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator arg0) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator arg0) {
                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        container.getOverlay().add(mButton3);
                        mButton3.setAlpha(1f);
                        anim.start();
                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onAnimationCancel(Animator arg0) {
                        container.getOverlay().add(mButton3);
                        mButton3.setAlpha(1f);
                        anim.start();
                    }
                });

                fadeOut.start();

            }
        });

    }


}
