package com.yexuhang.internship.dto;

import lombok.Data;

/**
 * @author Xuhang Ye
 * @time 11:00 AM
 */
@Data
public class updatePassword {
    private String username;
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;
}
