package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.IpAddress;

public interface IpAddressService {

    IpAddress ifNotExistSaveAndGet(String ipAddress);
}
