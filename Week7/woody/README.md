

# [Week7] 패키지 

## 학습할 것 (필수)

- package 키워드
- import 키워드
- 클래스패스
- CLASSPATH 환경변수
- -classpath 옵션
- 접근지시자



## 1. Package

### 개념

A package in Java is used to group related types of classes, interfaces or sub-packages.

➡️ 관련된 클래스, 인터페이스 혹은 서브 패키지를 구분짓는 개념!

**package keyword** is used to create a package in java

➡️ 자바에서는 아래와 같이 `package` 키워드를 통해 패키지를 생성할 수 있다.

```java
//save as Example.java
package mypackage;  
public class Example {  
 public static void main(String args[]){  
    System.out.println("Welcome to package");  
   }  
}  
```

Every Java class must belong to a package. If the `package` statement is omitted, the class belongs to the so-called *default package*, with no sub-directory structure.

➡️  모든 자바 클래스는 패키지에 속해야 한다. 만약 패키지를 지정하는 구문이 빠졌다면 자동적으로 디폴트 패키지에 속하게 된다(추천하는 방법은 X)

### How to run java package program

You need to use **FQCN**(Fully Qualified Class Name) to run the class. 

**To Compile:**  javac -d . Example.java 

	- `-d` == 클래스 파일을 어디다 둘지(목적지) 컴파일러에게 전달하는 명령
	- `.`  == 현재 폴더 위치

**To Run:**  java mypackage.Example



⭐️ **FQCN**(Fully Qualified Class Name) = 패키지 이름 + 클래스 이름

- 자바 뿐만 아니라 어떤 언어를 쓰던 자주 등장하는 단어
- 완전한 클래스 이름을 표현하는 
- 예를 들어 `String` 은 클래스 이름이고 패키지 명은 `java.lang` 이기 때문에 FQCN은 `java.lang.String` 이라고 한다.



### 장점

- 중복된 이름을 피하기 위해서( 유지보수에도 굳! )

- 패키지 단위로 접근 제어를 할 수 있다( 덕분에 data encapsulation 가능! ) 



#### ✨ API vs 라이브러리 차이?

The main difference is that the library refers to the code itself, while API refers to the interface.

- `api` : 컴포넌트를 사용하는 규약, 호출을 위한 수단, 구현 로직 없음
- `라이브러리`: 컴포넌트 자체, 구현 로직 존재



#### 패키지의 종류

Packages are divided into two categories:

1. built-in package ( packages from Java API )
2. user-defined package (create your own packages )



#### Built-in Package

자바 개발자들을 위해서 기본적으로 제공하는 클래스 혹은 인터페이스들을 가진 패키지를 말한다. jdk 혹은 jre에서 jar 파일 형태로 포함되어있다. 그중 대표적인 jar 파일은 rt.jar! 

rt.jar 파일은 runtime JAR의 줄임말. 자바 런타임 환경을 위해서 미리 컴파일 되어야 할 클래스 파일( Core Java API )들을 포함한다. 즉, JVM 실행 시 클래스 로더에서 가장 먼저 로딩해야하는 패키지들(대표적으로 java 디렉토리 아래 `lang, io, util, sql` 패키지)을 담고있다.

그래서 rt.jar 파일을 클래스패스에 추가시키기 않는다면 `java.lang.String` , `java.lang.Threa`, `java.util.ArrayList` 와 같은 자바 코어 클래스들에 접근할 수 없다.



#### 패키지 이름 지정 규칙

- 패키지 이름은 모두 소문자
- 자바의 예약어는 사용 불가

- [참고](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html)



## 2.  Import

### 개념

To use a class or a package from the library, you need to use the `import` keyword like below:

아래와 같이 클래스 단위 혹은 패키지 단위로 import 할 수 있다.

```java
import package.name.Class;   // Import a single class
import package.name.*;   // Import the whole package
```

### 주의 사항

1. Cannot import Sub-package

   - If you import a package, subpackages will not be imported.

     ➡️ 단, `import` 키워드를 통해 sub-package를 import 할 수는 없다. sub-package를 제외한 클래스와 인터페이스만을 import 할 수 있다.

2. Cannot import unpackaged class

   - default 위치에 있거나 이름없는 패키지 안에 있는 클래스의 경우 import가 불가능하다
   - `import *` 불가능

