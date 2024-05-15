package com.codezm.auths.news.mapper;

import com.codezm.auths.news.domain.NewsUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-05
 */
@Repository
public interface LoginUserMapper
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public NewsUser selectUserByUserId(Long userId);

    /**
     * 查询用户信息
     *
     * @param userName 用户信息主键
     * @return 用户信息
     */
    public NewsUser selectUserByUserName(String userName);

    /**
     * 查询用户信息
     *
     * @param phone 用户信息主键
     * @return 用户信息
     */
    public NewsUser selectUserByPhone(String phone);

    /**
     * 查询用户信息列表
     * 
     * @param newsUser 用户信息
     * @return 用户信息集合
     */
    public List<NewsUser> selectUserList(NewsUser newsUser);

    /**
     * 新增用户信息
     * 
     * @param newsUser 用户信息
     * @return 结果
     */
    public int insertUser(NewsUser newsUser);

    /**
     * 修改用户信息
     * 
     * @param newsUser 用户信息
     * @return 结果
     */
    public int updateUser(NewsUser newsUser);

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);
}
