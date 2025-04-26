<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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

    /* 백드롭 (modal-backdrop) */
    #modalBackdrop {
        position: fixed;
        inset: 0;                       /* top/right/bottom/left: 0 */
        background: rgba(0, 0, 0, 0.4); /* 반투명 어둡게 */
        backdrop-filter: blur(2px);     /* 살짝 흐리게 */
        z-index: 1000;
        display: none;
    }

    /* 모달 박스 컨테이너 */
    #updateModal {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 1001;
        display: none;
        width: 360px;                   /* 원하는 너비 */
    }

    /* 모달 내부 콘텐츠 */
    #updateModal .modal-content {
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    }
    #updateModal .modal-header,
    #updateModal .modal-footer {
        margin-bottom: 12px;
    }
    #updateModal .modal-header {
        font-size: 18px;
        font-weight: bold;
    }
    #updateModal .modal-footer {
        text-align: right;
    }
    #updateModal .modal-footer button {
        margin-left: 8px;
    }

    #modalBackdrop,
    #updateModal {
        display: none; /* 기본 숨김 */
    }


</style>

<body>

<div style="padding: 20px;">
    <h1>재고 조회</h1>
    <h4><br></h4>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px;">
        <!-- 검색 영역 -->
        <form method="get" id="inventorySearchForm" action="/admin/pages/inventory/inventory-1/search">
            <div class="search-bar">
                <!-- 대분류 선택 -->
                <select id="category1" name="category1">
                    <option value="" <c:if test="${param.category1 == ''}">selected</c:if>>대분류 선택</option>
                    <option value="PC"        <c:if test="${param.category1 == 'PC'}">selected</c:if>>PC</option>
                    <option value="주변기기"  <c:if test="${param.category1 == '주변기기'}">selected</c:if>>주변기기</option>
                </select>

                <!-- 중분류 선택 -->
                <select id="category2" name="category2">
                    <option value="" <c:if test="${param.category2 == ''}">selected</c:if>>중분류 선택</option>
                    <c:forEach var="mid" items="${midCategories}">
                        <option value="${mid}" <c:if test="${param.category2 == mid}">selected</c:if>>${mid}</option>
                    </c:forEach>
                </select>

                <!-- 소분류 선택 -->
                <select id="category3" name="category3">
                    <option value="" <c:if test="${param.category3 == ''}">selected</c:if>>소분류 선택</option>
                    <c:forEach var="small" items="${smallCategories}">
                        <option value="${small}" <c:if test="${param.category3 == small}">selected</c:if>>${small}</option>
                    </c:forEach>
                </select>

                <!-- 검색 유형 -->
                <select name="searchType">
                    <option value="prodId"   <c:if test="${param.searchType == 'prodId'}">selected</c:if>>상품ID</option>
                    <option value="clientId" <c:if test="${param.searchType == 'clientId'}">selected</c:if>>고객ID</option>
                    <option value="wareId"   <c:if test="${param.searchType == 'wareId'}">selected</c:if>>창고ID</option>
                </select>

                <!-- 키워드 -->
                <input type="text" name="keyword" value="${fn:escapeXml(param.keyword)}" placeholder="검색" />

                <button type="submit">🔍</button>
            </div>
        </form>


        <!-- export 버튼 -->
        <div class="export-buttons" style="display: flex; gap: 10px;">
            <form method="get" action="/admin/pages/inventory/inventory-1/api/excel" style="margin: 0;">
                <button onclick ="showAlert()">exportExcel</button>
            </form>
        </div>

    </div>

    <!-- 삭제 (AJAX 스크립트 바인딩용 id 추가, onclick 제거) -->
     <!-- AJAX로 삭제 로직 바인딩할 버튼 -->
     <button id="deleteBtn"
             type="button"
              class="btn-action btn-delete">
       <i class="fa fa-trash"></i> 삭제
     </button>

    <!-- 수정 -->
    <button id="updateBtn"
            type="button"
            class="btn-action btn-update">
        <i class="fa fa-edit"></i> 수정
    </button>



    <div id="tableContainer">
        <div class="table-wrapper">
            <table id="contractTable">
                <thead>
                <tr>
                    <th>선택</th>
                    <th>재고ID</th>
                    <th>고객ID</th>
                    <th>상품ID</th>
                    <!-- 수량 정렬링크 -->
                    <c:url var="sortAscUrl"  value="/admin/pages/inventory/inventory-1/search">
                        <c:param name="page"      value="${currentPage}" />
                        <c:param name="category1" value="${param.category1}" />
                        <c:param name="category2" value="${param.category2}" />
                        <c:param name="category3" value="${param.category3}" />
                        <c:param name="searchType" value="${param.searchType}" />
                        <c:param name="keyword"   value="${param.keyword}" />
                        <c:param name="sortBy"    value="asc" />
                    </c:url>
                    <c:url var="sortDescUrl" value="/admin/pages/inventory/inventory-1/search">
                        <c:param name="page"      value="${currentPage}" />
                        <c:param name="category1" value="${param.category1}" />
                        <c:param name="category2" value="${param.category2}" />
                        <c:param name="category3" value="${param.category3}" />
                        <c:param name="searchType" value="${param.searchType}" />
                        <c:param name="keyword"   value="${param.keyword}" />
                        <c:param name="sortBy"    value="desc" />
                    </c:url>
                    <th>
                        수량
                        <a href="${sortAscUrl}"  title="오름차순">▲</a>
                        <a href="${sortDescUrl}" title="내림차순">▼</a>
                    </th>
                    <th>창고ID</th>
                    <th>창고명</th>
                    <th>창고구역</th>
                    <th>최종입고일</th>
                    <th>최종출고일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="inventory" items="${List}" varStatus="status">
                    <tr
                            data-inventory-id="${inventory.inventoryId}"
                            data-prod-id      ="${inventory.prodId}"
                            data-ware-id      ="${inventory.wareId}"
                    >
                        <td>
                            <input type="checkbox"
                                   name="selectedIndexes"
                                   value="${inventory.inventoryId}" />
                        </td>
                        <td>${inventory.inventoryId}</td>
                        <td>${inventory.clientId}</td>
                        <td>${inventory.prodId}</td>
                        <td>${inventory.quantity}</td>
                        <td>${inventory.wareId}</td>
                        <td>${inventory.wareName}</td>
                        <td>${inventory.warePosition}</td>
                        <td><fmt:formatDate value="${inventory.lastInboundDate}"  pattern="yyyy.MM.dd" /></td>
                        <td><fmt:formatDate value="${inventory.lastOutboundDate}" pattern="yyyy.MM.dd" /></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty List}">
                    <tr>
                        <td colspan="10">검색 결과가 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <c:if test="${totalPages > 1}">
            <div class="pagination" style="margin-top: 20px; text-align: center;">
                <ul style="display: inline-flex; list-style: none; padding: 0;">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li style="margin: 0 5px;">
                            <c:url var="pageUrl" value="/admin/pages/inventory/inventory-1/search">
                                <c:param name="page"       value="${i}" />
                                <c:param name="searchType" value="${param.searchType}" />
                                <c:param name="keyword"    value="${param.keyword}" />
                                <c:param name="category1"  value="${param.category1}" />
                                <c:param name="category2"  value="${param.category2}" />
                                <c:param name="category3"  value="${param.category3}" />
                                <c:param name="sortBy"     value="${param.sortBy}" />
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





    <%--모달--%><!-- 백드롭 -->
