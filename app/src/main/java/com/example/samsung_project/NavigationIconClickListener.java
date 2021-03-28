package com.example.samsung_project;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import androidx.annotation.Nullable;

public class NavigationIconClickListener implements OnClickListener {
    private final AnimatorSet animatorSet;
    private Context context;
    private View sheet;
    private Interpolator interpolator;
    private int height;
    private boolean backdropShown;
    private Drawable openIcon;
    private Drawable closeIcon;

    NavigationIconClickListener(Context context, View sheet) {
        this(context, sheet, (Interpolator)null);
    }

    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator) {
        this(context, sheet, interpolator, (Drawable)null, (Drawable)null);
    }

    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator, @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.animatorSet = new AnimatorSet();
        this.backdropShown = false;
        this.context = context;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height = displayMetrics.heightPixels;
    }

    public void onClick(View view) {
        this.backdropShown = !this.backdropShown;
        this.animatorSet.removeAllListeners();
        this.animatorSet.end();
        this.animatorSet.cancel();
        this.updateIcon(view);
        @SuppressLint("ResourceType") int translateY = this.height - this.context.getResources().getDimensionPixelSize(2131099911);
        ObjectAnimator animator = ObjectAnimator.ofFloat(this.sheet, "translationY", new float[]{this.backdropShown ? (float)translateY : 0.0F});
        animator.setDuration(500L);
        if (this.interpolator != null) {
            animator.setInterpolator(this.interpolator);
        }

        this.animatorSet.play(animator);
        animator.start();
    }

    private void updateIcon(View view) {
        if (this.openIcon != null && this.closeIcon != null) {
            if (!(view instanceof ImageView)) {
                throw new IllegalArgumentException("updateIcon() must be called on an ImageView");
            }

            if (this.backdropShown) {
                ((ImageView)view).setImageDrawable(this.closeIcon);
            } else {
                ((ImageView)view).setImageDrawable(this.openIcon);
            }
        }

    }
}
