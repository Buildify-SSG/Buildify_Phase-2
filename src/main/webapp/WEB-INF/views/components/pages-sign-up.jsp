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
			width: 100%;
			max-width: 720px;
			margin: 0 auto;
			padding: 40px 30px;
			background: #fff;
			border-radius: 10px;
			box-shadow: 0 0 20px rgba(0,0,0,0.1);
		}
		.signup-container h2 {
			font-size: 24px;
			font-weight: 600;
			text-align: center;
			margin-bottom: 30px;
		}
		.signup-container .form-label {
			font-weight: 500;
		}
		.form-control {
			height: 45px;
			font-size: 14px;
		}
		.form-text {
			font-size: 12px;
			color: #777;
		}
		.btn-submit {
			height: 50px;
			font-size: 16px;
		}
		@media (max-width: 768px) {
			.signup-container {
				padding: 30px 20px;
			}
			.btn-submit {
				font-size: 15px;
				height: 45px;
			}
		}
	</style>
</head>
<body class="bg-light">

<main class="d-flex w-100 justify-content-center align-items-center" style="min-height: 100vh;">
	<div class="signup-container">
		<h2>회원가입</h2>
		<form method="post" action="/components/pages-sign-up">
			<div class="mb-3">
				<label class="form-label">ID</label>
				<input type="text" class="form-control" name="userId"
					   placeholder="아이디를 입력하세요" maxlength="12"
					   pattern="[a-zA-Z0-9]{4,12}" required>
				<div class="form-text">(영문과 숫자 조합, 4~12자)</div>
			</div>
			<div class="mb-3">
				<label class="form-label">이메일</label>
				<input type="email" class="form-control" name="userEmail"
					   placeholder="example@email.com"
					   pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
					   required>
			</div>
			<div class="mb-3">
				<label class="form-label">비밀번호</label>
				<input type="password" class="form-control" name="userPw"
					   placeholder="비밀번호를 입력하세요"
					   pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&]).{8,15}"
					   required>
				<div class="form-text">(영문, 숫자, 특수문자 포함 8~15자)</div>
			</div>
			<div class="mb-3">
				<label class="form-label">이름</label>
				<input type="text" class="form-control" name="userName"
					   placeholder="이름을 입력하세요" required>
			</div>
			<div class="mb-3">
				<label class="form-label">연락처</label>
				<input type="text" class="form-control" name="userPhone"
					   placeholder="010-0000-0000"
					   pattern="^01[0-9]-\d{3,4}-\d{4}$" required>
			</div>
			<div class="mb-3">
				<label class="form-label">주소</label>
				<input type="text" class="form-control" id="address" name="userAddress"
					   placeholder="주소를 입력하세요" required>
				<button type="button" id="btn-search-address" class="btn btn-sm btn-secondary mt-2">주소 검색</button>
			</div>
			<div class="mb-4">
				<label class="form-label">사업자등록번호</label>
				<input type="text" class="form-control" name="businessNumber"
					   placeholder="000-00-00000"
					   pattern="^\d{3}-\d{2}-\d{5}$" required>
			</div>
			<div class="d-grid">
				<button type="submit" class="btn btn-primary btn-submit">회원가입</button>
			</div>
		</form>
	</div>
</main>

<script>
	document.getElementById("btn-search-address").addEventListener("click", function () {
		new daum.Postcode({
			oncomplete: function (data) {
				document.getElementById("address").value = data.address;
			}
		}).open();
	});
</script>

<script src="../../static/js/app.js"></script>
</body>
</html>
