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
    <form:form action="/test/form" method="post" role="form" commandName="testForm">
      <spring:bind path="name">
        <div>
          <form:label path="name">Nazwa: </form:label>
          <form:input path="name"/>
        </div>
      </spring:bind>
      <div>
        <button type="submit">Wyślij</button>
      </div>
    </form:form>
  </div>
</body>
</html>
