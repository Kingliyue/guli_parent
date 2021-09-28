package com.atliyue.mcs.controller;

import com.atliyue.mcs.entity.CrmBanner;
import com.atliyue.mcs.service.CrmBannerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**2
 * 后台系统调用的controller
 */
@RestController
@RequestMapping("/mcs/adminBanner")
@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;
    @GetMapping("/bannerList/{limit}/{size}")
    public Result getAdminBannerList(@PathVariable("limit") long limit,@PathVariable("size") long size){
        IPage page = new Page(limit,size);
        IPage iPage = crmBannerService.page(page, null);
        return Result.ok().data("page",iPage);
    }

    @PutMapping("update")
    public Result updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return  Result.ok();
    }
    @PostMapping("addBanner")
    public  Result insertBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return Result.ok();
    }
    @DeleteMapping("deleteBanner/{bannerId}")
    public Result deleteBanner(@PathVariable String  bannerId){
        crmBannerService.removeById(bannerId);
        return Result.ok();
    }

}
