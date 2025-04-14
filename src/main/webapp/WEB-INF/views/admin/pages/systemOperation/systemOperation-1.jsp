<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </style>

<body>

<%-- 창고 선택  --%>
  <div>
      <h1>창고 레이아웃 조회</h1>
      <h4><br></h4>

      <h2>창고 선택  :
      <select>
          <option>Ware1</option>
          <option>Ware2</option>
          <option>Ware3</option>
      </select></h2>


<%-- 테이블: 창고 레이아웃 --%>
      <table style="margin-top: 20px; border-collapse: collapse; text-align: center; table-layout: fixed; width: 100%; max-width: 300px; height: 300px;">
          <colgroup>
              <col span="5" style="width: 20%;">
          </colgroup>
          <tbody>
          <tr>
              <td class="tooltip-cell" data-tooltip="A1">A1</td>
              <td class="tooltip-cell" data-tooltip="A2">A2</td>
              <td class="tooltip-cell" data-tooltip="A3">A3</td>
              <td class="tooltip-cell" data-tooltip="A4">A4</td>
              <td class="tooltip-cell" data-tooltip="A5">A5</td>
          </tr>
          <tr>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="B1">B1</td>
              <td class="tooltip-cell" data-tooltip="B2">B2</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="B3">B3</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="B4">B4</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="창고 : WARE1<br>위치 : B5<br>계약 고객: USER-1<br>고객 이름 : ㅇㅇㅇ<br>계약 기간<br>2025.1.1 ~ 12.31">B5</td>
          </tr>
          <tr>
              <td class="tooltip-cell" data-tooltip="C1">C1</td>
              <td class="tooltip-cell" data-tooltip="C2">C2</td>
              <td class="tooltip-cell" data-tooltip="C3">C3</td>
              <td class="tooltip-cell" data-tooltip="C4">C4</td>
              <td class="tooltip-cell" data-tooltip="C5">C5</td>
          </tr>
          <tr>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="D1">D1</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="D2">D2</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="D3">D3</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="D4">D4</td>
              <td style="background-color: #f5cbb3;" class="tooltip-cell" data-tooltip="D5">D5</td>
          </tr>
          <tr>
              <td class="tooltip-cell" data-tooltip="E1">E1</td>
              <td class="tooltip-cell" data-tooltip="E2">E2</td>
              <td class="tooltip-cell" data-tooltip="E3">E3</td>
              <td class="tooltip-cell" data-tooltip="E4">E4</td>
              <td class="tooltip-cell" data-tooltip="E5">E5</td>
          </tr>
          </tbody>
      </table>

<%-- 창고 정보 테이블 --%>
      <div style="margin-top: 30px;">
          <table border="1" style="width: 100%; text-align: center;">
              <thead>
                  <tr>
                      <th>창고 ID</th>
                      <th>창고 위치</th>
                      <th>클라이언트 NO</th>
                      <th>이름</th>
                      <th>사업자번호</th>
                      <th>최종 입고일</th>
                      <th>최종 출고일</th>
                      <th>계약 기간</th>
                  </tr>
              </thead>
              <tbody>
                  <tr>
                      <td>WARE1</td>
                      <td>A*1</td>
                      <td>USER-1</td>
                      <td>○○○</td>
                      <td>000-00-00000</td>
                      <td>2025.4.2</td>
                      <td>2025.3.27</td>
                      <td>2025.1.1 ~ 2025.12.31</td>
                  </tr>
                  <tr>
                      <td>WARE2</td>
                      <td>C*12</td>
                      <td>USER-2</td>
                      <td>○○○</td>
                      <td>000-00-00000</td>
                      <td>2005.4.25</td>
                      <td>2025.4.21</td>
                      <td>2025.1.1 ~ 2025.12.31</td>
                  </tr>
              </tbody>
          </table>
      </div>
  </div>
</div>


<script>
  document.querySelectorAll('.tooltip-cell').forEach(cell => {
    let tooltipBox;

    cell.addEventListener('mouseenter', (e) => {
      const tooltipText = cell.getAttribute('data-tooltip');
      tooltipBox = document.createElement('div');
      tooltipBox.innerHTML = tooltipText.replace(/\n/g, '<br>'); // allow multiline
      tooltipBox.className = 'tooltip-clone';
      Object.assign(tooltipBox.style, {
        position: 'absolute',
        backgroundColor: '#f1f5f9',
        color: '#111827',
        padding: '10px 14px',
        borderRadius: '8px',
        boxShadow: '0 2px 8px rgba(0,0,0,0.15)',
        fontSize: '13px',
        fontWeight: '400',
        zIndex: '9999',
        maxWidth: '320px',
        whiteSpace: 'pre-wrap',
        pointerEvents: 'none'
      });
      document.body.appendChild(tooltipBox);
      const rect = cell.getBoundingClientRect();
      tooltipBox.style.top = `${rect.top + window.scrollY + rect.height + 5}px`;
      tooltipBox.style.left = `${rect.left + window.scrollX}px`;
    });

    cell.addEventListener('mouseleave', () => {
      if (tooltipBox) {
        tooltipBox.remove();
        tooltipBox = null;
      }
    });
  });
</script>
</body>
</file>
