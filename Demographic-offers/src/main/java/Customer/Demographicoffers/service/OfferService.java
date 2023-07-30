package Customer.Demographicoffers.service;

import Customer.Demographicoffers.model.*;
import Customer.Demographicoffers.repository.OffersRepository;
import Customer.Demographicoffers.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class OfferService {
    private final String CONSENT_BASE_URI= "http://localhost:8080";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    private BlockingQueue<DemographicsOffersModel> queue= new LinkedBlockingQueue<>();
    public ResponseEntity<DemographicsOffersModel> postOffer(DemographicsOffersModel demographicsOffersModel)
    {
        demographicsOffersModel.setOfferId(UUID.randomUUID().toString());
        DemographicsOffersModel response= offersRepository.save(demographicsOffersModel);
        queue.add(response);
        processOffers();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    public void processOffers()
    {
        Runnable task = () -> {
            while (!queue.isEmpty()) {
                try {
                    validateConsentAndSendNotification(queue.take());
                    log.info("consuming from queue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        log.info("starting executor");
        executorService.execute(task);
        executorService.shutdown();

    }
   public void validateConsentAndSendNotification(DemographicsOffersModel demographicsOffersModel)
    {
       List<DemographicsRegistrationModel> data =registrationRepository.findByCityIn(demographicsOffersModel.getCities());
        log.info("List of registrations-->"+data);
       HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        for(DemographicsRegistrationModel input: data){
           ResponseEntity<ConsentModel> response= restTemplate.exchange(CONSENT_BASE_URI+"/consent/"+input.getConsentId(),HttpMethod.GET,entity,ConsentModel.class);
           log.info("response for consent validation-->"+response.toString());
        if(response.getStatusCode().is2xxSuccessful())// if it's 2XX thinking as valid consent present
        {
            new PermissionConstants();
           Optional value= response.getBody().getPayload().stream().filter(
                    permission->PermissionConstants.PERMISSION_TO_READ_ADDRESS.equalsIgnoreCase(permission)).findFirst();
           if(value.isPresent()) {
               EventModel requestBody = EventModel.builder().payload(demographicsOffersModel).build();
               ResponseEntity eventResponse = restTemplate.postForEntity(CONSENT_BASE_URI + "/event/", new HttpEntity(requestBody, headers), Void.class);
               log.info("event sent to Event MS" + eventResponse.getStatusCode());
           }
        }
        }

    }


}
