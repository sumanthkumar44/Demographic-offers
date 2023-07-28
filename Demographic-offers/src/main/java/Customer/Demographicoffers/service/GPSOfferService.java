package Customer.Demographicoffers.service;

import Customer.Demographicoffers.model.DemographicsOffersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GPSOfferService {
    @Autowired
    RestTemplate restTemplate;
    public List<DemographicsOffersModel> getOffersByGpsLocation(String latitude, String longitude)
    {
        return null;
    }
}
