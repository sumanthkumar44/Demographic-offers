package Customer.Demographicoffers.controller;

import Customer.Demographicoffers.model.DemographicsOffersModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps")
public class GPSOffersController {
    @GetMapping
    public ResponseEntity getOffersByGpsLocation(){
        return null;
    }
}
