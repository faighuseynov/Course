package az.course.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/20/16
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class DbHelper {
    public static Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/Course2");
        Connection conn = ds.getConnection();
        if (conn == null ){
            throw new Exception("Connection is null");
        } else {
            System.out.println("Connected!");
        }
        return conn;
    }
}
