package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.myapplication.bean.DataBean;
import com.example.myapplication.utils.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class DbFrag extends Fragment {
    private View view;
    private XRecyclerView mMyxre;
    private CheckBox mMyccb;
    private Xrevpter xrevpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.xre_layout, null);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isHidden()){

        }else {
            initData();
        }
    }

    private void initData() {
        List<DataBean> seleAll = Utils.getSeleAll();
        xrevpter.addData(seleAll);
    }

    private void initView(View view) {
        mMyxre = view.findViewById(R.id.myxre);
        mMyccb = view.findViewById(R.id.myccb);
        mMyxre.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyxre.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        xrevpter = new Xrevpter(getActivity(),2);
        mMyxre.setAdapter(xrevpter);

    }
}
