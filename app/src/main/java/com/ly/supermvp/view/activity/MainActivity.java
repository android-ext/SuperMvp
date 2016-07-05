package com.ly.supermvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.adapter.SectionsPagerAdapter;
import com.ly.supermvp.delegate.MainActivityDelegate;
import com.ly.supermvp.mvp_frame.presenter.ActivityPresenter;

public class MainActivity extends ActivityPresenter<MainActivityDelegate> implements View.OnClickListener {

    @Override
    protected Class<MainActivityDelegate> getDelegateClass() {
        return MainActivityDelegate.class;
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        super.initData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
    }

    @Override
    protected void initView() {
        super.initView();

        setSwipeBackEnable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewDelegate.setViewPagerAdapter(mSectionsPagerAdapter);
        viewDelegate.setupWithViewPager();
        viewDelegate.setOnClickListener(this, R.id.fab);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
