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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

/**
 * Created by Leo on 3/20/16.
 */

@SpringBootApplication
public class ChangeNotificationService {
    private final static Log logger = LogFactory.getLog(ChangeNotificationService.class);

    public static void main(String[] args) {

        SpringApplication.run(ChangeNotificationService.class, args);
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DynamoDbContext dynamoDbContext()
    {
        return new DynamoDbContext();
    }
}
