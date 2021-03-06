<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<script src="<c:url value="/resources/js/app.js"/>"></script>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                ${admin.username}
                <ul class="dropdown">
                    <li><a href="/admin/institutions">Zarządzaj fundacjami</a></li>
                    <li><a href="/admin/administrators">Zarządzaj administratorami</a></li>
                    <li><a href="/admin/users">Zarządzaj użytkownikami</a></li>
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
        <section>
            <h2>Dodaj dane instytucji:</h2>
            <form:form action="/admin/userAdd" method="post" modelAttribute="user">
                <form:hidden path="id"/>
                <div class="form-group form-group--inline">
                    <label>
                        Wpisz nazwę użytkownika: <form:input path="username"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Wpisz hasło użytkownika: <form:input path="password"/>
                    </label>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </form:form>
        </section>
    </nav>
</header>



<%@include file="/WEB-INF/views/footer.jsp" %>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
