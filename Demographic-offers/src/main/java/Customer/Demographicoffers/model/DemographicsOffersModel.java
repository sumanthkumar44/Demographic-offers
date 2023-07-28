package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DemographicsOffersModel {
    private String adUrl;
    private String retailerId;
    private String adId;
    private String payload;
}
