package com.xpcf.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xpcf.blog.mapper.BlogMapper;
import com.xpcf.blog.pojo.entity.Blog;
import com.xpcf.blog.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-05-25
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
