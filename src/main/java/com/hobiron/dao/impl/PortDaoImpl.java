package com.hobiron.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hobiron.bean.Port;
import com.hobiron.dao.PortDao;

@Repository
public class PortDaoImpl implements PortDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Port selectPortByOpenid(String openid) {

        try {
            return jdbcTemplate.queryForObject(
                    "SELECT p.used_flow_bytes, p.total_flow_bytes, a.ss_host, p.port_no, p.password, a.ss_encrypt_method FROM port p, agent a WHERE user_id = ( SELECT id FROM user WHERE openid = ? ) AND p.agent_id = a.id",
                    new BeanPropertyRowMapper<>(Port.class), openid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int updateAvailablePortToAssigned(int userId, String password) {
        return jdbcTemplate.update(
                "UPDATE port SET user_id = ?, password = ? WHERE NOT EXISTS ( SELECT 1 FROM port WHERE user_id = ? ) AND user_id is null LIMIT 1",
                userId, password, userId);
    }

    @Override
    public int updateAssignedPortToAvailable(int userId) {
        return jdbcTemplate.update(
                "UPDATE port SET user_id = NULL, used_flow_bytes = 0, password = NULL WHERE user_id = ?", userId);
    }

    @Override
    public int increatePortUsedFlowBytes(int portNo, String token, long bytes) {
        return jdbcTemplate.update(
                "UPDATE port SET used_flow_bytes = used_flow_bytes + ? WHERE agent_id = ( SELECT id FROM agent where token = ? ) AND port_no = ? AND used_flow_bytes < total_flow_bytes",
                bytes, token, portNo);
    }

    @Override
    public void insertPort(int agentId, long totalFlowBytes, int portNo) {
        jdbcTemplate.update("INSERT INTO port(agent_id, total_flow_bytes, port_no) VALUES(?, ?, ?)", agentId,
                totalFlowBytes, portNo);
    }

    @Override
    public List<Port> selectUsedPortsBySsHost(String ssHost) {
        return jdbcTemplate.query(
                "SELECT p.used_flow_bytes, p.total_flow_bytes, a.ss_host, p.port_no, p.password, a.ss_encrypt_method FROM port p, agent a WHERE a.ss_host = ? AND p.agent_id = a.id AND p.user_id IS NOT NULL",
                new BeanPropertyRowMapper<>(Port.class), ssHost);
    }

    @Override
    public Port selectPortByTokenAndPortNo(String token, int portNo) {
        return jdbcTemplate.queryForObject(
                "SELECT p.used_flow_bytes, p.total_flow_bytes, a.ss_host, p.port_no, p.password, a.ss_encrypt_method FROM port p, agent a WHERE p.agent_id = ( SELECT id FROM agent WHERE token = ? ) AND p.port_no = ?",
                new BeanPropertyRowMapper<>(Port.class), token, portNo);
    }

}
