package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventModel {

    private String eventId;
    private DemographicsOffersModel payload;
    private final String eventType="DISC:DEMOGRAPHICS";

}
