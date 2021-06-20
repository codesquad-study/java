# Week12. 애노테이션(Annotation)

## 학습할 것 (필수)

- 애노테이션 정의하는 방법
- [@retention](https://github.com/retention)
- [@target](https://github.com/target)
- [@documented](https://github.com/documented)
- 애노테이션 프로세서

<br><br>

## 1. 애노테이션 정의하는 방법

### [1] 어노테이션이란?

- 프로그램의 소스코드 안에 다른 프로그램을 위한 **정보를 미리 약속한 형식으로 포함**시킨 것이다.
- 프로그래밍 **언어에 영향을 미치지 않으면서** 다른 프로그램에게 **유용한 정보를 제공**할 수 있는 장점이 있다.

<br>

### [2] 표준 어노테이션

- 자바에서 기본적으로 제공하는 에너테이션들

  | 애너테이션           | 설명                                                         |
  | -------------------- | ------------------------------------------------------------ |
  | @Override            | **컴파일러에게 오버라이딩**하는 메서드라는 것을 알린다.      |
  | @Deprecated          | **앞으로 사용하지 않을 것**을 권장하는 대상에 붙인다.        |
  | @SuppressWarings     | 컴파일러의 **특정 경고메세지가 나타나지 않게** 해준다.       |
  | @SafeVarags          | **지네릭스 타입의 가변인자**에 사용한다.(JDK1.7)             |
  | @FunctionalInterface | **함수형 인터페이스**라는 것을 알린다.(JDK1.8)               |
  | @Native              | native 메서드에서 참조되는 상수 앞에 붙인다.(JDK1.8)         |
  | @Target              | **애너테이션이 적용 가능한 대상을 지정**하는데 사용한다.     |
  | @Documented          | 애너테이션 정보가 **javadoc으로 작성된 문서에 포함**되게 한다. |
  | @Inherited           | 애너테이션이 **자손 클래스에 상속**되도록 한다.              |
  | @Retention           | 애너테이션이 유지되는 **범위를 지정하는데 사용**한다.        |
  | @Repeatable          | **애너테이션을 반복해서 적용**할 수 있게 한다.               |

<br>

- @Override
- @Deprecated
- @SupressWarnings
- @SafeVargs

<br><br>

### [3] 메타 애너테이션

- 애너테이션에 붙이는 애너테이션
- 애너테이션을 정의할 때 애너테이션의 **적용대상(target)**이나 **유지기간(retention)**등을 지정하는데 사용된다.

**(1) @Target**

- 애너테이션이 적용 가능한 대상을 지정하는데 사용.

- @SupressWarnings

  ```java
  @Target({TYPE, FILED, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
  public @interface SurpressWarnings {
    String[] value();
  }
  ```

  

| 대상 타입       | 의미                            |
| --------------- | ------------------------------- |
| ANNOTATION_TYPE | 애너테이션                      |
| CONSTRUCTOR     | 생성자                          |
| FIELD           | 필드(멤버변수, enum상수)        |
| LOCAL_VARIABLE  | 지역변수                        |
| METHOD          | 메서드                          |
| PACKAGE         | 패키지                          |
| PARAMETER       | 매개변수                        |
| TYPE            | 타입(클래스, 인터페이스, enum)  |
| TYPE_PARAMETER  | 타입 매개변수(JDK1.8)           |
| TYPE_USE        | 타입이 사용되는 모든 곳(JDK1.8) |

<br>

**(2) @Retention**

- 애너테이션이 유지(retention)되는 기간을 지정하는데 사용된다.

- 유지 정책(retention policy)의 종류

  | 유지 정책 | 의미                                              |
  | --------- | ------------------------------------------------- |
  | SOURCE    | 소스파일에만 존재, 클래스 파일에는 존재하지 않음. |
  | CLASS     | 클래스 파일에 존재, 실행시에 사용불가. 기본값     |
  | RUNTIME   | 클래스 파일에 존재, 실행시에 사용가능             |

  - SOURCE

    - 컴파일러가 사용하는 애너테이션

    - ex) @Override, @SurpressWarnings

    - Override annotation

      ```java
      @Target(ElementType.METHOD)
      @Retention(RetentionPolicy.SOURCE)
      public @interface Override {}
      ```

      <br>

  - RUNTIME

    - 실행 시 **리플렉션(reflection)**을 통해 **클래스에 저장된 애너테이션의 정보를 읽어서 처리**할 수 있다.

    - FunctionalInterface annotation

      ```java
      @Documented
      @Retention(RetentionPolicy.RUNTIME)
      @Target(ElementType.TYPE)
      public @interface FunctionalInterface {}
      ```

      <br>

  - CLASS

    - **컴파일러**가 애너테이션의 정보를 **클래스 파일에 저장**할 수 있게는 하지만,

      클래스 파일이 JVM에 `로딩`될 때는 애너테이션의 **정보가 무시**되어 실행 시에 애너테이션에 대한 정보를 얻을 수 없다.

<br>

**(3) Documented**

- 애너테이션에 대한 정보가 javadoc으로 작성한 문서에 포함되도록 한다.

  ```java
  @Documented
  @Retentinon(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  public @interface FuntionalInterface {}
  ```

<br>

**(4) Inherited**

- 애너테이션이 `자손 클래스에 상속`되도록 한다.

  ```Java
  @Inherited //@SuperAnno가 자손까지 영향 미치게
  @interface SuperAnno {}
  
  @SuperAnno
  class Parent {}
  
  class Child extends Parents {}
  ```

<br>

**(5) Repeatable**

- 선언한 애너테이션은 여러번 사용할 수 있다.

  ```java
  @Repeatable(ToDos.class) //ToDo애너테이션을 여러 번 반복해서 쓸 수 있게 한다.
  @interafce ToDo {
    String value();
  }
  
  @ToDo("delete test codes")
  @ToDo("override inherited methods")
  class MyClass {
    ...
  }
  ```

<br>

**(6) @Native**

- 네이티브 메서드(native method)에 의해 참조되는 상수 필드(constant field)에 붙이는 애너테이션

- 네이티브 메서드(native method) :  JVM이 설치된 OS의 메서드를 말한다. 

  ```java
  public class Object {
    private static native void registerNatives();
    
    static {
      registerNatives();
    }
    
    protected native Object clone() throws CloneNOtSupportedException;
    public final native Class<?> getClass();
    public final native void notify();
    public final native void notifiyAll();
    public final native void wait(long timeout) throws InterruptedException;
    public native int hashCode();
    ...
  }
  ```

- 네이티브 코드(Native code)

  - 운영체제(OS)가 직접 실행할 수 있는 코드(원시 기계 코드)
  - 네이티브 코드는 `캐시`에서 보관한다.한 번 컴파일된 코드를 캐시에서 바로 실행하므로 속도가 빠르다.

<br><br>

### [4] 애너테이션 타입 정의하기

```java
@interface 애너테이션이름 {
  타입 요소이름(); // 애너테이션 요소를 선언한다.
}
```

<br>

**(1) 애너테이션 요소**

- 애너테이션 요소(element) : 애너테이션 내에 선언되 메서드

- 애너테이션 요소는 반환값이 있고 매개변수는 없는 추상 메서드의 형태를 가지며, 상속을 통해 구현하지 않아도 된다.

- 다만. 애너테이션을 적용할 시, 요소들의 값을 빠짐없이 지정해주어야 한다.

-  애너테이션의 각 요소는 기본 값을 가질 수 있으며 애너테이션을 적용할 때 값 미지정 시, 기본값 지정

  ```java
  @interface TestInfo {
    int count() default 1;
  }
  
  @TestInfo	//@TestInfo(count = 1)과 동일
  public class NewClass {...}
  ```

- 애너테이션의 요소가 하나일 경우, 애너테이션 적용 시 요소의 이름을 생략하고 값만 적어도 된다 

  ```java
  @interface TestInfo {
    String value();
  }
  
  @TestInfo("passed")
  public class newClass{...}
  ```

- 요소의 타입이 배열인 경우, 괄호 {}를 사용해서 여러 개의 값을 지정할 수 있다.

  ```java
  @interface TestInfo {
    String[] testTools();
  }
  
  @Test(testTools = ("JUnit", "AutoTester"))
  @Test(testTools = "JUnit")
  @Test(testTools = {})
  ```

<br><br>

### [4] 마커 애너테이션

- 요소가 하나도 정의되지 않은 애너테이션
- Ex) Serialzable, Cloneable interface

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Test {}
```

<br><br>

### [5] 애너테이션 요소의 규칙

- 요소의 타입은, 기본형, String, enum, 애너테이션, Class만 허용된다.
- ()안에 매개변수를 선언할 수 있다.
- 예외를 선언할 수 없다.
- 요소를 타입 매개변수로 정의할 수 없다.

<br><br>

### [6] 애너테이션 프로세서

- `컴파일 시점`에 끼어들어서 특정 `애너테이션이 붙어있는 소스코드를 참고`해서 또 다른 `애너테이션을 만드는 기능`

- 아래와 같은 과정을 통해서 `Annotation processing`이 이루어진다.

  출처 블로그 : https://andole98.github.io/java/java-annotation-process/#

  1. 애너테이션 클래스를 생성한다.

     ```java
     @Retention(RetentionPolicy.SOURCE)
     @Target(ElementType.TYPE)
     public @interface AutoFactory {
         
     }
     
     @Retention(RetentionPolicy.SOURCE)
     @Target(ElementType.TYPE)
     public @interface AutoElement {
         String tag();
     }
     ```

  2. 애너테이션 파서 클래스를 생성한다.

     - `AbstractProcessor` : 컴파일 파이프라인에 추가할 수 있는 플러그인, 소스코드 분석하고 코드 생산하기도 함.
       - `init` : Processing에 필요한 환경을 설정한다.
       - `getSupportedAnnotationTypes` : 추가할 애너테이션을 컴파일러에게 정의하는 메서드
       - 'process' : 애너테이션을 조작한다.
         - `FactoryBuilder`
           - 애너테이션의 정보를 받아 자바 코드를 생성하고, 빌드 폴더에 .class 파일을 생성한다.

  3. 애너테이션을 사용한다.

  4. 컴파일하면, 애너테이션 파서가 어노테이션을 처리한다.

     - `@AutoService` : 애너테이션 프로세서를 컴파일러에 바인딩하게 도와주는 구글 라이브러리

  5. 자동 생성된 클래스가 빌드 폴더에 추가된다.

<br><br>

## Reference

### 애노테이션

- 자바의 정석 - 애노테이션

<br>

**reflection**

- [효자손개발자] Reflction 이야기(feat. Annotation)

  https://better-dev.netlify.app/java/2020/08/20/thejava_8/#more

<br>

**애노테이션 프로세서**

- [개발매발] [Live Study] 12주차 과제: 애노테이션 : 애너테이션 프로세서 등록 설정 방법

  https://blog.baesangwoo.dev/posts/java-livestudy-12week/

- 어노테이션 프로세싱 - 어노테이션은 어떻게 동작하나?

  https://andole98.github.io/java/java-annotation-process/#

- Annotation Processing 101 (번역)

  https://medium.com/@jason_kim/annotation-processing-101-%EB%B2%88%EC%97%AD-be333c7b913

- [Baeldung] java-custom-annotation

  https://www.baeldung.com/java-custom-annotation

<br>

**나중에 볼 애노테이션 프로세서 관련 내용**

- [인프런] 더 자바, 코드를 조작하는 다양한 방법 - 다음에 세일할 때 사서 봐야겠다.

  https://www.inflearn.com/course/the-java-code-manipulation#curriculum

- [Oracle docs] interface Processor

  https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html