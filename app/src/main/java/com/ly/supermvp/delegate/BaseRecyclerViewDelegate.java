package com.ly.supermvp.delegate;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.common.Constants;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.view.LoadingView;
import com.ly.supermvp.widget.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * <Pre>
 *     recycleview通用视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/23 13:53
 */
public abstract class BaseRecyclerViewDelegate extends AppDelegate implements LoadingView{
    @Bind(R.id.progress_layout)
    ProgressLayout progress_layout;//进度条布局（通用，可实现错误按钮，点击重试）
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;//下拉刷新控件
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    //悬浮菜单
    @Bind(R.id.floating_action_menu)
    FloatingActionMenu floating_action_menu;
    @Bind(R.id.floating_action_button1)
    FloatingActionButton floating_action_button1;
    @Bind(R.id.floating_action_button2)
    FloatingActionButton floating_action_button2;
    @Bind(R.id.floating_action_button3)
    FloatingActionButton floating_action_button3;
    @Bind(R.id.floating_action_button4)
    FloatingActionButton floating_action_button4;

    protected List<FloatingActionButton> mFloatingActionButtons;//悬浮菜单选项数组

    /**
     * 初始化recyclerview，必须重写
     */
    abstract void initRecyclerView();
    /**
     * 设置悬浮菜单是否显示，必须重写
     */
    abstract boolean setFloatingActionMenuVisible();

    @Override
    public int getRootLayoutId() {
        return R.layout.layout_base_recyclerview;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initSwipeRefreshLayout();
        initRecyclerView();
        initFloatingActionMenu();
    }
    /**
     * 初始化悬浮菜单
     */
    private void initFloatingActionMenu() {
        floating_action_menu.setVisibility(setFloatingActionMenuVisible() ? View.VISIBLE : View.GONE);
        floating_action_menu.setClosedOnTouchOutside(true);
        mFloatingActionButtons = new ArrayList<>();
        mFloatingActionButtons.add(floating_action_button1);
        mFloatingActionButtons.add(floating_action_button2);
        mFloatingActionButtons.add(floating_action_button3);
        mFloatingActionButtons.add(floating_action_button4);
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeResources(Constants.colors);//设置下拉刷新控件变换的四个颜色
    }

    /**
     * 设置是否隐藏悬浮菜单选项卡
     * @param animate 是否动画
     */
    public void hideMenu(boolean animate){
        floating_action_menu.close(animate);
    }

    /**
     * 设置下拉刷新接口
     *
     * @param callBack 下拉刷新的回调接口
     */
    public void registerSwipeRefreshCallBack(final SwipeRefreshAndLoadMoreCallBack callBack) {
        RxSwipeRefreshLayout.refreshes(swipe_refresh_layout).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                // 2016/2/29 调用fragment的方法加载数据，需要解耦(已用接口解决)
                callBack.refresh();
            }
        });
    }

    public void setListAdapter(RecyclerView.Adapter adapter) {
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        progress_layout.showLoading();
    }

    @Override
    public void showContent() {
        RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).call(false);
        if (!progress_layout.isContent()) {
            progress_layout.showContent();
        }
    }

    @Override
    public void showError(int messageId, View.OnClickListener listener) {
        RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).call(false);
        if (!progress_layout.isError()) {
            progress_layout.showError(messageId, listener);
        }
    }
    @Override
    public Context getContext() {
        return null;
    }
}
