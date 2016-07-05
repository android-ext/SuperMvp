package com.ly.supermvp.view.activity;

import android.view.View;

import com.ly.supermvp.delegate.NewsDetailActivityDelegate;
import com.ly.supermvp.mvp_frame.presenter.ActivityPresenter;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * <Pre>
 *     新闻详情界面
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/6 15:33
 */
public class NewsDetailActivity extends ActivityPresenter<NewsDetailActivityDelegate>{
    /**
     * 需要点击列表传递过来的新闻详情链接
     */
    public static final String ARG_NEWS_URL = "arg_news_url";
    /**
     * 需要传递过来的新闻图片
     */
    public static final String ARG_NEWS_PIC = "arg_news_pic";
    /**
     * 需要传递过来的新闻标题
     */
    public static final String ARG_NEWS_TITLE = "arg_news_title";
    private String mUrl = "";
    private String mPic = "";
    private String mTitle = "";

    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected Class<NewsDetailActivityDelegate> getDelegateClass() {
        return NewsDetailActivityDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        if(getIntent().getExtras() != null){
            mUrl = getIntent().getStringExtra(ARG_NEWS_URL);
            mPic = getIntent().getStringExtra(ARG_NEWS_PIC);
            mTitle = getIntent().getStringExtra(ARG_NEWS_TITLE);
        }else {
            finish();
            viewDelegate.showToast("参数有误");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setSupportActionBar(viewDelegate.getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewDelegate.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        viewDelegate.setCollapsingToolbarLayoutTitle(mTitle);
        viewDelegate.setImageWithURL(mPic);
        viewDelegate.loadNewsDetail(mUrl);
    }
}
