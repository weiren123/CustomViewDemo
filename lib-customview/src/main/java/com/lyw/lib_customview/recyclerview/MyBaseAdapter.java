package com.lyw.lib_customview.recyclerview;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


public class MyBaseAdapter extends BaseQuickAdapter<BaseCustomViewModel,BaseViewHolder> {

    private VHData vhData;

    public MyBaseAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(@Nullable BaseViewHolder vh, @Nullable BaseCustomViewModel t) {
        if(vhData!=null){
            vhData.setVHData(vh,t);
        }

    }

    public interface VHData{
        void setVHData(BaseViewHolder vh, BaseCustomViewModel t);
    }

    public void setVHData(VHData vhData){
        this.vhData = vhData;
    }
}
