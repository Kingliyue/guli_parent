package com.atliyue.ucenter.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.atliyue.exception.MyException;
import com.atliyue.ucenter.entity.Member;
import com.atliyue.ucenter.mapper.MemberMapper;
import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.vo.Ucenter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyue.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-09-26
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public String login(Ucenter ucenter) {
        if(StringUtils.isEmpty(ucenter.getMobile())){
            throw new MyException(20001,"手机号不能为空");
        }
        if(StringUtils.isEmpty(ucenter.getPassword())){
            throw new MyException(20001,"密码不能为空");
        }
        //查看手机号是不是已经注册
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",ucenter.getMobile());
        Member member = baseMapper.selectOne(queryWrapper);
        if (member==null){
            throw new MyException(20001,"手机还没有注册");
        }
        //查询出

        if (!Md5Utils.getMD5(ucenter.getPassword().getBytes()).equals(member.getPassword())){
            throw new MyException(20001,"密码错误");

        }
        //判断用户是否禁用
        if(member.getIsDisabled()) {
            throw new MyException(20001,"密码错误");
        }
        //登录成功后生成token 并返给前端
        String jwtToken = JwtUtil.getJwtToken(member.getId(),member.getNickname());
        return jwtToken;
    }
}
