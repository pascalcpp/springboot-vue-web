package com.xpcf.blog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 7:48 PM
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}
