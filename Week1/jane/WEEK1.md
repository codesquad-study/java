

> ### 학습 목표
자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.


## JVM이란 무엇인가
- Java Virtual Machine
- "Write once, run anywhere"
- JIT Compiler HotSpot JVM등의 도입으로 Native 언어와 유사한 수준의 실행속도 보장
- GC(Garbage Collector)로 메모리 관리

## JVM 구성 요소

### Class Loader
1. 로딩
- BootStrap ClassLoader: 최상위 클래스 로더
- Extension ClassLoader: `jre/lib/ext` 또는 `java.ext.dirs`의 환경 변수로 지정된 폴더에 있는 클래스 파일을 로딩
   - Java9 이후 Platform ClassLoader로 이름 변경
- Application ClassLoader: 어플리케이션 레벨의 클래스(개발자가 직접 작성한 대부분의 클래스)를 로딩
   - Java9 이후 System ClassLoader로 이름 변경

2. 링킹
- verify: 자바 바이트코드 검증, 실패 시 오류 발생
- prepare: static 변수 메모리 할당, 기본값 할당
- resolve: 모든 심볼릭 메모리 참조를 메서드 영역에 있는 타입으로 직접 참조

3. 초기화
- static 변수 값이 자바 코드에 명시된 값으로 초기화
- static block 실행

### Execution Engine
- 로드된 클래스의 바이트코드를 실행하는 런타임 모듈
- 클래스 로더를 통해 런타임 데이터 영역에 배치된 바이트코드를 실행한다.
- Garbage Collector, JIT Compiler 포함
 
 
### Runtime Data Area
- 메서드 영역: 클래스 레벨 정보 저장, 스레드 간 공유됨 (JVM 당 하나의 영역만 존재)
- 힙 영역
   - 동적 영역
   - 모든 인스턴스 저장, 스레드 간 공유됨, GC의 대상
   - Eden(객체가 최초로 할당되는 장소), Survivor, Tenured(오래 사용될 것 같은 객체 저장) , Perm 영역으로 구성
- 스택 영역: 스레드마다 생성(공유 리소스가 아니므로 스레드 안전), 메서드에 관련된 정보가 스택 프레임의 형태로 저장됨
- PC Register: Java 메서드에 대한 위치 기록
- Native Method Stack: 네이티브로 작성된 메서드의 정보 저장, 스레드마다 생성됨

## 자바 컴파일 & 실행 방법
### 컴파일
javac를 이용해 클래스파일을 생성
```
javac Hello.java
```


### 실행
`java <package>.<classname>`로 실행
```
java package_name.Hello
```

💡 상위 버전의 바이트 코드는 하위버전의 자바 프로그램을 실행할 수 없다. 
&rarr; Java 14 버전으로 컴파일하고, Java 8 프로그램을 돌리면 `UnsupportedClassVersionError`가 발생한다.


### 명령어
- javac.exe(컴파일러):	자바소스코드를 바이트코드로 컴파일
- java.exe(인터프리터):	컴파일러가 생성한 바이트코드를 해석하고 실행
- javap.exe(역어셈블러):	컴파일된 클래스파일을 원래의 소스로 변환(디컴파일)


### 자바 컴파일러 옵션
- source와 target 버전 지정 가능
- https://stackoverflow.com/questions/15492948/javac-source-and-target-options



## 바이트코드
- JVM이 이해할 수 있는 코드, 즉 JVM 언어를 컴파일 한 결과로 나오는 코드
- 프로그래밍을 할 때 java가 아닌 kotlin, Scala 등 다른 언어로 짜더라도 JVM이 이해할 수 있는 바이트코드를 만들 수 있다면 실행할 수 있다.
- JVM만 있으면 운영체제와 사오간없이 실행 가능



## JIT 컴파일러
- Just In Time의 약자
- 자바로 프로그램을 실행할 때 라인 바이 라인으로 인터프리터가 바이트 코드를 기계가 이해할 수 있도록 번역을 하는데, 반복되는 코드는 jit 컴파일러가 기계어로 번역해서 캐싱을 해놓고 재사용한다. 
   - JIT 컴파일러는 런타임 시 바이트코드를 기계어로 한 번에 컴파일 후 캐싱해뒀다가, 이후 변경된 부분만 컴파일하고 나머지 부분은 캐시에서 가져와 바로 실행한다고 이해하면 된다. 
   - JIT 컴파일러를 사용하는 JVM은 내부적으로 해당 메서드가 얼마나 자주 수행되는지 체크하고 일정 정도를 넘을 때만 컴파일을 수행한다. 
- JIT 컴파일러는 클라이언트 컴파일러와 서버 컴파일러로 구분되는데, java8 버전부터는 옵션을 주지 않아도 tiered compilation라는 기본 옵션(client와 server를 번갈아가며 사용)이 적용된다.
   - 처음에는 클라이언트 컴파일러를 사용하다가 호출의 수가 증가하게 되면 C2 사용

### 컴파일 타임(javac를 실행할 때 발생) vs. 런타임(애플리케이션 실행 중에 발생)
💡 언제 에러가 나는게 더 좋을까?
&rarr; 컴파일 타임이 좋다. 



## JDK vs. JRE
- JDK: Java Development Kit
   - 자바 개발자 도구, JRE 포함
   - Javac는 JDK에 들어있다. 
- JRE: Java Runtime Environment
  - 자바 애플리케이션 구동에 필요한 요소들이 담겨있음
  - 자바 9버전부터는 JRE가 없다. 
  
 
 
 

 
 
