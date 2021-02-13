<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 11.02.2021
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>ONLINE SHOP ${product.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="/parts/header.jsp"/>
</header>
<div style="margin-bottom: 100px" class="container">
    <h3 class=" mt-2 mb-5">${product.name}</h3>
    <div style="padding: 32px 0; box-shadow: 0 1px 2px; border-radius: 10px 10px 10px 10px;"
         class="product_details d-flex mb-5">
        <div style="max-width: 50%;" class="details_left">
            <img style="display:block; margin-left:auto; margin-right:auto; width: 70%"
                 src="/product_images/${product.id}/${product.image}">
        </div>
        <div style="max-width: 50%" class="details_right">
            <div style="width:90%; margin: 0 auto">
                <h4 class="mb-4">${product.price}$</h4>
                <h6><fmt:message key="product.brand"/>: ${product.brand.name}</h6>
                <c:if test="${sessionScope.lang == 'ru'}">
                    <h6><fmt:message key="product.category"/>: ${product.category.nameRu}</h6>
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    <h6><fmt:message key="product.category"/>: ${product.category.nameEn}</h6>
                </c:if>

                <h6><fmt:message key="product.description"/>: </h6>

                <c:if test="${sessionScope.lang == 'ru'}">
                    <span style="display:block; word-wrap:break-word; white-space: pre-wrap">${product.descriptionRu}</span>
                </c:if>
                <c:if test="${sessionScope.lang == 'en'}">
                    <span style="display:block; word-wrap:break-word; white-space: pre-wrap">${product.descriptionEn}</span>
                </c:if>
            </div>
        </div>

    </div>
    <form action="${pageContext.request.contextPath}/api/products/addToCart">
        <div class="row">

            <input type="hidden" name="product_id" value="${product.id}">
            <button type="submit" style="margin-right:10px; width: 15%;border-color: black;border-radius: 0;background-color: lightgray"><b><fmt:message key="products.addToCart"/></b></button>
            <button style="width:5%" id="minus" class="btn btn-primary" type="button">-</button>
            <div class="col-1">
                <input type="text" name="quantity" id="quantity" class="form-control" value="1">
            </div>
            <button onclick="" style="width:5%" id="plus" class="btn btn-primary" type="button">+</button>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {
        $('#plus').click(function (e) {

            // Stop acting like a button
            e.preventDefault();
            // Get the field name
            var quantity = parseInt($('#quantity').val());

            // If is not undefined

            $('#quantity').val(quantity + 1);


            // Increment

        });

        $('#minus').click(function (e) {
            // Stop acting like a button
            e.preventDefault();
            // Get the field name
            var quantity = parseInt($('#quantity').val());

            // If is not undefined

            // Increment
            if (quantity > 1) {
                $('#quantity').val(quantity - 1);
            }
        });

    });
</script>
</body>
</html>
