package com.xpcf.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpcf.blog.common.lang.Result;
import com.xpcf.blog.pojo.entity.Blog;
import com.xpcf.blog.service.BlogService;
import com.xpcf.blog.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/5/2020 12:37 AM
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(value = "currentPage" , defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable("id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客不存在");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        Blog temp = null;
        if(blog.getId() != null){
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().equals(ShiroUtils.getProfile().getId()),"你没有权限");
        }else {
            temp = new Blog();
            temp.setUserId(ShiroUtils.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");

        blogService.saveOrUpdate(temp);
        return Result.success(null);

    }
}
