package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.Car;
import am.automobile.pumba.core.entity.ContactSeller;
import am.automobile.pumba.core.entity.IpAddress;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.ContactSellerMapper;
import am.automobile.pumba.core.repository.ContactSellerRepository;
import am.automobile.pumba.core.service.CarService;
import am.automobile.pumba.core.service.ContactSellerService;
import com.automobile.pumba.data.transfer.request.ContactSellerRequest;
import com.automobile.pumba.data.transfer.response.ContactSellerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactSellerServiceImpl implements ContactSellerService {

    private final ContactSellerRepository contactSellerRepository;
    private final ContactSellerMapper contactSellerMapper;
    private final CarService carService;

    @Override
    public ContactSellerResponse save(ContactSellerRequest contactSellerRequest, IpAddress ipAddress) {
        ContactSeller contactSeller = contactSellerMapper.toEntity(contactSellerRequest);
        if (contactSellerRequest.getCar() != null) {
            Car car = carService.findByIdAndIsPublicTrueAndIsApprovedTrue(contactSellerRequest.getCar());
            contactSeller.setCar(car);
        }
        contactSeller.setIpAddress(ipAddress);
        contactSeller.setIsDelete(false);
        ContactSeller save = contactSellerRepository.save(contactSeller);
        return contactSellerMapper.toResponse(save);
    }

    @Override
    public void deleteById(long id) {
        ContactSeller seller = findById(id);
        seller.setIsDelete(true);
        contactSellerRepository.save(seller);
    }

    @Override
    public ContactSeller findById(long id) {
        return contactSellerRepository.findByIdAndIsDeleteIsFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact Seller with id: " + id + " not found"));
    }

    @Override
    public Page<ContactSellerResponse> findAll(Pageable pageable) {
        Page<ContactSeller> sellers = contactSellerRepository.findAllByIsDeleteIsFalse(pageable);
        return sellers.map(contactSellerMapper::toResponse);
    }
}
