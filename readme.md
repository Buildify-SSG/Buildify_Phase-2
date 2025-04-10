# WareFlow - BuildiFY

<br>

> 📘 **구조 설명**  
> 프로젝트 패키지 및 리소스 구조를 한눈에 정리했습니다. <br>역할별로 구분되어 있어 유지보수에 용이합니다.

## 💡 기술 스택

| 영역 | 사용 기술 |
|------|-----------|
| Language | Java 17 |
| Framework | Spring Boot, MyBatis |
| DB | MySQL |
| View | Thymeleaf |
| Cache | Redis |
| Build Tool | Gradle |
| 기타 | Lombok, Validation, Swagger |
<br>

## 📦 프로젝트 구조

```
src/main/java/com.wareflow.buildify
├── cache              # Redis 등 캐시 관련
├── common             # 공통 기능 (공통 Response, 공통 Exception 등)
├── config             # 설정 관련 (WebMvc, Swagger, Security 설정 등)
├── constant           # 공통 상수 (에러 메시지, Redis 키, URL path 등)
├── domain             # 도메인 계층 (Entity, Aggregate 등)
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
└── WareFlowBuildifyApplication.java

src/main/resources
├── application.properties          # DB 등 일반 설정
├── application-secret.properties   # 민감한 설정 (DB 비밀번호, 보안 키 등)
├── application.yml                 # 설정 파일 (MyBatis, 보안, 커스텀 프로퍼티 등)
├── static                          # 정적 파일(css, js, 이미지 등)
│   ├── css/
│   ├── fonts/
│   ├── img/
│   └── js/
├── templates                      # Thymeleaf 템플릿
│   ├── admin/                     # 관리자용 페이지
│   │   ├── common/                # header/footer/sidebar 등 공통 프래그먼트
│   │   ├── layouts/               # 공통 레이아웃
│   │   └── pages/                 # 기능별 페이지 (inbound, outbound 등)
│   ├── user/                      # 고객용 페이지
│   │   ├── common/                # header/footer/sidebar 등 공통 프래그먼트
│   │   ├── layouts/               # 공통 레이아웃
│   │   └── pages/                 # 기능별 페이지 (inbound, outbound 등)
│   ├── components/                # 컴포넌트 예시 페이지들
└───└── common/                    # 일반 공통 템플릿 및 페이지
```
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