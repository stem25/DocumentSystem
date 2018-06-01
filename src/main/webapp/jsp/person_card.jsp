<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 30.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/personCard.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/task_card.js" type="text/javascript"></script>
<div class="person_card">
    <h2>
        <span onclick="goBack()"><i class="fa fa-arrow-left" style="font-size:21px;color:royalblue;padding-right: 0.1em"></i></span>${person.shortname}
    </h2>
    <div>
        <c:if test="${empty person.avatar}"><img src="${pageContext.request.contextPath}/img/avatar/unknown.png"></c:if>
        <c:if test="${not empty person.avatar}"><img src="${pageContext.request.contextPath}/img/avatar/${person.avatar}"></c:if>
        <label for="lastName">Фамилия:</label>
        <input type="text" id="lastName" name="lastName" value="${person.lastName}" readonly><br>
        <label for="firstName">Имя:</label>
        <input type="text" id="firstName" name="firstName" value="${person.firstName}" readonly><br>
        <label for="middleName">Отчество:</label>
        <input type="text" id="middleName" name="middleName" value="${person.middleName}" readonly><br>
        <label for="position">Должность:</label>
        <input type="text" id="position" name="position" value="${person.position}" readonly><br>
    </div>
</div>