# Week6. 상속

## 학습할 것(필수) 

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스

<br><br>

## 자바 상속의 특징

- 자바의 모든 클래스는 `Object` 를 상속하고 있다. 

- 자바는 `단일 상속`만을 지원한다.

- 자바에서는 상속의 횟수에 제한을 두지 않는다.

  ```java
  //상속의 횟수를 제한하지 않는다. = 상속받는 횟수에 제한을 두지 않는다.
  class A{}
  class B extends A {}
  class C extends B {}
  ```



<br><br>

## super 키워드

- this:인스턴스 자신의 주소를 나타내는 참조변수
- super: 현재 인스턴스의 부모 클래스(슈퍼 클래스)의 주소를 나타내는 참조변수
- super():  부모 클래스(슈퍼 클래스)의 생성자를 의미하는 키워드

```java
public class Parent {
    int value  = 10;
}
```



```java
class Child extends Parent{
  int value = 20;

  public void print() {
    System.out.println(value);
    System.out.println(this.value);
    System.out.println(super.value);
  }
}
```

```
20
20
10
```

<br><br>

## 메서드 오버라이딩(Overriding)

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

- `ElementType.METHOD` : 어노테이션 적용할 대상이 `METHOD`라는 것을 의미한다.
- `RetentionPolicy.SOURCE` : 컴파일 단계에서판단하겠다는 내용.

<br>

### 오버라이딩 조건

- `함수명이 동일`해야 하고, `매개변수`가 같아야 하고, `리턴 타입`이 같아야 한다.

```java
class Parent {
  public String print() {
  	return "parentPrint";
  }
}

```

```java
class Child {
	@Override
  public String print() {
 		return "childPrint"   
  }
}
```

<br><br>



## 추상 클래스(Abstract class)

```java
abstract class 클래스이름 {
  
}
```

<br>

### 추상 클래스의 특징

- 자체적으로 인스턴스 생성 불가능
- `생성자`와 `멤버변수`, `일반 메서드`를 모두 가질 수  있다.

```java
 abstract class AbstracSample {
    private int value = 5;
    abstract String getAbstracMethod();
}
```

- 추상 클래스를 구현할 때는 최대한 공통되는 부분을 잘 구분짓도록 한다.
  - 자바 진영에서는 추상 클래스를 단일 상속을 지원하고 있고, 상속할 경우, 무조건 추상 메서드를 구현해야 하기 때문이다.

<br><br>

## final 키워드

- final의 의미
  1. `마지막` 
  2. `변경될 수 없는`

| 대상               | 의미                                                         |
| ------------------ | ------------------------------------------------------------ |
| 클래스             | 해당 클래스의 상속을 막는다.<br>(어떤 클래스가 final 선언한 클래스를 상속할 수 없다.) |
| 메서드             | 오버라이딩을 막는다.(하위 클래스에서 메서드 재정의 불가)     |
| 멤벼변수, 지역변수 | 값 변경 불가능하다.                                          |

<br><br>

## Object 클래스

- 자바의 모든 클래스는 Object 클래스를 상속받고 있다.
-  모든 클래스의 최상위 클래스를 의미한다.

<br><br>

## 다이나믹 메서드 디스패치(Dynamic Method Dispatch)

- 다이나믹 : `런타임`의 의미

- 디스패치 : `어떤 메서드를 호출할지` 결정한다는 의미

- = 자바 프로그램이 런타임 시, 오버라이딩된 메서드 중 선택 가능하도록 해주는 기법.

  ```java
  class Parent {
    void print() {
      System.out.println("I'm your father");
    }
  }
  
  class Child extends Parent {
  	@Override
    void print() {
      System.out.println("I'm your child");
    }
  }
  
  class Main {
      public static void main(String[] args) {
          Parent parent = new Parent();
          Child child = new Child();
  
          Parent ref;
          ref = parent;
          ref.print();
  
          ref = child;
          ref.print();
      }
  }
  ```

  ````
  I'm your father
  I'm your child
  ````

  