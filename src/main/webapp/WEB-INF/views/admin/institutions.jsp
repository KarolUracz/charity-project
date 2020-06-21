<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Administrator panel</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                ${admin.username}
                <ul class="dropdown">
                    <li><a href="/admin/institutions">Zarządzaj fundacjami</a></li>
                    <li><a href="/admin/manageAdmins">Zarządzaj administratorami</a></li>
                    <li><a href="/admin/manageUsers">Zarządzaj użytkownikami</a></li>
                    <li>
                        <form action="<c:url value="/logout"/>" method="post">
                            <input type="submit" value="Wyloguj">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="index.html" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <ul class="help--slides-items">
            <c:forEach items="${institutions}" var="institution">
            <li>
                <div class="col">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">${institution.description}</div>
                    <div>
                        <a href="/admin/instUpdate/${institution.id}">Edytuj</a>
                        <a href="/admin/instDelete/${institution.id}">Usuń</a></div>
                </div>
            </li>
            </c:forEach>
            <li>
                <div class="btn"><a href="/admin/instAdd">Dodaj fundację</a></div>
                <div class="btn"><a href="/admin/panel">Wstecz</a></div>
            </li>
    </div>
</header>
<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
