package com.qun.newfeature;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TabLayoutActivity extends AppCompatActivity {

    private static final String TAG = "TabLayoutActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private boolean isStart = false;
    private Handler mHandler = new Handler();
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
        setContentView(R.layout.activity_tab_layout);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        //将mTabLayout和mViewPager绑定
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//让Tab能够滚动
        mViewPager.setAdapter(new MyPageAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: " + positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                ToastUtil.showToast(TabLayoutActivity.this, position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TabLayoutActivity.this, "你想干嘛", Toast.LENGTH_SHORT).show();
                ObjectAnimator.ofFloat(mFloatingActionButton, "rotation", 0, 360).setDuration(1000).start();
                Snackbar.make(mTabLayout, isStart ? "要停止播放吗？" : "要开始播放吗？", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Toast.makeText(TabLayoutActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
                                if (isStart) {
                                    //停止
                                    stopPlay();
                                } else {
                                    //开始
                                    startPlay();
                                }
                                isStart = !isStart;
                            }
                        })
                        .show();
            }
        });
    }

    private void startPlay() {
        //在让Handler执行定时任务之前，为了防止有老的任务没有被销毁，因此先清空一下Handler的任务
        mHandler.removeCallbacksAndMessages(null);
//        mViewPager.setCurrentItem();
        //让Handler执行定时周期性的任务，不断的更新ViewPager当前的页数
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = mViewPager.getCurrentItem();
                int newItem = (currentItem + 1) % mViewPager.getAdapter().getCount();
                Log.d(TAG, "翻页: "+newItem);
                mViewPager.setCurrentItem(newItem, true);
                //上面的任务执行完了，让Handler再执行一个延时任务
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void stopPlay() {
        //清空Handler发送的任务，这样所有Handler发送的不管是什么任何和消息都没有了
        mHandler.removeCallbacksAndMessages(null);
    }

    class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mStraggeredIcons.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 需要根据当前滚动到的脚标获取数据，然后将数据显示到View上，同时需要将这个View添加到ViewPager上
         *
         * @param container 就是ViewPager对象本身
         * @param position  当前ViewPager滚动到的脚标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "初始化条目: "+position);
            ImageView imageView = new ImageView(TabLayoutActivity.this);
            imageView.setImageResource(mStraggeredIcons[position]);
            //给ImageView设置宽和高属性，相当于布局文件中的 android:layout_width="match_parent"和android:layout_height="wrap_content"
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //还必须将创建出来的View添加到container中
            container.addView(imageView);
            return imageView;
        }

        /***
         * 用于销毁较老的View对象，必须覆写该方法
         * 将较老的View从ViewPager中移除
         * @param container ViewPager本身
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            Log.d(TAG, "销毁条目: "+position);
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "美女" + position;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源（handler的定时任务）
        mHandler.removeCallbacksAndMessages(null);
    }
}
