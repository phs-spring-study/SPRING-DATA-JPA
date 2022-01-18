예제 도메인 모델
------------
- <img alt="예제도메인모델" src ="./doc/img/예제도메인모델.PNG" width ="400" height ="400"/>

공통 인터페이스 기능
------------
- JAVA Proxy 기술로 JPA Repository 구현체를 주입함.

쿼리 메소드 기능
------------
- 메소드 이름으로 쿼리 생성
- NamedQuery
- @Query - 리파지토리 메소드에 쿼리 정의
- 파라미터 바인딩
- 반환 타입
- 페이징과 정렬
- 벌크성 수정 쿼리
- @EntityGraph

JPA Named Query
----------
- 정적 쿼리라서 Application 로딩 시점에 타입 에러를 알 수 있음. 
- 나머지 이점은 별로..(뭔가 어색한 사용법)

@Query, 리포지토리 메소드에 쿼리 정의하기
----------
- <img alt="메소드에Query" src ="./doc/img/메소드에Query.PNG" width ="400" height ="400"/>
- 실행할 메서드에 정적 쿼리를 직접 작성하므로 이름 없는 Named 쿼리라 할 수 있음

특정값 혹은 DTO로 조회하기
-------------

파라미터 바인딩
-------------
- 위치 기반
  - 위치 기반을 쓰는게 좋다.
- 이름 기반
  - 쓰지말자.
- 컬렉션 바인딩
  - 실무에서 빈번히 사용 된다.

사용자 정의 리포지토리 구현
-------------
- 스프링 데이터 JPA 리포지토리는 인터페이스만 정의하고 구현체는 스프링이 자동 생성
- 스프링 데이터 JPA가 제공하는 인터페이스를 직접 구현하면 구현해야 하는 기능이 너무 많음
- 다양한 이유로 인터페이스의 메서드를 직접 구현하고 싶다면?
  - JPA 직접 사용( EntityManager )
  - 스프링 JDBC Template 사용
  - MyBatis 사용
  - 데이터베이스 커넥션 직접 사용 등등...
  - Querydsl 사용
> XXXXRepositoryImpl 관례를 사용하면 JPA가 Search 해서 구현체 메소드를 호출해줌.
> 화면용 Repository와 비즈니스 로직용 Repository를 구분하자. (MemberRepository/MemberQueryRepository)

