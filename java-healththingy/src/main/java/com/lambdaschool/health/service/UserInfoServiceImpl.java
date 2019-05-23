package com.lambdaschool.health.service;

import com.lambdaschool.health.exception.ResourceNotFoundException;
import com.lambdaschool.health.model.UserInfo;

import com.lambdaschool.health.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService
{
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ArrayList<UserInfo> findAll() {
        ArrayList<UserInfo> list = new ArrayList<>();
        userInfoRepository.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Transactional
    @Override
    public UserInfo savename(UserInfo user)
    {
        UserInfo newUser = new UserInfo();
        newUser.setUsername(user.getUsername());



        return userInfoRepository.save(newUser);
    }
    public UserInfo findUserName(String username) {
        UserInfo user = userInfoRepository.findByUsername(username);
        return user;
    }

    @Transactional
    @Override
    public UserInfo updateUserInfo(UserInfo userInfo, String username)
    {

        UserInfo currentUser = userInfoRepository.findByUsername(username);

        if (currentUser != null)
        {
            if(userInfo.getWeight() != currentUser.getWeight())
            {
                currentUser.setWeight(userInfo.getWeight());
            }
            if(userInfo.getFname() != null)
            {
                currentUser.setFname(userInfo.getFname());
            }
            if(userInfo.getLname() != null)
            {
                currentUser.setLname(userInfo.getLname());
            }
            if(userInfo.getAge() != currentUser.getAge())
            {
                currentUser.setAge(userInfo.getAge());
            }
            if(userInfo.getEgoal() != null)
            {
                currentUser.setEgoal(userInfo.getEgoal());
            }
            if(userInfo.getGender() != null)
            {
                currentUser.setGender(userInfo.getGender());
            }
            if(userInfo.getWeeklyworkoutdays() != currentUser.getWeeklyworkoutdays())
            {
                currentUser.setWeeklyworkoutdays(userInfo.getWeeklyworkoutdays());
            }
            if(userInfo.getHeight() != currentUser.getHeight())
            {
                currentUser.setHeight(userInfo.getHeight());
            }
            if(userInfo.getMealsaday() != currentUser.getMealsaday())
            {
                currentUser.setMealsaday(userInfo.getMealsaday());
            }



            return currentUser;

        }
        else
        {
            throw new ResourceNotFoundException(username);
        }

    }

}
