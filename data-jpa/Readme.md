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

순수 JPA 페이징과 정렬
-------------


스프링 데이터 JPA 페이징과 정렬
-------------
- 오! 편하군!
- Page <-> Slice 변환도 간편!
- count 쿼리를 분리해서 JOIN이 많이 일어나는 상황에서 최적화도 가능!
- Page 인덱스 1이 아니라 0부터 시작한다!

벌크성 수정 쿼리
-------------
- @Modifying 어노테이션
- Bulk 연산은 영속성을 무시하고 동작하기 때문에 주의해서 사용해야함
  - 벌크연산 이후 em.flush() & em.clear()를 호출해서 영속성 컨텍스트를 리로드해야함!
    - JPQL 수행 전에 flush가 자동호출되므로.. flush는 생략해도 되지만.. clear는 생략할 수 없음!
  - 스프링 데이터 JPA에서는 @Modifying(clearAutomatically = true)를 활용해도 된다! 
 
EntityGraph
-------------
- 이전 강의와 겹치는 내용이 많음
  - lazy load 관련 이슈
    - N+1 문제 / fetch join
- fetch join 쿼리 짜는게 귀찮으니 @EntityGraph 어노테이션을 사용할 수 있다.
- NamedQuery와 마찬가지로 NamedEntityGraph 사용 가능


JPA Hint & Lock
-------------
- Dirty check시, 메모리에 2개의 데이터가 올라갈 수 밖에 없음(원본, 수정본)
  - QueryHint 어노테이션으로 readOnly 설정을 통해 읽기 전용 데이터 1개만 메모리에 로드 가능!
- Lock 어노테이션으로 Lcok 기능을 사용할 수 있다. (자주 사용할까 ?) 

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

JPA Auditing
-------------
- 엔티티를 생성, 변경할 때 변경한 사람과 시간을 추적하고 싶으면?
  - 등록일
  - 수정일
  - 등록자
  - 수정자