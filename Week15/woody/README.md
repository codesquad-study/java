# [Week15] 람다식



- 람다식 사용법
- 함수형 인터페이스
- Variable Capture
- 메소드, 생성자 레퍼런스



## 람다식 사용법

### 람다식

메소드를 하나의 식으로 표현한 것, 익명 함수를 단순화한 형태이기도 하다.

JDK 1.8부터 람다식이 도입되었고, 이로 인해 자바라는 객체 지향 언어에서도 함수형 언어를 사용할 수 있게 되었다.

이전에 익명 내부 클래스(Anonymous Inner Class) 형태로 존재했던 부분을 람다식으로 더 간단하게 표현할 수 있게 되었다.



### 람다식 작성

```java
// 함수형 인터페이스 메소드
반환타입 메소드명 (매개변수) {
    문장
}

// 람다식
(매개변수) -> {
    문장
}
```

- 메소드에서 이름과 반환타입을 제거하고, **람다 파라미터들**과  **람다 바디**만을 정의한 형태로 작성할 수 있다
  -  `(lambda parameters) -> { lambda body }`



## 함수형 인터페이스

- 추상 메소드를 딱 하나만 가지고 있는 인터페이스 (SAM interface - interface with only one abstract method)
  - default 메소드 혹은 static method는 여러 개가 있어도 상관없다. 다만, 추상 메소드가 두 개 이상이면 안된다.
  - 람다식과 인터페이스 메서드가 1:1로 연결될 수 있도록 하기 위함
- @FunctionalInterface 어노테이션 사용



### 자바의 함수형 프로그래밍

1. 함수를 일급객체(First Class Object)로 사용 가능
   - 함수도 객체처럼 변수나 함수의 인자처럼 리터럴하게 다룰 수 있다.
   - 함수 리터럴 : 함수 이름을 변수처럼 사용
     - 메소드 매개변수, 리턴 타입, 변수로 사용할 수 있다.
2. 순수 함수(Pure Function)
   - 다른 상태에 의존하지 않는 함수 (Stateless)
   - 따라서, 상태 값에 의존하는 함수는 순수 함수가 될 수 없다.
   - 외부 상태에 의존하지 않기 때문에, 언제나 같은 값을 넣으면 항상 같은 값이 나오는 것을 보장한다.
   - 외부에 있는 값을 변경하는 것은 안되지만 참조해서 사용하는 것은 가능하다. 단 그 사용된 값은 자동으로 final처럼 간주되어 해당 값은 변경이 불가능하다.
3. 고차 함수(High-Order Function)
   - 함수가 함수를 매개변수로 받을 수 있다
   - 함수가 함수를 리턴할 수 있다
   - 함수가 일급 객체임이 보장된다면 고차 함수의 조건도 자동으로 충족된다.
4. 불변성



### **java.util.function Package**

### 기본 함수형 인터페이스

| 함수형 인터페이스  | 메소드            | 함수 디스크립터 | 기본형 특화                                                |
| ------------------ | ----------------- | --------------- | ---------------------------------------------------------- |
| Predicate<T>       | boolean test(T t) | T -> boolean    | IntPredicate, LongPredicate, DoublePredicate               |
| Consumer<T>        | void accept(T t)  | T -> void       | IntConsumer, LongConsumer, DoubleConsumer                  |
| Function<T, R>     | R apply(T t)      | T -> R          | IntFunction, IntToDoubleFunction, etc ...                  |
| Supplier<T>        | T get()           | () -> T         | BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier |
| java.lang.Runnable | void run()        | () ->           |                                                            |



### 매개변수와 반환타입이 일치하는 함수형 인터페이스

| 함수형 인터페이스 | 메소드            | 함수 디스크립터 | 기본형 특화                                                 |
| ----------------- | ----------------- | --------------- | ----------------------------------------------------------- |
| UnaryOperator<T>  | T apply(T t)      | T -> T          | IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator    |
| BinaryOperator<T> | T apply(T t, T t) | (T, T) -> T     | IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator |



### 매개변수가 2개인 함수형 인터페이스

