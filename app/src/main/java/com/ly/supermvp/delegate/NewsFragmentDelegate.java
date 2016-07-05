package com.ly.supermvp.delegate;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ly.supermvp.adapter.NewsListAdapter;
import com.ly.supermvp.view.LoadingView;

/**
 * <Pre>
 *     新闻页面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 14:34
 */
public class NewsFragmentDelegate extends BaseRecyclerViewDelegate implements LoadingView{
    /**
     * 用于加载更多的列表布局管理器
     */
    private LinearLayoutManager mRecycleViewLayoutManager;

    @Override
    void initRecyclerView() {
        //设置分割线
//        recyclerview.addItemDecoration(new ListItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setHasFixedSize(true);
        mRecycleViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(mRecycleViewLayoutManager);
    }

    @Override
    boolean setFloatingActionMenuVisible() {
        return false;
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    public void registerLoadMoreCallBack(final SwipeRefreshAndLoadMoreCallBack callBack, final NewsListAdapter adapter) {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mRecycleViewLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.isShowFooter()) {
                    //加载更多
                    callBack.loadMore();
                }
            }
        });
    }
}
