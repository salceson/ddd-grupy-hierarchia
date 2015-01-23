<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Poll</title>
</head>
<body>
<header>
    <h1>Poll</h1>
</header>
<div>
    Content: ${poll.content}.<br/>
    Deadline: ${poll.deadline}
</div>
</body>
</html>
