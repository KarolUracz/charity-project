<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Panel Użytkownika</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                ${user.username}
                <ul class="dropdown">
                    <li><a href="/user/editProfile/${user.id}">Profil</a></li>
                    <li><a href="/user/myDonations/${user.id}">Moje zbiórki</a></li>
                    <li><a href="/user/passwordChange/${user.id}">Zmień hasło</a></li>
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
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
                <br>
                <div></div>
                <div class="btn"><a href="/user/donation">Formularz</a></div>
            </div>
        </div>
    </div>
</header>
<section class="help">
    <h2>Moje dary:</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
<%--        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.--%>
<%--            Możesz sprawdzić czym się zajmują.</p>--%>

        <ul class="help--slides-items">
            <c:forEach items="${userDonations}" var="userDonation" varStatus="loop">
                <c:if test="${loop.count % 2 != 0}">
                    <li>
                </c:if>
                <div class="col">
                    <div class="title">Data: ${userDonation.pickUpDate} ${userDonation.pickUpTime} Ilość: ${userDonation.quantity}</div>
                    <div class="subtitle">${userDonation.street} ${userDonation.city} ${userDonation.zipCode}</div>
                    <div class="subtitle">Dla ${userDonation.institution.name}</div>
                    <div class="subtitle">Status:
                        <c:if test="${userDonation.picked == false}">nieodebrane</c:if>
                        <c:if test="${userDonation.picked == true}">odebrano</c:if>
                    </div>
                </div>
                <c:if test="${loop.count % 2 == 0}">
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
