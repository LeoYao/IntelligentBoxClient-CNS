package intelligentBoxClient.cns.bootstrapper;

import intelligentBoxClient.cns.dao.IDynamoDbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yaohx on 3/24/2016.
 */
@Component
public class Bootstrapper {

    private IDynamoDbContext _ctx;

    @Autowired
    public Bootstrapper(IDynamoDbContext ctx) {
        _ctx = ctx;
    }

    public void startup() {
        _ctx.initialize();
    }
}