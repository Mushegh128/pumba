package am.automobile.pumba.core.service;

import com.automobile.pumba.data.transfer.response.ContactEmailResponse;
import com.automobile.pumba.data.transfer.response.ContactPhoneResponse;

import java.util.List;

public interface ContactService {

    List<ContactEmailResponse> findAllContactEmails();

    List<ContactPhoneResponse> findAllContactPhones();
}
