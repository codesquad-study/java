# 5주차 과제: 클래스 

## 학습할 것

- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기



### 클래스란?

객체를 생성하기 위해 상태와 행동을 정의한 템플릿. 객체들의 공통 속성을 가지고 있으며, 논리적인 엔티티일 뿐이고 물리적으로 존재하지는 않는다.

### (+) 객체란?

객체는 상태와 행동을 가지고 있고, 객체가 생성되면 **힙 메모리 영역**에 저장된다



## 클래스 정의하는 방법

```java
<접근 제어자> class <클래스 명> {<필드, 생성자, 메소드, ...>}
```



파일에서 클래스를 정의할 때, 접근 제어자가 public인 class는 하나 밖에 없다. 또한 public이 붙은 클래스 명은 파일명과 동일해야 한다. public보다 접근 영역이 좁은 클래스의 경우에는 하나의 클래스 파일에 여러 개 선언할 수 있다. 

```java
// 자바 파일 명 = Student.java
public class Student { }
class School { }
class Professor { }
```



위 코드를 컴파일해서 클래스 파일이 생성될 경우, 클래스 마다 하나의 클래스 파일이 생성된다.

```java
// 클래스 파일 명 = Student.class
public class Student { }
// 클래스 파일 명 = School.class
class School { }
// 클래스 파일 명 = Professor.class
class Professor { }
```



### 접근 제어자란?

- 클래스 혹은 변수의 접근 영역을 제어하기 위해서 사용

| 접근 제어자 | 영역                                                         | 동일 클래스 | 동일 패키지 | 자손 클래스 | 외부 영역 |
| ----------- | ------------------------------------------------------------ | ----------- | ----------- | ----------- | --------- |
| public      | 모든 영역에서 사용 가능                                      | O           | O           | O           | O         |
| protected   | 같은 패키지나 상속받은 클래스에서 사용 가능                  | O           | O           | O           |           |
| default     | 같은 패키지에서 사용 가능 (접근 제어자를 생략 시 default로 설정) | O           | O           |             |           |
| private     | 해당 클래스 내부에서만 사용 가능                             | O           |             |             |           |



## 클래스 구성 요소

### 1. 필드(field) 

객체의 상태 정보를 담는 필드.

- **인스턴스 변수**
  - 인스턴스마다 구분되는 **상태**나 정보를 담는다
  - 인스턴스가 생성되는 **런타임에 heap 영역 메모리에 할당**되고 gc에 의해 관리된다

- **클래스 변수**
  - `static` 키워드를 사용해서 사용하는 전역 변수이므로 인스턴스 생성 없이도 사용이 가능하다
  - 모든 인스턴스가 공유하는 변수
  - **컴파일 타임에 메모리에 할당**되는 변수
  - heap 영역이 아닌 **method 영역**에 할당되고 **gc의 관리를 받지 않는다**



### 2. 메소드(method)

객체의 행동을 정의하는 메서드

- **인스턴스 메소드**
  - 인스턴스의 상태를 관리하는 로직을 인스턴스 메소드로 정의
  - 인스턴스 메소드를 
- **클래스 메소드**
  - 인스턴스 변수와 관계없는 로직을 클래스 메소드로 정의
  - 클래스 메소드 내부에서는 인스턴스 변수를 사용할 수 없다!

### 👀 메소드 정의하는 방법

<img src="./images/메소드구성.png" alt="메소드구성" style="zoom:50%;" />

- **접근 제어자 및 기타 제어자** - 메소드에 접근할 수 있는 범위를 명시
- **리턴 타입** - 메소드 로직을 모두 수행한 뒤에 반환할 타입
- **매개변수** - 메서드에서 사용할 매개변수들을 명시
- **메소드 시그니처** - 메소드 이름과 파라미터를 통틀어 이르는 단위. 컴파일러는 메소드 시그니처를 보고 오버로딩된 메소드들을 구분



### 3. 생성자(constructor) 

