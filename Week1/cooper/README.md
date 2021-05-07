# Week1. JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.

## 1. JVM이란 무엇인가

JVM(Java Virtual Machine)은  자바를 실행시키기 위한 자바 가상 머신이다. 가상머신이란 물리적으로 존재하는 컴퓨터가 아닌, 컴퓨터 내부에 **소프트웨어적**으로 만들어낸 또 하나의 가상의 컴퓨터를 말한다. JVM은 자바 프로그램을 실행시키기 위해 필요한 환경을 제공해주는 프로그램이며 다양한 OS에 상관없이 Java를 실행시킬 수 있어 자바 프로그램이 **플랫폼 독립적** 일 수 있다. 하지만 각각의 운영체제에 맞는 JVM을 설치해야 하므로 JVM 자체는 운영체제에 종속적이다.

<br><br>

## 2. 컴파일 하는 방법

```
Source Code(.java) ---[javac.exe(comiler)]---> Java Byte Code(.class)
```

- 컴파일(Compile)
  - 소스코드를 가상머신이 이해할 수 있는 바이트 코드로 번역하는 일련의 과정을 일컫는 말이다.
  - 자바 컴파일 방법 : ```javac [program file]``` 을 선언한다.
  - javac : Source Code(.java)를 바이트코드(.class)fh 번역하는 compiler

<br><br>

## 3. 실행하는 방법

```
Byte Code(.class) -[java.exe(interpreter)]-> Run 
```

