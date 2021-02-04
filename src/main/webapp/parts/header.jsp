<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 28.01.2021
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<nav style="padding-left: 20px; padding-right: 20px" class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/api/home">ONLINE SHOP</a>
    <div class="d-flex justify-content-between" style="width:100%" id="navbarNav">
        <ul class="navbar-nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/api/login"><fmt:message key="header.btn.signin"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/api/register"><fmt:message key="header.btn.signup"/></a>
                </li>
            </c:if>

            <c:if test="${user != null}">
                <c:if test="${isAdmin}">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown"
                            aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink"
                                   role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Admin
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark"
                                    aria-labelledby="navbarDarkDropdownMenuLink">
                                    <li><a class="dropdown-item" href="/api/users">Users</a></li>
                                    <li><a class="dropdown-item" href="#">Products manage</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:if>


            </c:if>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="?lang=ru">RU</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?lang=en">EN</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/api/logout"><fmt:message key="header.btn.logout"/></a>
            </li>
        </ul>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</html>
