package com.wenzhan.blog.controller;

import com.wenzhan.blog.cache.RedisCache;
import com.wenzhan.blog.config.GithubConfig;
import com.wenzhan.blog.dto.BlogResult;
import com.wenzhan.blog.entity.Blog;
import com.wenzhan.blog.entity.Tag;
import com.wenzhan.blog.entity.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisCache redisCache;

//    @Resource
//    private SolrIndex solrIndex;

    @Resource
    private GithubConfig githubConfig;

    /**
     * right页面的内容
     * @param model
     */
    public void rightContent(Model model){
        List<Type> blogTypeList = redisCache.getBlogType();
        List<Tag> blogTagList = redisCache.getBlogTag();
        List<Blog> newBlogList = redisCache.getNewBlog();
        List<Blog> readBlogList = redisCache.getBlogByRead();

        model.addAttribute("newBlogList", newBlogList);
        model.addAttribute("blogTypeList", blogTypeList);
        model.addAttribute("blogTagList", blogTagList);
        model.addAttribute("readBlogList", readBlogList);
    }

    /**
     * 跳转到博客列表
     * @param model
     * @return
     */
    @GetMapping(value = "")
    public String goBlog(Model model) {
        try {
            long blogNum = redisCache.getBlogNum();
            long page = redisCache.getBlogPage(null);

            model.addAttribute("blogNum", blogNum);
            model.addAttribute("page", page);
            rightContent(model);
        }catch (Exception e) {
            logger.error("goBlog:" + e);
            e.printStackTrace();
        }finally {
            return "blog/blogList";
        }
    }

    /**
     * 跳转到博客列表（带类型）
     * @param model
     * @return
     */
    @GetMapping(value = "/{blogType}")
    public String goBlog(Model model,
                         @PathVariable("blogType") String blogType) {
        try {
            long blogNum = redisCache.getBlogNum();
            long page = redisCache.getBlogPage(blogType);

            model.addAttribute("blogType", blogType);
            model.addAttribute("blogNum", blogNum);
            model.addAttribute("page", page);
            rightContent(model);
        }catch (Exception e) {
            logger.error("goBlog:" + e);
            e.printStackTrace();
        }finally {
            return "blog/blogList";
        }
    }

    /**
     * 跳转到博客列表（带搜索条件）
     * @param model
     * @return
     */
//    @GetMapping(value = "/solr/{keywords}")
//    public String goBlogBySolr(Model model,
//                         @PathVariable("keywords") String keywords) {
//        try {
//            long blogNum = redisCache.getBlogNum();
//            long page = solrIndex.getBlogPage(keywords);
//
//            model.addAttribute("keywords", keywords);
//            model.addAttribute("blogNum", blogNum);
//            model.addAttribute("page", page);
//            rightContent(model);
//        }catch (Exception e) {
//            logger.error("goBlogBySolr:" + e);
//            e.printStackTrace();
//        }finally {
//            return "blog/blogList";
//        }
//    }

    /**
     * 获取博客列表数据
     * @param page
     * @param blogType
     * @return
     */
    @PostMapping(value = "/list", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public BlogResult getBlogList(@RequestParam(value="page",defaultValue="1",required=false)int page,
                                  @RequestParam(value="blogType",defaultValue="",required=false) String blogType) {
        BlogResult result = null;
        try {
            List<String> blogIdList = redisCache.getBlogIdList(page, blogType);
            if(blogIdList == null || blogIdList.isEmpty()){
                result = new BlogResult(false, "没有博客数据！");
                return result;
            }

            List<HashMap<String, Object>> blogInfoList = redisCache.getBlogInfoList(blogIdList);
            result = new BlogResult(true, blogInfoList);
        }catch (Exception e) {
            result = new BlogResult(false, e.getMessage());
            logger.error("getBlogList:" + e);
            e.printStackTrace();
        }finally {
            return  result;
        }

    }

    /**
     * 获取博客列表数据
     * @param page
     * @param keywords
     * @return
     */
//    @PostMapping(value = "/listBySolr", produces = { "application/json;charset=UTF-8" })
//    @ResponseBody
//    public BlogResult getBlogListBySolr(@RequestParam(value="page",defaultValue="1",required=false)int page,
//                                  @RequestParam(value="keywords",defaultValue="",required=false) String keywords) {
//        BlogResult result = null;
//        try {
//            List<HashMap<String, Object>> blogInfoList = solrIndex.querySolr(keywords, page);
//            if(blogInfoList == null || blogInfoList.isEmpty()){
//                result = new BlogResult(false, "没有跟 " + keywords + " 匹配的博客！");
//                return result;
//            }
//            result = new BlogResult(true, blogInfoList);
//        }catch (Exception e) {
//            result = new BlogResult(false, e.getMessage());
//            logger.error("getBlogListBySolr:" + e);
//            e.printStackTrace();
//        }finally {
//            return  result;
//        }
//
//    }

    /**
     * 获取博客详情
     * @param model
     * @param blogId
     * @return
     */
    @GetMapping(value = "/info/{blogId}")
    public String goBlogInfo(Model model, @PathVariable("blogId") String blogId) {
        try {
            long blogNum = redisCache.getBlogNumById(blogId);
            HashMap<String, Object> blogInfo = redisCache.getBlogInfo(blogId);
            model.addAttribute("blogInfo", blogInfo);
            model.addAttribute("blogNum", blogNum);
            model.addAttribute("githubConfig", githubConfig);
            rightContent(model);
        } catch (Exception e) {
            logger.error("goBlogInfo:" + e);
        }finally {
            return "blog/blogInfo";
        }

    }

}
