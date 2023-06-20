package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.ContactPhone;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.ContactPhoneRequest;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactPhoneMapper implements BaseMapper<ContactPhone, ContactPhoneRequest, ContactPhoneResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ContactPhone toEntity(ContactPhoneRequest contactPhoneRequest) {
        return modelMapper.map(contactPhoneRequest, ContactPhone.class);
    }

    @Override
    public ContactPhoneResponse toResponse(ContactPhone contactPhone) {
        return modelMapper.map(contactPhone, ContactPhoneResponse.class);
    }

    public void updateFromRequest(ContactPhoneRequest contactPhoneRequest, ContactPhone contactPhone) {
        modelMapper.map(contactPhoneRequest, contactPhone);
    }
}
