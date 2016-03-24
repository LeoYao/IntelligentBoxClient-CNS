package intelligentBoxClient.cns.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by yaohx on 3/24/2016.
 */
public interface IDynamoDbContext {
    AmazonDynamoDBClient getClient();
    void initialize();
}
