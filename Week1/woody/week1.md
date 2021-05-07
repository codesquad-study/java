## [Week1] JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가



### 학습 내용

- JVM이란 무엇인가

- 컴파일 하는 방법

- 실행하는 방법

- 바이트코드란 무엇인가

- JIT 컴파일러란 무엇이며 어떻게 동작하는지

- JVM 구성 요소

- JDK와 JRE의 차이

  

### **JVM(\**Java Virtual Machine\**)이란 무엇인가**

자바 바이트 코드를 실행시키기 위한 가상의 기계다. JVM은 Write once, run anywhere 즉, 한 번 작성한 코드라면 어떤 플랫폼 아래에서도 실행할 수 있도록 만들어졌다. 컴파일된 자바 바이트 코드와 OS 사이서 JVM이 중간 번역 역할을 해주기 때문에 Java 코드와 OS와의 직접 적인 상호작용이 사라지기 때문이다.

반면, 다른 언어의 경우, 컴파일 단계에서 OS에 종속적인 머신코드 혹은 목적 코드가 만들어지게 되므로 OS를 변경하게 될 경우 프로그램에도 파급효과가 있을 수 있다.

(단, JVM 자체는 OS에 종속적이다, JRE나 JDK 설치 시 OS 버전 별로 인스톨러가 별개인 이유도 이 때문이다)



### 컴파일 하는 방법

JRE에 있는 자바 컴파일러(javac.exe)를 통해 자바 바이트 코드(.class)로 컴파일 해준다.

```json
javac <options> <source files>
javac sourceFile.java
```



### **실행하는 방법**

컴파일된 sourceFile.class 혹은 압축파일 jar을 실행시킨다.

```json
java <options> <classfiles> <argument>
java <options> -jar file.jar <argument>
java sourceFile
```



### **바이트코드란 무엇인가**

소스코드(일반적으로 우리가 작성하는 고급언어, java 등)와 머신코드의 중간 코드 형태로 JVM과 같은 인터프리터에 의해 실행된다. 자바에서는 바이트 코드의 확장자가 .class이다.



(+) 그럼 목적 코드(Object code)는 뭐지??

- 컴파일러나 어셈블러에 의해서 생성된 코드를 말한다. 바이트 코드보다 한 단계 위에 있는 개념

  

### **JVM 구성 요소**

**ClassLoader Subsystem**

1. Loading

   자바 프로그램이 실행될 때 클래스 전부를 한꺼번에 로딩하지 않는다. 해당 클래스가 필요한 시점이 되었을 때 (동적으로) 클래스 정보(FQCN)를 통해 해당 클래스 파일을 찾고 바이트 코드로 읽어서 메모리에 로딩

   ＊FQCN : 클래스가 속한 패키지명을 모두 포함한 이름을 말한다.

2. Linking

   - Verify : .class 파일이 유효한지 확인
   - Prepare : 클래스 변수(정적 변수)를 위해서 미리 메모리를 디폴트 값으로 할당한다.
   - Resolution

3. Initialization

   - 모든 static 변수를 할당(original values, not default value)하고 static block을 실행한다.

**Runtime Data Area**

자바 바이트 코드를 실행하기 위한 메모리 공간

1. Method Area

클래스 로더에서 읽어온 클래스 정보들을 파싱해서 저장하는 곳이다. 모든 스레드들이 공유하는 메모리 영역이다. 클래스, 인터페이스, 필드 변수, 정적 변수 등이 저장된다.

2. Heap

프로그램을 실행하면서 **동적으로 할당되는 데이터**들의 영역이다. 프로그램 실행 후 생성된 객체 인스턴스 혹은 배열을 저장한다. Method Area와 마찬가지로 Heap Area 또한 모든 스레드가 공유하는 영역이다.

- Heap 크기가 부족해서 Java Object를 Heap에 할당하지 못할 경우 → `OutOfMemoryError` 발생

⭐️ 모든 스레드가 공유하는 영역 ==  멀티 스레드 환경 == thread-safe 하지 않다

3. PC(Program Counter) 레지스터

각 스레드에서 다음에 실행될 명령어 주소를 **스레드 별로 저장**(스레드 실행시 어디부터 실행해야하는지 가리키는 포인터 같은 존재)

4. Stack(call stack)

