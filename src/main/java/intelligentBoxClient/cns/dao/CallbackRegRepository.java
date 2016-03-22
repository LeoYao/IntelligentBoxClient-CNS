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

import java.util.Arrays;

/**
 * Created by yaohx on 3/22/2016.
 */
public class CallbackRegRepository {

    private DynamoDBMapper _mapper;

    private final static Log logger = LogFactory.getLog(CallbackRegRepository.class);

    public CallbackRegRepository(AmazonDynamoDBClient client) {
        _mapper = new DynamoDBMapper(client);
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

    public boolean upsert(CallbackReg item) {
        return upsert(item, false);
    }

    public boolean upsert(CallbackReg item, boolean overwritten) {
        try {
            if (!overwritten) {
                _mapper.save(item);
            } else {
                _mapper.save(item, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            }
            return true;
        } catch (Exception e) {
            logger.error("Error in upsert.", e);
            return false;
        }
    }

    public boolean delete(CallbackReg item) {
        return delete(item, false);
    }

    public boolean delete(CallbackReg item, boolean force) {
        try {
            if (!force) {
                _mapper.delete(item);
            } else {
                _mapper.delete(item, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            }
            return true;
        } catch (Exception e) {
            logger.error("Error in delete.", e);
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
