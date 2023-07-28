package Customer.Demographicoffers.service;

import Customer.Demographicoffers.model.Address;
import Customer.Demographicoffers.model.ConsentModel;
import Customer.Demographicoffers.model.DemographicsRegistrationModel;
import Customer.Demographicoffers.model.RegistrationDto;
import Customer.Demographicoffers.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    private final URI CONSENT_BASE_URI= URI.create("http://localhost:8080/consent");
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity register(RegistrationDto registrationDto){
        ConsentModel consentModel= ConsentModel.builder()
                .payload(registrationDto.getPermissionsList())
                .cin(registrationDto.getCin())
                .build();

        String consentId= createConsent(consentModel);
        DemographicsRegistrationModel demographicsRegistrationModel = DemographicsRegistrationModel.builder()
                .cin(registrationDto.getCin())
                .consentId(consentId)
                .isDemographicOfferAccepted(registrationDto.isDemographicOfferAccepted())
                .isGPSOffersAccepted(registrationDto.isGPSOffersAccepted())
                .postCode(getAddress(registrationDto.getCin()))
                .demographicId(UUID.randomUUID().toString())
                .build();
        registrationRepository.save(demographicsRegistrationModel);

        return new ResponseEntity(HttpStatus.CREATED);
    }
    //this method acts as a cas service which creates a
    private String createConsent(ConsentModel consentModel)
    {
        ConsentModel response= restTemplate.postForObject(CONSENT_BASE_URI,consentModel,ConsentModel.class);
        return response.getConsentId();

    }
    private String getAddress(String cin)
    {
        Address address= restTemplate.postForObject(CONSENT_BASE_URI,cin,Address.class);
        return address.getPostCode();
    }
}
