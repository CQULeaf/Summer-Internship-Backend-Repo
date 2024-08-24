package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_user")
public class CcUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像token
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 关注我的人数
     */
    private Integer followerCount;

    /**
     * 我关注的人数
     */
    private Integer followeeCount;

    /**
     * 我关注的文章数
     */
    private Integer likingPostCount;

    /**
     * 我发表的文章数量
     */
    private Integer postCount;

    /**
     * 未读通知数
     */
    private Integer notificationUnread;

    /**
     * 未读私信数
     */
    private Integer inboxUnread;

    /**
     * 一句话介绍
     */
    private String headline;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 注册时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 禁用时间
     */
    private LocalDateTime disabledAt;

    /**
     * 朋友的人数
     */
    private Integer friendCount;

    /**
     * 用户电话号码
     */
    private String phoneNumber;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 家乡
     */
    private String hometown;

    /**
     * 用户专业
     */
    private String major;

    /**
     * 用户MBTI
     */
    private String mbti;

  
    /**
     * 用户昵称
     */
    private String nickname;
}
