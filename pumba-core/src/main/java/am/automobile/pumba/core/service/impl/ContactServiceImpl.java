package am.automobile.pumba.core.service.impl;

import am.automobile.pumba.core.entity.ContactEmail;
import am.automobile.pumba.core.entity.ContactPhone;
import am.automobile.pumba.core.mapper.ContactEmailMapper;
import am.automobile.pumba.core.mapper.ContactPhoneMapper;
import am.automobile.pumba.core.repository.ContactEmailRepository;
import am.automobile.pumba.core.repository.ContactPhoneRepository;
import am.automobile.pumba.core.service.ContactService;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
