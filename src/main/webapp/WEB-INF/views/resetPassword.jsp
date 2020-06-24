<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
  </head>
  <body>
    <%@include file="header.jsp"%>

    <section class="login-page">
      <form action="/resetPassword" method="post">
        <div class="form-group form-group--inline">
        <label> Podaj maila na, którego mamy wysłać link do resetu hasła: <input type="text" id="resetMail" name="resetMail"/></label>
        </div>
        <div class="form-group form-group--buttons">
          <button type="submit" class="btn">Potwierdzam</button>
        </div>
      </form>
      </section>
    </section>

  <%@include file="footer.jsp"%>
  </body>
</html>
