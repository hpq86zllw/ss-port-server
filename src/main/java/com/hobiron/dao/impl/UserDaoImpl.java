package com.hobiron.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hobiron.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int updateUserNicknameByOpenid(String openid, String nickname) {
        return jdbcTemplate.update("UPDATE user SET nickname = ? WHERE openid = ?", nickname, openid);
    }

    @Override
    public void insertUser(String openid, String nickname) {
        jdbcTemplate.update("INSERT INTO user(openid, nickname) VALUES(?, ?)", openid, nickname);
    }

}
