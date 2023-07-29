package Customer.Demographicoffers.controller;


import Customer.Demographicoffers.model.DemographicsOffersModel;
import Customer.Demographicoffers.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OffersController {
    @Autowired
    OfferService offerService;
    @PostMapping
    public ResponseEntity postOffer(@RequestBody DemographicsOffersModel demographicsOffersModel)
    {
        return offerService.postOffer(demographicsOffersModel);

    }
}
