package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.CooperatingOrganization;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.CooperatingOrganizationRequest;
import com.automobile.pumba.data.transfer.response.CooperatingOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooperatingOrganizationMapper implements BaseMapper<CooperatingOrganization, CooperatingOrganizationRequest, CooperatingOrganizationResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CooperatingOrganization toEntity(CooperatingOrganizationRequest cooperatingOrganizationRequest) {
        return modelMapper.map(cooperatingOrganizationRequest, CooperatingOrganization.class);
    }

    @Override
    public CooperatingOrganizationResponse toResponse(CooperatingOrganization cooperatingOrganization) {
        return modelMapper.map(cooperatingOrganization, CooperatingOrganizationResponse.class);
    }
}
