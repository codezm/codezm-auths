package com.codezm.auths.news.security.handle;

import com.alibaba.fastjson2.JSON;
import com.codezm.auths.news.domain.NewsLoginUser;
import com.codezm.auths.news.manager.AsyncManager;
import com.codezm.auths.news.manager.factory.AsyncFactory;
import com.codezm.auths.news.service.TokenService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Component("NewsLogoutSuccessHandlerImpl")
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        NewsLoginUser newsLoginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(newsLoginUser))
        {
            String userName = newsLoginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(newsLoginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
