package com.yexuhang.internship;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  Xuhang Ye
 */
@SpringBootApplication
@MapperScan("com.yexuhang.internship.mapper")
public class InternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipApplication.class, args);
    }

}