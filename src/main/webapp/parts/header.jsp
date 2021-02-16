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
    <div class="d-flex justify-content-between" style="width:100%" id="navbarNav">
        <ul class="navbar-nav">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarCatalogueDropdown" aria-controls="navbarCatalogueDropdown"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCatalogueDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="catalogueNavbarLink"
                           role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="header.selector.catalog"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark"
                            aria-labelledby="catalogueNavbarLink">
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/api/products?category=1">
                                    <fmt:message key="header.btn.laptops"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/api/products?category=2">
                                    <fmt:message key="header.btn.smartphones"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

            <c:if test="${sessionScope.user != null}">
                <c:if test="${isAdmin}">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown"
                            aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink"
                                   role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <fmt:message key="header.selector.admin"/>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark"
                                    aria-labelledby="navbarDarkDropdownMenuLink">
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/api/admin/users">
                                            <fmt:message key="header.btn.users"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/api/admin/products_manager">
                                            <fmt:message key="header.btn.productsManager"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/api/admin/orders_manager">
                                            <fmt:message key="header.btn.ordersManager"/>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:if>
            </c:if>
        </ul>
        <ul class="navbar-nav">
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/api/orders">
                        <fmt:message key="header.btn.orders"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/api/cart">
                    <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
                        <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                    </svg>
                    <fmt:message key="header.btn.cart"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="javascript:addParam('lang','ru')">RU</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="javascript:addParam('lang','en')">EN</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="btn btn-outline-success"
                       href="/api/logout"><fmt:message key="header.btn.logout"/></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a style="margin-right: 3px" class="btn btn-outline-success" href="/api/login"><fmt:message key="header.btn.signin"/></a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-success" href="/api/register"><fmt:message key="header.btn.signup"/></a>
                </li>
            </c:if>

        </ul>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<script>
/*    function addParam(param, value) {
        var url = new URL(window.location.href);
        if (url.location.indexOf('?') === -1)
            url.searchParams.set(param, value);
        else
        url.searchParams.append(param, value);
    }*/

    function addParam(key, value) {
        key = encodeURIComponent(key);
        value = encodeURIComponent(value);

        // kvp looks like ['key1=value1', 'key2=value2', ...]
        var kvp = document.location.search.substr(1).split('&');
        let i=0;

        for(; i<kvp.length; i++){
            if (kvp[i].startsWith(key + '=')) {
                let pair = kvp[i].split('=');
                pair[1] = value;
                kvp[i] = pair.join('=');
                break;
            }
        }

        if(i >= kvp.length){
            kvp[kvp.length] = [key,value].join('=');
        }

        // can return this or...
        let params = kvp.join('&');

        // reload page with new params
        document.location.search = params;
    }
</script>
</html>
