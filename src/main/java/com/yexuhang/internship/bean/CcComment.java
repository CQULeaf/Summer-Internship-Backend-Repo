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
 * 回答评论表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_comment")
public class CcComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回答评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 原始正文内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 评论点赞数量
     */
    private Integer likeCount;

    /**
     * 帖子id
     */
    private Integer postId;
}
