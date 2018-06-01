<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 24.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/document_table.js" type="text/javascript"></script>
<table class="custom_table">
  <tr>
    <td>ID</td>
    <td>Тип</td>
    <td>Регистрационный номер</td>
    <td>Дата регистрации</td>
    <td>Автор</td>
  </tr>
  <c:if test="${empty documents}">
    <tr>
      <td colspan="5" align="center">
        <i class="fas fa-exclamation-triangle" style="font-size:24px;color:red;"></i> Ничего не найдено
      </td>
    </tr>
  </c:if>
  <c:forEach items="${documents}" var="document">
    <tr onclick=clickTableTr("<c:url value = "document/${document.id}"/>")>
      <td>${document.id}</td>
      <td>${document.storeName}</td>
      <td>${document.registrationNumber}</td>
      <td><fmt:formatDate value="${document.registrationDate}" pattern="HH:mm dd.MM.yyyy" /></td>
      <td>${document.author.lastName} ${document.author.firstName} ${document.author.middleName}</td>
    </tr>
  </c:forEach>
</table>