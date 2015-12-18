package com.fresco.androidutil.widgt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ling.He on 15/11/11.
 * 自适应高度的StaggeredGridLayoutManager，用于RecyclerView Wrap Content功能: RecyclerView设置height为wrap_content时，不会根据内容计算高度
 */
public class AdaptiveHeightStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    private RecyclerView.Adapter mAdapter;
    public AdaptiveHeightStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AdaptiveHeightStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        mAdapter = adapter;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        try {
            View view = recycler.getViewForPosition(0);
            if (view != null && mAdapter != null ) {
                measureChild(view, widthSpec, heightSpec);
                int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                int measuredHeight = view.getMeasuredHeight();
                //求出行数
                int line = mAdapter.getItemCount() / getSpanCount();
                if (mAdapter.getItemCount() % getSpanCount() > 0) line++;
                setMeasuredDimension(measuredWidth, measuredHeight * line);
            } else {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        } catch (Exception e) {
            super.onMeasure(recycler,state,widthSpec,heightSpec);
        }
    }
}
