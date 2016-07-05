package com.ly.supermvp.view;

import android.content.Context;
import android.view.View;

/**
 * <Pre>
 *     加载视图接口
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/28 16:27
 */
public interface LoadingView {
    void showLoading();
    void showContent();
    void showError(int messageId, View.OnClickListener listener);
    Context getContext();
}
