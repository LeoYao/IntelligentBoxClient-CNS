package intelligentBoxClient.cns;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import intelligentBoxClient.cns.dao.CallbackRegRepository;
import intelligentBoxClient.cns.dao.DynamoDbContext;
import intelligentBoxClient.cns.dao.objects.CallbackReg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

/**
 * Created by Leo on 3/20/16.
 */

@SpringBootApplication
public class ChangeNotificationService {
    private final static Log logger = LogFactory.getLog(ChangeNotificationService.class);

    public static void main(String[] args) {

        //SpringApplication.run(ChangeNotificationService.class, args);

        testDynamoDB();
    }

    public static void testDynamoDB()
    {
        DynamoDbContext ctx = new DynamoDbContext();
        ctx.initialize();

        CallbackRegRepository repos = ctx.getCallbackRegRepository();

        repos.delete(new CallbackReg("1", null), true);

        repos.upsert(new CallbackReg("1", "c1"));

        CallbackReg item = ctx.getCallbackRegRepository().get("1");
        logger.info(item.getAccountId() + " " + item.getCallbackUrl() + " " + item.getVersion());

        item.setCallbackUrl("c2");
        ctx.getCallbackRegRepository().upsert(item);

        item = ctx.getCallbackRegRepository().get("1");
        logger.info(item.getAccountId() + " " + item.getCallbackUrl() + " " + item.getVersion());
    }
}
