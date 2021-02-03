<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 28.01.2021
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<nav style="padding-left: 20px; padding-right: 20px" class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/api/home">ONLINE SHOP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <%--<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Products Catalog
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/products/notebooks">Notebooks</a>
                    <a class="dropdown-item" href="/products/notebooks">Smartphones</a>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarAdminDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Admin
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarAdminDropdownMenuLink">
                    <a class="dropdown-item" href="/products_manager">Products Manager</a>
                    <a class="dropdown-item" href="/users">Users</a>
                </ul>
            </li>--%>
            <div class="collapse navbar-collapse">
                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/api/login"><fmt:message key="header.btn.signin"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/api/register"><fmt:message key="header.btn.signup"/></a>
                    </li>
                </c:if>

                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/api/logout"><fmt:message key="header.btn.logout"/></a>
                    </li>
                </c:if>
            </div>
            <%-- <li class="nav-item">
                 <a class="nav-link" href="#">Basket</a>
             </li>--%>
            <%-- <li class="nav-item">
                 <form class="nav-item" action="/logout" method="post">
                     <input type="submit" class="btn nav-link" value="Log out">
                 </form>
             </li>--%>
        </ul>


    </div>
    <div class="lang">
        <a href="?lang=ru">RU</a>/
        <a href="?lang=en">EN</a>
    </div>
    <%--    </div>
    &lt;%&ndash;    <select style="color:rgba(255,255,255,.55)" class="custom-select-dark bg-dark" id="locales">
            <option></option>
            <option value="en">English</option>
            <option value="ru">Русский</option>
        </select>&ndash;%&gt;--%>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    /*    $(document).ready(function () {
            $("#locales").change(function () {
                var selectedOption = $('#locales').val();
                window.location.replace('?lang=' + selectedOption);
            });
        });*/
    /*    $('#locales').on('change', function() {
            // Save value in localstorage
            sessionStorage.setItem("lang", $(this).val());
        });
        $(document).ready(function() {
            if ($('#locales').length) {
                $('#locales').val(sessionStorage.getItem("lang"));
            }
        });*/
</script>
</html>
