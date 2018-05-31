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
<script src="${pageContext.request.contextPath}/js/task_card.js" type="text/javascript"></script>
<div class="document_card">
    <h2>
        <span onclick="goBack()"><i class="fa fa-arrow-left" style="font-size:21px;color:royalblue;padding-right: 0.1em"></i></span>${document.storeName} №${document.registrationNumber}. ${document.name}
    </h2>
    <div>
        <label for="registrationDate">Дата регистрации:</label>
        <input type="text" id="registrationDate" name="registrationDate" value="<fmt:formatDate value="${document.registrationDate}" pattern="HH:mm dd.MM.yyyy" />" readonly><br>
        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" value="${document.author.shortname}" readonly><br>
        <label for="text">Текст:</label>
        <textarea name="text" id="text" maxlength="255" readonly>${document.text}</textarea>
        <label for="executor">Исполнитель:</label>
        <input type="text" id="executor" name="executor" value="${document.executor.shortname}" readonly><br>
        <label for="issueDate">Дата выдачи:</label>
        <input type="text" id="issueDate" name="issueDate" value="<fmt:formatDate value="${document.issueDate}" pattern="HH:mm dd.MM.yyyy" />" readonly><br>
        <label for="inspector">Контролер:</label>
        <input type="text" id="inspector" name="inspector" value="${document.inspector.shortname}" readonly><br>
    </div>
</div>