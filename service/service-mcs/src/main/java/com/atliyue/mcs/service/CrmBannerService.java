package com.atliyue.mcs.service;

import com.atliyue.mcs.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-09-08
 */
public interface CrmBannerService extends IService<CrmBanner> {
    List<CrmBanner> getBannerList();
}
