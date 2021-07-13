# Week15. 람다식

# 학습할 것 (필수)

- 람다식 사용법
- 함수형 인터페이스
- Variable Capture
- 메소드, 생성자 레퍼런스

<br>

## 1. 람다식 사용법

- 람다식(Lambda expression) : 메서드를 하나의 식(expression)으로 표현한 것.

  - 메서드 : 메서드 이름과 반환값이 존재하는 형태(void 제외)

    - 자바의 메서드는 일급객체가 아니기 때문에 매개변수나 리턴값을 사용할 수 없다.

  - 람다식은 메서드명이 없기 때문에 익명함수(anonymous function)라고 부른다.

    - 메서드와 함수의 차이?

      ```
      메서드 :특정 클래스에 반드시 속해야 한다는 제약이 있다.
      함수 : 람다식을 통해 메서드가 하나의 독립적인 기능을 한다.
      ```

  <br>

- 람다식의 장점

  - 간결하면서 이해하기 쉽다.

  - 메서드를 변수처럼 사용하는 것이 가능해진다.

    <br>

- 람다식의 단점

  - 무명함수이므로 재사용이 불가능하다.

  - 디버깅이 까다롭다.

  - 재귀로 사용할 경우, 부적합하다.

    <br>

- 람다식 표현

  ```java
  (int a, int b) -> { return a > b ? a : b}
  
  // 블록 생략
  // return 생략 가능
  // 식(expression)이므로 ; 생략 가능
  (int a, int b) -> a > b ? a : b
  
  // 타입이 없고 매개 변수가 하나일 경우, 괄호 생략 가능
  // 타입이 있을 경우, 괄호 생략 불가능
  (a, b) -> a > b ? a : b
  
  // 괄호 안에 single statement의 경, 블록 생략 가능
  // return이 포함된 경우는 괄호{} 생략 불가능
  (String name, int i) -> System.out.println(name + " " + i);
  ```

  <br>

