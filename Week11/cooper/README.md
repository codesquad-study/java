# Enum

## 학습할 것 (필수)

- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet



## 1. enum을 정의하는 방법

### (1) Enum이란?

- 열거형은 서로 연관된 상수를 편리하게 선언하기 위한 것으로 **여러 상수를 정의**할 때 유용하다.

- 자바 Enum의 장점 : 타입에 안전한 열거형(`type-safe enum`)

  - C언어 : 타입이 달라도 **값이 같으면** 조건식 결과가 참(true)이다.

  - Java : **타입과 실제 값이 모두 일치**해야 한다. (타입이 다를 경우, 컴파일 에러를 발생한다.)

    ```java
    Enum Card {
      TWO(2);  
      //생성자, 변수 생략
    }
    
    Enum Wheel {
      MOTOR_CYCLE(2);
      //생성자, 변수 생략
    }
    
    class Test {
      public static void main(String[] args) {
        System.out.println(Card.TWO == Wheel.MOTOR_CYCLE);
        //java: incomparable types: enum_test.Card and enum_test.Wheel (값은 같지만 타입이 다르다.)
      }
    }
    ```

- 각각의 상수들은 객체이며, 각각의 상수는 public static final이 선언되어있다. `static final`이 선언되어있으므로 동등성을 보장하고 `==`을 통해서 비교가 가능하다.

  ```java
  classd Card {
    static final Card TWO = new Card(2);
    
    private int value;
    
    private Card (int value) {
      this.value = value;
    }
  }
  ```

  <br>

### (2) Enum 정의 및 사용법

1. 정의하는 방법 : Enum 이름을 명시하고, 괄호{} 안에 상수 이름을 나열한다.

   ```java
   Enum 열거형 이름 {
     enum 열거형 이름 {상수명1, 상수명2, 상수명3}
   }
   ```
   <br>

2. 열거형 상수 비교

   - Enum의 상수들은 각각 `==`선언해서 비교가 가능하다. 

   - 비교 연산자의 경우, `compareTo()` 메서드를 사용한다. `>, < 사용불가`

     ```java
     public class Test {
       public static void main (String[] args) {
         System.out.println(Card.TWO.compareTo(Card.ONE)); //상수에 값을 설정하지 않을 경우, ordinal로 비교한다.
       }
     }
     ```

     <br>

3. 열거형 추상 메서드 추가하기

   - 각각의 상수마다 다른 연산을 지정해야 하는 경우가 발생하다. 이 때, 추상 메서드를 이용해서 다른 연산을 처리할 수 있도록 한다.

     ```java
     public enum Calculator {
         ADD("+") {
             @Override
             double calculate(double a, double b) {
                 return a + b;
             }
         }
       	//뺄셈, 곱하기, 나눗셈
           ;
     
         private String operator;
     
         abstract double calculate(double a, double b);
     
         Calculator(String operator) {
             this.operator = operator;
         }
     }
     ```

     <br>

   - 추상 메서드를 선언하는 것도 좋지만, `FunctionalInterface`와 `lambda`를 사용하면 코드가 조금 더 간결해진다.

     ```java
     public enum Calculator {
         ADD("+", (a, b) -> a + b),
         SUBSTRACT("-", (a, b) -> a - b),
         MULTIPLY("*", (a, b) -> a *  b),
         MODIFY("/", (a, b) -> a / b);
     
         private String operator;
     
         private BiFunction<Double, Double, Double> calculate;
     
         Calculator(String operator, BiFunction<Double, Double, Double> calculate) {
             this.operator = operator;
             this.calculate = calculate;
         }
     
         public double calculate(double a, double b) {
             return this.calculate.apply(a, b);
         }
     }
     
     ```

     <br>

   <br>



## 2. enum이 제공하는 메서드 (values() + valueOf())

| 메서드                                    | 설명                                                       |
| ----------------------------------------- | ---------------------------------------------------------- |
| T[] values()                              | 해당 enum 타입에 정의된 상수 배열을 반환한다.              |
| Class<E> gettDeclaringClass()             | 열거형의 객체를 반환한다.                                  |
| String name()                             | 열거형 상수의 이름을 문자열로 반환한다.                    |
| int ordinal()                             | 열거형 상수가 정의돈 순서를 반환한다. (index는 0부터 시작) |
| T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다.  |

<br>

### (1) values()

