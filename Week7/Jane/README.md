## 패키지
- 클래스의 묶음
- 서로 다른 패키지에는 같은 이름의 클래스가 존재할 수 있다.
- 클래스의 full name은 패키지 이름을 포함하고 있다.
- 패기지는 다른 패키지를 포함할 수 있다.

>
>1) java.lang.String 클래스는 java/lang 디렉터리에 속한 String.class 파일이다.
>2) System클래스 역시 java/lang 디렉토리에 속해있다.

### 패키지 선언
- 클래스명과의 구분을 위해 패키지명은 소문자로 적는 것을 원칙으로 한다.
- 패키지 이름은 java로 시작할 수 없다.
- 자바의 예약어를 사용할 수 없다.
- 패키지 선언은 주석과 공백을 제외한 소스 파일의 첫 문장이어야 한다.
- 패키지를 선언하지 않는다면 자바에서 기본적으로 제공하는 unnamed package 안에 클래스가 생성된다.

```java
package com.codechobo.book;
```

---

## import문
- import문으로 패키지를 미리 명시하면 패키지명을 붙이지 않아도 다른 패키지의 클래스를 사용할 수 있다.
- import문은 package 선언 다음, 클래스 선언 이전에 위치해야 한다.
```java
import 패키지명.클래스명;
import 패기지명.*;
```
- 같은 패키지나 java.lang 패키지는 import하지 않아도 알아서 불러와진다.

### static import문
- import문 앞에 static 키워드를 붙이면 static 멤버를 호출할 때 클래스 이름을 생략할 수 있다.
```java
import static java.lang.System.out;
import static java.lang.Math.*;

class Main {
    public static void main(String[] args) {
        out.println(random());
        out.println("Math.PI :" + PI);
    }
}
```
- 실행 결과
![](https://images.velog.io/images/janeljs/post/0825fa32-ce09-4fa4-bb5a-7cdc56f37769/image.png)

---

## 클래스 패스
- JVM 또는 Java 컴파일러가 사용하는 파라미터
- 패키지의 root directory를 classpath에 포함시켜야 JVM이 패키지 내 클래스를 찾을 수 있다.
- `;`를 구분자로 여러 개의 경로를 클래스 패스에 지정할 수 있다.

### CLASSPATH 환경변수
- 시스템 변수 설정을 통해 지정
- JVM이 시작될 때 JVM 클래스 로더가 classpath 환경변수 디렉토리에 있는 클래스들을 먼저 JVM에 로딩한다.

### classpath 옵션
- 컴파일 시 참조할 클래스 파일들의 경로를 지정해주는 옵션

```
javac <options> <souce files>
javac -classpath 파일 경로
```
---


## 제어자(modifier)
- 클래스, 변수 또는 메서드 선언부에 함께 사용되어 부가적인 의미를 부여한다.
- 접근 제어자: public, protected, default, private
  - 접근 제어자는 한 번에 한 가지만 사용할 수 있으며 주로 제일 왼쪽에 위치한다.
- 기타: static, final, abstract, native, transient, synchronized, volatile, strictfp

### 접근 제어자(access modifier)
- 멤버 또는 클래스에 대한 외부의 접근을 제한하는 역할을 한다.
- 목적
   - 클래스 내부에 선언된 데이터의 보호
   - 내부적으로만 사용되는 멤버변수나 메서드 등을 클래스 내부에 은닉
- 멤버 변수는 **상속이 예상된다면 protected**, 아니라면 **private**으로 선언하고 getter, setter를 통해 접근하도록 정의한다.
- 접근 제어자의 종류
  - private: 같은 클래스 내에서만 접근 가능
  - default: 같은 패키지 내에서만 접근 가능
  - protected: 같은 패키지 또는 다른 패키지의 자손 클래스에서 접근 가능
  - public: 접근 제한 없음
> **public > protected > default > private**


|                   | 해당 클래스 내 | 같은 패키지 내 | 상속받은 클래스 | import 한 클래스 |
| ----------------- | -------------- | -------------- | --------------- | ---------------- |
| public            | O              | O              | O               | O                |
| protected         | O              | O              | O               | X                |
| (package private) | O              | O              | X               | X                |
| private           | O              | X              | X               | X                |




### static
- 뜻: 클래스의, 공통적인
- static 멤버변수, 메서드, 초기화 블럭은 인스턴스 생성 없이도 사용할 수 있다.
- static 멤버변수
  - static 멤버변수는 클래스가 메모리에 로드될 때 생성된다.
  - static 멤버변수(클래스 변수)는 모든 인스턴스 사이에 공유되기 때문에 인스턴스와 관계 없이 동일한 값을 지닌다.
- static 메서드
  - static 메서드 내에서는 인스턴스 멤버들을 직접 사용할 수 없다.
- static 초기화 블럭
  - static 초기화 블럭은 맨 처음 static을 호출하는 상황에서 자동으로 초기화되며 단 한 번만 호출된다.
```java
class Main {
    public static void main(String[] args) {
        System.out.println(Circle.PI);
    }
}

class Circle {
    static float PI;
    int r;

    static {
        PI = 3.14f;
    }
}
```
- 실행 결과
![](https://images.velog.io/images/janeljs/post/e6ef28ea-dac1-4cea-b87a-d6381f536d04/image.png)

### final
- 뜻: 마지막의, 변경될 수 없는
- String과 Math는 대표적인 final 클래스이다.

| 대상               | 의미                                                         |
| ------------------ | ------------------------------------------------------------ |
| 클래스             | 변경할 수 없는 클래스, 확장할 수 없는 클래스 &rarr; final 클래스는 다른 클래스의 조상이 될 수 없다. |
| 메서드             | 변경할 수 없는 메서드 &rarr; final 메서드는 오버라이딩이 불가하다. |
| 멤버변수, 지역변수 | final 변수는 값을 변경할 수 없는 상수이다.                   |

### abstract
- 뜻: 추상의, 미완성의
- 추상 클래스: 추상 메서드를 포함한 클래스
- 추상 메서드: 선언부만 작성하고 구현부는 작성하지 않은 메서드
- 추상 클래스는 미완성인 메서드가 존재하는 '미완성 설계도'이므로 인스턴스를 생성할 수 없다.
```java
abstract class AbstractTest { 
    abstract void move();
}
```

### transient
  -  Serialize하는 과정에 제외하고 싶은 경우 선언하는 키워드
  - 클래스 내의 보안 정보(암호, 키 등) 등에 지정

### synchronized
  - 메서드에 한 번에 한 개의 쓰레드만 접근 가능하도록 설정
### volatile
  - 변수를 메인 메모리에 저장히겠다고 명시
  - 변수의 값을 읽어올 때 CPU 캐시가 아닌 메인 메모리에서 값을 읽어옴
  - 멀티 쓰레드 환경에서 변수 값 불일치 문제를 방지할 수 있음
  - 하나의 쓰레드만 read&write가 가능하고, 나머지 쓰레는 read만 가능한 상황에서 최신값 보장 가능


---

***Source***
- 자바의 정석 (남궁성 저)