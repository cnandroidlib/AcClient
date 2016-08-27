package thereisnospon.acclient;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import java.util.List;

import thereisnospon.acclient.widget.BottomsheetLayout;

/**
 * Created by yzr on 16/8/25.
 */
public class FabBehavior extends FloatingActionButton.Behavior {



    private boolean isBottomSheetLayout(View view){
        return view instanceof BottomsheetLayout;
    }


    public FabBehavior(Context context, AttributeSet attrs) {
        super();
    }


    private boolean isAnimatingOut = false;

    ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOut = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency)||isBottomSheetLayout(dependency);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        boolean superRet=super.onDependentViewChanged(parent, child, dependency);
        if (isBottomSheetLayout(dependency))
            updateForBottomSheetLayout(parent,child);
        return superRet;
    }


    ValueAnimator mFabTranslationYAnimator;
    ValueAnimator roateAnimation;
    private static Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR=new FastOutSlowInInterpolator();

    private float mFabTranslationY;
    private void updateForBottomSheetLayout(CoordinatorLayout parent,final FloatingActionButton fab){
        final float targetTransY = getFabTranslationYForBottomSheetLayout(parent, fab);

        Logger.d(" m :"+mFabTranslationY+" t: "+targetTransY);

        if (mFabTranslationY == targetTransY) {
            // We're already at (or currently animating to) the target value, return...
            return;
        }

        final float currentTransY = ViewCompat.getTranslationY(fab);
        if (mFabTranslationYAnimator != null && mFabTranslationYAnimator.isRunning()) {
            mFabTranslationYAnimator.cancel();
        }

        if(roateAnimation!=null&&roateAnimation.isRunning()){
            roateAnimation.cancel();
        }

        if (fab.isShown()
                && Math.abs(currentTransY - targetTransY) > (fab.getHeight() * 0.667f)) {
            // If the FAB will be travelling by more than 2/3 of it's height, let's animate
            // it instead
            if (mFabTranslationYAnimator == null) {
                mFabTranslationYAnimator = new ValueAnimator();
                mFabTranslationYAnimator.setInterpolator(
                        FAST_OUT_SLOW_IN_INTERPOLATOR);
                mFabTranslationYAnimator.addUpdateListener(
                        new  ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animatior) {
                                ViewCompat.setTranslationY(fab,
                                        (float)animatior.getAnimatedValue());
                            }
                        });
            }
            if(roateAnimation==null){
                roateAnimation=new ValueAnimator();
                roateAnimation.setInterpolator(new FastOutSlowInInterpolator());
                roateAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ViewCompat.setRotation(fab,(float)animation.getAnimatedValue());
                    }
                });
            }
            float t=ViewCompat.getRotation(fab);
            roateAnimation.setFloatValues(t,t+90);
            roateAnimation.start();
            mFabTranslationYAnimator.setFloatValues(currentTransY, targetTransY);
            mFabTranslationYAnimator.start();
        } else {
            // Now update the translation Y
            ViewCompat.setTranslationY(fab, targetTransY);

        }

        mFabTranslationY = targetTransY;


    }

    private float getFabTranslationYForBottomSheetLayout(CoordinatorLayout parent,
                                                         FloatingActionButton fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (isBottomSheetLayout(view) && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset,
                        ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        return minOffset;
    }



    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        int slop=5;
        if (((dyConsumed > slop&& dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed > slop)) && child.getVisibility() == View.VISIBLE&&!isAnimatingOut) {// 显示
            scaleHide(child,viewPropertyAnimatorListener);
        } else if (((dyConsumed < -slop && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed < -slop)) && child.getVisibility() != View.VISIBLE) {
            scaleShow(child,null);
        }

    }


    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, float velocityX, float velocityY, boolean consumed) {


        return true;
    }

    public static void scaleShow(View view, ViewPropertyAnimatorListener listener){
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(listener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start();
    }
    public static void scaleHide(View view,ViewPropertyAnimatorListener listener){
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(listener)
                .start();
    }
}