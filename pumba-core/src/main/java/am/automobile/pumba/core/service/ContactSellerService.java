package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.ContactSeller;
import am.automobile.pumba.core.entity.IpAddress;
import com.automobile.pumba.data.transfer.request.ContactSellerRequest;
import com.automobile.pumba.data.transfer.response.ContactSellerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactSellerService {

    ContactSellerResponse save(ContactSellerRequest contactSellerRequest, IpAddress ipAddress);

    void deleteById(long id);

    ContactSeller findById(long id);

    Page<ContactSellerResponse> findAll(Pageable pageable);
}
