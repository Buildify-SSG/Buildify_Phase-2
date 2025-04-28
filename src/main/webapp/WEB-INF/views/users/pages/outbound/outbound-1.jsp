<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <h1>출고 요청</h1>
    <h4><br></h4>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
        <!-- 검색 영역 -->
        <form method="get" action="/users/pages/outbound/outbound-1/search">
            <div class="search-bar">
                <select name="searchType">
                    <option value="prodId">상품 ID</option>
                    <%--                    <option value="brand">브랜드</option>--%>
                    <option value="prodName">상품명</option>
                    <%--                    <option value="prodCategoryId">카테고리 ID</option>--%>
                </select>
                <input type="text" name="keyword" placeholder="검색" />
                <button type="submit">🔍</button>
            </div>
        </form>

        <!-- export 버튼 -->
<%--        <div class="export-buttons">--%>

<%--            <button style="margin-right: 5px;">exportExcel</button>--%>
<%--            <button>exportPDF</button>--%>
<%--        </div>--%>
    </div>


    <!-- 표 -->
    <div class="table-wrapper">
        <div style="display: flex; justify-content: flex-end; margin-bottom: 10px;">
            <button type="button" id="requestOutboundBtn" style="background-color: #14bedc; color: white; padding: 8px 16px; border: none; border-radius: 6px; font-weight: bold; cursor: pointer;" >출고 요청</button>
        </div>
        <table id="contractTable">
            <thead>
            <tr>
                <th>선택</th>
                <th>상품ID</th>
                <th>상품명</th>
                <th>
                    가격
                    <a class="sort" href="#" onclick="sortTable('prodPrice', 'asc'); return false;">▲</a>
                    <a class="sort" href="#" onclick="sortTable('prodPrice', 'desc'); return false;">▼</a>
                </th>
                <th>사이즈</th>
                <th>수량</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="outbound" items="${List}" varStatus="status">
                <tr class="outbound-row"
                    data-prodid="${outbound.prodId}"
                    data-prodname="${outbound.prodName}"
                    data-prodprice="${outbound.prodPrice}"
                    data-prodsize="${outbound.prodSize}"
                    data-quantity="${outbound.quantity}"
                    data-inventoryid="${outbound.inventoryId}">
                    <td><input type="checkbox" class="prod-check" value="${outbound.inventoryId}" /></td>
                    <td>${outbound.prodId}</td>
                    <td>${outbound.prodName}</td>
                    <td><fmt:formatNumber value="${outbound.prodPrice}" type="number" groupingUsed="true"/></td>
                    <td>${outbound.prodSize}</td>
                    <td>${outbound.quantity}</td>
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

