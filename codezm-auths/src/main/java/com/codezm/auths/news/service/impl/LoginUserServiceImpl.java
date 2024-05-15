package com.codezm.auths.news.service.impl;

import java.util.List;

import com.codezm.auths.news.domain.NewsUser;
import com.codezm.auths.news.mapper.LoginUserMapper;
import com.codezm.auths.news.service.ILoginUserService;
import com.codezm.auths.news.service.TokenService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-05
 */
@Service
public class LoginUserServiceImpl implements ILoginUserService
{
    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public NewsUser selectUserByUserId(Long userId)
    {
        return loginUserMapper.selectUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param newsUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<NewsUser> selectUserList(NewsUser newsUser)
    {
        return loginUserMapper.selectUserList(newsUser);
    }
    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public NewsUser selectUserByUserName(String userName)
    {
        return loginUserMapper.selectUserByUserName(userName);
    }

    /**
     * 新增用户信息
     * 
     * @param newsUser 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(NewsUser newsUser)
    {
        newsUser.setCreateTime(DateUtils.getNowDate());
        return loginUserMapper.insertUser(newsUser);
    }

    /**
     * 修改用户信息
     * 
     * @param newsUser 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(NewsUser newsUser)
    {
        newsUser.setUpdateTime(DateUtils.getNowDate());
        return loginUserMapper.updateUser(newsUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Long[] userIds)
    {
        return loginUserMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Long userId)
    {
        return loginUserMapper.deleteUserByUserId(userId);
    }
}
