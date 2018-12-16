package com.example.jinhui.testcollapsingtoolbarlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/12/16.
 */
public class ScrollingActivityNew extends AppCompatActivity {

    private static final String TAG = "ScrollingActivityNew";

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_head)
    ImageView mHeadImage;
    @BindView(R.id.subscription_title)
    TextView mTitle;
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    private float mSelfHeight = 0;  //用以判断是否得到正确的宽高值
    private float mTitleScale;      //标题缩放值
    private float mTestScaleY;      //测试按钮y轴缩放值
    private float mTestScaleX;      //测试按钮x轴缩放值
    private float mHeadImgScale;    //头像缩放值
    private float mHeadImgX;    //头像x缩放值
    private float mHeadImgY;    //头像y缩放值

    private float mLLayoutX;    //布局x缩放值
    private float mLLayoutY;    //布局y缩放值


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling1);
        ButterKnife.bind(this);

        final float screenW = getResources().getDisplayMetrics().widthPixels;
        final float toolbarHeight = getResources().getDimension(R.dimen.tool_bar_height);
        final float initHeight = getResources().getDimension(R.dimen.app_bar_height);


        /**
         *   移动效果值／最终效果值 =  移动距离／ 能移动总距离（确定）
         */
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mSelfHeight == 0) {
                    //获取标题高度
                    mSelfHeight = mTitle.getHeight();
                    //得到标题的高度差
                    float distanceTitle = mTitle.getTop() - (toolbarHeight - mTitle.getHeight()) / 2.0f;
                    //得到测试按钮的高度差
                    float distanceTest = tvTest.getY() - (toolbarHeight - tvTest.getHeight()) / 2.0f;
                    //得到图片的高度差
                    float distanceHeadImg = mHeadImage.getY() - (toolbarHeight - mHeadImage.getHeight()) / 2.0f;
                    //得到测试按钮的水平差值  屏幕宽度一半 - 按钮宽度一半
                    float distanceSubscribeX = screenW / 2.0f - (tvTest.getWidth() / 2.0f);

                    //得到图片的水平差值  屏幕宽度一半 - 按钮宽度一半
                    float distanceHeadImgX = screenW / 2.0f - (mHeadImage.getWidth() / 2.0f);
                    // 布局
                    float distanceLLayoutY = llLayout.getY() - (toolbarHeight - llLayout.getHeight()) / 2.0f;
                    float distanceLLayoutX = screenW / 2.0f - (llLayout.getWidth() / 2.0f);

                    //得到高度差缩放比值  高度差／能滑动总长度 以此类推
                    mTitleScale = distanceTitle / (initHeight - toolbarHeight);
                    mTestScaleY = distanceTest / (initHeight - toolbarHeight);
//                    mHeadImgScale = distanceHeadImg / (initHeight - toolbarHeight);

                    mTestScaleX = (float) (distanceSubscribeX / (initHeight - toolbarHeight) - 0.5);

                    mLLayoutX = (float) (distanceLLayoutX / (initHeight - toolbarHeight) - 0.5);
                    mLLayoutY = distanceLLayoutY / (initHeight - toolbarHeight);


                    mHeadImgX = distanceHeadImgX / (initHeight - toolbarHeight);
                    mHeadImgY = distanceHeadImg / (initHeight - toolbarHeight);

                }

                Log.e(TAG, "mTestScaleX: " + mTestScaleX);
                //得到文本框、头像缩放值 不透明 ->透明  文本框x跟y缩放
                float scale = 1.0f - (-verticalOffset) / (initHeight - toolbarHeight);

                tvSearch.setScaleX(scale);
                tvSearch.setScaleY(scale);
                tvSearch.setAlpha(scale);

                // 图片y轴缩放
//                mHeadImage.setScaleX(scale);
//                mHeadImage.setScaleY(scale);
                //设置头像y轴平移
                mHeadImage.setTranslationY(mHeadImgY * verticalOffset);
                //设置头像x轴平移
                mHeadImage.setTranslationX(mHeadImgX * verticalOffset);
                //设置标题y轴平移
                mTitle.setTranslationY(mTitleScale * verticalOffset);
                //设置测试按钮x跟y平移
                tvTest.setTranslationY(mTestScaleY * verticalOffset);
//                tvTest.setTranslationX(-mTestScaleX * verticalOffset);  // 向右
                tvTest.setTranslationX(mTestScaleX * verticalOffset);  // 向左

                llLayout.setTranslationX(mLLayoutX * verticalOffset);
                llLayout.setTranslationY(mLLayoutY * verticalOffset);
            }
        });

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollingActivityNew.this, TestScrollActivity.class));
            }
        });


    }
}
