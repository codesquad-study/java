# Week9.예외처리

## 학습할 것 (필수)

- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 자바가 제공하는 예외 계층 구조
- Exception과 Error의 차이는?
- RuntimeException과 RE가 아닌 것의 차이는?
- 커스텀한 예외 만드는 방법

<br><br>

## 예외(Exception) & 에러(Error)

- __에러(Error)__

  - 시스템에 비정상적인 상황이 생겼을 때 발생

  - 시스템 레벨에서 발생하기 때문에 심각한 오류

  - 개발자가 미리 예측해서 처리할 수 없기 때문에, 어플리케이션에서 오류에 대한 처리를 신경쓰지 않아도 된다.

  - 예시

    - 컴파일 에러(compile error) : 자바 컴파일러가 문법 검사를 통해 발견한 오류

      (ex. 세미콜론 누락, 괄호 불일치, classpath 누락 클래스)

    - 런타임 에러(runtime error) : 실행 과정(Runtime)에 발생하는 오류.

      (ex. NullPointerException, 무한루프, 0으로 나누는 경우)

  <br>

- __예외(Exception)__
  - 개발자가 구현한 로직에서 발생한다.
  - 발생할 상황을 미리 예측하여 처리할 수 있다.
  - 개발자가 처리할 수 있기 때문에 예외를 구분하고 그에 따른 처리 방법을 명확히 알고 적용하는 것이 중요하다.

<br><br>

## [1] 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

### (1) try-catch

1. 예외처리 방법

   ```java
   try {
     //예외 가능성이 있는 코드
   } catch (IllegalArgumentException e1) {
     // IllegalArgumentException 발생 시, 예외 처리 코드
   } catch (ArithmeticException e2) {
     // ArithmeticException 발생 시, 예외 처리 코드
   } catch (NullPointerException e3) {
     // NullPointerException 발생 시, 예외 처리 코드
   }
   ```

   - printStackTrace() : 예외 발생 당시 호출 스택에 있었던 메서드 정보와 예외 메시지를 화면에 출력한다.
   - getMessage() : 발생한 예외 클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.

   <br>

2. try-catch문의 흐름

   ```java
   try {
     //여기에 예외를 발생할만한 코드를 추가한다.
   } catch (발생할 예외01) {
     //여기서, 예외를 발생할 경우
   } catch (발생할 예외02) {
     //여기의 예외는 스킵한다.
   }
   ```

   1. try-catch 블록 처리 시, 첫 번쨰 catch 블록부터 순서대로`instanceof `연산자를 이용해서 검사한다.
   2. 검사 결과가 **true**일 경우, catch 블록 코드를 실행하고 try-catch문을 탈출한다.

   3. 검사 결과가 **false**일 경우, 다음 catch 블록으로 이동한다.

   <br>

3. Multi-catch block (JDK 1.7~)

   ```java
   try {
     //예외가 발생할 수도 있는 코드
   } catch (예외클래스01 | 예외클래스02 참조변수){
     //발생할 예외에 맞는 예외처리 코드
   }
   ```

   - 여러개의 예외를 하나의 catch로 합칠 수 있다.
   - 주의! 나열된 부모 클래스가 `부모-자식` 관계일 경우라면 오류를 발생한다!
   - 단점 : 멀티 캐치 블록 내에서는 발생한 예외가 정확히 어떤 예외인지 확인하기 힘들기 때문에 주로 `공통 조상 클래스`에 대한 정보를 담는다.

<br>

### (2) finally

```java
try {
  //예외 발생 가능성 코드
} catch (발생할 예외 클래스) {
  //해당 예외 발생 시, 진행할 예외 처리 코드
} finally {
  //예외 발생 여부 관계없이 , 무조건 실행할 문장
}
```

- 예외 처리 발생 여부 관계없이 무조건 실행할 코드.

- finally 블록 내 문장은 **try-catch 블록에 return이 존재할지라도 실행**된다.

  ```java
  public class FinallySample {
      public static void main(String[] args) {
          methodName();
      }
  
      public static void methodName() {
          try {
              System.out.println("try 블록 코드");
              return;
          } catch (ArithmeticException e) {
  
          } finally {
              System.out.println("finally 블록 코드");
          }
      }
  }
  
  ```

  <결과>

  ```
  try 블록 코드
  finally 블록 코드
  ```

<br>

### (3) throw

- 고의로 예외를 발생시키는 키워드.
- 사용방법 : 해당 Exception Class 객체 생성

```java
public static void methodName() {
  if(조건1) {
  	throw new IllegalArgumentException("잘못된 입력 값입니다."); 
  }
  
  if(조건2) {
    //클래스에 대한 부정적인 접근
    throw new IllegalAccessException("잘못된 접근 입니다.");
  }
}
```

<br>

### (4) throws

```java
public void methodName() throws 예외클래스1, 예외클래스2,... {
  //메서드 구현 내용
}
```

- 해당 메서드를 사용하는 메서드에세 예외를 처리하도록 `책임을 전가`하는 역할.
- 메서드 선언 부분에 예외를 선언함으로써 **해당 메서드를 사용할경우 어떤 예외를 처리해야 하는지** 알려준다.

<br><br>

## [2] 예외 계층 구조