3. use FQCN when same name exists in multiple packages

   - 만약 `com.mycompany.app` 패키지 아래의 MyClass라는 클래스가 있고 클래스 내부에서 com.abc와 com.xyz 아래에 이름이 같은 calculate() 메소드를 쓰고 싶다고 가정한다면

   - (1) 하나만 import, 하나는 FQCN 사용

   - ```java
     import com.xyz.util.Calculator;
     // ...
     Calculator.calculate(); // xyz 아래 calculate() 실행
     com.abc.util.Calculator.calculate(); // abc 아래 calculate() 실행
     ```

   - (2) 둘 다 FQCN 사용

     ```java
     com.xyz.util.Calculator.calculate(); // xyz 아래 calculate() 실행
     com.abc.util.Calculator.calculate(); // abc 아래 calculate() 실행
     ```

   - [예시 참조](https://books.google.co.kr/books?id=_lf0DwAAQBAJ&pg=PA40&lpg=PA40&dq=FQCN+meaning&source=bl&ots=_ARV6BAVPr&sig=ACfU3U0do4a2m5DQVshAuxvQTEvkvpZAsQ&hl=en&sa=X&ved=2ahUKEwj85Zewt-7wAhWaH3AKHUZ9AJQQ6AEwCnoECBgQAw#v=onepage&q=FQCN%20meaning&f=false)

### java.lang 패키지

- java.lang 패키지 내부 클래스들은 모든 클래스에서 자동으로 import 

### Static Import

상수나 정적 메소드를 import 할 수 있는 키워드

자바 버전 5 혹은 이상부터 사용 가능하다.

예를 들어 static 키워드로 import하지 않았을 경우 아래와 같이 static 메소드 혹은 상수를 사용할 수 있다.

```java
double r = Math.cos(Math.PI * theta);
```

만약 static import를 사용한다면 클래스 명을 생략할 수 있다.

```java
import static java.lang.Math.PI;
import static java.lang.Math.*;

double r = cos(PI * theta);
```

💡 [참고](https://docs.oracle.com/javase/tutorial/java/package/usepkgs.html) Use static import very sparingly. Overusing static import can result in code that is difficult to read and maintain, because readers of the code won't know which class defines a particular static object. Used properly, static import makes code more readable by removing class name repetition.

- static import는 되도록 사용하지 않는게 좋다

- 가독성과 유지보수에 좋지 않다 개발자가 해당 static 객체가 어느 클래스에서 정의되었는지 알기 힘들기 때문

- 클래스 이름이 계속 반복될 때 해당 클래스 이름을 지우기 위한 용도 정도로만 사용하는게 좋다. (무분별 사용 X)

  

## 3. CLASSPATH

패키지에 포함되지 않은 java 소스 파일을 컴파일할 때 클래스를 쉽게 찾기 위한 경로로 사용된다.

default CLASSPATH ==  "." 이다. 그리고 환경변수 설정에서 CLASSPATH를 따로 지정하게 되면 default 값인 "."를 설정된 경로로 override 할 수 있다.

주로 JVM이 프로그램을 실행할 때, 클래스 파일(`.class`)을 찾는데 이때 클래스 패스를 사용한다.



## 4. CLASSPATH 환경변수

환경 변수는 운영체제에 지정하는 변수

즉, 클래스 경로를 환경변수 설정을 통해서 지정할 수 있다.

JVM 동작 시 클래스 로더가 CLASSPATH 환경 변수 참고하기 때문에 우리가 클래스 파일을 실행시킬 때  -classpath 옵션을 따로 주지 않아도 되는 편리성을 제공한다.



## 5. -classpath 옵션 (-cp)

보통 CLASSPATH 환경 변수를 설정해주는 것 보다는 터미널에서 `-cp` 혹은 `-classpath` 명령을 통해 직접 명시해주는 것을 추천.

이 방식은 **다른 어플리케이션에 영향을 주지 않고** 각 어플리케이션에 맞는 클래스 패스를 지정해줄 수 있게 해주기 때문.

- CLASSPATH 환경변수 보다도 우선순위가 높아서 -classpath 명령어를 사용하면 환경변수로 설정해놓은 CLASSPATH를 override

- The default value of the class path is "." == current working directory

### 💡 언제 사용할 수 있을까?

해당 옵션은 컴파일 할 때 (`javac`) 그리고 클래스 파일을 실행할 때 (`java`) 둘 다 사용할 수 있다.



### 💡 클래스 패스가 어떤 식으로 사용될까? 클래스 로딩 할 때!

1. 사용하고자 하는 jar 파일을 빌드도구(maven, gradle 등)를 통해 dependency 설정  

2. 빌드도구가 해당 jar 파일을 다운받아서 클래스 패스를 확인해 특정한 위치(under the CLASSPATH)에 가져다 놓는다



## 6. 접근지시자

- 클래스, 메소드 혹은 변수의 접근 영역을 제어하기 위해서 사용

| 접근 제어자 | 영역                                                         | 동일 클래스 | 동일 패키지 | 자손 클래스 | 외부 영역 |
| ----------- | ------------------------------------------------------------ | ----------- | ----------- | ----------- | --------- |
| public      | 모든 영역에서 사용 가능                                      | O           | O           | O           | O         |
| protected   | 같은 패키지나 상속받은 클래스에서 사용 가능                  | O           | O           | O           |           |
| default     | 같은 패키지에서 사용 가능 (접근 제어자를 생략 시 default로 설정) | O           | O           |             |           |
| private     | 해당 클래스 내부에서만 사용 가능                             | O           |             |             |           |



## Reference

#### package

https://www.javatpoint.com/package

https://www.w3schools.com/java/java_packages.asp

https://docs.oracle.com/javase/tutorial/java/package/packages.html

#### API vs Library (What’s the Difference?)

https://rapidapi.com/blog/api-vs-library/#:~:text=The%20main%20difference%20is%20that,API%20refers%20to%20the%20interface.&text=An%20API%20can%20be%20made,of%20useful%20techniques%20or%20functions.

#### What is rt.jar in Java?

https://www.quora.com/What-is-rt-jar-in-Java

https://refreshjava.com/java/built-in-packages-in-java

#### CLASSPATH

https://docs.oracle.com/javase/tutorial/essential/environment/paths.html

https://www3.ntu.edu.sg/home/ehchua/programming/java/J9c_PackageClasspath.html



### 백기선 스터디

추천 해주신 책 : 테스트 주도 개발



