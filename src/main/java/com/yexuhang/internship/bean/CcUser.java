package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-21
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
     * 封面图片token
     */
    private String cover;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册IP
     */
    private String createIp;

    /**
     * 注册地址
     */
    private String createLocation;

    /**
     * 最后登录时间
     */
    private Integer lastLoginTime;

    /**
     * 最后登陆IP
     */
    private String lastLoginIp;

    /**
     * 最后登录地址
     */
    private String lastLoginLocation;

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
    private Integer followingArticleCount;

    /**
     * 我关注的问题数
     */
    private Integer followingQuestionCount;

    /**
     * 我关注的话题数
     */
    private Integer followingTopicCount;

    /**
     * 我发表的文章数量
     */
    private Integer articleCount;

    /**
     * 我发表的问题数量
     */
    private Integer questionCount;

    /**
     * 我发表的回答数量
     */
    private Integer answerCount;

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
     * 个人主页
     */
    private String blog;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 地址
     */
    private String location;

    /**
     * 注册时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 禁用时间
     */
    private Integer disableTime;
}
