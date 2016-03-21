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

    public void CreateCallbackRegistrationTable()
    {
        String sql = "create tabel call_back_reg "
                + "(user_id int UNIQUE, call_back_url varchar(255) if not exists;";
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

    public void tx_insert() throws SQLException {
        c.setAutoCommit(false);
        //c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        Statement stmt = c.createStatement();
        stmt.execute(
                "Insert into tbl1 (one, two) values " +
                        "(\"world\", 30);");
        c.rollback();
        stmt.close();
        c.setAutoCommit(true);
    }

    public void insert() throws SQLException {
        Statement stmt = c.createStatement();
        stmt.execute(
                "Insert into tbl1 (one, two) values " +
                        "(\"world\", 40);");

        stmt.close();
    }
}
