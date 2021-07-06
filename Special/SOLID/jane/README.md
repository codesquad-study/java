# 객체 지향 특징
## 추상화(Abstraction)
추상의 사전적인 의미는 "여러가지 사물이나 개념에서 **공통되는 특성이나 속성** 따위를 추출하여 파악하는 작용"이다. 그러므로 추상화는 **여러 개체들을 분해해서 찾을 수 있는 공통되는 특성을 관심 영역에 따라 재조합**하는 것이라고 이해할 수 있다. 

예를 들어 **관심 영역(애플리케이션의 컨텍스트)**를 병원으로 설정한다면 사람은 환자이고 환자의 공통적인 행위는 먹다, 자다 등이지만, 은행으로 설정한다면 사람은 고객이되고 고객의 공통적인 행위는 입금하다, 출금하다, 대출하다 등이 된다. 또한 이러한 행위들은 클래스 안의 메서드로 추상화시킬 수 있다.

마찬가지로 환자의 시력, 몸무게, 고객의 직업, 연봉 등도 클래스 안의 속성, 즉 필드로 추상화시킬 수 있다. 즉, **사람의 다양한 속성을 정해진 애플리케이션의 경계에 따라 추출하고 재조합하는 작업이 모델링이며, 객체 지향 패러다임 내에서의 추상화**인 것이다. 

따라서 **추상화는 모델링이며, 클래스는 추상화의 결과**라고 말할 수 있다. 


## 상속(Inheritance)
흔히 상속이라고 하면 `조부모님-부모님-자식` 등의 관계도를 생각하지만, 객체 지향에서의 상속은 `동물-포유류-구체적인 종`과 같은 관계에 가깝다. 즉 상위 클래스는 하위 클래스의 "부모"라기 보다는 공통적인 특성을 갖고있는 분류에 가까우며, **하위 클래스는 상위 클래스의 코드를 상속(inherit)하기 보다는 재사용 및 확장(extend)**한다고 표현하는게 더 정확하다.

상속관계가 헷갈릴 때는 상위 클래스와 하위 클래스 사이에 `IS-A(is a kind of)` 관계가 성립하는지 생각해보면 된다. 예를 들어 `아기 토끼는 엄마 토끼이다.`라는 문장은 성립하지 않지만, `토끼는 동물이다.`라는 문장은 성립한다. 이는 아기 토끼는 엄마 토끼의 하위 클래스가 될 수 없지만, 토끼는 동물의 하위 클래스가 될 수 있음을 의미한다.

따라서 상속은 **하위 클래스가 상위 클래스의 코드를 재사용 및 확장**하는 것이라고 이해할 수 있다. 


## 다형성(Polymorphism)
다형성이란 프로그래밍 언어가 갖고 있는 자료형 체계의 성질을 설명하는 단어로, 프로그램 언어 각 요소(상수, 변수, 오브젝트, 메소드 등)가 다양한 자료형에 속하는 것이 허용되는 성질을 의미한다. 즉, 다형성이란 프로그램 언어의 각 요소가 '여러 가지 형태를 가질 수 있는 능력'이라고 이해할 수 있다. 

객체 지향에서 다형성이란 **서로 다른 유형의 객체가 동일한 메시지를 수신할 때 서로 다른 메서드를 이용해 메시지를 처리하는 것**이다. 이런 관점에서 이해한다면 다형성은 **하나의 메시지와 하나 이상의 메서드 사이의 관계이며, 하나의 객체는 동일한 역할을 수행할 수 있는 다른 객체로 대체할 수 있다는 것**을 의미한다.

다형성의 장점은 객체들의 대체 가능성으로 인해 설계가 유연하고 재사용이 가능하다는 것이다. 다형성이 보장될 경우, **송신자가 수신자의 종류를 모르더라도 메시지를 전송하는 것이 가능하며, 송신자에게 어떠한 영향 없이 수신자를 교체, 추가**하는 것이 가능하다. 따라서 책임을 완수할 수 있는 새로운 유형의 객체를 정의함으로써 객체들의 협력의 범위를 넓힐 수 있다.



