<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 08.02.2021
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Edit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container-fluid">
    <h2>Edit product</h2>
    <form action="${pageContext.request.contextPath}/api/admin/products_manager/edit_product/save" method="post" enctype="multipart/form-data" >
        <div class="form-group mt-5 mb-3 w-25">
            <p>Current image: <span>${product.image}</span></p>
            <input type="text" name="id" value="${product.id}" hidden/>
            <input type="text" name="product_image" value="${product.image}" hidden/>
            <img id="img" style="width: 50%" src="/product_images/${product.id}/${product.image}" alt="${product.image}"/>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="product_pic"><fmt:message key="product.image"/></label>
            <input type="file" class="form-control" name="file" id="product_pic" accept=".png, .jpg, .jpeg">
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="name"><fmt:message key="product.productName"/></label>
            <input value="${product.name}" name="name" type="text" class="form-control" id="name" required>
        </div>
        <c:set var="selected_brandId" value="${product.brand.id}"/>
        <c:set var="selected_categoryId" value="${product.category.id}"/>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="brand"><fmt:message key="product.brand"/></label>
            <select name="brand" class="form-control" id="brand" required>
                <c:forEach var="brand" items="${brands}">
                    <option ${brand.id == selected_brandId ? 'selected' : ''} value="${brand.id}">${brand.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="category"><fmt:message key="product.category"/></label>
            <select name="category" class="form-control" id="category" required>
                <c:forEach var="category" items="${categories}">
                    <c:if test="${sessionScope.lang == 'ru'}">
                        <option ${category.id == selected_categoryId ? 'selected' : ''} value="${category.id}">${category.nameRu}</option>
                    </c:if>
                    <c:if test="${sessionScope.lang == 'en'}">
                        <option ${category.id == selected_categoryId ? 'selected' : ''} value="${category.id}">${category.nameEn}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div  class="form-group mb-3 w-50">
            <label class="form-label" for="description_ru"><fmt:message key="product.descriptionRu"/></label>
            <textarea style="white-space: pre-wrap" name="description_ru" cols=50 rows="10" class="form-control" id="description_ru">${product.descriptionRu}</textarea>
        </div>
        <div  class="form-group mb-3 w-50">
            <label class="form-label" for="description_en"><fmt:message key="product.descriptionEn"/></label>
            <textarea style="white-space: pre-wrap" name="description_en" cols=50 rows="10" class="form-control" id="description_en">${product.descriptionEn}</textarea>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="price"><fmt:message key="product.price"/></label>
            <input value="${product.price}" name="price" type="number" class="form-control" id="price" required>
            <span style="display: none" id="invalid" class="invalid-feedback"><fmt:message key="product.price.invalidfeedback"/></span>
        </div>
        <input id="btn" type="submit"
               class="btn btn-primary" value="Edit product"/>
    </form>
</div>
</body>

</html>