package com.ly.supermvp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ly.supermvp.R;
import com.ly.supermvp.view.fragment.NewsFragment;
import com.ly.supermvp.view.fragment.PicturesFragment;
import com.ly.supermvp.view.fragment.WeatherFragment;

/**
 * <Pre>
 * viewpager选项卡适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 16:26
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NewsFragment.newInstance();
            case 1:
                return PicturesFragment.newInstance();
            case 2:
                return WeatherFragment.newInstance();
            default:
                return NewsFragment.newInstance();
        }
//        return MainActivity.PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.main_tab_news);
            case 1:
                return context.getResources().getString(R.string.main_tab_picture);
            case 2:
                return context.getResources().getString(R.string.main_tab_weather);
        }
        return null;
    }
}
