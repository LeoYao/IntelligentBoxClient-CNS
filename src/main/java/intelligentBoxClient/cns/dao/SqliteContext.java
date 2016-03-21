package intelligentBoxClient.cns.dao;

import java.sql.*;

/**
 * Created by Leo on 3/20/16.
 */
public class SqliteContext {

    private Connection c = null;

    public void connect(String dbFile) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

        System.out.println("Opened database successfully");
    }

    public void disconnect() {

        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void query() throws SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tbl1;");
        while (rs.next()) {
            String one = rs.getString("one");
            int two = rs.getInt("two");
            System.out.println("one = " + one);
            System.out.println("two = " + two);
            System.out.println();
        }
        rs.close();
        stmt.close();
    }

}
