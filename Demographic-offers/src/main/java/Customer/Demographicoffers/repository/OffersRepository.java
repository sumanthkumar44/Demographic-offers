package Customer.Demographicoffers.repository;

import Customer.Demographicoffers.model.DemographicsOffersModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OffersRepository extends MongoRepository<DemographicsOffersModel,String> {
    DemographicsOffersModel save(DemographicsOffersModel demographicsOffersModel);
    List<DemographicsOffersModel> findByPostcode(String postcode);
}