<div id="modalBackdrop"></div>

<!-- 수정용 모달 -->
<div id="updateModal">
    <div class="modal-content">
        <div class="modal-header">
            수량 수정
            <button type="button" id="cancelBtn" style="float:right; border:none; background:none; font-size:18px; cursor:pointer;">×</button>
        </div>
        <div class="modal-body">
            <input type="hidden" id="modalInventoryId" />
            <div style="margin-bottom:8px;">
                <label>상품ID</label>
                <input type="text" id="modalProdId" class="form-control" readonly />
            </div>
            <div style="margin-bottom:8px;">
                <label>창고ID</label>
                <input type="text" id="modalWareId" class="form-control" readonly />
            </div>
            <div style="margin-bottom:8px;">
                <label>새 수량</label>
                <input type="number" id="modalNewQty" class="form-control" min="0" />
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" id="cancelBtn2" class="btn btn-secondary">취소</button>
            <button type="button" id="confirmUpdate" class="btn btn-primary">확인</button>
        </div>
    </div>
</div>


<script>
    document.addEventListener("DOMContentLoaded", () => {
        const backdrop     = document.getElementById("modalBackdrop");
        const modal        = document.getElementById("updateModal");
        const updateBtn    = document.getElementById("updateBtn");
        const cancelBtns   = document.querySelectorAll("#cancelBtn, #cancelBtn2");
        const confirmBtn   = document.getElementById("confirmUpdate");

        // 모달 열기
        updateBtn.addEventListener("click", () => {
            const checked = document.querySelectorAll('input[name="selectedIndexes"]:checked');
            if (checked.length !== 1) {
                return alert("한 개만 선택해주세요.");
            }
            const tr = checked[0].closest("tr");
            document.getElementById("modalInventoryId").value = tr.dataset.inventoryId;
            document.getElementById("modalProdId").value      = tr.dataset.prodId;
            document.getElementById("modalWareId").value      = tr.dataset.wareId;
            document.getElementById("modalNewQty").value      = "";
            backdrop.style.display = "block";
            modal.style.display    = "block";
        });

        // 모달 닫기
        cancelBtns.forEach(btn =>
            btn.addEventListener("click", () => {
                backdrop.style.display = "none";
                modal.style.display    = "none";
            })
        );

        // 확인 버튼 → AJAX 호출
        confirmBtn.addEventListener("click", () => {
            const dto = {
                inventoryId: document.getElementById("modalInventoryId").value,
                quantity:    parseInt(document.getElementById("modalNewQty").value, 10)
            };
            if (isNaN(dto.quantity) || dto.quantity < 0) {
                return alert("유효한 수량을 입력해주세요.");
            }
            fetch('/admin/pages/inventory/inventory-1/updateQuantity', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(dto)
            })
                .then(res => res.json())
                .then(json => {
                    if (json.success) {
                        alert("수정되었습니다.");
                        location.reload();
                    } else {
                        alert("오류: " + (json.message || "수정 실패"));
                    }
                })
                .catch(() => alert("서버 에러"));
        });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const deleteBtn = document.getElementById("deleteBtn");

        deleteBtn.addEventListener("click", async () => {
            // 1) 체크된 ID들 수집
            const checkedBoxes = Array.from(
                document.querySelectorAll('input[name="selectedIndexes"]:checked')
            );
            if (checkedBoxes.length === 0) {
                return alert("삭제할 항목을 하나 이상 선택해주세요.");
            }
            const ids = checkedBoxes.map(cb => cb.value);

            // 2) 확인
            if (!confirm(ids.length + '개 항목을 삭제하시겠습니까?')) {
                return;
            }

            try {
                // 3) AJAX 요청
                const resp = await fetch(
                    "/admin/pages/inventory/inventory-1/delete",
                    {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(ids)
                    }
                );
                const json = await resp.json();

                if (json.success) {
                    alert(json.deletedCount + '개 삭제되었습니다.');
                    // 4) 화면 갱신
                    location.reload();
                } else {
                    alert("삭제에 실패했습니다.");
                }
            } catch (err) {
                console.error(err);
                alert("서버 통신 중 오류가 발생했습니다.");
            }
        });
    });