- 해당 클래스의 상수를 배열에 저장해서 반환하는 메서드

  ```java
  public class Test {
      public static void main(String[] args) {
          for (Calculator value : Calculator.values()) {
              System.out.println(value);
          }
      }
  }
  
  
  //결과
  //ADD
  //SUBSTRACT
  //MULTIFLY
  //MODIFY
  ```

  <br>

  

### (2) valueOf()

- 문자열 형태로 상수 이름을 입력하면 해당하는 상수를 반환하는 메서드

- java.lang.enum에 구현된 values() 로직

  ```java
  public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                              String name) {
      T result = enumType.enumConstantDirectory().get(name);
      if (result != null)
          return result;
      if (name == null)
          throw new NullPointerException("Name is null");
      throw new IllegalArgumentException(
          "No enum constant " + enumType.getCanonicalName() + "." + name);
  }
  ```

  <br>

### (3) of() : value를 통해서 field를 반환하는 메서드

- 이전에 CS 좌표 미션에서 사용했던 of 메서드

  ```java
  import java.util.Arrays;
  import java.util.function.Function;
  
  public enum FigureKind {
      LINE(Line.NUMBER_OF_POINTS, Line::new),
      TRIANGLE(Triangle.NUMBER_OF_POINTS, Triangle::new),
      RECTANGLE(Rectangle.NUMBER_OF_POINTS, Rectangle::new);
  
      private int numberOfPoints;
      private Function<Points, Figure> mapper;
  
      FigureKind(int numberOfPoints, Function<Points, Figure> mapper) {
          this.numberOfPoints = numberOfPoints;
          this.mapper = mapper;
      }
  
      public Figure createShape(Points points) {
          return mapper.apply(points);
      }
  
      public static FigureKind of(int numberOfPoints) {
          return Arrays.stream(values())
                  .filter(figureKind -> figureKind.numberOfPoints == numberOfPoints)
                  .findAny()
                  .orElseThrow(() -> new IllegalArgumentException("일치하는 도형이 없습니다."));
      }
  }
  ```

  <br>

<br>

## 3. java.lang.Enum

```java
public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {
    /**
     * The name of this enum constant, as declared in the enum declaration.
     * Most programmers should use the {@link #toString} method rather than
     * accessing this field.
     */
    private final String name;
  
      public final String name() {
        return name;
    }
}
```

- java.lnag.Enum은 모든 자바 열거형의 조상이다. 모든 열거형은 Enum 클래스를 상속받기 때문에 enum type은 별도로 상속 받을 수 없다.
- 위에 선언한 메서드들은 해당 클래스에 선언되 있지만, 대부분의 method는 final로 선언되어 있기 때문에 별도의 overrding을 할 수 없다.

<br><br>

## 4. EnumSet

<img src="https://media.geeksforgeeks.org/wp-content/uploads/20200911115830/EnumSetinJava.png" width="700" height="500">

- Enum type을 사용하기 위한 Set 구현체

- 특징

  - EnumSet은 AbstractSet class를  상속하고 Set interface를 구현한다.

  - EnumSet은 Java Collections Framework이지만, 동기화(Synchornized) 되지 않는다.

    (동기식으로 사용하기 위해서는 Collections.synchronizedMap 사용 또는 외부에서 동기화 구현)

  - 모든 메서드는 arithmetic bitwise operation을 사용하기 때문에 모든 기본 연산의 시간 복잡도가 O(1)이다.
    - bitwise operation : 비트 연산

  - null 객체를 허용하지 않고 그렇게 하면 NullPointerException을 발생시킨다.

- 예시

  ```java
  public class EnumSetTest {
      public static void main(String[] args) {
          EnumSet<Calculator> enumSet1, enumSet2, enumSet3, enumSet4;
  
          enumSet1 = EnumSet.of(Calculator.ADD, Calculator.MODIFY);
          enumSet2 = EnumSet.complementOf(enumSet1);
          enumSet3 = EnumSet.allOf(Calculator.class);
          enumSet4 = EnumSet.range(Calculator.SUBSTRACT, Calculator.MULTIPLY);
  
          System.out.println("enumSet1 = " + enumSet1);
          System.out.println("enumSet2 = " + enumSet2);
          System.out.println("enumSet3 = " + enumSet3);
          System.out.println("enumSet4 = " + enumSet4);
      }
  }
  
  ```

  <br>



## Reference

- EnumSet ni java [GeeksforGeeks] : https://www.geeksforgeeks.org/enumset-class-java/

- Enum[어썸오 블로그] : https://wisdom-and-record.tistory.com/52

  