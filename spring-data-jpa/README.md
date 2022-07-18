# 공통 인터페이스 기능
- @SpringBootApplication 사용시 해당 패키지와 하위 패키지 인식
  - 위치 달라지면 @EnbableJpaRepoisitories 사용해야함
- JpaRepository를 상속받는 interface를 만들면, spring-data-jpa가 구현체를 만들어준다.
- @Repository 없어도 된다.
    - @Repository 는 component scan 외에도 jpa의 예외를 spring 예외로 변환해주는 기능도 있음
    
# 쿼리 메소드 기능
- 메소드명으로 쿼리 가능
- parameter가 2개가 넘어가면 다른 방법으로 푸는걸 추천
    - NamedQuery 등
- 컴파일 에러 장점

## @NamedQuery
- 거의 안쓴다.
- 이름으로 만들어 재활용
- application 로딩 시점에 오류 체크

## @Query
- application 로딩 시점에 오류 체크
- ***동적쿼리는 쿼리DSL이 가장 깔끔하고 유지보수성이 높다.***

## 반환타입
- 컬렉션은 없으면 빈 컬렉션 반환
  - list null이거나 비어있는거 체크는 apache common collectionutils 사용하자
- 단건은 없으면 null or optional, 2개 이상이면 에러
  - spring에서 jpa exception 추상화해서 에러를 던진다.
- mono, flux 쓰려면  R2DBC 사용

## 페이징
- Page, Slice
- 쿼리 최적화 위해 countQuery 쿼리 사용 가능
- page.map 으로 dto 변환하자

## 벌크성 수정 쿼리
- @Modifying과 @Query로 벌크 update 사용 가능
- 영속성 컨텍스트와 싱크 작업이 필요

## @EntityGraphh

## JPA Hint & Lock
- 영속성 컨텍스트 비교를 위한 snapshot을 안 만들 수 있음
  - @Transaction read only 하면 되는거 아닌가?

## 사용자 정의 레포지토리 구현
- QueryDsl 에서 유용하게 사용한다.
- Spring이 알아서 엮어서 해준다.
  - 기존 레포티토리 이름 + Impl
- 핵심 비즈니스 레포지토리와 화면에 맞춘 레포지토리 분리 추천

## Auditing
- createdBy는 AuditorAware 빈을 추가하고 스프링 시큐리티에서 꺼내와서 사용

## 도메인 컨버터
- 트랜잭션 없는 범위에서 시작했으므로 영속성 컨텍스트 관리 X

## 페이징과 정렬
- pageable로 정렬도 가능
- 페이징 정보 둘 이상도 가능 (접두사)

## 스프링 데이터 JPA 구현체 분석
- SimpleJpaRepository 분석하자
- save 자체가 @Transactional이 있음
- @Transacional(readonly = true) 사용하면 플러시 생략 -> 성능 향상

## 새로운 엔티티 구별 방법
- 식별자가 null이나 '0'
- Persistable

## 나머지 기능들
- 동적 쿼리 기능
  - Specifications
  - Query By Example
    - 동적쿼리는 QueryDSL 사용하자
- Projections
  - dto만 조회하고 싶을 때 사용
    - submission의 source만 가져올때?
    - JdkDynamicAopProxy
    - 프록시 등의 기술로 interface로 가짜 객체를 만듦
- 네이티브 쿼리
  - 궁극의 방법, 사용하지 않는게 좋음
  - 정렬 안 될 수 있음
  - Projections와 같이 쓰는걸 추천
