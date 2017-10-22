package com.hobiron.tx;

import java.util.List;

import com.hobiron.bean.Port;
import com.hobiron.bean.PortFlow;

public interface PortService {

    Port getPort(String openid);

    void applyPort(String openid);

    void deletePort(String openid);

    void calculatePortFlow(PortFlow portFlow);

    void enablePort(Port port);

    void disablePort(Port port);

    List<Port> getUsedPortsBySsHost(String ssHost);

    Port getPortByTokenAndPortNo(String token, int portNo);

}
