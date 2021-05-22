# 상속

- 기존 클래스를 재사용하여 새로운 클래스를 작성하는 것

  (상위클래스에서 정의한 필드와 메서드를 하위클래스도 동일하게 사용할 수 있게 물려받는 것)

- 코드의 재사용성을 높이고 코드의 중복을 제거하여 프로그램의 생산성과 유지보수에 기여한다.



### 상속의 장점

1. 적은 양의 코드로 새로운 클래스를 작성할 수 있다.
2. 공통적으로 코드를 관리하기 때문에 코드의 추가 및 변경이 용이하다.



## 자바 상속의 특징

- 자손 클래스는 조상 클래스의 모든 멤버를 상속받는다.
- 생성자와 초기화 블럭은 상속되지 않는다.
- 자손 클래스의 멤버 개수는 조상 클래스보다 항상 같거나 많다.

- 단일 상속
  - C++은 multiple inheritance가 가능하지만, java는 single inheritance만 가능하다. 
  - 다중 상속을 구현하면 여러 클래스로부터 상속받기 때문에 복합적인 기능을 가진 클래스를 쉽게 작성할 수 있지만, 선언부(이름, 매개변수)가 같고 내용은 다른 인스턴스 메서드가 어떤 클래스로부터 왔는지 추적할 수 없다. 따라서 java는 클래스 간의 관계를 명확하게 해주는 단일 상속(single inheritance)만 지원한다. 
- Object 클래스의 서브클래스이다.



### 사용 방법

```java
class Parent {}
class Child extends Parent { ... }
```

- Child class는 Parent class의 모든 멤버를 상속받기 때문에 상속을 거듭할수록 멤버 개수가 늘어난다. 
  ※ 단 생성자와 초기화 블럭은 상속되지 않는다. 
- 상속을 구현하기 위해서는 자식 클래스가 조상 클래스를 확장한다는 의미로 extends 키워드를 사용한다.



---

## super & super()
### super
자손 클래스에서 조상 클래스로부터 상속받은 멤버를 참조하는데 사용되는 참조변수
※ 모든 인스턴스 메서드에는 this와 super가 지역 변수로 존재하며, this와 super에는 각각이 속한 인스턴스 주소가 자동으로 저장된다. 

```java
class Parent {
    String x = "parent";
}

class Child extends Parent {
    String x = "child";

    void method() {
        System.out.println("x = " + x);
        System.out.println("this.x = " + this.x);
        System.out.println("super.x = " + super.x);
    }
}

// Result
// x = child
// this.x = child
// super.x = parent
```
### super()
조상의 멤버는 조상의 생성자를 통해 초기화하는 것이 바람직한데, 이 때 조상의 생성자를 호출하는데 사용하는 것이 super()이다. 
※ this(): 같은 클래스의 다른 생성자를 호출하는데 사용

```java
public class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Point3D extends Point {
    int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
}
```



---

## Inheritance vs. composition
코드 재사용에는 두 가지 방법이 있는데 하나는 inheritance이고 하나는 composition이다. 

```java
class Circle {
	int x;
    int y;
    int r;
}

class Point {
    int x;
    int y;
}
```
### Inheritance
예를 들어 위와 같이 Point 클래스가 있고 Point 클래스의 멤버 변수를 모두 포함하는 Circle 클래스가 있을 때 Point 클래스를 상속한다면 아래와 같이 표현할 수 있다.
```java
class Circle extends Point {
    int r;
}
```
### Composition
Composition은 클래스 간에 포함(composite) 관계를 맺어주는 것,
즉, 한 클래스의 멤버변수로 다른 클래스 타입의 참조 변수를 선언하는 것을 의미한다. 


```java
class Circle {
    Point c = new Point();
    int r;
}
```
위와 같이 Point 클래스를 Circle 클래스의 멤버 변수로 선언함으로써 포함 관계를 맺을 수 있다.

#### 그렇다면 상속 관계를 맺을지, 포함 관계를 맺을지는 어떻게 결정하면 좋을까?

>
> Circle **is a** Point.
> Circle **has a** Point

위의 예제에서는 원이 점이라기 보다는 원이 점을 가지고 있는 것이기 때문에 포함 관계를 맺어주는 것이 더 적합하다. 다시 말해 Inheritance는 object 사이에 is-a relationship을 갖고 있을 때 사용되며, compostion은 has-a relationship을 갖고 있을 때 사용된다. 



## 메소드 오버라이딩

## Overriding
조상 클래스로부터 상속받은 메서드의 내용을 변경하는 것 (change, modify, overwrite)

### 조건
- 메서드의 선언부(메서드 이름, 매개변수, 반환타입)가 조상 클래스의 메서드와 일치해야 한다.
- 접근 제어자와 예외는 제약 조건 하에서만 변경 가능하다.
> 1. 접근 제어자는 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.
> - public > protected > default > private
>   - 조상 클래스의 메서드 접근 제어자가 protected라면, 오버라이딩 하는 자손 클래스의 메서드는 protected나 public이어야 함.
> 2. 조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다. 

### 예시
```java
class point {
    int x;
    int y;
    
    String getLocation() {
        return "x : " + x + ", y :" + y;
    }
}

class Point3D extends Point {
    int z;

    String getLocation() {      // overriding
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}
```



## Method Dispatch

- **Method dispatch** is the algorithm used to decide which **method** should be invoked in response to a message



## Static Method Dispatch

- 구현 클래스를 통해 컴파일 시점에서 컴파일러가 어떤 메소드를 호출할지 명확하게 알고있는 경우
- e.g. 메서드 오버로딩



## Dynamic Method Dispatch

