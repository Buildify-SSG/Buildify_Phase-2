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

    #modifyModal input[type="date"] {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    #btnWrap {
        width: 100px;
        margin: 1em;
        margin-left: auto;
        display: flex;
        justify-content: flex-end;
    }
    #popupBtn {
        width: 150px;
        height: 50px;
        padding: 10px 5px;
        background-color: #007bff;
        color: #fff; /* 텍스트 색상을 지정하세요 */
        border: none; /* 테두리 제거 */
        cursor: pointer;
    }
    #modalWrap {
        position: fixed; /* 화면에 고정 */
        z-index: 9999; /* bump above other elements */
        padding-top: 100px;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.7); /* 반투명한 배경색 */
        display: none; /* 초기에는 숨김 */
    }

    #modalBody {
        position: relative;
        z-index: 10000; /* ensure content sits above overlay */
        color: #333; /* default text color */
    }

    #modalBody table td,
    #modalBody table th {
        color: #333 !important;
    }

    #modalBody {
        width: 800px;
        height: auto;
        padding: 30px 30px;
        margin: 0 auto;
        border: 1px solid #777;
        background-color: #fff;
    }

    #closeBtn {
        float: right;
        font-weight: bold;
        color: #777;
        font-size: 25px;
        cursor: pointer;
    }
    /* 모달 안 테이블 input[type="date"] */
    #modalSelectedBody input[type="date"] {
        width: 130px;
        padding: 4px 6px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    /* 버튼 꾸미기 */
    #modalBody button {
        padding: 6px 12px;
        margin: 8px 4px 0 0;
        border: 1px solid #888;
        background-color: #f5f5f5;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    }

    #modalBody button:hover {
        background-color: #e1e1e1;
    }

    /* 테이블 셀 꾸미기 */
    #modalSelectedBody td {
        padding: 8px;
        text-align: center;
        vertical-align: middle;
    }

    #modalSelectedBody tr:nth-child(even) {
        background-color: #f9f9f9;
    }
</style>

<body>

<div style="padding: 20px;">

    <h1>회원 조회</h1>
    <h4><br></h4>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
        <!-- 검색 영역 -->
       <form method="post" action="/admin/pages/systemOperation/systemOperation-2/search">
            <div class="search-bar">
                <select name="searchType">
                   <option value="clientId">고객 ID</option>
                    <option value="userName">이름</option>
                    <option value="wareId">계약창고</option>
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

        <div style="display: flex; justify-content: flex-end; margin-bottom: 10px;">
            <button type="button"
                    id="popupBtn"
                    style="background-color: crimson; color: white; padding: 8px 16px; border: none; border-radius: 6px;
                           font-weight: 600; cursor: pointer; width: 90px; height: 40px;">
                수정
            </button>
        </div>

        <div id="modalWrap"> <!-- 모달 창을 감싸는 div -->
            <div id="modalBody">
                <span id="closeBtn">&times;</span> <!-- 모달을 닫는 X 버튼 -->
<%--                <p>모달창 내용!</p> <!-- 모달 창 내용 -->--%>
                <form method="post" action="/admin/pages/systemOperation/systemOperation-2/api/modify">
                <TABLE>
                    <thead>
                    <tr>
                        <th>고객 ID</th>
                        <th>이름</th>
                        <th>계약 시작일</th>
                        <th>계약 마감일</th>
                        <th>계약 창고</th>
                        <th>계약 위치</th>
                    </tr>
                    </thead>
                <tbody  id="modalSelectedBody">

                </tbody>
                </TABLE>
                    <div style="display: flex; justify-content: center; margin-top: 20px; gap: 12px;">
                        <button type="submit">수정</button>
                        <button type="button" onclick="document.getElementById('modalWrap').style.display='none'">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


        <table id="contractTable">

            <thead>
            <tr>
                <th>선택</th>
                <th>고객 ID</th>
                <th>이름</th>
                <th>계약 기간</th>
                <th>
                    남은 계약 일수
                    <a class="sort" href="#" onclick="sortTable('prodSize', 'asc'); return false;">▲</a>
                    <a class="sort" href="#" onclick="sortTable('prodSize', 'desc'); return false;">▼</a>
                </th>
                <th>계약 창고</th>
                <th>계약 위치</th>
                <th>
                    월 이용료
                    <a class="sort" href="#" onclick="sortTable('prodSize', 'asc'); return false;">▲</a>
                    <a class="sort" href="#" onclick="sortTable('prodSize', 'desc'); return false;">▼</a>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lease" items="${List}" varStatus="status">
                <tr>