- 람다 사용법

  - 메서드를 익명 함수 형태로 바꾸고 싶은 경우,

    -  메서드의 이름과 반환타입을 제거 + 매개변수 선언부와 바디 사이에  `->`를 추가한다.

      ```java
      반환타입 메서드이름 (매개변수 선언) {
        문장들
      }
      
      (매개변수 선언) -> {
        문장들
      }
      
      ```

      <br>

  - 예시

    ```java
    public class Main {
        public static void main(String[] args) {
            List<Integer> list = new ArrayList<>();
    
            for (int i = 10; i > 0; i--) {
                list.add(i);
            }
    				
          	//람다식을 사용한 예시(내림차순 정렬)
            Collections.sort(list, (a, b) -> - a.compareTo(b));
    
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }
    
    ```

    <br>

  <br>

  ## 2. FunctionalInterface

  - 하나의 추상 메서드를 가지고 있는 인터페이스 (람다식을 다루기 위한 인터페이스)

  - 인터페이스 상단에 `@FunctionalInterface`를 명시한다.

    ```java
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface FunctionalInterface {}
    ```

    

  - 람다 표현식(lambda expression)을 사용하여 함수형 인터페이스의 인스턴스를 나타낼 수 있다.

  - Ex) Runnable, Comparable, ActionListener

    <br>

    

  ### 함수형 인터페이스 사용 방법

  1. interface를 생성

  2. 추상 메서드 하나를 정의
  3. 상단에 `@FunctionalInterface` 선언

  ```java
  @FunctionalInterface
  public interface ApplePredicate {
      boolean test(Apple apple); //추상 메서드
  }
  
  ```

  <br>

  ### `java.util.function` 패키지

  - Runnable을 제외하고 java.util.function 패키지 안에 있다.
  - [oracle 공식 문서](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

  | 인터페이스          | 메서드             | 매개변수 갯수 | 리턴 값 |
  | ------------------- | ------------------ | ------------- | ------- |
  | Runnable            | void run()         | 0             | void    |
  | Supplier            | T get()            | 0             | T       |
  | Consumer            | void accept(T t)   | 1             | void    |
  | Function<T, R>      | R apply(T t)       | 1             | R       |
  | Predicate           | boolean test(T t)  | 1             | boolean |
  | BiFunction<T, U, R> | R apply(T, t, U u) | 2             | R       |

  <br>

  ```java
  import java.util.*;
  
  class Main {
      public static void main(String[] args) {
          List<Integer> list =
                  Arrays.asList(-2, -1, 0, 1, 2);
  
          Predicate<Integer> predicate = x -> x > 0;
  
          for (Integer element : list) {
              if (predicate.test(element)) {
                  System.out.println(element);
              }
          }
      }
  }
  ```

  <br>

### 스트림(Stream)

- 이전에 배열 혹은 컬렉션의  데이터를 접근하기 위해 반복문이나 반복자(iterator)를 사용해서 매번 새로운 코드를 작성해야 했다.

- 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공괸 결과를 얻을 수 있다.

- `스트림 생성 - 중간 연산(스트림 변환) - 최종 연산(스트림 사용)` 순으로 작업이 이루어진다.

  - `지연(lazy) 연산`이므로 최종 연산을 수행하기 전까지 중간 연산은 수행하지 않는다.

- 스트림을 사용할 경우, 위의 반복문(for)를 아래와 같이 변경할 수 있다.

  ```java
  class Main {
    public static void main(String[] args) {
  		list.stream() 									 // 1.스트림 생성
      	.filter(number -> number > 0) 						 // 2.중간 연산(스트림 생성)
      	.forEach(System.out::println); // 3. 최종 연산 (스트림 사용)
  	}
  }
  ```

  <br>

### 컬렉션과 람다

- Java 1.8부터 Collection Framework에서 `FunctionalInterface`를 사용하는 메서드들이 추가되었다.

  | 인터페이스 | 메서드                                           | 설명                             |
  | ---------- | ------------------------------------------------ | -------------------------------- |
  | Collection | boolean removeI(Predicate<E> filter)             | 조건에 맞는 요소를 삭제          |
  | List       | void replaceAll(UnaryOperator<E> operator)       | 모든 요소를 변환하여 대체        |
  | Iterable   | void forEach(Consumer<T> action)                 | 모든 요소에 작업 action을 수행   |
  | Map        | V compute(K key, BiFunction<K, V, V> f)          | 지정된 키 값에 작업 f를 수행     |
  |            | V computeIfAbsent(K key, Function<K, V> f)       | 키가 없으면, 작업 f 수행 후 추가 |
  |            | V computeIfPresent(K key, BiFunction<K, V, V> f) | 지정된 키가 있을 때, 작업 f 수행 |
  |            | V merge(K key, v valuie, Bifuction<V, V, V> f)   | 모든 요소에 병합 작업을 f를 수행 |
  |            | void forEach(BiConsumer <K, V> action)           | 모든 요소에 작업 action을 수행   |
  |            | void replaceAll(BiFuntion<K, V, V> f)            | 모든 요소에 치환작업 f를 수행    |

  <출처: 자바의 정석 3판(p804)>

  <br>

## 3. Variable Capture

- 다른 언어의 **`closure`**와 같은 의미

- 람다식을 사용할 때 외부 변수를 람다식 내부에서 접근할 수 있다.

  - Closure로 불리는 이유 : 함수의 scope이 확장되서 `int a`까지 덮어버린다(close over) -> "Closure"

  ```java
  int a = 100;
  someMethod( x -> x * 2 + a); //변수 a가 함수에 참조한다. -> closure
  ```

  <br>

- effectively final : final을 명시하지 않아도 final인 상태

  - 자바 런타임 진행 시, 익명 클래스의 number가 값의 정보(value)를 가지고 있으므로 number의 정보 변경 금지.

  - 그래서 number에 `final`을 선언하거나, `effectively final`이 작용해서 변경이 불가능하다.

  - [예제1. effectively final + closure]

    ```java
    private void test() {
    	int number = 100; 
        //effectively final : final이지만 final로 표시 안된 상태
        //자바8 이전-> final 선언 필요
        //자바8 이후 -> final 선언 필요X
        
        testClosure("Anonymous Class", new Runnable() {
        	@Override
            public void run() {
            	// number = 20; //error
            	System.out.println(number); //effectively final로 사용되는 요소
                				   //외부의 number 참조 : closure
            }
        });
        
        testClosure("Lambda Expression", () -> System.out.println(number));
    }
    ```

    <br>

  - [예제2. scope 체크]

    ```java
     private void test4() {
    
        int number = 100;
    
        testClosure("Anonymous Class", new Runnable() {
          @Override
          public void run() {
            int number = 50; // no compile-time error
            System.out.println(number); //anonymous class의 scope는 new Runnable 내부이기 때문
          }
        });
    
        testClosure("Lambda Expression", () -> {
          // int number = 50; 
          // compile-time error : 이미 참조하고 있기 때문 -> class의 인스턴스 변수
          System.out.println(number); //-> lambda expression의 scope는 closed class
        });
    
      }
    ```

    <br>

  - [예제3. 외부 변수에 접근하고 싶은 경우]

    ```java
    public class ClosureExamples {
      private int number = 999;
    
      public static void main(String[] args) {
        new ClosureExamples().test3();
      }
    
    
      private void test() {
        int number = 100;
    
        testClosure("Anonymous Class", new Runnable() {
          int number = 20;
          @Override
          public void run() {
            System.out.println(number); //결과 20
          }
        });
    
        testClosure("Lambda Expression", () -> System.out.println(number)); //결과 100
    
      }
    ```
    
    <br>
    
    - **this.number을 사용할 수 없는 이유**
      - number의 this는 ClosureExample에 소속된 것이 아님
      -  **new Runnable()의 scope**이다.
      - Runnable 내 ClosureExample의 number를 사용하고 싶은 경우
        - **ClosureExamples.this.number** 선언.
    
     
    
    - **lambda expression에서 this.number**는 **ClosureExample의 인스턴스 변수**이다.
    - 이유 : **Java는 lexical scope을 따르고 있어** lambda의 scope가 **closed class(ClosureExamples)**이다.
    - 그러므로 lambda expression의 this는 **ClosureExamples의 this를 따른다.**
    
     
    
    - **scope : 참조 대상 식별자(indentifier)을 찾아내기 위한 규칙**
      - **lexical scope(=static scope)** : **함수 선언 시점**에따라 **상위 스코프가 결정**하는 방식.
      - **Dynamic scope** : **함수의 호출 위치**에 따라 **상위 스코프가 결정**되는 방식.
    
    <br>

- 접근 가능 대상: `static`,`instance`, `local` variable

- closure의 개념 공부하기 좋은 영상 : [케빈님의 모던 자바8 유튜브 영상](https://www.youtube.com/watch?v=pjtk7vvryio) 

  

<br>

## 4. 메서드, 생성자 레퍼런스

### Method Reference

- 람다식을 더욱 간결하게 표현할 수 있는 방법이며, 람다식이 하나의 메서드만 호출하는 경우에 사용이 가능하다.

  ```java
  class Sample {
    public static void main(String[] args) {
      List<String> stringList = list.stream()
                      .map(n -> String.valueOf(n)) //현재 사용하고 있는 람다식
                      .collect(Collectors.toList());
      
      List<String> stringList = list.stream()
                      .map(String::valueOf) //람다식을 메서드 참조로 변경한 형태
                      .collect(Collectors.toList());
    }
  }
  ```

  

- 메서드 참조 방법

  | 종류                           | 람다                       | 메서드 참조       |
  | ------------------------------ | -------------------------- | ----------------- |
  | static 메서드 참조             | (x) -> ClassName.method(x) | ClassName::method |
  | 인스턴스 메서드 참조           | (Obj.x) -> obj.mehtod(x)   | ClassName::method |
  | 특정 객체 인스턴스 메서드 참조 | (x) -> obj.method(x)       | obj::method       |
  | 생성자의 메서드 참조           | () -> new obj();           | obj::new          |

  <출저: 자바의 정석 3판(p.813)>

  <br>

  ```java
  //static 메서드 참조
  str -> String.valueOf(str) //람다
  String::valueOf //메서드 참조
  
  
  //2. 인스턴스 메서드 참조
  (s1, s2) -> s1.equals(s2) //람다
  String::equals //메서드 참조
    
  //3. 특정 객체 인스턴스 메서드 참조
  (x) -> obj.equals(x) //람다
  obj::equas //메서드 참조
    
  //4. 생성자 메서드 참조
  () -> new String(); //람다
  String::new //메서드 참조
  ```

<br>

## 5. 동작 파라미터화

- 동작 파라미터화 : 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블럭

  (메서드의 파라미터를 통해 동작을 결정한다.)

  <br>

- 동작 파라미터화의 필요성

  1. 소프트웨어 공학의 `DRY(Don't repeat yourself)` 원칙을 지킬 수 있다.

  2. 변화하는 요구사항에 유연하게 대응할 수 있다.

     ```java
     public static List<Apple> filterApplesBuyWeight(List<apple> inventory, int weight) { //사과 색으로 조건을 작성하고 싶은 경우, parameter도 변경해야 한다.
       List<apple> result = new ArrayList<>();
     
       //1. 목록 검색, 필터링 조건을 적용하는 부분에서 중복이 발생한다.
       for (Apple apple : inventory) { 
         
         //2. 조건을 변경할 경우, 메서드의 내부 코드를 계속해서 변경해야 한다.
         if( apple.getWeight() > weight) { 
           result.add(apple);
         }
       }
     }
     ```

     <br>

- 동작 파라미터화하는 방법

  1. 함수형 인터페이스 정의
  2. 함수형 인터페이스 구현체 정의
  3. 메서드 작성

  <br>

  1. 함수형 인터페이스 정의

     ```java
     @FunctionalInterface
     public interface ApplePredicate {
         boolean test(Apple apple);
     }
     ```

     <br>

  2. 함수형 인터페이스 구현체 정의

     ```java
     public class AppleGreenColorPredicate implements ApplePredicate {
         @Override
         public boolean test(Apple apple) {
             return Color.GREEN.equals(apple.getColor());
         }
     }
     
     public class AppleHeavyWeightPredicate implements ApplePredicate {
         @Override
         public boolean test(Apple apple) {
             return apple.getWeight() > 150;
         }
     }
     ```

     <br>

  3. 메서드 작성

     ````java
     
     public class Apples {
         private final List<Apple> apples;
     
         public Apples(List<Apple> apples) {
             this.apples = Collections.unmodifiableList(apples);
         }
     
         public List<Apple> filterApples(ApplePredicate appleFilter) {
             List<Apple> result = new ArrayList<>();
     
             for (Apple apple : apples) {
                 if(appleFilter.test(apple)) {
                     result.add(apple);
                 }
             }
             return result;
         }
       	
       	//toString Override code 생략
     }
     ````

     <br>

  4. 테스트

     ```java
     import java.util.*;
     
     public class Main {
         public static void main(String[] args) {
             Apples apples = new Apples(Arrays.asList(
                     new Apple(Color.GREEN, 100),
                     new Apple(Color.RED, 200),
                     new Apple(Color.GREEN, 300)
             ));
     
             List<Apple> list = apples.filterApples(new AppleGreenColorPredicate());
             System.out.println(list);
     
             list = apples.filterApples(new AppleHeavyWeightPredicate());
             System.out.println(list);
         }
     }
     
     //결과1 : [Apple{color=GREEN, weight=100}, Apple{color=GREEN, weight=300}]
     //결과2 : [Apple{color=RED, weight=200}, Apple{color=GREEN, weight=300}]
     ```

     <br>

  <br>

## Reference

### 1. 스트림

- [TCP school] 스트림 API - http://tcpschool.com/java/java_stream_concept
- [Eric Han's IT Blog] Java Stream 정리 : https://futurecreator.github.io/2018/08/26/java-8-streams/

<br>

### 2. Closure

- [Kevin TV] 모던 자바(자바8) 못다한 이야기 - 09 - 01 Closure : https://www.youtube.com/watch?v=pjtk7vvryio
- [Kevin TV] Kevin 님 github 예시 코드 - https://github.com/Kevin-Lee/modern-java-untold/blob/52e752a37d1d6f5c6425af7da11dcd9a7ba2fad7/src/main/java/cc/kevinlee/modernjava/e09_closure/ClosureExamples.java#L108

