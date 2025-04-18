<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>WareFlow | 초기 진입 페이지</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f6f9;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background: white;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h1 {
            margin-bottom: 12px;
            font-size: 28px;
            color: #0066cc;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        .note {
            margin-top: 20px;
            font-size: 13px;
            color: #999;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🚀 WareFlow 초기 진입 페이지</h1>
    <p>이 페이지는 팀원들이 진입하는 기본 홈입니다.</p>
    <p>추후 로그인, 대시보드 또는 공지 등으로 연결할 수 있습니다.</p>
    <div class="note">
        📂 위치: <code>/WEB-INF/views/index.jsp</code>
    </div>
    <div style="margin-top: 30px;">
        <a href="admin/pages/index" style="margin-right: 20px; text-decoration: none; color: #0066cc;">🔧 관리자 페이지</a>
        <a href="users/pages/index" style="text-decoration: none; color: #009900;">👤 유저 페이지</a>
        <a href="<c:url value='/login' />" style="margin-left: 20px; text-decoration: none; color: #cc0000;">🔑 로그인</a>
    </div>
    <div style="margin-top: 40px; padding: 20px; background-color: #f0f0f0; border-radius: 10px;">
        <p style="font-weight: bold; margin-bottom: 10px;">📦 컴포넌트 페이지</p><br>
        <a href="<c:url value='/components/pages-sign-in' />" style="margin-right: 15px;">🔐 로그인</a>
        <a href="<c:url value='/common/pages/signup' />" style="margin-right: 15px;">📝 회원가입</a>
        <a href="<c:url value='/components/ui-buttons' />" style="margin-right: 15px;">🔲 버튼</a>
        <a href="<c:url value='/components/ui-forms' />" style="margin-right: 15px;">🧾 입력폼</a>
        <a href="<c:url value='/components/ui-cards' />" style="margin-right: 15px;">📇 카드</a>
        <a href="<c:url value='/components/charts-chartjs' />" style="margin-right: 15px;">📊 차트</a>
        <a href="<c:url value='/components/ui-typography' />" style="margin-right: 15px;">🔤 텍스트</a>
        <a href="<c:url value='/components/icons-feather' />" style="margin-right: 15px;">🎨 아이콘</a>
        <a href="<c:url value='/components/maps-google' />" style="margin-right: 15px;">🗺 구글맵</a>
        <a href="<c:url value='/components/pages-blank' />" style="margin-right: 15px;">📄 빈 페이지</a>
    </div>
</div>
</body>
</html>
