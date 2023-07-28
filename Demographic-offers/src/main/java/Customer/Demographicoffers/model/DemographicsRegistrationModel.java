package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DemographicsRegistrationModel {
    private String demographicId;
    private String postCode;
    private String cin;
    private String consentId;
    private String CASConsentId;
    private boolean isGPSOffersAccepted;
    private boolean isDemographicOfferAccepted;

}
