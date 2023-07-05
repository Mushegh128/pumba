package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.repository.IpAddressRepository;
import am.automobile.pumba.core.service.IpAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IpAddressServiceImpl implements IpAddressService {

    private final IpAddressRepository ipAddressRepository;

    @Override
    public IpAddress ifNotExistSaveAndGet(String ipAddress) {
        Optional<IpAddress> ipAddressOptional = ipAddressRepository.findByIpAddress(ipAddress);
        if (ipAddressOptional.isPresent()) {
            return ipAddressOptional.get();
        } else {
            IpAddress IpAddressBuild = IpAddress.builder()
                    .ipAddress(ipAddress)
                    .build();
            return ipAddressRepository.save(IpAddressBuild);
        }
    }
}
