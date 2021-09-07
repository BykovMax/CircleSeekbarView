package max.bykov.seekbar;

// copy from https://github.com/yuriy-budiyev/circular-progress-bar/blob/master/src/main/java/com/budiyev/android
// /circularprogressbar/CircularProgressBar.java
/*
 * MIT License
 *
 * Copyright (c) 2017 Yuriy Budiyev [yuriy.budiyev@yandex.ru]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Circular progress bar
 */
public final class CircularProgressBar extends View {


    public CircularProgressBar(@NonNull final Context context) {
        super(context);
        //        initialize(context, null, 0, 0);
    }

    public CircularProgressBar(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        //        initialize(context, attrs, 0, 0);
    }

    public CircularProgressBar(@NonNull final Context context, @Nullable final AttributeSet attrs,
                               final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //        initialize(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public CircularProgressBar(@NonNull final Context context, @Nullable final AttributeSet attrs,
                               final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //        initialize(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        Paint p = new Paint();
        //        p.setColor(Color.RED);
        //        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_name);
        //        canvas.drawBitmap(b, 0, 0, p);
        //        canvas.save();
        //        Drawable drawable=  getResources().getDrawable(R.drawable.toggle_gradient);
        //        Drawable drawable=  getResources().getDrawable(R.drawable.shape);
        //        Drawable drawable=  getResources().getDrawable(R.drawable.oval);
        //        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
        //                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA });
        //        drawable.setShape(GradientDrawable.RECTANGLE);
        //        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        //        drawable.setCornerRadius(40);
        //        drawable.setStroke(10, Color.BLACK, 20, 5);
        //        Bitmap b= BitmapFactory.decode
        //        ColorDrawable drawable=new ColorDrawable(Color.RED);
        //        drawable.setBounds(20, 0, 500, 500);
        //        drawable.draw(canvas);
        //         canvas.restore();
       /* Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(150, 200);
        path.lineTo(50, 200);
        path.close();
        path.moveTo(300f,50);
//        path.o

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setShadowLayer(30f, 40f, 40f, Color.GRAY);
//        setLayerType(LAYER_TYPE_SOFTWARE, p);
        canvas.drawPath(path,p);*/


    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //        final int defaultSize = mDefaultSize;
        final int defaultWidth = Math.max(getSuggestedMinimumWidth(), 100);
        final int defaultHeight = Math.max(getSuggestedMinimumHeight(), 100);
        final int width;
        final int height;
        switch (widthMode) {
            case MeasureSpec.EXACTLY: {
                width = widthSize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                width = Math.min(defaultWidth, widthSize);
                break;
            }
            case MeasureSpec.UNSPECIFIED:
            default: {
                width = defaultWidth;
                break;
            }
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY: {
                height = heightSize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                height = Math.min(defaultHeight, heightSize);
                break;
            }
            case MeasureSpec.UNSPECIFIED:
            default: {
                height = defaultHeight;
                break;
            }
        }
        setMeasuredDimension(width, height);
        //        invalidateDrawRect(width, height);
    }




    @Override
    protected void onSizeChanged(final int width, final int height, final int oldWidth, final int oldHeight) {
        //        invalidateDrawRect(width, height);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //        mVisible = true;
        //        if (mIndeterminate) {
        //            startIndeterminateAnimations();
        //        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //        mVisible = false;
        //        cancelIndeterminateAnimations();
        //        cancelProgressAnimation();
    }

