<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>재고 조회</h1>
    <h4><br></h4>

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px;">
        <!-- 검색 영역 -->
        <form method="get" action="/admin/pages/inventory/inventory-1/search">
            <div class="search-bar">

                <!-- 대분류 선택 -->
                <select id="category1" name="category1">
                    <option value="">대분류 선택</option>
                    <option value="PC">PC</option>
                    <option value="주변기기">주변기기</option>
                </select>

                <!-- 중분류 선택 -->
                <select id="category2" name="category2">
                    <option value="">중분류 선택</option>
                </select>

                <!-- 소분류 선택 -->
                <select id="category3" name="category3">
                    <option value="">소분류 선택</option>
                </select>

                <select name="searchType">
                    <option value="prodId">상품ID</option>
                    <option value="clientId">고객ID</option>
                    <option value="wareId">창고ID</option>
                </select>

                <input type="text" name="keyword" placeholder="검색" />
                <button type="submit">🔍</button>
            </div>
        </form>

        <!-- Export 버튼 -->
        <div class="export-buttons">
            <button style="margin-right: 5px;">exportExcel</button>
            <button>exportPDF</button>
        </div>
    </div>

    <!-- 표 -->
    <button type="button" style="margin-right: 15px; background: crimson" onclick="confirmDelete()">Delete</button>

    <div class="table-wrapper">
        <table id="contractTable">
            <thead>
            <tr>
                <th>선택</th>
                <th>재고ID</th>
                <th>고객ID</th>
                <th>상품ID</th>
                <th>수량</th>
                <th>창고ID</th>
                <th>창고명</th>
                <th>창고구역</th>
                <th>최종입고일</th>
                <th>최종출고일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="inventory" items="${List}" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="selectedIndexes" value="${inventory.inventoryId}" /></td>
                    <td>${inventory.inventoryId}</td>
                    <td>${inventory.clientId}</td>
                    <td>${inventory.prodId}</td>
                    <td>${inventory.quantity}</td>
                    <td>${inventory.wareId}</td>
                    <td>${inventory.wareName}</td>
                    <td>${inventory.warePosition}</td>
                    <td>${inventory.last_inbound_date}</td>
                    <td>${inventory.last_outbound_date}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty List}">
                <tr>
                    <td colspan="10">검색 결과가 없습니다.</td>
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

<!-- 진짜 비동기 AJAX 드랍다운 JS -->
<script>
    window.onload = function() {
        document.getElementById("category1").addEventListener("change", function(e) {
            e.preventDefault();
            console.log("test");
            const category1 = this.value;
            const category2 = document.getElementById("category2");
            const category3 = document.getElementById("category3");

            // 중분류, 소분류 초기화
            category2.innerHTML = '<option value="">중분류 선택</option>';
            category3.innerHTML = '<option value="">소분류 선택</option>';

            if (category1) {
                fetch(`/admin/pages/inventory/inventory-1/getMidCategories?category1=`+ encodeURIComponent(category1))
                    .then(response => response.json())
                    .then(data => {
                        data.forEach(midCategory => {
                            const option = document.createElement("option");
                            option.value = midCategory;
                            option.textContent = midCategory;
                            category2.appendChild(option);
                        });
                    })
                    .catch(error => console.error('중분류 불러오기 실패:', error));
            }
        });

        document.getElementById("category2").addEventListener("change", function(e) {
            e.preventDefault();
            const category2 = this.value;
            const category3 = document.getElementById("category3");

            // 소분류 초기화
            category3.innerHTML = '<option value="">소분류 선택</option>';

            if (category2) {
                fetch(`/admin/pages/inventory/inventory-1/getSmallCategories?category2=`+encodeURIComponent(category2))
                    .then(response => response.json())
                    .then(data => {
                        data.forEach(subCategory => {
                            const option = document.createElement("option");
                            option.value = subCategory;
                            option.textContent = subCategory;
                            category3.appendChild(option);
                        });
                    })
                    .catch(error => console.error('소분류 불러오기 실패:', error));
            }
        });
    };

    function confirmDelete() {
        const confirmed = confirm('정말 삭제하시겠습니까?');
        if (confirmed) {
            document.querySelector('form[action="/admin/pages/product/product-1/api/productRemove"]').submit();
        }
    }
</script>

</body>