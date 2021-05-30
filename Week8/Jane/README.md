## 인터페이스
- 밑그림만 그려져 있는 기본 설계도
- 추상메서드를 갖지만 추상클래스보다 추상화 정도가 높아 일반 메서드 또는 멤버변수를 구성원으로 가질 수 없다.
- 인터페이스로부터만 상속받을 수 있으며, 클래스와 달리 다중상속이 가능하다.
- 추상클래스와 마찬가지로 인스턴스를 생성할 수 없으며 인터페이스를 implements하는 클래스를 통해 완성된다.

### 인터페이스 정의
```java
public interface OperateCar {

   // constant declarations, if any

   // method signatures
   
   // An enum with values RIGHT, LEFT
   int turn(Direction direction,
            double radius,
            double startSpeed,
            double endSpeed);
   int changeLanes(Direction direction,
                   double startSpeed,
                   double endSpeed);
   int signalTurn(Direction direction,
                  boolean signalOn);
   int getRadarFront(double distanceToCar,
                     double speedOfCar);
   int getRadarRear(double distanceToCar,
                    double speedOfCar);
         ......
   // more method signatures
}
```

### 인터페이스의 장점
- 인터페이스를 작성하면 메서드의 내용을 몰라도 프로그램을 작성하는 것이 가능하기 때문에 한 쪽에서는 인터페이스를 구현하는 클래스를 작성하고, 다른 쪽에서는 프로그램을 작성하여 협업 시 개발 시간을 단축시킬 수 있다.
- 프로젝트의 기본 틀을 인터페이스로 작성하면 표준화된 프로그램 개발이 가능하다. 
- 서로 관계없는 클래스들이 하나의 인터페이스를 구현하도록 함으로써 클래스 간 관계를 맺어 줄 수 있다.
- 인터페이스를 사용하면 클래스의 선언과 구현을 분리시킬 수 있기 때문에 클래스 간 독립적인 프로그래밍이 가능해진다. 


### 인터페이스의 접근 제어자
- 인터페이스의 접근제어자는 public 또는 default만 사용할 수 있다.
- 모든 멤버변수는 public static final이어야 한다. 
- static메서드와 default메서드를 제외한 모든 메서드는 public abstract이어야 한다. 
- 인터페이스의 멤버에 사용된 접근 제어자는 생략하는 것이 가능하며, 생략된 제어자는 컴파일러가 컴파일 시 자동적으로 추가한다.

### 인터페이스 상속
```java
interface AnimalEat {
   void eat();
}
interface AnimalTravel {
   void travel();
}
class Animal implements AnimalEat, AnimalTravel {
   public void eat() {
      System.out.println("Animal is eating");
   }
   public void travel() {
      System.out.println("Animal is travelling");
   }
}
public class Demo {
   public static void main(String args[]) {
      Animal a = new Animal();
      a.eat();
      a.travel();
   }
}
```

### 인터페이스 타입
- 메서드는 인터페이스 타입을 매개변수로 가질 수 있다.
**&rarr;** 메서드 호출 시 인터페이스를 구현한 클래스의 인스턴스가 매개변수로 제공된다. (e.g. `attack(new Fighter())`)
```java
class Fighter extends Unit implements Fightable {
    public void move(int x, int y) { ... }
    public void attack(Fightable f) { ... }
}
```
- 메서드는 인터페이스 타입을 반환형으로 가질 수 있다.
**&rarr;** 메서드 호출 시 인터페이스를 구현한 클래스의 인스턴스(인스턴스 주소)가 반환된다.
```java
Fightable method() {
    Fighter f = new Fighter();
    return f;
}
```


### 기타
- 만약 인터페이스의 메서드 중 일부만 구현한다면 아래와 같이 추상 클래스로 선언해주어야 한다. 
```java
abstract class Fighter implements Fightable {
    public void move(int x, int y) { ... }
}
```
- 상속과 구현을 동시에 할 수 있다.
```java
class Fighter extends Unit implements Fightable {
    public void move(int x, int y) { ... }
    public void attack(Unit u) { ... }
}
```
- 인터페이스 타입의 참조변수로 해당 인터페이스를 구현한 클래스의 인스턴스를 참조할 수 있으며, 인터페이스 타입으로의 형변환도 가능하다. 
```java
Fightable f = new Fighter();
```

## 인터페이스 메서드
- JDK 1.8부터 추상 메서드뿐만 아니라 default 메서드와 static 메서드 또한 인터페이스에 선언할 수 있게 되었다.

### default 메서드
- default 메서드(추상 메서드의 기본적인 구현을 제공하는 메서드)는 추상 메서드가 아니기 때문에 추가 시 클래스를 변경할 필요가 없다.
```java
interface MyInterface {
    void method();
    default void newMethod() {}
}
```
- 추가된 default 메서드와 기존 메서드 간의 이름 중복으로 인해 충돌이 일어날 경우 아래와 같이 처리한다.
  - 여러 인터페이스의 디폴트 메서드 간 충돌이 일어날 경우, 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 해야 한다. 
  - 디폴트 메서드와 조상 클래스의 메서드 간 충돌이 일어날 경우, 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다. 

### static 메서드
- 인터페이스의 인스턴스 생성과는 관계없이 인터페이스 타입에서 호출이 가능한 정적 메서드
```java

// Java program to demonstrate scope
// of static method in Interface.
  
interface PrintDemo {
  
    // Static Method
    static void hello()
    {
        System.out.println("Called from Interface PrintDemo");
    }
}

public class InterfaceDemo implements PrintDemo {
  
    public static void main(String[] args)
    {
  
        // Call Interface method as Interface
        // name is preceeding with method
        PrintDemo.hello();
  
        // Call Class static method
        hello();
    }
  
    // Class Static method is defined
    static void hello()
    {
        System.out.println("Called from Class");
    }
}
```
```
Called from Interface PrintDemo
Called from Class
```

### private 메서드
- Java 9 버전부터 추가되었다.
- 인터페이스 내부의 코드의 재사용성 증가
### 제약 조건
- private 인터페이스 메서드는 추상 메서드가 될 수 없다.
- private 메서드는 인터페이스 내부에서만 사용 가능하다.
- private non-static 메서드는 private static 메서드 내부에서 사용할 수 없다.


<br/>





---

***Source***
- 자바의 정석 (남궁성 저)
- https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
- https://www.tutorialspoint.com/multiple-inheritance-by-interface-in-java