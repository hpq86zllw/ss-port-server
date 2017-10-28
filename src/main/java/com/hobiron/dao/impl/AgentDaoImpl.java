package com.hobiron.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hobiron.bean.Agent;
import com.hobiron.dao.AgentDao;

@Repository
public class AgentDaoImpl implements AgentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int updateAgentBySsHost(String ssHost, String ssEncryptMethod, String baseUrl, String token) {
        return jdbcTemplate.update(
                "UPDATE agent SET ss_host = ?, ss_encrypt_method = ?, base_url = ?, token = ? WHERE ss_host = ?",
                ssHost, ssEncryptMethod, baseUrl, token, ssHost);
    }

    @Override
    public void insertAgent(String ssHost, String ssEncryptMethod, String baseUrl, String token) {
        jdbcTemplate.update("INSERT INTO agent(ss_host, ss_encrypt_method, base_url, token) VALUES(?, ?, ?, ?)", ssHost,
                ssEncryptMethod, baseUrl, token);
    }

    @Override
    public Agent selectAgentBySsHost(String ssHost) {
        return jdbcTemplate.queryForObject(
                "SELECT id, ss_host, ss_encrypt_method, base_url, token FROM agent WHERE ss_host = ?",
                new BeanPropertyRowMapper<>(Agent.class), ssHost);
    }

}
