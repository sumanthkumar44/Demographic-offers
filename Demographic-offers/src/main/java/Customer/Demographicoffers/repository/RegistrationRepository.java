package Customer.Demographicoffers.repository;

import Customer.Demographicoffers.model.DemographicsRegistrationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends MongoRepository<DemographicsRegistrationModel,String> {
    DemographicsRegistrationModel save(DemographicsRegistrationModel demographicsRegistrationModel);
    List<DemographicsRegistrationModel> findByCin(String cin);

}