- 실행(execution)
  - 자바 실행 방법 : ```java [argument]`[program file]```
  - java : 자바 인터프리터, 바이트코드(.class)를 실행시킨다.
  - 인터프리터(interpreter) : 소스코드를 실행시키는 컴퓨터 프로그램

<br><br>

## 4. 바이트코드란 무엇인가

 바이트코드는 JVM(Java Virtual Machine)이 이해할 수 있는 언어로 변환된 자바 소스 코드를 의미한다.  자바 컴파일러에 의해 변환되는 코드의 명령어의 크기가 1바이트라서 자바 바이트 코드라고 불린다. 자바 바이트 코드의 확장자는 .class이며, JVM만 설치되어 있다면, 어떤 운영체제에서라도 실행될 수 있다.

<br>

### (+자바 바이트 코드 출력 방법)

1. javap(역어셈블러)

   - 역어셈블러(disassembler) : 기계어를 어셈블리어로 변환하는 컴퓨터 프로그램

   - ``` java -c [target].class```
   - Ex) java -c Hello.class (-c : 역어셈블한 코드 출력하는 옵션)

<br><br>

## 5. JIT 컴파일러란 무엇이며 어떻게 동작하는지

**인터프리터**는 바이트 코드 명령어를 하나씩 읽어 해석 및 실행하며 해석이 빠른 장점이 있었다. 하지만** 전체적인 프로그램 실행 속도는 느리다**는 단점이 존재했다.

**JIT(Just-In-Time) 컴파일러**는 프로그램이 실행 중인 런타임에 실제 기계어로 변환해주는 컴파일러이다. 프로그램의 실행 속도를 향상시키키 위해 개발되었으며 동적 번역(dynamic translation)이라고 불리며 즉, JIT 컴파일러는 자바 컴파일러가 생성한 자바 바이트 코드를 런타임에 기계어로 변환하는데 사용한다.

JIT 컴파일러는 인터프리터의 단점을 보완한다.  **바이트 코드 전체를 컴파일해 네이티브 코드(원시 기계 코드)로 변경**하기 때문이다. JIT 컴파일러는 기본적으로 **해당 메서드를 호출할 때 활성화** 된다.  해당 메서드 호출 시, 컴파일된 코드를 직접 호출해 네이티브 코드를 직접 실행한다. 바이트 코드 전체가 컴파일된 네이티브 코드를 실행하는 것이기 때문에 전체적인 실행 속도는 인터프리팅 방식보다 빠르다.

- 네이티브 코드 
  - 운영체제(OS)가 직접 실행할 수 있는 코드(원시 기계 코드)
  - 네이티브 코드는 캐시에서 보관한다. 한 번 컴파일된 코드를 캐시에서 바로 실행하므로 속도가 빠르다.

하지만 무조건적으로 JIT컴파일러는 사용하는 것은 아니다. JVM은 내부적으로 **해당 메서드의 호출 및 실행 횟수**를 체크하고 일정 기준이 넘었을 경우에만 JIT 컴파일러를 사용한다.

오라클 핫스팟 VM의 핫스팟 컴파일러의 경우, 내부적으로 프로파일링을 통해 가장 컴파일리 필요한 부분(Hot Spot)을 찾아 컴파일한다. 그리고 만약 컴파일된 바이트 코드일지라도 해당 메서드의 호출이 적다면 캐시에서 네이티브 코드를 제거하고 인터프리터 모드로 동작한다.



## 6.  JVM 구성 요소

![JVM In JRE](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F990BF73B5B73A02817)

### 1. Class Loader

- 자바 컴파일러에 의해 바이트 코드로 변환된 클래스를 읽어들여 Runtime Data Area에 적재하는 역할을 한다.

  - **BootStrap ClassLoader **
    - 가장 최우선적으로 로드한다.
    -   jre/lib/rt.jar 파일을 통해 기본 자바 API 라이브러리를 로드한다.
  - **Extension ClassLoader**
    - jre/lib/ext 디렉토리의 모든 확장 코어 클래스 파일을 로드한다.
    -  로드하는 위치는 JDK 확장 디렉토리 (JAVA_HOME/lib/ext/) 또는 java.ext.dirs에 저장된 경로이다.
  - **Application ClassLoader**
    - 시스템 클래스 로더라고 불리기도 하며 사용자가 직접 정의한 클래스 파일들을 로드한다.
    - classpath 환경 변수에 있는 클래스 파일이나 -classpath 또는 -cp 명령어 옵션이 있는 파일들을 로드한다.

  <br>

### 2. Runtime Data Area

- JVM의 메모리 영역으로 자바 어플리케이션을 실행할 때 사용하는 데이터를 적재하는 영역이다.

  - **Method 영역** 

    - 클래스 정보가 저장되는 공간이다. 클래스 메서드 정보, 멤버 변수 이름과 타입, 메서드에 관한 정보(메서드 이름, 파라미터, 리턴값)를 저장한다.

    - static 메서드, final 클래스 변수를 저장하며 JVM당 하나의 메서드 영역을 가지고 있다.

  - **Heap 영역**

    - ```new키워드```로 생성된 인스턴스 또는 객체를 관리하는 공간으로 GC의 대상이다.
    - ```Method영역```에 생성된 클래스만 성상 가능하고, GC(Garbage Collector)에 의해 사용하지 않는 클래스/배열을 제거한다.

  - **Stack 영역**

    - 모든 메서드 호출에 대해 **스택 프레임**이라는 스택 메모리의 하나이 항목이 만들어진다.
    - 스택 영역은 공유 리소스가 아니므로 스레드로부터 안전하다.

  <br>

### 3. Execution Engine

-  ```Class Loader```에 의해 ```Runtime Data Area```에 적재된 **바이트 코드(.class)를 기계어로 변환**하고 실행하는 역할을 한다.

- 명령어는 Interpreter 또는 JIT Compiler 중 적절한 것으로 바이트 코드를 네이티브 코드로 변환 및 실행한다. (윗 내용 참고)

   <br>

## 7. JDK와 JRE의 차이

```text
- JRE(Java Runtime Enviroment) : JVM이 자바 프로그램을 동작시키기 위한 특정 라이브러리 파일과 기타 파일.

- JDK(Java Development Kit) : 자바 프로그래밍을 위한 자바 컴파일러(javac) + 인터프리터(java)를 포함.
```

<br><br>

## Reference

- TCP School : http://www.tcpschool.com/java/java_intro_programming

- JVM의 동작원리 및 기본개념 : https://steady-snail.tistory.com/67
- JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가?(읽는거 추천) : https://cbw1030.tistory.com/289
- JVM 구성 요소 : https://www.skyer9.pe.kr/wordpress/?p=280
- 클래스 로더 참고 차료 : https://sas-study.tistory.com/262