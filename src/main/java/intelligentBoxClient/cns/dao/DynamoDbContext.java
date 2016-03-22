package intelligentBoxClient.cns.dao;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.ListTablesSpec;
import com.amazonaws.services.dynamodbv2.model.*;
import javafx.scene.control.Tab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Iterator;

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
