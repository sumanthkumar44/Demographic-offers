package Customer.Demographicoffers.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String demographicId;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String postCode;
    private String cin;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String consentId;
    private boolean isGPSOffersAccepted;
    private boolean isDemographicOfferAccepted;
    private List<String> permissionsList;
    private final String consentType="DEM";
}
