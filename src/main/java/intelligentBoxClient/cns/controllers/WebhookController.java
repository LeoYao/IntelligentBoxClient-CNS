package intelligentBoxClient.cns.controllers;

/**
 * Created by Leo on 3/20/16.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String verify(@RequestParam(value="challenge", defaultValue="default") String challenge) {
        return challenge;
    }
}
