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
 * 举报
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_report")
public class CcReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "report_id", type = IdType.AUTO)
    private Integer reportId;

    /**
     * 举报目标ID
     */
    private Integer reportableId;

    /**
     * 举报目标类型：post、comment、user
     */
    private String reportableType;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 举报原因
     */
    private String reason;

    /**
     * 举报时间
     */
    private Integer createTime;
}
