package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.ContactEmail;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.ContactEmailRequest;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactEmailMapper implements BaseMapper<ContactEmail, ContactEmailRequest, ContactEmailResponse> {

    private final ModelMapper modelMapper;

    @Override
    public ContactEmail toEntity(ContactEmailRequest contactEmailRequest) {
        return modelMapper.map(contactEmailRequest, ContactEmail.class);
    }

    @Override
    public ContactEmailResponse toResponse(ContactEmail contactEmail) {
        return modelMapper.map(contactEmail, ContactEmailResponse.class);
    }
}
