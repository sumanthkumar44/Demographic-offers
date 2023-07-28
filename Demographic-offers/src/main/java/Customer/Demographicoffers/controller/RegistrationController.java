package Customer.Demographicoffers.controller;

import Customer.Demographicoffers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    public ResponseEntity register()
    {
        return null;
    }
}
