package com.xpcf.blog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import javax.net.ssl.SSLContext;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 7:42 PM
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token){
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
