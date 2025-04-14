<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <style>
    .dashboard-container {
      padding: 40px;
      background: linear-gradient(to bottom right, #f0f4f8, #ffffff);
      display: flex;
      flex-direction: column;
      gap: 40px;
      min-height: 100vh;
    }
    .status-area {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      gap: 30px;
    }

    .status-left {
      display: flex;
      flex-direction: column;
      gap: 20px;
      flex: 1;
    }

    .status-row {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .status-box {
      background-color: #a9c9f3;
      color: #fff;
      padding: 16px 32px;
      border-radius: 10px;
      font-weight: 600;
      font-size: 18px;
      min-width: 160px;
      text-align: center;
    }

    .arrow-icon {
      font-size: 20px;
      color: #5a82b7;
    }

    .highlight-box {
      background-color: #d293e1;
      color: white;
      padding: 16px 32px;
      border-radius: 10px;
      font-weight: 600;
      font-size: 18px;
      min-width: 180px;
      text-align: center;
    }
    .chart-container {
      display: flex;
      gap: 30px;
      flex-wrap: wrap;
    }
    .chart-box {
      flex: 1 1 45%;
      background-color: white;
      border: 1px solid #ddd;
      border-radius: 12px;
      padding: 24px;
      box-shadow: 0 6px 12px rgba(0, 0, 0, 0.05);
    }
    .chart-box h3 {
      margin-bottom: 16px;
      color: #333;
      font-size: 18px;
      font-weight: 700;
    }

    .status-area {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      gap: 30px;
    }

    .status-flow-box {
      flex: 1 1 75%;
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .status-row {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .status-box {
      background-color: #a9c9f3;
      color: #fff;
      padding: 16px 32px;
      border-radius: 10px;
      font-weight: 600;
      font-size: 18px;
      min-width: 160px;
      text-align: center;
    }

    .arrow-icon {
      font-size: 20px;
      color: #5a82b7;
    }

    .notice-card {
      flex: 1;
      background-color: #eb5c5c;
      padding: 20px 24px;
      border-radius: 10px;
      color: white;
      font-weight: bold;
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .notice-card h3 {
      font-size: 14px;
      margin-bottom: 12px;
    }

    .notice-card ul {
      list-style-type: disc;
      padding-left: 20px;
      font-size: 12px;
    }
    .status-area {
      width: 100%;
      display: flex;
      justify-content: center;
    }

    .status-row-group {
      width: 100%;
      max-width: 1440px; /* 공지사항과 동일한 너비 */
      display: flex;
      flex-direction: column;
      gap: 20px;
      padding: 0 20px; /* 양쪽 여백 */
    }

    .status-row {
      display: flex;
      justify-content: space-between;
      align-items: center;
      gap: 10px;
      flex-wrap: wrap;
    }

    .status-area.unified-status {
      display: flex;
      justify-content: space-between;
      align-items: stretch;
      gap: 30px;
      max-width: 1440px;
      width: 100%;
      margin: 0 auto;
    }

    .status-row-group {
      flex: 3;
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .notice-card {
      flex: 1;
      background-color: #4aa6c3;
      padding: 24px 28px;
      border-radius: 10px;
      color: #ffffff;
      font-weight: bold;
      min-height: 140px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    #news-list li {
      opacity: 0;
      transform: translateY(10px);
      animation: fadeInUp 0.5s ease forwards;
    }

    @keyframes fadeInUp {
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    @media (max-width: 1024px) {
      .status-area.unified-status {
        flex-direction: column;
        align-items: stretch;
      }

      .status-row-group {
        width: 100%;
        max-width: 100%;
      }

      .notice-card {
        width: 100%;
        margin-top: 20px;
      }
    }

    @media (max-width: 480px) {
      .status-box,
      .highlight-box {
        font-size: 14px;
        padding: 12px 16px;
      }

      .arrow-icon {
        display: none;
      }

      .notice-card h3 {
        font-size: 13px;
        margin-bottom: 8px;
      }

      .notice-card ul {
        font-size: 11px;
        padding-left: 16px;
      }

      .chart-box h3 {
        font-size: 16px;
        margin-bottom: 12px;
      }

      .chart-box h1 {
        font-size: 18px;
      }
    }
    @media (max-width: 784px) {
      .arrow-icon {
        display: none !important;
      }
      .chart-container {
        flex-direction: column;
      }
    }
  </style>

<body>

  <div class="dashboard-container">

    <div class="status-area unified-status">
      <div class="status-row-group">
        <div class="status-row">
          <div class="status-box">입고요청 : 00건</div>
          <div class="arrow-icon">➜</div>
          <div class="status-box">입고승인 : 00건</div>
          <div class="arrow-icon">➜</div>
          <div class="status-box">입고완료 : 00건</div>
          <div class="highlight-box">신규가입 : 00건</div>
        </div>
        <div class="status-row">
          <div class="status-box">출고요청 : 00건</div>
          <div class="arrow-icon">➜</div>
          <div class="status-box">출고승인 : 00건</div>
          <div class="arrow-icon">➜</div>
          <div class="status-box">출고완료 : 00건</div>
          <div class="highlight-box">계약 대기 : 00건</div>
        </div>
      </div>

      <div class="notice-card">
        <h3>📢 실시간 물류 뉴스</h3>
        <ul id="news-list">
          <li><em>로딩 중...</em></li>
        </ul>
      </div>

      <script>
  let allArticles = [];
  let currentIndex = 0;

  function rotateNews() {
    const list = document.getElementById("news-list");
    list.innerHTML = "";
    const slice = allArticles.slice(currentIndex, currentIndex + 2);
    slice.forEach(article => {
      const li = document.createElement("li");
      li.innerHTML = `
        <a href="${article.url}" target="_blank" style="color: white; text-decoration: underline;">
          ${article.title}
        </a>
      `;
      list.appendChild(li);
    });
    currentIndex = (currentIndex + 2) % allArticles.length;
  }

  function loadNews() {
    fetch("https://newsapi.org/v2/everything?q=logistics&language=ko&sortBy=publishedAt&pageSize=30&apiKey=fb7e4ecc150841c1b78f9909fdad95f6")
      .then(res => res.json())
      .then(data => {
        allArticles = data.articles;
        currentIndex = 0;
        rotateNews();
      })
      .catch(err => {
        const list = document.getElementById("news-list");
        list.innerHTML = "<li>뉴스를 불러오는 데 실패했습니다.</li>";
      });
  }

  loadNews(); // 초기 30개 로딩
  setInterval(rotateNews, 5000); // 5초마다 2개씩 교체
  setInterval(loadNews, 900000); // 900초마다 전체 다시 로딩
      </script>


    </div>


    <div class="chart-container">
      <div class="chart-box">
        <h3>입고 현황</h3>
		  <br><br><br><br><br><br>
		  <h1>추후 차트 삽입</h1>
		  <br><br><br><br><br><br>
        <!-- 추후 차트 삽입 -->
      </div>
      <div class="chart-box">
        <h3>출고 현황</h3>
		  <br><br><br><br><br><br>
		  <h1>추후 차트 삽입</h1>
		  <br><br><br><br><br><br>
        <!-- 추후 차트 삽입 -->
      </div>
    </div>
  </div>
</div>

</body>