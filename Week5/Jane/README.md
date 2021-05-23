## 목표

자바의 Class에 대해 학습하세요.



## 학습할 것 (필수)

- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기



##  클래스

- 객체 지향 프로그래밍에서 객체를 생성하기 위해 상태와 행동을 정의하는 일종의 설계도
- 객체들의 협력 관계를 코드로 옮기는 도구
- 협력에 참여하여 메시지를 주고 받는 객체를 만드는데 필요한 구현 메커니즘



### 클래스 작성 규칙

- 하나 이상의 문자로 이루어져야 한다.
- 첫번째 글자는 숫자가 올 수 없다.
- '$', '_'외의 특수 문자는 사용할 수 없다.
- 자바 키워드는 사용할 수 없다.



### 멤버

- 필드(Field)
  - 객체의 데이터가 저장되는 곳
  - 객체가 소멸되기 전까지 객체와 함께 존재
  - 인스턴스 변수
    - heap영역에 할당되고 gc에 의해 관리됨
  - 클래스 변수
    - static 영역에 할당되고 gc의 관리를 받지 않음

- 생성자(Constructor) 

  - 반드시 1개 이상 존재
  - 선언 생략 시 컴파일러가 기본 생성자를 바이트코드에 자동으로 추가함
  - new 연산자로 호출되는 중괄호 블록

  - 객체 생성 시 필드를 초기화하고 메서드를 호출하여 객체 사용을 준비
  - 리턴 타입이 없음
  - Heap 영역에 객체를 생성하고 객체의 주소를 반환

- 메소드(Method) : 객체의 동작에 해당하는 실행 블록
- 초기화 블록
  - 명시적 초기화에서 불가능한 초기화 수행 가능
  - **생성자보다 우선순위가 낮음** (생성자에서 다른 값으로 초기화 시 값이 변경될 수 있음)
  - 클래스 초기화 블록: 클래스 변수 초기화
    - 클래스 변수 초기화: 기본값 → 명시적 초기화 → 클래스 초기화 블록
  - 인스턴스 초기화 블록: 인스턴스 변수 초기화
       - 인스턴스 변수 초기화: 기본값 → 명시적 초기화 → 인스턴스 초기화 블록 → 생성자



### 참고

- 명시적 초기화 (변수의 선언과 동시에 초기화)
  - 변수를 선언과 동시에 값을 넣어주는 것

- transient
  -  Serialize하는 과정에 제외하고 싶은 경우 선언하는 키워드
  - 클래스 내의 보안 정보(암호, 키 등) 등에 지정

- synchronized
  - 메서드에 한 번에 한 개의 쓰레드만 접근 가능하도록 설정
- volatile
  - 변수를 메인 메모리에 저장히겠다고 명시
  - 변수의 값을 읽어올 때 CPU 캐시가 아닌 메인 메모리에서 값을 읽어옴
  - 멀티 쓰레드 환경에서 변수 값 불일치 문제를 방지할 수 있음
  - 하나의 쓰레드만 read&write가 가능하고, 나머지 쓰레는 read만 가능한 상황에서 최신값 보장 가능

- 두 개 이상의 클래스가 선언된 소스파일을 컴파일하면, 클래스를 선언한 개수만큼 바이트 코드 파일이 생긴다.
- 인스턴스화: 클래스로부터 객체를 만드는 과정
- 인스턴스: 클래스로부터 만들어진 객체



## 메서드

- 선언부와 실행 블록으로 구성
- 선언부: 리턴타입, 메소드이름, 매개변수선언



### 오버로딩

- 메서드 이름이 같고 매개변수의 수와 타입이 달라야 함
- 반환형과 매개변수 이름은 상관 X



## this

- 객체 자신
- 인스턴스(자기 자신)의 메모리 주소 저장
- 모든 인스턴스 메서드의 숨겨진 지역변수로 존재

- 클래스의 속성과 매개변수의 이름이 같거나 객체 자신의 참조값을 전달하고 싶을 때 사용

### this()

- 생성자 내부에서 오버로딩된 다른 생성자를 호출할 때 사용
- 호출하는 곳의 첫 번째 문장에서 호출되어야 함

```java
public Class Test{
       int a;
       int b;

       public void Test(){
           this(null,null);
       }

       public void Test(int a){
           this(a, null);
       }

       public void Test(int b){
           this(null, b);
       }

       public void Test(int a , int b){
            this.a = a;
            this.b = b;
       }
}
```









---

***Source***

- https://blog.naver.com/ljseokd/222171753866
- https://www.notion.so/Live-Study-5-75f857b63e524d33914a8b3ec6e1e894

- https://jeeneee.dev/java-live-study/week5-class/

- https://crushonit.tistory.com/13

- https://nesoy.github.io/articles/2018-06/Java-transient

- https://codingdog.tistory.com/entry/java-volatile-%EB%B3%80%EC%88%98%EC%9D%98-%EA%B0%80%EC%8B%9C%EC%84%B1%EA%B3%BC-%EC%B5%9C%EC%A0%81%ED%99%94
- https://nesoy.github.io/articles/2018-06/Java-volatile

- https://blog.naver.com/swoh1227/222174170682