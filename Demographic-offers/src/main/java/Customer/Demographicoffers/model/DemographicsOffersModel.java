package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("DemographicsOffersModel")
public class DemographicsOffersModel {
    @Id
    private String subject;
    private String URL;
    private String description;
    private List<String> postcode;
    private List<String> cities;
    private List<String> countries;
    private String retailerId;
}
