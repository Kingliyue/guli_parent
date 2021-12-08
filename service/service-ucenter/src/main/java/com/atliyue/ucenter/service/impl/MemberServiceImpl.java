package com.atliyue.ucenter.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.atliyue.entity.Member;
import com.atliyue.exception.MyException;
import com.atliyue.ucenter.mapper.MemberMapper;
import com.atliyue.ucenter.service.MemberService;
import com.atliyue.ucenter.vo.Ucenter;
import com.atliyue.utils.JwtUtil;
import com.atliyue.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate redisTemplate ;
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

    @Override
    public void register(Ucenter ucenter) {
        String password = ucenter.getPassword();
        String mobile = ucenter.getMobile();
        if(StringUtils.isEmpty(mobile)){
            throw  new MyException(2001,"手機好不能為空");
        }

        String code = (String)redisTemplate.opsForValue().get(mobile);
        if(StringUtils.isEmpty(code)){
            throw  new MyException(2001,"验证码失效请重新发送");
        }
        String name = ucenter.getName();
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
            throw  new MyException(2001,"用户名或密码不能为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("mobile",mobile);
        //1.判断手机号注册没有
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw  new MyException(20001,"手机号注册过");
        }
        final  Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(name);
        member.setPassword(MD5.encrypt(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);

    }
}
