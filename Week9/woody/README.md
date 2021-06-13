# [Week9] 예외 처리



## 학습할 것

- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 자바가 제공하는 예외 계층 구조
- Exception과 Error의 차이는?
- RuntimeException과 RE가 아닌 것의 차이는?
- 커스텀한 예외 만드는 방법



### 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

프로그램 실행 도중 발생하는 예외를 처리하기 위해서 try/catch/finally 사용한다

#### 1. try ﹒ catch ﹒ finally

```java 
try {
  // 예외 처리
} catch (e1) {
  // try 블럭에서 예외 발생 시 실행할 코드
} catch (e2) {
  // 위의 catch 문에서 핸들하지 못하는 exception에 대해서 처리
} ...
  finally {
    // 예외 발생 여부와 상관없이 무조건 실행되는 코드
}
```



#### (+) Multicatch block

JDK 1.7 버전부터 catch 블럭을 하나로 합칠 수 있게 되었다. 

```java 
try {
  System.out.println(1 / 0);
} catch (IllegalArgumentException | ArithmeticException e) {
  System.out.println(e.)
}
```

단 

 

#### 2. throw ﹒ throws

| throw                                | throws                                                       |
| ------------------------------------ | ------------------------------------------------------------ |
| 명시적으로 예외를 던져주기 위해 사용 | 예외가 발생한 메소드를 호출한 곳으로 예외 객체를 넘기기 위해 사용() |
| 메소드 내부에서 선언                 | 메소드 시그니처에 선언                                       |
| 여러 개의 예외를 던질 수 없다.       | throws 키워드 뒤에 여러 개의 예외를 선언할 수 있다.          |



```java
public void method() {
  // 개발자가 예외를 고의로 발생시킨다.
  throw new RuntimeException();
}
```



```java
public void method() throws ClassNotFoundException {
  // ...
  // ClassNotFoundException 예외 발생 시 method를 호출했던 상위 메소드(Caller method)로 떠넘긴다
}
```



#### 3. try-with-resources

- try 문 안에서 선언과 동시에 값을 초기화할 수 있다.

  ```java
  try (PrintWriter writer = new PrintWriter(new File("test.txt"))) {
      writer.println("Hello World");
  }
  ```

- 여러 개의 resource를 중복으로 선언해도 OK~

  ```java
  try (Scanner scanner = new Scanner(new File("testRead.txt"));
      PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
      while (scanner.hasNext()) {
  	writer.print(scanner.nextLine());
      }
  }
  ```

  

- try가 종료되거나 예외가 발생했을 때, 자동으로 resource 해제 (close() 호출) 
  - -> 실수로 close()를 작성하지 않는 것을 방지해준다.

- **try-catch-finally을 try-with-resource로 치환!**

```java
Scanner scanner = null;
try {
    scanner = new Scanner(new File("test.txt"));
    while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
} finally {
    if (scanner != null) {
        scanner.close();
    }
}
```



```java
try (Scanner scanner = new Scanner(new File("test.txt"))) {
    while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException fnfe) {
    fnfe.printStackTrace();
}
```