## 캡슐화(Encapsulation)
캡슐화는 이름처럼 **데이터 속성과 기능을 캡슐 안에 묶어서 관리**한다는 의미와 **특정 객체의 정보를 외부(다른 객체)로부터 은닉**한다는 의미를 가진다.

캡슐화를 통해 객체의 데이터의 속성과 기능을 묶어서 관리하면 **각 객체의 독립적인 역할을 보장**할 수 있고, 객체의 정보를 은닉하면 외부 객체가 **특정 객체의 정보에 직접 접근하거나 변경하는 것을 차단**할 수 있다. 

즉, 객체는 다른 객체가 **무엇(what)**을 수행하는지는 알 수 있지만 **어떻게(how)** 수행하는지에 대해서는 알 수 없다. 이러한 특성은 객체의 수정이 다른 객체에게 주는 영향을 최소화하며, 소프트웨어의 유지 보수 및 확장을 용이하게 한다.



# SOLID 원칙 

## 단일 책임 원칙(SRP, Single Responsibility Principle)
> There should never be more than one reason for a class to change. In other words, every class should have only one responsibility.

즉, 한 클래스는 하나의 책임만 가져야 한다는 원칙이다. 예를들어 Developer라는 클래스가 있다고 가정해보자. 
```java
public class Developer {
    private final static boolean BACKEND = true;
    private final static boolean FRONTEND = false;
    private final boolean role;

    public void work() {
        if (this.role == BACKEND) {
            ...
        } else { 
            ... 
        }
    }
}
```
Developer 클래스의 work() 메서드는 백엔드와 프론트엔드 개발자의 행위를 모두 구현하려고 하기 때문에, 단일 책임 원칙을 위반하고 있다. 이를 개선하기 위해 해당 클래스를 Developer 클래스를 상속하는BackendDeveloper와 FrontendDeveloper 클래스로 분리한다면 분기문을 사용할 필요가 없으며, 단일책임 원칙을 지킬 수 있다.

위와 같이 역할에 따라 클래스를 분리해 놓으면, 백엔드 개발자의 역할이 바뀐다고 해도 FrontendDeveloper 클래스에는 전혀 영향이 가지 않는다. 이처럼 변경이 있을 때 파급 효과가 적다면 단일 책임 원칙을 잘 따른 것이라고 판단할 수 있다.


## 개방-폐쇄 원칙(OCP, Open/Closed Principle)
> Software entities ... should be open for extension, but closed for modification.

소프트웨어의 요소(클래스, 모듈, 함수 등)는 확장에는 열려있으나 변경에는 닫혀있어야 한다는 원칙이다. 
Baeldung에서 제공해주는 예제를 보면 더 쉽게 이해할 수 있다. 
```java
public interface CalculatorOperation {
    void perform();
}

public class Addition implements CalculatorOperation {
    private double left;
    private double right;
    private double result;

    // constructor, getters and setters

    @Override
    public void perform() {
        result = left + right;
    }
}

public class Calculator {

    public void calculate(CalculatorOperation operation) {
        if (operation == null) {
            throw new InvalidParameterException("Cannot perform operation");
        }
        operation.perform();
    }
}
```
CalculatorOperation 클래스가 존재하고 Addition, Division 등의 구체적인 연산을 구현한 클래스가 CalculatorOperation 인터페이스를 구현하는 방식으로 설계한다면, 추가적인 연산이 생겨 클래스를 확장하더라도 Calculator의 코드는 변경할 필요가 없다. 이와같이 다형성을 활용하면 새로운 기능이 추가될 때 기존에 사용하던 인터페이스를 구현한 새로운 클래스를 만들어주기만 하면 된다. 

따라서 유연성, 재사용성, 유지보수성 등 객체지향 프로그래밍의 장점을 제대로 누리기 위해서는 개방-폐쇄 원칙 준수가 필수적이다.

## 리스코프 치환 원칙(LSP, Liskov Subsitution principle)
> Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it.

프로그램 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다는 원칙이다. 

