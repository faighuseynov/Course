<%--
  Created by IntelliJ IDEA.
  User: fuadp
  Date: 4/2/16
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>

    <script type="text/javascript" src="js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="css/login.css" />

    <style type="text/css">
    .msg {
        margin-left: -195px;
        color: red;
    }
    </style>

</head>
<body>
<div class="container">
    <div class="card card-container">
        <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
        <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
        <p id="profile-name" class="profile-name-card"></p>
        <form class="form-signin" action="ls" method="post">
            <c:if test="${not empty msg}">
                <label class="msg">${msg}</label>
            </c:if>
            <span id="reauth-email" class="reauth-email"></span>
            <input type="text" id="inputEmail" name="username" class="form-control" placeholder="Username" >
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" >
            <div id="remember" class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
        </form><!-- /form -->
        <a href="#" class="forgot-password">
            Forgot the password?
        </a>
    </div><!-- /card-container -->
</div><!-- /container -->

</body>
</html>


<!--
you can substitue the span of reauth email for a input with the email and
include the remember me checkbox
-->
