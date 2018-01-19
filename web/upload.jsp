<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 4/12/16
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
     <form action="us" method="post" enctype="multipart/form-data">
        <input type="text" name="fileId">
         <input type="file" name="fileName" />
         <input type="submit" value="Gonder" />

     </form>
</body>
</html>