**각 스레드별로 하나씩** 런타임 스택 영역을 할당받는다. 스택 영역 내부에는 지역 변수, 참조 변수와 같이 다른 스레드와 공유하지 않는 자원들이 저장된다. 메서드 호출 시 스택 프레임이 하나씩 생성되어 스택에 쌓이다가 메서드가 종료되면 해당 스택 프레임이 스택에서 제거된다.

- 쓰레드가 허락된 스택 용량보다 많은 계산을 필요할 경우 → `StackOverflowError` 발생

5. Native method stacks

마찬가지로 **각 스레드별로 하나씩** 존재한다.

- 자바 바이트 코드가 아닌 언어로 작성된 메서드를 위한 스택

☝ 쓰레드 마다 존재하는 PC(Program Counter), Stack, Native method stacks



**Execution Engine**

1. Interpreter

   자바 바이트 코드를 한 줄 씩 실행시킨다. 실행을 하면서 컴파일 과정이 같이 진행되기 때문에 전체 프로그램 실행 속도가 느리다는 단점이 있다.

   - Compiler VS Interpreter

     우리가 코드를 동작시키기 위해서는 크게 '번역(compile)'과 '실행(execution / run)'이라는 두 가지 작업을 거쳐야 한다.

     컴파일러의 경우

     모든 코드를 어셈블리와 같은 목적코드로 번역하는 과정을 거치고, 프로그램을 실행할 때는 이미 앞서 컴파일된 목적 코드를 실행시키면 된다. 모든 코드를 한 번에 컴파일하는 과정에서 시간이 오래걸리지만, 그만큼 프로그램 실행 시간은 단축된다.

     반면, 인터프리터의 경우

     런타임에 한 줄 씩 읽으면서 번역과 실행을 동시에 진행하게 된다. 프로그램 실행을 위해서는 실행 뿐만 아니라 번역을 하는 시간까지 소요되기 때문에 느려질 수 밖에 없다. 또 모든 코드를 분석하고 번역하는 컴파일러는 반복되는 코드에 대한 최적화가 가능한 반면 인터프리터는 반복되는 코드를 계속 디코딩해야하는 단점이 있다. 대신 한 줄 씩 실행하기 때문에 좀 더 유연한 디버깅이 가능하다. (파이썬이 디버깅 도중에 값을 바꿔도 정상적으로 실행되는 이유)

     

2. JIT 컴파일러

- (주의) "런타임 환경"에서 컴파일된 자바 바이트 코드(.class)를 기계어(native code)로 번역하기 위해 사용되는 컴파일러!

JIT 컴파일러는 런타임 실행 시 한땀한땀 해석하고 실행시키는 인터프리터의 단점을 보완한다. 원래  Execution Engine은 기본적으로 인터프리터를 통해 바이트 코드로 변환하지만, 일부 반복되는 메소드(코드)가 나타날 경우에는 JIT 컴파일러에게 해당 부분의 자바 바이트 코드를 한꺼번에 Native Code로 번역을 요청해서 성능을 높인다.

3. Garbage Collector

동적으로 할당된 메모리 영역 중에서 더 이상 참조되지 않는 영역을 deallocate



### **JDK와 JRE의 차이**

**JRE ( Java Runtime Environment )**

자바 프로그램 실행에 필요한 요소들이 들어있다. JVM, 필수 라이브러리(rt.jar), JIT 컴파일러 등으로 구성되어 있다.

**JDK ( Java Development Kit ) = JRE + 개발에 필요한 툴**

자바 개발을 위해 필요한 컴파일러(javac.exe), 인터프리터(java.exe), 디버거 등이 들어있다. JDK 안에 JRE도 포함되어있다.

(+) 그래서 자바 개발을 하고 싶은 사람들은 JDK를, 자바 프로그램을 실행만 시키고 싶다면 JRE를 설치하면 된다. 우리가 jar 파일을 실행시킬 때는 사실 jre만 설치하고 실행해도 무방하다.([참고](https://stackoverflow.com/a/12261192))



### 참고 자료

JVM 관련 자료

http://tcpschool.com/java/java_intro_programming

https://dzone.com/articles/jvm-architecture-explained

https://hongsii.github.io/2018/12/20/jvm-memory-structure/

https://icarus8050.tistory.com/56

바이트 코드

https://medium.com/@rahul77349/machine-code-vs-byte-code-vs-object-code-vs-source-code-vs-assembly-code-812c9780f24c