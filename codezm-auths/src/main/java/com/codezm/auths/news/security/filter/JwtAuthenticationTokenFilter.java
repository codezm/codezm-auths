package com.codezm.auths.news.security.filter;

import com.codezm.auths.news.domain.NewsLoginUser;
import com.codezm.auths.news.service.TokenService;
import com.codezm.auths.news.untils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component("NewsJwtAuthenticationTokenFilter")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        NewsLoginUser newsLoginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(newsLoginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(newsLoginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(newsLoginUser, null, newsLoginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