예를 들어 동물이라는 클래스를 상속하는 고양이와 강아지 클래스가 있다고 할 때 고양이 클래스의 인스턴스는 동물 객체 참조 변수에 대입하더라도 동물 클래스의 인스턴스 역할을 하는 데 문제가 없다.
```java
Animal cat1 = new Cat();
```
그러나 A 클래스가 B 클래스가 가진 역할을 수행할 수 없는데도, A 클래스가 B 클래스를 상속하도록 설계한다면 리스코프 치환 원칙을 위배한 것이다. 예를 들어 Father 클래스의 역할을 Daughter가 할 수 없는데도 Daughter 클래스가 Father 클래스를 상속하도록 설계한 건 객체지향의 상속을 잘못 적용하고 있으며, 리스코프 치환 원칙을 위배한 사례라고 불 수 있다.
```java
Father jane = new Daughter();
```

쉽게 말해 항위 클래스는 상위 클래스의 한 종류라는 상속의 규칙을 잘 지키고 있고, 인터페이스를 구현하고 있는 구현 클래스가 인터페이스의 규약을 전부 지키고 있다면 리스코프 치환 원칙을 준수하고 있다고 판단할 수 있다.

## 인터페이스 분리 원칙(ISP, Interface Segregation Principle)
> Many client-specific interfaces are better than one general-purpose interface.

특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다는 원칙이다.
![](https://images.velog.io/images/janeljs/post/530e1718-a16e-4554-b225-28104c1211b3/image.png)

예를 들어 Payment 인터페이스를 Loan 인터페이스와 Bank 인터페이스로 분리한다면 Bank 인터페이스에 수정이 발생해도 Loan 클라이언트에게는 영향을 주지 않으며, Loan 클라이언트는 Bank 인터페이스가 아닌 Loan 인터페이스에 있는 메서드들과만 의존 관계를 맺는다. 또한 구현 클래스는 한 개의 범용 인터페이스 사용으로 인한 비어있는 메서드들을 가질 필요가 없다.  

이처럼 인터페이스 분리 원칙을 적용한다면 인터페이스가 명확해지고 대체 가능성이 높아진다는 장점이 있다.


## 의존관계 역전 원칙(DIP, Dependency Inversion Principle)
> Depend upon abstractions, not concretions.

추상화에 의존해야지 구체화에 의존하면 안 된다는 원칙이다.

예를 들어 자동차와 타이어의 관계를 생각해보자. 타이어는 소모품이고, 계절에 따라 교체될 수 있다. 따라서 자동차와 타이어의 관계를 설정할 때 자동차가 스노우타이어라는 구체 클래스를 의존하게 하는 것보단, 스노우타이어, 일반타이어, 광폭 타이어가 구현하고 있는 타이어라는 인터페이스를 새로 정의하고, 자동차는 타이어 인터페이스를 의존하게 하는 것이 좋다. 

인터페이스가 아닌 구현체에 의존하게 된다면 구현체의 유연한 변경이 어려워지기 때문에, 구현 클래스 또는 하위 클래스보다 변경 가능성이 낮은 인터페이스, 추상 클래스, 상위 클래스에 의존하는 것이 좋다.

## 정리
>- SRP: 어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다.
- OCP: 자신의 확장에는 열려 있고, 주변의 변화에 대해서는 닫혀 있어야 한다.
- LSP: 서브 타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다.
- ISP: 클라이언트는 자신이 사용하지 않는 메서드에 의존 관계를 맺으면 안 된다.
- DIP: 자신보다 변하기 쉬운 것에 의존하지 마라.

---

***Source***
- https://en.wikipedia.org/wiki/SOLID
- 스프링 입문을 위한 자바 객체 지향의 원리와 이해 (김종민 저)
- 객체지향의 사실과 오해 (조영호 저)
- 김영한 님 객체지향 설계와 스프링 자료
- https://gist.github.com/sungjaeHong/db5a2f6784eca3ab97d2fa36b967a402
- https://coding-factory.tistory.com/328
- https://coding-factory.tistory.com/328
- https://www.baeldung.com/java-open-closed-principle
- https://www.baeldung.com/java-interface-segregation
- https://www.baeldung.com/java-dependency-inversion-principle
- http://stg-tud.github.io/sedc/Lecture/ws13-14/3.4-ISP.html#mode=document
