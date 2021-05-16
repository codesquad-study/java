# 3주차 과제: 연산자 



## 목표

자바가 제공하는 다양한 연산자를 학습하세요.



## 학습할 것

- 산술 연산자
- 비트 연산자
- 관계 연산자
- 논리 연산자
- instanceof
- assignment(=) operator
- 화살표(->) 연산자
- 3항 연산자
- 연산자 우선 순위
- (optional) Java 13. switch 연산자



## 산술 연산자

수학적인 계산에 사용되는 연산자

| 연산자 |  설명  |
| :----: | :----: |
|   +    | 더하기 |
|   -    |  빼기  |
|   *    | 곱하기 |
|   /    | 나누기 |
|   %    | 나머지 |
|   ++   | 1 증가 |
|   --   | 1 감소 |

### 특징

- 산술 연산에서는 타입 캐스팅과 타입 프로모션이 발생할 수 있기 때문에 자료형에 따른 값의 변화에 주의해야 한다.
- 복합 할당(compound assignment)이 가능하다.
  - `x+=1`
- `+` 연산자는 문자열을 합치는 데도 사용된다.
  - java는 기본적으로 연산자 오버로딩을 지원하지 않지만 String 클래스의 `+` 연산자만 오버로딩이 되어있다.

- 정수형에 대하여 `%` 또는 `/` 연산을 수행하면 `java.lang.ArithmeticException`이 발생한다.
- 실수형의 경우 0에 대하여 `%` 연산을 수행할경우 NaN(Not a Number)이 나온다. 

```java
public class Test {
    public static void main(String[] args) {
        double number1 = 316.0 / 0;
        System.out.println("number1: " + number1);
        double number2 = 316.0 % 0;
        System.out.println("number2: " + number2);
        double number3 = -316.0 / 0;
        System.out.println("number3: " + number3);
    }
}
```

```java
number1: Infinity
number2: NaN
number3: -Infinity
```



## 비트 연산자

| 연산자 |                             설명                             |
| :----: | :----------------------------------------------------------: |
|   &    |     대응되는 비트가 모두 1이면 1을 반환 (비트 AND 연산)      |
|   \|   | 대응되는 비트 중에서 하나라도 1이면 1을 반환 (비트 OR 연산)  |
|   ^    |     대응되는 비트가 서로 다르면 1을 반환 (비트 XOR 연산)     |
|   ~    |      비트를 1이면 0으로, 0이면 1로 반전 (비트 NOT 연산)      |
|   <<   | 지정한 수만큼 비트들을 전부 왼쪽으로 이동 (left shift 연산)  |
|   >>   | 부호를 유지하면서 지정한 수만큼 비트를 전부 오른쪽으로 이동 (right shift 연산) |
|  >>>   | 지정한 수만큼 비트를 전부 오른쪽으로 이동시키며, 새로운 비트는 전부 0이 됨. |

### AND

| 비트 1 | 비트 2 | 결과 |
| :----: | :----: | :--: |
|   0    |   0    |  0   |
|   0    |   1    |  0   |
|   0    |   0    |  0   |
|   1    |   1    |  1   |

### OR

| 비트 1 | 비트 2 | 결과 |
| :----: | :----: | :--: |
|   0    |   0    |  0   |
|   0    |   1    |  1   |
|   1    |   0    |  1   |
|   1    |   1    |  1   |

### XOR

| 비트 1 | 비트 2 | 결과 |
| :----: | :----: | :--: |
|   0    |   0    |  0   |
|   0    |   1    |  1   |
|   1    |   0    |  1   |
|   1    |   1    |  0   |

### 시프트 연산자

- `>>`를 사용하여 비트를 이동할 경우, 비트의 이동으로 새로 생기는 왼쪽 비트는 양수일 경우에는 0으로, 음수일 경우에는 1로 채워짐
- `>>>`를 사용하여 비트를 이용할 경우 왼쪽 비트가 항상 0으로 채워짐

