package com.lambdaschool.health.service;

import com.lambdaschool.health.model.UserInfo;

import java.util.ArrayList;

public interface UserInfoService
{
    ArrayList<UserInfo> findAll();

    UserInfo savename(UserInfo user);
    UserInfo updateUserInfo(UserInfo userInfo, String username);
    UserInfo findUserName(String username);
}
