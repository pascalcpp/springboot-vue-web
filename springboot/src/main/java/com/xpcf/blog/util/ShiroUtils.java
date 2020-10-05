package com.xpcf.blog.util;

import com.xpcf.blog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 7:47 PM
 */
public class ShiroUtils {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
