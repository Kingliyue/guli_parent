package com.atliyue.order.controller;


import com.atliyue.entity.CourseInfoVo;
import com.atliyue.entity.Member;
import com.atliyue.order.entity.TOrder;
import com.atliyue.order.fegin.EduCourseClient;
import com.atliyue.order.fegin.UcenterClient;
import com.atliyue.order.service.OrderService;
import com.atliyue.order.utils.OderNoUtil;
import com.atliyue.result.Result;
import com.atliyue.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/order/Torder")
public class OrderController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private EduCourseClient eduCourseClient;

    @GetMapping("getOrderInfoByCourse")
    public Result getOrderInfoByCourse(){
        orderService.getOrderInfoByCourse();
        return Result.ok();
    }
    @PostMapping("generateOrders")
    public Result generateOrders(@RequestBody TOrder order, HttpServletRequest httpServletRequest){
        CourseInfoVo courseInfoByCourseId = eduCourseClient.getCourseInfoByCourseId(order.getCourseId());

        Member member = ucenterClient.getEntityByToken(JwtUtil.getMemberIdByJwtToken(httpServletRequest));
        order.setCourseTitle(courseInfoByCourseId.getTitle());
        order.setCourseCover(courseInfoByCourseId.getCover());
        order.setTeacherName(courseInfoByCourseId.getTeacherName());
        order.setMemberId(member.getId());
        order.setNickname(member.getNickname());
        order.setMobile(member.getMobile());
        order.setPayType(1);
        order.setStatus(0);
        order.setOrderNo(OderNoUtil.getOrderNo());
        orderService.save(order);
        return Result.ok().data("orderId",order.getId());
    }
    @GetMapping("getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable("orderId") String orderId){
        TOrder byId = orderService.getById(orderId);
        return Result.ok().data("order",byId);
    }
}

