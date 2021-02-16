<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 13.02.2021
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Make order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <h1><fmt:message key="makeOrder.order"/></h1>
    <table class="table">
        <tr>
            <td colspan="5" style="text-align: end">
                <form action="/api/make_order" method="post">
                    <input type="hidden" name="userId" value="${cartData.userId}"/>
                    <button class="btn btn-outline-primary" type="submit"><fmt:message
                            key="makeOrder.submitOrder"/></button>
                </form>
            </td>
        </tr>
        <tr>
            <th></th>
            <th><fmt:message key="product.productName"/></th>
            <th><fmt:message key="product.price"/></th>
            <th><fmt:message key="product.amount"/></th>
            <th><fmt:message key="product.sum"/></th>
        </tr>
        <c:forEach var="item" items="${cartData.items}">
            <tr>
                <td style="width: 150px;">
                    <img style="width:60%" src="/product_images/${item.product.id}/${item.product.image}"/>
                </td>
                <td>
                    <a style="text-decoration: none; color: black"
                       href="${pageContext.request.contextPath}/api/products/product_details?product_id=${item.product.id}">
                            ${item.product.name}
                    </a>
                </td>
                <td>${item.product.price}$</td>
                <td>${item.amount}</td>
                <td>${item.product.price*item.amount}$</td>
            </tr>

        </c:forEach>
        <tr>
            <td style="text-align: end" colspan="5">
                <b><fmt:message key="product.total"/>: ${cartData.totalPrice}$</b>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <h4 class="mb-4"><fmt:message key="makeOrder.payment"/></h4>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                        <fmt:message key="makeOrder.payment.cash"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2"
                           disabled>
                    <label class="form-check-label" for="flexRadioDefault2">
                        <fmt:message key="makeOrder.payment.card"/>
                    </label>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <h4 class="mb-4"><fmt:message key="makeOrder.delivery"/></h4>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="delivery" id="delivery" checked>
                    <label class="form-check-label" for="delivery">
                        <fmt:message key="makeOrder.delivery.pickUp"/>
                    </label>
                </div>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
