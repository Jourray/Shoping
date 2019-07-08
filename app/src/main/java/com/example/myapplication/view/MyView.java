package com.example.myapplication.view;

import com.example.myapplication.bean.DataBean;

import java.util.ArrayList;

public interface MyView {
    void getViewSuccess(ArrayList<DataBean> dataBeans);

    void getViewFails(String data);
}
