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
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div style="margin-left: auto; margin-right: auto" class="card col-3 mt-5">
    <div class="card-body">
        <form class="form-signin"
              action="${pageContext.request.contextPath}/api/register"
              autocomplete="off"
              method="post" novalidate>

            <p style="text-align: center" ><fmt:message key="register.form.info"/></p>
            <c:if test="${userExists}">
                <div class="alert ${userExists ? 'alert-danger' : ''}" role="alert">
                    <span><fmt:message key="register.error.userExists"/></span>
                </div>
            </c:if>

            <div id="error" class="alert alert-danger" role="alert" style="display: none">
                <span><fmt:message key="register.error.password.dontMatch"/></span>
            </div>
            <p class="form-group">
                <label for="firstName"><fmt:message key="register.form.label.firstName"/></label>
                <input class="form-control ${firstNameError ? 'is-invalid' : ''}" value="${user.firstName}" type="text" name="firstName" id="firstName"
                       required>
                <span class="invalid-feedback"><fmt:message key="register.validation.firstName"/></span>
            </p>
            <p class="form-group">
                <label for="lastName"><fmt:message key="register.form.label.lastName"/></label>
                <input class="form-control ${lastNameError ? 'is-invalid' : ''}" value="${user.lastName}" type="text" name="lastName" id="lastName" required>
                <span class="invalid-feedback"><fmt:message key="register.validation.lastName"/></span>
            </p>
            <p class="form-group">
                <label for="email"><fmt:message key="register.form.label.email"/></label>
                <input class="form-control ${emailError ? 'is-invalid' : ''}" value="${user.email}" type="email" name="email" id="email"
                       placeholder="name@example.com"
                       required>
                <span class="invalid-feedback"><fmt:message key="register.validation.email"/></span>
            </p>
            <p class="form-group">
                <label for="password"><fmt:message key="register.form.label.password"/></label>
                <input class="form-control ${passwordError ? 'is-invalid' : ''}" type="password" name="password" id="password" required>
                <c:if test="${passwordError}">
                    <span class="invalid-feedback"><fmt:message key="register.validation.password"/></span>
                </c:if>
            </p>
            <p class="form-group">
                <label for="confirmPassword"><fmt:message key="register.form.label.confirmPassword"/></label>
                <input class="form-control ${passwordError ? 'is-invalid' : ''}" type="password" id="confirmPassword"
                       required>
                <c:if test="${passwordError}">
                    <span class="invalid-feedback"><fmt:message key="register.validation.password"/></span>
                </c:if>
            </p>
            <button id="btn" class="btn btn-primary" type="submit"><fmt:message key="btn.submit.register"/></button>
        </form>
    </div>
</div>
</div>
<script>
    /*    $(document).ready(function () {
            $('#error').hide();
        });*/
    $('#password, #confirmPassword').on('keyup', function () {
        if ($('#password').val() === $('#confirmPassword').val()) {
            $('#error').hide()
            $('#password').removeClass("is-invalid");
            $('#confirmPassword').removeClass("is-invalid");
            $('#btn').prop( 'disabled', false );

        } else {
            $('#error').show()
            $('#password').addClass("is-invalid");
            $('#confirmPassword').addClass("is-invalid");
            $('#btn').prop( "disabled", true );
        }
    });
</script>
</body>
</html>
