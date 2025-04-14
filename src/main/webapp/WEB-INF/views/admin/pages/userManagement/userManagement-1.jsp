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

    </style>

<body>


    <div style="padding: 20px;">

        <h1>회원 조회</h1>
        <h4><br></h4>

        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px;">
            <!-- 검색 영역 -->
            <div class="search-bar">
                <select>
                    <option value="customer">고객 ID</option>
                    <option value="warehouse">이름</option>
                    <option value="warehouse">사업자번호</option>
                </select>
                <input type="text" placeholder="검색" />
                <button>🔍</button>
            </div>

            <!-- export 버튼 -->
            <div class="export-buttons">
                <button style="margin-right: 5px;">exportExcel</button>
                <button>exportPDF</button>
            </div>
        </div>



        <!-- 표 -->
        <div class="table-wrapper">
        <table id="contractTable">
            <thead>
            <tr>
                <th>고객ID</th>
                <th>이름</th>
                <th>연락처</th>
                <th>사업자번호</th>
                <th>등록일</th>
                <th>상태</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>USER-3</td>
                <td>ㅇㅇ</td>
                <td>010-1234-5678</td>
                <td>123-45-67890</td>
                <td>2023.12.12</td>
                <td>승인</td>
            </tr>
            <tr>
                <td>USER-4</td>
                <td>ㄴㄴ</td>
                <td>010-2345-6789</td>
                <td>234-56-78901</td>
                <td>2024.1.8</td>
                <td>미승인</td>
            </tr>
            <tr>
                <td>USER-5</td>
                <td>ㄷㄷ</td>
                <td>010-3456-7890</td>
                <td>345-67-89012</td>
                <td>2024.3.11</td>
                <td>승인</td>
            </tr>
            <tr>
                <td>USER-6</td>
                <td>ㄹㄹ</td>
                <td>010-4567-8901</td>
                <td>456-78-90123</td>
                <td>2025.1.9</td>
                <td>미승인</td>
            </tr>
            <tr>
                <td>USER-7</td>
                <td>ㅁㅁ</td>
                <td>010-5678-9012</td>
                <td>567-89-01234</td>
                <td>2023.11.23</td>
                <td>승인</td>
            </tr>
            <tr>
                <td>USER-8</td>
                <td>ㅂㅂ</td>
                <td>010-6789-0123</td>
                <td>678-90-12345</td>
                <td>2024.5.2</td>
                <td>미승인</td>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
</div>



</body>
