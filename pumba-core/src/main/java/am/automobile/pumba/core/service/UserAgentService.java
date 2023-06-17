package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.UserAgent;

public interface UserAgentService {

    UserAgent save(String agent, IpAddress ipAddress);
}
