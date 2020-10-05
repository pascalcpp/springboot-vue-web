package com.xpcf.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 5:25 AM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    private String avatar;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String password;
    private Integer status;
    private LocalDateTime created;
    private LocalDateTime lastLogin;



}
