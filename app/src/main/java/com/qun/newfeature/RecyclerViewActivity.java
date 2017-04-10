package com.qun.newfeature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<DataBean> dataBeanList = initData();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(dataBeanList, this);
        //仅仅给RecyclerView设置Adapter是不会显示界面，还必须给RecyclerView设置布局管理器
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mToolbar.setTitle("RecyclerView的使用");
        //初始化ToolBar
        setSupportActionBar(mToolbar);
        //打开系统自带的导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<DataBean> initData() {
        List<DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            DataBean dataBean = new DataBean();
            dataBean.imageId = mStraggeredIcons[i];
            dataBean.title = "美女" + i;
            dataBeanList.add(dataBean);
        }
        return dataBeanList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclerview_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listView_vertical:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
                break;
            case R.id.listView_horizontal:

                break;
            case R.id.gridView_vertical:

                break;
            case R.id.gridView_horizontal:

                break;
            case R.id.staggerView_vertical:

                break;
            case R.id.staggerView_horizontal:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