</script>




<!-- 진짜 비동기 AJAX 드랍다운 JS -->
<script>
    window.addEventListener('DOMContentLoaded', () => {
        const form      = document.getElementById('inventorySearchForm');
        const c1        = document.getElementById('category1');
        const c2        = document.getElementById('category2');
        const c3        = document.getElementById('category3');

        // --- 이 함수 하나만 추가 ---
        async function refreshTable() {
            const url  = form.action + '?' + new URLSearchParams(new FormData(form));
            const res  = await fetch(url);
            const html = await res.text();
            const doc  = new DOMParser().parseFromString(html, 'text/html');

            // 1) #tableContainer 전체를 새로 교체
            const newContainer = doc.querySelector('#tableContainer');
            document.querySelector('#tableContainer').innerHTML = newContainer.innerHTML;

            // 2) (선택) 주소창에 쿼리 반영
            if (history.replaceState) {
                history.replaceState(null, '', url);
            }
            // 3) URL 업데이트 (브라우저 주소 줄에 파라미터 반영)
            history.replaceState(null, '', url);
        }

        // 대분류 변경 → 중/소 초기화 → 중분류 fetch → 테이블 리프레시
        c1.addEventListener('change', function() {
            c2.innerHTML = '<option value="">중분류 선택</option>';
            c3.innerHTML = '<option value="">소분류 선택</option>';

            if (this.value) {
                fetch('/admin/pages/inventory/inventory-1/getMidCategories?category1=' + encodeURIComponent(this.value))
                    .then(r => r.json())
                    .then(list => list.forEach(mid => {
                        const opt = document.createElement('option');
                        opt.value = mid; opt.textContent = mid;
                        c2.appendChild(opt);
                    }))
                    .catch(console.error)
                    .finally(refreshTable);
            } else {
                refreshTable();
            }
        });

        // 중분류 변경 → 소 초기화 → 소분류 fetch → 테이블 리프레시
        c2.addEventListener('change', function() {
            c3.innerHTML = '<option value="">소분류 선택</option>';

            if (this.value) {
                fetch('/admin/pages/inventory/inventory-1/getSmallCategories?category2=' + encodeURIComponent(this.value))
                    .then(r => r.json())
                    .then(list => list.forEach(sub => {
                        const opt = document.createElement('option');
                        opt.value = sub; opt.textContent = sub;
                        c3.appendChild(opt);
                    }))
                    .catch(console.error)
                    .finally(refreshTable);
            } else {
                refreshTable();
            }
        });

        // 소분류 변경 → 바로 테이블 리프레시
        c3.addEventListener('change', refreshTable);
    });
</script>


<script>
    function showAlert(){
        <c:if test="${not empty msg}">
        alert("${msg}");
        </c:if>
    }


</script>


</div>
</body>