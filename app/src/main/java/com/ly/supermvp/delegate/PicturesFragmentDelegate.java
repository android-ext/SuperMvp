package com.ly.supermvp.delegate;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.ly.supermvp.R;
import com.ly.supermvp.adapter.PictureGridAdapter;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.view.LoadingView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * <Pre>
 * 美图大全视图代理类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 14:52
 */
public class PicturesFragmentDelegate extends BaseRecyclerViewDelegate implements LoadingView {
    private static final int PRELOAD_SIZE = 6;
    private LinearLayout ll_dialog_holder;//弹窗的布局
    private DialogPlus mDialog;

    private StaggeredGridLayoutManager mGridViewLayoutManager;//recycleview视图样式管理器


    @Override
    void initRecyclerView() {
        mGridViewLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mGridViewLayoutManager);
    }

    @Override
    boolean setFloatingActionMenuVisible() {
        return true;
    }

    /**
     * 设置加载更多接口
     *
     * @param callBack 加载更多的回调
     */
    public void registerLoadMoreCallBack(final SwipeRefreshAndLoadMoreCallBack callBack, final PictureGridAdapter adapter) {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = mGridViewLayoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
                        >= adapter.getItemCount() - PRELOAD_SIZE;
                    if (!swipe_refresh_layout.isRefreshing() && isBottom) {
                        callBack.loadMore();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }


    public void showRefreshLayout() {
        if (!swipe_refresh_layout.isRefreshing()) {
            RxSwipeRefreshLayout.refreshing(swipe_refresh_layout).call(true);
        }
    }


    public void showDialog(String imgUrl) {
        ll_dialog_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
        Holder holder = new ViewHolder(ll_dialog_holder);
        PhotoView photo_view = (PhotoView) holder.getInflatedView().findViewById(R.id.photo_view);
        photo_view.enable();//启动缩放功能
        GlideUtil.loadImage(getActivity(), imgUrl, photo_view);
        photo_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        showOnlyContentDialog(holder, Gravity.TOP, false);
    }

    /**
     * 设置悬浮菜单项目点击事件
     */
    public void setFloatingActionButtonListener(final FloatingActionButtonListener listener){
        for(int i = 0; i < mFloatingActionButtons.size(); i++){
            String id;
            switch (i) {
                case 0 : id = "4001"; break;
                case 1 : id = "4002"; break;
                case 2 : id = "4003"; break;
                case 3 : id = "4004"; break;
                default: id = "4001"; break;
            }
            final String finalId = id;
            mFloatingActionButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(finalId);
                    hideMenu(true);
                }
            });
        }
    }

    public interface FloatingActionButtonListener{
        void onClick(String id);
    }

    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity  显示位置（居中，底部，顶部）
     * @param expanded 是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        mDialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        mDialog.show();
    }
}
