package com.bz.event;

import com.bz.bean.CommentBase;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public class DetailCommentEvent {

    public List<CommentBase> commentBase;

    public DetailCommentEvent(List<CommentBase> commentBase) {
        this.commentBase = commentBase;
    }
}
