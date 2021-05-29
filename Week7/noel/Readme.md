# 7주차 과제: 패키지

열: 2021년 5월 29일 오후 6:11
태그: 백기선스터디

💾 관련이슈: [https://github.com/whiteship/live-study/issues/7](https://github.com/whiteship/live-study/issues/7)

🎞️ 유튜브:  [https://www.youtube.com/watch?v=-nMtXqSwCWg&list=PLfI752FpVCS96fSsQe2E3HzYTgdmbz6LU&index=14](https://www.youtube.com/watch?v=-nMtXqSwCWg&list=PLfI752FpVCS96fSsQe2E3HzYTgdmbz6LU&index=14)

---

# package

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled.png)

- [https://javagoal.com/packages-in-java/#2](https://javagoal.com/packages-in-java/#2)

```java
- 연관된 타입을 그룹핑해서 접근 보호와 네임 스페이스 관리
    - 타입: "클래스, 인터페이스, 열거 및 애노테이션"
- C++의 "namespace"와 비슷한 개념
```

## Package 종류

```java
1. built-in package
2. user-defined package
```

## package 역할

- 파일 시스템의 디렉토리와 비슷한 역할

```java
- 식별자 역할
    - 각 타입의 연관성을 쉽게 확인할 수 있음
    - 동일한 이름을 가진 클래스 구분할 수 있음
- 데이터캡슐화(혹은 데이터 은닉) 용도로 사용할 수 있음
    - 접근제어자
```

## package 특징

```sql
- 하나의 소스파일에는 첫 번째 문장으로, 단 "한번"의 패키지 선언만을 허용한다.
- 모든 클래스는 "반드시 하나"의 패키지에 속해야한다.
- 패키지 이름과 위치한 폴더의 이름이 같아야한다.
- 패키지를 입력하지 않으면 "이름 없는 패키지"에 위치
```

## package naming

- 같은 이름의 패키지명을 가진다면 충돌이 일어남
- 이러한 문제를 방지하기 위해 자바에서 정한 규칙이 있음

```java
- 패키지의 이름을 클래스, 인터페이스 이름과 겹치지 않도록 모두 "소문자"로 작성

- 회사의 경우 도메인의 "역순"으로 패키지명을 사용하면 좋음
	- URL: naver.com 
  - package: com.naver."프로젝트명 또는 팀명"

- 자바 언어에서 사용하는 패키지는 java, javax 으로 패키지명의 루트로 사용할 수 없음
```

## Built-in Package

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%201.png)

- [https://javagoal.com/packages-in-java/#2](https://javagoal.com/packages-in-java/#2)

```java
-  Java에서 제공하는 기본 제공 패키지
-  클래스, 인터페이스, 열거형 등이 많이 포함되어 있음.
  
- 종류
 -  java.lang
    - "String", "System.out.***" 등...
    - 기초적인거라 import 하지 않아도, 자동으로 생성해준다.
 -  java.util
 -  java.awt

```

## User defined packages

```java
package 상위패키지.하위패키지.클래스;
```

## 패키지별 클래스명 충돌 해결법

```java
- FQCN을 사용해서 코드 작성
```

## FQCN ?

```java
Fully Qualified Class Name

- 클래스가 속한 패키지명을 모두 포함한 이름

- "Alias Name" : String str = new String();
- "FQCN" : java.lang.String str = new java.lang.String(); 
```

# import

```java

- "다른 패키지"의 클래스를 사용하려면 "패키지명이 포함된 클래스 이름"을 사용해야한다. 

- 즉, 매번 패키지명을 붙여서 작성해야하는 불편함을 겪게 된다.

- import문의 역할은 컴파일러에게 소스파일에 사용된 클래스의 패키지에 대한 정보를 제공하는 것이다. 

- 따라서 "컴파일 시"에 컴파일러는 import문을 통해 소스파일에 사용된 클래스들의 패키지를 알아낸 후 모든 클래스 이름 앞에 "패키지명을 붙여 준다."
```

## import 선언

- 클래스 또는 정적(static) 멤버를 import 할 수 있음.
- import문은 `FQCN` 형식을 따른다.
- import문이 너무 많아지면, 클래스의 패키지 구분이 어려워지므로 유의해서 사용

```java

1. import java.util.클래스명; 
2. import java.util.*  // 전체 패키지 import
```

## 전체 패키지 import  유의점

```java
- 패턴 매칭이 되지 않음
    - import. java.util.M* `(X)`

- 하위 패키지는 import 대상이 아님 ❗❗❗❗❗
```

## import 생략가능한 패키지

```java
1. java.lang
2. 동일 패키지
```

## static import

```java
- static import문을 사용하면 static멤버를 호출할 때 클래스 이름을 생략할 수 있음
- 과도하게 사용할 경우, 코드를 읽는 입장에서 난해할 수 있음.
```

## package 멤버의 사용

- public 패키지 멤버를 외부에서 사용하는 방법

```java
# 풀패키지 경로로 멤버 참조 (FQCN)

  🥕 java.util.Random random = new java.util.Random();

# 패키지 멤버 import

  🥕 import java.util.Random;
  🥕 Random random = new Random();

# 전체 패키지 import

  🥕 import java.util.*;

# static import

  🥕 import static org.junit.Assert.*;
  🥕 assertThat(1, is(1));
   
```

## 디렉토리 구조

```java
- 실제 디렉토리 구조는 패키지명과 동일하게 저장됨.

- com.example.project 패키지의 MyClass 클래스가 존재한다면?
    
    - "$BASE_DIR/com/example/project/MyClass.class" 와 같이 저장됨.

- "기본 디렉토리($BASE_DIR)"은 파일 시스템의 어디에나 위치할 수 있음
   
    - 따라서 자바 컴파일러와 JVM은 기본 디렉토리의 위치를 알고 있어야 함
    - 이를 위해서 필요한 "환경 변수"(environment variable)을 "CLASSPATH"
```

# 클래스패스

```java
- 클래스패스는 "JVM" 혹은 "Java 컴파일러"가 사용하는 파라미터
- 클래스나 패키지를 찾을 때 기준이 되는 경로
- 커맨드 쉘이 실행할 프로그램을 찾을 수 있게끔 PATH를 알려주는데 사용

- 만약, CLASSPATH가 세팅되어 있지 않다면 자바 컴파일러는 필요한 파일을 찾을 수 없고 컴파일 타임에 "ERROR"를 던지게 된다.
```

# CLASSPATH 환경변수

- 윈도우 시스템 환경변수 설정

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%202.png)

# classpath 옵션

- CLASSPATH 환경변수를 등록하여 사용하는 대신, 커맨드라인 옵션을 사용할 수 있음.
- javac로 java파일을 컴파일 할 때에도 -cp(classpath) 옵션을 통해 컴파일 할 수 있다.
- **cp(classpaht) 옵션은 컴파일할 때, 실행할 때 모두 사용할 수 있음**
- `-classpath` 혹은 `-cp`

```java

java -classpath path
javac -cp path
```

## 클래스패스 실습 예제

```java
package com.example.java;
  
class RequiredClass{  
    public void print(String s){  
        System.out.println(s);  
  }  
}  
  
public class ClasspathDemo {  
    public static void main(String[] args) {  
        RequiredClass rc = new RequiredClass();  
  rc.print("classpath test");  
  }  
}
```

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%203.png)

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%204.png)

