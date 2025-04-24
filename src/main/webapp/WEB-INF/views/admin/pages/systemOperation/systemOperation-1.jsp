<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="kakaoApiKey" value="${applicationScope.kakaoApiKey}" />
<c:set var="selectedWareId" value="${param.wareId != null ? param.wareId : 'W001'}" />

<style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8fafc;
            color: #333;
        }
        h2 {
            font-size: 20px;
            margin-bottom: 12px;
            color: #1f2937;
        }
        select {
            padding: 8px 12px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #fff;
            margin-bottom: 16px;
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
            margin-bottom: 32px;
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
        table tbody tr:hover {
            background-color: #f1f5f9;
            transition: background-color 0.2s ease;
            cursor: pointer;
        }

        .tooltip-cell {
            position: relative;
            cursor: pointer;
            overflow: visible;
            z-index: 1;
        }

        .table-info-1{
            display: flex;
            gap: 16px;
            flex: 1;
            color: white !important;

        }

        .ware-info {
            flex: 1;
            height: 300px;
            background-color: #1e293b;
            border-radius: 12px;
            padding: 24px;
            color: white !important;
            text-align: left;
            font-size: 15px;
            line-height: 1.6;
        }

        @media (max-width: 800px) {
            .table-info {
                flex-direction: column;
                align-items: stretch;
            }

            .table-info > table,
            .table-info > div {
                width: 90% !important;
                margin: 0 auto;

            }
            .table-info > div {
                flex-direction: column;
                gap: 16px;
            }

            .table-info > div > div {
                flex: 1 1 100%;
            }

            .table-info-1{
                display: table-column;
                gap: 20em;
                flex: 1;
                margin-bottom: 20px;
                color: white !important;
            }

            .ware-info{
                margin-bottom: 4em !important;
                width: 100%;
                margin: 0 auto;
                color: white !important;
            }
        }
    </style>

<body>

<%-- 창고 선택  --%>
  <div>
      <h1>창고 레이아웃 조회</h1>
      <h4><br></h4>

      <h2>창고 선택  :
      <select id="warehouseSelect" onchange="updateTable()">
          <c:forEach var="info" items="${wareInfo}">
              <option value="${info.wareId}" <c:if test="${info.wareId == selectedWareId}">selected</c:if>>${info.wareId} - ${info.wareName}</option>
          </c:forEach>
      </select></h2>


<%-- 테이블: 창고 레이아웃 --%>
      <div class="table-info" style="display: flex; align-items: flex-start; gap: 24px; margin: 40px 0;">
          <!-- 창고 좌표 테이블 -->
          <table style="border-collapse: collapse; text-align: center; table-layout: fixed; width: 20%; height: 300px;">
              <colgroup>
                  <col span="5" style="width: 20%;">
              </colgroup>
              <tbody>
                <c:forEach var="row" begin="0" end="4">
                  <tr>
                    <c:forEach var="col" begin="1" end="5">
                      <c:set var="rowLabel" value="${fn:substring('ABCDE', row, row+1)}" />
                      <c:set var="coord" value="${rowLabel}${col}" />
                      <c:set var="dtoList" value="${map[selectedWareId][coord]}" />
                      <td class="tooltip-cell"
                          style="<c:if test='${not empty dtoList}'>background-color: #facc15;</c:if>"
                          data-tooltip="<c:if test='${not empty dtoList}'>
                          창고: ${dtoList[0].wareId}
                          위치: ${coord}
                          계약 고객: ${dtoList[0].clientId}
                          고객 이름: ${dtoList[0].clientName}
                          사업자 번호: ${dtoList[0].clientBusinessNumber}
                          계약 기간: <fmt:formatDate value="${dtoList[0].startDate}" pattern="yyyy.MM.dd" /> ~ <fmt:formatDate value="${dtoList[0].endDate}" pattern="yyyy.MM.dd" />
                          최종 입고일: <fmt:formatDate value="${dtoList[0].lastInboundDate}" pattern="yyyy.MM.dd" />
                          최종 출고일: <fmt:formatDate value="${dtoList[0].lastOutboundDate}" pattern="yyyy.MM.dd" />
                            </c:if>">
                        ${coord}
                      </td>
                    </c:forEach>
                  </tr>
                </c:forEach>
              </tbody>
          </table>

          <!-- 오른쪽 박스 3개 -->
          <div class="table-info-1">
              <div class="ware-info" id="tooltipDisplay">
                  <h1> 툴 표시 공간</h1>
              </div>
              <div class="ware-info" style="color: white; text-align: center">
<%--                  <h1> 카카오 지도 표시 </h1>--%>
                  <div id="map" style="color:black; width: 100%; height: 100%;"></div>
              </div>
              <div class="ware-info" style="color: white !important; text-align: left">
                  <c:forEach var="info" items="${wareInfo}">
                      <c:if test="${info.wareId == selectedWareId}">
                          <h5 style="color:white;">창고 ID: ${info.wareId}</h5>
                          <h5 style="color:white;">창고 이름: ${info.wareName}</h5>
                          <h5 style="color:white;">주소:</h5>
                          <h5 style="color:white; margin-left: 1em;">${info.wareAddress}</h5>
                          <h5 style="color:white;">관리자: ${info.wareAdminName}</h5>
                          <h5 style="color:white;">가용 용량: ${info.wareAvailSpace}</h5>
                          <h5 style="color:white;">총 면적: ${info.wareTotalSize}</h5>
                          <h5 style="color:white;">사용률: <fmt:formatNumber value="${info.usageRate}" type="percent" maxFractionDigits="2"/></h5>
                          <h5 style="color:white;">최종 입고일: <fmt:formatDate value="${info.lastInboundDate}" pattern="yyyy.MM.dd"/></h5>
                          <h5 style="color:white;">최종 출고일: <fmt:formatDate value="${info.lastOutboundDate}" pattern="yyyy.MM.dd"/></h5>
                      </c:if>
                  </c:forEach>
              </div>
          </div>
      </div>

      <%-- 창고 정보 테이블 --%>
      <div style="margin-top: 30px;">
          <table border="1" style="width: 100%; text-align: center;">
              <thead>
              <tr>
                  <th>창고 ID</th>
                  <th>창고 주소</th>
                  <th>담당자</th>
                  <th>가용량</th>
                  <th>총면적</th>
                  <th>창고 사용율</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="wareInfo" items="${wareInfo}" varStatus="status">
                  <tr>
                      <td>${wareInfo.wareId}</td>
                      <td>${wareInfo.wareAddress}</td>
                      <td>${wareInfo.wareAdminName}</td>
                      <td>${wareInfo.wareAvailSpace}</td>
                      <td>${wareInfo.wareTotalSize}</td>
                      <td><fmt:formatNumber value="${wareInfo.usageRate}" type="percent" maxFractionDigits="2" /></td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
      </div>

  </div>


<script>
    const coordMap = {
        <c:forEach var="info" items="${wareInfo}" varStatus="status">
        "${info.wareId}": [${info.lat}, ${info.lng}]<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    };
    const nameMap = {
        <c:forEach var="info" items="${wareInfo}" varStatus="status">
        "${info.wareId}": "${info.wareName}"<c:if test="${!status.last}">,</c:if>
      </c:forEach>
    };
    const currentName = nameMap["${selectedWareId}"];
    const currentCoord = coordMap["${selectedWareId}"];
</script>

<script>
  document.querySelectorAll('.tooltip-cell').forEach(cell => {
    cell.addEventListener('mouseenter', (e) => {
      const tooltipText = cell.getAttribute('data-tooltip');
      document.getElementById("tooltipDisplay").innerHTML = tooltipText.replace(/\n/g, '<br>');
    });

    cell.addEventListener('mouseleave', () => {
      document.getElementById("tooltipDisplay").innerHTML = "<h1> 툴 표시 공간</h1>";
    });
  });
</script>

<script>
    function updateTable() {
      const selected = document.getElementById("warehouseSelect").value;
      location.href = "?wareId=" + selected;
    }
</script>

<%-- 카카오지도 스크립트 --%>
<c:set var="kakaoApiKey" value="${applicationScope.kakaoJavascriptKey}" />
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&autoload=true"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(currentCoord[0], currentCoord[1]), // 선택된 창고 좌표
            level: 5,
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };

    // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 지도 타입 변경 컨트롤을 생성한다
    var mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도에 확대 축소 컨트롤을 생성한다
    var zoomControl = new kakao.maps.ZoomControl();

    // 지도의 우측에 확대 축소 컨트롤을 추가한다
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 지도에 마커를 생성하고 표시한다
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(currentCoord[0], currentCoord[1]),
        map: map // 마커를 표시할 지도 객체
    });

    // 마커 위에 표시할 인포윈도우를 생성한다
    var infowindow = new kakao.maps.InfoWindow({
        content : '<div>창고 이름: ' + currentName + '</div>'
    });

    // 인포윈도우를 지도에 표시한다
    infowindow.open(map, marker);

    window.addEventListener('load', function () {
        setTimeout(() => {
            if (typeof kakao === 'undefined') {
                alert('카카오 지도 API 로딩 실패!');
            } else {
                console.log("✅ kakao.maps 로드 완료");
            }
        }, 1000);
    });

</script>

</body>
</file>
