<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 27.01.2021
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp" />
</header>
<div class="container">
    <%--<nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link"
                   th:text="#{users.page.prev}"
                   th:href="@{/users(page=${users.previousOrFirstPageable().getPageNumber()+1})}">Previous</a>
            </li>
            <li th:each="page : ${pageNumbers}" class="page-item"
                th:classappend="${page==users.getNumber() + 1} ? active">
                <a class="page-link" th:href="@{/users(page=${page})}" th:text="${page}">1</a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   th:text="#{users.page.next}"
                   th:href="@{/users(page=${users.nextOrLastPageable().getPageNumber()+1})}">Next</a>
            </li>
        </ul>
    </nav>--%>
    <table class="table table-bordered" style="background-color:white; margin-top: 30px;" align="center" border="1">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td scope="row">${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
