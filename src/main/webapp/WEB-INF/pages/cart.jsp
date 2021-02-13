<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 11.02.2021
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <c:if test="${cart.items.isEmpty() == true}">
        <h1><fmt:message key="cart.empty"/></h1>
    </c:if>
    <c:if test="${cart.items.isEmpty() == false}">
        <table class="table">
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td style="width: 150px;"><img style="width:100%" src="/product_images/${item.product.id}/${item.product.image}" alt="${item.product.name}"/></td>
                    <td>
                        <a style="text-decoration: none; color: black" href="${pageContext.request.contextPath}/api/products/product_details?product_id=${item.product.id}">
                            ${item.product.name}
                        </a>
                    </td>
                    <td><fmt:message key="cart.amount"/> ${item.amount}</td>
                    <td style="text-align: end">
                        <a class="nav-link" href="${pageContext.request.contextPath}/api/cart/remove?id=${item.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                            <fmt:message key="products.table.actions.remove"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <button class="btn btn-outline-primary" type="submit">
                        <fmt:message key="cart.makeOrder"/>
                    </button>
                </td>
                <td style="text-align: end" colspan="3">
                    <h5>
                        <fmt:message key="cart.totalPrice"/> ${cart.totalPrice}$
                    </h5>
                </td>
            </tr>
        </table>
    </c:if>
</div>


</body>
</html>
