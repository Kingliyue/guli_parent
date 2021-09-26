package com.atliyue.ucenter.service;

import com.atliyue.ucenter.entity.Member;
import com.atliyue.ucenter.vo.Ucenter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-09-26
 */
public interface MemberService extends IService<Member> {
    String login(Ucenter ucenter);

    void register(Ucenter ucenter);
}