    //    private void initialize(@NonNull final Context context, @Nullable final AttributeSet attributeSet,
    //                            @AttrRes final int defStyleAttr, @StyleRes final int defStyleRes) {
    //        mForegroundStrokePaint.setStyle(Paint.Style.STROKE);
    //        mBackgroundStrokePaint.setStyle(Paint.Style.STROKE);
    //        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    //        mDefaultSize = Math.round(DEFAULT_SIZE_DP * displayMetrics.density);
    //        if (attributeSet == null) {
    //            mMaximum = DEFAULT_MAXIMUM;
    //            mProgress = DEFAULT_PROGRESS;
    //            mStartAngle = DEFAULT_START_ANGLE;
    //            mIndeterminateMinimumAngle = DEFAULT_INDETERMINATE_MINIMUM_ANGLE;
    //            mProgressAnimator.setDuration(DEFAULT_PROGRESS_ANIMATION_DURATION);
    //            mIndeterminate = DEFAULT_INDETERMINATE;
    //            mAnimateProgress = DEFAULT_ANIMATE_PROGRESS;
    //            mDrawBackgroundStroke = DEFAULT_DRAW_BACKGROUND_STROKE;
    //            mForegroundStrokePaint.setColor(DEFAULT_FOREGROUND_STROKE_COLOR);
    //            mForegroundStrokePaint
    //                    .setStrokeWidth(Math.round(DEFAULT_FOREGROUND_STROKE_WIDTH_DP * displayMetrics.density));
    //            mForegroundStrokePaint.setStrokeCap(getStrokeCap(DEFAULT_FOREGROUND_STROKE_CAP));
    //            mBackgroundStrokePaint.setColor(DEFAULT_BACKGROUND_STROKE_COLOR);
    //            mBackgroundStrokePaint
    //                    .setStrokeWidth(Math.round(DEFAULT_BACKGROUND_STROKE_WIDTH_DP * displayMetrics.density));
    //            mIndeterminateRotationAnimator.setDuration(DEFAULT_INDETERMINATE_ROTATION_ANIMATION_DURATION);
    //            mIndeterminateSweepAnimator.setDuration(DEFAULT_INDETERMINATE_SWEEP_ANIMATION_DURATION);
    //        } else {
    //            TypedArray attributes = null;
    //            try {
    //                attributes = context.getTheme()
    //                        .obtainStyledAttributes(attributeSet, R.styleable.CircularProgressBar, defStyleAttr,
    //                                defStyleRes);
    //                setMaximum(attributes.getFloat(R.styleable.CircularProgressBar_maximum, DEFAULT_MAXIMUM));
    //                setProgress(attributes.getFloat(R.styleable.CircularProgressBar_progress, DEFAULT_PROGRESS));
    //                setStartAngle(attributes.getFloat(R.styleable.CircularProgressBar_startAngle,
    //                DEFAULT_START_ANGLE));
    //                setIndeterminateMinimumAngle(attributes
    //                        .getFloat(R.styleable.CircularProgressBar_indeterminateMinimumAngle,
    //                                DEFAULT_INDETERMINATE_MINIMUM_ANGLE));
    //                setProgressAnimationDuration(attributes
    //                        .getInteger(R.styleable.CircularProgressBar_progressAnimationDuration,
    //                                DEFAULT_PROGRESS_ANIMATION_DURATION));
    //                setIndeterminateRotationAnimationDuration(attributes
    //                        .getInteger(R.styleable.CircularProgressBar_indeterminateRotationAnimationDuration,
    //                                DEFAULT_INDETERMINATE_ROTATION_ANIMATION_DURATION));
    //                setIndeterminateSweepAnimationDuration(attributes
    //                        .getInteger(R.styleable.CircularProgressBar_indeterminateSweepAnimationDuration,
    //                                DEFAULT_INDETERMINATE_SWEEP_ANIMATION_DURATION));
    //                setForegroundStrokeColor(attributes.getColor(R.styleable
    //                .CircularProgressBar_foregroundStrokeColor,
    //                        DEFAULT_FOREGROUND_STROKE_COLOR));
    //                setBackgroundStrokeColor(attributes.getColor(R.styleable
    //                .CircularProgressBar_backgroundStrokeColor,
    //                        DEFAULT_BACKGROUND_STROKE_COLOR));
    //                setForegroundStrokeWidth(attributes.getDimension(R.styleable
    //                .CircularProgressBar_foregroundStrokeWidth,
    //                        Math.round(DEFAULT_FOREGROUND_STROKE_WIDTH_DP * displayMetrics.density)));
    //                setForegroundStrokeCap(getStrokeCap(attributes
    //                        .getInt(R.styleable.CircularProgressBar_foregroundStrokeCap,
    //                        DEFAULT_FOREGROUND_STROKE_CAP)));
    //                setBackgroundStrokeWidth(attributes.getDimension(R.styleable
    //                .CircularProgressBar_backgroundStrokeWidth,
    //                        Math.round(DEFAULT_BACKGROUND_STROKE_WIDTH_DP * displayMetrics.density)));
    //                setAnimateProgress(attributes
    //                        .getBoolean(R.styleable.CircularProgressBar_animateProgress, DEFAULT_ANIMATE_PROGRESS));
    //                setDrawBackgroundStroke(attributes.getBoolean(R.styleable
    //                .CircularProgressBar_drawBackgroundStroke,
    //                        DEFAULT_DRAW_BACKGROUND_STROKE));
    //                setIndeterminate(
    //                        attributes.getBoolean(R.styleable.CircularProgressBar_indeterminate,
    //                        DEFAULT_INDETERMINATE));
    //            } finally {
    //                if (attributes != null) {
    //                    attributes.recycle();
    //                }
    //            }
    //        }
    //        mProgressAnimator.setInterpolator(new DecelerateInterpolator());
    //        mProgressAnimator.addUpdateListener(new ProgressUpdateListener());
    //        mIndeterminateRotationAnimator.setFloatValues(360f);
    //        mIndeterminateRotationAnimator.setRepeatMode(ValueAnimator.RESTART);
    //        mIndeterminateRotationAnimator.setRepeatCount(ValueAnimator.INFINITE);
    //        mIndeterminateRotationAnimator.setInterpolator(new LinearInterpolator());
    //        mIndeterminateRotationAnimator.addUpdateListener(new StartUpdateListener());
    //        mIndeterminateSweepAnimator.setFloatValues(360f - mIndeterminateMinimumAngle * 2f);
    //        mIndeterminateSweepAnimator.setInterpolator(new DecelerateInterpolator());
    //        mIndeterminateSweepAnimator.addUpdateListener(new SweepUpdateListener());
    //        mIndeterminateSweepAnimator.addListener(new SweepAnimatorListener());
    //    }


}