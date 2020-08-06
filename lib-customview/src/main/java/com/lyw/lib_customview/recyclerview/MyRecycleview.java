package com.lyw.lib_customview.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lyw.lib_customview.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

public class MyRecycleview extends LinearLayout implements RecycleViewInterface{
    public View childView;
    private RecyclerView recyclerView;
    public SmartRefreshLayout refreshLayout;
    private Context context;
    private BaseQuickAdapter adapter;
    private View footView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList mList;
    private int childItem;
    private VHData vhData;

    public MyRecycleview(@NonNull Context context){
        this(context,null);
    }

    public MyRecycleview(@NonNull Context context, @Nullable AttributeSet attrs){
        this(context, attrs,0);
    }

    public MyRecycleview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonMyRecycleView, defStyleAttr, R.style.CommonMyRecycleView_Style);
        childItem = typedArray.getResourceId(R.styleable.CommonMyRecycleView_commonChildView, R.layout.common_child_item);
        View view = View.inflate(context,R.layout.common_myrecycleview,this);
        refreshLayout = view.findViewById(R.id.smart_layout);
        recyclerView = view.findViewById(R.id.rey_view);
        setLayoutManager(null);
        setAdapter(null);
        childView = View.inflate(context, childItem, null);
        setVHData();
    }
    @Override
    public void setAdapter(BaseQuickAdapter adapter){
        if(adapter==null){
            adapter = new MyBaseAdapter(childItem);
        }
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        if(layoutManager==null){
            layoutManager = new LinearLayoutManager(context);
        }
        this.layoutManager = layoutManager;
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addFootView(View view){
        this.footView = view;
        if(adapter!=null){
            adapter.addFooterView(footView);
        }
    }
    @Override
    public void setData(ArrayList mList){
        this.mList = mList;
        if(adapter!=null){
            adapter.setNewData(mList);
        }
    }

    @Override
    public void creatChildItem(int layout) {
        this.childItem = layout;
    }

    public void setVHData(){
        if(adapter!=null){
            if(adapter instanceof MyBaseAdapter){
                ((MyBaseAdapter) adapter).setVHData(new MyBaseAdapter.VHData() {
                    @Override
                    public void setVHData(BaseViewHolder vh, BaseCustomViewModel t) {
                        if(vhData !=null){
                            vhData.convert(vh,t);
                        }
                    }
                });
            }
        }
    }
    public interface VHData{
        void convert(BaseViewHolder vh, BaseCustomViewModel t);
    }

    public void setConvert(VHData vhData){
        this.vhData = vhData;
    }
}
