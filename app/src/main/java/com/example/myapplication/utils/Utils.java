package com.example.myapplication.utils;

import com.example.myapplication.application.MyApplication;
import com.example.myapplication.bean.DataBean;

import java.util.List;

import client.example.text.dao.DaoSession;
import client.example.text.dao.DataBeanDao;

public class Utils {
    public static void addData(DataBean dataBean) {
        DaoSession daoSession = MyApplication.getDaoSession();
        DataBean seleone = getSeleone(dataBean.getId());
        if (seleone == null) {
            daoSession.insert(dataBean);
        }
    }

    public static void getDelete(DataBean dataBean) {
        DaoSession daoSession = MyApplication.getDaoSession();
        DataBean seleone = getSeleone(dataBean.getId());
        if (seleone != null) {
            daoSession.delete(dataBean);
        }
    }

    public static DataBean getSeleone(String id) {
        DaoSession daoSession = MyApplication.getDaoSession();
        return daoSession.queryBuilder(DataBean.class)
                .where(DataBeanDao.Properties.Id.eq(id))
                .build()
                .unique();
    }

    public static List<DataBean> getSeleAll() {
        DaoSession daoSession = MyApplication.getDaoSession();
        return daoSession.loadAll(DataBean.class);
    }
}