### 문제를 해결하려면?

- 패키지가 있기때문에 컴파일된 .class 파일은 그 경로에 존재해야 한다.
- .class 파일을 실행시키기 위해선 package 의 경로가 포함되어야 한다.

```bash
$ java -cp C:/Users/psh/Desktop/test com.example.java.ClasspathDemo main

> classpath test ❗❗❗❗❗❗
```

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%205.png)

### JVM이 ClasspathDemo 를 정상적으로 찾았다.

## JVM의 클래스로더

![](https://github.com/codesquad-study/java/blob/main/Week7/noel/img/Untitled%206.png)

```bash
- JVM의 클래스로더는 런타임 시에 $**CLASSPATH** 환경변수를 호출해 해당 디렉토리에 정의된 클래스들을 로딩하게 된다.

- 클래스로더는 3가지가 존재한다.
```

### Bootstrap Class Loader

```bash
- 기본 클래스로더 중 최상위 클래스 로더

- "jre/lib/rt.jar" 에 담긴 "JDK 클래스 파일"을 "로딩"해줌

- "String 클래스나, Object 클래스"를 "사용할 수 있었던 이유"가 바로 BootStrap Class Loader가 "자동"으로 메모리에 "적재"해주기 때문
```

### Extension Class Loader

```bash
-  **jre/lib/ext** 폴더나 **java.ext.dirs** 환경 변수로 지정된 폴더에 있는 클래스 파일을 로딩
```

### System Class Loader

```bash
- 우리가 만든 Class를 메모리에 올리는 역할 

- "classpath" 기준으로 클래스들을 로드 ❗❗❗❗❗
```

**IDE의 자동 클래스패스 설정**

최근에는 운영체제 상의 환경변수로 클래스패스를 설정하는 것은 지양하고 IDE나 빌드도구를 통해 클래스패스를 설정한다.

(참고. [https://gintrie.tistory.com/67](https://gintrie.tistory.com/67))

---

### Reference.

[https://ahnyezi.github.io/java/javastudy-7-package/#2-패키지-사용방법](https://ahnyezi.github.io/java/javastudy-7-package/#2-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95)

[https://blog.baesangwoo.dev/posts/java-livestudy-7week/](https://blog.baesangwoo.dev/posts/java-livestudy-7week/)

[https://kils-log-of-develop.tistory.com/430](https://kils-log-of-develop.tistory.com/430)

[https://velog.io/@dion/백기선님-온라인-스터디-7주차-패키지](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-7%EC%A3%BC%EC%B0%A8-%ED%8C%A8%ED%82%A4%EC%A7%80)

[https://hyeon424.tistory.com/entry/Java-import-선언의-이점](https://hyeon424.tistory.com/entry/Java-import-%EC%84%A0%EC%96%B8%EC%9D%98-%EC%9D%B4%EC%A0%90)

[https://www.notion.so/ed8e346f88f54849a06ff968b1877ca5](https://www.notion.so/ed8e346f88f54849a06ff968b1877ca5)

---

---

# ❤ 받은 게시글 스크랩

- 총 11개
- 많으니까, 백기선님이 언급한 키워드에 대해서만 학습하고 정리

```sql
7주차 과제입니다.
https://kils-log-of-develop.tistory.com/430

(whiteship) 정리 깔끔하네요. 딱 원하는 수준의 학습입니다. 감사합니다.
```

```sql
이번엔 일찍 제출해봅니다!! 항상 감사드립니다. 👍
https://sangwoobae.github.io/posts/java-livestudy-7week/

(whiteship) FQCN 언급해 주셔서 감사합니다.
```

```sql
7주차 과제 제출합니다. : )
날이 추워져서 노를 젓는데 손이 시렵네요..
https://www.notion.so/ed8e346f88f54849a06ff968b1877ca5

(whiteship) 굳! 빌트인 패키지를 언급한 글을 기다리고 있었습니다. 감사합니다.

```

```sql
7주차 과제 제출합니다.
https://pej4303.tistory.com/57

(whiteship) 그렇죠. 컴파일 할 때도 클래스패스가 필요하단 내용 감사합니다.
```

```sql
7주차 과제입니다. 감사합니다!
새해 복 많이 받으세요!!
https://www.notion.so/7-3b8b74b0bd574255a04ecfe7e9773b3c

(whiteship) 정리 깔끔합니다.
```

```sql
새해복 많이 받으세요~~~
7주차 과제입니다.
https://gintrie.tistory.com/67

```

```sql
패키지 관련 퀴즈를 발견해서 링크 공유할게요 :)
다들 새해 복 많이 받으세요~

과제: 자바의 패키지와 클래스패스
https://ahnyezi.github.io/java/javastudy-7-package/

퀴즈
https://www.geeksforgeeks.org/java-gq/packages-gq/

(whiteship) 정리도 잘 하셨고 퀴즈 링크도 감사합니다.
```

```sql
7주차 스터디 입니다 :)
https://velog.io/@jaden_94/7주차-항해일지
선장님 새해 복 많이 받으시고 좋은 일만 가득하세요!
선원님들도 모두 좋은 일 가득하세요!!

(whiteship) 클래스로더 계층 구조 설명 감사합니다.
```

```sql
7주차 과제 제출합니다.
선장님 새해 복 많이 받으세요!! 👍

https://parkadd.tistory.com/45

(whiteship) rt.jar에 대한 자세한 설명 감사합니다.
```

```sql
7주차 과제 제출합니다
새해복 많이 받으세요~

https://github.com/dacapolife87/javaStudy/blob/main/study/week07/Week07_package.md

(whiteship) import 관련 예제가 좋네요. 감사합니다.
```

```sql
과제 제출합니다!

https://velog.io/@ljs0429777/7주차-과제-패키지

선장님!
static import을 공부하면서 Anti 패턴인 Constant Interface에 대한 내용을 접했는데 나름 정리한다고 헀는데 정리하면서 이해가 전반적으로 부족했습니다 ㅠㅠ 라이브 방송때 Constant Interface에 대해 짧은 설명 부탁드려두 될까요?! (라이브 때는 알바하고 있을때라..퇴근하고 바로 녹화본으로 보겠습니다!)

(whiteship) 정리 잘 하셨네요. 좋은 질문 감사합니다. 라이브 방송때 다루겠습니다.
```