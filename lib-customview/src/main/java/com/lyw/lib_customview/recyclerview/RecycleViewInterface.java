package com.lyw.lib_customview.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

public interface RecycleViewInterface {

    void setAdapter(BaseQuickAdapter adapter) throws Throwable;
    void setLayoutManager(RecyclerView.LayoutManager layoutManager);
    void setData(ArrayList data);

    void creatChildItem(int layout);
}
