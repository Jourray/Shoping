package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.myapplication.bean.DataBean;
import com.example.myapplication.presneter.Presenter;
import com.example.myapplication.utils.Utils;
import com.example.myapplication.view.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstpageFrag extends Fragment implements MyView {
    private View view;
    private XRecyclerView mMyxre;
    private Xrevpter xrevpter;
    private Presenter presenter;
    private static final String TAG = "FirstpageFrag";
    private int a;
    private CheckBox mMyccb;

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
        presenter = new Presenter(this);
        presenter.getVtop();
    }

    @Override
    public void getViewSuccess(final ArrayList<DataBean> dataBeans) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                xrevpter.addData(dataBeans);
            }
        });
    }

    @Override
    public void getViewFails(String data) {
        Log.d(TAG, "getViewFails: " + data);
    }

    private void initView(View view) {
        mMyxre = view.findViewById(R.id.myxre);
        mMyccb = view.findViewById(R.id.myccb);
        mMyccb.setVisibility(View.VISIBLE);
        mMyxre.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyxre.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));
        xrevpter = new Xrevpter(getActivity(),1);
        mMyxre.setAdapter(xrevpter);
        xrevpter.setOnItemclick(new Xrevpter.OnItemclick() {
            @Override
            public void onClick(View v, int position, DataBean dataBean) {
                int num = dataBean.getNum();
                CheckBox viewById = v.findViewById(R.id.mycb);
                if (viewById.isChecked()) {
                        Utils.addData(dataBean);
                        Toast.makeText(getActivity(), "添加数据库", Toast.LENGTH_SHORT).show();
                    a += num;
                    mMyccb.setText(a + "");
                } else {
                    a -= num;
                    mMyccb.setText(a + "");
                }

            }
        });

    }
}
