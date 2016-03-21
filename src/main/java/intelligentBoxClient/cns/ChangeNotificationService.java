package intelligentBoxClient.cns;

import intelligentBoxClient.cns.controllers.WebhookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Leo on 3/20/16.
 */

@SpringBootApplication
public class ChangeNotificationService
{
    public static void main( String[] args )
    {
        SpringApplication.run(ChangeNotificationService.class, args);
        //System.out.println( "Hello World!" );
    }
}
