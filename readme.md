# WareFlow - 📦 BuildiFy - WMS 시스템 (2차 프로젝트)

<br>


## 프로젝트 개요
 BuildiFy WMS(창고 관리 시스템)는 물류센터의 **입·출고 요청부터 재고 현황 모니터링, 계약 관리, 보고서 생성**까지   
 창고 운영 전 과정을 웹 기반으로 자동화·시각화하는 시스템입니다.  
 
주요 목적은  
- 입·출고 처리 효율화  
- 실시간 재고 정확도 확보  
- 관리자용 대시보드를 통한 의사결정 지원  
- Excel·PDF 기반 출력물 자동 생성
    
등을 통해 물류 운영 비용을 절감하고, 사용자 편의성을 극대화하는 것입니다.

## 💡 기술 스택

| 영역 | 사용 기술 |
|------|-----------|
| Language | Java 17 |
| Framework | Spring , MyBatis |
| DB | MySQL |
| View | JSP |
| Cache | Spring Singleton |
| Build Tool | Gradle |
| 기타 | Lombok , Spring Security |
<br>

## 📦 프로젝트 구조

```
src/main/java/com.wareflow.buildify
├── cache              # Redis 등 캐시 관련
├── common             # 공통 기능 (공통 Response, 공통 Exception 등)
├── config             # 설정 관련 (WebMvc, Swagger, Security 설정 등)
├── constant           # 공통 상수 (에러 메시지, Redis 키, URL path 등)
├── domain             # 도메인 계층 (Controller,Service,Mapper 등)
│   └── admin
│       └── inbound
│           └── controller
│           └── repository
│           └── service
│       └── ....
│   └── user
│       └── ....
├── dto                # 요청/응답 DTO
├── exception          # 예외 처리
├── mysql              # DB 쿼리 직접 사용하는 경우 (선택)
├── temp               # 임시 작업용
├── util               # 공통 유틸 클래스
└── vo                 # DB 통신 VO


src/main/resources
├── application-secret.properties   # 민감한 설정 (DB 비밀번호, 보안 키 등)
├── log4j2.xml                      # log4j2 설정 파일
├── config                          # 설정 파일
│   ├── mybatis-config.xml          # mabatis 설정 파일
├── mappers                        # Mapper 
│   ├── admin/                     # 관리자용 mapper
│   ├── users/                     # 고객용 mapper
└───└── auth/                      # 로그인용 mapper

src/main/webapp
├── static                          # 정적 파일(css, js, 이미지 등)
│   ├── css/
│   ├── fonts/
│   ├── img/
│   └── js/
├── WEB-INF
│   ├── root-context.xml
│   ├── servlet-context.xml
│   ├── web.xml
│   └── views
│   │   ├── admin/                    # 관리자 페이지
│   │   │      ├── layouts/           # header/footer/sidebar 등 관리자 레이아웃
│   │   │      ├── pages/             # 관리자 구현 페이지 모음
│   │   │      │      ├── inbound/
│   │   │      │      ├── outbound/
│   │   │      │      ├── ...                
│   │   ├── users/                    # 유저 페이지
│   │   │      ├── layouts/           # header/footer/sidebar 등 관리자 레이아웃
│   │   │      ├── pages/             # 유저 구현 페이지 모음
│   │   │      │      ├── inbound/
│   │   │      │      ├── outbound/
│   │   │      │      ├── ...           
└───└───└── common/
│   │   │      ├── pages/             # 관리자 구현 페이지 모음
└───└───└──────└──────└── errorpage/  # 커스텀 에러페이지 구현

```
---

## 프로젝트 실행 가이드
 1. **환경 준비**  
   - JDK 17 설치  
   - MySQL 8.x 이상 설치 및 실행  
   - Gradle 설치 (wrapper 사용 시 별도 설치 불필요)  

2. **DB 설정**  
   - `src/main/resources/application-secret.properties` 에서 DB 접속 정보 설정
     
     ```properties
     application-secret.driver=com.mysql.cj.jdbc.Driver
     application-secret.url=jdbc:mysql://localhost:3306//buildifydb?serverTimezone=Asia/Seoul
     application-secret.username=YOUR_DB_USER
     application-secret.password=YOUR_DB_PASSWORD

     ```
   - 초기 스키마 및 더미 데이터 로딩  
     ```bash
     mysql -u YOUR_DB_USER -p buildify_wms < db/schema.sql
     mysql -u YOUR_DB_USER -p buildify_wms < db/data/init_data.sql
     ```

