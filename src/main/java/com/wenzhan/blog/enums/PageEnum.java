package com.wenzhan.blog.enums;

public enum PageEnum {
    adminPageSize(10),
    blogPageSize(5);

    private int pageSize;

    PageEnum(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageEnum{" +
                "pageSize=" + pageSize +
                '}';
    }
}
