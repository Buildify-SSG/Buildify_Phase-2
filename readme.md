# WareFlow - 📦 BuildiFy - WMS 시스템 (2차 프로젝트)

<br>



## 프로젝트 개요
 작성 예정

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
 작성 예정

---

## 🛠 주요 기능
 작성 예정

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
