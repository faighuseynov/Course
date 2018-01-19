package az.course.service;

import az.course.dao.CourseDao;
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
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return courseDao.getStudentList();
    }

    @Override
    public void addStudent(Student student) throws Exception {
        courseDao.addStudent(student);
    }

    @Override
    public List<Student> getSectorComboList() throws Exception {
        return courseDao.getSectorComboList();
    }

    @Override
    public Student editStudent(long studentId) throws Exception {
        return courseDao.editStudent(studentId);
    }

    @Override
    public List<Student> searchStudent(String keyword) throws Exception {
        return courseDao.searchStudent(keyword);
    }

    @Override
    public List<Student> getStudentComboList() throws Exception {
        return courseDao.getStudentComboList();
}

    @Override
    public List<Student> getStudentComboListBySectorId(int sectorId) throws Exception {
        return courseDao.getStudentComboListBySectorId(sectorId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Student> getAdvSearchStudentList(Student student) throws Exception{
        return courseDao.getAdvSearchStudentList(student);
}

    @Override
    public void updateStudent(Student student, long studentId) throws Exception {
        courseDao.updateStudent(student,studentId);
    }

    @Override
    public String checkUsername(String username) throws Exception {
        return courseDao.checkUsername(username);
    }
}
