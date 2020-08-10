package com.lyw.lib_customview.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyw.lib_customview.R;

import java.util.List;

public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.AutoViewHolder> {
    private  Context mContext;
    private  List<String> datas;

    public AutoPollAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.datas = list;
    }

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_child_item, parent, false);
        AutoViewHolder holder = new AutoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder autoViewHolder, int mPosition) {
        int position = mPosition%datas.size();
        autoViewHolder.tv_content.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        if(datas.size()<=2){
            return datas.size();
        }
        return Integer.MAX_VALUE;
    }

    public class AutoViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content;
        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
             tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}
