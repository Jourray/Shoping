package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.bean.DataBean;
import com.example.myapplication.utils.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

class Xrevpter extends XRecyclerView.Adapter<Xrevpter.ViewHolder> {
    ArrayList<DataBean> list = new ArrayList<>();
    private Context context;
    private int type;
    private int math;

    public Xrevpter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void addData(List<DataBean> dataBeans) {
        this.list.clear();
        this.list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.te_te_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final DataBean dataBean = list.get(i);
        viewHolder.unm.setText(dataBean.getNum() + "");
        viewHolder.title.setText(dataBean.getTitle());
        if (type == 2) {
            viewHolder.cb.setChecked(true);
        }
        if (type == 1) {
            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        viewHolder.cb.setChecked(true);
                    } else {
                        viewHolder.cb.setChecked(false);
                    }
                }
            });
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cb.isChecked()) {
                    viewHolder.cb.setChecked(true);
                } else {
                    if (type == 2) {
                        Utils.getDelete(dataBean);
                        list.remove(dataBean);
                        notifyDataSetChanged();
                        Toast.makeText(context, "删除数据库", Toast.LENGTH_SHORT).show();
                    }
                }
                if (type == 1) {
                    onItemclick.onClick(v, i, dataBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView unm;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mytvtitle);
            unm = itemView.findViewById(R.id.mytvnum);
            cb = itemView.findViewById(R.id.mycb);
        }
    }

    public OnItemclick onItemclick;

    public void setOnItemclick(OnItemclick onItemclick) {
        this.onItemclick = onItemclick;
    }

    public interface OnItemclick {
        void onClick(View v, int position, DataBean dataBean);
    }
}
