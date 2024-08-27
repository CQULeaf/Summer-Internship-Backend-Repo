package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.CcReport;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcReportMapper;
import com.yexuhang.internship.service.CcReportService;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * <p>
 * 举报 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-27
 */
@Service
public class CcReportServiceImpl extends ServiceImpl<CcReportMapper, CcReport> implements CcReportService {

    @Override
    public CommonResult<?> saveReport(CcReport ccReport) {
        ccReport.setCreateTime((int) Instant.now().getEpochSecond());
        boolean result = this.save(ccReport);
        if (result) {
            return CommonResult.success("举报成功");
        } else {
            return CommonResult.error("举报失败");
        }
    }
}
