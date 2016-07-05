package com.ly.supermvp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.model.news.NewsBody;
import com.ly.supermvp.utils.GlideUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <Pre>
 * 新闻列表适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 16:26
 */
public class NewsListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0x00;//内容
    private static final int TYPE_FOOTER = 0x01;//加载更多

    private Activity context;
    private List<NewsBody> newsBodies;


    /**
     * 条目点击监听
     */
    private OnItemClickListener mOnItemClickListener;

    /**
     * 是否显示加载更多视图
     */
    private boolean mShowFooter = true;

    public NewsListAdapter(Activity context, List<NewsBody> newsBodies) {
        this.context = context;
        this.newsBodies = newsBodies;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewsBody item = newsBodies.get(position);
            ItemViewHolder holder1 = (ItemViewHolder) holder;

            if (item.imageurls != null && item.imageurls.size() > 0) {
                GlideUtil.loadImage(context, item.imageurls.get(0).url, holder1.imageView);
            } else {
                GlideUtil.loadImage(context, "", holder1.imageView);
            }
            holder1.desc.setText(item.desc);
            holder1.title.setText(item.title);
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (this.newsBodies == null) {
            return begin;
        }
        return this.newsBodies.size() + begin;
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tv_desc)
        TextView desc;
        @Bind(R.id.iv_desc)
        ImageView imageView;
        @Bind(R.id.tv_title)
        TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v, this.getPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 点击条目接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
