package com.atliyue.mcs.controller;


import com.atliyue.mcs.service.CrmBannerService;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 * btoc前端controller
 * @author liYue
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/edu/crm-banner")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;
    //1.查询最热门的八个课程
    public Result getCourseList(){
        crmBannerService.getCourseList();
        return  Result.ok();
    }
    //2.查询最受欢迎四个老师

    //3.查询banner轮播图
    @GetMapping("getBannerList")
    public Result getBannerList(){
        List bannerList =crmBannerService.getBannerList();
        return  Result.ok().data("BannerList",bannerList);
    }

}
