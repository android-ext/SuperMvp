package com.ly.supermvp.model.pictures;

import java.util.List;

/**
 * <Pre>
 *     美图大全实体类
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 15:39
 */
public class ShowApiPictures {
    public PageBean pagebean;
    public String ret_code;
    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<PictureBody> contentlist;
    }
}
