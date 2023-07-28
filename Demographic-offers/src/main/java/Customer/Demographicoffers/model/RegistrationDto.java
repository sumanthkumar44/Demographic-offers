package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String demographicId;
    private String postCode;
    private String cin;
    private String consentId;
    private String CASConsentId;
    private boolean isGPSOffersAccepted;
    private boolean isDemographicOfferAccepted;
    private List<String> permissionsList;
    private final String consentType="DEM";
}
