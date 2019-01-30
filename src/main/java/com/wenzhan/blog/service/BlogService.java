package com.wenzhan.blog.service;

import java.util.HashMap;
import java.util.List;

import com.wenzhan.blog.entity.Blog;
import com.wenzhan.blog.entity.Tag;
import com.wenzhan.blog.entity.Type;

public interface BlogService {
	
	/**
	 * 获取所有博客信息
	 * @return
	 */
	List<Blog> getAllBlog();

	/**
	 * 获取所有博客类型
	 * @return
	 */
	List<Type> getAllBlogType();
	
	/**
	 * 获取所有博客标签
	 * @return
	 */
	List<Tag> getAllBlogTag();

	/**
	 * 通过blogId获取博客信息
	 * @param blogId
	 * @return
	 */
	Blog getBlogInfoById(int blogId);
	
	/**
	 * 通过blogId获取博客标签
	 * @param blogId
	 * @return
	 */
	List<String> getTagListById(int blogId);
	
	/**
	 * 获取所有博客Id
	 * @return
	 */
	List<String> getAllBlogId(String blogType);
	
	/**
	 * 获取最新博客
	 * @return
	 */
	List<Blog> getNewBlog();
	
	/**
	 * 修改博客访问量
	 * @param list
	 */
	void updateBlogNum(List<HashMap<String, Object>> list);
	
	/**
	 * 获取博客标签
	 * @return
	 */
	List<HashMap<String, Object>> getTagList();
}
