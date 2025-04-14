<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="config.jsp"%>
</head>
<body>
<div class="wrapper">

    <%@ include file="sidebar.jsp" %>
    <div class="main">

        <%@ include file="header.jsp" %>
        <main class="content">
        <jsp:include page="${body}" />
        </main>

    </div>
</div>



<%@ include file="footer.jsp" %>

<!-- JS 공통 연결 -->
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
</body>
</html>