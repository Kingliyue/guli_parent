package com.atliyue.order.controller;


import com.atliyue.edu.vo.CourseInfoVo;
import com.atliyue.order.entity.TOrder;
import com.atliyue.order.fegin.EduCourseClient;
import com.atliyue.order.service.OrderService;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private EduCourseClient eduCourseClient;
    @GetMapping("getOrderInfoByCourse")
    public Result getOrderInfoByCourse(){
        orderService.getOrderInfoByCourse();
        return Result.ok();
    }
    @PostMapping("generateOrders")
    public Result generateOrders(@RequestBody TOrder order){
        CourseInfoVo courseInfoByCourseId = eduCourseClient.getCourseInfoByCourseId(order.getCourseId());
        order.setCourseTitle(courseInfoByCourseId.getTitle());
        order.setCourseCover(courseInfoByCourseId.getCover());
        order.setTeacherName(courseInfoByCourseId.getTeacherName());

        orderService.save(order);

        return Result.ok().data("orderId",order.getId());
    }
}

