package com.ztiany.android.share_element;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.View;

import com.ztiany.android.R;

public class ShareElementDemoActivity extends AppCompatActivity implements GridLayoutFragment.GridFragmentCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_demo);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.act_share_container_rl, GridLayoutFragment.newInstance(), GridLayoutFragment.class.getName())
                    .commit();
        }


    }

    @Override
    public void onOpenDetailFragment(View view, int drawableId) {
        DetailFragment detailFragment = DetailFragment.newInstance(drawableId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailFragment.setSharedElementEnterTransition(new DetailsTransition());

            detailFragment.setEnterTransition(new Slide());
            detailFragment .setReturnTransition(new Explode());


            getSupportFragmentManager()
                    .findFragmentByTag(GridLayoutFragment.class.getName())
                    .setExitTransition(new Slide());

            getSupportFragmentManager()
                    .findFragmentByTag(GridLayoutFragment.class.getName())
                    .setReenterTransition(new Explode());

            detailFragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, DetailFragment.TRANS_NAME)
                .replace(R.id.act_share_container_rl, detailFragment, DetailFragment.class.getName())
                .addToBackStack(DetailFragment.class.getName())
                .commit();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private class DetailsTransition extends TransitionSet {
        DetailsTransition() {
            init();
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void init() {
            setOrdering(ORDERING_TOGETHER);

                     addTransition( new ChangeBounds()).

                    addTransition(new ChangeTransform()).

                    addTransition(new ChangeImageTransform());
        }
    }

}
