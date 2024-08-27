package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcReport;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 举报 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-27
 */
@RestController
@RequestMapping("/ccReport")
public class CcReportController {
    @Autowired
    private CcReportService ccReportService;

    /**
     * 记录举报内容
     *
     * @param ccReport 举报信息
     * @return 操作结果
     */
    @PostMapping("/save")
    public CommonResult<?> saveReport(@RequestBody CcReport ccReport) {
        return ccReportService.saveReport(ccReport);
    }
}
