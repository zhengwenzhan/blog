package com.wenzhan.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {

    private static final long serialVersionUID = -3335005137621958470L;

    /**
     * 标签ID
     */
    private int tagId;

    /**
     * 标签名称
     */
    private String tagName;

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}