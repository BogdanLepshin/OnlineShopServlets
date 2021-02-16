<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 16.02.2021
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Orders Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <table class="table table" style="background-color:white; margin-top: 30px;" align="center" border="1">
        <thead class="table-dark">
        <tr>
            <th><fmt:message key="ordersManager.orderId"/></th>
            <th><fmt:message key="ordersManager.userId"/></th>
            <th><fmt:message key="ordersManager.createdAt"/></th>
            <th><fmt:message key="ordersManager.completedAt"/></th>
            <th><fmt:message key="ordersManager.paymentType"/></th>
            <th><fmt:message key="ordersManager.deliverType"/></th>
            <th><fmt:message key="ordersManager.status"/></th>
            <th><fmt:message key="ordersManager.totalPrice"/></th>
            <td></td>
        </tr>
        </thead>

        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <c:if test="${sessionScope.lang == 'ru'}">
                    <td>${order.formatCreationDateRu()}</td>
                    <td>${order.formatCompletionDateRu()}</td>
                    <td>${order.payment.paymentTypeRu}</td>
                    <td>${order.delivery.typeRu}</td>
                    <td>${order.status.nameRu}</td>
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    <td>${order.formatCreationDateEn()}</td>
                    <td>${order.formatCompletionDateEn()}</td>
                    <td>${order.payment.paymentTypeEn}</td>
                    <td>${order.delivery.typeEn}</td>
                    <td>${order.status.nameEn}</td>
                </c:if>
                <td>${order.totalPrice}$</td>
                <td>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/api/admin/orders_manager/order_details?orderId=${order.id}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
                            <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                            <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                        </svg>
                        <fmt:message key="products.table.actions.edit"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%--<script>
    function setStatus(id) {
        $('#status').val(3);
    }
</script>--%>
</body>
</html>
