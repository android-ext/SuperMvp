package com.ly.supermvp.model.news;

import java.util.List;

/**
 * <Pre>
 *     新闻列表实体类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:28
 */
public class ShowApiNews {
    public PageBean pagebean;
    public String ret_code;
    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<NewsBody> contentlist;
    }
}
