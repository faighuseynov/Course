package az.course.util;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/20/16
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public final class SelectConstants {

    public static final String getStudentList = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,D.DESCRIPTION AS SECTOR FROM STUDENT S\n" +
            " INNER JOIN DICTIONARY D ON S.SECTOR_ID = D.ID WHERE S.ACTIVE = 1";

    public static final String getSectorComboList = "SELECT ID,DESCRIPTION from dictionary\n" +
            "WHERE DICT_NAME = 'sector' ";

    public static final String getStudentById = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,D.ID as SECTOR_ID,D.DESCRIPTION AS SECTOR FROM STUDENT S\n" +
            " INNER JOIN DICTIONARY D ON S.SECTOR_ID = D.ID WHERE S.ACTIVE = 1 AND S.ID = ?";


    public static final String getStudentComboList = "SELECT S.ID,S.NAME,S.SURNAME FROM STUDENT S WHERE S.ACTIVE = 1";

    public static final String getStudentComboBySectorId = "SELECT S.ID,S.NAME,S.SURNAME,S.ADRESS,S.DOB,D.DESCRIPTION AS SECTOR FROM STUDENT S\n" +
            " INNER JOIN DICTIONARY D ON S.SECTOR_ID = D.ID WHERE S.ACTIVE = 1 and S.SECTOR_ID = ?";



}
