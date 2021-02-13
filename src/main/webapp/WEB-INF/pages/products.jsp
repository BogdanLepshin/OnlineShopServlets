<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 10.02.2021
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container">
    <div class="row justify-content-center mt-5">
        <c:forEach varStatus="i" var="item" items="${products}">
            <div class="card" style="width: 18rem;">
                <img style="width: 60%; margin-left: auto; margin-right: auto"
                     src="${pageContext.request.contextPath}/product_images/${item.id}/${item.image}"
                     class="card-img-top" alt="...">
                <div class="card-body">
                    <a style="text-decoration: none; color: black"
                       href="${pageContext.request.contextPath}/api/products/product_details?product_id=${item.id}">
                        <h6 style="text-align: center" class="card-title">${item.name}</h6>
                    </a>
                    <p class="card-price">
                        <b>${item.price}$</b>
                    </p>
                    <form action="${pageContext.request.contextPath}/api/products/addToCart">
                        <input type="hidden" name="product_id" value="${item.id}">
                        <input type="hidden" name="quantity" value="1">
                        <button id="submit" class="btn btn-outline-primary w-100" type="submit">
                            <svg style="margin-bottom: 4px" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                 fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
                                <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                            </svg>
                            <b><fmt:message key="products.addToCart"/></b>
                        </button>
                    </form>
                    <%--<a&lt;%&ndash;style="width: 100%;border-color: black;border-radius: 0;background-color: lightgray"&ndash;%&gt;
                            href="#" class="btn btn-outline-primary w-100">
                        <svg style="margin-bottom: 4px" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                             fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
                            <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                        </svg>
                        <b><fmt:message key="products.addToCart"/></b></a>--%>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function switchClick(id) {
        $('#submit'+id).click();
    }
</script>
</body>
</html>
