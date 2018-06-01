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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/documentCard.css" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/incoming_card.js" type="text/javascript"></script>
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
        <label for="sender">Отправитель:</label>
        <input type="text" id="sender" name="sender" value="${document.sender.shortname}" readonly><br>
        <label for="addressee">Адресат:</label>
        <input type="text" id="addressee" name="addressee" value="${document.addressee.shortname}" readonly><br>
        <label for="outgoingNumber">Исходящий номер:</label>
        <input type="text" id="outgoingNumber" name="outgoingNumber" value="${document.outgoingNumber}" readonly><br>
        <label for="outgoingRegistrationDate">Исходящая дата регистрации:</label>
        <input type="text" id="outgoingRegistrationDate" name="outgoingRegistrationDate" value="<fmt:formatDate value="${document.outgoingRegistrationDate}" pattern="HH:mm dd.MM.yyyy" />" readonly><br>
    </div>
</div>