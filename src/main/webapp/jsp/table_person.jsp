<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 24.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/person_table.js" type="text/javascript"></script>
<table class="custom_table">
  <tr>
    <td>ID</td>
    <td>Фамилия</td>
    <td>Имя</td>
    <td>Отчество</td>
  </tr>
  <c:if test="${empty persons}">
    <tr>
      <td colspan="4" align="center">
      <i class="fas fa-exclamation-triangle" style="font-size:24px;color:red;"></i> Ничего не найдено
      </td>
    </tr>
  </c:if>
  <c:forEach items="${persons}" var="person">
    <tr onclick=clickTableTr("<c:url value = "ecm/${person.id}"/>")>
      <td>${person.id}</td>
      <td>${person.lastName}</td>
      <td>${person.firstName}</td>
      <td>${person.middleName}</td>
    </tr>
  </c:forEach>
</table>
