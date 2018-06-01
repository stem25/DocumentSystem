<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 24.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<script src="${pageContext.request.contextPath}/js/person_table.js" type="text/javascript"></script>
<table class="custom_table">
  <tr>
    <td>ID</td>
    <td>Фамилия</td>
    <td>Имя</td>
    <td>Отчество</td>
    <td style="width:5em;"></td>
  </tr>
  <c:if test="${empty persons}">
    <tr>
      <td colspan="4" align="center">
      <i class="fas fa-exclamation-triangle" style="font-size:24px;color:red;"></i> Ничего не найдено
      </td>
    </tr>
  </c:if>
  <c:forEach items="${persons}" var="person">
    <tr>
      <td onclick=clickTableTr("<c:url value = "ecm/${person.id}"/>")>${person.id}</td>
      <td onclick=clickTableTr("<c:url value = "ecm/${person.id}"/>")>${person.lastName}</td>
      <td onclick=clickTableTr("<c:url value = "ecm/${person.id}"/>")>${person.firstName}</td>
      <td onclick=clickTableTr("<c:url value = "ecm/${person.id}"/>")>${person.middleName}</td>
      <td onclick=clickTableTr("ecm/person/${person.id}")><span><i class="fas fa-folder-open"></i> Открыть</span></td>
    </tr>
  </c:forEach>
</table>
