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
