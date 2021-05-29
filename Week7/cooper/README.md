# Week7. 패키지

## 학습할 것

- package 키워드
- import 키워드
- 클래스패스
- -classpath 옵션
- CLASSPATH 환경변수
- 접근제어자

<br><br>

## 1. keyword : package

### 1) package 사용 이유?

- 클래스를 유일하게 만들어주는 `식별자` 역할

  - 네임스페이스(namespace) 역할을 한다.

    - `namespace` : 이름을 구분지을 수 있는 공간을 의미.

      ```
      /Users/dir_a/test.java
      /Users/dir_b/test.java
      
      - 두 같은 파일을 구분할 수 있는 이유는?
      	dir_a와 dir_b : 디렉토리가 파일을 구분시켜줄 수 있기 때문이다.
      ```

- 패키지 이름과 위치한 폴더의 이름이 같아야 한다.

<br>

### 2) java compile 방법?

- 자바는 가장 `상위 디렉토리(root)에서 컴파일(compile)`한다. 

  (해당 클래스 파일의 위치까지 가서 컴파일하지 않는다.)

<br>

### 2) package 사용법

- package키워드 사용법

  - 클래스가 포함될 패키지를 지정할 때 사용

    ```
    상위패키지.하위패키지.클래스
    
    ex) package com.java.study.week7;
    ```

  - `Build-in-Package`

    - java.lang package는 별도의 import를 선언하지 않아도 자동적으로 불러온다.

      ```java
      String s = new String("sample");
      
      java.lang.String s = new java.lang.String("sample");
      ```

<br>

### 3) 명명법

- `숫자`로 시작하면 안된다.
- `_ $를 제외한 특수문자` 사용을 금지.
- 모두 `소문자`로만 작성하는 것이 관례

<br>

- `FQCN(Fully Qualified Class Name)` : 클래스가 속한 패키지명을 모두 포함한 이름

  - String 클래스의 패키지명 : `java.lang`
  - String의 FQCN :` java.lang.String`

  ```java
  java.lang.String s = new java.lang.String();
  ```

<br><br>

## 2. keyword : import

### 1) import 사용 이유?

- `다른 패키지의 클래스를 사용`하고자할 때, `패키지명을 생략`하기 위함이다

  - import를 사용하지 않는다면...

    ```java
    package com.whiteship.study;
    
    public class TestClass {
      com.whiteship.sample TodoClass todoClass = new com.whiteship.TodoClass();
    }
    ```

  - 하지만, import를 사용할 경우, **깔끔!**

    ```java
    package com.whiteship.study;
    
    import com.whiteship.sample.TodoClass;
    
    public class TestClass {
      TodoClass todoClass = new TodoClass();
    }
    
    ```

<br>

### 2) import 사용 시, 주의사항

1. import로 지정된 패키지의 하위 패키지는 impor대상이 아님!

   - 만약 하위 패키지의 클래스를 이용하고 싶다면 import문 추가 작성!

     ```java
     import com.mycompany.*; //com.mycompany 패키지의 클래스를 사용하고 싶은 경우
     import com.mycompany.project.*; //com.mycompay.project의 클래스를 사용하고 싶은 경우
     ```

<br>

1. 만약 다른 패키지의 같은 class를 사용하고 싶다면?

   - 각각의 import를 선언하고 `사용 클래스에 package명을 작성`한다.

     ```java
     package com.sample;
     
     import com.study_algorithm.*;
     import com.study_java.*;
     
     public Class Study {
       com.study_algorithm.Cooper cooper = new com.study_algorithm.Cooper();
       
       com.study_java.Cooper cooper = new com.study_java.Cooper();
     }
     ```

<br>

### 3) static import?

- 메서드, 변수를 `패키지, 클래스명 없이 접근 가능`한 import

  ```java
  import static java.lang.System.*;
  
  public class Test {
    public static void main(String[] args) {
      out.println("Text");
    }
  }
  ```

<br><br>

## 3. 클래스 패스(class path), -classpath

### 1) class path?

- JVM이나 Java 컴파일러에서 클래스와 패키지 위치를 지정해주는 파라미터.

- 즉, 클래스들의 위치를 지정해주는 값.

