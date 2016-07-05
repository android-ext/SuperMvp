package com.ly.supermvp.delegate;

/**
 * <Pre>
 * 下拉刷新与加载更多接口，用于presenter与view解耦
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/22 11:03
 */
public interface SwipeRefreshAndLoadMoreCallBack {
    /**
     * 下拉刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();
}
