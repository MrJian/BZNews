package com.bz.utils;

/**
 * Created by Administrator on 2015/11/17.
 */
public interface Constans {

     interface URL {

         //首页
         String HOME_NEWS = "http://ribao.ibaozou.com/api/v1/articles/latest";
         // 首页下拉刷新
         String HOME_BEFORE = "http://ribao.ibaozou.com/api/v1/articles/before/";
         // 排行榜
         String RANKING = "http://daily.ibaozou.com/api/2/articles/hot";
         // 栏目
         String LIST = "http://ribao.ibaozou.com/api/v1/sections";

         String WELCOME = "http://daily.ibaozou.com/api/2/start-image/";

         String Comment = "http://dailyapi.ibaozou.com/api/v2/articles/";

         String CommentHeaderS = "http://baozouribao.com/api/v1/articles/";
    }
}
