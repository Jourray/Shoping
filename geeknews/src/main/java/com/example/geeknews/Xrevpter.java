package com.example.geeknews;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geeknews.bean.Bean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

class Xrevpter extends XRecyclerView.Adapter<Xrevpter.ViewHolder> {
    private Context context;
    ArrayList<Bean.DataBean> list = new ArrayList<>();

    public Xrevpter(Context context) {
        this.context = context;
    }

    public void addData(List<Bean.DataBean> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.te_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.desc.setText(list.get(i).getNum() + "");
        final Integer integer = new Integer(i);
        viewHolder.cb.setTag(integer);
        if (Shoping.hashMap.containsKey(integer)) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cb.isChecked()) {
                    Shoping.hashMap.put(integer, true);
                } else {
                    Shoping.hashMap.remove(integer);
                }
                onItemClik.onClick(v, list.get(i));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.mycb);
            desc = itemView.findViewById(R.id.mytv);
        }
    }

    public OnItemClik onItemClik;

    public void setOnItemClik(OnItemClik onItemClik) {
        this.onItemClik = onItemClik;
    }

    public interface OnItemClik {
        void onClick(View v, Bean.DataBean list);
    }
}
