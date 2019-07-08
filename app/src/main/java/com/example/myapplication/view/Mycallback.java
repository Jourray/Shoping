package com.example.myapplication.view;

import com.example.myapplication.bean.DataBean;

import java.util.ArrayList;

public interface Mycallback {
    void getCallSuccess(ArrayList<DataBean> dataBeans);

    void getCallFails(String data);
}
