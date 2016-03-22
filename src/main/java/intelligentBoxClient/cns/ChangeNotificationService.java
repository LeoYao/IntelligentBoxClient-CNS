package intelligentBoxClient.cns;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Leo on 3/20/16.
 */

@SpringBootApplication
public class ChangeNotificationService
{
    private final static Log logger = LogFactory.getLog(ChangeNotificationService.class);

    public static void main(String[] args) {

/*
        SqliteContext ctx = new SqliteContext();
        try {
            ctx.connect("test.db");
            ctx.query();
            System.out.println("------------------");
            ctx.tx_insert();
            ctx.query();
            System.out.println("------------------");
            ctx.insert();
            ctx.query();
            System.out.println("------------------");
        } catch (SQLException e) {
            if (SQLiteErrorCode.SQLITE_BUSY.code == e.getErrorCode()) {
                System.out.println("locked");
            } else {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ctx.disconnect();
        }*/

        //SpringApplication.run(ChangeNotificationService.class, args);

        AmazonDynamoDBClient client = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());

        DynamoDB dynamoDB = new DynamoDB(client);

        /*
        String tableName = "Movies";

        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(
                            new KeySchemaElement("year", KeyType.HASH),  //Partition key
                            new KeySchemaElement("title", KeyType.RANGE)), //Sort key
                    Arrays.asList(
                            new AttributeDefinition("year", ScalarAttributeType.N),
                            new AttributeDefinition("title", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
        */

        Table table = dynamoDB.getTable("call_back_reg");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#uid", "user_id");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":uid", 12345678);

        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("user_id = :uid")
                //.withNameMap(new NameMap().with("#uid", "user_id"))
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {

            //items = table.query(querySpec);

            items = table.query(new KeyAttribute("user_id", 12345678));
            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                logger.info(item.getNumber("user_id") + ": "
                        + item.getString("call_back_url"));
            }


            items = table.query(new KeyAttribute("user_id", 23456789));
            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                logger.info(item.getNumber("user_id") + ": "
                        + item.getString("call_back_url"));
            }
        } catch (Exception e) {
            System.err.println("Unable to query an item of id 12345678");
            System.err.println(e.getMessage());
        }


    }
}
