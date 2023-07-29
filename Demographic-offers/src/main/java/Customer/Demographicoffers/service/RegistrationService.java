package Customer.Demographicoffers.service;

import Customer.Demographicoffers.model.Address;
import Customer.Demographicoffers.model.ConsentModel;
import Customer.Demographicoffers.model.DemographicsRegistrationModel;
import Customer.Demographicoffers.model.RegistrationDto;
import Customer.Demographicoffers.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    private final String CONSENT_BASE_URI= "http://localhost:8080";
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity register(RegistrationDto registrationDto){

        DemographicsRegistrationModel demographicsRegistrationModel = DemographicsRegistrationModel.builder()
                .cin(registrationDto.getCin())
                .consentId(createConsent(registrationDto))
                .isDemographicOfferAccepted(registrationDto.isDemographicOfferAccepted())
                .isGPSOffersAccepted(registrationDto.isGPSOffersAccepted())
                .postCode(getAddress(registrationDto.getCin()))
                .demographicId(UUID.randomUUID().toString())
                .build();
        registrationRepository.save(demographicsRegistrationModel);

        return new ResponseEntity(HttpStatus.CREATED);
    }
    private String createConsent(RegistrationDto registrationDto)
    {
        ConsentModel consentModel= ConsentModel.builder()
                .payload(registrationDto.getPermissionsList())
                .cin(registrationDto.getCin())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(consentModel ,headers);
        //restTemplate.put(uRL, entity);

        ConsentModel response= restTemplate.postForObject(CONSENT_BASE_URI+"/consent",entity,ConsentModel.class);
        return response.getConsentId();

    }
    private String getAddress(String cin)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Address> address= restTemplate.exchange(CONSENT_BASE_URI+"/CDNA/"+cin,HttpMethod.GET,entity,Address.class);
        return address.getBody().getPostCode();
    }

}