```java
public class Test {
    public static void main(String args[]) {
        int a = 10;
        int b = -10;
        System.out.println(a<<2);
        System.out.println(a>>3);
        System.out.println("a: " + Integer.toBinaryString(a));
        System.out.println("1의 개수: " + Integer.bitCount(a));
        System.out.println("a>>2: " + Integer.toBinaryString(a >> 2));
        System.out.println("a<<2: " + Integer.toBinaryString(a << 2));
        System.out.println("a>>>5: " + Integer.toBinaryString(a >>> 5));
        System.out.println("b: " + Integer.toBinaryString(b));
        System.out.println("b>>5: " + Integer.toBinaryString(b >> 5));
        System.out.println("b>>>5: " + Integer.toBinaryString(b >>> 5));
    }
}
```

```java
40
1
a: 1010
1의 개수: 2
a>>2: 10
a<<2: 101000
a>>>5: 0
b: 11111111111111111111111111110110
b>>5: 11111111111111111111111111111111
b>>>5: 111111111111111111111111111

Process finished with exit code 0

```



## 관계 연산자

### 대소비교 연산자 

- 두 피연산자의 크기를 비교하는 연산자
- 기본형 중에서는 boolean형을 제외한 나머지 자료형에 다 사용할 수 있고, 참조형에는 사용할 수 없다.

### 등가비교 연산자

- 두 피연산자에 저장되어 있는 값이 같은지, 또는 다른지를 비교하는 연산자
- 모든 자료형에 사용할 수 있지만, 참조형의 경우 값이 아닌 객체의 주소값을 비고한다.

```java
public class Test {
    public static void main(String[] args) {
        float a = 0.1f;
        double b = 0.1;
        double c = (double) a;

        System.out.println("10.0==10.0f ? " + (10.0 == 10.0f));
        System.out.println("0.1==0.1f ? " + (0.1 == 0.1f));
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println("c=" + c);
        System.out.println("a==b ? " + (a == b));
        System.out.println("b==c ? " + (b == c));
        System.out.println("c==a ? " + (c == a));
    }
}
```

```java
10.0==10.0f ? true
0.1==0.1f ? false
a=0.1
b=0.1
c=0.10000000149011612
a==b ? false
b==c ? false
c==a ? true
```

- String 

```java
public class Test {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = "Hello";

        System.out.printf("str1==\"Hello\" \t결과:%b%n",str1=="Hello");
        System.out.printf("str2==\"Hello\" \t결과:%b%n",str2=="Hello");
        System.out.printf("str1==str3 \t결과:%b%n",str1==str3);

        System.out.printf("str2.equals(\"Hello\") \t결과:%b%n",str2.equals("Hello"));
    }
}
```

```java
str1=="Hello" 	결과:true
str2=="Hello" 	결과:false
str1==str3 	결과:true
str2.equals("Hello") 	결과:true
```



## 논리 연산자

| 연산자 |                             설명                             |
| :----: | :----------------------------------------------------------: |
|   &&   |      논리식이 모두 참이면 참을 반환함. (논리 AND 연산)       |
|  \|\|  |  논리식 중에서 하나라도 참이면 참을 반환함. (논리 OR 연산)   |
|   !    | 논리식의 결과가 참이면 거짓을, 거짓이면 참을 반환함. (논리 NOT 연산) |

|   A   |   B   | A && B | A \|\| B |  !A   |
| :---: | :---: | :----: | :------: | :---: |
| true  | true  |  true  |   true   | false |
| true  | false | false  |   true   | false |
| false | true  | false  |   true   | true  |
| false | false | false  |  false   | true  |

```java
public class Test {
    public static void main(String[] args) {
        char ch1 = 'b', ch2 = 'B';

        boolean result1, result2, result3;

        result1 = (ch1 > 'a') && (ch1 < 'z');
        result2 = (ch2 < 'A') || (ch2 < 'Z');
        result2 = (ch2 < 'A') || (ch2 < 'Z');
        result3 = (ch2 < 'A') && (ch2 < 'Z');

        System.out.println("&& 연산자에 의한 결과 : " + result1);
        System.out.println("|| 연산자에 의한 결과 : " + result2);
        System.out.println("! 연산자에 의한 결과 : " + !result2);
        System.out.println("&& 연산자에 의한 결과 : " + result3);
    }
}
```

```java
&& 연산자에 의한 결과 : true
|| 연산자에 의한 결과 : true
! 연산자에 의한 결과 : false
&& 연산자에 의한 결과 : false
```



