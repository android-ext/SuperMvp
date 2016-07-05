package com.ly.supermvp.delegate;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.ly.supermvp.R;
import com.ly.supermvp.mvp_frame.view.AppDelegate;
import com.ly.supermvp.utils.GlideUtil;
import com.rey.material.widget.ProgressView;

import butterknife.Bind;

/**
 * <Pre>
 *     新闻详情界面代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/6 15:34
 */
public class NewsDetailActivityDelegate extends AppDelegate{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progress)
    ProgressView mProgressView;
    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.iv_detail)
    ImageView mImageView;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initWebView();
    }

    /**
     * 初始化webview
     */
    private void initWebView() {
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式(true);
        ws.setAppCacheEnabled(true);
        ws.setSupportZoom(false);
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setJavaScriptCanOpenWindowsAutomatically(true);//js支持
        ws.setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressView.setVisibility(View.GONE);
            }

        });
    }

    @Override
    public android.support.v7.widget.Toolbar getToolbar() {
        return mToolbar;
    }

    public void setCollapsingToolbarLayoutTitle(String title){
        mCollapsingToolbarLayout.setTitle(title);
    }

    public void setImageWithURL(String url){
        GlideUtil.loadImage(getActivity(), url, mImageView);
    }

    public void loadNewsDetail(String url){
        mWebView.loadUrl(url);
    }
}
