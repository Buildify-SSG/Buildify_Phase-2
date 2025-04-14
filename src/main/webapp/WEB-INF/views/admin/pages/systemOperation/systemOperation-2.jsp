<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
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


    </style>



    <div style="padding: 20px;">
        <%-- 계약 상태 필터 --%>
        <div class="filter-box">
            <strong>계약 상태별 보기:</strong>
            <label><input type="checkbox" id="filter-active" checked> 이용중</label>
            <label style="margin-left: 10px;"><input type="checkbox" id="filter-cancelled" checked> 해지</label>
        </div>

        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px;">
          <%-- 검색 영역 --%>
          <div class="search-bar">
            <select>
              <option value="customer">고객 ID</option>
              <option value="warehouse">계약 창고</option>
            </select>
            <input type="text" placeholder="검색" />
            <button>🔍</button>
          </div>

          <%-- export 버튼 --%>
          <div class="export-buttons">
            <button style="margin-right: 5px;">exportExcel</button>
            <button>exportPDF</button>
          </div>
        </div>



        <%-- 표 --%>
        <table id="contractTable">
            <thead>
            <tr>
                <th>고객 ID</th>
                <th>이름</th>
                <th>계약기간</th>
                <th>계약 창고</th>
                <th>계약 위치</th>
                <th>월 이용료</th>
                <th>계약 상태</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>USER-1</td>
                <td>ㅇㅇㅇ</td>
                <td>2025.1.1 ~<br>2025.12.31</td>
                <td>WARE1</td>
                <td>A*1</td>
                <td>750000</td>
                <td class="status-active">✅ 이용중</td>
            </tr>
            <tr>
                <td>USER-2</td>
                <td>ㅇㅇㅇ</td>
                <td>2025.1.1 ~<br>2025.12.31</td>
                <td>WARE2</td>
                <td>C*3</td>
                <td>1200000</td>
                <td class="status-cancelled">❌ 해지</td>
            </tr>
            <tr><td>USER-3</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*2</td><td>800000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-4</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*4</td><td>900000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-5</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*3</td><td>750000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-6</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*1</td><td>880000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-7</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*4</td><td>760000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-8</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*2</td><td>810000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-9</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*5</td><td>770000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-10</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*5</td><td>870000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-11</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*6</td><td>730000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-12</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*6</td><td>860000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-13</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*7</td><td>740000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-14</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*7</td><td>890000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-15</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*8</td><td>790000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-16</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*8</td><td>820000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-17</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*9</td><td>750000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-18</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*9</td><td>880000</td><td class="status-cancelled">❌ 해지</td></tr>
            <tr><td>USER-19</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE1</td><td>A*10</td><td>760000</td><td class="status-active">✅ 이용중</td></tr>
            <tr><td>USER-20</td><td>ㅇㅇㅇ</td><td>2025.1.1 ~<br>2025.12.31</td><td>WARE2</td><td>C*10</td><td>800000</td><td class="status-cancelled">❌ 해지</td></tr>
            </tbody>
        </table>
    </div>


</body>
