package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.CooperatingOrganization;
import am.automobile.pumba.core.mapper.CooperatingOrganizationMapper;
import am.automobile.pumba.core.repository.CooperatingOrganizationRepository;
import am.automobile.pumba.core.service.CooperatingOrganizationService;
import am.automobile.pumba.core.util.IOUtil;
import com.automobile.pumba.data.transfer.request.CooperatingOrganizationRequest;
import com.automobile.pumba.data.transfer.response.CooperatingOrganizationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CooperatingOrganizationServiceImpl implements CooperatingOrganizationService {

    private final CooperatingOrganizationMapper cooperatingOrganizationMapper;
    private final CooperatingOrganizationRepository cooperatingOrganizationRepository;
    private final IOUtil ioUtil;
    @Value("${pumba.path.image.cooperating-organization}")
    private String folderPath;

    @Override
    public CooperatingOrganizationResponse create(CooperatingOrganizationRequest cooperatingOrganizationRequest) {
        log.debug("Creating cooperating organization: {}", cooperatingOrganizationRequest);

        CooperatingOrganization cooperatingOrganization = cooperatingOrganizationMapper.toEntity(cooperatingOrganizationRequest);
        String saveLogoPath = ioUtil.saveImage(folderPath, cooperatingOrganizationRequest.getLogo());
        cooperatingOrganization.setLogo(saveLogoPath);
        CooperatingOrganization savedOrganization = cooperatingOrganizationRepository.save(cooperatingOrganization);

        CooperatingOrganizationResponse response = cooperatingOrganizationMapper.toResponse(savedOrganization);

        log.debug("Cooperating organization created: {}", response);

        return response;
    }

    @Override
    public List<CooperatingOrganizationResponse> findAll() {
        log.debug("Fetching all cooperating organizations");

        List<CooperatingOrganization> cooperatingOrganizationList = cooperatingOrganizationRepository.findAll();

        log.debug("Found {} cooperating organizations", cooperatingOrganizationList.size());

        return cooperatingOrganizationList.stream()
                .map(cooperatingOrganizationMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
