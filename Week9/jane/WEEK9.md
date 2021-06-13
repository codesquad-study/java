# 예외 처리

## 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

- 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하기 위해 프로그램 실행 시 발생할 수 있는 예외에 대비한 코드를 작성하는 것

## throw
- 연산자 new를 이용해서 예외 클래스의 객체를 만든 다음 키워드 throw를 이용해서 고의로 예외를 발생시킬 수 있다.
```java
class Main {
    public static void main(String[] args) {
        try {
            Exception e = new Exception("throw로 예외 강제 발생");
            throw e;
            // throw new Exception("예외");
        } catch (Exception e) {
            System.out.println("에러 메시지 : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```
- 실행 결과![](https://images.velog.io/images/janeljs/post/c05197d7-3f84-454e-ad08-1c53e4b61d49/image.png)


## throws 

```java
public static void main(String[] args) throws IOException { ... do something }
```
- do something 부분 실행에 실패하면 caller에게 예외가 전달되고 caller가 예외를 처리한다 
&rarr; 메서드를 소환한 코드가 예외를 처리하게 된다. 
- 메서드를 사용할 때 발생할 수 있는 예외를 미리 명시하기
	&rarr; 사용자가 미리 예외를 인지하고 그에 대한 처리를 강제할 수 있다.
## try/catch

```java
try { // 예외를 처리하길 원하는 실행 코드 }

catch (e1) { // e1 예외가 발생할 경우에 실행될 코드 } 
catch (e2) { // e2 예외가 발생할 경우에 실행될 코드 } 

...
finally { // 예외 발생 여부와 상관없이 무조건 실행될 코드 }
```
1. `try{ ... }`:  예외가 발생하면 catch블록에서 처리, 예외가 발생하지 않는다면 바로 finally블럭으로 이동 
2. `catch (e1) { ... }`
   - 적절한 catch블럭을 찾지 못하면 예외 처리 불가 -> 프로그램 강제 종료 
   - 적절한 catch블럭을 찾으면 throw 문의 피연산자를 예외 객체의 형식 매개변수로 전달 
3. `finally{ ... }`: 모든 예외처리가 끝나면 finally블럭으로 이동

>- 다른 제어문과는 달리 예외 처리문은 중괄호({})를 생략할 수 없다.
- 모든 예외 클래스는 Exception 클래스의 자손이므로, `catch (Exception e)`와 같이 선언해 놓으면 어떠한 종류의 예외가 발생하더라도 이 catch 블럭에 의해 처리된다. 
- try블럭 안에 return문이 있어서 try블럭을 벗어나더라도 finally블럭은 실행된다.


```java
public static void main(String[] args) {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	try {
		StringTokenizer st = new StringTokenizer(br.readLine());
	} catch (IOException e) {
		e.printStackTrace(); // 참조변수 e를 통해, 생성된 IOException인스턴스에 접근
	}
}
```
- try문 안의 내용 실행에 실패하면 caller에게 예외가 전달되는 것이 아니라 catch문을 통해 예외를 처리하게 된다. 
- `e.printStackTrace()`: 예외 발생 당시 호출스택(Call Stack)에 있었던 메서드의 정보와 예외 메시지를 화면에 출력한다.

> `getMessage()`: 발생한 예외클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.



### 자바가 제공하는 예외 계층 구조

![img](https://images.velog.io/images/janeljs/post/6f8be533-a45c-4b85-a181-04661796b3dd/image.png)



### 1. RuntimeException 클래스와 그 자손들 
- 프로그래머의 실수로 발생하는 예외
   - e.g. ArrayIndexOutOfBoundsException, NullPointerException, ClassCastException, ArithmeticException
- 치명적인 예외 상황을 발생시키지 않는 예외들로 구성  
- unchecked 예외 &rarr; 컴파일 가능
- try/catch문을 사용하기보다는 예외에 신경쓰며 프로그램 작성하기   

### 2. 그 외의 Exception 클래스와 그 자손들
- 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
  - e.g. FileNotFoundException, ClassNotFoundException, DataFormatException
- checked 예외 &rarr; 예외를 처리하지 않으면 컴파일 시 오류 발생 
- 아래의 코드는 readLine()에서 발생할 수 있는 IOException에 대한 예외를 처리해주지 않았으므로 컴파일 시 오류가 발생한다.
```java
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 	
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		... 
	}
}
```

- `throws IOException`을 통해 예외를 처리해주거나 `try/catch문`을 통해 예외 처리를 해 주면 된다. 

> 사용자 정의 예외 클래스를 만들 때 기존에는 Exception 클래스를 상속받아 checked 예외를 작성하곤 했지만, 요즘은 RuntimeException 클래스를 상속받아 선택적인 예외처리를 허용하는 방향으로 변화하고 있다. 





### Exception과 Error의 차이는?

- 오류(error): 프로그램 코드에 의해서 수습될 수 없는 심각한 오류 
  &rarr; 시스템 레벨에서 프로그램에 심각한 문제를 야기하여 실행 중인 프로그램을 종료시킨다.  
  - e.g. OutOfMemoryError, StackOverflowError
- 예외(exception): 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류 
&rarr; 실행 중인 프로그램을 비정상적으로 종료시키지만, 개발자가 발생할 수 있는 상황을 미리 예측하여 처리할 수 있다.





### RuntimeException과 RE가 아닌 것의 차이는?

### 1. RuntimeException 클래스와 그 자손들 
- 프로그래머의 실수로 발생하는 예외
   - e.g. ArrayIndexOutOfBoundsException, NullPointerException, ClassCastException, ArithmeticException
- 치명적인 예외 상황을 발생시키지 않는 예외들로 구성  
- unchecked 예외 &rarr; 컴파일 가능
- try/catch문을 사용하기보다는 예외에 신경쓰며 프로그램 작성하기   

### 2. 그 외의 Exception 클래스와 그 자손들
- 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
  - e.g. FileNotFoundException, ClassNotFoundException, DataFormatException
- checked 예외 &rarr; 예외를 처리하지 않으면 컴파일 시 오류 발생 
- 아래의 코드는 readLine()에서 발생할 수 있는 IOException에 대한 예외를 처리해주지 않았으므로 컴파일 시 오류가 발생한다.
```java
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 	
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		... 
	}
}
```

- `throws IOException`을 통해 예외를 처리해주거나 `try/catch문`을 통해 예외 처리를 해 주면 된다. 

> 사용자 정의 예외 클래스를 만들 때 기존에는 Exception 클래스를 상속받아 checked 예외를 작성하곤 했지만, 요즘은 RuntimeException 클래스를 상속받아 선택적인 예외처리를 허용하는 방향으로 변화하고 있다. 





### 커스텀한 예외 만드는 방법

사용자 정의 예외는 보통 RuntimeException을 상속함으로써 만들 수 있다. 
(RuntimeException은 Exception의 서브클래스이고, Exception은 Throwable의 서브클래스이고, Throwable은 Serializable 인터페이스를 구현하고 있는데, Serializable은 직렬화 가능 여부를 보여주는 마커 인터페이스로 구현해야 하는 메서드는 없다.)

```java
public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super("해당 번호의 질문이 존재하지 않습니다.");
    }
}
```

RuntimeException 안에 들어가보면 아래와 같은 기본 생성자들을 확인할 수 있는데, 이번에 브라이언의 테크톡을 들으며 Throwable cause를 매개변수로 받는 생성자 또한 유용하게 사용할 수 있다는 것을 알게되었다. 

```java   
    public RuntimeException() {
        super();
    }
    
    public RuntimeException(String message) {
        super(message);
    }

    public RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
```
Throwable 매개변수는 언제 사용하면 좋을까?

지금까지 설계했던 애플리케이션은 복잡하게 연결된 예외가 없었어서 Throwable을 사용할 필요가 없었다. 그러나 애플리케이션의 복잡도가 높아지고 exception 간 맵핑이 생기면 상위 객체로 예외 처리의 책임을 위임하는 상황이 발생한다.

예를 들어 예외 A와 예외 B가 있고, 예외 A가 예외 B를 발생시킨다고 가정하자. 이 때, 예외 A를 cause exception으로 등록한다면 chained exception이 발생했을 때 root cause를 수월하게 찾을 수 있다.

쉽게 이해할 수 있도록 예제 코드를 작성해 보았다.

```java
public class ChainedExceptionExample {
    public static void main(String[] args) {
        try {
            checkCandy();
        } catch (CandyNotFoundException e) {
            throw new CryingBabyException("아이가 울어요.", e);
        }
    }

    static void checkCandy() {
        throw new CandyNotFoundException("사탕이 없어요.");
    }
}

class CryingBabyException extends RuntimeException {
    public CryingBabyException(String message) {
        super(message);
    }

    public CryingBabyException(String message, Throwable cause) {
        super(message, cause);
    }
}
```
위와 같이 사탕이 없다면 아기가 우는 예외가 발생한다고 가정해보자.
CryingBabyException내부에 Throwable cause를 매개변수로 받는 생성자를 정의했기 때문에 CryingBabyException이 발생했을 때 CandyNotFoundException을 cause로 등록해줄 수 있다.

코드를 실행하면 아래와 같이 원인 예외가 명확하게 나온다.
![](https://images.velog.io/images/janeljs/post/60fc9a45-80db-40e3-8e79-770c21476cf9/image.png)

