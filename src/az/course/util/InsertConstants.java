package az.course.util;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/24/16
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public final class InsertConstants {

    public static final String addStudent = "INSERT INTO STUDENT(ID,NAME,SURNAME,ADRESS,DOB,SEKTOR_ID)\n" +
            " VALUES(STUDENT_SEQ.NEXTVAL,?,?,?,?,?)";
    public static final String addLoginUser = "INSERT INTO LOGIN_USER(ID,USERNAME,PASSWORD,STUDENT_ID,ROLE)\n" +
            "VALUES(LOGIN_USER_SEQ.NEXTVAL,?,?,?,?)";

}