| 함수형 인터페이스   | 메소드                 | 함수 디스크립터   | 기본형 특화                                                  |
| ------------------- | ---------------------- | ----------------- | ------------------------------------------------------------ |
| BiPredicate<T, U>   | boolean test(T t, U u) | (T, U) -> boolean |                                                              |
| BiConsumer<T, U>    | void accept(T t, U u)  | (T, U) -> void    | ObjIntConsumer, ObjLongConsumer, ObjDoubleConsumer           |
| BiFunction<T, U, R> | R apply(T t, U u, R r) | (T, U) -> R       | ToIntBiFunction<T, U>, ToLongBiFunction<T, U>, ToDoubleBiFunction<T, U> |





## Variable Capture

### Scope level (in python)

```python
x = "Global Scope"

def outer_func():
    x = "Enclosing Scope"
    
    def inner_func():
        x = "Local Scope"
        print("I found x in", x)
    
    inner_func()
    
outer_func()
```



람다식 내부에서 외부 지역변수를 사용할 경우 값을 복사해서 사용하는데 이 과정을 `Lambda Capturing(람다 캡쳐링)`이라고 한다

(조큼 더 공부가 필요해요;;)



## 메소드, 생성자 레퍼런스

### 메소드 참조

메소드 레퍼런스를 사용해서 람다를 간결하게 표현할 수 있다.

1. 정적 메소드 참조

   - Integer::parseInt ( Integer 클래스의 정적 메소드 parseInt 참조 )
   -  👉 **타입::스태틱 메소드**

2. 인스턴스 메소드 참조

   - String::length ( String 객체의 length 메소드 참조 )
   - 👉 **타입::인스턴스 메소드**

3. 기존 객체의 인스턴스 메소드 참조

   -  expensiveTransaction::getValue ( Transaction 타입의 지역변수 expensiveTransaction의 getValue() 메소드 참조 )
   - 👉 **객체 래퍼런스::인스턴스 메소드** 

   

### 람다와 메소드 레퍼런스 예시

| 람다                                     | 메소드 참조                       |
| ---------------------------------------- | --------------------------------- |
| (Apple apple) -> apple.getWeight()       | Apple::getWeight                  |
| () -> Thread.currentThread().dumpStack() | Thread.currentThread()::dumpStack |
| (str, i) -> str.substring(i)             | String::substring                 |
| (String s) -> System.out.println(s)      | System.out::println               |
| (String s) -> this.isValidName(s)        | this::isValidName                 |



### 생성자 참조

- 클래스 명과 new 키워드를 사용해서 기존의 생성자 참조를 만들 수 있다

```java
ClassName::new
```



**생성자 참조 예시**

- 파라미터 갯수에 따라 적절한 함수형 인터페이스를 사용하거나 새로 정의하여 사용하면 된다.

1. 디폴트 생성자

```java
Supplier<Apple> appleMaker = Apple::new; // 메서드 참조
Supplier<Apple> appleMaker = () -> new Apple(); // 람다식
Apple apple = appleMaker.get();
```

2. Apple(Integer weight) 시그니처 생성자

```java
Function<Integer, Apple> appleMaker = Apple::new; // 메서드 참조
Function<Integer, Apple> appleMaker = (weight) -> new Apple(weight); // 람다식
Apple apple = appleMaker.apply(100);
```

3. Apple(String color, Integer weight) 시그니처 생성자

```java
BiFunction<Color, Integer, Apple> appleMaker = Apple::new; // 메서드 참조
BiFunction<Color, Integer, Apple> appleMaker = (color, weight) -> new Apple(color, weight); // 람다식
Apple apple = appleMaker.apply("RED", 100);
```

4. 파라미터가 3개인 시그니처 생성자 - 새로 정의하여 사용

```java
@FunctionalInterface
public interface TriFunction<A, B, C, R> {
  R apply(A a, B b, C c);
}
TriFunction<Color, Integer, Integer, Apple> appleMaker = Apple::new;
```





## Reference

- https://www.geeksforgeeks.org/functional-interfaces-java/

- 모던 자바 인 액션