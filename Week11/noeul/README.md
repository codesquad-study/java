# 11주차 과제: Enum



# enum 정의하는 방법

- 열거형(enums): 커스텀 데이터 타입 상수들의 집합
- 자바의 열거형은 `값`과 `타입`을 관리하기 때문에 보다 논리적인 오류를 줄일 수 있음.

```java
enum Number {
  ONE(1), TWO(2), THREE(3), FOUR(4);

  private final int value;

  private Number(int number) {
     value = number;
  }

  public int getValue() {
     return value;
  }
}
```

- 아래와 같이 싱글톤(Singleton)으로 이해하면 쉽다.

```java
public class Number {
	public static final Number ONE = new Number(1);
  public static final Number TWO = new Number(2);
  public static final Number THREE = new Number(3);
  public static final Number FOUR = new Number(4);

  private final int value;

  private Number(int value) {
		this.value = value;
	} 

  public int getValue() {
	  return value;
  }
}
```

# java.lang.Enum

- 모든 열거형(enums)의 조상
- 모든 열거형은 Enum 클래스를 상속 받기 때문에 추가적인 상속을 받을 수 없음

| 메서드                                    | 설명                                                      |
| ----------------------------------------- | --------------------------------------------------------- |
| T[] values()                              | 해당 enum 타입에 정의된 상수 배열을 반환한다.             |
| Class<E> getDeclaringClass()              | 열거형의 Class 객체를 반환한다.                           |
| String name()                             | 열거형 상수의 이름을 문자열로 반환한다.                   |
| int ordinal()                             | 열거형 상수가 정의된 순서를 반환한다.(0부터 시작)         |
| T valueOf(Class<T> enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다. |



## values()

```java
...
Number[] numbers = Number.values();
```



## valuesOf()

```java

if(Number.ONE == Number.valueOf("ONE")){
	System.out.print("true");
}

---------

>> true
```



# Enum을 쓰는 이유

- 코드 가독성 및 리팩토링 용이
- 데이터들 간의 연관 관계 표현
- 상태와 행위를 한 곳에서 관리
- 데이터 그룹 관리



# Enum 활용 예

- 상태와 행위를 한 곳에서 관리

```java
enum CalculatorType{
        CALC_A(value -> value),
        CALC_B(value -> value*7),
        CALC_C(value -> value*2),
        CALC_ETC(value -> 0L);

        private Function<Long, Long> expression;

        CalculatorType(Function<Long, Long> expression) {
            this.expression = expression;
        }
        public long calculate(long value){
            return expression.apply(value);
        }
    }

    public static void main(String[] args) {
        CalculatorType type = CalculatorType.CALC_B;
        long originValue = 10000L;
        long result = type.calculate(originValue);
        System.out.println(result);
    }

-----

> 70000
```

- 람다식을 지원하지 않는 Java 7 이하인 경우, enum의 추상 메서드를 활용

```java
enum CalculatorType{
        CALC_A{
            long calculate(long value){return value;}
        },
        CALC_B{
            long calculate(long value){return value*7;}
        },
        CALC_C{
            long calculate(long value){return value*2;}
        },
        CALC_ETC{
            long calculate(long value){return 0L;}
        };
        abstract long calculate(long value);
    }
```



# EnumSet

- enum을 위해 고안된 특별한 `Set` 인터페이스 구현체
- `EnumSet`은 `Set` 인터페이스를 구현하면서 `AbstractSet`를 상속하지만, 대부분의 메서드를 재정의해서 사용
- [⚠ 참고] [EnumSet을 왜 쓸까](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-11%EC%A3%BC%EC%B0%A8-Enum#enumset%EC%9D%84-%EC%99%9C-%EC%93%B8%EA%B9%8C)



## 유의사항

- 동일한 enum 집합의 원소들만 저장할 수 있음
- `thread-safe`하지 않음
- `null` 을 추가 할 수 없음
- enum에 정의된 순서에 따라 저장
- EnumSet은 복사시에 `Fail-Safe Iterator`를 사용하므로 컬렉션이 반복되는 도중에 변경되어도 `ConcurrentModificationException`이 발생하지 않음

> Fail-Safe Iterator는 실제 Collection의 복제본을 작성하고 반복합니다. 
반복자가 작성된 후 수정이 발생해도 사본은 그대로 유지됩니다. 
따라서 이 반복자는 기존 Collection을 수정하더라도 계속 반복됩니다.

- enumset의 메서드 설명은 생략하고, 아래 예제로 주요 메서드 표현

```java
...

System.out.println("------------------------- allOf -------------------------");
EnumSet<Number> enumSetAll =  EnumSet.allOf(Number.class);
enumSetAll.forEach(System.out::println);

System.out.println("------------------------- range (3~4) -------------------------");
EnumSet<Number> enumSetRange =  EnumSet.range(Number.THREE, Number.FOUR);
enumSetRange.forEach(System.out::println);

System.out.println("------------------------- complementOf (3~4) -------------------------");
EnumSet<Number> enumSetComplement=  EnumSet.complementOf(EnumSet.of(Number.THREE, Number.FOUR));
enumSetComplement.forEach(System.out::println);

System.out.println("------------------------- copyOf -------------------------");
EnumSet<Number> enumSetCopy = EnumSet.copyOf(enumSetAll);
enumSetCopy.forEach(System.out::println);

System.out.println("------------------------- noneOf -------------------------");
EnumSet<Number> enumSet = EnumSet.noneOf(Number.class);
enumSet.add(Number.ONE);
enumSet.add(Number.FOUR);
enumSet.remove(Number.FOUR);

enumSet.forEach(System.out::println);
```

```java
------------------------- allOf -------------------------
ONE
TWO
THREE
FOUR
------------------------- range (3~4) -------------------------
THREE
FOUR
------------------------- complementOf (3~4) -------------------------
ONE
TWO
------------------------- copyOf -------------------------
ONE
TWO
THREE
FOUR
------------------------- noneOf -------------------------
ONE
```

---



# 참고

- [JAVA_ Fail-Safe Iterator vs Fail-Fast Iterator](https://simuing.tistory.com/entry/JAVA-Fail-Safe-Iterator-vs-Fail-Fast-Iterator)
- [https://velog.io/@dion/백기선님-온라인-스터디-11주차-Enum#javalangenum](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-11%EC%A3%BC%EC%B0%A8-Enum#javalangenum)
- [https://www.baeldung.com/java-enumset](https://www.baeldung.com/java-enumset)
- [https://techblog.woowahan.com/2527/](https://techblog.woowahan.com/2527/)
- 자바의 정석