<!-- ✅ 모달 -->
<div id="outbound" style="display: none; position: fixed; top: 20%; left: 50%; transform: translateX(-50%); background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.2); z-index: 1000; min-width: 400px;">
    <h3 style="margin-bottom: 16px;">출고 수량 입력</h3>

    <!-- ✅ form 시작 -->
    <form id="FF">
        <table style="width: 100%; border-collapse: collapse;">
            <thead>
            <tr style="background: #f1f5f9;">
                <th style="padding: 6px;">상품 ID</th>
                <th style="padding: 6px;">상품명</th>
                <th style="padding: 6px;">가격</th>
                <th style="padding: 6px;">사이즈</th>
                <th style="padding: 6px;">창고수량</th>
                <th style="padding: 6px;">출고수량</th>
                <th style="padding: 6px;">상태</th>
            </tr>
            </thead>
            <tbody id="Inputs"></tbody>
        </table>

        <!-- ✅ 버튼도 form 안쪽에 있어야 함 -->
        <div style="margin-top: 16px; text-align: right;">
            <button type="submit" style="padding: 6px 12px; background: #14bedc; color: white; border: none; border-radius: 5px;">요청</button>
            <button type="button" onclick="closeModal()" style="margin-right: 8px; padding: 6px 12px; background: #e5e7eb; color: #333; border: none; border-radius: 5px;">취소</button>
        </div>
    </form>
    <!-- ✅ form 끝 -->
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









    document.getElementById('requestOutboundBtn').addEventListener('click', function () {
        const checked = document.querySelectorAll('.prod-check:checked');
        const Inputs = document.getElementById('Inputs');
        Inputs.innerHTML = '';

        if (checked.length === 0) {
            alert("출고 요청할 상품을 선택해주세요.");
            return;
        }

        const prodIds = Array.from(checked).map(cb => cb.value);
        const query = prodIds.map(function (id) {
            return 'inventoryIds=' + encodeURIComponent(id);
        }).join('&');

        fetch('/users/pages/outbound/outbound-1/modal-info?' + query)
            .then(function (res) {
                return res.json();
            })
            .then(function (inventorys) {
                inventorys.forEach(function (inventory) {
                    Inputs.innerHTML +=
                        '<tr>' +
                            '<td>' +
                                inventory.prodId +
                                '<input type="hidden" name="prodIds" value="' + inventory.prodId + '">' +
                                '<input type="hidden" name="inventoryIds" value="' + inventory.inventoryId + '">' +
                            '</td>' +
                            '<td>' + inventory.prodName + '</td>' +
                            '<td>' + inventory.prodPrice + '</td>' +
                            '<td>' + inventory.prodSize + '</td>' +
                            '<td>' + inventory.quantity + '</td>' +
                            '<td>' +
                                '<input type="number" name="quantities" min="1" max="' + inventory.quantity + '" step="1" required style="width: 60px;" oninput="if(this.value > ' + inventory.quantity + ') this.value=' + inventory.quantity + ';" />' +
                                '<input style="display: none;" type="hidden" name="wareIds" value="' + inventory.wareId + '">' +
                                '<input style="display: none;" type="hidden" name="warehousePosXs" value="' + inventory.warehousePosX + '">' +
                                '<input style="display: none;" type="hidden" name="warehousePosYs" value="' + inventory.warehousePosY + '">' +
                            '</td>' +
                            '<td>' + (inventory.status === 0 ? '대기' : '승인') + '</td>' +
                        '</tr>';
                });

                document.getElementById('outbound').style.display = 'block';
                // ❌ submit 이벤트는 이제 위임 방식으로 처리합니다.
            })
            .catch(function (err) {
                console.error("모달 정보 불러오기 실패:", err);
                alert("상품 정보를 불러오지 못했습니다.");
            });
    });
    // 모달 닫기 함수
    function closeModal() {
        document.getElementById('outbound').style.display = 'none';
    }

    // ✅ 이벤트 위임 방식으로 모달 form의 submit 처리
    document.addEventListener('submit', async function (e) {
        if (e.target && e.target.id === 'FF') {
            console.log("📥 submit 이벤트 발생 확인!");
            e.preventDefault();

            const form = e.target;
            const formData = new FormData(form);
            const prodIds = formData.getAll('prodIds');
            const quantities = formData.getAll('quantities').map(q => parseInt(q));
            const wareIds = formData.getAll('wareIds');
            const warehousePosXs = formData.getAll('warehousePosXs');
            const warehousePosYs = formData.getAll('warehousePosYs');
            const inventoryIds = formData.getAll('inventoryIds');

            console.log("📦 전송할 상품 ID:", prodIds);
            console.log("📦 전송할 수량:", quantities);
            console.log("📦 전송할 창고 ID:", wareIds);
            console.log("📦 전송할 창고 X좌표:", warehousePosXs);
            console.log("📦 전송할 창고 Y좌표:", warehousePosYs);
            console.log("📦 전송할 인벤토리 Id:", inventoryIds);

            try {
                const res = await fetch('/users/pages/outbound/outbound-1/request', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ prodIds, quantities, wareIds, warehousePosXs, warehousePosYs,inventoryIds })
                });

                if (res.ok) {
                    alert("출고 요청이 성공적으로 등록되었습니다.");
                    location.reload();
                } else {
                    alert("출고 요청에 실패했습니다.");
                }
            } catch (err) {
                console.error("❌ 서버 요청 중 에러:", err);
                alert("요청 중 오류가 발생했습니다.");
            }
        }
    });



</script>

</body>