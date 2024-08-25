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
 * 文章表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_post")
public class CcPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 原始的正文内容
     */
    private String postContent;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 关注者数量
     */
    private Integer likeCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 帖子封面
     */
    private String cover;

    /**
     * 话题ID
     */
    private Integer topicId;
}
