package Customer.Demographicoffers.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("DemographicsOffers")
public class DemographicsOffersModel {
    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String offerId;
    private String subject;
    private String URL;
    private String description;
    private List<String> postcode;
    private List<String> cities;
    private List<String> countries;
    private String retailerId;
    private String latitude;
    private String longitude;
}
