package com.atliyue.order.service;

import com.atliyue.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-11-30
 */
public interface OrderService extends IService<TOrder> {

    TOrder getOrderInfoByCourse();
}
