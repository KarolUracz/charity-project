<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<%@ include file="header.jsp" %>
>

<section class="login-page">
    <h2>Zresetuj hasło</h2>
    <form:form method="post" action="/reset-password" modelAttribute="user">
        <input type="hidden"  path="username" type="hidden" value="${user.username}"/>
        <div class="form-group">
            <form:input type="password" name="password" placeholder="Hasło" path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło"/>
        </div>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" name="Potwierdź">
        </div>
    </form:form>
</section>

<%@ include file="footer.jsp" %>
>
</body>
</html>
