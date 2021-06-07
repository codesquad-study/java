# 1. OOP(Object Oriented Programming)

------

1. 추상화 : 어떤 영역에서 필요로 하는 **속성**이나 **행동**을 추출하는 작업

   - 사물들의 공통적 특징, 추상적 특징을 파악해 인식의 대상으로 삼는 행위 (하나의 집합(그룹화)을 이룬다)

2. 캡슐화 : 정보은 은닉(information hiding)을 통해 **높은 응집도** 와 **낮은 결합도**를 갖도록 한다.

   - 응집도 : 모듈 안의 **요소들이 얼마나 밀접하게 관련**있는지 나타낸다.
   - 결합도 : 어떤 기능을 실행하는데 다른 클래스나 **모듈들에 얼마나 의존적인지** 나타낸다.

3. 일반화 : 여러 객체들이 가진 **공통된 특성을 부각시켜 하나의 개념이나 법칙으로 성립**시키는 과정

   - 외부 세계에 자식 클래스를 **캡슐화(또는 은닉)**하는 개념

   - 이 때, 캡슐화 관계는 일반화 관계를 통해 **클래스 자체를 캡슐화**하는 것으로 확장된다.

   - 일반화 관계는 ‘is kind of 관계가 성립해야 한다.’

     - ex) Stack is kind of ArrayList (false : ArrayList 대신 Stack을 대체할 수 없기 때문)

        ![](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99CB7F3359F4465026)

     - 이런 관계일 때는 위임(delegation)을 사용한다.

     - 위임(delegation) : 자신이 직접 기능을 실행하지 않고 다른 클래스의 객체가 기능을 실행하도록 위임

     - **기능을 재사용**할 때는 **위임**을 사용하자.

       ```java
       public class Stack<T> {
         private ArrayList<T> arList = new ArrayList<>();
       
         public void push(T e) {
       	    arList.add(e);  
       	}  
       
       	public void pop() {
       	    return arList.remove(arList.size() - 1);  
       	}
       }
       ```

4. 다형성(Polymoriphism) : 객체가 **같은 메세지**를 받았을 때 **각자의 방식으로 동작을 처리**하는 능력

   ```java
   abstract class Pet {
     public abstract void talk();
   }
   
   class Dog extends Pet {
     public void talk() {
   	...
   	}
   }
   
   class Cat extends Pet {
     public void talk() {
   	...
   	}
   }
   
   class Parrot extends Pet {
     public void talk() {
   		...
   	}
   }
   
   public class Main {
     public static void groupTalk(Pet[] pets) {
       Arrays.stream(pets).forEach(pet -> pet.talk);
     }
   
     public static void main(String[] args) {
       Pet[] pets = {new Cat(), new Dog(), new Parrot()};
       groupTalk(pets);  
   	}
   }
   ```

5. 피터 코드의 상속 규칙

   - 자식 클래스가 **부모 클래스의 역할을 표현**하고 있으면 **위배**된다. (false : 사람 - 운전자)
   - 자식 클래스의 **인스턴스들 사이에 변환 관계가 필요**하다면 규칙에 **위반** (false : 운전자 : 운전할 때 - 회사원 : 일할 때)
   - **재정의(Override)하지 않고 확장만 수행**해야 한다.
   - 재사용할 목적으로 상속 관계를 표현하지 않는다.
   - 슈퍼 클래스가 역할, 트랜잭션, 디바이스를 표현하지 않아야 한다.

# 2. SOLID 원칙

------

### 1. 단일 책임 원칙(Single Responsibility Principle)

------

- **어떤 객체보다도 작업을 가장 잘할 수 있는 객체에게 책임 할당 (책임 = 잘할 수 있는 것)**- 변경이 있을 때 가능한 한 영향 받는 부분을 줄여야 한다. (책임 = 변경 이유)(책임이 많이 질수록 코드끼리 강하게 결합된 가능성이 높다)
- 회귀 테스트 : 해당 변화가 기존 시스템의 기능에 영향을 주는지 평가하는 테스트(회귀 테스트를 줄이는 방법 : 변경 사항 발생 시, 영향을 받는 부분 최소화)

### 2. 개방-폐쇄 법칙(Opened - Closed Principle)

------

- **코드를 변경하지 않으면서 기능을 추가할 수 있도록 설계**
- 무엇이 변하는 지, 무엇이 변하지 않는지를 구분할 수 있어야 함.
- 클래스를 변경하지 않고도 대상 클래스의 환경을 변경할 수 있는 설계(클래스 = 변화의 단위)

### 3. 리스코프 치환 원칙(Liskove Substitution Principle)

------

- **자식 클래스는 최소한 자신의 부모 클래스에서 가능한 행위는 수행할 수 있어야 한다.**
- 일반화 관계 : **'is kind of'** 관계
- LSP를 만족하기 위해서는 **부모 클래스의 인스턴스를 자식의 인스턴스로 대신할 수 있어야 한다**.
- '[객체.메서드(인자 리스트)]' 형태가 자식 클래스에서도 만족할 경우 가능하다.
- LSP를 만족시키는 가장 간단한 방법 : 재정의되지 않도록 하면 된다.

### 4. 의존 역전 원칙(Dependency Inversion Principle)

------

- **의존 관계를 맺을 때, 변화하기 어려운 것, 거의 변화가 없는 것에 의존하는 원칙**
- **변하지 않는 것 = 인터페이스, 변하기 쉬운 것 = 구체 클래스**
- **의존성 주입(Dependency Injection)** : 클래스 외부에서 의존되는 것을 대상 객체의 인스턴스 변수에 주입하는 기술.

### 5. 인터페이스 분리 법칙(Interface Segregation Principle)

------

- 인터페이스를 **클라이언트에 특화**되도록 분리시키라는 설계 원칙
- **SRP를 만족하더라도 ISP를 만족한다고는 할 수 없다.**(게시판 구현 시, 사용자가 삭제 기능을 사용하지 못하지만, 관리자는 삭제 기능을 수행할 수 있다 -> 모든 메서드가 들어 있는 **인터페이스가 클라이언트에 상관없이 사용**된다면 ISP에 위배.)