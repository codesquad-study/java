# Special.Reflection

### 목차

1. Reflection?

2. java.lange.reflect

3. Reflection 사용 시, 장단점

   

## 1. Reflection?

이미 로딩이 완료된 클래스에서 또 다른 클래스를 동적으로 로딩(Dynamic Loading)하여 생성자, 멤버 필드 그리고 멤버 메서드 등을 사용할 수 있도록 하는 API

- `동적 로딩(Dynamic Loading)`

  - 클래스를 **로딩**할 때 **필요한 정보**를 구하고, 그 클래스가 올바른지 **검사**한다.
  - 모든 클래스는 그 클래스가 참조되는 순간 동적으로 `JVM에 링크`되며, `메모리에 로딩`된다.
    - 필요한 정보 : 클래스의 생성자,메서드, 필드, 상속관계
    - 이전에는 JVM이 클래스에 대한 정보를 갖고 있지 않는다.

  출처 : [클래스로더 1, 동적인 클래스 로딩과 클래스 로더](https://javacan.tistory.com/entry/1)

<br><br>

## 2. java.lang.reflect package

- Reflection과 관련된 클래스들이 정의된 패키지

| 원하는 정보  | 메서드명         | 설명                                                         |
| ------------ | ---------------- | ------------------------------------------------------------ |
| Class        | getClass()       | 객체에 속한 `class name`를 확인하고 싶은 경우 사용하는 메서드 |
| Constructors | getConstructor() | 객체에 속한 `public constructor`를 사용하고 싶은 경우 사용하는 메서드 |
| Methods      | getMethods()     | 객체에 속한 `public method`를사용하고 싶은 경우 사용하는 메서드 |

- 추가적으로 클래스 `패키지 정보`, `접근` `지정자`, `수퍼 클래스`, `애노테이션`도 가능.

- 예시 코드

  ```java
  class CodeSquad {
      private String member;
  
      public CodeSquad() {
          member = "cooper";
      }
  
      public static void learnJava() {
          System.out.println("자바를 배운다.");
      }
  
      private static void eatSomething() {
          System.out.println("혼자 뭔가를 먹는다.");
      }
  
      public void goCodeSquad() {
          System.out.println(member + "는 codesquad 가는 중.");
      }
  
      protected void playGame() {
          System.out.println(member + "는 게임을 하는 중.");
      }
  
      void talk() {
          System.out.println("수다를 떤다.");
      }
  
      private void takeRest() {
          System.out.println(member + "는 논땡이를 피는 중.");
      }
  
      public void eatLunch(String food) {
          System.out.printf("%s는 %s를 점심으로 먹는 중.\n", member, food);
      }
  }
  ```

  ```java
  import java.lang.reflect.Constructor;
  import java.lang.reflect.Field;
  import java.lang.reflect.Method;
  
  class TestMain {
      public static void main(String[] args) throws Exception {
  
          //1. 객체 생성 (동적 로딩)
          CodeSquad codeSquad = new CodeSquad();
  
          //2. class 이름 호출하기
          Class codeClass = codeSquad.getClass();
          System.out.println("클래스 이름은 " + codeClass.getName());
  
          //3. constructor 이름 호출하기
          Constructor codeConstructor = codeClass.getConstructor();
          System.out.println("생성자 이름은" + codeConstructor);
  
          //4. 객체에 선언된 public 메서드 조회
          Method[] methods = codeClass.getMethods();
          for (Method method : methods) {
              System.out.printf("%s의 메서드 : %s \n", codeClass.getName(), method);
          }
  
          //5. 객체에 선언된 메서드들 호출
          Method methodCall = codeClass.getDeclaredMethod("eatLunch", String.class);
          methodCall.invoke(codeSquad, "돈까스");
  
          //6. private 메서드 호출
          Method privateMethodCall = codeClass.getDeclaredMethod("takeRest");
          privateMethodCall.setAccessible(true);
          privateMethodCall.invoke(codeSquad);
  
          //7. private field 변경하기
          Field privateField = codeClass.getDeclaredField("member");
          privateField.setAccessible(true);
          privateField.set(codeSquad, "honux");
          methodCall.invoke(codeSquad, "샐러드");
      }
  }
  
  ```

  <br><br>

### 3. Reflection 사용 시 장단점

1. `장점`

   - Extensibility Feature : 정규화된 이름을 사용하여 확장성 개체의 인스턴스를 만들어 외부 사용자 정의 클래스를 사용할 수 있다.

     - 확장성 : reflection을 통해 객체 내부 필드 설정 가능 + 조금 더 유연한 코드를 작성할 수 있다(?)
     - 외부 사용자 정의 클래스 : reflection을 통해 객체를 생성할 수 있다.

   - Debugging and testing tools : 디버거는 클래스의 private  멤버를 reflection의 특성을 이용한다.

     

<br>

2. `단점`
   - Performance Overhead : reflection 연산은 `속도면에서 느리다` (성능에 예민한 애플리케이션에서 사용을 자제.)
   - Exposure of Internals : reflective code는 `추상화를 깨트린다.`(?)
     - 추상화 : 여러 객체에 공통적으로 사용되는, 사용되어야 하는 내용을 추출하는 작업

<br><br>

## Reference

### Reflection 관련

- [Oracle] Using Java Reflection

  https://www.oracle.com/technical-resources/articles/java/javareflection.html

- [GeeksforGeeks] Reflectino in Java

  https://www.geeksforgeeks.org/reflection-in-java/

<br>

### 동적 로딩

- [운영체제] Dynamic Loading 동적적재 & Overlay(paging VMM과 차이점 )

  https://jhnyang.tistory.com/44

- 클래스로더 1, 동적인 클래스 로딩과 클래스 로더

  https://javacan.tistory.com/entry/1

