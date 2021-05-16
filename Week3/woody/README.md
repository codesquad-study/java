# [Week3] 연산자



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



### 들어가기 전

#### 연산자란?

- 연산을 수행하는 기호를 말한다.

- 연산의 대상을 피연산자(operand)라고 한다.

  

### 산술 연산자

1. 사칙 연산자 

   - Addition(`+`)

     - 피연산자 중 문자열이 있다면 다른 피연산자도 문자열로 변환되어 연산

   - Subtraction(`-`)

     - 이항 연산(Binary Operator)에서는 첫 번째 피연산자에서 두 번째 피연산자를 빼는 연산
     - 단항 연산(Unary Negation)에서는 해당 피연산자 값의 부호가 변경

   - Division(`/`)

     - 정수를 0으로 나눌 경우 `ArithmeticException` 발생 
     - 실수를 0으로 나눌 경우 `INF(무한)` 값으로 반환
     - 음수로 나누기 연산 가능

   - Multiplication(`*`)

   - Modulus(`%`)

     - 실수를 0으로 모드(mod) 연산할 경우  `NAN(숫자가 아님, not a number)`
     - 음수로 모드(mod) 연산 가능

     

2. 증감 연산자

   - Increment(`++`) , Decrement(`--`)

     - 단항 연산

   - 연산자 위치에 따라 결과값 변경
     - prefix (피연산자 **앞**에 위치할 경우) : 연산 먼저 진행한 다음 **결과 반환**
       - ex) `++i`, `--i`
     - postfix (피연산자 **뒤**에 위치할 경우) : **값을 먼저 리턴**하고 연산 진행
       - ex) `i++`, `i--`

     

### 비트 연산자

1. 기본 비트 연산자

   - AND(`&`)

     - 하나라도 0이면 0 반환, o/w 1반환

     | AND  |  0   |  1   |
     | :--: | :--: | :--: |
     |  0   |  0   |  0   |
     |  1   |  0   |  1   |

   - OR(`|`)

     - 하나라도 1이면 1 반환, o/w 0 반환

     |  OR  |  0   |  1   |
     | :--: | :--: | :--: |
     |  0   |  0   |  1   |
     |  1   |  1   |  1   |

   - XOR(`^`) 

     - 두 비트가 서로 다르면 1, 같으면 0 반환

     | XOR  | 0    | 1    |
     | ---- | ---- | ---- |
     | 0    | 0    | 1    |
     | 1    | 1    | 0    |

     

   - NOT(`~`) - 단일항

     | NOT  | 0    | 1    |
     | ---- | ---- | ---- |
     |      | 1    | 0    |

     

2. Shift 연산자

   - Left Shift (`<<`) 

     - 지정된 수 만큼 모든 비트를 왼쪽으로 이동 (2배씩 증가)
     - 비트 이동 시, 오른쪽 비트는 0으로 채워짐
     - 8 << 2 == 16

   - Right Shift (`>>`)

     - 지정된 수 만큼 모든 비트를 오른쪽으로 이동(2배씩 감소)

     - 비트 이동 시, 왼쪽 비트는 피연산자가 양수일 경우 0으로 음수일 경우 1로 채워짐

     - ```java
       a = 0000 0000 0000 0000 0000 0000 0011 1100
       b = 1111 1111 1111 1111 1111 1111 1100 0100
       a >> 1 = 0000 0000 0000 0000 0000 0000 0001 1110
       b >> 1 = 1111 1111 1111 1111 1111 1111 1110 0010  
       ```

       

   - Unsigned Right Shift (`>>>`)

     - 부호비트까지 포함해서 무조건 모든 비트를 오른쪽으로 이동

     - 비트 이동 시, 왼쪽 비트는 무조건 0으로 채워짐 (피연산자가 양수일 경우 `>>`와 똑같은 결과 반환)

     - ```java
       a = 0000 0000 0000 0000 0000 0000 0011 1100
       b = 1111 1111 1111 1111 1111 1111 1100 0100
       a >>> 1 = 0000 0000 0000 0000 0000 0000 0001 1110
       b >>>  1 = 0111 1111 1111 1111 1111 1111 1110 0010
       ```

       

### 관계 연산자

- 두 피연산자를 비교할 때 사용하는 연산자 

- 피연산자의 타입이 다를 경우 더 큰 피연산자의 타입으로 변환되어 비교

- 연산 결과는 boolean(true 혹은 false)로 반환



#### 등가 비교 연산자 (Comparsion Operators)

- Equal to (`==`)
  - Primitive Type 비교 시, 두 피연산자의 값을 비교
  - Reference Type 비교 시, 두 피연산자의 주소값을 비교
- Not equal (`!=`)
  - 값이 다른지를 체크한다는 점을 빼면, Equal to와 동일

#### 관계 연산자 (Relational Operators)

boolean이나 reference type에는 사용할 수 없다

- Greater than (`>`)
- Less than (`<`)
- Greater than or equal to (`>=`)
- Less than or equal to (`<=`)