<%--                    data-client-id="${lease.clientId}"--%>
<%--                    data-client-name="${lease.clientName}"--%>
<%--                    data-start-date="${lease.startDate}"--%>
<%--                    data-end-date="${lease.endDate}"--%>
<%--                    data-ware-id="${lease.wareId}"--%>
<%--                    data-ware-coord="${lease.wareCoord}">--%>
                    <td>
                        <label>
                            <input type="checkbox"
                                   name="selectedIndexes"
                                   value="${status.index}"
                                   data-client-id="${lease.clientId}"
                                   data-client-name="${lease.clientName}"
                                   data-start-date="${lease.startDate}"
                                   data-end-date="${lease.endDate}"
                                   data-ware-id="${lease.wareId}"
                                   data-ware-coord="${lease.wareCoord}" />
                        </label>
                    </td>
                    <td>${lease.clientId}</td>
                    <td>${lease.clientName}</td>
                    <td>
                        ${lease.startDate} ~ ${lease.endDate}
                    </td>
                    <td>${lease.remainDays} 일</td>
                    <td>${lease.wareId}</td>
                    <td>${lease.wareCoord}</td>
                    <td>₩ <fmt:formatNumber value="${lease.wareFee}" type="number" groupingUsed="true"/></td>
                </tr>
            </c:forEach>
            <c:if test="${empty List}">
                <tr>
                    <td colspan="7">검색 결과가 없습니다.</td>
                </tr>
            </c:if>
            </tbody>

        </table>

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
document.addEventListener('DOMContentLoaded', () => {
    const popupBtn = document.getElementById('popupBtn');
    const modalWrap = document.getElementById('modalWrap');
    const closeBtn = document.getElementById('closeBtn');
    const modalSelectedBody = document.getElementById('modalSelectedBody');
    const form = modalWrap.querySelector('form');

    popupBtn.addEventListener('click', () => {
        const checkedBoxes = document.querySelectorAll('input[name="selectedIndexes"]:checked');
        console.log('Checked boxes:', checkedBoxes.length); // 선택된 체크박스 개수 출력
        if (checkedBoxes.length === 0) {
            alert('하나 이상 선택해주세요.');
            return;
        }
        modalSelectedBody.innerHTML = '';

        // Remove previously added inputs from form except the table and button
        // But since we append inputs directly to form, remove all hidden inputs before adding new ones
        Array.from(form.querySelectorAll('input[type="hidden"], input[type="date"]')).forEach(input => {
            form.removeChild(input);
        });

        checkedBoxes.forEach(cb => {
            const clientId = cb.getAttribute('data-client-id');
            const clientName = cb.getAttribute('data-client-name');
            const startDate = cb.getAttribute('data-start-date');
            const endDate = cb.getAttribute('data-end-date');
            const wareId = cb.getAttribute('data-ware-id');
            const wareCoord = cb.getAttribute('data-ware-coord');

            console.log('Extracted data:', { clientId, clientName, startDate, endDate }); // 추출된 데이터 출력
            console.log('Extracted data:', { wareId, wareCoord });

            const tr = document.createElement('tr');
            // 고객 ID
            const cell1 = tr.insertCell();
            cell1.textContent = clientId;
            const input1 = document.createElement('input');
            input1.type = 'hidden';
            input1.name = 'clientIdList';
            input1.value = clientId;
            form.appendChild(input1);
            // 이름
            const cell2 = tr.insertCell();
            cell2.textContent = clientName;

            // 계약 시작일
            const cell3 = tr.insertCell();
            cell3.textContent = startDate;

            // 계약 마감일
            const cell4 = tr.insertCell();
            const input4 = document.createElement('input');
            input4.type = 'date';
            input4.value = endDate;
            input4.name = 'endDateList';
            cell4.appendChild(input4);

            // 계약 창고
            const cell5 = tr.insertCell();
            cell5.textContent = wareId;
            const input5 = document.createElement('input');
            input5.type = 'hidden';
            input5.name = 'wareIdList';
            input5.value = wareId;
            form.appendChild(input5);

            // 계약 위치
            const cell6 = tr.insertCell();
            cell6.textContent = wareCoord;
            const input6 = document.createElement('input');
            input6.type = 'hidden';
            input6.name = 'wareCoordList';
            input6.value = wareCoord;
            form.appendChild(input6);

            modalSelectedBody.appendChild(tr);
        });
        console.log('Showing modal');
        modalWrap.style.display = 'block';
    });

    closeBtn.addEventListener('click', () => {
        modalWrap.style.display = 'none';
    });

    modalWrap.addEventListener('click', (e) => {
            if (e.target === modalWrap) {
                modalWrap.style.display = 'none';
            }
    });
});
</script>
</div>

</body>
