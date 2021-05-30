# Week8. 인터페이스(interface)

## 학습할 것

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스 기본 메서드(Default Method), 자바8
- 인터페이스 static 메서드, 자바8
- 인터페이스의 private 메소드, 자바9

<br><br>

## 1. 인터페이스 정의하는 방법

### 1) 인터페이스? 

인터페이스는 추상클래스보다 추상화 정도가 높은 개념. (추상 클래스의 일종)

- 멤버변수는 모두 `상수형(public static final)`으로 선언되어야 한다.

- 메서드는 모두 `추상 메서드(public abstract)`로 선언되어야 한다.

- 하지만, Java8이후 부터 `default Method`와 `static Method`가 추가 되었다.

  - default method와 static method는 `구현부`가 존재한다.

  ```java
  public interface Flyable {
    //상수(public static final)
   타입 상수명 = 값;
    
    //추상 메서드 (pulbic abstract)
    타입 메서드명(매개변수, ...);
    
    //default 메서드 (구현부가 있다!)
    default 타입 메서드명(매개변수, ..) {}
    
    //정적 메서드 (구현부가 있다!)
    static 타입 메서드명(매개변수, ...) {}
  }
  ```

  - 상수(절대적) : 인터페이스에 값을 정해줄테니 제공해주는 값만 참조하라.
  - 추상 메서드(강제적) : 가이드를 제공할테니 구현해서 사용하라.
  - 디폴드 메서드(선택적) : 메서드를 선택해서 사용해서 된다. (맘에 들지 않으면 재정의(Override)하면 된다.)
  - 스태틱 메서드(절대적) : 인터페이스에서 제공해주는 것으로 무조건 사용

<br><br>

### 2) 추상 클래스와 인터페이스의 의미

- 추상(abstract) : `is-a` 관계
  - 클래스 계층구조에서 `안정성` 제공
  - 클래스의 상속 관계에서 결합도가 높다.
  - 부모 클래스의 명세 변경 시, 코드 손상될 우려가 있다.

<br>

- 인터페이스(interface) : `has-a` 관계

  - 생성된 클래스 간 결합도가 낮다.

  - 상속에 비해 명세 변경시, 구성 요소를 쉽게 변경할 수 있다.(유연성 제공)

    

- ex) Cooper는 Person(사람)이면서 개발할 수 있다.(Developable)

  ```java
  class Cooper extends Person implements Developable {}
  ```

<br><br>

## 2. 인터페이스 구현하는 방법

- 인터페이스는 `implements` 라는 키워드를 통해서 구현한다.
- 하나의 클래스(추상 클래스)는 여러 개의 인터페이스로부터 상속을 받는 것이 가능하다. 즉, `다중 상속`이 가능하다.

```java
public interface Animal {
  void sound();
  
  public void sleep() {
    System.out.println("잠을 잔다...");
  }
}

public class Dog implements Animal {
  @Override
  public void sound() {
    System.out.println("멍멍");
  }
}

public class Cat implements Animal {
  @Override
  public void. sound() {
    System.out.println("야옹");
  }
}

public class AnimalTest {
  public static void main(String[] args) {
    Animal dog = new Dog();
    dog.sound();
    dog.sleep();
    
    //멍멍
    //잔다...
  }
}
```

<br>

### Constant Interface 지양하기!

- interface의 변수는 `static final` 을 암묵적으로 표시하고 있다.

- interface를 사용하는 목적은 규약을 정의하기 위해 입력한 것이다.

- 만약 상수를 모아두고 싶다면 `class`로 선언하자.

  ```java
  public class Constants {
    public static final int NUMBER = 100;
    public static final String NAME = "keesun";
    
    //private default constructor를 이용해서 인스턴스화 차단
    private Constants() {}
  }
  ```

  

<br><br>

## 3. 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

- 인터페이스 레퍼런스을 통해서 객체를 담을 수 있는 방식이다.
- 인터페이스 레퍼런스를 통해 구현체를 만들면 `약한 결합`이 가능하다.

  

### 강한 결합 & 약한 결합

- 의존성(dependency) : A가 변경하고자 할 때, B를 변경 여부. (true, false)

- 결합도(coupling) : A를 변경할 때, B를 변경해야하는 정도.(높다/낮다)

  - `강한 결합` : A를 변경할 때, B를 변경해야 하는 요소가 많은 형태.

    (B는 A에게 강한 의존성을 가지고 있다)

  - `약한 결합` : A를 변경할 때, B를 변경해야하는 요소가 적은 형태.

    (B는 A에게 약한 의존성을 가지고 있다)

- 강한 결합 예시

```java
class Person {
  private Chicken chicken; //chicken->pizza로 변경 시, 변경
  
  public Person() {
    this.chicken = new Chicken(); //chicken->pizza로 변경 시, 변경
  }
  
  public void eatFood() {
    chicken.eat();//chicken->pizza로 변경 시, 변경
  }
}

class Chicken {
	void eat() {
    System.out.println("eat chicken");
  }
}
```

- 약한 결합 예시

```java
interface Food {
  void eat();
}

//chicken class
class Chicken imiplements Food {
  @Override
  public void eat() {
    System.out.println("치킨를 먹는다");
  }
}

//pizza class
class Pizza implements Food {
  @Override
  public vod eat() {
    System.out.println("피자를 먹는다")
  }
}

```

