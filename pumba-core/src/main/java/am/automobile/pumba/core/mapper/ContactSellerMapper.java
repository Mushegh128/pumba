package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.ContactSeller;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.ContactSellerRequest;
import com.automobile.pumba.data.transfer.response.ContactSellerResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactSellerMapper implements BaseMapper<ContactSeller, ContactSellerRequest, ContactSellerResponse> {

    private final ModelMapper modelMapper;
    private final CarMapper carMapper;

    @Override
    public ContactSeller toEntity(ContactSellerRequest contactSellerRequest) {
        return modelMapper.map(contactSellerRequest, ContactSeller.class);
    }

    @Override
    public ContactSellerResponse toResponse(ContactSeller contactSeller) {

        ContactSellerResponse contactSellerResponse = modelMapper.map(contactSeller, ContactSellerResponse.class);
        if (contactSeller.getCar() != null) {
            contactSellerResponse.setCar(carMapper.toResponse(contactSeller.getCar()));
            contactSellerResponse.setCar(carMapper.toResponse(contactSeller.getCar()));
        }
        return contactSellerResponse;
    }
}
