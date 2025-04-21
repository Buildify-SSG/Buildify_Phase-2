<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>창고 신청</title>
    <style>
        .warehouse-select {
            margin: 20px 0;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(5, 60px);
            gap: 5px;
            width: max-content;
            margin-top: 30px;
        }

        .grid-item {
            width: 60px;
            height: 60px;
            background-color: #fff;
            border: 1px solid #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-align: center;
            white-space: pre-line;
        }

        .grid-item:hover {
            background-color: #f2f2f2;
        }

        .grid-item.disabled {
            background-color: orange;
            pointer-events: none;
            color: white;
            font-size: 0.85em;
        }

        .grid-item.selected {
            background-color: #f9cc00;
        }
    </style>
</head>
<body>

<!-- ✅ 드롭다운 영역 -->
<div class="warehouse-select">
    <label for="warehouseCode">창고 선택:</label>
    <select id="warehouseCode" name="warehouseCode" class="form-select" style="width: 250px; padding: 8px;">
        <c:forEach var="wh" items="${wareInfo}" varStatus="status">
            <option value="${wh.wareId}" <c:if test="${status.first}">selected</c:if>>
                    ${wh.wareId} - ${wh.wareName}
            </option>
        </c:forEach>
    </select>
</div>

<!-- ✅ 격자형 창고 구조 -->
<div class="grid-container">
    <c:forEach var="row" items="${['A','B','C','D','E']}">
        <c:forEach var="col" begin="1" end="5">
            <div class="grid-item" data-coord="${row}${col}">${row}${col}</div>
        </c:forEach>
    </c:forEach>
</div>

<!-- ✅ 예약 좌표 정보를 담은 JSON 처리 -->
<script>
    const layoutMap = JSON.parse('${mapJson}');

    function updateGridByWarehouse(warehouseId) {
        const usedCoords = layoutMap[warehouseId] ? Object.keys(layoutMap[warehouseId]) : [];

        document.querySelectorAll(".grid-item").forEach(cell => {
            const coord = cell.dataset.coord;

            if (usedCoords.includes(coord)) {
                cell.classList.add("disabled");
                cell.innerText = coord + "\n(사용중)";
                cell.style.cursor = "not-allowed";
            } else {
                cell.classList.remove("disabled");
                cell.innerText = coord;
                cell.style.cursor = "pointer";
            }
        });
    }

    document.addEventListener("DOMContentLoaded", () => {
        const dropdown = document.querySelector("#warehouseCode");

        // ✅ 최초 로딩 시 드롭다운에서 선택된 값 사용
        const initiallySelected = dropdown.value;
        updateGridByWarehouse(initiallySelected);

        dropdown.addEventListener("change", e => {
            const selected = e.target.value;
            updateGridByWarehouse(selected);
        });
    });
</script>

</body>
</html>
