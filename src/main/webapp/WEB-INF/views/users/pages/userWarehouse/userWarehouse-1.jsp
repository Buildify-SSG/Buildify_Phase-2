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

        .submit-button {
            margin-top: 30px;
        }
    </style>
</head>
<body>

<form id="warehouseForm" method="post" action="/users/pages/userWarehouse/userWarehouse-1">
    <div class="warehouse-select">
        <label for="warehouseCode">창고 선택:</label>
        <select id="warehouseCode" name="wareId" class="form-select" style="width: 250px; padding: 8px;">
            <c:forEach var="wh" items="${wareInfo}" varStatus="status">
                <option value="${wh.wareId}" <c:if test="${status.first}">selected</c:if>>
                        ${wh.wareId} - ${wh.wareName}
                </option>
            </c:forEach>
        </select>
    </div>

    <!-- ✅ 격자형 창고 구조 -->
    <div class="grid-container" id="grid">
        <c:forEach var="row" items="${['A','B','C','D','E']}">
            <c:forEach var="col" begin="1" end="5">
                <div class="grid-item" data-coord="${row}${col}" data-x="${row}" data-y="${col}">${row}${col}</div>
            </c:forEach>
        </c:forEach>
    </div>

    <!-- ✅ 전송용 hidden 필드 (여러 좌표 선택 가능하게 배열로) -->
    <div id="hiddenFieldsContainer"></div>

    <div class="submit-button">
        <button type="submit">신청하기</button>
    </div>
</form>

<script>
    const layoutMap = JSON.parse('${mapJson}');
    const form = document.getElementById('warehouseForm');
    const hiddenContainer = document.getElementById('hiddenFieldsContainer');

    function updateGridByWarehouse(warehouseId) {
        const usedCoords = layoutMap[warehouseId] ? Object.keys(layoutMap[warehouseId]) : [];

        document.querySelectorAll(".grid-item").forEach(cell => {
            const coord = cell.dataset.coord;
            cell.classList.remove("disabled", "selected");
            cell.innerText = coord;
            cell.style.cursor = "pointer";

            if (usedCoords.includes(coord)) {
                cell.classList.add("disabled");
                cell.innerText = coord + "\n(사용중)";
                cell.style.cursor = "not-allowed";
            }
        });

        hiddenContainer.innerHTML = ''; // 이전 hidden inputs 제거
    }

    document.addEventListener("DOMContentLoaded", () => {
        const dropdown = document.querySelector("#warehouseCode");
        const gridItems = document.querySelectorAll(".grid-item");

        dropdown.addEventListener("change", e => {
            updateGridByWarehouse(e.target.value);
        });

        updateGridByWarehouse(dropdown.value); // 초기 설정

        gridItems.forEach(cell => {
            cell.addEventListener("click", () => {
                if (!cell.classList.contains("disabled")) {
                    cell.classList.toggle("selected");

                    const x = cell.dataset.x;
                    const y = cell.dataset.y;
                    const coordKey = `${x}${y}`;
                    const inputId = `coord-${coordKey}`;

                    if (cell.classList.contains("selected")) {
                        const inputX = document.createElement("input");
                        inputX.type = "hidden";
                        inputX.name = "warehousePosX";
                        inputX.value = x;
                        inputX.id = inputId + "-x";

                        const inputY = document.createElement("input");
                        inputY.type = "hidden";
                        inputY.name = "warehousePosY";
                        inputY.value = y;
                        inputY.id = inputId + "-y";

                        hiddenContainer.appendChild(inputX);
                        hiddenContainer.appendChild(inputY);
                    } else {
                        document.getElementById(inputId + "-x")?.remove();
                        document.getElementById(inputId + "-y")?.remove();
                    }
                }
            });
        });
    });
</script>

</body>
</html>
