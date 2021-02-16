<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Order № ${order.id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <h1 class="mb-5"><fmt:message key="makeOrder.order"/></h1>

    <h4><fmt:message key="orderDetails.status"/>:
        <span
                <c:if test="${order.status.id == 3}">style="color:green"</c:if>
                <c:if test="${order.status.id == 2}">style="color:red"</c:if>
                <c:if test="${order.status.id == 1}">style="color:blue"</c:if>>
            <c:if test="${sessionScope.lang == 'ru'}">
                ${order.status.nameRu}
            </c:if>
            <c:if test="${sessionScope.lang == 'en'}">
                ${order.status.nameEn}
            </c:if>
        </span>
    </h4>

    <ul>
        <c:if test="${order.status.id == 3}">
            <li>
                <fmt:message key="orderDetails.paidAt"/>:
                <c:if test="${sessionScope.lang == 'ru'}">
                    ${order.formatCompletionDateRu()}
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    ${order.formatCompletionDateEn()}
                </c:if>
            </li>
        </c:if>

        <c:if test="${order.status.id == 2}">
            <li>
                <fmt:message key="orderDetails.canceledAt"/>:
                <c:if test="${sessionScope.lang == 'ru'}">
                    ${order.formatCompletionDateRu()}
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    ${order.formatCompletionDateEn()}
                </c:if>
            </li>
        </c:if>

        <li>
            <fmt:message key="orderDetails.createdAt"/>:
            <c:if test="${sessionScope.lang == 'ru'}">
                ${order.formatCreationDateRu()}
            </c:if>
            <c:if test="${sessionScope.lang == 'en'}">
                ${order.formatCreationDateEn()}
            </c:if>
        </li>
    </ul>

    <table class="table">
        <form action="/api/admin/orders_manager/order_details/change_status" method="post">
        <tr>
            <td colspan="5">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <p><fmt:message key="orderDetails.changeStatus"/>:</p>
                    <select name="statusId" id="status" class="form-select w-25">
                        <c:if test="${sessionScope.lang == 'ru'}">
                            <c:forEach var="status" items="${statuses}">
                                <option value="${status.id}" ${order.status.id == status.id ? 'selected' : ''}>
                                        ${status.nameRu}
                                </option>
                            </c:forEach>
                        </c:if>

                        <c:if test="${sessionScope.lang == 'en'}">
                            <c:forEach var="status" items="${statuses}">
                                <option value="${status.id}" ${order.status.id == status.id ? 'selected' : ''}>
                                        ${status.nameEn}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <button id="change_status" style="display: none" type="submit"></button>

            </td>
        </tr>
        </form>
        <tr>
            <th></th>
            <th><fmt:message key="product.productName"/></th>
            <th><fmt:message key="product.price"/></th>
            <th><fmt:message key="product.amount"/></th>
            <th><fmt:message key="product.sum"/></th>
        </tr>
        <c:forEach var="item" items="${order.orderDetailsList}">
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
                <b><fmt:message key="product.total"/>: ${order.totalPrice}$</b>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <h4 class="mb-4"><fmt:message key="makeOrder.payment"/></h4>
                <c:if test="${sessionScope.lang == 'ru'}">
                    ${order.payment.paymentTypeRu}
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    ${order.payment.paymentTypeEn}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <h4 class="mb-4"><fmt:message key="makeOrder.delivery"/></h4>
                <c:if test="${sessionScope.lang == 'ru'}">
                    ${order.delivery.typeRu}
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    ${order.delivery.typeEn}
                </c:if>
            </td>
        </tr>
    </table>

</div>
<script>
    $('#status').on('change', function () {
        $('#change_status').click();
    });
</script>
</body>
</html>
