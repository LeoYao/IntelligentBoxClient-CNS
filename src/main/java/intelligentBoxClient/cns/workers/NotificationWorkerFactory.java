package intelligentBoxClient.cns.workers;

import intelligentBoxClient.cns.dao.ICallbackRegRepository;
import intelligentBoxClient.cns.dao.IDynamoDbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by yaohx on 3/24/2016.
 */
@Service
public class NotificationWorkerFactory implements INotificationWorkerFactory {

    private IDynamoDbContext _ctx;
    private ApplicationContext _appCtx;

    @Autowired
    public NotificationWorkerFactory(IDynamoDbContext ctx, ApplicationContext appCtx)
    {
        _ctx = ctx;
        _appCtx = appCtx;
    }

    public INotificationWorker getWorker()
    {
        return _appCtx.getBean(NotificationWorker.class);
    }
}
