<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 27.01.2021
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Login form's Main</title>
    <%--<link rel="stylesheet" href="/css/style.css">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body class="page">
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container" style="margin-top: 60px">
    <div style="margin-left: auto; margin-right: auto" class="card col-3">
        <div class="card-body ">
            <h2></h2>
            <form class="form-signin"
                  autocomplete="off"
                  action="${pageContext.request.contextPath}/api/login"
                  method="post"
                  novalidate>
                <p style="text-align: center"><fmt:message key="login.form.info"/></p>
                <c:if test="${auth_error}">
                    <div class="alert alert-danger" role="alert">
                        <span><fmt:message key="login.auth.badCredentials"/></span>
                    </div>
                </c:if>
                <c:if test="${access_error}">
                    <div class="alert alert-danger" role="alert">
                        <span><fmt:message key="login.auth.accessError"/></span>
                    </div>
                </c:if>
                <c:if test="${auth_error_session_exists}">
                    <div class="alert alert-danger" role="alert">
                        <span><fmt:message key="login.auth.sessionExists"/></span>
                    </div>
                </c:if>
                <p class="form-group">
                    <label for="email">
                        <fmt:message key="login.form.label.email"/>
                    </label>
                    <input class="form-control" type="email" name="email" id="email"
                           placeholder="email@example.com" required>
                </p>
                <p class="form-group">
                    <fmt:message key="login.form.label.password" var="password"/>
                    <label for="password">${password}</label>
                    <input class="form-control" type="password" name="password" id="password"
                           placeholder="${password}" required>
                </p>
                <button type="submit"
                        class="btn btn-primary"
                        style="margin-top:30px">
                    <fmt:message key="btn.submit.login"/>
                </button>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
