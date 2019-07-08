package com.example.myapplication.presneter;

import com.example.myapplication.bean.DataBean;
import com.example.myapplication.model.Model;
import com.example.myapplication.view.MyView;
import com.example.myapplication.view.Mycallback;

import java.util.ArrayList;

public class Presenter implements MyPresenter {

    private final Model model;
    private final MyView myview;

    public Presenter(MyView myView) {
        model = new Model();
        this.myview = myView;
    }

    @Override
    public void getVtop() {
        if (model != null) {
            model.getData(new Mycallback() {
                @Override
                public void getCallSuccess(ArrayList<DataBean> dataBeans) {
                    myview.getViewSuccess(dataBeans);
                }

                @Override
                public void getCallFails(String data) {
                    myview.getViewFails(data);
                }
            });
        }
    }
}
