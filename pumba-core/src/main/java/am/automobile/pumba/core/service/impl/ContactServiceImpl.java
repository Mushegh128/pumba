package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.ContactEmail;
import am.automobile.pumba.core.entity.ContactPhone;
import am.automobile.pumba.core.exception.EntityNotFoundException;
import am.automobile.pumba.core.mapper.ContactEmailMapper;
import am.automobile.pumba.core.mapper.ContactPhoneMapper;
import am.automobile.pumba.core.repository.CarRepository;
import am.automobile.pumba.core.repository.ContactEmailRepository;
import am.automobile.pumba.core.repository.ContactPhoneRepository;
import am.automobile.pumba.core.service.ContactService;
import com.automobile.pumba.data.transfer.request.ContactEmailRequest;
import com.automobile.pumba.data.transfer.request.ContactPhoneRequest;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactPhoneRepository contactPhoneRepository;
    private final ContactEmailRepository contactEmailRepository;
    private final ContactPhoneMapper contactPhoneMapper;
    private final ContactEmailMapper contactEmailMapper;
    private final CarRepository carRepository;

    @Override
    public List<ContactEmailResponse> findAllContactEmails() {
        List<ContactEmail> contactEmails = contactEmailRepository.findAll();
        return contactEmails.stream()
                .map(contactEmailMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<ContactPhoneResponse> findAllContactPhones() {
        List<ContactPhone> contactPhones = contactPhoneRepository.findAll();
        return contactPhones.stream()
                .map(contactPhoneMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public ContactPhone findContactPhoneById(long id) {
        log.info("Find contactPhone by ID: {}", id);
        return contactPhoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContactPhone with id: " + id + " not found"));
    }

    @Override
    public ContactEmail findContactEmailById(long id) {
        log.info("Find contactEmail by ID: {}", id);
        return contactEmailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContactEmail with id: " + id + " not found"));
    }

    @Override
    public ContactEmailResponse createEmail(ContactEmailRequest contactEmailRequest) {
        ContactEmail contactEmail = contactEmailMapper.toEntity(contactEmailRequest);
        ContactEmail save = contactEmailRepository.save(contactEmail);
        return contactEmailMapper.toResponse(save);
    }

    @Override
    public ContactPhoneResponse createPhone(ContactPhoneRequest contactPhoneRequest) {
        ContactPhone contactPhone = contactPhoneMapper.toEntity(contactPhoneRequest);
        ContactPhone save = contactPhoneRepository.save(contactPhone);
        return contactPhoneMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void deleteEmailById(long id) {
        ContactEmail contactEmail = findContactEmailById(id);
        carRepository.updateContactEmailToNull(id);
        contactEmailRepository.delete(contactEmail);
    }

    @Override
    public ContactEmailResponse updateEmail(long id, ContactEmailRequest contactEmailRequest) {
        ContactEmail contactEmail = findContactEmailById(id);
        contactEmailMapper.updateFromRequest(contactEmailRequest, contactEmail);
        ContactEmail save = contactEmailRepository.save(contactEmail);
        return contactEmailMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void deletePhoneById(long id) {
        ContactPhone contactPhone = findContactPhoneById(id);
        carRepository.updateContactPhoneIdToNull(id);
        contactPhoneRepository.delete(contactPhone);
    }

    @Override
    public ContactPhoneResponse updatePhone(long id, ContactPhoneRequest contactPhoneRequest) {
        ContactPhone contactPhone = findContactPhoneById(id);
        contactPhoneMapper.updateFromRequest(contactPhoneRequest, contactPhone);
        ContactPhone save = contactPhoneRepository.save(contactPhone);
        return contactPhoneMapper.toResponse(save);
    }
}
