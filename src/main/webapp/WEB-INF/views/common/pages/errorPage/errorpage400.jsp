<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        color: #343a40;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0;
    }
    .error-container {
        text-align: center;
        border: 1px solid #dee2e6;
        background-color: #ffffff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .error-code {
        font-size: 72px;
        font-weight: bold;
        color: #dc3545;
    }
    .error-message {
        font-size: 24px;
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .error-hint {
        font-size: 16px;
        color: #6c757d;
    }
    .error-button {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .error-button:hover {
        background-color: #0056b3;
    }
</style>

<div class="error-container">
    <div class="error-code">400</div>
    <div class="error-message">잘못된 요청입니다.</div>
    <div class="error-hint">요청하신 정보가 유효하지 않거나, 필수 값이 누락되었을 수 있습니다.<br>
        입력 값을 다시 확인해주세요.</div>
    <button onclick="history.back()" class="error-button">이전 페이지로 돌아가기</button>
</div>
</body>
</html>