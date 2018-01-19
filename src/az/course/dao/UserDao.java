package az.course.dao;

import az.course.model.Student;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 3/19/16
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public Student login(String username,String password) throws Exception;
}
