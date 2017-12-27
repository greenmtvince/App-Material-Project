package com.example.xyzreader.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Vinnie on 12/21/2017.
 */

//FAB Animation explanation found at:
//https://guides.codepath.com/android/floating-action-buttons
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs){
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes){
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,target,nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed){
        super.onNestedScroll(coordinatorLayout,child,target,dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed);
        if (dyConsumed>0&& child.getVisibility()==View.VISIBLE){

            //This workaround to a Coordinator Layout Bug found at
            //https://stackoverflow.com/questions/41761736/android-design-library-25-1-0-causes-
            // floatingactionbutton-behavior-to-stop-worki/42082313#42082313
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        } else if (dyConsumed<0 ){//&& child.getVisibility()==View.INVISIBLE){
            child.show();
        }
    }
}
