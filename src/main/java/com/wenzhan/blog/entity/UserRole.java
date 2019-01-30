package com.wenzhan.blog.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2912490331468084113L;

    /**
     * 主键
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色
     */
    private String role;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
