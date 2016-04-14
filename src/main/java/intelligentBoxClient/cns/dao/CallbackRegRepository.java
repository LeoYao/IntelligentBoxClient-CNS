package intelligentBoxClient.cns.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import intelligentBoxClient.cns.dao.objects.CallbackReg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaohx on 3/22/2016.
 */
@Repository
public class CallbackRegRepository implements ICallbackRegRepository {

    private DynamoDBMapper _mapper;

    private final static Log logger = LogFactory.getLog(CallbackRegRepository.class);

    @Autowired
    public CallbackRegRepository(DynamoDbContext ctx) {
        _mapper = new DynamoDBMapper(ctx.getClient());
    }

    public CallbackReg get(String accountId) {
        try {

            CallbackReg item = _mapper.load(CallbackReg.class, accountId);
            return item;
        } catch (Exception e) {
            logger.error("Error in get.", e);
            return null;
        }
    }

    public boolean register(String accountId, String callbackUrl){
        try {
            CallbackReg item = _mapper.load(CallbackReg.class, accountId);
            if (item == null || item.getCallbackUrl() == null || item.getCallbackUrl().trim().length() == 0){
                item = new CallbackReg(accountId, callbackUrl);
                _mapper.save(item, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            } else {
                List<String> callbackUrls = Arrays.asList(item.getCallbackUrl().split(","));
                if (!callbackUrls.contains(callbackUrl)){
                    String newCallbackUrls = item.getCallbackUrl() + "," + callbackUrl;
                    CallbackReg newItem = new CallbackReg(accountId, newCallbackUrls);
                    _mapper.save(newItem, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Error in get.", e);
            return false;
        }
    }

    public boolean unregister(String accountId, String callbackUrl){
        try {
            CallbackReg item = _mapper.load(CallbackReg.class, accountId);
            if (item == null || item.getCallbackUrl() == null || item.getCallbackUrl().trim().length() == 0){
                return true;
            } else {
                List<String> callbackUrls = Arrays.asList(item.getCallbackUrl().split(","));
                if (callbackUrls.contains(callbackUrl)){
                    if (callbackUrls.size() == 1){
                        _mapper.delete(item, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (String cu : callbackUrls) {
                            if (cu.equals(callbackUrl)){
                                continue;
                            }
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(cu);
                        }
                        String newCallbackUrls = sb.toString();
                        CallbackReg newItem = new CallbackReg(accountId, newCallbackUrls);
                        _mapper.save(newItem, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("Error in get.", e);
            return false;
        }
    }

    public static void initialize(AmazonDynamoDBClient client)
    {
        try {
            DynamoDB dynamoDB = new DynamoDB(client);

            String tableName = "CallbackReg";
            logger.debug("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(
                            new KeySchemaElement("account_id", KeyType.HASH)
                    ),
                    Arrays.asList(
                            new AttributeDefinition("account_id", ScalarAttributeType.S)
                    ),
                    new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
            logger.debug("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (ResourceInUseException e) {
            logger.trace("Unable to create table: ", e);
        } catch (InterruptedException e) {
            logger.error("Unable to create table: ", e);
        }
    }

}