![](https://madplay.github.io/img/post/2019-03-02-java-checked-unchecked-exceptions-1.png)

(출처 : 오늘도 MadPlay! 블로그)

- Object는 Throwable을 상속하고 있음.
- Throwable 클래스는 Exception & Error를 상속하고 있음.
- RuntimeException은 Exception 클래스의 **서브 클래스**이다.

<br><br>

## [3] Checked & Unchecked Exception

- __Checked Exception__

  - `컴파일 시점에서 확인할 수 있는 예외.`

  - checked exception 발생 시, 반드시 해당 예외를 처리해야 하거나,

    - ex) try - catch

      ```java
          public static void methodName() {
              try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                  br.readLine();
              } catch (IOException ie) {
                  System.out.println("예외 못잡는건 못참지!");
              }
          }
      
      ```

      <br>

  - 코드가 속한 선언부에 예외를 선언해주어야 한다.

    - ex) throws

      ```java
          public static void methodName2() throws IOException {
              BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
              br.readLine();
          }
      ```

      <br>

- __Unchecked Exception__

  - `컴파일 시점에서 확인되지 않는 예외`이다. 

    - 컴파일러가 예외를 처리하거나 선언하도록 강제하지 않는다.

    - 프로그래머가 알아서 처리를 해야 한다.

      

  - 예시 : RuntimeException과 하위 클래스, Error와 하위 클래스

    ```java
    class Main {
      public static void main(String args[]) {
        int x = 0;
        int y = 10;
        int z = y/x;
      }
    }
    
    ```

    ```
    Exception in thread "main" java.lang.ArithmeticException: / by zero
        at Main.main(Main.java:5)
    Java Result: 1
    ```

  <br>

- __Runtime Exception이 예외를 명세하지 않는 이유?__

  - Runtime Exception은 프로그램 코드의 문제로 발생하는 예외아다.
  - 클라이언트 쪽(메서드 호출하는 쪽)에서 이를 복구(or 회복)하거나 대처할 수 있을 거라고 예상하기 힘들다.
  - RuntimeException은 프로그램 어디서나 매우 빈번하게 발생할 수 있기 때문에 모든 Runtime Exception을 메서드에 명시하도록 강제하는 것은 프로그램의 명확성을 떨어뜨릴 수 있다.

<br><br>

## [4] Chained Exception

```java
// Java program to demonstrate working of chained exceptions
public class ExceptionHandling {
    public static void main(String[] args) {
        try {
            // Creating an exception
            NumberFormatException ex =
                       new NumberFormatException("Exception");
  
            // Setting a cause of the exception
            ex.initCause(new NullPointerException(
                      "This is actual cause of the exception"));
  
            // Throwing an exception with cause.
            throw ex;
        }
  
        catch(NumberFormatException ex) {
            // displaying the exception
            System.out.println(ex);
  
            // Getting the actual cause of the exception
            System.out.println(ex.getCause());
        }
    }
}
```

<결과>

```java
java.lang.NumberFormatException: Exception
java.lang.NullPointerException: This is actual cause of the exception
```

- 예외와 예외를 연관시킬 수 있는 기능
- initCause() : 원인 예외를 지정할 수 있는 기능.
  - Throwable 클래스에 정의되어 있어서 모든 예외 클래스에서 사용 가능.

<br><br>

# [5] User-defined Custom Exception

1. 사용자 정의 예외 작성법

   - User-defined exception을 작성하기 위해서는 Exception을 상속받아 exception class를 구현한다.
   - 사용자 정의 예외 클래스 작성 시, 생성자에 문자열을 추가하고 super 클래스에 추가하면 getMessage()를 통해 예외 메세지를 확인할 수 있다.

2. 사용자 정의 예외의 장점

   - 이름으로도 정보 전달이 가능하다.
   - 상세한 예외 정보를 제공할 수 있다.
   - 예외에 대한 응집도가 향상된다.
   - 예외 발생 후, 후처리가 용이하다.
   - 예외 생성 비용을 감소시킨다.

   출처 : [custom exception을 언제 써야 할까?](https://woowacourse.github.io/javable/post/2020-08-17-custom-exception/)

```java
/ / A Class that represents use-defined expception
class MyException extends Exception
{
	public MyException(String s) {
		// Call constructor of parent Exception
		super(s);
	}
}

// A Class that uses above MyException
public class Main {
	// Driver Program
	public static void main(String args[]) {
		try {
			// Throw an object of user defined exception
			throw new MyException("GeeksGeeks");
		}
		catch (MyException ex) {
			System.out.println("Caught");

			// Print the message from MyException object
			System.out.println(ex.getMessage());
		}
	}
}

```

<br><br>

# Reference

- 자바 예외 구분: Checked Exception, Unchecked Exception

  https://madplay.github.io/post/java-checked-unchecked-exceptions

  

- [Java Study 9주차] 예외처리

  https://wisdom-and-record.tistory.com/46

  

- Unchecked Exceptions(runtime exception이 명세하지 않은 이유.)

  https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html

  

- Chained Exceptions in Java (geeks for geek)

  https://www.geeksforgeeks.org/chained-exceptions-java/
  
  

- Java 컴파일에러와 런타임에러 차이.

  https://linked2ev.github.io/java/2019/05/05/JAVA-5.-compileError-vs-RuntimeError/

  

- custom예외는 언제 써야 할까?

  https://woowacourse.github.io/javable/post/2020-08-17-custom-exception/

  