생성자는 클래스를 인스턴스화 시킬 때 사용한다. 선언 시, 클래스 이름과 동일해야 한다. 생성자 내부에서는 보통 객체 생성에 필요한 초기화가 진행된다. 클래스에서는 하나 이상의 생성자가 필요하다. 때문에 클래스에서 생성자를 구현하지 않았을 경우, 컴파일 타임에 디폴트 생성자가 자동으로 생성된다. (생성자를 하나라도 구현했을 경우, 디폴트 생성자가 자동으로 생성되지는 않는다)

```java
public class Student {
    String id;

    // 생성자
    public Person(String id) {
        // 초기화
        this.id = id;
    }
}
```



### 👀 객체를 생성하는 방법

객체를 생성하기 위해서는 `new` 키워드와 함께 클래스 생성자를 호출해야 한다.

`new`키워드는 생성된 객체를 메모리(heap 영역)에 할당하고 해당 메모리에 대한 참조값을 반환하여 클래스를 인스턴스화하는 역할을 한다.



### 4. 블록**(**Blocks)

- **클래스 블록**
  - 클래스가 로딩될 때 딱 한 번만 호출

- **인스턴스 블록** 

  -  인스턴스에 관련된 변수를 초기화 하거나 메서드를 실행할 때 사용

- **블록 호출 순서**

  - 클래스 블록 -> 인스턴스 블록 -> 생성자

    

#### (+) 초기화 순서

- 클래스 로딩 시: 기본값 할당 → 명시적 초기화 → 클래스 초기화 블록
- 인스턴스 생성 시: 기본값 할당→ 명시적 초기화 → 인스턴스 초기화 블록 → 생성자
- (+) 명시적 초기화 = 필드 영역에서 하는 초기화 작업

```java
// 클래스
public class Example { 
    // [ 명시적 초기화 ] 인스턴스 변수
    String instanceVariable;   
    // [ 명시적 초기화 ] 클래스 변수
    static String classVariable;

    //  
    // [ 블록 초기화 ] 인스턴스 초기화 블록
    {                       
        instanceVariable = "Instance Variable";
    }
  
    // [ 블록 초기화 ] 클래스 초기화 블록
    static {                
        classVariable = "Class Variable";
    }

    // 생성자
    public Example() { }

    // 인스턴스 메서드
    public void instanceMethod() { }

    // 클래스 메서드
    static void classMethod() { }
}
```



### this 키워드 이해하기

this는 인스턴스 자기 자신을 가리키는 참조변수이다. 

```java
public class Student { 
	private Long id;
  private String name;
  
  public Student (Long id, String name) {
    this.id = id; // (현재 인스턴스).id ➡️ 인스턴스의 id 값을 가리킨다
    this.name = name; // (현재 인스턴스).name ➡️ 인스턴스의 name 값을 가리킨다
  }
  
  public Student (Long id) {
     this(id, "Anonymous");
  }
  
  public void setSomething() {
     this.getME().name = "Another Name";
  } 
  
  public Student getME() {
    return this; // 현재 인스턴스 자체를 리턴할 수 있다.
  }
}
```



#### 1) To refer current class instance variable

this는 현재 인스턴스를 참조하기 위한 참조 변수

<img src="./images/this키워드.png" alt="this키워드" style="zoom:50%;" />



this는 사실 사용하지 않아도 무방한 경우가 있긴 하지만, 주로 클래스 속성 변수명과 메소드의 파라미터 변수명의 혼동을 피하기 위해서 사용한다.

#### 2) To invoke current class method

현재 인스턴스의 변수를 호출할 수 있다. 

#### 3) To invoke current class constructor

this()는 현재 인스턴스의 클래스 생성자를 호출할 수 있다. 보통 생성자를 재사용하기 위해서 활용된다.

#### 4) To return current class instance

현재 인스턴스 자체를 리턴할 수 도 있다.



### 참고

클래스 정의

- https://docs.oracle.com/javase/tutorial/java/concepts/index.html

- https://www.javatpoint.com/object-and-class-in-java



this 키워드

- https://www.w3schools.com/java/ref_keyword_this.asp

- https://www.javatpoint.com/this-keyword