# Enum

- 여러 상수를 선언할 때 편리하게 선언할 수 있는 방법
- 열거형 상수는 `==`를 사용하여 비교할 수 있다.

```java
enum 열거형이름 { 상수명1, 상수명2, ... }
```



### 장점

1. 데이터의 그룹화 및 관리에 용이하다. 
2. Lambda를 사용하면 활용성을 극대화 할 수 있다.

```java
    import java.util.function.BiFunction;
    
    public enum Operator {
    	PLUS("더하기", (a, b) -> (a + b)),
    	MINUS("빼기", (a, b) -> (a - b)),
    	MULTIPLY("곱하기", (a, b) -> (a * b)),
    	DIVIDE("나누기", (a, b) -> (a / b));
    
    	private final String name;
    	private final BiFunction<Double, Double, Double> biFunction;
    
    	Operator(String name, BiFunction<Double, Double, Double> biFunction) {
    		this.name = name;
    		this.biFunction = biFunction;
    	}
    
    	public Double calculate(double a, double b) {
    		return this.biFunction.apply(a,b);
    	}
    }
```



## Singleton

Serializable 인터페이스를 구현하고 있기 때문에 전통적인 방식에서 deserialization 시 새로운 인스턴스가 생성되지 않는지 보장해야했던 것과 달리 JVM에 의해 싱글톤으로 관리되는 것이 보장된다.

>  Enum에서 선언한 상수들은 클래스가 로딩될 때 하나의 인스턴스로 생성되어 싱글톤 형태로 관리된다.  

1. javap 명령어(`javap -c 클래스이름`)를 통해 enum 클래스파일을 원시 코드로 디스어셈블해보면 enum의 각 상수가 정의한 enum 타입의 객체를 생성하는 것을 확인할 수 있다.
2. 각 상수가 싱글톤으로 생성되기 때문에 enum의 멤버 변수는 thread-safe하다. &rarr; double-checked locking을 안해도 된다.
   - double-checked locking: https://www.baeldung.com/java-singleton-double-checked-locking



## java.lang.Enum

- 모든 열거형의 조상 클래스

  | 메서드                                    | 설명                                                      |
  | ----------------------------------------- | --------------------------------------------------------- |
  | T[] values()                              | 해당 enum 타입에 정의된 상수 배열을 반환한다.             |
  | Class<E> getDeclaringClass()              | 열거형의 Class객체를 반환한다.                            |
  | String name()                             | 열거형 상수의 이름을 문자열로 반환한다.                   |
  | int ordinal()                             | 열거형 상수가 정의된 순서(0부터 시작) 을 정수로 반환한다. |
  | T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다. |

- 모든 열거형은 내부적으로 java.lang.Enum 클래스를 상속받고 있기 때문에 다른 클래스를 상속받을 수 없다. 

```java
public abstract class Enum<E extends Enum<E>>
extends Object
implements Comparable<E>, Serializable
```



## EnumSet

- 열거형 타입과 함께 사용되도록 특화된 Set 구현체
- Set 인터페이스를 구현하고, AbstactSet을 상속한다.