```java
class Person {
  private Food food; //변경할 필요X
  
  public Person(Food food) {//변경할 필요X (파라미터만 입력하면 됨)
    this.food = food;
  }
  
  public void eatFood() {
    food.eat();//변경할 필요X
  }
}
```

<br><br>

## 4. 인터페이스 상속

- 인터페이스는 오직 `인터페이스`로 부터 ` 다중 상속(확장)`  이 가능하다.
  - 다중 상속 의미는 상위로 여러 인터페이스 받아 확장할 수 있다는 의미(extends, implements 둘다)
- 서브 인터페이스는 슈퍼 인터페이스의 메서드(abstract)까지 모두 구현해야 한다.
- 인터페이스 레퍼런스는 인터페이스를 구현한 클래스의 인스턴스를 가리킬 수 있고 해당 인터페이스에 선언된 메서드만 호출할 수 있다.

```java
public interface Movable {
    void move();
}


public interface Sleepable {
    void sleep();
}


public interface Animal extends Movable, Sleepable {
}


public class Dog implements Animal {

    @Override
    public void move() {
        System.out.println("네발로 움직임");
    }

    @Override
    public void sleep() {
        System.out.println("꿀잠zzzzZ");
    }
}
```



<br><br>

## 5. 인터페이스 기본 메서드(Default Method), 자바8

### 1) 인터페이스 기본 메서드가 나온 배경?

- 인터페이스는 메서드 선언이 아니라 `구현부(body)`를 제공하는 방법

- 과거 인터페이스에 기본 메서드가 제공되지 않았을 때,

  <img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/8c813d95-413f-428a-b4cd-1aa7783b55cc/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210530%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210530T005536Z&X-Amz-Expires=86400&X-Amz-Signature=2508dd0c531c3e3b4dba2dd30c29b30d04aa1b1a786f98d128786a3617c56c46&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" width="500" height="300">

- 중간에 추상 클래스를 만들어서, 추상 클래스를 확장하는 구현체에서는 필요한 메서드만 구현할 수 있도록 하였다.

  <img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2b3f4fc1-c8a0-4e72-b94a-b708592021c2/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210530%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210530T005505Z&X-Amz-Expires=86400&X-Amz-Signature=f384df1b988aa0603956ce9988711e6ea3f6392cbb9bb9ea2ad81b343813e80c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" width="500" height="300">

- 기본메서드가 도입된 이후, 별도의 추상 클래스가 필요없이도 구현이 가능하게 되었다.

  - 과거 방식에서는 추상 클래스를 상속받기 때문에 `상속에 자유롭지 못했다.`
  - 각 구현체는 interface를 구현한 것임으로 `상속에 유리`하다.

- 예시 : HandlerInterceptor(In Spring)

  ```java
  public interface HandlerInterceptor {
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
  			throws Exception {
      
  		return true;
  	}
  
    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
  			@Nullable ModelAndView modelAndView) throws Exception {
  	}
    
    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
  			@Nullable Exception ex) throws Exception {
  	}
  }
  ```

<br>

### 2) default method가 중복된 경우

```java
public interface JoinGroup {
  default void preJoin() {
    System.out.println();
  }
}

public interface joinMember {
  default void preJoin() {
    System.out.println();
  }
  
  default void afterJoin() {
    System.out.println("after join group");
  } 
}

public class HelloJoinMember implements JoinMember, JoinGroup {
  @Override
  public void preJoin() {
    joinMember.super.preJoin(); //상위 메서드 선택
    joinGroup.super.preJoin();
  }
}
```

<br>

### 3) 시그니처 중복일 경우

```java
interface Dancable {
	void fly() ;
}

interface Flyable {
  default void fly() {}
}

interface Child extends Dancable,Flyable {
  // 시그니처 중복이기 때문에 추가적으로 Overriding이 필요하다.
    @Override
    default void fly() {
        Flyable.super.fly();
    }  
}
```

<br><br>

## 6. 인터페이스 static 메서드, 자바8

- 인스턴스 없이 `인스턴스 타입`으로만 메서드 선언이 가능한 메서드.

- 형태

  ```java
  static 타입 메서드명(매개변수, ...) {} 
  ```

  <br>

- 예제1. static과 default의 메서드명이 같은 경우?

  ```java
  interface grandFatherI {
    static void methodCall () {
      System.out.println("static method!!");
    }
  }
  
  interface fatherI extends grandFatherI {
    default void methodCall() {
      System.out.println("defualt method!!");
    }
  }
  
  class childC implement fatherI {
  }
  
  
  class Test {
    public static void main(String[] args) {
      new childC().methodCall(); // default method!!
      childC.methodCall(); // static method!!
    }
  }
  ```

<br><br>

## 7. 인터페이스의 private 메서드, 자바9

- 자바9 이전에는 모든 메서드들은 무조건 `public`이어야 했다.
- 자바9 이후부터, 접근 제이자 `private method`, `private static method` 키워드 추가.
- private : class 내부에서만 사용가능한 접근 제어자.

```java
interface Operator {
  
  private int add (int a, int b) {
    return a + b; //구현부가 존재!
  }
  
  private static int multiply(int a, int b) {
    return a * b;//구현부가 존재!
  }
}


```

<br><br>

## Reference

- [java private method] : https://howtodoinjava.com/java9/java9-private-interface-methods/
- https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6
