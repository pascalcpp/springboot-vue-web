package com.xpcf.blog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/4/2020 8:18 PM
 */
@Data
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;


}