- Java 9 부터는 final 키워드로 선언된 자원을 try 문에 지정할 수 있다
  - we can now use [\*final\* or even effectively final](https://www.baeldung.com/java-effectively-final) variables inside a \*try-with-resources\*  block
  - scanner의 경우 final로 선언되어 있기 때문에 try문에 선언할 수 있다.
  - writer는 명시적으로 final이라고 선언되어있지는 않지만 처음 선언한 뒤 바뀔 일이 없기 때문에(effectively final) 마찬가지로 try문에 선언이 가능하다

```java
final Scanner scanner = new Scanner(new File("testRead.txt"));
PrintWriter writer = new PrintWriter(new File("testWrite.txt"))
try (scanner; writer) { 
    // ...
}
```



### Exception과 Error의 차이는?

**오류(Error)**

- 시스템의 비정상적인 상황이 생겼을 때 발생
- 대부분 복구가 불가능하기 때문에 코드레벨에서 따로 에러에 대한 처리를 해줄 필요가 없음
-  OutOfMemory, StackOverFlow 등이 대표적

**예외(Exception)**

- 개발자가 구현한 로직에서 발생
- 예외 상황은 복구 가능하다 (try-catch 문 등으로 핸들해줄 수 있다)
- Checked Exceptions에는 NoSuchMethod, ClassNotFound.
  Unchecked Exceptions : NullPointer, IndexOutOfBounds.

### 자바가 제공하는 예외 계층 구조

<img src="./images/exception-계층구조.png" alt="exception-계층구조" style="zoom:40%;" />

**Throwable**

- error 클래스와 exception 클래스의 수퍼클래스

- 해당 클래스를 상속받은 인스턴스는 JVM 혹은 자바 코드의 throw 문에 의해 던져질 수 있다. 

  - Only objects that are instances of this class (or one of its subclasses) are **thrown by the Java Virtual Machine** or can be thrown by the **Java `throw` statement**

    

**Unchecked Exception (RunTimeException)**

- 컴파일 타임에 확인이 안되는 에러, 즉 런타임에 발생하는 예외
- NullPointerException, ArithmeticException, IllegalArgumentException, IndexOutOfBoundException 등 동적으로 발생하는 예외들이 이에 해당한다.

**Checked Exception**

- 컴파일 타임에 확인할 수 있는 예외, 처리해주지 않을 시 컴파일 에러 발생
- 어플리케이션이 반드시 해당 예외를 처리해주어야 하는 경우
  - IOException, SQLException, FileNotFoundException 등이 있다.



### 커스텀한 예외 만드는 방법

1. 모든 예외는 Throwable의 서브클래스여야 한다.
2. RunTimeException을 만들고 싶다면 RunTimeException 클래스를 상속받아야 한다
3. Checked Exception을 만들고 싶다면 Exception 클래스를 상속받아야 한다.



### (+) 예외 처리 방식

#### 1. Re-throwing Exceptions

예외를 처리하고 다시 예외를 발생시켜서 해당 메소드를 호출한 상위 메소드로 예외를 던지는 방식을 말한다. 이런 방식은 주로 예외가 발생한 곳에서 예외 핸들을 해주고 상위 시스템으로 예외 상황을 알릴 때 유용하다.

```java

 String name = null;

 try {
      return name.equals("Joe"); // causes NullPointerException
 } catch (Exception e) {
      System.out.println("handle exception");
      throw e;
 }

```



#### 2. Wrapping Exceptions

- 말 그대로 예외를 래핑해서 상위로 전달할 수 있다.
- 좀 더 추상화 수준이 높은 예외로 랩핑해서 던질 수 있다는 장점
- 복구가 불가능한 Checked Exception을 처리해야할 경우, 이를 Unchecked Exception으로 전환하여 다른 계층에서 일일이 예외를 선언할 필요가 없도록 할 수도 있다.

```java
String name = null;
try {
     return name.equals("Joe"); // causes NullPointerException
} catch (Exception e) {
     System.out.println("handle exception");
     throw new IllegalArgumentException(e);
}
```

- 예외 래핑 결과

<img src="./images/예외-래핑.png" alt="예외-래핑" style="zoom:50%;" />

- 그림으로 흐름을 잘 설명해놓으셨다

  - https://www.notion.so/3565a9689f714638af34125cbb8abbe8

  

#### (+) 이펙티브 자바에서 예외 언급

- **아이템 69. 예외는 진짜 예외 상황에만 사용하라**
  - 예외는 반드시 예외 상황에서만 사용하며 일상적인 제어 흐름용으로 사용해서는 안 된다
  - 예외가 발생하기 전, 상태 검사 메서드를 제공하거나 옵셔널 또는 특정 값을 반환하도록 하는 등 미리 프로그램의 흐름을 적절하게 제어하는 것이 좋다.
  - 예를 들어, Iterable 인터페이스를 사용하기 위해서 상태 검사 메소드인 hasNext를 사용해서 정상적인 제어 흐름에 대한 처리를 해주는 것이 중요하다.

- **아이템 70. 복구할 수 있는 상황에는 검사 예외를, 프로그래밍 오류에는 런타임 예외를 사용하라**

  - 호출하는 쪽에서 복구가 가능하다면 check exception
    - try-catch로 예외를 처리하거나 혹은 throw를 통해 콜스택의 상위 메소드에서 처리하도록 예외를 바깥으로 던지자

  - 프로그래밍 오류는 보통 uncheck exception으로 처리한다.

- **아이템 71. 필요 없는 검사 예외 사용은 피하라**
  - 과하게 검사 예외를 사용하게 되면 try-catch문이 코드를 뒤덮게 되는 상황이..
  - 가독성 측면에서도 

- **아이템 73. 추상화 수준에 맞는 예외를 던져라**

  - 추상화 수준이 높은 예외로 래핑해서 상위 메소드로 던질 수 있다.

  - ```java
    try {
        // 저수준 추상화를 이용한다.
    } catch (LowerLevelException e) {
        // 추상화 수준에 맞게 다시 랩핑해서 던진다
        throw new HigherLevelException(...);
    }
    ```

    

### Reference

- throw and throws
  - https://www.javatpoint.com/difference-between-throw-and-throws-in-java
  - https://techdifferences.com/difference-between-throw-and-throws-in-java.html

- try-with-resource
  - https://www.baeldung.com/java-try-with-resources
- error and exception
  - https://techdifferences.com/difference-between-error-and-exception.html

- exception hierarchy

  - https://www.manishsanger.com/java-exception-hierarchy/

  - https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html

- 예외 처리 방법 (예외 복구, 예외 처리 회피, 예외 전환)
  - https://www.notion.so/3565a9689f714638af34125cbb8abbe8
  - https://www.baeldung.com/java-wrapping-vs-rethrowing-exceptions

- https://velog.io/@youngerjesus/%EC%9E%90%EB%B0%94-%EC%98%88%EC%99%B8-%EC%B2%98%EB%A6%AC