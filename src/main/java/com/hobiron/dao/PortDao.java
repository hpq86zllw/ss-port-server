package com.hobiron.dao;

import java.util.List;

import com.hobiron.bean.Port;

public interface PortDao {

    Port selectPortByOpenid(String openid);

    int updateAvailablePortToAssignedByOpenid(String openid, String password);

    int updateAssignedPortToAvailableByOpenid(String openid);

    int increatePortUsedFlowBytes(int portNo, String token, long bytes);

    void insertPort(int agentId, long totalFlowBytes, int portNo);

    List<Port> selectUsedPortsBySsHost(String ssHost);

    Port selectPortByTokenAndPortNo(String token, int portNo);

}
