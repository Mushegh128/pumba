package am.automobile.pumba.core.service;

import com.automobile.pumba.data.transfer.request.CooperatingOrganizationRequest;
import com.automobile.pumba.data.transfer.response.CooperatingOrganizationResponse;

import java.util.List;

public interface CooperatingOrganizationService {

    CooperatingOrganizationResponse create(CooperatingOrganizationRequest cooperatingOrganizationRequest);

    List<CooperatingOrganizationResponse> findAll();
}
