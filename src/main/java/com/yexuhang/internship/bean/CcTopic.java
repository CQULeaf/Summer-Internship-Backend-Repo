package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 话题表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_topic")
public class CcTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 话题ID
     */
    @TableId(value = "topic_id", type = IdType.AUTO)
    private Integer topicId;

    /**
     * 话题名称
     */
    private String name;

    /**
     * 封面图片token
     */
    private String cover;

    /**
     * 话题描述
     */
    private String description;

    /**
     * 帖子数量
     */
    private Integer postCount;

    /**
     * 关注者数量
     */
    private Integer followerCount;

    private String flag;
}
