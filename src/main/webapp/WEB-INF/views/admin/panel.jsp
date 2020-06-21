<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags" %>
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
                    <li><a href="#">Zarządzaj administratorami</a></li>
                    <li><a href="#">Zarządzaj użytkownikami</a></li>
                    <li>
                        <nav>
                            <form action="<c:url value="/logout"/>" method="post">
                                <input type="submit" value="Wyloguj">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </nav
                        >
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
        <h2>
            <div class="col">
                <div class="title"><a href="/admin/institutions">Zarządzaj fundacjami</a></div>
            </div>
            <div class="col">
                <div class="title"><a href="/admin/manageAdmins">Zarządzaj administratorami</a></div>
            </div>
            <div class="col">
                <div class="title"><a href="/admin/manageUsers">Zarządzaj użytkownikami</a></div>
            </div>
        </h2>
    </div>
</header>
</body>
</html>
