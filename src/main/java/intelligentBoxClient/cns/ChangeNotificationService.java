package intelligentBoxClient.cns;

import intelligentBoxClient.cns.controllers.WebhookController;
import intelligentBoxClient.cns.dao.SqliteContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

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

        SpringApplication.run(ChangeNotificationService.class, args);

    }
}
