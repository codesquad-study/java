# Week5. 클래스

## 학습할 것(필수)

- 클래스를 정의하는 방법
- 객체를 만드는 방법(new 키워드 이해하기 )
- 메서드 정의하는 방법
- 생성자 정의하는 방법

<br>

## 1. 클래스를 정의하는 방법

### 1. 클래스란?

- 객체들의 ```협력 관계```를 코드로 옮기는 과정이다.
- 협력에 참여하여 ```메세지```를 주고 받는 객체를 만드는데 필요한 구현 메커니즘이다.
- 크게 ```속성(변수)```과 ```행위(메서드)```를 가지게 된다.

<br>

### 2. 클래스를 정의하는 법

``` java
class Car {
  private String carName;
  private boolean power;

  //객체 타입도 변수로 가질 수 있다. (인스턴스 변수)
  private Engine engine;
  private Tire tire;
	
  //2. 생성자를 선언해야 객체가 생성된다.
  public Car(String carName, Engine engine, Tire tire) {
    this.carName = carName;
    this.power = false;
    this.engine = engine;
    this.tire = tire;
  }
  
  public void powerOn() {
    this.power = !this.power;
  }
}

interface Engine {}
class BenzEngine implements Engine {}

interface Tire {}
class MicherinTire implements Tire {}
```

<br><br>



## 2. 객체를 만드는 방법(new 키워드 이해하기)

### 1. 인스턴스화

```
클래스명 변수명 = new 클래스명();
```

- 클래스로 정의된 내용을 토대로 ```실체화```하는 작업
- 인스턴스화는 ```new연산자```를 통해서 이루어진다.
- **인스턴스화 과정**
  - new연산자가 메모리 영역에 저장 공간을 할당받음.
  - 생성자(Constructor)가 인스턴스를 초기화.
  - new연산자가 생성된 인스턴스의 메모리 주소를 변수에 저장
  - 변수 내에 저장된 메모리 주소를 통해 객체에 접근.

<br>

### 2. new 연산자

- 클래스 타입의 인스턴스를 생성해주는 연산자

- ```Heap 영역```에 **공간 할당 및 참조값을 객체에게 반환**해주는 역할

  - `Heap영역`은 런타임에 동적으로 변경되는 데이터를 저장한다.

  - 만약 참조값을 할당하지 않는다면?

    - 참조 변수를 선언하지 않으면, `GC` 혹은 `프로그램 종료`시 사라진다.

      ```Java
      //참조변수를 선언하지 않은 경우
      new AirBnbProject();
      
      //참조변수 할당 경우
      AirBnbProject airBnbProject = new AirBnbProject();
      ```

<br>

- 1-2의 인스턴스화 예제

  ```java
  Engine benzEngine = new BenzEngine();
  Tire micherineTire = new MicherinTire();
  
  Car teslaCar = new Car("쿠퍼의 테슬라", benzEngine, micherineTire);
  ```

<br><br>

## 3. 메서드를 정의하는 방법

- 메서드는 `선언부`와 `실행블록`으로 정의한다.
  - 선언부 : 리턴타입, 메서드 이름, 매개변수
  - 실행블록 : 메서드를 구현하는 부분

<br>

- 메서드는 오버라이딩과 오버로딩이 가능하다.
  - 오버라이딩(overriding) : 메서드의 구현부를 다시 정의하는 내용
  - 오버로딩(overloading) :  메서드 `이름과 기본적인 로직`은 유지하고 `매개변수 or 타입`이 달라지는 것.

<br><br>

## 4. 생성자 정의하는 방법

> 생성는 인스턴스 초기화를 담당하는 메서드의 한 종류.

- 생성자 선언 시, 조건

  - 생성자의 메서드명은 `클래스명과 동일`해야 한다.

  - 생성자는 리턴값이 없다.

    

- 생성자 선언 특징

  - class 내부에 생성자를 별도로 선언하지 않을 경우, 컴파일러에서 `default 생성자`를 생성한다.

    - default 생성자 : 매개인자가 없는 생성자

    - 별도로 생성자를 선언할 경우, default 생성자는 생성되지 않는다.

    ```java
    class Car {
      private String carName;
      private boolean power;
    
      //객체 타입도 변수로 가질 수 있다. (인스턴스 변수)
      private Engine engine;
      private Tire tire;
    
      /*
      - 없으면 compiler가 만들어준다.
      	public Car () {  	
      	}
      */
      
      //이것도 defualt constructor이다.
      //별도로 생성자를 선언하면 컴파일러에서 default constructor를 생성하지 않는다.
      public Car () {
        this.carName = "cooperCar";
        this.engine = new BenzEngine();
        this.tire = new MicherinTire();
      }
    }
    ```

  <br>

  - 생성자 오버로딩

    - 생성자도 일종의 메서드이기 때문에 오버로딩이 가능하다.
    - 오버로딩 : `메서드 이름` 그대로, `매개변수`는 다르게

    ```java
    class Car {
      private String carName;
      private boolean power;
      private Engine engine;
      private Tire tire;
      
      public Car () {
        this.carName = "cooperCar";
        this.power = false;
      	this.engine = new BenzEngine();
        this.tire = new micherinTire();
      }
     	//오버로딩된 생성자. 
      public Car (String carName, Engine engine, Tire tire) {
        this.carName = carName;
        this.engine = engine;
        this.tire = tire;
      }
    }
    ```

  <br><br>

  ## 5. this 키워드

   - this : 자신의 인스턴스의 주소를 가리키는 참조 변수

     ```java
     public class Main {
         public static void main(String[] args) {
             Car cooperCar = new Car();
     
             System.out.println(cooperCar);
             System.out.println(cooperCar.getThis());      
         }
     }
     ```

     

     ```
     instance_practice.Car@29453f44
     instance_practice.Car@29453f44
     ```

  <br>

   - this() : 자신의 생성자

     ```java
     class Car {
       private String carName;
       private boolean power;
       private Engine engine;
       private Tire tire;
     
       public Car (String carName, Engine engine) {
         this.carName = carName;
         this.engine = engine;
         this.tire = new MicherinTire();
       }
       
       public Car (String carName, Engine engine, Tire tire) {
     		this(carName, engine);
         this.tire = tire;
       }
       
       public Car getThis() {
         return this;
       }
     }
     ```

     

  