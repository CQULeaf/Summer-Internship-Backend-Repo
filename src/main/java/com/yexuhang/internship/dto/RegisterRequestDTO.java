package com.yexuhang.internship.dto;

import lombok.Data;

/**
 * @author Xuhang Ye
 * @time 1:07 AM
 */
@Data
public class RegisterRequestDTO {
    private String username;
    private String password1;
    private String password2;
}
