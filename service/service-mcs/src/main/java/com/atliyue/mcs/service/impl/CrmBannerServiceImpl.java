package com.atliyue.mcs.service.impl;

import com.atliyue.mcs.entity.CrmBanner;
import com.atliyue.mcs.mapper.CrmBannerMapper;
import com.atliyue.mcs.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-09-08
 */
@Cacheable(value = "getBannerList",key = "'banner'")
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Autowired
    private CrmBannerMapper crmBannerMapper;
    @Override
    public List getBannerList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 2");
        List<CrmBanner> crmBanners = crmBannerMapper.selectList(wrapper);
        return crmBanners;
    }
}
