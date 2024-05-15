package com.codezm.auths.news.service;

import com.codezm.auths.news.domain.NewsUser;
import com.codezm.auths.news.domain.NewsLoginUser;


import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("NewsUserPwdServiceImpl")
public class UserPwdServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserPwdServiceImpl.class);

    @Autowired
    private ILoginUserService loginUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NewsUser newsUser = loginUserService.selectUserByUserName(username);
        if (StringUtils.isNull(newsUser)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(newsUser.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(newsUser.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 未激活");
        }

        return createLoginUser(newsUser);
    }

    public UserDetails createLoginUser(NewsUser newsUser) {
        return new NewsLoginUser(newsUser.getUserId(), newsUser.getDeptId(), newsUser);
    }
}
