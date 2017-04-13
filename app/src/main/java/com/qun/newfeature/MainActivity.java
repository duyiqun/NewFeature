package com.qun.newfeature;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;
    private Toolbar mToorbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private EditText mEtUsername;
    private EditText mEtPwd;
    private TextInputLayout mTilUsername;
    private TextInputLayout mTilPwd;

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
//                        Toast.makeText(MainActivity.this, "跳转到tabLayout界面", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
                        break;
                    case R.id.left_menu_appBarLayout:
//                        Toast.makeText(MainActivity.this, "跳转到appBarLayout界面", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, AppBarLayoutActivity.class));
                        break;
                    case R.id.left_menu_collaspingToolBarLayout:
//                        Toast.makeText(MainActivity.this, "跳转到collaspingToolBarLayout界面", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, CollaspingToolBarLayoutActivity.class));
                        break;
                }
                return true;
            }
        });
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mTilUsername = (TextInputLayout) findViewById(R.id.til_username);
        mTilPwd = (TextInputLayout) findViewById(R.id.til_pwd);
        mEtPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /**
             *
             * @param v 其实就是mEtPwd对象
             * @param actionId 键盘右下角的Action键的ID
             * @param event
             * @return
             */
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: v=" + v + "/actionId=" + actionId);
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ToastUtil.showToast(MainActivity.this, "被点击了");
                }
                //返回false代表系统输入法还会走其原本的逻辑，比如自动隐藏输入法
                //返回true代表不让系统输入法走其原本的逻辑
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

    public void submit(View view) {
        String username = mEtUsername.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username) || username.length() > 10) {
            //显示错误信息
            mTilUsername.setErrorEnabled(true);
            mTilUsername.setError("用户名不合法");
            //让EdUsername获取到焦点即可
            mEtUsername.requestFocus();
            return;
        } else {
            //隐藏错误信息
            mTilUsername.setErrorEnabled(false);
            mTilUsername.setError("");
        }
        if (TextUtils.isEmpty(pwd)) {
            //显示错误信息
            mTilPwd.setErrorEnabled(true);
            mTilPwd.setError("密码不合法");
        } else {
            //隐藏错误信息
            mTilPwd.setErrorEnabled(false);
            mTilPwd.setError("");
            mEtPwd.requestFocus(View.FOCUS_RIGHT);
            return;
        }
    }

    public void writeSdcard(View view) {

        //检查当前App有没有被授权写SDCard权限
        //如果没有被授权，则动态（弹出对话框）申请一个权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
            //用户还没有给我授权这个Manifest.permission.WRITE_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "name.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("abc");
            fileWriter.close();
            ToastUtil.showToast(this, "写完了");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                //用户授权了
                Toast.makeText(this, "用户授权了", Toast.LENGTH_SHORT).show();
            } else {
                //用户拒绝了
                Toast.makeText(this, "用户拒绝了", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