3. **앱 실행**  
     ```bash
     cd 프로젝트_루트_디렉터리
     ./gradlew clean build
     ./gradlew bootRun
     정상 구동 시 http://localhost:8080 에 접속 가능
    
4. **캐시/뷰 리소스 적용**  
     ```
     cache 패키드의 Singleton 빈이 정상 등록되었는지 확인
	    src/main/webapp/static 내 CSS/JS 파일 변경 시 브라우저 캐시 비우기

5. **테스트 실행**  
     ```
     bash
     ./gradlew test
---

## 🛠 주요 기능
 1. 인증·인가  
	•	Spring Security 기반 로그인/로그아웃  
	•	관리자(Admin) / 사용자(User) 역할별 접근 제어  

2. 입고 관리 (Inbound)  
	•	입고 요청 등록·조회·수정  
	•	관리자 승인·반려 처리  
	•	Excel 리포트 자동 생성
	•	재고 및 입고 이력 실시간 업데이트  
   
4. 출고 관리 (Outbound)  
	•	출고 요청 등록·조회·수정·삭제  
	•	관리자 승인·반려 처리
	•	Excel 리포트 자동 생성  
	•	재고 및 출고 이력 실시간 업데이트  

6. 재고 현황  
	•	재고 현황 조회    
	•	재고 카테고리 별 조회  

7. 계약 관리  
	•	창고 임대 계약 등록·갱신  
	•	계약 기간 체크  
	•	계약별 고객 정보 관리  

8. 대시보드  
	•	관리자용 홈 화면  
	•	JavaScript 기반 차트(JSP+Chart.js)로 시각화  
	•	5분 단위 자동 리프레시  

9. 공통  
	•	글로벌 예외 처리(@ControllerAdvice) 및 커스텀 에러 페이지  
	•	Spring Singleton 캐시 활용  

10. 보안·성능  
	•	MyBatis 성능 튜닝(동적 SQL, 페이징)   
	•	트랜잭션 관리 및 롤백 보장(@Transactional)  


---

## 👥 팀원
- **김선민**
- **김성준**
- **이동휘**
- **신민혁**

---

## 🧾 커밋, PR, 이슈 컨벤션
<br>

### ✅ 커밋 메시지 규칙

```
[이모지] 타입 : 간단한 요약

- 상세 설명 1
- 상세 설명 2 (선택)
```

| 이모지 | 타입       | 설명                         |
|--------|------------|------------------------------|
| ✨     | feature    | 새로운 기능 추가             |
| 🐛     | fix        | 버그 수정                    |
| ♻️     | refactor   | 코드 리팩토링                |
| 📝     | docs       | 문서 수정 (README 등)        |
| 💄     | style      | 코드 스타일 변경 (세미콜론, 띄어쓰기 등) |
| ✅     | test       | 테스트 코드 추가/수정        |
| 🔧     | chore      | 빌드, 설정 관련              |
| 🚀     | perf       | 성능 개선                    |
| 🔥     | remove     | 코드 삭제                    |
| 🚧     | wip        | 작업 중 (Work in progress)   |
| 🗃️     | db         | DB 관련 작업 (스키마 등)     |
| 🔀     | merge      | 브랜치 병합                  |
| 🐳     | docker     | 도커 관련 작업               |
| 🔒     | security   | 보안 관련 수정               |


---

### 📦 PR 템플릿

```md
### 🔧 작업 내용
- [ ] 작업 요약

### 📌 참고 사항
- [ ] 참고할 점
```

---

### 📌 이슈 템플릿

```md
### 📌 이슈 내용 
간단한 설명

### ✅ 작업 항목
- [ ] 할 일 1
- [ ] 할 일 2

### 💬 참고
예상되는 영향이나 고민
```

📌 메서드명 네이밍 규칙 (Spring Project)
- 조회: get / find / fetch
- 등록: create / save / register / add
- 수정: update / modify
- 삭제: delete / remove
- 검증: check / validate / exists
- 처리: process / handle
→ 반환되는 타입과 목적에 따라 일관성 있게 작성

📌 예시
- UserService
  - getUserById(Long id)
  - createUser(UserDTO dto)
  - updateUser(UserDTO dto)
  - deleteUser(Long id)
