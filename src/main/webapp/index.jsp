<%--
  Created by IntelliJ IDEA.
  User: nsychev
  Date: 24.05.2018
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>ECM</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

  </head>
  <body style="width:100%; height: 100%;margin: 0;">
    <div class="left_menu">
      <%
        String requestURL = request.getRequestURL().toString();
        String servletPath = request.getServletPath();
        String appPath = requestURL.substring(0, requestURL.indexOf(servletPath));
        String fullpath = appPath + request.getAttribute("javax.servlet.forward.servlet_path");
      %>
      <ul>
        <li><a href="<%= fullpath %>">Все авторы</a></li>
      </ul>
    </div>
    <div class="content">
      <c:choose>
        <c:when test="${page == 'persons'}">
          <%@ include file="jsp/table_person.jsp" %>
        </c:when>
        <c:when test="${page == 'documentsByAuthor'}">
          <%@ include file="jsp/table_documents.jsp" %>
        </c:when>
        <c:when test="${page == 'task'}">
          <%@ include file="jsp/task_card.jsp" %>
        </c:when>
        <c:when test="${page == 'incoming'}">
          <%@ include file="jsp/incoming_card.jsp" %>
        </c:when>
        <c:when test="${page == 'outgoing'}">
          <%@ include file="jsp/outgoing_card.jsp" %>
        </c:when>
        <c:otherwise>
          PAGE NOT FOUND
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
