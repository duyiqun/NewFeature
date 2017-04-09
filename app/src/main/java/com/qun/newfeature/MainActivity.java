package com.qun.newfeature;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar mToorbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到ToolBar控件
        mToorbar = (Toolbar) findViewById(R.id.toolbar);
        mToorbar.setTitle("主标题");//如果想修改主标题，一定得在 setSupportActionBar(mToolbar);之前，否则没效果
        //用这个ToolBar替换ActionBar
        setSupportActionBar(mToorbar);//使用该代码之前必须确保：当前Activity使用的主题不能带有ActionBar
        //给ToolBar添加子标题
        mToorbar.setSubtitle("子标题");
        mToorbar.setTitleTextColor(Color.RED);
//        mToorbar.setNavigationIcon(R.mipmap.ic_launcher);
        //给ToolBar的导航图标添加事件
//        mToorbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "点击导航图标", Toast.LENGTH_SHORT).show();
//            }
//        });
        //初始化NavigationView，跟ToolBar关联起来
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //打开ToolBar的左侧的导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToorbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        //自己给抽屉菜单添加监听器
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d(TAG, "onDrawerSlide:" + slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "菜单被打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "菜单被关闭了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //给NavigationView绑定菜单条目的点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.left_menu_recyclerView:
//                        Toast.makeText(MainActivity.this, "跳转到recyclerView界面", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case R.id.left_menu_tabLayout:
                        Toast.makeText(MainActivity.this, "跳转到tabLayout界面", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_menu_appBarLayout:
                        Toast.makeText(MainActivity.this, "跳转到appBarLayout界面", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.left_menu_collaspingToolBarLayout:
                        Toast.makeText(MainActivity.this, "跳转到collaspingToolBarLayout界面", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

                Toast.makeText(this, "添加好友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:

                Toast.makeText(this, "分享好友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:

                Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
