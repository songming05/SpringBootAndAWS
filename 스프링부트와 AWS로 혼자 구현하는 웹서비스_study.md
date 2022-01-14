# 스프링부트와 AWS로 혼자 구현하는 웹서비스_study



## 1. IntelliJ

인텔리제이에서는 하나의 프로젝트만 열린다. 이클립스의 Workspace 개념과는 사뭇 다르다. 특히나 multi-module 환경에서 효과가 좋다.

https://jojoldu.tistory.com/334



## 2. Gradle

https://docs.gradle.org/current/userguide/what_is_gradle.html

혹시 gradle 탭이 안나온다면

* build.gradle에 import gradle
* 플러그인 설치 and...



## 3. Test Code

TDD랑 단위테스트는 조금 다릅니다. TDD는

> 1. 항상 실패하는 테스트를 먼저 작성(RED)
> 2. 테스트가 통과하는 프로덕션 코드를 작성(GREEN)
> 3. 해당 프로덕션 코드를 리팩토링(Refactor)



#### assertThat(assertj) VS. assertThat(junit) 

> https://youtu.be/zLx_fI24UXM
>
> youtube - AssertJ가 JUnit의 assertThat 보다 편리한 이유 (백기선)
>
>
> https://joel-costigliola.github.io/assertj/assertj-core.html
>
> official

Matchers (junit...depend...hamcrest)

자동완성기능이 좀 약한편.. Matchers.is(T)



## 4. ORM(Object Relational Mapping) - JPA

RDB를 사용하면 CRUD는 피할 수 없다.

패러다임 불일치가 일어난다. 영원성, 일관성, 저장성이 강조된 DB 와 메시지 기반, 기능, 속성 강조의 객체지향 프로그래밍 언어가 추구하는 철학이 다르다.



* @Entity

  + 테이블과 링크될 클래스
  + 기본적으로 클래스의 CamelCase 를 under_score_case로 매칭해준다.
  + ***setter를 만들지 않는다!!***

* @Id

  + 해당 테이블의 PK 필드

* @GenerateValue

  + PK의 생성규칙

* @Column

  + 테이블의 컬럼을 나타냄
  + 선언이 없더라도 필드는 모두 컬럼에 해당
  + 문자열의 경우 VARCHAR(255)가 기본값

  

### Repository (interface Type)

DBLayer 접근자, MyBatis에서 DAO라 불리는 객체

extends JpaRepository<Entity 클래스, PK 타입> 사용 시 기본적인 CRUD 메소드 생성

🚨 Entity 와 기본 EntityRepository 는 함께 위치하도록 할 것





