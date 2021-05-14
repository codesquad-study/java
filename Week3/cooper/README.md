# Week3. 연산자

## 1. 산술 연산자

- 산술연산자는 수학적인 계산에 사용되는 연산자이다.

- 산술연산자는 사칙연산자( `+, -, *, /`) 와 나머지 연산자(`%`)로 구성되어있다.

- 산술 연산자 사용 시, 많이 하는 실수

  -  **나누기 연산자를 사용하는 경우**

    - 나누기 연산자 사용 시, `int / int = int` 형태이다. 그런데 만약 **몫이 0보다 작은 경우(0 < 몫 < 1)** 일 경우, 소수점이 아닌 0이 출력되기 때문에 type에 대해 조심하도록 하자.

      ```java
      public DivideOperationTest {
        public static void main (String[] args) {
          int aAsInt = 3;
          int bAsInt = 5;
          
          int resultAsInt = a / b; //result == 0;
          
          double aAsDouble = 3;
          double bAsDouble = 5;
          
          double resultAsDouble = a / b; //result = 0.6
        }
      }
      ```

      <br>

  - **StackOverFlow**

    - 더하기 혹은 곱하기 연산자를 사용하는 경우, 해당 데이터 타입의 값의 범위를 벗어나면 다른 값이 출력되므로 `데이터타입`을 주의하도록 하자!

      ```java
      public class StackOverFlowTest {
          public static void main(String[] args) {
              int a = 200000;
              int b = 200000;
      
              System.out.println(a * b);
            //예상 값 : 40000000000
            //실제 값 : 1345294336
          }
      }
      ```

<br><br>

## 2. 비트 연산자

### `& | ^ ~`

- `&`(AND) : 두 피연산자 모두 1이면 1을 반환(그 외 0 반환)

  | A    | B    | A & B |
  | ---- | ---- | ----- |
  | 1    | 1    | 1     |
  | 1    | 0    | 0     |
  | 0    | 1    | 0     |
  | 1    | 1    | 1     |

  <br>

  

- `|`(OR) : 두 피연산자 중 하나만 1이면, 1을 반환(그 외 0 반환)

  | A    | B    | A \| B |
  | ---- | ---- | ------ |
  | 1    | 1    | 1      |
  | 1    | 0    | 1      |
  | 0    | 1    | 1      |
  | 0    | 0    | 0      |

  <br>

  

- `^`(XOR) : 두 피연산자 값이 서로 다르면 1을 반환(그 외 0 반환)

  | A    | B    | A ^ B |
  | ---- | ---- | ----- |
  | 1    | 1    | 0     |
  | 1    | 0    | 1     |
  | 0    | 1    | 1     |
  | 0    | 0    | 0     |

  ```java
  class XOR {
    public static void main(String[] args) {
      int a = 8; //1000(2)
      int b = 2; //0010(2)
      
      int result = a ^ b; //result = 10 = 1010(2)
    }
  }
  ```

<br>

- `~`(NOT) : 피연산자의 2진수의 값을 0은 1로, 1은 0으로 변경

  | A (8)    | ~A(-9)   |
  | -------- | -------- |
  | 01000(2) | 10111(2) |

  

  ```java
  class NotOperation {
    public static void main(String[] args) {
  		int a = 0b1000;
      
      System.out.println(Integer.toBinaryString(a)); //01000(2)
      System.out.println(Integer.toBinaryString(~a)); //10111(2)
    }
  }
  ```

<br>

### `시프트 연산자 <<, >>`

- `A << N` : 비트A를 왼쪽으로 N만큼 이동시킨다. (빈자리는 0으로 채워짐)

- `A >> N` :  비트A를 오른쪽으로 N만큼 이동시킨다.

  - A > 0 (양수), 0으로 채워진다. (최상위 비트가 0이기 때문)
  - A < 0 (음수), 1로 채워진다. (최상위 비트가 1이기 때문)

  ```java
  class ShiftOperationTest {
    public static void main(String[] args) {
  		int a = 0b1000;
      
      System.out.println(Integer.toBinaryString(a << 2)); //100000(2)
      System.out.println(Integer.toBinaryString(~a)); //10(2)    
    }
  }
  ```

  <br>

- `A >>> N` : 비트A를 왼쪽으로 N만큼 이동시킨다. (빈자리는 0으로 채워진다.)

<br><br>

## 3. 관계(비교) 연산자

### 대소비교 연산자 `> >= < <=`

- 두 피연산자의 값의 크기를 비교한다.
- primitive type만 사용 가능하다.(refrence type 사용불가.)

<br>

### 등가비교 연산자 `== !=`

- 두 피연산자의 값이 일치 여부를 확인하는 연산자이다.

- Primitive type : 두 값의 일치 여부를 확인한다.

