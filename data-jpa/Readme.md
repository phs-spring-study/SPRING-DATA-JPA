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

Web 확장 - 도메인 클래스 컨버터
-------------
- ID를 통해 요청하지만 도메인 클래스 컨버터가 작동해 요청 데이터를 Entity 객체로 변환해준다.
  - 조회에서만 사용.. 굳이 쓸까? (영속성은 어떻게 되지? 그래서 조회만 하라는건가..)

Web 확장 - 페이징과 정렬
-------------
- 요청 파라미터
  - 예) /members?page=0&size=3&sort=id,desc&sort=username,desc
- page: 현재 페이지, 0부터 시작한다.
- size: 한 페이지에 노출할 데이터 건수
- sort: 정렬 조건을 정의한다. 예) 정렬 속성,정렬 속성...(ASC | DESC), 정렬 방향을 변경하고 싶으면 sort 파라미터 추가 ( asc 생략 가능 )

- 페이징 정보가 둘 이상이면 접두사로 구분
- @Qualifier 에 접두사명 추가 "{접두사명}_xxx”
  - 예제: /members?member_page=0&order_page=1

- Page Index를 1부터 ? (그냥 0으로 받아서 + 1하도록..)

스프링 데이터 JPA 분석
-------------
- @Repository 적용
  - Spring Bean Component 대상
  - JPA 예외를 스프링이 추상화한 예외로 변환 (하부 구현체를 교체하더라도 일관된 예외 처리 로직을 사용할 수 있다!)
- @Transactional 트랜잭션 적용
  - JPA의 모든 변경은 트랜잭션 안에서 동작
  - 스프링 데이터 JPA는 변경(등록, 수정, 삭제) 메서드를 트랜잭션 처리
  - 서비스 계층에서 트랜잭션을 시작하지 않으면 리파지토리에서 트랜잭션 시작
  - 서비스 계층에서 트랜잭션을 시작하면 리파지토리는 해당 트랜잭션을 전파 받아서 사용
  - 그래서 스프링 데이터 JPA를 사용할 때 트랜잭션이 없어도 데이터 등록, 변경이 가능했음(사실은 트랜잭션이 리포지토리 계층에 걸려있는 것임)
    - 하지만 Service에 Transactional이 없다면 영속성을 유지할 수 없기때문에 Service 로직내에서 Entity를 마음껏 사용하기 힘들다!

- @Transactional(readOnly = true)
  - 데이터를 단순히 조회만 하고 변경하지 않는 트랜잭션에서 readOnly = true 옵션을 사용하면 플러시를 생략해서 약간의 성능 향상을 얻을 수 있음

- **save() 메서드**
  - 새로운 엔티티면 저장( persist )
  - 새로운 엔티티가 아니면 병합( merge )

새로운 엔티티를 구별하는 방법
-------------
- 새로운 엔티티를 판단하는 기본 전략
  - 식별자가 객체일 때 null 로 판단
  - 식별자가 자바 기본 타입일 때 0 으로 판단
  - Persistable 인터페이스를 구현해서 판단 로직 변경 가능
  - Custom ID를 구현할 경우 Persistable 인터페이스를 구현하자(getId, isNew)