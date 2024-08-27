package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.CcReport;
import com.yexuhang.internship.config.CommonResult;

/**
 * <p>
 * 举报 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-27
 */
public interface CcReportService extends IService<CcReport> {

    /**
     * 保存举报内容
     *
     * @param ccReport 举报信息
     * @return 操作结果
     */
    CommonResult<?> saveReport(CcReport ccReport);
}
