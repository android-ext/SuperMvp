package com.ly.supermvp.model.pictures;

import com.ly.supermvp.model.OnNetRequestListener;

import java.util.List;

/**
 * <Pre>
 * 图片大全数据接口
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:02
 */
public interface PicturesModel {
    String DEFAULT_TYPE = "4001";//类别 "清纯"

    void netLoadPictures(String type, int page, OnNetRequestListener<List<PictureBody>> listener);
}
