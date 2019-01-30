package com.wenzhan.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = 391463107086735437L;

    //主键ID
    private int blogId;

    //博客名称
    private String blogName;

    //博客图片
    private String blogImg;

    //博客介绍
    private String introduction;

    //博客内容
    private String content;

    //发布时间
    private Date time;

    //浏览数量
    private int browse;

    //赞的数量
    private int praise;

    //博客类型
    private String blogType;

    //转载Url
    private String reprintedUrl;

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogName='" + blogName + '\'' +
                ", blogImg='" + blogImg + '\'' +
                ", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", browse=" + browse +
                ", praise=" + praise +
                ", blogType='" + blogType + '\'' +
                ", reprintedUrl='" + reprintedUrl + '\'' +
                '}';
    }
}
