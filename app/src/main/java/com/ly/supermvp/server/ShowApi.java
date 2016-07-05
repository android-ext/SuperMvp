package com.ly.supermvp.server;

import com.ly.supermvp.common.BizInterface;
import com.ly.supermvp.model.news.ShowApiNews;
import com.ly.supermvp.model.entity.ShowApiResponse;
import com.ly.supermvp.model.pictures.ShowApiPictures;
import com.ly.supermvp.model.weather.ShowApiWeather;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * <Pre>
 *     易源api
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:22
 */
public interface ShowApi {
    /**
     * 新闻列表
     * @param cacheControl
     * @param page
     * @param channelId
     * @param channelName
     * @return
     */
    @GET(BizInterface.NEWS_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Call<ShowApiResponse<ShowApiNews>> getNewsList(@Header("Cache-Control") String cacheControl,
                                  @Query("page") int page,
                                  @Query("channelId") String channelId,//新闻频道id，必须精确匹配
                                  @Query("channelName") String channelName);//新闻频道名称，可模糊匹配

    /**
     * 天气预报响应
     * @param area 地区名称，比如北京
     * @param needMoreDay 是否需要返回7天数据中的后4天。1为返回，0为不返回。
     * @param needIndex 是否需要返回指数数据，比如穿衣指数、紫外线指数等。1为返回，0为不返回。
     * @param needAlarm 是否需要天气预警。1为需要，0为不需要。
     * @param need3HourForcast 是否需要当天每3小时1次的天气预报列表。1为需要，0为不需要。
     * @return
     */
    @GET(BizInterface.WEATHER_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Observable<ShowApiResponse<ShowApiWeather>> getWeather(@Header("Cache-Control") String cacheControl,
                                                           @Query("area") String area,
                                                           @Query("needMoreDay") String needMoreDay,
                                                           @Query("needIndex") String needIndex,
                                                           @Query("needAlarm") String needAlarm,
                                                           @Query("need3HourForcast") String need3HourForcast);

    /**
     * 美图大全响应
     * @param type "id": 4001, //此id很重要，在【图片查询】接口里将使用此id进行分类查询
    "name": "清纯"
     * @param page 页数
     * @return
     */
    @GET(BizInterface.PICTURES_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Observable<ShowApiResponse<ShowApiPictures>> getPictures(@Header("Cache-Control") String cacheControl,
                                                             @Query("type") String type,
                                                             @Query("page") int page);
}
