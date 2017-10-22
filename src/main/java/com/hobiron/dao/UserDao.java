package com.hobiron.dao;

public interface UserDao {

    int updateUserNicknameByOpenid(String openid, String nickname);

    void insertUser(String openid, String nickname);

}
