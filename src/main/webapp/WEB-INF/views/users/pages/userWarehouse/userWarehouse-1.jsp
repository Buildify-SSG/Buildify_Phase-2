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
            background: #f8fafc;
            margin: 0; padding: 0;
            color: #333;
        }
        *, *::before, *::after { box-sizing: border-box; }

        .container-flex {
            display: grid;
            grid-template-columns: minmax(400px, 1fr) 1fr;
            gap: 1.5rem;
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }
        .left-panel, .right-panel {
            background: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        .warehouse-select {
            margin-bottom: 16px;
        }
        .warehouse-select label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }
        .warehouse-select select {
            width: 100%;
            padding: 8px;
            border: 1px solid #d1d5db;
            border-radius: 4px;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 12px;
        }
        .grid-item {
            aspect-ratio: 1;
            background: #fff;
            border: 1px solid #d1d5db;
            border-radius: 6px;
            display: flex; align-items: center; justify-content: center;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s;
            text-align: center;
        }
        .grid-item:hover { transform: scale(1.03); }
        .grid-item.disabled {
            background: #fb923c;
            color: #fff;
            cursor: not-allowed;
            opacity: 0.8;
        }
        .grid-item.selected {
            background: #facc15;
            box-shadow: 0 0 0 3px #fbbf24;
        }

        .submit-button {
            text-align: right;
            margin-top: 16px;
        }
        .btn-primary {
            background: #3b82f6;
            border: none;
            padding: 10px 20px;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-primary:disabled {
            background: #93c5fd;
            cursor: not-allowed;
        }

        #map {
            width: 100%;
            height: 100%;
            min-height: 400px;
            border-radius: 8px;
        }

        /* Modal */
        .custom-modal {
            display: none;
            position: fixed;
            inset: 0;
            background: rgba(0,0,0,0.4);
            align-items: center;
            justify-content: center;
        }
        .custom-modal-content {
            position: absolute;
            /* 세로 중앙 */
            top: 10%;
            /* 왼쪽 패널 중간보다 조금 오른쪽으로 */
            left: 0%;
            /* 자신의 크기만큼 보정 */
            transform: translate(-50%, -50%);
            /* 기존 스타일 유지 */
            background: #fff;
            /* ... */
            background: #fff;
            border-radius: 6px;
            width: 90%; max-width: 360px;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            position: relative;
        }
        .custom-modal-close {
            position: absolute;
            top: 12px; right: 12px;
            font-size: 20px;
            cursor: pointer;
        }
        .custom-modal-actions {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container-flex">
    <div class="left-panel">
        <form id="warehouseForm" method="post" action="/users/pages/userWarehouse/userWarehouse-1">
            <div class="warehouse-select">
                <label for="warehouseCode">창고 선택</label>
                <select id="warehouseCode" name="wareId">
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
                        <div class="grid-item" data-coord="${row}${col}">${row}${col}</div>
                    </c:forEach>
                </c:forEach>
            </div>
            <div id="hiddenFields"></div>
            <div class="submit-button">
                <button type="button" class="btn-primary" id="submitBtn" disabled>신청하기</button>
            </div>
        </form>
    </div>
    <div class="right-panel">
        <div id="map"></div>
    </div>
</div>

<div id="customModal" class="custom-modal">
    <div class="custom-modal-content">
        <span class="custom-modal-close" id="closeModalBtn">&times;</span>
        <h5>창고 신청 확인</h5>
        <p><strong>선택한 섹션:</strong> <span id="modal-sections"></span></p>
        <p><strong>임대 기간:</strong>
            <select id="rental-months">
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
            <button type="button" class="btn-primary" id="closeModalBtn2" style="background:#6b7280;">아니오</button>
            <button type="button" class="btn-primary" id="confirmYesBtn">예</button>
        </div>
    </div>
</div>

<c:set var="kakaoApiKey" value="${applicationScope.kakaoJavascriptKey}" />
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&autoload=true"></script>
<script>
    const layoutMap = ${mapJson};
    const gridItems = document.querySelectorAll('.grid-item');
    const dropdown = document.getElementById('warehouseCode');
    const form = document.getElementById('warehouseForm');

    let lat = parseFloat(dropdown.selectedOptions[0].dataset.lat);
    let lng = parseFloat(dropdown.selectedOptions[0].dataset.lng);
    const map = new kakao.maps.Map(document.getElementById('map'), { center: new kakao.maps.LatLng(lat, lng), level: 5 });
    const marker = new kakao.maps.Marker({ position: new kakao.maps.LatLng(lat, lng), map });
    let infowindow = new kakao.maps.InfoWindow({ content: '<div style="padding:5px;">'+ dropdown.selectedOptions[0].dataset.name +'</div>' });
    infowindow.open(map, marker);

    dropdown.addEventListener('change', e => updateGridAndMap(e.target.value));
    gridItems.forEach(cell => cell.addEventListener('click', toggleCell));

    function updateGridAndMap(wareId) {
        const used = layoutMap[wareId] ? Object.keys(layoutMap[wareId]) : [];
        gridItems.forEach(c => {
            const coord = c.dataset.coord;
            c.classList.remove('disabled','selected'); c.innerText = coord;
            if (used.includes(coord)) { c.classList.add('disabled'); c.innerText = coord+'\n(사용중)'; }
        });
        document.getElementById('hiddenFields').innerHTML = '';
        document.getElementById('submitBtn').disabled = true;
        lat = parseFloat(dropdown.selectedOptions[0].dataset.lat);
        lng = parseFloat(dropdown.selectedOptions[0].dataset.lng);
        marker.setPosition(new kakao.maps.LatLng(lat, lng)); map.setCenter(marker.getPosition());
        infowindow.setContent('<div style="padding:5px;">'+dropdown.selectedOptions[0].dataset.name+'</div>');
    }

    function toggleCell() {
        if (this.classList.contains('disabled')) return;
        const coord = this.dataset.coord;
        const hidden = document.getElementById('hiddenFields');
        if (this.classList.toggle('selected')) {
            const inp = document.createElement('input'); inp.type='hidden'; inp.name='selectedCoords'; inp.value=coord; inp.id='coord-'+coord; hidden.appendChild(inp);
        } else { document.getElementById('coord-'+coord)?.remove(); }
        document.getElementById('submitBtn').disabled = document.querySelectorAll('.grid-item.selected').length===0;
    }

    function updateModalFeeInfo() {
        const months = parseInt(document.getElementById('rental-months').value,10);
        const selected = [...document.querySelectorAll('.grid-item.selected')].map(e=>e.dataset.coord);
        const fee = selected.length*months*100;
        document.getElementById('modal-sections').innerText=selected.join(', ');
        document.getElementById('modal-fee').innerText=fee.toLocaleString()+'만원';
        let mi=document.getElementById('rentalMonths'); if(!mi){mi=document.createElement('input');mi.type='hidden';mi.name='rentalMonths';mi.id='rentalMonths';document.getElementById('hiddenFields').appendChild(mi);}mi.value=months;
    }

    document.getElementById('submitBtn').addEventListener('click',()=>{updateModalFeeInfo();document.getElementById('customModal').style.display='flex';});
    document.getElementById('closeModalBtn').addEventListener('click',()=>document.getElementById('customModal').style.display='none');
    document.getElementById('closeModalBtn2').addEventListener('click',()=>document.getElementById('customModal').style.display='none');
    window.addEventListener('click',e=>{if(e.target.id==='customModal')document.getElementById('customModal').style.display='none';});
    document.getElementById('confirmYesBtn').addEventListener('click',()=>{document.getElementById('customModal').style.display='none';form.submit();});
    document.getElementById('rental-months').addEventListener('change',updateModalFeeInfo);
    updateGridAndMap(dropdown.value);
</script>
<c:if test="${not empty msg}"><script>alert("${msg}");</script></c:if>
</body>
</html>
