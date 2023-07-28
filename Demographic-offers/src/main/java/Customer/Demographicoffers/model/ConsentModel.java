package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentModel {

    private String consentId;
    private List<String> payload;
    private String createdAt;
    private final String consentType="DEM";
    private String cin;
}
