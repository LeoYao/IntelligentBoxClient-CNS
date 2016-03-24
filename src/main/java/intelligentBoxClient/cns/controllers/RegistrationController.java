package intelligentBoxClient.cns.controllers;

import intelligentBoxClient.cns.dao.CallbackRegRepository;
import intelligentBoxClient.cns.dao.DynamoDbContext;
import intelligentBoxClient.cns.dao.ICallbackRegRepository;
import intelligentBoxClient.cns.dao.objects.CallbackReg;
import intelligentBoxClient.cns.message.RegistrationRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yaohx on 3/21/2016.
 */
@RestController
public class RegistrationController {

    private static Log logger = LogFactory.getLog(RegistrationController.class);

    private ICallbackRegRepository _callbackRegRepoistory;

    @Autowired
    public RegistrationController(ICallbackRegRepository callbackRegRepoistory)
    {
        _callbackRegRepoistory = callbackRegRepoistory;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public void register(@RequestBody RegistrationRequest request) {
        logger.debug("received: " + request.toString());
        _callbackRegRepoistory.upsert(new CallbackReg(request.getAccountId(), request.getCallbackUrl()), true);
        logger.debug("registered: " + request.toString());
    }

    @RequestMapping(value="/unregister", method = RequestMethod.POST)
    public void unregister(@RequestBody RegistrationRequest request) {
        logger.debug("received: " + request.toString());
        _callbackRegRepoistory.delete(new CallbackReg(request.getAccountId(), request.getCallbackUrl()), true);
        logger.debug("unregistered: " + request.toString());
    }

}
