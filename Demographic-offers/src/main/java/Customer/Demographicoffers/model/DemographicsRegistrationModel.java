package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("DemographicsRegistration")
public class  DemographicsRegistrationModel {
    @Id
    private String demographicId;
    private String postCode;
    private String cin;
    private String consentId;
    private String city;


}
