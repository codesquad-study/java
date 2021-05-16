# 4주차 과제: 제어문



## 목표

자바가 제공하는 제어문을 학습하세요.



## 학습할 것

- 선택문
- 반복문



## 선택문

- 특정 데이터의 값에 따라 수행할 구문을 선택하는 문법 (조건문의 일종)

- 기본 형태

```java
switch(데이터){

[case 상수:] [수행 statment;] [break;]

[case 상수:] [수행 statment;] [break;]

[default:] [수행 statment;] [break;]

}
```

- 객체를 switch문의 평가식으로 사용할 때는 null 체크를 해주는 것이 좋다.



### Fall Through

- switch 문에서 case 내에서 break문을 생략하여 다음 case가 실행되는 현상



### Switch Expressions

- switch expressions
- 기본 형태

```java
case label_1, label_2, ..., label_n -> expression;|throw-statement;|block 
```

- break 또는 yield문(switch 블록 내부에서 값을 반환하고 싶을 때 사용)을 빼먹기 쉽기 때문에 colon case label보다는 arrow case label을 권장한다.

```java
public enum Day { SUNDAY, MONDAY, TUESDAY,
    WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; }

// ...

    int numLetters = 0;
    Day day = Day.WEDNESDAY;
    switch (day) {
        case MONDAY:
        case FRIDAY:
        case SUNDAY:
            numLetters = 6;
            break;
        case TUESDAY:
            numLetters = 7;
            break;
        case THURSDAY:
        case SATURDAY:
            numLetters = 8;
            break;
        case WEDNESDAY:
            numLetters = 9;
            break;
        default:
            throw new IllegalStateException("Invalid day: " + day);
    }
    System.out.println(numLetters);
```

위와 같은 코드를 아래와 같이 바꿀 수 있다.

```java
    Day day = Day.WEDNESDAY;    
    System.out.println(
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        }
    );    
```

- yield 사용 예제

```java
    int numLetters = switch (day) {
        case MONDAY, FRIDAY, SUNDAY -> {
            System.out.println(6);
            yield 6;
        }
        case TUESDAY -> {
            System.out.println(7);
            yield 7;
        }
        case THURSDAY, SATURDAY -> {
            System.out.println(8);
            yield 8;
        }
        case WEDNESDAY -> {
            System.out.println(9);
            yield 9;
        }
        default -> {
            throw new IllegalStateException("Invalid day: " + day);
        }
    };  
```



### enum vs String

- 아래 예시코드에서 enum의 경우 default를 선언해주지 않아도 에러가 나지 않지만, String의 경우 default를 선언해주지 않으면 `the switch expression does not cover all possible input values`라는 에러가 발생한다.
- switch case에서는 input 타입에 해당하는 모든 범위에 대하여 일치하는 case 라벨이 존재해야하기 때문이다.
- 당연히 enumSwitch 안에서도 WEDNESDAY case라벨이 없거나 enum에 선언되어 있는 라벨이 빠지면 동일한 에러가 발생한다.

```java
public class Test {

    public static void main(String[] args) {
        System.out.println(enumSwitch(Day.TUESDAY));
        System.out.println(stringSwitch("TUESDAY"));

    }

    enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}

    static int enumSwitch(Day day) {
        return switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
    }

    static int stringSwitch(String s) {
        return switch (s) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
            case "TUESDAY" -> 7;
            case "THURSDAY, SATURDAY" -> 8;
            case "WEDNESDAY" -> 9;
            default -> -1;
        };
    }
}
```



### if ~else문 리팩토링

```java
public class Number {
    private static final Map<String, BiFunction<Number, Number, Number>> operators = new HashMap<>();
    
    static {
        operators.put("+", (a, b) -> new Number(a.no + b.no));
        operators.put("-", (a, b) -> new Number(a.no - b.no));
        operators.put("*", (a, b) -> new Number(a.no * b.no));
        operators.put("/", (a, b) -> new Number(a.no / b.no));
    }

    private int no;

    public Number(int no) {
        this.no = no;
    }

    public Number calculate(String expression, Number number) {
        BiFunction<Number, Number, Number> operator = operators.get(expression);
        if (operator == null) {
          throw new IllegalArgumentException();
        }
        
        return operator.apply(this, number);
    }
}
```



## 반복문

### while문

- loop-condition이 false가 될 때까지 while {} 블록 안의 코드를 실행

![Executing a while loop](https://devcomprd.wpengine.com/wp-content/uploads/2021/03/Loop1.jpg)

### do / while문

- loop를 한 번 실행 후 조건식을 검사
- loop-condition의 true/false와 상관없이 while{} 블록 안의 코드가 한 번은 실행된다.

![Executing a do...while loop](https://devcomprd.wpengine.com/wp-content/uploads/2021/03/Loop3.jpg)

```java
int counter = 1;   // Control variable initialized

do{
   System.out.println(counter);
   counter--;   // Decrements the control variable
}while(counter <= 10);   // Condition statement
```



### for문

- 초기식, 조건식, 증감식으로 구성

![Executing a for loop](https://devcomprd.wpengine.com/wp-content/uploads/2021/03/Loop2.jpg)

### Enhanced for문

- iter : enhanced for



---

***Source***

- https://ehpub.co.kr/java-3-2-%EC%84%A0%ED%83%9D%EB%AC%B8/

- https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html

- https://www.slipp.net/questions/566
- http://www.tcpschool.com/java/java_control_loop

- https://www.developer.com/design/using-different-types-of-java-loops-looping-in-java/