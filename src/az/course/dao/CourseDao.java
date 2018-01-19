package az.course.dao;

import az.course.model.Student;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/20/16
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CourseDao {

    public List<Student> getStudentList() throws Exception;

    public void addStudent(Student student) throws Exception;

    public List<Student> getSectorComboList() throws Exception;

    public Student editStudent(long studentId) throws Exception;

    public List<Student> searchStudent(String keyword) throws Exception;

    public List<Student> getStudentComboList() throws Exception;

    List<Student> getStudentComboListBySectorId(int sectorId) throws Exception;

    List<Student> getAdvSearchStudentList(Student student) throws Exception;

    public void updateStudent(Student student,long studentId) throws Exception;

    String checkUsername(String username) throws Exception;

}
