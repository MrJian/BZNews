package com.bz.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class CommentsBean {


    /**
     * per_page : 10
     * hottest : [{"score":990203,"like":false,"hottest":true,"dislike":false,"own":false,"dislikes":0,"id":1698617,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/50ff6d20a","name":"我cao尼玛币","id":250462},"content":"我对于恐怖分子表示强烈谴责","readable_time":"2015-11-18T11:04:51+08:00","likes":166},{"score":986928,"like":false,"hottest":true,"dislike":false,"own":false,"dislikes":0,"id":1698668,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/04007023e","name":"锯国元首","id":151664},"content":"普京发动\u201c大帝之怒\u201d！\nISIS受到 299792458点伤害！\nISIS卒\u2026\u2026","readable_time":"2015-11-18T11:47:15+08:00","likes":124},{"score":981472,"like":false,"hottest":true,"dislike":false,"own":false,"dislikes":0,"id":1698631,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/b05f864dd","name":"吃热干面不要芝麻酱不要萝卜不要葱","id":418452},"content":"007:普大帝之怒火滔天","readable_time":"2015-11-18T11:16:27+08:00","likes":87}]
     * total_count : 344
     * total_pages : 35
     * page : 1
     * latest : [{"score":0,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701809,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/a0b1cb5f0","name":"只想凝视着我","id":180113},"content":"瞧瞧人家的态度","readable_time":"2015-11-19T14:08:14+08:00","likes":0},{"score":0,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701626,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/85cddf71b","name":"阿狸ahri小姐","id":237213},"content":"想起以前那句话⋯⋯恐怖分子在中国不可能作案成功⋯⋯因为想在人多的时候发动恐怖袭击，只有春运，然而十个恐怖分子只有三个买到了车票，只有一个挤上了火车，然后挤上车的恐怖分子发现炸药被偷了","readable_time":"2015-11-19T13:07:01+08:00","likes":0},{"score":549092,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701338,"user":{"real_avatar_url":"http://wanzao2.b0.upaiyun.com/system/avatars/1925677/original/20151023140954367.jpg-s1","name":"撸完管用妹纸擦","id":285263},"content":"对ISIS就一句话一句话：作得越大，死得越惨~！！","readable_time":"2015-11-19T10:19:11+08:00","likes":2},{"score":378448,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701312,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/7b0587d9f","name":"最佳第六人","id":393460},"content":"牛","readable_time":"2015-11-19T09:56:54+08:00","likes":1},{"score":0,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701275,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/6407f8b5f","name":"branren","id":373633},"content":"有人就有恩怨，有恩怨就有江湖，人就是江湖。地球真是永远安宁不了","readable_time":"2015-11-19T09:24:48+08:00","likes":0},{"score":646221,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701257,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/7ed99ae32","name":"真言术孕","id":377005},"content":"对于此种恐怖袭击我表示谴责，严正的谴责，我谴责死恐怖分子。","readable_time":"2015-11-19T09:12:01+08:00","likes":3},{"score":809963,"like":false,"hottest":true,"dislike":false,"own":false,"dislikes":0,"id":1701112,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/b328a91bc","name":"毛毛","id":293934},"content":"现在终于有目标打了，各国都在展示自己的先进武器并予以测试，中国当然也在队列中。中国发动嘴炮攻击，对IS造成000000点伤害","readable_time":"2015-11-19T06:35:38+08:00","likes":7},{"score":646221,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701111,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/0a3be5df5","name":"嗯可以吃","id":84990},"content":"不得不说。我真的很讨厌伊斯兰教","readable_time":"2015-11-19T06:31:30+08:00","likes":3},{"score":378448,"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701101,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/9c6485ba5","name":"OM_Nicki","id":424984},"content":"这两天莫斯科都快疯了 不仅火车站地铁搜出携带炸弹的恐怖分子 而且人口密集地区分分钟上演枪战呀\u2026\u2026然而新闻不会报道的","readable_time":"2015-11-19T06:02:03+08:00","likes":1},{"score":0,"parent":{"user_id":331587,"user_name":"歪小毛","id":1700781},"like":false,"hottest":false,"dislike":false,"own":false,"dislikes":0,"id":1701079,"user":{"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/6520506ce","name":"keyboard-man","id":122718},"content":"\u2026\u2026都说了是对叙利亚sis基地进行轰炸\u2026\u2026你以为是对平民乱炸啊","readable_time":"2015-11-19T04:43:23+08:00","likes":0}]
     */
    public int per_page;
    public List<HottestEntity> hottest;
    public int total_count;
    public int total_pages;
    public int page;
    public List<LatestEntity> latest;


}
