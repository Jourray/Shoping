package com.example.geeknews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.geeknews.frag.Wechat;
import com.example.geeknews.frag.Zhihu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mMytppbar;
    private TabLayout mMytab;
    private LinearLayout mMyll;
    private DrawerLayout mMydrawer;
    private ArrayList<Fragment> list;
    private int num = 0;
    private NavigationView mMyna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mMytppbar = findViewById(R.id.mytppbar);
        mMytab = findViewById(R.id.mytab);
        mMyna = findViewById(R.id.myna);
        mMyll = findViewById(R.id.myll);
        mMydrawer = findViewById(R.id.mydrawer);
        setTitle("");
        setSupportActionBar(mMytppbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mMydrawer, mMytppbar, R.string.app_name, R.string.app_name);
        mMydrawer.addDrawerListener(toggle);
        toggle.syncState();
        initFrag();
        initVisi();
        mMyna.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mywechaat:
                        switchFrag(0);
                        mMytppbar.setTitle("微信");
                        mMydrawer.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.myzhihu:
                        switchFrag(1);
                        mMytppbar.setTitle("知乎");
                        mMydrawer.closeDrawer(Gravity.LEFT);
                        break;
                }
                ;
                return false;
            }
        });
    }

    private void switchFrag(int type) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = list.get(type);
        if (!fragment.isAdded()) {
            transaction.add(R.id.myll, fragment);
        }
        transaction.show(fragment);
        transaction.hide(list.get(num));
        transaction.commit();
        num = type;
    }

    private void initVisi() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.myll, list.get(0));
        transaction.commit();
    }

    private void initFrag() {
        list = new ArrayList<>();
        list.add(new Wechat());
        list.add(new Zhihu());
    }
}
