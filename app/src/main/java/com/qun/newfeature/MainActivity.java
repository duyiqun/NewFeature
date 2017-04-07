package com.qun.newfeature;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToorbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToorbar = (Toolbar) findViewById(R.id.toolbar);
        mToorbar.setTitle("主标题");
        setSupportActionBar(mToorbar);
        mToorbar.setSubtitle("子标题");
        mToorbar.setTitleTextColor(Color.RED);
        mToorbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToorbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击导航图标", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
