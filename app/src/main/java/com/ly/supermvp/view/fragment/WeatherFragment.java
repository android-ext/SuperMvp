package com.ly.supermvp.view.fragment;

import android.text.TextUtils;
import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.delegate.WeatherFragmentDelegate;
import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.weather.ShowApiWeather;
import com.ly.supermvp.model.weather.WeatherModel;
import com.ly.supermvp.model.weather.WeatherModelImpl;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.orhanobut.logger.Logger;

/**
 * <Pre>
 * 天气预报fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:43
 */
public class WeatherFragment extends FragmentPresenter<WeatherFragmentDelegate> implements View.OnClickListener {
    public static final String NEED_MORE_DAY = "1";
    public static final String NEED_INDEX = "1";
    public static final String NEED_ALARM = "1";
    public static final String NEED_3_HOUR_FORCAST = "1";

    private WeatherModel mWeatherModel;

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    protected Class<WeatherFragmentDelegate> getDelegateClass() {
        return WeatherFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mWeatherModel = new WeatherModelImpl();
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.bt_weather);
    }

    /**
     * 获取天气预报
     */
    private void netWeather() {
        if(TextUtils.isEmpty(viewDelegate.getInputLocation())){
            viewDelegate.showSnackbar("输入为空");
            return;
        }
        mWeatherModel.netLoadWeatherWithLocation(viewDelegate.getInputLocation(), NEED_MORE_DAY,
                NEED_INDEX, NEED_ALARM, NEED_3_HOUR_FORCAST, new OnNetRequestListener<ShowApiWeather>() {
                    @Override
                    public void onStart() {
                        viewDelegate.showLoading();
                    }

                    @Override
                    public void onFinish() {
                        viewDelegate.showContent();
                    }

                    @Override
                    public void onSuccess(ShowApiWeather weather) {
                        Logger.d("onSuccess");
                        viewDelegate.closeSoftInput();
                        viewDelegate.showNowWeatherDialog(weather);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        viewDelegate.showSnackbar("请求错误");
                        Logger.d("onFailure");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_weather:
                netWeather();
                break;
        }
    }
}