### 논리 연산자

연산의 결과로 boolean(true 혹은 false)을 반환하며, 주로 조건식에서 사용된다.

- AND(`&&`) - 하나라도 false일 경우 false
- OR(`||`) - 하나라도 true일 경우 true
- NOT(`!`) - boolean 값을 반전

### (+) 우선순위

2와 3의 배수이면서 6의 배수가 아닌 수인지 확인하고 싶을 때

```java
i%2==0 || i%3==0 && i%6!=0 // i%3==0 && i%6!=0 부터 실행
(i%2==0 || i%3==0) && i%6!=0 // 괄호부터 실행
```

둘 사이의 우선순위가 `&&` > `||` 이기때문에 필요에 따라 괄호를 붙여주어야 한다.



#### (+) short circuit evaluation

`<1st op> && <2nd op>` 혹은 `<1st op> || <2nd op>` 일 때

첫 번째 피연산자 `<1st op>`의 결과로 전체 결과값이 이미 정해진 경우 두 번째 연산 `<2nd op>`을 진행하지 않는다.



### assignment(=) operator

오른쪽의 값을 왼쪽에 대입한다고 해서, 대입 연산자라고도 한다. 변수에 값을 할당하기 위해서 사용한다.

```java
int num = 1;
```



### 복합 대입 연산자

아래와 같이 산술연산자와 비트연산과 결합되어 사용되기도 한다.

```java
// 산술 연산자 결합
i += 2; // i = i + 2
i -= 2; // i = i - 2
i *= 2; // i = i * 2
i *= 2 + j // i = i * (2 + j)
i /= 2; // i = i / 2
i %= 2; // i = i % 2

// 비트 연산자 결합
i &= 2; // i = i & 2
i |= 2; // i = i | 2
i ^= 2; // i = i ^ 2

// 시프트 연산자 결합
i <<= 2; // i = i << 2
i >>= 2; // i = i >> 2
i >>>= 2; // i = i >>> 2
```



### 화살표(->) 연산자

Java 8에 도입된 람다식을 위한 연산자.

코드가 한 줄이면 중괄호( `{ //코드 }`) 생략 가능

```java
(arguement, ..) -> {expression1, ...}
(argument, .. ) -> single expression
```



### 3항 연산자

연산자에서 피연산자의 개수가 한 개라면 단항 연산자, 두 개면 이항 연산자, 세 개면 삼항 연산자라고 한다. 

- else if 문을 삼항 연산자 한 줄로 아래와 같이 표현할 수 있다.

```java
int num = (10 < 5) ? 30 : 50;
(조건문) ? <true일 때 리턴되는 결과> : <false일 때 리턴되는 결과> 
```



### 연산자 우선 순위

- 괄호의 우선순위가 가장 높다. 그 뒤로 산술, 비교, 논리, 대입 순서로 우선순위가 있다.

- 단항 > 이상 > 삼항 순서로 우선순위가 높다.
- 연산 진행은 왼쪽 ➡️ 오른쪽, 단항과 대입 연산자의 경우에는 반대

- 연산자 우선순위 테이블 - [참고](http://www.cs.bilkent.edu.tr/~guvenir/courses/CS101/op_precedence.html)

  

### instanceof 연산

해당 객체 혹은 배열이 특정 타입의 인스턴스인지를 테스트하는 데 사용되는 연산이다. (한 마디로, 타입 비교 연산자)

- 보통 다운 캐스팅을 하기 전 instanceof를 통해 `ClassCastException`을 미리 방지하는데 쓰인다.

```java
class Animal{}  
class Dog extends Animal{//Dog inherits Animal 
   public static void main(String args[]){  
     Dog d=new Dog();  
     System.out.println(d instanceof Animal);//true  
    
 }
  static void downcasting(Animal a){
    if(a instanceof Dog){  
       Dog d=(Dog)a;//downcasting  
       System.out.println("downcasting");  
    }  
  }
}  
```

- (+) [“instanceof”, Why And How To Avoid It In Code](https://armedia.com/blog/instanceof-avoid-in-code/#:~:text=The%20java%20%E2%80%9Cinstanceof%E2%80%9D%20operator%20is,returns%20either%20true%20or%20false.)

### (optional) Java 13. switch 연산자



## 참조

- 연산자
  - https://www.w3schools.com/java/java_operators.asp

- shift 연산
  - 개념 참고 : http://tcpschool.com/java/java_operator_bitwise
  - 예시 참고 : https://www.tutorialspoint.com/Bitwise-right-shift-operator-in-Java#:~:text=The%3E%3E%20operator%20is%20a%20signed,specified%20by%20the%20right%20operand.

- instanceof
  - https://www.javatpoint.com/downcasting-with-instanceof-operator

- 대입연산자
  - https://blog.baesangwoo.dev/posts/java-livestudy-3week/

- 삼항연산자
  - https://coding-factory.tistory.com/266
- 연산자 우선순위
  - https://toma0912.tistory.com/66

