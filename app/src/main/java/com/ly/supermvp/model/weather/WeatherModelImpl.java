package com.ly.supermvp.model.weather;

import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.server.RetrofitService;
import com.ly.supermvp.model.entity.ShowApiResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * <Pre>
 * 天气预报实现类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/1 14:48
 */
public class WeatherModelImpl implements WeatherModel {
    @Override
    public void netLoadWeatherWithLocation(String area, String needMoreDay, String needIndex,
                                           String needAlarm, String need3HourForcast,
                                           final OnNetRequestListener listener) {
        //使用RxJava响应Retrofit
        Observable<ShowApiResponse<ShowApiWeather>> observable = RetrofitService.getInstance().
                createShowApi().getWeather(RetrofitService.getCacheControl(), area, needMoreDay,
                needIndex, needAlarm, need3HourForcast);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        listener.onStart();
                    }
                })
                .subscribe(new Subscriber<ShowApiResponse<ShowApiWeather>>() {
                    @Override
                    public void onCompleted() {
                        //仅成功后会回调
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiWeather> showApiWeatherShowApiResponse) {
                        if(showApiWeatherShowApiResponse.showapi_res_body.now == null){
                            listener.onFailure(new Exception(showApiWeatherShowApiResponse.showapi_res_code));
                        }else {
                            listener.onSuccess(showApiWeatherShowApiResponse.showapi_res_body);
                        }
                    }
                });
    }
}
