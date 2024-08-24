package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章关注关系表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_follow")
public class CcFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 关注目标的ID
     */
    private Integer followableId;

    /**
     * 关注目标类型 user、question、article、topic
     */
    private String followableType;

    /**
     * 关注时间
     */
    private Integer createTime;
}
