package az.course.dao;

import az.course.model.LoginUser;
import az.course.model.Student;
import az.course.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 3/19/16
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements UserDao {



    @Override
    public Student login(String username, String password) throws Exception {
        Student user = new Student();
        Connection c =  null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT L.ID,L.USERNAME,L.PASSWORD,L.STUDENT_ID,L.ROLE,S.NAME,S.SURNAME FROM LOGIN_USER L\n" +
                "INNER JOIN STUDENT S ON L.STUDENT_ID = S.ID\n" +
                "WHERE L.ACTIVE = 1 AND S.ACTIVE = 1 AND L.USERNAME = ? AND L.PASSWORD = ?";
          try {
             c = DbHelper.getConnection();
              if (c != null) {
                 ps = c.prepareStatement(sql);
                 ps.setString(1,username);
                 ps.setString(2,password);
                 rs = ps.executeQuery();
                 if (rs.next()) {
                   user.setName(rs.getString("NAME"));
                   user.setSurname(rs.getString("SURNAME"));
                   user.setRole(rs.getString("ROLE"));

                 }    else {
                     user = null;
                 }
              }  else {
                  System.out.println("Connection is null!");
              }
          }   catch (Exception ex) {
              ex.printStackTrace();
          }   finally {
              JdbcUtil.close(c,ps,rs);
          }


        return user;
    }
}
