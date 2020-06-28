<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
  </head>
  <body>
    <%@ include file="header.jsp"%>>

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form method="post" modelAttribute="userForm">
        <div class="form-group">
          <form:input type="email" name="email" placeholder="Email"  path="username"/>
          <form:errors path="username"/>
        </div>
        <div class="form-group">
          <form:input type="password" name="password" placeholder="Hasło"  path="password"/>
          <form:errors path="password"/>
        </div>
        <div class="form-group">
          <form:input type="password" name="password2" placeholder="Powtórz hasło"  path="passwordConfirm"/>
          <form:errors path="passwordConfirm"/>
        </div>

        <div class="form-group form-group--buttons">
          <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

<%@ include file="footer.jsp"%>>
  </body>
</html>