- classpath option : 파일 경로를 지정해주는 옵션

  ```shell
  javac -classpath [컴파일한 파일 경로] [컴파일할 파일]
  
  javac -classpath(-cp) /Users/cooper/CooperCode/private_pratice/java-practice/src/string CooperCode/private_pratice/java-practice/src/string/JoinPractice.java
  ```

<br>

### 2. Java Compile 시, 주의사항

- 자바는 가장 `상위 디렉토리(root)에서 컴파일(compile)`한다. 

  - root 아닌 경로에서 java 실행 시,

  - 시작 경로 : /home

    ```shell
    java  CooperCode/private_pratice/java-practice/src/string/JoinPractice.java
    ```

    

    ```
    오류: 기본 클래스 CooperCode.private_pratice.java-practice.src.string.JoinPractice.java을(를) 찾거나 로드할 수 없습니다.
    ```

  - root 경로에서 java 실행 시,

    - `.class파일`을 실행시키기 위해서는 `package 경로`를 포함시켜야 함.
    - 시작 경로 : /home

    ```shell
    java -classpath /Users/cooper/CooperCode/private_pratice/java-practice/src string.JoinPractice
    ```

<br>

- **Compile 원리**

  - JVM의 클래스로더는 런타임 시, `$CLASSPATH` 환경변수를 호출해 해당 정의된 클래스들을 로딩한다.

    ![](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fcaa950a4-535a-44d9-98c8-cd136f7771d1%2FUntitled.png?table=block&id=4013022a-746b-4ab1-9a68-9408796c9f91&spaceId=af9c3f71-bb46-4a7f-933b-fd22501eabb5&width=1790&userId=b99d061d-fb39-4688-9943-3f494c979052&cache=v2)

    ​			(출처 : sonsh님의 notion)

  - BootStrap Class Loader

    - 기본 클래스로더 중 최상위 클래스 로더
    - `jre/lib/rt.jar`에 담긴 `JDK 클래스 파일`을 로딩
    - String, Object 클래스가 자동으로 스택 메모리에 적재한다.

  - Extension Class Loader

    - `jre/lib/ext/` 혹은 `java.ext.dirs` 환경 변수로 지정된 폴더에 있는 클래스 파일 로딩

  - System Class Loader

    - 사용자 정의 class(우리가 만든 class)를 메모리에 적재하는 역할.
    - `$CLASSPATH` 기준으로 클래스들을 로드

<br><br>

## 4. CLASSPATH 환경변수

### 환경변수

- `운영체제에서 지정하는 변수`
- java의 경우, javac 혹은 JVM이 `클래스 위치를 찾는데 사용하는 경로`.

<br>

### 환경 변수 위치

- Window : `시스템 > 환경변수`
- linux, unix 계열 : `/etc/profile`

- Intellij IDE 환경 설정

  ![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bc383643-075c-4af5-a76c-1fdb67b6452e/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210529%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210529T100410Z&X-Amz-Expires=86400&X-Amz-Signature=55116644e8b75154ee82ce0763ee2aed2f673cd662c970eb55e263e96fb96168&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

<br><br>

## 5. 접근 제어자

<출처 : [tcp school 접근 제어자](http://tcpschool.com/java/java_modifier_accessModifier )>

| <img src="http://tcpschool.com/lectures/img_java_access_private.png" width="300" height="200"> | <img src="http://tcpschool.com/lectures/img_java_access_default.png" width="300" height="200"> |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <img src="http://tcpschool.com/lectures/img_java_access_protected.png" width="300" height="200"> | <img src="http://tcpschool.com/lectures/img_java_access_public.png" width="300" height="200"> |



| 제어자                   | 같은 클래스 | 같은 패키지 | 하위 클래스 | 전체 |
| ------------------------ | ----------- | ----------- | ----------- | ---- |
| public                   | O           | O           | O           | O    |
| protected                | O           | O           | O           | X    |
| Private-package(default) | O           | O           | X           | X    |
| private                  | O           | X           | X           | X    |

<br><br>



## Reference

- https://www.notion.so/ed8e346f88f54849a06ff968b1877ca5
- https://javarevisited.blogspot.com/2015/01/what-is-rtjar-in-javajdkjre-why-its-important.html#axzz6wFJco2m2

