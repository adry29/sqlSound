
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adryc
 */
public class bdConnector {
    String dburl="jdbc:mysql://localhost:3306/sqlsound";
    String dbuser="root";
    String dbpassword="";
    Connection conn;
    
    public Connection connect() throws SQLException{
        conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
        return conn;
    }
    
    public void disconnect() throws SQLException{
        conn.close();
    }
}
