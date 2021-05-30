# [Week8] 인터페이스



## 학습할 것 (필수)

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9



## 인터페이스란?

소프트웨어 간에 서로 소통할 수 있게끔 만들어놓은 공통적인 지침서다.

조금 더 코드 레벨로 들어가면, 실질적인 구현부 없이 추상 메소드만 가지는 콜렉션이다.

🙌 Method bodies exist only for default methods and static methods.

➡️ 단, java 8 이후부터는 인터페이스에서 예외적으로 default method와 static method를 사용해서 구체적인 구현 로직을 넣을 수 있다.



#### 인터페이스의 장점

1. 메시지를 전달하기만 하면 된다! 

   - 메소드를 호출하는 소프트웨어는 구체적인 구현 로직에 대해 알 필요 없이 메소드 시그니처만 알면 된다.
   - 각 모듈의 내부 내용을 감추고 *인터페이스*를 통해서만 메시지를 전달하자

2. 표준화 가능

3. 관계없는 클래스들을 하나의 공통 인터페이스로 묶을 수 있다.

   - Comparable 등 공통적으로 사용해야하는 

4. 유지 보수에 유리하다

   - 클래스 선언부와 구현을 분리함으로서 내부 구현에 대한 종속성을 낮춘다. 내부구현을 바꾸더라도 전체 코드에 영향을 주지 않는다. 

   - 실제 구현에 독립적인 프로그램을 작성할 수 있다.

     

## 인터페이스 정의하는 방법

인터페이스를 작성하는 것은 클래스를 작성하는 것과 동일하다. 단지 키워드로 class 대신 interface를 사용할 뿐이다.

그리고 interface에도 클래스와 같이 접근제어자를 사용할 수 있다. 단 아래와 같은 조건이 있다. 아래 조건을 지키지 않을 경우에는 컴파일러에서 자동으로 생략된 제어자를 추가해준다.

- 멤버 변수는 public static final만 가능하다.

- 메소드 접근 제어자로 `public` 혹은 `default` 만 사용 가능

can contain *only* constants, method signatures, default methods, static methods, and nested types.

다만 모든 메소드들이 abstract이어야 하기 때문에 구체적인 구현 바디 없이 메소드 시그니처 뒤에 바로 세미콜론으로 끝맺어야 한다. (default 메소드 제외)

```java
public interface OperateCar {
  // constant declarations, if any
  // method signatures
   
   int turn(Direction direction,
            double radius,
            double startSpeed,
            double endSpeed);
   int changeLanes(Direction direction,
                   double startSpeed,
                   double endSpeed);
   int signalTurn(Direction direction,
                  boolean signalOn);
 
   // more method signatures
```



## 인터페이스 상속

다중 상속이 가능하며, 인터페이스는 인터페이스로부터만 상속 가능하다. Object클래스처럼 최고 조상 클래스가 존재하지 않는다.

```java
interface Movable {
  void move(int x, int y);
}
interface Attackable {
  void attack(Unit u);
}
interface Fightable extends Movable, Attackable { }
```



## 인터페이스 구현하는 방법

Interfaces cannot be instantiated—they can only be *implemented* by classes or *extended* by other interfaces

➡️ 인터페이스는 인스턴스화 될 수 없고, 구현한다는 의미의 키워드 `implements`를 사용해서 클래스를 통해 구현되거나 다른 인터페이스를 통해 확장될 수 있다.



구현 클래스에서 인터페이스를 구현할 때는 

```java
class Fighter implements Fightable {
	public void move(int x, int y) { /* 실제 구현 */ }
	public void attack(Unit u) { /* 실제 구현 */ }
}
```

abstract 클래스에서 interface를 상속받을 때는

```java
abstract class AbstractFighter implements Fightables { 
	public void move(int x, int y) { /* 실제 구현 */ }
}
```

(+) abstract은 상속받을 때 extends 사용

```java
class Fighter extends AbstractFighter {
	public void attack (Unit u) { /* 실제 구현 */ }
}
```



By convention, the `implements` clause follows the `extends` clause, if there is one.

➡️ 상속(extends)과 구현(implements)을 동시에 할 경우, extends가 implement 보다 앞에 선언하는 것이 관례



## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

해당 인터페이스 타입의 참조변수로 클래스의 인스턴스를 참조할 수 있으며, 인터페이스 타입으로 형변환도 가능하다.

