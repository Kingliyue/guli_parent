package com.atliyue.order.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping
@FeignClient
public interface UcenterClient {
}
