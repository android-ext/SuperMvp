package com.ly.supermvp.common;

/**
 * <Pre>
 *     网络请求相关配置
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:24
 */
public interface BizInterface {
    /**
     * 百度API接口
     */
    String API = "http://apis.baidu.com";
    /**
     * 开发者API密钥
     */
    String API_KEY = "4720bdbcfb3aa457eefd38d2f8fa580f";
    /**
     * 新闻接口
     服务商： 易源接口
     */
    String NEWS_URL = "/showapi_open_bus/channel_news/search_news";
    /**
     * 天气预报 (根据地名)
     服务商： 易源接口
     */
    String WEATHER_URL = "/showapi_open_bus/weather_showapi/address";

    /**
     * 美图大全 (根据类型)
     * "list": [
     {
     "id": 4001, //此id很重要，在【图片查询】接口里将使用此id进行分类查询
     "name": "清纯"
     },
     {
     "id": 4002,
     "name": "气质"
     },
     {
     "id": 4003,
     "name": "萌女"
     },
     {
     "id": 4004,
     "name": "校花"
     },
     {
     "id": 4005,
     "name": "婚纱"
     },
     {
     "id": 4006,
     "name": "街拍"
     },
     {
     "id": 4007,
     "name": "非主流"
     },
     {
     "id": 4008,
     "name": "美腿"
     },
     {
     "id": 4009,
     "name": "性感"
     },
     {
     "id": 4010,
     "name": "车模"
     },
     {
     "id": 4011,
     "name": "男色图片"
     },
     {
     "id": 4012,
     "name": "模特美女"
     },
     {
     "id": 4013,
     "name": "美女魅惑"
     },
     {
     "id": 4014,
     "name": "日韩美女"
     }
     ],
     服务商： 易源接口
     */
    String PICTURES_URL = "/showapi_open_bus/pic/pic_search";

}