- 컴파일타임에는 알 수 없는 메서드의 의존성을 런타임에 늦게 바인딩 하는 경우

- 즉, 컴파일 타임이 아닌 런타임 때 메서드의 클래스타입이 동적으로 결정된다.
- 인터페이스, 추상클래스, 수퍼클래스 등의 메서드를 오버라이딩하면 동적 디스패치가 가능하다.



## Double Dispatch

동적 디스패치를 두 번 하는 것

### visitor pattern

![Visitor-Design-Pattern-Diagram](https://media.geeksforgeeks.org/wp-content/uploads/Visitor-Design-Pattern-Diagram.png)

- 실제 로직을 가지고 있는 객체(visitor)가 로직을 적용할 객체(element)를 방문하면서 실행하는 패턴
- 장점: 로직을 객체 구조에서 분리시켜 디자인하는 패턴 &rarr; 구조를 수정하지 않고도 새로운 동작을 추가할 수 있다(새로운 기능이 필요할 때 visitor만 추가하면 됨)
- 단점
  - 객체 구조가 변경된다면 모든 visitor를 수정해야한다.
  - 설계시 visit() 메서드의 반환타입을 알고있어야 한다.



## 추상화
- 기존 클래스의 공통적인 부분을 뽑아 조상 클래스를 만드는 것




## 추상 클래스
- 미완성 설계도
- 인스턴스 생성 불가 &rarr; 추상 클래스는 해당 클래스를 상속하고 있는 자손클래스에 의해 완성된다.
- 추상 메서드를 포함하고 있는 클래스



## 추상 메서드
- 메서드의 내용이 상속받는 클래스에 따라 달라지는 것을 고려하여 선언부만 작성하고 구현부는 작성하지 않은 메서드
- 주석을 통해 기능과 목적을 명시해주고, 괄호 대신 `;`으로 마무리한다.

```java
class Main {
    public static void main(String[] args) {
        Unit[] group = {new Marine(), new Tank(), new Dropship()};
        for (Unit x : group) {
            x.move(100, 300);
        }
    }
}

abstract class Unit {
    int x, y;

    abstract void move(int x, int y);

    void stop() {
        /* 현재 위치에 정지 */
    }
}

class Marine extends Unit {
    @Override
    void move(int x, int y) {
        System.out.printf("Marine[x=%d, y=%d]\n", x, y);
    }
}

class Tank extends Unit {
    @Override
    void move(int x, int y) {
        System.out.printf("Tank[x=%d, y=%d]\n", x, y);
    }
}

class Dropship extends Unit {
    @Override
    void move(int x, int y) {
        System.out.printf("Dropship[x=%d, y=%d]\n", x, y);
    }
}

```
![](https://images.velog.io/images/janeljs/post/ad06f356-8dea-4cc8-bd81-cdbb34d2b4cd/image.png)





## final 키워드

- 변경될 수 없다는 뜻 (Immutable/Read-only)

| 대상               | 의미                                                         |
| ------------------ | ------------------------------------------------------------ |
| 클래스             | 다른 클래스의 부모클래스로 사용할 수 없다. (subclass를 가질 수 없다, 상속x) |
| 메서드             | 오버라이딩을 통하여 재정의할 수 없다.                        |
| 멤버변수, 지역변수 | - 값을 변경할 수 없다.<br />- 객체 변수에 final을 선언하면 그 변수에 다른 참조값을 지정할 수는 없지만 객체의 속성은 변경할 수 있다. 즉 객체 자체를 immutable하게 만든느 것은 아니다.<br />- 메서드 인자에 final을 붙이면 메서드 안에서 변수값을 변경할 수 없다. |



## java.lang.Object 클래스

- 자바의 최상위 클래스
- 모든 클래스는 Object 클래스의 메서드를 사용할 수 잇음
- 필드가 없고 11개의 메서드만으로 구성

|               메소드               |                             설명                             |
| :--------------------------------: | :----------------------------------------------------------: |
|      protected Object clone()      |            해당 객체의 복제본을 생성하여 반환함.             |
|     boolean equals(Object obj)     |      해당 객체와 전달받은 객체가 같은지 여부를 반환함.       |
|     protected void finalize()      | 해당 객체를 더는 아무도 참조하지 않아 가비지 컬렉터가 객체의 리소스를 정리하기 위해 호출함. |
|        Class<T> getClass()         |              해당 객체의 클래스 타입을 반환함.               |
|           int hashCode()           |              해당 객체의 해시 코드값을 반환함.               |
|           void notify()            | 해당 객체의 대기(wait)하고 있는 하나의 스레드를 다시 실행할 때 호출함. |
|          void notifyAll()          | 해당 객체의 대기(wait)하고 있는 모든 스레드를 다시 실행할 때 호출함. |
|         String toString()          |             해당 객체의 정보를 문자열로 반환함.              |
|            void wait()             | 해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행할 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함. |
|      void wait(long timeout)       | 해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나 전달받은 시간이 지날 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함. |
| void wait(long timeout, int nanos) | 해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나 전달받은 시간이 지나거나 다른 스레드가 현재 스레드를 인터럽트(interrupt) 할 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함. |



---

***Source***

- 자바의 정석 기초편

- https://leemoono.tistory.com/20

- https://blog.naver.com/swoh1227/222181505425

- http://www.tcpschool.com/java/java_api_object

- https://github.com/mongzza/java-study/blob/main/study/6%EC%A3%BC%EC%B0%A8.md

- https://www.geeksforgeeks.org/visitor-design-pattern/

- https://blog.naver.com/sejonghumble/222183439699

- https://blog.lulab.net/programming-java/java-final-when-should-i-use-it/

- https://advenoh.tistory.com/13