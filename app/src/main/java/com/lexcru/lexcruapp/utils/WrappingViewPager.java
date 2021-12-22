package com.lexcru.lexcruapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class WrappingViewPager extends ViewPager {

    List<Integer> childHeights = new ArrayList<>(getChildCount());
    int minHeight = 0;
    int currentPos = 0;

    public WrappingViewPager(@NonNull Context context) {
        super(context);
        setOnPageChangeListener();
    }

    public WrappingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainMinHeightAttribute(context, attrs);
        setOnPageChangeListener();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        childHeights.clear();

        //calculate child views
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h < minHeight) {
                h = minHeight;
            }
            childHeights.add(i, h);
        }

        if (childHeights.size() - 1 >= currentPos) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeights.get(currentPos), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void obtainMinHeightAttribute(@NonNull Context context, @Nullable AttributeSet attrs) {
        int[] heightAttr = new int[]{android.R.attr.minHeight};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, heightAttr);
        minHeight = typedArray.getDimensionPixelOffset(0, -666);
        typedArray.recycle();
    }

    private void setOnPageChangeListener() {
        this.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPos = position;

                ViewGroup.LayoutParams layoutParams = WrappingViewPager.this.getLayoutParams();
                layoutParams.height = childHeights.get(position);
                WrappingViewPager.this.setLayoutParams(layoutParams);
                WrappingViewPager.this.invalidate();
            }
        });
    }
}
