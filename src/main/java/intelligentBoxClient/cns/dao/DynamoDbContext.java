package intelligentBoxClient.cns.dao;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by yaohx on 3/22/2016.
 */
public class DynamoDbContext {

    private final static Log logger = LogFactory.getLog(DynamoDbContext.class);

    private AmazonDynamoDBClient _client;

    public DynamoDbContext()
    {
         _client = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());
    }

    public void initialize()
    {
        CallbackRegRepository.initialize(_client);
    }

    public CallbackRegRepository getCallbackRegRepository()
    {
        return new CallbackRegRepository(_client);
    }
}
