package com.yexuhang.internship.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * @author Xuhang Ye
 */
@Data
@TableName("user")
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String avatar;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String nickname;
    private String bio;
    private String gender;
}
