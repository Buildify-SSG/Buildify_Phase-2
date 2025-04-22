<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>창고 신청</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .warehouse-select { margin: 20px 0; }
        .grid-container {
            display: grid;
            grid-template-columns: repeat(5, 60px);
            gap: 5px;
            width: max-content;
            margin-top: 30px;
        }
        .grid-item {
            width: 60px; height: 60px;
            background-color: #fff;
            border: 1px solid #ccc;
            display: flex; justify-content: center; align-items: center;
            font-weight: bold; cursor: pointer;
            transition: background-color 0.3s ease;
            text-align: center; white-space: pre-line;
        }
        .grid-item:hover { background-color: #f2f2f2; }
        .grid-item.disabled {
            background-color: orange;
            pointer-events: none;
            color: white;
            font-size: 0.85em;
        }
        .grid-item.selected { background-color: #f9cc00; }
        .submit-button { margin-top: 30px; }
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

    <div class="grid-container">
        <c:forEach var="row" items="${['A','B','C','D','E']}">
            <c:forEach var="col" begin="1" end="5">
                <div class="grid-item" data-coord="${row}${col}" data-x="${row}" data-y="${col}">
                        ${row}${col}
                </div>
            </c:forEach>
        </c:forEach>
    </div>

    <div id="hiddenFields"></div>

    <div class="submit-button">
        <!-- ✅ 툴팁 추가: 비활성 상태에서 설명 보임 -->
        <button type="button" class="btn btn-primary" id="submitBtn" data-bs-toggle="modal" data-bs-target="#confirmModal" disabled title="최소 하나 이상의 창고 구역을 선택해주세요">
            신청하기
        </button>
    </div>
</form>

<!-- ✅ 신청 확인 모달 -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">창고 신청 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
            </div>
            <div class="modal-body">
                선택하신 창고로 신청하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
                <button type="button" class="btn btn-primary" id="confirmYesBtn">예</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    const layoutMap = ${mapJson};
    const gridItems = document.querySelectorAll(".grid-item");
    const dropdown = document.getElementById("warehouseCode");

    function updateGridByWarehouse(warehouseId) {
        const usedCoords = layoutMap[warehouseId] ? Object.keys(layoutMap[warehouseId]) : [];

        gridItems.forEach(cell => {
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

        document.getElementById('hiddenFields').innerHTML = '';
        toggleSubmitButton();
    }

    function toggleSubmitButton() {
        const selectedCount = document.querySelectorAll(".grid-item.selected").length;
        document.getElementById("submitBtn").disabled = selectedCount === 0;
    }

    document.addEventListener("DOMContentLoaded", () => {
        if (dropdown.value) updateGridByWarehouse(dropdown.value);

        dropdown.addEventListener("change", (e) => {
            updateGridByWarehouse(e.target.value);
        });

        gridItems.forEach(cell => {
            cell.addEventListener("click", () => {
                if (!cell.classList.contains("disabled")) {
                    const coord = cell.dataset.coord;
                    const hiddenContainer = document.getElementById('hiddenFields');

                    if (cell.classList.contains("selected")) {
                        cell.classList.remove("selected");
                        const existingInput = document.getElementById("coord-" + coord);
                        if (existingInput) existingInput.remove();
                    } else {
                        cell.classList.add("selected");
                        const input = document.createElement("input");
                        input.type = "hidden";
                        input.name = "selectedCoords";
                        input.value = coord;
                        input.id = "coord-" + coord;
                        hiddenContainer.appendChild(input);
                    }

                    toggleSubmitButton();
                }
            });
        });

        const confirmBtn = document.getElementById("confirmYesBtn");
        const form = document.getElementById("warehouseForm");
        const modalEl = document.getElementById("confirmModal");

        confirmBtn.addEventListener("click", () => {
            const modal = bootstrap.Modal.getInstance(modalEl);
            modal.hide();
            form.submit();
        });
    });
</script>

<!-- ✅ alert 메시지 출력 -->
<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
    </script>
</c:if>

</body>
</html>
