package Customer.Demographicoffers.repository;

import Customer.Demographicoffers.model.DemographicsOffersModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OffersRepository extends MongoRepository<DemographicsOffersModel,String> {
    DemographicsOffersModel save(DemographicsOffersModel demographicsOffersModel);
    List<DemographicsOffersModel> findByPostcode(String postcode);
    @Query("{latitude: ?0, longitude: ?1}")
    List<DemographicsOffersModel> findByLatitudeAndLongitude(String latitude, String longitude);

}
