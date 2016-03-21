package intelligentBoxClient.cns;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import intelligentBoxClient.cns.controllers.WebhookController;
import intelligentBoxClient.cns.dao.SqliteContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.Arrays;

import org.sqlite.JDBC.*;
import org.sqlite.SQLiteErrorCode;

/**
 * Created by Leo on 3/20/16.
 */

@SpringBootApplication
public class ChangeNotificationService
{

    public static void main( String[] args )
    {
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

        DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider()));

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

    }
}
