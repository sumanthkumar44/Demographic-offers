package Customer.Demographicoffers.controller;

import Customer.Demographicoffers.model.DemographicsOffersModel;
import Customer.Demographicoffers.service.GPSOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gps")
public class GPSOffersController {
    @Autowired
    private GPSOfferService gpsOfferService;
    @GetMapping(value="/{cin}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOffersByGpsLocation(@PathVariable("cin") String cin,
                                                 @RequestParam("latitude") String latitude,
                                                 @RequestParam("longitude") String longitude ){

        return gpsOfferService.getOffersByGpsLocation(cin,latitude,longitude);
    }
}
