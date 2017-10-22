package com.hobiron.tx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobiron.dao.UserDao;
import com.hobiron.tx.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(String openid, String nickname) {
        int resultNum = userDao.updateUserNicknameByOpenid(openid, nickname);
        if (resultNum != 1) {
            userDao.insertUser(openid, nickname);
        }
    }

}
