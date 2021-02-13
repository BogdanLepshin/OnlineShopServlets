<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 04.02.2021
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Add product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body class="page">
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div class="container-fluid">
    <h2>Create product</h2>
    <form action="${pageContext.request.contextPath}/api/products_manager/create_product/save" method="post" enctype="multipart/form-data" >
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="product_pic"><fmt:message key="product.image"/></label>
            <input type="file" class="form-control" name="file" id="product_pic" accept=".png, .jpg, .jpeg" required>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="name"><fmt:message key="product.productName"/></label>
            <input name="name" type="text" class="form-control" id="name" required>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="brand"><fmt:message key="product.brand"/></label>
            <select name="brand" class="form-control" id="brand" required>
                <c:forEach var="brand" items="${brands}">
                    <option value="${brand.id}">${brand.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="category"><fmt:message key="product.category"/></label>
            <select name="category" class="form-control" id="category" required>
                <c:forEach var="category" items="${categories}">
                    <c:if test="${sessionScope.lang == 'ru'}">
                        <option value="${category.id}">${category.nameRu}</option>
                    </c:if>
                    <c:if test="${sessionScope.lang == 'en'}">
                        <option value="${category.id}">${category.nameEn}</option>
                    </c:if>
                </c:forEach>

            </select>
        </div>
        <div  class="form-group mb-3 w-50">
            <label class="form-label" for="description_ru"><fmt:message key="product.descriptionRu"/></label>
            <textarea name="description_ru" cols=50 rows="10" class="form-control" id="description_ru"></textarea>
        </div>
        <div  class="form-group mb-3 w-50">
            <label class="form-label" for="description_en"><fmt:message key="product.descriptionEn"/></label>
            <textarea name="description_en" cols=50 rows="10" class="form-control" id="description_en"></textarea>
        </div>
        <div class="form-group mb-3 w-25">
            <label class="form-label" for="price"><fmt:message key="product.price"/></label>
            <input name="price" type="number" class="form-control" id="price" value="0" required>
            <span style="display: none" id="invalid" class="invalid-feedback"><fmt:message key="product.price.invalidfeedback"/></span>
        </div>
        <input id="btn" type="submit"
               class="btn btn-primary" value="Create product"/>
    </form>

</div>
<script>
    $('#price').on('change', function () {
       var price = $(this).val();
       if (price < 0) {
           $(this).addClass('is-invalid')
           $('#btn').prop( "disabled", true);
           $('#invalid').show();
       } else {
           $(this).removeClass('is-invalid')
           $('#btn').prop( "disabled", false);
           $('#invalid').hide();
       }
    });
</script>
</body>
</html>