- Reference type : 두 객체의 주소값을 비교한다.

  - 주소값으로 확인하기 때문에 같은 값을 가진 객체라도 같지 않다고 반환한다.
  - 값을 통한 비교를 원한다면 Reference type의 논리적 동치성(논리적으로 둘이 같은지) `equals 재정의` 와 'hash 재정의'을 해서 확인이 가능하다.

  ```java
  class ReferenceComparasion {
    public void static main(String[] args) {
      Box myBox = new Box();
      Box yourBox = new Box();
      
      System.out.println(myBox == yourBox); //false
      System.out.println(myBox == yourBox); 
    }
  }
  
  class Box {
    private int count = 4;
    
    //equals 재정의
    @Override
    public boolean equals (Object o) {
      if(this == o) {
        return true;
      }
      
     if(!(o instanceof Box)) {
       return false;
     }
      
      Box other = (Box) o;
      
      return this.count == other.count;
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(count);
    }
  }
  ```

  

  <br>

## 4. 논리 연산자

### `&& || !`

- `&&` (OR 결합) : 피연산자 중, 한 쪽만 true경우, true

  | A     | B     | A && B |
  | ----- | ----- | ------ |
  | true  | true  | true   |
  | true  | false | false  |
  | false | true  | false  |
  | false | false | false  |

  <br>

- `||` (AND 결합) : 피연산자 중, 모두 true일 경우, true

  | A     | B     | A \|\| B |
  | ----- | ----- | -------- |
  | true  | true  | true     |
  | true  | false | true     |
  | false | true  | true     |
  | false | false | false    |

  <br>

- `!` (논리 부정) : 피연산자 결과의 반대로 출력

  | A     | !A    |
  | ----- | ----- |
  | true  | false |
  | false | True  |

  <br><br>

## 5. instanceof

- 타입 비교 연산자이며, 해당 객체가 `같은 클래스, 하위 클래스의 인스턴스` 또는 `인터페이스를 구현한 클래스의 인스턴스`인지 확인할 수 있다.

  ```java
  class InstanceOfTest {
    public static void main(String[] args) {
      System.out.println(Bird instanceof Bird); // true
      System.out.println(Bird instanceof Animal);//true
      System.out.println(Bird instanceof Flyable);//true
      
      Syste.out.println(Bird instanceo Movable); //false
    }
  }
  
  class Bird extends Animal implements Flyable {}
  class Animal {}
  interface Flyable {}
  interface Movable {}
  ```

  <br><br>

## 6. assignment(=) operator

- 할당 연산자 : 연산자 오른쪽 값을 왼쪽의 변수 저장 공간에 할당한다.

- Primitive type : 값을 저장한다. `int x = 3;`

- Reference type : 객체의 주소를 저장한다. `Animal animal = new Bird();`

  - `new Bird()`의 주소가 `Bird@139a55`라면 animal에 `Bird@139a55`를 저장한다.

    (객체 주소 방식 : `객체타입@해시코드`)

  <br><br>

## 7. 화살표(->) 연산자

- 자바 8의 람다식의 도입하며 도입된 연산자이다.
- `->`를 기준으로 왼쪽은 `파라미터`, 오른쪽은 `구현부`형태로 분리한다. (`{parameter} -> {body}`)

```java
List<InningDto> list = inningList.stream()
  	.filter(inning -> inning.getTeamId == teamId)
  	.collect(Collections.toList());
```

<br><br>

## 8. 3항 연산자

- 삼항 연산자는 조건문의 참/거짓에 따라서 실행할 로직을 구분하는 연산자이다.

```
(조건문) ? (true 로직) : (false 로직)
```

만약 아래와 같이 해당 조건문의 경우,

```java
if(condition) {
  actMethodIfTrue();
} else {
  actMethodIfFalse();
}
```

이와 같은 조건문을 삼항 연자라를 통해서 변경이 가능하다.

```java
condition ? actMethodIfTrue() : actMethodIfFalse();
```

<br><br>

## 9. 연산자 우선 순위

- 연산자의 우선 순위가 필요한 이유?!

  - 여러 연산자가 존재할 때, 다양한 연산 결과를 반환할 수 있다.

  - 연산자 우선순위

    | 우선순위 | 연산자                                        | 내용                     |
    | -------- | --------------------------------------------- | ------------------------ |
    | 1        | (), []                                        | 괄호/대괄호              |
    | 2        | !, ~, ++, --                                  | 부정 / 증감 연산자       |
    | 3        | *, /, %                                       | 곱셈 / 나눗셈 연산자     |
    | 4        | +, -                                          | 덧셈 / 뺄셈 연산자       |
    | 5        | <<, >>, >>>                                   | 비트단위의 쉬프트 연산자 |
    | 6        | <, <=, >, >=                                  | 관계 연산자              |
    | 7        | ==, !=                                        |                          |
    | 8        | &                                             | 비트 단위 논리 연산자    |
    | 9        | ^                                             |                          |
    | 10       | \|                                            |                          |
    | 11       | &&                                            | 논리곱 연산자            |
    | 12       | \|\|                                          | 논리합 연산자            |
    | 13       | ? :                                           | 조건 연산자              |
    | 14       | =, +=, -=, /=, %=,<br /> <<=, >>=, &=, ^=, ~= | 대입 / 할당 연산자       |

  

  

  