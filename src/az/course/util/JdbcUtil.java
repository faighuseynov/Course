package az.course.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/20/16
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcUtil {

    public static void close(Connection c,PreparedStatement ps,ResultSet rs) throws Exception{
        if (c != null) {
            c.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public static void close2(Connection c,CallableStatement cs,ResultSet rs) throws Exception{
        if (c != null) {
            c.close();
        }
        if (cs != null) {
            cs.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}