## instanceof

- instanceof 연산자를 사용하면 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아볼 수 있다.
- instanceof의 왼쪽 참조변수의 값이 null이 아니고 오른쪽에 위치한 타입으로 형변환이 가능하면 true, 아니면 false를 반환한다.

```java
void doWork(Car c) {
    if (c instanceof FireEngine) {
        FireEngine fe = (FireEngine) c;
        fe.water();
    }
}
```



## assignment(=) operator

- 오른쪽 피연산자를 왼쪽 피연산자 값으로 할당한다.

  

## 화살표(->) 연산자

- 기본 형태

```java
parameter -> expression
(parameter1, parameter2) -> expression
(parameter1, parameter2) -> { code block }
```

- 예시

```java
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        Consumer<Integer> method = (n) -> { System.out.println(n); };
        numbers.forEach( method );
    }
}
```

```java
long count = customers
  .stream()
  .filter(c -> c.getPoints() > 10 && c.getName().startsWith("Charles"))
  .count();
```



## 3항 연산자

- 기본 형태

```
booleanExpression ? expression1 : expression2
```

- 조건식의 연산결과가 true 이면, 결과는 피연산자 1이고, 조건식의 연산결과가 false 이면 결과는 피연산자2이다.
- 삼항 연산자는 중첩해서 사용할 수 있지만 가독성과 유지보수 측면에서 중첩 사용을 권장하지 않는다.

```java
String msg = num > 10 ? "Number is greater than 10" 
  : (num > 5 ? "Number is greater than 5" : "Number is less than equal to 5");
```



## 연산자 우선 순위

| 우선순위 | 연산자                                                      | 피연산자   | 연산 방향 (결합 방향) |
| -------- | ----------------------------------------------------------- | ---------- | --------------------- |
| 0        | ( ) 괄호 속 연산자                                          | 다양       | -                     |
| 1        | 증감 ( ++, -- ), 부호 ( +, - ), 비트 ( ~ ), 논리 ( ! )      | 단항       | **←**                 |
| 2        | 산술 ( *. / % )                                             | 이항       | →                     |
| 3        | 산술 ( +, - )                                               | 이항       | →                     |
| 4        | 쉬프트 ( >>, <<, >>> )                                      | 이항       | →                     |
| 5        | 비교 ( <, >, <=, >=, instanceof)                            | 이항       | →                     |
| 6        | 비교 ( ==, != )                                             | 이항       | →                     |
| 7        | 논리 &                                                      | 이항(단항) | →                     |
| 8        | 논리 ^                                                      | 이항(단항) | →                     |
| 9        | 논리 \|                                                     | 이항(단항) | →                     |
| 10       | 논리 &&                                                     | 이항       | →                     |
| 11       | 논리 \|\|                                                   | 이항       | →                     |
| 12       | 조건 ( ? : )                                                | 삼항       | →                     |
| 13       | 대입 ( =, +=, -=, *=, /=, %=, &=, ^=, \|=, <<=, >>=, >>>= ) | 이항       | **←**                 |



## (optional) Java 13. switch 연산자

- switch expressions
- 기본 형태

```java
case label_1, label_2, ..., label_n -> expression;|throw-statement;|block 
```

- break 또는 yield문(switch 블록 내부에서 값을 반환하고 싶을 때 사용)을 빼먹기 쉽기 때문에 colon case label보다는 arrow case label을 권장

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



---

***Source***

- https://xxxelppa.tistory.com/196?category=858435

- https://www.w3schools.com/java/java_operators.asp

- https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op1.html

- http://www.tcpschool.com/c/c_operator_bitwise

- https://www.geeksforgeeks.org/java-integer-bitcount-method/

- https://myeonguni.tistory.com/41

- http://kang0217.blogspot.com/2014/09/java_75.html

- http://tcpschool.com/java/java_operator_logic

- https://blogs.oracle.com/javamagazine/is-it-time-for-overloading-in-java#anchor_1

- https://www.baeldung.com/java-stream-filter-count

- https://programmers.co.kr/learn/courses/5/lessons/118

- https://www.baeldung.com/java-ternary-operator

- https://wikidocs.net/81922

- https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html