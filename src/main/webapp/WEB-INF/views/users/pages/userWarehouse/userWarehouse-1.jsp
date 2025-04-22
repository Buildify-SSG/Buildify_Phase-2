<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>창고 신청</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!-- 폰트 + 병합된 UI 스타일 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <style>
        html, body {
            font-family: 'Noto Sans KR', sans-serif;
            background: linear-gradient(135deg, #f8fafc, #e2e8f0); /* 부드러운 그라데이션 */
            color: #333;
            margin: 0;
            padding: 0;
            line-height: 1.5;
            font-size: 14px;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        *, *::before, *::after {
            box-sizing: border-box;
        }

        a {
            color: inherit;
            text-decoration: none;
        }

        .container-flex {
            display: flex;
            justify-content: flex-start;
            align-items: flex-start;
            gap: 2rem;
            width: 100%;
            padding-top: 40px;
            position: relative;
            z-index: 1;
        }

        .left-panel {
            width: 700px;
            flex-shrink: 0;
            transform: translateY(20px);
        }

        .right-panel {
            flex-grow: 1;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(5, 70px);
            gap: 10px;
            margin-top: 40px;
        }

        .grid-item {
            width: 70px;
            height: 70px;
            background-color: #fff;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.2s ease;
            text-align: center;
            white-space: pre-line;
            font-size: 15px;
        }

        .grid-item:hover {
            background-color: #f1f5f9;
            transform: scale(1.05);
        }

        .grid-item.disabled {
            background-color: #fb923c;
            pointer-events: none;
            color: white;
            font-size: 0.85em;
            border: none;
            box-shadow: none;
        }

        .grid-item.selected {
            background-color: #facc15;
            box-shadow: 0 0 0 3px #fbbf24;
        }

        .submit-button {
            margin-top: 40px;
        }

        #map {
            width: 100%;
            height: 420px;
            border-radius: 12px;
            margin-top: 120px;
            margin-left: -200px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .btn-primary {
            background-color: #3b82f6;
            border-color: #3b82f6;
            font-weight: 600;
        }

        .btn-primary:hover {
            background-color: #2563eb;
            border-color: #2563eb;
        }

        .modal.fade.show {
            display: flex !important;
            align-items: center;
            justify-content: center;
        }

        .modal.fade {
            transition: opacity 0.15s linear;
        }

        .modal-backdrop {
            transition: opacity 0.15s linear;
        }

        .modal-dialog {
            margin: 0 auto;
            transform: translate(0, 0) !important;
            max-width: 300px;
            width: 100%;
        }
    </style>

</head>
<body>
<div class="container-flex">
    <form id="warehouseForm" method="post" action="/users/pages/userWarehouse/userWarehouse-1" class="left-panel">
        <div class="warehouse-select">
            <label for="warehouseCode">창고 선택:</label>
            <select id="warehouseCode" name="wareId" class="form-select" style="width: 250px; padding: 8px;">
                <c:forEach var="wh" items="${wareInfo}" varStatus="status">
                    <option value="${wh.wareId}" data-lat="${wh.lat}" data-lng="${wh.lng}" data-name="${wh.wareName}" <c:if test="${status.first}">selected</c:if>>
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
            <button type="button" class="btn btn-primary" id="submitBtn" data-bs-toggle="modal" data-bs-target="#confirmModal" disabled>
                신청하기
            </button>
        </div>
    </form>

    <div class="right-panel">
        <div id="map"></div>
    </div>
</div>

<!-- 모달 -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">창고 신청 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
            </div>
            <div class="modal-body">
                <p><strong>선택한 섹션:</strong> <span id="modal-sections"></span></p>
                <p><strong>임대 기간:</strong>
                    <select id="rental-months" class="form-select" style="display:inline-block; width:auto;">
                        <option value="1" selected>1개월</option>
                        <option value="2">2개월</option>
                        <option value="3">3개월</option>
                        <option value="4">4개월</option>
                        <option value="5">5개월</option>
                        <option value="6">6개월</option>
                    </select>
                </p>
                <p><strong>총 금액:</strong> <span id="modal-fee"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
                <button type="button" class="btn btn-primary" id="confirmYesBtn">예</button>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="kakaoApiKey" value="${applicationScope.kakaoJavascriptKey}" />
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&autoload=true"></script>

<script>
    const layoutMap = ${mapJson};
    const gridItems = document.querySelectorAll(".grid-item");
    const dropdown = document.getElementById("warehouseCode");
    const mapContainer = document.getElementById("map");

    let lat = parseFloat(dropdown.options[dropdown.selectedIndex].dataset.lat);
    let lng = parseFloat(dropdown.options[dropdown.selectedIndex].dataset.lng);
    let name = dropdown.options[dropdown.selectedIndex].dataset.name;

    let map = new kakao.maps.Map(mapContainer, {
        center: new kakao.maps.LatLng(lat, lng),
        level: 5
    });
    let marker = new kakao.maps.Marker({ position: new kakao.maps.LatLng(lat, lng), map: map });
    let infowindow = new kakao.maps.InfoWindow({ content: '<div style="padding:5px;">' + name + '</div>' });
    infowindow.open(map, marker);

    dropdown.addEventListener("change", (e) => {
        updateGridByWarehouse(e.target.value);
        lat = parseFloat(e.target.options[e.target.selectedIndex].dataset.lat);
        lng = parseFloat(e.target.options[e.target.selectedIndex].dataset.lng);
        name = e.target.options[e.target.selectedIndex].dataset.name;

        const moveLatLon = new kakao.maps.LatLng(lat, lng);
        map.setCenter(moveLatLon);
        marker.setPosition(moveLatLon);
        infowindow.setContent('<div style="padding:5px;">' + name + '</div>');
        infowindow.open(map, marker);
    });

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

    // ✅ 총 금액 계산 로직 함수로 분리
    function updateModalFeeInfo() {
        // ✅ 먼저 rentalMonths 값을 얻어야 함!
        const rentalMonths = parseInt(document.getElementById("rental-months").value);

        // ✅ 숨겨진 input에 rentalMonths 값 추가/갱신
        const hiddenContainer = document.getElementById('hiddenFields');
        let existingInput = document.getElementById("rentalMonths");
        if (existingInput) {
            existingInput.value = rentalMonths;
        } else {
            const hiddenMonths = document.createElement("input");
            hiddenMonths.type = "hidden";
            hiddenMonths.name = "rentalMonths";
            hiddenMonths.id = "rentalMonths";
            hiddenMonths.value = rentalMonths;
            hiddenContainer.appendChild(hiddenMonths);
        }

        // ✅ 선택된 섹션 수 계산
        const selected = document.querySelectorAll(".grid-item.selected");
        const coordList = Array.from(selected).map(item => item.dataset.coord);
        const sectionList = coordList.join(", ");
        const sectionCount = coordList.length;

        // ✅ 금액 계산
        const unitFeePerMonth = 100; // 1개월당 100만원
        const totalFee = sectionCount * rentalMonths * unitFeePerMonth;

        // ✅ 모달에 표시
        document.getElementById("modal-sections").innerText = sectionList || "없음";
        document.getElementById("modal-fee").innerText = totalFee.toLocaleString() + "만원";
    }


    document.addEventListener("DOMContentLoaded", () => {
        if (dropdown.value) updateGridByWarehouse(dropdown.value);
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

        document.getElementById("submitBtn").addEventListener("click", updateModalFeeInfo);
        document.getElementById("rental-months").addEventListener("change", updateModalFeeInfo);
    });
</script>

<c:if test="${not empty msg}">
    <script>alert("${msg}");</script>
</c:if>
</body>
</html>
