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