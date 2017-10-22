package com.hobiron.dao;

import com.hobiron.bean.User;

public interface UserDao {

    int updateUserNicknameByOpenid(String openid, String nickname);

    void insertUser(String openid, String nickname);

    User selectUserByOpenid(String openid);

}
