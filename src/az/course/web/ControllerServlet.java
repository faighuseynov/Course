package az.course.web;

import az.course.dao.CourseDao;
import az.course.dao.CourseDaoImpl;
import az.course.dao.DbHelper;
import az.course.model.Student;
import az.course.service.CourseService;
import az.course.service.CourseServiceImpl;
import az.course.util.Constants;
import az.course.util.Security;
import az.course.util.SendMail;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 2/17/16
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControllerServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
          processRequest(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = "";
        String address = "";
        CourseDao courseDao = new CourseDaoImpl();
        CourseService courseService = new CourseServiceImpl(courseDao);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        try {
            if (action.equalsIgnoreCase("getStudentList"))  {
                List<Student> studentList = courseService.getStudentList();
                request.setAttribute("studentList",studentList);
                address = "WEB-INF/parseJsp/studentData.jsp";

            } else if (action.equalsIgnoreCase("getStudentComboList")) {
                int sectorId = Integer.parseInt(request.getParameter("sectorId"));
                List<Student> studentList = null;
                if (sectorId == 0) {
                    studentList = courseService.getStudentComboList();
                }   else {
                    studentList = courseService.getStudentComboListBySectorId(sectorId);
            }

                request.setAttribute("studentComboList",studentList);
                address = "WEB-INF/parseJsp/studentComboData.jsp";
            }

            else if (action.equalsIgnoreCase("getTeacherList")) {
                List<Student> teacherList = courseService.getStudentList();
                request.setAttribute("teacherList",teacherList);
                address = "WEB-INF/parseJsp/teacherData.jsp";
            }

            else if (action.equalsIgnoreCase(Constants.ADD_STUDENT)) {
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                Student student = new Student();
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String dob = request.getParameter("dob");
                int sectorId = Integer.parseInt(request.getParameter("sector"));
                String username = request.getParameter("username");
                String pwd = request.getParameter("pwd");
                String role = request.getParameter("role");

                System.out.println("dob = "+dob);
                student.setName(name);
                student.setSurname(surname);
                student.setAddress(adress);
                student.setDob(dateFormat2.parse(dob));
                student.setSectorId(sectorId);
                student.setUsername(username);
                student.setPassword(Security.encodeMd5(pwd));
                student.setRole(role);
                System.out.println("Md5 = "+Security.encodeMd5(pwd)+" Base64= "+Security.encodeBase64(pwd));
                courseService.addStudent(student);
                SendMail.sendMail("Yeni shifre","Sizin melumatlariniz: username = "+username+" pwd: "+pwd,"fuad.pashabeyli@gmail.com");

            }  else if (action.equalsIgnoreCase(Constants.GET_SECTOR_COMBO_LIST)) {
                List<Student> sectorList = courseService.getSectorComboList();
                request.setAttribute("sectorList",sectorList);
                address = "WEB-INF/parseJsp/sectorComboData.jsp";
            }  else if (action.equalsIgnoreCase(Constants.EDIT_STUDENT)) {
                long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = courseService.editStudent(studentId);
                request.setAttribute("student",student);
                address = "WEB-INF/parseJsp/editStudent.jsp";
            }  else if (action.equalsIgnoreCase("searchStudent")) {
                String keyword = request.getParameter("keyword");
                List<Student> studentList = courseService.searchStudent(keyword);
                request.setAttribute("studentList",studentList);
                address = "WEB-INF/parseJsp/studentData.jsp";

            }  else if (action.equalsIgnoreCase("searchTeacher")) {
                String keyword = request.getParameter("keyword");
            }  else if (action.equalsIgnoreCase("advancedSearchStudent")) {
                String beginDate = null;
                String endDate = null;
                if (request.getParameter("beginDate") != "" ) {
                beginDate = request.getParameter("beginDate");
                }
                if (request.getParameter("endDate") != "") {
                    endDate = request.getParameter("endDate");
                }

                long sectorComboId = Long.parseLong(request.getParameter("sectorComboId"));
                Student s = new Student();
                s.setSectorId(sectorComboId);
                s.setBeginDate(beginDate);
                s.setEndDate(endDate);


                /*if (!request.getParameter("beginDate").equals(null) || !request.getParameter("beginDate").equals("") || !request.getParameter("beginDate").isEmpty()) {
                     beginDate = dateFormat.parse(request.getParameter("beginDate"));
                }
                if (!request.getParameter("endDate").equals(null) || !request.getParameter("endDate").equals("") || !request.getParameter("endDate").isEmpty()) {
                    endDate = dateFormat.parse(request.getParameter("endDate"));
                }*/

                List<Student> getAdvSearchStudentList = courseService.getAdvSearchStudentList(s);
                request.setAttribute("studentList",getAdvSearchStudentList);
                address = "WEB-INF/parseJsp/studentData.jsp";
            }    else if (action.equalsIgnoreCase("updateStudent")) {
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = new Student();
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String dob = request.getParameter("dob");
                int sectorId = Integer.parseInt(request.getParameter("sector"));
                student.setName(name);
                student.setSurname(surname);
                student.setAddress(adress);
                student.setDob(dateFormat2.parse(dob));
                student.setSectorId(sectorId);
                courseService.updateStudent(student,studentId);
            }  else if (action.equalsIgnoreCase("checkUsername")) {
                String username = request.getParameter("username");
                String check = courseService.checkUsername(username);
                System.out.println(check);
                request.setAttribute("check",check);
                address = "WEB-INF/parseJsp/check.jsp";


            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
        requestDispatcher.forward(request,response);


    }

}
