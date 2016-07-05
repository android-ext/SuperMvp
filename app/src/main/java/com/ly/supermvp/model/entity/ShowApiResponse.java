package com.ly.supermvp.model.entity;

/**
 * <Pre>
 *     易源API通用响应数据
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/1 14:29
 */
public class ShowApiResponse<T> {
    public String showapi_res_code;
    public String showapi_res_error;
    public T showapi_res_body;
}
