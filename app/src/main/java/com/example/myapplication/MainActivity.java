package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar mMytoobar;
    private LinearLayout mMyll;
    private TabLayout mMytab;
    private FragmentManager manager;
    private FirstpageFrag firstpageFrag;
    private DbFrag dbFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTab();

    }

    private void initTab() {
        mMytab.addTab(mMytab.newTab().setText("首页"));
        mMytab.addTab(mMytab.newTab().setText("收藏"));
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        firstpageFrag = new FirstpageFrag();
        dbFrag = new DbFrag();
        fragmentTransaction.add(R.id.myll, firstpageFrag);
        fragmentTransaction.add(R.id.myll, dbFrag);
        fragmentTransaction.show(firstpageFrag);
        fragmentTransaction.hide(dbFrag);
        fragmentTransaction.commit();
        mMytab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        FragmentTransaction tran = manager.beginTransaction();
                        tran.show(firstpageFrag);
                        tran.hide(dbFrag);
                        tran.commit();
                        mMytoobar.setTitle("首页");
                        break;
                    case 1:
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.show(dbFrag);
                        trans.hide(firstpageFrag);
                        trans.commit();
                        mMytoobar.setTitle("收藏");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView() {
        mMytoobar = findViewById(R.id.mytoobar);
        mMyll = findViewById(R.id.myll);
        mMytab = findViewById(R.id.mytab);
        setTitle("");
        setSupportActionBar(mMytoobar);

    }
}
