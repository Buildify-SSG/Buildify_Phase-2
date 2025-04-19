<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    body {
        font-family: 'Noto Sans KR', sans-serif;
        background-color: #f8fafc;
        color: #333;
    }
    .filter-box {
        margin-bottom: 16px;
        font-size: 15px;
    }
    .filter-box strong {
        margin-right: 10px;
    }
    .search-bar {
        display: flex;
        align-items: center;
    }
    .search-bar select,
    .search-bar input,
    .search-bar button {
        padding: 8px 12px;
        margin-right: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 6px;
        background-color: #fff;
    }
    .search-bar button {
        cursor: pointer;
    }
    .export-buttons button {
        padding: 8px 16px;
        margin-left: 10px;
        background-color: #1f2937;
        color: white;
        border: none;
        border-radius: 6px;
        font-weight: 600;
        transition: background-color 0.2s ease;
        cursor: pointer;
    }
    .export-buttons button:hover {
        background-color: #0f172a;
        transform: translateY(-2px);
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
    .status-active {
        color: #10b981;
        font-weight: bold;
    }
    .status-cancelled {
        color: #ef4444;
        font-weight: bold;
    }

    .search-bar input:hover,
    .search-bar select:hover,
    .search-bar input:focus,
    .search-bar select:focus {
        border-color: #94a3b8;
        box-shadow: 0 0 0 2px rgba(148, 163, 184, 0.2);
        outline: none;
    }

    .search-bar button:hover {
        background-color: #1e293b;
        color: white;
        transition: background-color 0.2s ease;
    }

    .filter-box label:hover {
        opacity: 0.8;
        cursor: pointer;
    }

    table tbody tr:hover {
        background-color: #f1f5f9;
        transition: background-color 0.2s ease;
        cursor: pointer;
    }

    .sort {
        color: darkgrey;
    }

</style>

<body>

<div style="padding: 20px;">

    <h1>회원 조회</h1>
    <h4><br></h4>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
        <!-- 검색 영역 -->
        <form method="post" action="/admin/pages/userManagement/userManagement-1/search">
            <div class="search-bar">
                <select name="searchType">
                    <option value="clientId">고객 ID</option>
                    <option value="userName">이름</option>
                    <option value="userPhone">연락처</option>
                    <option value="businessNumber">사업자번호</option>
                </select>
                <input type="text" name="keyword" placeholder="검색" />
                <button type="submit">🔍</button>
            </div>
        </form>

        <!-- export 버튼 -->
        <div class="export-buttons">
            <button style="margin-right: 5px;">exportExcel</button>
            <button>exportPDF</button>
        </div>
    </div>

    <!-- 표 -->
    <div class="table-wrapper">
            <div style="display: flex; justify-content: flex-end; margin-bottom: 10px;">
            </div>
        <table id="contractTable">
            <div>
            <input type="checkbox" style="margin-right: 5px" id="statusActive" onchange="filterByStatus()" checked>
                <span>✅ 창고 이용중</span>

            <input type="checkbox" style="margin-left: 5px" id="statusInactive" onchange="filterByStatus()" checked><span>  ❌ 이용 X</span>
            </div>

                <thead>
                <tr>
                    <th>고객 ID</th>
                    <th>이름</th>
                    <th>연락처</th>
                    <th>사업자 번호</th>
                    <th>가입일</th>
                    <th>
                        상태
                        <a class="sort" href="#" onclick="sortTable('prodSize', 'asc'); return false;">▲</a>
                        <a class="sort" href="#" onclick="sortTable('prodSize', 'desc'); return false;">▼</a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${List}" varStatus="status">
                    <tr data-status="${user.userStatus}">
                        <td>${user.clientId}</td>
                        <td>${user.userName}</td>
                        <c:choose>
                            <c:when test="${fn:length(user.userPhone) == 11}">
                                <td>
                                        ${fn:substring(user.userPhone, 0, 3)}-${fn:substring(user.userPhone, 3, 7)}-${fn:substring(user.userPhone, 7, 11)}
                                </td>
                            </c:when>
                            <c:when test="${fn:length(user.userPhone) == 10}">
                                <td>
                                        ${fn:substring(user.userPhone, 0, 3)}-${fn:substring(user.userPhone, 3, 6)}-${fn:substring(user.userPhone, 6, 10)}
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>${user.userPhone}</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${user.businessNumber}</td>
                        <td>
                                <fmt:formatDate value="${user.userEnterdate}" pattern="yyyy.MM.dd" />
                        </td>
                        <c:choose>
                        <c:when test="${user.userStatus == '1'}">
                            <td>✅ 창고 이용중</td>
                        </c:when>
                        <c:when test="${user.userStatus == '0'}">
                            <td>❌ 이용 X</td>
                        </c:when>
                            <c:otherwise>
                                <span>❓ 상태 미정</span>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                <c:if test="${empty List}">
                    <tr>
                        <td colspan="7">검색 결과가 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        <!-- Pagination Block -->
        <c:if test="${totalPages > 1}">
            <div class="pagination" style="margin-top: 20px; text-align: center;">
                <ul style="display: inline-flex; list-style: none; padding: 0;">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li style="margin: 0 5px;">
                            <c:url var="pageUrl" value="">
                                <c:param name="page" value="${i}" />
                                <c:param name="searchType" value="${param.searchType}" />
                                <c:param name="keyword" value="${param.keyword}" />
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
</div>

<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
    </script>
</c:if>

<script>
    function sortTable(field, direction) {
        const table = document.getElementById("contractTable");
        const tbody = table.querySelector("tbody");
        const rows = Array.from(tbody.querySelectorAll("tr"));

        const fieldIndex = {
            prodPrice: 4,
            prodSize: 6
        }[field];

        rows.sort((a, b) => {
            const valA = a.children[fieldIndex].innerText.trim();
            const valB = b.children[fieldIndex].innerText.trim();

            const numA = parseFloat(valA);
            const numB = parseFloat(valB);

            const isNumber = !isNaN(numA) && !isNaN(numB);

            let result;
            if (isNumber) {
                result = numA - numB;
            } else {
                result = valA.localeCompare(valB);
            }

            return direction === "asc" ? result : -result;
        });

        rows.forEach(row => tbody.appendChild(row)); // 재배치
    }
</script>

<script>
    function filterByStatus() {
        const showActive = document.getElementById('statusActive').checked;
        const showInactive = document.getElementById('statusInactive').checked;

        const rows = document.querySelectorAll("#contractTable tbody tr");

        rows.forEach(row => {
            const status = row.getAttribute("data-status");
            const isActive = status === "1";
            const isInactive = status === "0";

            if ((isActive && showActive) || (isInactive && showInactive)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });

        // 선택된 게 아무것도 없으면 안내 문구 추가
        const tbody = document.querySelector("#contractTable tbody");
        const visibleRows = Array.from(rows).filter(row => row.style.display !== "none");

        let noResultRow = document.getElementById("no-result-row");
        if (!showActive && !showInactive) {
            if (!noResultRow) {
                noResultRow = document.createElement("tr");
                noResultRow.id = "no-result-row";
                noResultRow.innerHTML = `<td colspan="7">조건에 맞는 검색 결과가 없습니다.</td>`;
                tbody.appendChild(noResultRow);
            }
        } else {
            if (noResultRow) {
                noResultRow.remove();
            }
        }
    }
</script>

</body>
