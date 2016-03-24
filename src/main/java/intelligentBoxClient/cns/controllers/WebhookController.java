package intelligentBoxClient.cns.controllers;

/**
 * Created by Leo on 3/20/16.
 */
import intelligentBoxClient.cns.message.DropboxNotification;
import intelligentBoxClient.cns.workers.INotificationWorker;
import intelligentBoxClient.cns.workers.INotificationWorkerFactory;
import intelligentBoxClient.cns.workers.NotificationWorker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {
    private final static Log logger = LogFactory.getLog(WebhookController.class);

    private INotificationWorkerFactory _workerFactory;

    @Autowired
    public WebhookController(INotificationWorkerFactory workerFactory)
    {
        _workerFactory = workerFactory;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String verify(@RequestParam(value="challenge") String challenge) {
        logger.debug("Received challenge: " + challenge);
        return challenge;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public void notifyChange(@RequestBody DropboxNotification dropboxNotification) {

        String[] accounts = dropboxNotification.getList_folder().getAccounts();
        INotificationWorker worker = _workerFactory.getWorker();
        worker.start(accounts);

        StringBuffer sb = new StringBuffer();
        sb.append("AccountIds: ");
        for (String account : accounts) {
            sb.append(account).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        logger.debug(sb.toString());
    }

}
