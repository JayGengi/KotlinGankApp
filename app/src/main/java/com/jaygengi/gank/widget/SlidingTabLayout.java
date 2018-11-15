/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaygengi.gank.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jaygengi.gank.R;
import com.jaygengi.gank.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * To be used with ViewPager to provide a tab indicator component which give constant feedback as to
 * the user's scroll progress.
 * <p>
 * To use the component, simply add it to your view hierarchy. Then in your
 * {@link android.app.Activity} or {@link android.support.v4.app.Fragment} call
 * {@link #setViewPager(ViewPager)} providing it the ViewPager this layout is being used for.
 * <p>
 * The colors can be customized in two ways. The first and simplest is to provide an array of colors
 * via {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)}. The
 * alternative is via the {@link TabColorizer} interface which provides you complete control over
 * which color is used for any individual position.
 * <p>
 * The views used as tabs can be customized by calling {@link #setCustomTabView(int, int)},
 * providing the layout ID of your custom layout.
 */
public class SlidingTabLayout extends HorizontalScrollView {

    /**
     * Allows complete control over the colors drawn in the tab layout. Set with
     * {@link #setCustomTabColorizer(TabColorizer)}.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

        /**
         * @return return the color of the divider drawn to the right of {@code position}.
         */
        int getDividerColor(int position);

    }

    private static final int TITLE_OFFSET_DIPS = 24;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 16;

    private int mTitleOffset;

    private int mTabViewLayoutId;
    private int mTabViewTextViewId;

    private Context context;
    private int screenWidth;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    private final SlidingTabStrip mTabStrip;
    private List<TextView> tabList;

    private int leftRightmargin;
    private int limitTabCount = 6;

    private OnTabItemClickListener tabItemClickListener;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;
        leftRightmargin = DensityUtil.dip2px(this.context, 20);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)this.context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        screenWidth =dm.widthPixels;

        // Disable the Scroll Bar
        setHorizontalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);

        mTitleOffset = (int) (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);

        mTabStrip = new SlidingTabStrip(context);
        addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * Set the custom {@link TabColorizer} to be used.
     *
     * If you only require simple custmisation then you can use
     * {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)} to achieve
     * similar effects.
     */
    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        mTabStrip.setCustomTabColorizer(tabColorizer);
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        mTabStrip.setSelectedIndicatorColors(colors);
    }

    /**
     * Sets the colors to be used for tab dividers. These colors are treated as a circular array.
     * Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setDividerColors(int... colors) {
        mTabStrip.setDividerColors(colors);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId) {
        mTabViewLayoutId = layoutResId;
        mTabViewTextViewId = textViewId;
    }

    public void setStripLength(int length){
        mTabStrip.setStripLength(length);
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
            adjustTabSpace();
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * {@link #setCustomTabView(int, int)}.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);

//        textView.setTypeface(Typeface.DEFAULT_BOLD);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            // If we're running on Honeycomb or newer, then we can use the Theme's
//            // selectableItemBackground to ensure that the View has a pressed state
//            TypedValue outValue = new TypedValue();
//            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
//                    outValue, true);
//            textView.setBackgroundResource(outValue.resourceId);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            // If we're running on ICS or newer, enable all-caps to match the Action Bar tab style
//            textView.setAllCaps(true);
//        }
//        int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
//        textView.setPadding(padding, padding, padding, padding);

        return textView;
    }



    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();
        tabList = new ArrayList<>();

        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = null;
            TextView tabTitleView = null;

            if (mTabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip,
                        false);
                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
            }

            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }

            tabTitleView.setText(adapter.getPageTitle(i));
            tabView.setOnClickListener(tabClickListener);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

            mTabStrip.addView(tabView, lp);
            tabList.add(tabTitleView);

        }

        setTabTextColor(tabList, mViewPager.getCurrentItem());
    }

    public void setText(ArrayList<String> list){
        tabList = new ArrayList<>();
        final OnClickListener tabClickListener = new TabClickListener();
        for (int i = 0; i < list.size(); i++) {
            View tabView = null;
            TextView tabTitleView = null;

            if (mTabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip,
                        false);
                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
            }

            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }

            tabTitleView.setText(list.get(i));
//            tabView.setOnClickListener(tabClickListener);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mTabStrip.addView(tabView, lp);
            tabList.add(tabTitleView);

        }
        adjustTabSpace();
    }


    public void setLeftRightMargin(int margin){
        this.leftRightmargin = margin;
    }

    public void setLimitTabCount(int limitTabCount) {
        this.limitTabCount = limitTabCount;
    }

    /**
     * 动态调节每一个标签宽度， 小于6个标签时平分，6+正好显示5个半
     */
    private void adjustTabSpace() {
        int leftMargin = leftRightmargin;
        int totalSpaceWidth;
        int singleSpaceWidth;
        if (tabList.size() < limitTabCount) {
            totalSpaceWidth = screenWidth - leftMargin * 2;
            for (int i = 0; i < tabList.size(); i++) {
                float textViewWidth = tabList.get(i).getPaint().measureText(tabList.get(i).getText().toString());
                totalSpaceWidth -= textViewWidth;
            }
            singleSpaceWidth = totalSpaceWidth / (tabList.size() - 1);
        } else {
            totalSpaceWidth = screenWidth - leftMargin;
            for (int i = 0; i < limitTabCount; i++) {
                float textViewWidth = tabList.get(i).getPaint().measureText(tabList.get(i).getText().toString());
                if (i == (limitTabCount-1)) {
                    totalSpaceWidth -= textViewWidth / 2;
                } else {
                    totalSpaceWidth -= textViewWidth;
                }
            }
            singleSpaceWidth = totalSpaceWidth / (limitTabCount-1);
        }
        for (int i = 0; i < tabList.size(); i++) {
            TextView textView = tabList.get(i);
            if (i == 0) {
                textView.setPadding(leftMargin, 0, singleSpaceWidth / 2, 0);
            } else if (i == tabList.size() - 1) {
                textView.setPadding(singleSpaceWidth / 2, 0, leftMargin, 0);
            } else {
                textView.setPadding(singleSpaceWidth / 2, 0, singleSpaceWidth / 2, 0);
            }

        }


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mViewPager != null) {
            scrollToTab(mViewPager.getCurrentItem(), 0);
        }
    }

    public void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;

            if (tabIndex > 0 || positionOffset > 0) {
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= mTitleOffset;
            }

            scrollTo(targetScrollX, 0);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            mTabStrip.onViewPagerPageChanged(position, positionOffset);

            View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;
            scrollToTab(position, extraOffset);

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }

            setTabTextColor(tabList, position);
        }

    }

    private void setTabTextColor(List<TextView> tabList, int curItem) {
        for (int i = 0; i < tabList.size(); i++) {
            if (curItem == i) {
                tabList.get(i).setTextColor(getResources().getColor(R.color.md_blue_500));
                tabList.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                TextPaint paint = tabList.get(i).getPaint();
                paint.setFakeBoldText(true);
            } else {
                tabList.get(i).setTextColor(getResources().getColor(R.color.md_grey_700));
                tabList.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                TextPaint paint = tabList.get(i).getPaint();
                paint.setFakeBoldText(false);
            }
        }
    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i);
                    if(tabItemClickListener != null){
                        tabItemClickListener.onItemClick(i);
                    }
                    return;
                }
            }
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener tabItemClickListener){
        this.tabItemClickListener = tabItemClickListener;
    }

    public interface OnTabItemClickListener{
        void onItemClick(int position);
    }

}
