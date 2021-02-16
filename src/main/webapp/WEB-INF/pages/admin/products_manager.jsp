<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 04.02.2021
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Product Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body class="page">
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container-fluid">
    <h2>Manage Products</h2>
    <a class="btn btn-outline-primary mt-2" href="${pageContext.request.contextPath}/api/admin/products_manager/create_product"><fmt:message key="products.btn.newProduct"/></a>
        <form action="${pageContext.request.contextPath}/api/admin/products_manager" method="post">
            <div class="row mt-3">
                <label for="category" class="col-1"><fmt:message key="products.label.category"/> :</label>
                <select name="category" class="col-3 form-select w-25" id="category">

                <c:set value="${selected_category}" var="selected"/>
                    <option value="0" selected><fmt:message key="products.category.allCategories"/></option>
                    <c:forEach var="category" items="${categories}">
                    <c:if test="${sessionScope.lang == 'ru'}">
                        <option value="${category.id}"
                            ${category.id == selected ? 'selected' : ''}>${category.nameRu}</option>
                    </c:if>
                    <c:if test="${sessionScope.lang == 'en'}">
                        <option value="${category.id}"
                            ${category.id == selected ? 'selected' : ''}>${category.nameEn}</option>
                    </c:if>
                    </c:forEach>
                </select>
            </div>
            <button style="display: none" id="btn_select_category" type="submit"></button>
        </form>
    <table class="table table-bordered mt-1">
        <thead class="table-dark">
        <th scope="col">ID</th>
        <th scope="col"><fmt:message key="products.table.image"/></th>
        <th scope="col"><fmt:message key="products.table.productName"/></th>
        <th scope="col"><fmt:message key="products.table.brand"/></th>
        <th scope="col"><fmt:message key="products.table.category"/></th>
        <th scope="col"><fmt:message key="products.table.enabled"/></th>
        <th scope="col"><fmt:message key="products.table.actions"/></th>
        </thead>
        <tbody>
            <c:forEach varStatus="i" var="item" items="${products}">

                <tr>
                    <td>${item.id}</td>
                    <td style="width: 150px;">
                        <a href="/product_images/${item.id}/${item.image}">
                            <img style="width:100%" src="/product_images/${item.id}/${item.image}"/>
                        </a>
                    </td>
                    <td>${item.name}</td>
                    <td>${item.brand.name}</td>
                    <c:if test="${sessionScope.lang == 'ru'}">
                        <td>${item.category.nameRu}</td>
                    </c:if>
                    <c:if test="${sessionScope.lang == 'en'}">
                        <td>${item.category.nameEn}</td>
                    </c:if>
                    <td>
                        <form action="${pageContext.request.contextPath}/api/admin/products_manager/product_status" method="get">
                            <input type="hidden" name="id" value="${item.id}">
                            <div class="form-check form-switch">
                            <input onclick="switchClick(${i.index})" name="product_status" class="form-check-input" type="checkbox" id="switch" ${item.enabled == true ? 'checked' : ''}>
                        </div>
                            <input id="submit${i.index}" type="submit" hidden>
                        </form>
                    </td>
                    <input type="hidden" value="${i.index}">

                    <td>
                        <a class="nav-link" href="${pageContext.request.contextPath}/api/admin/products_manager/edit_product?id=${item.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                            <fmt:message key="products.table.actions.edit"/>
                        </a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/api/admin/products_manager/delete?id=${item.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                            <fmt:message key="products.table.actions.remove"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script>
    $('#category').on('change', function () {
        $('#btn_select_category').click();
    });
    function switchClick(id) {
        $('#submit'+id).click();
    }
</script>
</body>
</html>
