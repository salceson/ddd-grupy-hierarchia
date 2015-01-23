<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Test Form</title>
</head>
<body>
  <header>
    <h1>Test form</h1>
  </header>
  <div>
    <form:form action="/poll/add" method="post" role="form" commandName="pollForm">
      <spring:bind path="content">
        <div>
          <form:label path="content">Idea: </form:label>
          <form:textarea path="content"/>
        </div>
      </spring:bind>
      <spring:bind path="deadline">
        <div>
          <form:label path="deadline">Deadline (YYYY-MM-DD HH:MM): </form:label>
          <form:input path="deadline" type="datetime"/>
        </div>
      </spring:bind>
      <div>
        <button type="submit">Create</button>
      </div>
    </form:form>
  </div>
</body>
</html>