If you define a reference variable whose type is an interface, any object you assign to it *must* be an instance of a class that implements the interface.

➡️ 만약 인터페이스 레퍼런스를 참조변수 선언 타입(static type)으로 지정했다면, 해당 참조 변수로 할당되는 object는 반드시 **선언된 인터페이스를 상속받은 인스턴스**여야 한다.



## 인터페이스의 기본 메소드 (Default Method), 자바 8

이미 존재하는 구현체들을 깨지않고 java 8에서 기존의 Collection 기능에 람다 메소드를 추가하는 과정에서 default 메소드가 등장했다.

default 메소드가 등장하면서 interface는 다중 상속을 활용할 수 있다는 강점과 더불어 부모 클래스에서 공통 기능을 구현할 수 있다는 abstract의 장점을 함께 가지게 되었다. 대신 상속받은 default 메소드들의 signature가 같은 경우에는 ambiguous problem이 발생할 수 있는 점을 고려해야 한다. 

반면 abstract 클래스 같은 경우에는 생성자를 구현할 수 있고 상태를 가질 수 있기 때문에 문맥 상황에 맞게 선택하면 된다.

🔗 [참고](https://muhammadkhojaye.blogspot.com/2014/03/interface-default-methods-in-java-8.html)

#### 디폴트 메서드 충돌 규칙

"오버라이드!"

1. 인터페이스 디폴트 메서드 간의 충돌 ➡️ 구현 클래스에서 따로 오버라이드 처리를 해주어야 한다.
2. 인터페이스 디폴트 메서드 vs 조상 클래스 메소드 충돌 ➡️ 조상 클래스의 메소드가 오버라이드 된다.



#### 1번의 구체적인 예시

```java
public interface Person {
    default int getId() {
        return 0;
    }
}

public interface Identified {
    default int getId() {
        return Math.abs(hashCode());
    }
}

public class Employee implements Person, Identified {
    public int getId() {
        return Identified.super.getId(); // ⭐️⭐️⭐️ 구현 클래스에서 오버라이드 처리!
    }
}
```



## 인터페이스의 static 메소드, 자바 8

java 8로 접어들면서 인터페이스에 static 메소드도 추가가 가능해졌다. 

Every instance of the class shares its static methods

➡️ 일반 클래스에 구현된 static 메소드와 같이, 모든 클래스들은 인터페이스에 구현된 static 메소드를 공유할 수 있다



## 인터페이스의 private 메소드, 자바 9

Java 9부터 인터페이스에서 private 키워드를 사용할 수 있게 되었다.

Private method can be used only inside interface and other static and non-static interface methods.

➡️  Private으로 선언된 메소드는 오직 인터페이스 내부에서만 사용이 가능하고 **상속이 불가능**하다. 주로 코드를 재사용하기 위한 용도로 사용된다.



#### private static method in Interface 

인터페이스의 static 메소드에서도 private 키워드를 사용할 수 있다!

1. private static 메소드는 해당 인터페이스의 다른 static 메소드 혹은 non-static 메소드에서 호출될 수 있다.
2. private non-static 메소드는 static에서는 호출될 수 없다.

- [참고](https://howtodoinjava.com/java9/java9-private-interface-methods/#:~:text=Since%20java%209%2C%20you%20will,private%20static%20method%20in%20interfaces.&text=Private%20interface%20method%20cannot%20be,and%20non%2Dstatic%20interface%20methods.)



#### (+) Which should you use, abstract classes or interfaces?

- Consider using **abstract classes** if any of these statements apply to your situation:

  - You want to **share code** among **several** closely related classes. ➡️ 응집도가 강한 클래스들끼리 코드를 공유하고 싶을 때

  - You expect that classes that extend your abstract class have many **common methods or fields**, or require access modifiers other than public (such as **protected and private**). ➡️ 공통 요소들이 많고 public 이외의 접근 제어자를 사용하고 싶을 때

    

- Consider using **interfaces** if any of these statements apply to your situation:

  - You expect that **unrelated classes** would implement your interface. For example, the interfaces [`Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html) and [`Cloneable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Cloneable.html) are implemented by many unrelated classes. ➡️ 다중 상속 장점 활용
  - You want to specify the **behavior** of a **particular data type**, but not concerned about who implements its behavior. ➡️ 호출하는 쪽은 메시지만 신경쓰고 싶다면!
  - You want to take advantage of multiple inheritance of type. ➡️  다중 상속을 원할 때

🔗 [참고](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)