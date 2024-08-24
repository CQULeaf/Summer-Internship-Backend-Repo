package com.yexuhang.internship.demos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aolin
 */
@Api(tags = "测试接口")
@RequestMapping("/test")
@RestController
public class TestController {
    @ApiOperation("列表测试接口")
    @GetMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success("测试成功");
    }
}