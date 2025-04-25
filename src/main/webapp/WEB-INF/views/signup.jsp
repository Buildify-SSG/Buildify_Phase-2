<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="../../static/css/app.css" rel="stylesheet">
	<link href="../../static/css/custom.css" rel="stylesheet">
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style>
		.signup-container {
			width: 100%; max-width: 720px;
			margin: 0 auto; padding: 40px 30px;
			background: #fff; border-radius: 10px;
			box-shadow: 0 0 20px rgba(0,0,0,0.1);
		}
		.signup-container h2 {
			font-size: 24px; font-weight: 600;
			text-align: center; margin-bottom: 30px;
		}
		.signup-container .form-label { font-weight: 500; }
		.form-control { height: 45px; font-size: 14px; }
		.form-text { font-size: 12px; color: #777; }
		.btn-submit { height: 50px; font-size: 16px; }
		.phone-input-group input,
		.phone-input-group select {
			display: inline-block; width: 30%; text-align: center;
		}
		@media (max-width: 768px) {
			.signup-container { padding: 30px 20px; }
			.btn-submit { font-size: 15px; height: 45px; }
		}
	</style>
</head>
<body class="bg-light">

<main class="d-flex w-100 justify-content-center align-items-center" style="min-height: 100vh;">
	<div class="signup-container">
		<h2>회원가입</h2>
		<form id="signupForm" method="post" action="<c:url value='/signup'/>">
			<div class="mb-3">
				<label class="form-label">ID</label>
				<input type="text" class="form-control" name="userId"
					   value="${user.userId}" placeholder="아이디를 입력하세요" maxlength="12"
					   pattern="[a-zA-Z0-9]{4,12}" required>
				<div class="form-text">(영문과 숫자 조합, 4~12자)</div>
				<c:if test="${not empty msg}">
					<div class="form-text text-danger">${msg}</div>
				</c:if>
			</div>
			<div class="mb-3">
				<label class="form-label">이메일</label>
				<input type="email" class="form-control" name="userEmail"
					   value="${user.userEmail}" placeholder="example@email.com"
					   required>
			</div>
			<div class="mb-3">
				<label class="form-label">비밀번호</label>
				<input type="password" class="form-control" name="userPw"
					   id="userPw" placeholder="비밀번호를 입력하세요"
					   pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&]).{8,15}" required>
				<div class="form-text">(영문, 숫자, 특수문자 포함 8~15자)</div>
			</div>
			<div class="mb-3">
				<label class="form-label">비밀번호 확인</label>
				<input type="password" class="form-control" name="userPwConfirm"
					   id="userPwConfirm" placeholder="비밀번호를 다시 입력하세요" required>
				<div id="pwCheckMsg" class="form-text text-danger" style="display:none;">
					❗ 비밀번호가 일치하지 않습니다.
				</div>
			</div>
			<div class="mb-3">
				<label class="form-label">이름</label>
				<input type="text" class="form-control" name="userName"
					   value="${user.userName}" placeholder="이름을 입력하세요" required>
			</div>
			<div class="mb-3">
				<label class="form-label">연락처</label>
				<div class="phone-input-group">
					<select name="userPhone1" class="form-control d-inline-block" required>
						<option value="010" ${userPhone1 == '010' ? 'selected' : ''}>010 (휴대폰)</option>
						<option value="070" ${userPhone1 == '070' ? 'selected' : ''}>070 (인터넷전화)</option>
						<option value="050" ${userPhone1 == '050' ? 'selected' : ''}>050 (안심번호)</option>
						<!-- 기타 지역번호 옵션 생략 -->
					</select>
					<input type="text" class="form-control d-inline-block" name="userPhone2" maxlength="4"
						   placeholder="0000" pattern="\d{3,4}" required value="${userPhone2}">
					<input type="text" class="form-control d-inline-block" name="userPhone3" maxlength="4"
						   placeholder="0000" pattern="\d{4}" required value="${userPhone3}">
				</div>
				<input type="hidden" name="userPhone" id="userPhone" value="${user.userPhone}">
			</div>
			<div class="mb-3">
				<label class="form-label">주소</label>
				<input type="text" class="form-control" id="userAddressSearch"
					   placeholder="주소 검색 버튼을 눌러주세요" readonly required
					   value="${userAddressSearch}">
				<button type="button" id="btn-search-address" class="btn btn-sm btn-secondary mt-2">주소 검색</button>
			</div>
			<div class="mb-3">
				<label class="form-label">상세 주소</label>
				<input type="text" class="form-control" id="userAddressDetail"
					   placeholder="상세 주소를 입력하세요" required value="${userAddressDetail}">
			</div>
			<input type="hidden" name="userAddress" id="userAddress" value="${user.userAddress}">
			<div class="mb-4">
				<label class="form-label">사업자등록번호</label>
				<input type="text" class="form-control" name="businessNumber"
					   value="${user.businessNumber}" placeholder="000-00-00000"
					   pattern="^\d{3}-\d{2}-\d{5}$" required>
				<div class="form-text">000-00-00000 형식</div>
			</div>
			<div class="d-grid">
				<button type="submit" class="btn btn-primary btn-submit">회원가입</button>
			</div>
		</form>
	</div>
</main>

<script>
	// 주소 검색 기능
	document.getElementById("btn-search-address").addEventListener("click", function () {
		new daum.Postcode({
			oncomplete: function (data) {
				document.getElementById("userAddressSearch").value = data.address;
			}
		}).open();
	});

	// 폼 제출 전 전화번호 조합 및 비밀번호 확인
	document.addEventListener("DOMContentLoaded", function() {
		const form = document.getElementById("signupForm");
		form.addEventListener("submit", function(e) {
			// 비밀번호 확인
			const pw = document.getElementById("userPw").value;
			const pwConfirm = document.getElementById("userPwConfirm").value;
			const pwCheckMsg = document.getElementById("pwCheckMsg");
			if (pw !== pwConfirm) {
				pwCheckMsg.style.display = "block";
				e.preventDefault();
				return;
			} else {
				pwCheckMsg.style.display = "none";
			}

			// 주소 합치기
			const searchAddr = document.getElementById("userAddressSearch").value;
			const detailAddr = document.getElementById("userAddressDetail").value;
			document.getElementById("userAddress").value = searchAddr + " " + detailAddr;

			// 전화번호 조합 및 hidden 덮어쓰기
			const p1 = document.querySelector('select[name="userPhone1"]').value;
			const p2 = document.querySelector('input[name="userPhone2"]').value;
			const p3 = document.querySelector('input[name="userPhone3"]').value;
			document.getElementById("userPhone").value = p1 + "-" + p2 + "-" + p3;
		});
	});
</script>
<script src="../../static/js/app.js"></script>
</body>
</html>