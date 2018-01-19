package az.course.dao;

import az.course.model.Student;
import az.course.util.InsertConstants;
import az.course.util.JdbcUtil;
import az.course.util.SelectConstants;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/20/16
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */

public class CourseDaoImpl implements CourseDao {




    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SelectConstants.getStudentList;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
               ps = c.prepareStatement(sql);
               rs = ps.executeQuery();
               while (rs.next()) {
                  Student student = new Student();
                  student.setId(rs.getLong("ID"));
                  student.setName(rs.getString("NAME"));
                  student.setSurname(rs.getString("SURNAME"));
                  student.setAddress(rs.getString("ADRESS"));
                  student.setDob(rs.getDate("DOB"));
                  student.setSector(rs.getString("SEKTOR"));
                  studentList.add(student);

               }
            }  else {
                System.out.println("Connection is null!");
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
    }

        return studentList;
    }

    @Override
    public void addStudent(Student student) throws Exception {
        Connection c = null;
        CallableStatement cs = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        String sql1 = InsertConstants.addStudent;
        String sql2 = InsertConstants.addLoginUser;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
              ps = c.prepareStatement(sql1, new String[]{"id"});
              ps.setString(1,student.getName());
              ps.setString(2,student.getSurname());
              ps.setString(3,student.getAddress());
              ps.setDate(4,new Date(student.getDob().getTime()));
              ps.setLong(5, student.getSectorId());
              ps.executeUpdate();
              ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long lastId = rs.getLong(1);
                    ps1 = c.prepareStatement(sql2);
                    ps1.setString(1,student.getUsername());
                    ps1.setString(2,student.getPassword());
                    ps1.setLong(3,lastId);
                    ps1.setString(4,student.getRole());
                    ps1.execute();
                }

            }   else {
                System.out.println("Connection is null!");
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            c.commit();
            JdbcUtil.close(c,ps1,null);
        }
    }

    @Override
    public List<Student> getSectorComboList() throws Exception {
        List<Student> sectorList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SelectConstants.getSectorComboList;
        try {
            c = DbHelper.getConnection();
            if (c !=null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student sector = new Student();
                    sector.setSectorId(rs.getInt("ID"));
                    sector.setSector(rs.getString("DESCRIPTION"));
                    sectorList.add(sector);
                }
            }   else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
        }
        return sectorList;
    }

    @Override
    public Student editStudent(long studentId) throws Exception {
        Student student = new Student();
        Connection c = null;
      //  PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{?=call QRUP11.GETSTUDENTBYIDFUNC(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, studentId);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                if (rs.next()) {

                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setSectorId(rs.getInt("SEKTOR_ID"));
                   /* System.out.println("SektorId = "+rs.getInt("SEKTOR_ID"));*/
                 //   student.setSector(rs.getString("SEKTOR"));

                }
            }  else {
                System.out.println("Connection is null!");
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close2(c, cs, rs);
        }

        return student;
    }

    @Override
    public List<Student> searchStudent(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,D.DESCRIPTION AS SEKTOR FROM STUDENT S\n" +
                "INNER JOIN DICTIONARY D ON S.SEKTOR_ID = D.ID WHERE S.ACTIVE = 1 AND\n" +
                "(LOWER(S.NAME) LIKE LOWER('%"+keyword+"%') OR LOWER(S.SURNAME) LIKE LOWER('%"+keyword+"%') OR LOWER(S.ADRESS) LIKE LOWER('%"+keyword+"%')\n" +
                "OR LOWER(D.DESCRIPTION) LIKE LOWER('%"+keyword+"%'))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setSector(rs.getString("SEKTOR"));
                    studentList.add(student);

                }
            }  else {
                System.out.println("Connection is null!");
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
        }

        return studentList;
    }

    @Override
    public List<Student> getStudentComboList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SelectConstants.getStudentComboList;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    studentList.add(student);

                }
            }  else {
                System.out.println("Connection is null!");
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
        }

        return studentList;
    }

    @Override
    public List<Student> getStudentComboListBySectorId(int sectorId) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SelectConstants.getStudentComboBySectorId;
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1,sectorId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    studentList.add(student);

                }
            }  else {
                System.out.println("Connection is null!");
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
        }

        return studentList;
    }

    @Override
    public List<Student> getAdvSearchStudentList(Student student) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,D.DESCRIPTION AS SEKTOR FROM STUDENT S\n" +
                "INNER JOIN DICTIONARY D ON S.SEKTOR_ID = D.ID WHERE S.ACTIVE = 1 ";
     //   String sql1 = ;
      //  String sql2 = " AND S.DOB >= ? ";
      //  String sql3 = " and S.DOB < ? ";
        try {
          c = DbHelper.getConnection();
          if (c != null) {

             if (student.getSectorId() != 0) {
                  sql+=" AND S.SEKTOR_ID = "+student.getSectorId();

              }
              if (student.getBeginDate() != null) {
                  sql += " AND S.DOB >=  "+"TO_DATE('"+new java.sql.Date(dateFormat.parse(student.getBeginDate()).getTime())+"','YYYY-MM-DD')";
              }
              if (student.getEndDate() != null) {
                  sql += " AND S.DOB < "+"TO_DATE('"+new java.sql.Date(dateFormat.parse(student.getEndDate()).getTime())+"','YYYY-MM-DD')";
              }
              System.out.println(sql);
              ps = c.prepareStatement(sql);
              rs = ps.executeQuery();
              while (rs.next()) {
                  Student st = new Student();
                  st.setId(rs.getLong("ID"));
                  st.setName(rs.getString("NAME"));
                  st.setSurname(rs.getString("SURNAME"));
                  st.setAddress(rs.getString("ADRESS"));
                  st.setDob(rs.getDate("DOB"));
                  st.setSector(rs.getString("SEKTOR"));
                  studentList.add(st);

              }


          }   else {
              System.out.println("Connection is null!");
          }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtil.close(c,ps,rs);
        }
        return studentList;
    }

    @Override
    public void updateStudent(Student student, long studentId) throws Exception {
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QRUP11.MAIN_PACK.UPDATESTUDENT(?,?,?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
               cs = c.prepareCall(sql);
                cs.setString(1,student.getName());
                cs.setString(2,student.getSurname());
                cs.setString(3,student.getAddress());
                cs.setDate(4,new Date(student.getDob().getTime()));
                cs.setLong(5, student.getSectorId());
                cs.setLong(6,studentId);
                cs.execute();


            }   else {
                System.out.println("Connection is null!");
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            c.commit();
            JdbcUtil.close2(c,cs,null);
        }
    }

    @Override
    public String checkUsername(String username) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT username from LOGIN_USER\n" +
                "where username = ?";
        try {
           c = DbHelper.getConnection();
           if (c != null) {
              ps = c.prepareStatement(sql);
              ps.setString(1,username);
              rs = ps.executeQuery();
              if (rs.next()) {
                  return "true";
              }
           }   else {
               System.out.println("Connection is null!");
           }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }  finally {
            JdbcUtil.close(c,ps,rs);
        }
        return "false";
    }
}

