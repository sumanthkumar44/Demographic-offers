package Customer.Demographicoffers.service;

import Customer.Demographicoffers.helper.ResourceNotFoundException;
import Customer.Demographicoffers.model.ConsentModel;
import Customer.Demographicoffers.model.DemographicsOffersModel;
import Customer.Demographicoffers.model.DemographicsRegistrationModel;
import Customer.Demographicoffers.repository.OffersRepository;
import Customer.Demographicoffers.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class GPSOfferService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private OffersRepository offersRepository;
    private final String CONSENT_BASE_URI= "http://localhost:8080";
    @Autowired
    RestTemplate restTemplate;
    public ResponseEntity<List<DemographicsOffersModel>> getOffersByGpsLocation(String cin, String latitude, String longitude)
    {
        if(hasValidConsent(cin))
        {
           return new ResponseEntity<>(offersRepository.findByPostcode(getPostcode(latitude, longitude)),HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Customer with this CIN is not registered for this service");

    }
    private boolean hasValidConsent(String cin)
    {
        DemographicsRegistrationModel demographicsRegistrationModel= registrationRepository.findByCin(cin);
        if(demographicsRegistrationModel!=null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            ResponseEntity<ConsentModel> response = restTemplate.exchange(CONSENT_BASE_URI + "/consent/" + demographicsRegistrationModel.getConsentId(), HttpMethod.GET, new HttpEntity(headers), ConsentModel.class);
            log.info("response for consent validation-->" + response.toString());
            if (response.getStatusCode().is2xxSuccessful()) {
                return true;
            }
        }

        return false;
    }
    private String getPostcode(String latitude, String longitude)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> gpsResponse= restTemplate.exchange(CONSENT_BASE_URI+"/geolocation?latitude="+latitude+"&longitude="+longitude, HttpMethod.GET,new HttpEntity(headers),String.class);
        return gpsResponse.getBody();
    }

}
