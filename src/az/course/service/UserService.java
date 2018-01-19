package az.course.service;

import az.course.model.LoginUser;
import az.course.model.Student;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 3/19/16
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    public Student login(String username,String password) throws Exception;


}
