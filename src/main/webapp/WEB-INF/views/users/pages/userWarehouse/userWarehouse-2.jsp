<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 창고 신청 내역</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8fafc;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            text-align: center;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.06);
            border: 1px solid #e2e8f0;
        }
        table th {
            background-color: #e2e8f0;
            color: #1f2937;
            font-size: 14px;
            padding: 12px;
            font-weight: bold;
        }
        table td {
            padding: 12px;
            font-size: 14px;
            border-top: 1px solid #e2e8f0;
        }
        table tbody tr:hover {
            background-color: #f1f5f9;
            transition: background-color 0.2s ease;
            cursor: pointer;
        }
        h2 {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div style="padding: 40px;">
    <h2>내 창고 신청 내역</h2>

    <table>
        <thead>
        <tr>
            <th>창고 ID</th>
            <th>섹션</th>
            <th>사용 면적 (㎡)</th>
            <th>계약 면적 (㎡)</th>
            <th>시작일</th>
            <th>종료일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="warehouse" items="${myWarehouses}">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${warehouse.wareId == 'W001'}">서울</c:when>
                        <c:when test="${warehouse.wareId == 'W002'}">부산</c:when>
                        <c:when test="${warehouse.wareId == 'W003'}">대구</c:when>
                        <c:when test="${warehouse.wareId == 'W004'}">광주</c:when>
                        <c:when test="${warehouse.wareId == 'W005'}">대전</c:when>
                        <c:otherwise>${warehouse.wareId}</c:otherwise>
                    </c:choose>
                </td>
                <td>${warehouse.warehousePosX}${warehouse.warehousePosY}</td>
                <td>${warehouse.warehouseUsage}</td>
                <td>${warehouse.contractArea}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty warehouse.wareStartDate}">
                            <fmt:formatDate value="${warehouse.wareStartDate}" pattern="yyyy-MM-dd"/>
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${not empty warehouse.wareEndDate}">
                            <fmt:formatDate value="${warehouse.wareEndDate}" pattern="yyyy-MM-dd"/>
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>

        <c:if test="${empty myWarehouses}">
            <tr>
                <td colspan="7">신청한 창고 내역이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

</body>
</html>
