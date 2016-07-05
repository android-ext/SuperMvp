package com.ly.supermvp.delegate;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.model.weather.ShowApiWeather;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.view.LoadingView;
import com.ly.supermvp.widget.ProgressLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.rey.material.widget.EditText;

import butterknife.Bind;

/**
 * <Pre>
 * 天气预报界面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:44
 */
public class WeatherFragmentDelegate extends AppDelegate implements LoadingView{

    private ImageView iv_weather;
    private TextView tv_weather, tv_aqi, tv_sd, tv_wind_direction, tv_wind_power, tv_temperature_time,
            tv_temperature;

    private LinearLayout ll_dialog_holder;

    @Bind(R.id.progress_layout)
    ProgressLayout mProgressLayout;
    @Bind(R.id.et_location)
    EditText et_location;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_weather;
    }

    /**
     * 获取输入的地名
     * @return
     */
    public String getInputLocation(){
        return et_location.getText().toString();
    }

    /**
     * 显示当前天气弹窗
     */
    public void showNowWeatherDialog(ShowApiWeather weather) {
        ll_dialog_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_weather, null);
        Holder holder = new ViewHolder(ll_dialog_holder);
        findHolderChildView(holder);
        GlideUtil.loadImage(getActivity(), weather.now.weather_pic, iv_weather);
        tv_weather.setText(weather.now.weather);
        tv_temperature.setText(weather.now.temperature + "℃");
        tv_temperature_time.setText(weather.now.temperature_time);
        tv_aqi.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_aqi),
                weather.now.aqi));
        tv_sd.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_sd),
                weather.now.sd));
        tv_wind_direction.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_wind_direction),
                weather.now.wind_direction));
        tv_wind_power.setText(String.format(getActivity().getResources().getString(R.string.weather_dialog_wind_power),
                weather.now.wind_power));
        showOnlyContentDialog(holder, Gravity.BOTTOM, false);
    }

    /**
     * 查找弹窗的holder的子控件
     *
     * @param holder
     */
    private void findHolderChildView(Holder holder) {
        iv_weather = (ImageView) holder.getInflatedView().findViewById(R.id.iv_weather);
        tv_weather = (TextView) holder.getInflatedView().findViewById(R.id.tv_weather);
        tv_temperature = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature);
        tv_temperature_time = (TextView) holder.getInflatedView().findViewById(R.id.tv_temperature_time);
        tv_aqi = (TextView) holder.getInflatedView().findViewById(R.id.tv_aqi);
        tv_sd = (TextView) holder.getInflatedView().findViewById(R.id.tv_sd);
        tv_wind_direction = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_direction);
        tv_wind_power = (TextView) holder.getInflatedView().findViewById(R.id.tv_wind_power);
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity         显示位置（居中，底部，顶部）
     * @param expanded        是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        dialog.show();
    }
    /**
     * 关闭软键盘
     */
    public void closeSoftInput(){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et_location.getWindowToken(), 0);
    }

    @Override
    public void showLoading() {
        mProgressLayout.showLoading();
    }

    @Override
    public void showContent() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
