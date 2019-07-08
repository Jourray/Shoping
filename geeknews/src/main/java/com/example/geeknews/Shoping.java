package com.example.geeknews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.geeknews.bean.Bean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Shoping extends AppCompatActivity {

    private XRecyclerView mMyxre;
    private TextView mMytv;
    private Xrevpter xrevpter;
    private int num;
    private static final String TAG = "Shoping";
    public static HashMap<Integer, Boolean> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
                .build();
        okHttpClient.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Bean bean = new Gson().fromJson(string, Bean.class);
                        final List<Bean.DataBean> data = bean.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xrevpter.addData(data);
                            }
                        });
                    }
                });
    }

    private void initView() {
        mMyxre = findViewById(R.id.myxre);
        mMytv = findViewById(R.id.mytv);
        mMyxre.setLayoutManager(new LinearLayoutManager(this));
        mMyxre.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        hashMap = new HashMap<>();
        xrevpter = new Xrevpter(this);
        mMyxre.setAdapter(xrevpter);
        xrevpter.setOnItemClik(new Xrevpter.OnItemClik() {
            @Override
            public void onClick(View v, Bean.DataBean list) {
                CheckBox viewById = v.findViewById(R.id.mycb);
                if (viewById.isChecked()) {
                    num += list.getNum();
                    mMytv.setText(num + "");
                } else {
                    num -= list.getNum();
                    mMytv.setText(num + "");
                }
            }
        });
    }
}
