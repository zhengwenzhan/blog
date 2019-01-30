package com.wenzhan.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type implements Serializable {

    //主键ID
    private int typeId;

    //类型名称
    private String typeName;

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
