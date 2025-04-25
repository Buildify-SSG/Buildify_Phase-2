<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>창고 신청</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <style>
        html, body {
            font-family: 'Noto Sans KR', sans-serif;
            background: linear-gradient(135deg, #f8fafc, #e2e8f0);
            color: #333;
            margin: 0; padding: 0;
            line-height: 1.5; font-size: 14px;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
        *, *::before, *::after { box-sizing: border-box; }

        .container-flex {
            display: flex; gap: 2rem;
            width: 100%; padding-top: 40px;
        }
        .left-panel { width: 700px; }
        .right-panel { flex-grow: 1; }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(5, 70px);
            gap: 10px; margin-top: 40px;
        }
        .grid-item {
            width: 70px; height: 70px;
            background: #fff; border: 1px solid #d1d5db;
            border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            display: flex; justify-content: center; align-items: center;
            font-weight: bold; cursor: pointer;
            transition: all 0.2s ease;
            text-align: center; white-space: pre-line; font-size: 15px;
        }
        .grid-item:hover { transform: scale(1.05); }
        .grid-item.disabled {
            background: #fb923c; pointer-events: none;
            color: #fff; border: none; box-shadow: none; font-size: 0.85em;
        }
        .grid-item.selected {
            background: #facc15; box-shadow: 0 0 0 3px #fbbf24;
        }

        .submit-button { margin-top: 40px; }
        #map {
            width: 100%; height: 420px;
            border-radius: 12px; margin-top: 120px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .btn-primary {
            background: #3b82f6; border-color: #3b82f6; font-weight: 600;
        }
        .btn-primary:hover {
            background: #2563eb; border-color: #2563eb;
        }

        /* 커스텀 모달 스타일 */
        .custom-modal {
            display: none; position: fixed; z-index: 9999;
            left: 0; top: 0; width: 100%; height: 100%;
            background: rgba(0,0,0,0.5);
        }
        .custom-modal-content {
            background: #fff;
            margin: 10% auto; padding: 20px;
            border-radius: 8px; width: 90%; max-width: 400px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            position: relative;
        }
        .custom-modal-close {
            position: absolute; right: 12px; top: 8px;
            font-size: 24px; font-weight: bold; cursor: pointer;
        }
        .custom-modal-actions {
            display: flex; justify-content: flex-end;
            gap: 10px; margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container-flex">
    <form id="warehouseForm" method="post" action="/users/pages/userWarehouse/userWarehouse-1" class="left-panel">
        <div class="warehouse-select mb-3">
            <label for="warehouseCode" class="form-label">창고 선택:</label>
            <select id="warehouseCode" name="wareId" class="form-select" style="width:250px;">
                <c:forEach var="wh" items="${wareInfo}" varStatus="status">
                    <option value="${wh.wareId}"
                            data-lat="${wh.lat}"
                            data-lng="${wh.lng}"
                            data-name="${wh.wareName}"
                            <c:if test="${status.first}">selected</c:if>>
                            ${wh.wareId} - ${wh.wareName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="grid-container">
            <c:forEach var="row" items="${['A','B','C','D','E']}">
                <c:forEach var="col" begin="1" end="5">
                    <div class="grid-item" data-coord="${row}${col}">
                            ${row}${col}
                    </div>
                </c:forEach>
            </c:forEach>
        </div>

        <div id="hiddenFields"></div>
        <div class="submit-button">
            <button type="button" class="btn btn-primary" id="submitBtn" disabled>
                신청하기
            </button>
        </div>
    </form>

    <div class="right-panel">
        <div id="map"></div>
    </div>
</div>

<!-- 커스텀 모달 -->
<div id="customModal" class="custom-modal">
    <div class="custom-modal-content">
        <span class="custom-modal-close" id="closeModalBtn">&times;</span>
        <h5>창고 신청 확인</h5>
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
        <div class="custom-modal-actions">
            <button type="button" class="btn btn-secondary" id="closeModalBtn2">아니오</button>
            <button type="button" class="btn btn-primary" id="confirmYesBtn">예</button>
        </div>
    </div>
</div>

<c:set var="kakaoApiKey" value="${applicationScope.kakaoJavascriptKey}" />
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&autoload=true"></script>
<script>
    const layoutMap = ${mapJson};
    const gridItems  = document.querySelectorAll('.grid-item');
    const dropdown   = document.getElementById('warehouseCode');
    const form       = document.getElementById('warehouseForm');

    // 카카오 맵 초기화
    let lat = parseFloat(dropdown.selectedOptions[0].dataset.lat);
    let lng = parseFloat(dropdown.selectedOptions[0].dataset.lng);
    const map = new kakao.maps.Map(
        document.getElementById('map'),
        { center: new kakao.maps.LatLng(lat, lng), level: 5 }
    );
    const marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(lat, lng), map
    });
    let infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">' + dropdown.selectedOptions[0].dataset.name + '</div>'
    });
    infowindow.open(map, marker);

    // 이벤트 바인딩
    dropdown.addEventListener('change', e => updateGridAndMap(e.target.value));
    gridItems.forEach(cell => cell.addEventListener('click', toggleCell));

    function updateGridAndMap(wareId) {
        const used = layoutMap[wareId] ? Object.keys(layoutMap[wareId]) : [];
        gridItems.forEach(c => {
            const coord = c.dataset.coord;
            c.classList.remove('disabled','selected');
            c.innerText = coord;
            if (used.includes(coord)) {
                c.classList.add('disabled');
                c.innerText = coord + '\n(사용중)';
            }
        });
        document.getElementById('hiddenFields').innerHTML = '';
        document.getElementById('submitBtn').disabled = true;

        // 지도 업데이트
        lat = parseFloat(dropdown.selectedOptions[0].dataset.lat);
        lng = parseFloat(dropdown.selectedOptions[0].dataset.lng);
        marker.setPosition(new kakao.maps.LatLng(lat, lng));
        map.setCenter(marker.getPosition());
        infowindow.setContent(
            '<div style="padding:5px;">' + dropdown.selectedOptions[0].dataset.name + '</div>'
        );
    }

    function toggleCell() {
        if (this.classList.contains('disabled')) return;
        const coord = this.dataset.coord;
        const hidden = document.getElementById('hiddenFields');
        if (this.classList.toggle('selected')) {
            const inp = document.createElement('input');
            inp.type  = 'hidden';
            inp.name  = 'selectedCoords';
            inp.value = coord;
            inp.id    = 'coord-' + coord;
            hidden.appendChild(inp);
        } else {
            document.getElementById('coord-' + coord)?.remove();
        }
        document.getElementById('submitBtn').disabled =
            document.querySelectorAll('.grid-item.selected').length === 0;
    }

    function updateModalFeeInfo() {
        const months = parseInt(document.getElementById('rental-months').value, 10);
        const selected = [...document.querySelectorAll('.grid-item.selected')]
            .map(e => e.dataset.coord);
        const fee = selected.length * months * 100;
        document.getElementById('modal-sections').innerText = selected.join(', ');
        document.getElementById('modal-fee').innerText      = fee.toLocaleString() + '만원';

        let mi = document.getElementById('rentalMonths');
        if (!mi) {
            mi = document.createElement('input');
            mi.type = 'hidden';
            mi.name = 'rentalMonths';
            mi.id   = 'rentalMonths';
            document.getElementById('hiddenFields').appendChild(mi);
        }
        mi.value = months;
    }

    // 모달 제어
    document.getElementById('submitBtn').addEventListener('click', () => {
        updateModalFeeInfo();
        document.getElementById('customModal').style.display = 'block';
    });
    document.getElementById('closeModalBtn').addEventListener('click', () =>
        document.getElementById('customModal').style.display = 'none'
    );
    document.getElementById('closeModalBtn2').addEventListener('click', () =>
        document.getElementById('customModal').style.display = 'none'
    );
    window.addEventListener('click', e => {
        if (e.target.id === 'customModal')
            document.getElementById('customModal').style.display = 'none';
    });
    document.getElementById('confirmYesBtn').addEventListener('click', () => {
        document.getElementById('customModal').style.display = 'none';
        form.submit();
    });

    // 개월수 변경 시 금액 즉시 갱신
    document.getElementById('rental-months')
        .addEventListener('change', updateModalFeeInfo);

    // 페이지 로드 시 초기 그리드 업데이트
    updateGridAndMap(dropdown.value);
</script>

<c:if test="${not empty msg}">
<script>alert("${msg}");</script>
</c:if>
