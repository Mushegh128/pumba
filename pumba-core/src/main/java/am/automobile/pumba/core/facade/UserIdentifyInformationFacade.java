package am.automobile.pumba.core.facade;

import am.automobile.pumba.core.entity.IpAddress;
import jakarta.servlet.http.HttpServletRequest;

public interface UserIdentifyInformationFacade {

    IpAddress save(String ipAddress, String userAgent);

    IpAddress save(HttpServletRequest request);
}
