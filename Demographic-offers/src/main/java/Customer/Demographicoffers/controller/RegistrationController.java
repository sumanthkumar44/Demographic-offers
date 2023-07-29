package Customer.Demographicoffers.controller;

import Customer.Demographicoffers.model.DemographicsRegistrationModel;
import Customer.Demographicoffers.model.RegistrationDto;
import Customer.Demographicoffers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegistrationDto registrationDto)
    {
        return registrationService.register(registrationDto);
    }
}
