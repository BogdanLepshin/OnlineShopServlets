<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 14.02.2021
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <h1 class="mb-5"><fmt:message key="header.btn.orders"/></h1>

    <c:if test="${orders.isEmpty()}">
        <h3><fmt:message key="orders.isEmpty"/></h3>
    </c:if>
    <c:if test="${orders.isEmpty() == false}">
        <c:forEach var="order" items="${orders}">
            <a style="text-decoration: none; color:black" href="${pageContext.request.contextPath}/api/orders/order_details?id=${order.id}">
                <div style="padding: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); border-radius: 10px 10px 10px 10px;"
                     class="card mb-3">
                    <table class="table">

                        <tr>
                            <td>
                                <h5><fmt:message key="orders.order"/> № ${order.id}</h5>
                            </td>
                            <td style="text-align: end">
                                <c:if test="${order.completionDate == null}">
                                    <c:if test="${sessionScope.lang == 'ru'}">
                                        ${order.formatCreationDateRu()}
                                    </c:if>
                                    <c:if test="${sessionScope.lang == 'en'}">
                                        ${order.formatCreationDateEn()}
                                    </c:if>
                                </c:if>
                                <c:if test="${order.completionDate != null}">
                                    <c:if test="${sessionScope.lang == 'ru'}">
                                        ${order.formatCompletionDateRu()}
                                    </c:if>
                                    <c:if test="${sessionScope.lang == 'en'}">
                                        ${order.formatCompletionDateEn()}
                                    </c:if>
                                </c:if>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b <c:if test="${order.status.id == 3}">style="color:green"</c:if>
                                   <c:if test="${order.status.id == 2}">style="color:red"</c:if>
                                   <c:if test="${order.status.id == 1}">style="color:blue"</c:if>>
                                    <c:if test="${sessionScope.lang == 'ru'}">
                                        ${order.status.nameRu}
                                    </c:if>
                                    <c:if test="${sessionScope.lang == 'en'}">
                                        ${order.status.nameEn}
                                    </c:if>
                                </b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="width: 150px;">
                                <c:forEach var="od" items="${order.orderDetailsList}">
                                    <img style="width:5%; margin-right: 10px"
                                         src="/product_images/${od.product.id}/${od.product.image}"/>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: end" colspan="2"><b><fmt:message
                                    key="product.total"/>: ${order.totalPrice}$</b></td>
                        </tr>
                    </table>
                </div>
            </a>
        </c:forEach>
    </c:if>

</div>
</body>
</html>
