package intelligentBoxClient.cns.controllers;

/**
 * Created by Leo on 3/20/16.
 */
import intelligentBoxClient.cns.message.Notification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {
    private final static Log logger = LogFactory.getLog(WebhookController.class);

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String verify(@RequestParam(value="challenge") String challenge) {
        logger.debug("Received challenge: " + challenge);
        return challenge;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public void notifyChange(@RequestBody Notification notification) {
        StringBuffer sb = new StringBuffer();
        sb.append("AccountIds: ");
        for (String account : notification.getList_folder().getAccounts()) {
            sb.append(account).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        logger.debug(sb.toString());
    }

}
