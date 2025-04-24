<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 상품 목록</title>
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
    <h2>내 상품 목록</h2>

    <table>
        <thead>
        <tr>
            <th>상품 ID</th>
            <th>브랜드</th>
            <th>상품명</th>
            <th>가격</th>
            <th>상품 코드</th>
            <th>크기(cm³)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${List}">
            <tr>
                <td>${product.prodId}</td>
                <td>${product.brand}</td>
                <td>${product.prodName}</td>
                <td><fmt:formatNumber value="${product.prodPrice}" pattern="#,###" /> 원</td>
                <td>${product.prodCode}</td>
                <td>${product.prodSize}</td>
            </tr>
        </c:forEach>

        <c:if test="${empty productDTOList}">
            <tr>
                <td colspan="6">등록된 상품이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- ✅ 페이지네이션 -->
    <c:if test="${totalPages > 1}">
        <div class="pagination" style="margin-top: 20px; text-align: center;">
            <ul style="display: inline-flex; list-style: none; padding: 0;">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li style="margin: 0 5px;">
                        <c:url var="pageUrl" value="">
                            <c:param name="page" value="${i}" />
                        </c:url>
                        <a href="${pageUrl}"
                           style="padding: 6px 12px; text-decoration: none; border: 1px solid #ccc; border-radius: 4px;
                                   background-color: ${i == currentPage ? '#333' : '#fff'};
                                   color: ${i == currentPage ? '#fff' : '#000'};">
                                ${i}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

</div>

</body>
</html>
