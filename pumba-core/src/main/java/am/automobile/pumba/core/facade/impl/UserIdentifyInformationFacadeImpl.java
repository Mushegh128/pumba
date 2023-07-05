package am.automobile.pumba.core.facade.impl;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.facade.UserIdentifyInformationFacade;
import am.automobile.pumba.core.service.IpAddressService;
import am.automobile.pumba.core.service.UserAgentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserIdentifyInformationFacadeImpl implements UserIdentifyInformationFacade {

    private final UserAgentService userAgentService;
    private final IpAddressService ipAddressService;

    @Override
    public IpAddress save(String ipAddress, String userAgent) {
        IpAddress address = ipAddressService.ifNotExistSaveAndGet(ipAddress);
        userAgentService.save(userAgent, address);
        return address;
    }

    @Override
    public IpAddress save(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        return save(ipAddress, userAgent);
    }
}
