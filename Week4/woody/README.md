



# [Week4] 제어문



## 학습할 것

- 선택문
- 반복문



### 1. 조건문(선택문) - conditional statement

- 특정 조건에 따라 다른 명령이 실행되도록 하는 statement 
- (+) statement VS expression 차이 - [참고](https://shoark7.github.io/programming/knowledge/expression-vs-statement)
  - statement :  실행가능한 최소의 독립적인 코드 단위
  - expression : 평가를 통해 하나의 값으로 표현(reduce)될 수 있는 단위
    - 수식 뿐만 아니라 연산자, 함수호출, 식별자, 배열 등까지를 포함한다.
  - **expression은 statement의 부분집합**

#### 1-1. if문

- 조건식과 중괄호{} 구성

- 조건식이 참이면 해당하는 코드블럭이 실행된다.

- ```java
  if (조건식) { 
    // 조건식 == true 일 때 실행되어야 할 코드
  }
  ```

#### 1-2. if-else 문

1. else 문

- if 문 + else 문 추가

- ```java
  if (조건식) { 
    // 조건식 == true 일 때 실행되어야 할 코드
  } else {
    // 조건식 == false 일 때 실행되어야 할 코드
  }
  ```

- 그런데 이 경우에는 else 문이 불필요하다. 가독성 측면에서도 아래 코드가 더 깔끔

- ```java
  if (조건식) { 
    // 조건식 == true 일 때 실행되어야 할 코드
  } 
    // 조건식 == false 일 때 실행되어야 할 코드
  
  ```

2. else if 문

   - 처리해야할 경우의 수가 3개 이상일 경우 else if 문을 추가한다.

   - ```java
     if (조건식1) { 
       // 조건식1 == true 일 때 실행되어야 할 코드
     } else if (조건식2) {
       // 조건식2 == true 일 때 실행되어야 할 코드
     } else {
       // 어느 조건에도 맞지 않을 경우 실행되어야 할 코드
     }
     ```

#### 1-3. switch 문

하나의 조건식에서 많은 경우의 수를 처리할 수 있다는 장점이 있다. 

단, 제약조건이 존재하는데, switch 문의 **조건식 결과**가  **정수** 혹은 **문자열**이어야 한다. 그리고 **case문의 값**은 문자를 포함한 정수 상수와 문자열만 가능하고 중복되면 안된다(변수 혹은 실수는 안됨).

 switch 문의 작동 순서는 아래와 같다.

- (1) 조건식을 계산
- (2) 해당 값과 일치하는 case 문으로 이동하여 명령 실행
- (3) 이후 문장들을 수행
- (4) break 혹은 switch 문의 끝을 만나면 switch문을 빠져나간다.

```java
switch (result) {
        case 0: 
            System.out.println("zero");
        case 100:
            System.out.println("thousand");
            break;
        default: // 기본값
            result = "other";
            break;
    }
```



- 클린코드 3장 49p
  - "일반적으로 나는 switch 문을 단 한 번만 참아준다. 다형적 객체를 생성하는 코드 안에서다."
  - switch 문은 작게 만들기도 어렵기 때문에 단일 블록이나 함수를 선호하신다고 함
  - 한 가지 작업만 하는 switch 문을 하기도 힘들기 때문에 되도록이면 사용하지 않으려고 한다.

### 2. 반복문

어떤 작업이 반복적으로 수행되도록 할 때 사용한다. 보통 반복 횟수를 지정할 때는 for문을 그렇지 않다면 while문을 사용한다.

#### 2-1. for 문

```java
for (초기화; 조건식; 증감식) { 
 // 조건식이 true인 동안 수행될 코드 블럭
}
```

작동 순서는 초기화 ➡️ 조건식 ➡️ 코드 블럭 

​                                  ↖️  증감식 ↙️



- ✨ 둘 이상의 변수를 초기화 및 증감하고 싶을 경우 

  - `,`로 구분한다
  - 단, 두 변수 타입이 일치해야 한다.

  ```java
  for (int i=0, j=0; i<=10; i++, j--) { ... }
  ```

- ✨ 무한 반복문 가능

  - 초기화, 조건식, 증감식 모두 생략이 가능하다.

  ```java
  for(;;) { ... }
  ```

  

#### 2-2. for-each 문

Java5 부터 들어온 배열 순회 방식 중 하나. 반복문을 위한 카운터 변수를 선언하는 대신, 배열의 타입과 같은 변수를 조건문에서 선언해주는 형식

- 주로 가독성을 위해서 Collection class 혹은 배열을 순회할 때 많이 사용한다.

```java
for (타입 변수명 : 배열) { ... }
```

#### 2-3. While 문

조건식과 코드 블럭으로 이루어져 있다. 조건식이 true 인 동안 코드 블럭이 반복 실행된다.

```java
while (조건식) { ... }
```



#### 2-4. Do-While 문

기존 while문과 다른 점은 코드 블럭을 먼저 실행한 후 조건식을 수행한다.

```java
do { ... } while (조건식);
```



#### 2-5. break과 continue

1. break
   - 반복이 수행되는 중에 break을 만나게되면 가장 가까운 반복문을 벗어나게 된다.
2. continue
   - 반복이 수행되는 중에 continue를 만나면 반복문의 끝으로 이동해서 다음 반복으로 넘어간다.

### 참조

- statement VS expression
  - https://shoark7.github.io/programming/knowledge/expression-vs-statement

- for each loop
  - https://www.geeksforgeeks.org/for-each-loop-in-java/

- 자바의 정석 기초편