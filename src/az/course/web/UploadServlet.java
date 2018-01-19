package az.course.web;

import az.course.model.Student;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 4/12/16
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Catdi!");
        String filePath = "";
        String fileName = "";
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Does not support!");
            // if not, we stop here
            return;
        }

        // configures some settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(REQUEST_SIZE);
        System.out.println(getServletContext().getRealPath(""));
        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data          // \\
            List formItems = upload.parseRequest((RequestContext) request);
            Iterator iter = formItems.iterator();

            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    fileName = new File(item.getName()).getName();
                    System.out.println("FileName = "+fileName);
                    filePath = uploadPath + File.separator + fileName;
                    System.out.println("filePath = "+filePath);
                    File storeFile = new File(filePath);

                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            Student student = new Student();
            student.setName("Fuad");
            student.setAddress(filePath);

            request.setAttribute("imagePath",filePath);
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
            ex.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/views/view.jsp").forward(request, response);

    }
}
