package com.codezm.auths.news.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserPwdAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPwdAuthenticationToken authenticationToken = (UserPwdAuthenticationToken) authentication;

        String userName = (String) authenticationToken.getPrincipal();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        UserPwdAuthenticationToken authenticationResult = new UserPwdAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return UserPwdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
