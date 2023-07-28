package Customer.Demographicoffers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String flat;
    private String city;
    private String postCode;
}
