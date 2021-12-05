package com.atliyue.order.service.impl;

import com.atliyue.order.entity.TOrder;
import com.atliyue.order.mapper.OrderMapper;
import com.atliyue.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-11-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements OrderService {

    @Override
    public TOrder getOrderInfoByCourse() {

        return null;
    }
}
