package com.example.myapplication.model;

import android.util.Log;


import com.example.myapplication.bean.Bean;
import com.example.myapplication.bean.DataBean;
import com.example.myapplication.view.Mycallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements MyModel {
    private static final String TAG = "Model";

    @Override
    public void getData(final Mycallback mycallback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
                .build();
        okHttpClient.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                        mycallback.getCallFails(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.d(TAG, "onResponse: " + string);
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            JSONArray data = jsonObject.getJSONArray("data");
                            ArrayList<DataBean> dataBeans = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                String num = jsonObject1.optString("num");
                                String title = jsonObject1.optString("title");
                                String id = jsonObject1.optString("id");
                                dataBeans.add(new DataBean(id,title, Integer.parseInt(num)));
                            }
                            mycallback.getCallSuccess(dataBeans);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }
}
