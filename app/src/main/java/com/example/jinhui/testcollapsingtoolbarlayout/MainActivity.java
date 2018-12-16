package com.example.jinhui.testcollapsingtoolbarlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/CTSN/TestCollapsingToolbarLayout
 * 工具栏折叠效果
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_scroll)
    Button btScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_scroll,R.id.bt_scroll_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_scroll:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            case R.id.bt_scroll_new:
                startActivity(new Intent(this, ScrollingActivityNew.class));
                break;
        }
    }
}
