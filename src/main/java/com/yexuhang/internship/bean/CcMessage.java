package com.yexuhang.internship.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cc_message")
public class CcMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 私信ID
     */
    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    /**
     * 接收者ID
     */
    private Integer receiverId;

    /**
     * 发送者ID
     */
    private Integer senderId;

    /**
     * 私信内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Timestamp createdAt;
}
