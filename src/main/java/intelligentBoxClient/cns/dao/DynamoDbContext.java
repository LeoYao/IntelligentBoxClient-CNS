package intelligentBoxClient.cns.dao;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Created by yaohx on 3/22/2016.
 */
@Repository
public class DynamoDbContext implements IDynamoDbContext {

    private final static Log logger = LogFactory.getLog(DynamoDbContext.class);

    private AmazonDynamoDBClient _client;

    public DynamoDbContext() {
        _client = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());
    }

    public AmazonDynamoDBClient getClient()
    {
        return _client;
    }

    public void initialize()
    {
        CallbackRegRepository.initialize(_client);
    }
}
