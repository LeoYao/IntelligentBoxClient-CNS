package intelligentBoxClient.cns.workers;

import intelligentBoxClient.cns.bootstrapper.Configuration;
import intelligentBoxClient.cns.dao.CallbackRegRepository;
import intelligentBoxClient.cns.dao.objects.CallbackReg;
import intelligentBoxClient.cns.message.Notification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Created by yaohx on 3/24/2016.
 */
@Component("notificationWorker")
@Scope("prototype")
public class NotificationWorker implements Runnable, INotificationWorker {

    private static Log logger = LogFactory.getLog(NotificationWorker.class);

    private int _retryMax = -1;
    private String[] _accounts;
    private CallbackRegRepository _callbackRegRepository;

    @Autowired(required = false)
    private Configuration _configuation;

    @Autowired
    public NotificationWorker(CallbackRegRepository callbackRegRepository)
    {
        _callbackRegRepository = callbackRegRepository;
    }

    public void start(String[] accounts)
    {
        _accounts = accounts;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for(String account : _accounts)
        {
            notify(account);
        }
    }

    private void notify(String account)
    {
        int retryMax = getRetryMax();
        logger.debug(retryMax);
        CallbackReg callbackReg = _callbackRegRepository.get(account);
        if (callbackReg != null)
        {
            String callbackUrl = "http://" + callbackReg.getCallbackUrl() + "/notify";
            RestTemplate restTemplate = new RestTemplate();
            for (int i = 0; i <= retryMax; ++i)
            {
                if (i == retryMax)
                {
                    logger.error("Failed to notify account [" + account +"], callbackUrl: [" + callbackUrl + "]");
                    break;
                }
                Notification request = new Notification(account);
                ResponseEntity<Object> response = restTemplate.postForEntity(callbackUrl, request, Object.class);
                if (HttpStatus.OK != response.getStatusCode())
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            logger.warn("Skip account [" + account +"] because its callback is not registered.");
        }
    }

    public int getRetryMax()
    {
        if (_retryMax < 0)
        {
            if (_configuation != null)
            {
                _retryMax = _configuation.getRetryMax();
            }
            else
            {
                _retryMax = 1;
            }
        }

        return _retryMax;
    }

    public void setRetryMax(int retryMax)
    {
        _retryMax = retryMax;
    }
}
