package com.wenzhan.blog.service.impl;

import com.wenzhan.blog.dao.AdminBlogDao;
import com.wenzhan.blog.service.AdminService;
import com.wenzhan.blog.dto.Page;
import com.wenzhan.blog.entity.Blog;
import com.wenzhan.blog.entity.Tag;
import com.wenzhan.blog.entity.Type;
import com.wenzhan.blog.enums.PageEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminBlogDao adminBlogDao;

    @Override
    public Page<Blog> getBlogList(int pageNum, String name) {
        long count = adminBlogDao.getBlogCount(name.trim());
        Page page = new Page(PageEnum.adminPageSize.getPageSize(), count, pageNum);
        List<Blog> blogList = adminBlogDao.getBlog(page.getOffSet(), page.getPageSize(), name);
        page.setResult(blogList);
        return page;
    }

    /**
     * 插入博客
     *
     * @param blog
     * @param blogTag
     * @return
     */
    @Override
    public int insertBlog(Blog blog, String blogTag) {
        adminBlogDao.insertBlog(blog);

        String[] tags = blogTag.split(",");
        List<HashMap<String, Integer>> tagList = new ArrayList<>();
        HashMap<String, Integer> map;
        for (int i = 0; i < tags.length; i++) {
            map = new HashMap<>();
            map.put("blogId", blog.getBlogId());
            map.put("tagId", Integer.parseInt(tags[i]));
            tagList.add(map);
        }

        adminBlogDao.insertBlogTagByBlogId(tagList);

        return blog.getBlogId();
    }

    /**
     * 删除博客
     *
     * @param blogId
     */
    @Override
    public void delBlog(long blogId) {
        adminBlogDao.delBlog(blogId);
        adminBlogDao.delBlogTag(blogId);
    }

    /**
     * 获取博客信息
     *
     * @param blogId
     * @return
     */
    @Override
    public HashMap<String, Object> getBlog(long blogId) {
        HashMap<String, Object> map = new HashMap<>();
        Blog blog = adminBlogDao.getBlogById(blogId);
        List<Tag> tagList = adminBlogDao.getBlogTagById(blogId);
        map.put("blog", blog);
        map.put("tagList", tagList);
        return map;
    }

    /**
     * 修改博客
     *
     * @param blog
     * @param blogTag
     * @return
     */
    @Override
    public void updateBlog(Blog blog, String blogTag) {
        adminBlogDao.updateBlog(blog);
        adminBlogDao.delBlogTag(blog.getBlogId());
        String[] tags = blogTag.split(",");
        List<HashMap<String, Integer>> tagList = new ArrayList<>();
        HashMap<String, Integer> map;
        for (int i = 0; i < tags.length; i++) {
            map = new HashMap<>();
            map.put("blogId", blog.getBlogId());
            map.put("tagId", Integer.parseInt(tags[i]));
            tagList.add(map);
        }

        adminBlogDao.insertBlogTagByBlogId(tagList);

    }

    @Override
    public Page getBlogTypeList(int pageNum) {
        long count = adminBlogDao.getBlogTypeCount();
        Page page = new Page(PageEnum.adminPageSize.getPageSize(), count, pageNum);
        List<Type> blogTypeList = adminBlogDao.getBlogType(page.getOffSet(), page.getPageSize());
        page.setResult(blogTypeList);
        return page;
    }

    @Override
    public void addBlogType(String typeName) {
        adminBlogDao.insertBlogType(typeName);
    }

    @Override
    public Page getBlogTagList(int pageNum) {
        long count = adminBlogDao.getBlogTagCount();
        Page page = new Page(PageEnum.adminPageSize.getPageSize(), count, pageNum);
        List<Tag> blogTagList = adminBlogDao.getBlogTag(page.getOffSet(), page.getPageSize());
        page.setResult(blogTagList);
        return page;
    }

    @Override
    public void addBlogTag(String tagName) {
        adminBlogDao.insertBlogTag(tagName);
    }

    @Override
    public void updateBlogImg(String blogImg, long blogId) {
        adminBlogDao.updateBlogImg(blogImg, blogId);
    }

}
