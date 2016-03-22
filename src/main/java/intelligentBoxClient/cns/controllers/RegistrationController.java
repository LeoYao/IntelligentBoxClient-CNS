package intelligentBoxClient.cns.controllers;

import intelligentBoxClient.cns.message.RegistrationRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yaohx on 3/21/2016.
 */
@RestController
public class RegistrationController {
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public void verify(@RequestBody RegistrationRequest request) {


    }
}
