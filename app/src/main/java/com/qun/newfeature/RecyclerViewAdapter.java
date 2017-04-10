package com.qun.newfeature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Qun on 2017/4/9.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<DataBean> mDataBeanList;

    public RecyclerViewAdapter(List<DataBean> dataBeanList) {
        mDataBeanList = dataBeanList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //编写条目布局
        //使用布局填充器将条目布局转换为View
        /**
         * 参数2：ViewGroup root ，如果传入null，那么在填充布局的时候会忽略布局上的宽和高属性
         *  如果传入ViewGroup，那么在填充布局的时候就会考虑布局上的宽和高属性
         * 参数3：是否让布局填充器将R.layout.list_item布局自动添加到ViewGroup上，如果传入true，那么返回值为parent本身而不再是R.layout.list_item转换的View了
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //创建ViewHolder，并把View作为构造参数传递进去
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        //将ViewHolder返回
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataBeanList == null ? 0 : mDataBeanList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View itemView) {
            super(itemView);

        }
    }
}
