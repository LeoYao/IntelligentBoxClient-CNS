package IntelligentBoxClient.ChangeNotificationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
	@RequestMapping("/")
    public String verify(@RequestParam(value="challenge", defaultValue="") String challenge) {
        return challenge;
    }
}
