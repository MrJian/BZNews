package com.bz.bean;

/**
 * Created by Administrator on 2015/11/19.
 */
public class CommentBase {


    /**
     * score : 0
     * like : false
     * hottest : false
     * dislike : false
     * own : false
     * dislikes : 0
     * id : 1701809
     * user : {"real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/a0b1cb5f0","name":"只想凝视着我","id":180113}
     * content : 瞧瞧人家的态度
     * readable_time : 2015-11-19T14:08:14+08:00
     * likes : 0
     */
    public int score;
    public boolean like;
    public boolean hottest;
    public boolean dislike;
    public boolean own;
    public int dislikes;
    public int id;
    public UserEntity user;
    public String content;
    public String readable_time;
    public int likes;

    public int type;
    public String date;


    public class UserEntity {
        /**
         * real_avatar_url : http://zhihu.b0.upaiyun.com/avatar/a0b1cb5f0
         * name : 只想凝视着我
         * id : 180113
         */
        public String real_avatar_url;
        public String name;
        public int id;


    }


}
