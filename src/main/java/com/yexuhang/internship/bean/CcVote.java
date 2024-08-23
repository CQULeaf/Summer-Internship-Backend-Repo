package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_vote")
public class CcVote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 投票目标ID
     */
    private Integer votableId;

    /**
     * 投票目标类型 question、answer、article、comment
     */
    private String votableType;

    /**
     * 投票类型 up、down
     */
    private String type;

    /**
     * 投票时间
     */
    private Integer createTime;
}
