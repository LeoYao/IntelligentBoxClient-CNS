package intelligentBoxClient.cns.controllers;

/**
 * Created by Leo on 3/20/16.
 */
import intelligentBoxClient.cns.message.Notification;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String verify(@RequestParam(value="challenge") String challenge) {

        System.out.println("---------------------Challenge----------------------");
        System.out.println("Received challenge: " + challenge);
        System.out.println("----------------------------------------------------");
        return challenge;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public void notifyChange(@RequestBody Notification notification) {
        System.out.println("--------------------Notification--------------------");
        for (String account : notification.getList_folder().getAccounts()) {
            System.out.println("Account: " + account);
        }

        for (String user : notification.getDelta().getUsers()) {
            System.out.println("User: " + user);
        }
        System.out.println("----------------------------------------------------");
    }
}
