package com.ly.supermvp.model.pictures;

import com.ly.supermvp.model.OnNetRequestListener;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.server.RetrofitService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * <Pre>
 *     图片大全数据实现类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:04
 */
public class PicturesModelImpl implements PicturesModel{
    @Override
    public void netLoadPictures(String type, int page, final OnNetRequestListener<List<PictureBody>> listener) {
        Observable<ShowApiResponse<ShowApiPictures>> observable = RetrofitService.getInstance().
                createShowApi().getPictures(RetrofitService.getCacheControl(), type, page);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        listener.onStart();
                    }
                })
                .subscribe(new Subscriber<ShowApiResponse<ShowApiPictures>>() {
                    @Override
                    public void onCompleted() {
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiPictures> showApiPicturesShowApiResponse) {
                        if (showApiPicturesShowApiResponse.showapi_res_body != null) {
                            listener.onSuccess(showApiPicturesShowApiResponse.showapi_res_body.pagebean.contentlist);
                        } else {
                            listener.onFailure(new Exception());
                        }
                    }
                });
    }
}
