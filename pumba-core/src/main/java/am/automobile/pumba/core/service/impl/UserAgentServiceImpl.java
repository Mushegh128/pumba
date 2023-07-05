package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.entity.UserAgent;
import am.automobile.pumba.core.repository.UserAgentRepository;
import am.automobile.pumba.core.service.UserAgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAgentServiceImpl implements UserAgentService {

    private final UserAgentRepository userAgentRepository;

    @Override
    public UserAgent save(String agent, IpAddress ipAddress) {
        UserAgent userAgent = UserAgent.builder()
                .userAgent(agent)
                .ipAddress(ipAddress)
                .build();
        return userAgentRepository.save(userAgent);
    }
}