![img](https://www.baeldung.com/wp-content/uploads/2018/10/EnumSet-1-2.jpg)



- 같은 enum 타입에 속한 값들만 포함할 수 있다.
- null 값을 추가할 수 없으며, null을 추가하려고 하면 NullPointerException이 발생한다.
- enum에 선언된 순서대로 요소가 저장도니다.
- fail-safe iterator를 사용하여 컬렉션이 순회 중에 수정되도라도 ConcurrentModificationException이 발생하지 않는다.
  - fail-fast는 동작 중 오류가 발생하면 바로 오류를 알리고 작업을 중단닿는 반면, fail-safe는 동작 중 오류가 발생해도 작업을 계속 진행한다.
  - ArrayList, HashMap 등 java.util 패키지에 속한 컬렉션들의 기본 iterator는 fail-fast iterator를 사용한다. 

### 메서드

| Modifier and Type                      | Method                             | Description                                                  |
| :------------------------------------- | :--------------------------------- | :----------------------------------------------------------- |
| `static <E extends Enum<E>>EnumSet<E>` | `allOf(Class<E> elementType)`      | Creates an enum set containing all of the elements in the specified element type. |
| `EnumSet<E>`                           | `clone()`                          | Returns a copy of this set.                                  |
| `static <E extends Enum<E>>EnumSet<E>` | `complementOf(EnumSet<E> s)`       | Creates an enum set with the same element type as the specified enum set, initially containing all the elements of this type that are *not* contained in the specified set. |
| `static <E extends Enum<E>>EnumSet<E>` | `copyOf(Collection<E> c)`          | Creates an enum set initialized from the specified collection. |
| `static <E extends Enum<E>>EnumSet<E>` | `copyOf(EnumSet<E> s)`             | Creates an enum set with the same element type as the specified enum set, initially containing the same elements (if any). |
| `static <E extends Enum<E>>EnumSet<E>` | `noneOf(Class<E> elementType)`     | Creates an empty enum set with the specified element type.   |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E e)`                          | Creates an enum set initially containing the specified element. |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E e1, E e2)`                   | Creates an enum set initially containing the specified elements. |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E first, E... rest)`           | Creates an enum set initially containing the specified elements. |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E e1, E e2, E e3)`             | Creates an enum set initially containing the specified elements. |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E e1, E e2, E e3, E e4)`       | Creates an enum set initially containing the specified elements. |
| `static <E extends Enum<E>>EnumSet<E>` | `of(E e1, E e2, E e3, E e4, E e5)` | Creates an enum set initially containing the specified elements. |
| `static <E extends Enum<E>>EnumSet<E>` | `range(E from, E to)`              | Creates an enum set initially containing all of the elements in the range defined by the two specified endpoints. |

- Color 열거형의 모든 요소를 포함한 EnumSet 생성

```java
EnumSet.allOf(Color.class);
```

- Color 열거형의 비어있는 컬렉션 생성

```java
EnumSet.noneOf(Color.class);
```

- 범위 내 EnumSet 생성

```java
EnumSet.range(Color.YELLOW, Color.BLUE);
[YELLOW, GREEN, BLUE]
```

- 특정 요소를 제외한 EnumSet 생성

```java
EnumSet.complementOf(EnumSet.of(Color.BLACK, Color.WHITE));
[RED, YELLOW, GREEN, BLUE]
```

- 복사

```java
EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
```

## 참고
### EnumUtils 클래스
- [EnumUtils 공식문서](https://commons.apache.org/proper/commons-lang/javadocs/api-release/org/apache/commons/lang3/EnumUtils.html)
```
    EnumUtils.isValidEnum(MembershipType.class, membershipId)
```
- [EnumUtils.java](https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/EnumUtils.java)
### parse, don't validate
```java
public enum Status {
  OPEN(true), 
  CLOSE(false);

  public static Status parse(String statusStr) {
    return Status.valueOf(statusStr.toUpperCase()); 
  }
    
  public Boolean getBoolean() { return this.bool }
}
```    
    
    
---

***Source***

- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Enum.html

- 자바의 정석 Chapter 12

- https://wisdom-and-record.tistory.com/52

- https://velog.io/@kyle/%EC%9E%90%EB%B0%94-Enum-%EA%B8%B0%EB%B3%B8-%EB%B0%8F-%ED%99%9C%EC%9A%A9

- https://www.baeldung.com/a-guide-to-java-enums

- https://lng1982.tistory.com/129

- https://www.geeksforgeeks.org/advantages-and-disadvantages-of-using-enum-as-singleton-in-java/

- https://www.baeldung.com/java-enumset

- https://www.baeldung.com/java-fail-safe-vs-fail-fast-iterator

- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/EnumSet.html
