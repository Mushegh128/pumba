package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.ContactEmail;
import am.automobile.pumba.core.entity.ContactPhone;
import com.automobile.pumba.data.transfer.request.ContactEmailRequest;
import com.automobile.pumba.data.transfer.request.ContactPhoneRequest;
import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;

import java.util.List;

public interface ContactService {

    List<ContactEmailResponse> findAllContactEmails();

    List<ContactPhoneResponse> findAllContactPhones();

    ContactPhone findContactPhoneById(long id);

    ContactEmail findContactEmailById(long id);

    ContactEmailResponse createEmail(ContactEmailRequest contactEmailRequest);

    ContactPhoneResponse createPhone(ContactPhoneRequest contactPhoneRequest);

    void deleteEmailById(long id);

    ContactEmailResponse updateEmail(long id, ContactEmailRequest contactEmailRequest);

    void deletePhoneById(long id);

    ContactPhoneResponse updatePhone(long id, ContactPhoneRequest contactPhoneRequest);
}
