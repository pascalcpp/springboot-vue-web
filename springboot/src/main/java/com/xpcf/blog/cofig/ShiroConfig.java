package com.xpcf.blog.cofig;

import com.xpcf.blog.shiro.AccountRealm;
import com.xpcf.blog.shiro.JwtFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSentinelManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 6:57 PM
 */
@Configuration
public class ShiroConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO){

        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;

    }

    @Bean
    public DefaultSecurityManager securityManager(AccountRealm accountRealm,
                                           SessionManager sessionManager,
                                           RedisCacheManager redisCacheManager){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(redisCacheManager);

        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){

        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**","jwt");

        chainDefinition.addPathDefinitions(filterMap);

        return chainDefinition;

    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition){

        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt",jwtFilter);
        filterFactoryBean.setFilters(filters);


        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return filterFactoryBean;


    }


}
