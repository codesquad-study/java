# Week4. 제어문

## 제어문

> 자바 코드는 기본적으로 위에서 아래로 코드가 실행횐다. 하지만 모든 코드가 순차적으로 실행될 수 없다. `순서 변경`하거나 `코드 반복`,` 건너 뛰기` 등 순서를 제어 해야하는 경우가 발생한다. 이 경우, 선택문(`if-then` `if-then-else` `switch`)과 반복문(`for` `while` `do-while`), 분기문(`break` `continue` )을 이용해 실행흐름을 제어할 수 있다.



## 1. 선택문

### if - then

- `if-then`은 지정한 조건을 만족할 때, 지정한 내부 블록을 실행한다.

  ```Java
  if(codition) {
    //condition == true 시, 실행할 블록
  }
  ```

### if-then-else

- `if-then-else`는 지정한 조건을 만족할 경우(true), `if블록`을 실행하고,

- 조건은 만족하지 못할 경우(false), `else블록`을 실행시킨다.

  ```java
  if(condition) {
    //condition == true 시, 실행할 블록
  } else {
    //condition == false 시, 실행할 블록
  }
  ```

<br>

- if문을 조건을 만족 시키지 못하지만 **추가 조건(condition)**이 필요한 경우 `else if`문을 사용한다.

  ```java
  if(A_condition) {
    //A_condition == true 시, 실행할 블록
  } else if (B_condition) {
    //A_condition == false && B_condition == true 시, 실행할 블록
  } else {
    //A_condition == false && B_condition == false 시, 실행할 블록
  }
  ```



[if-else if문 줄이기](https://donnaknew.tistory.com/1)

[if-else if문 줄이기2](https://donnaknew.tistory.com/2?category=767933)

<br><br>



## 2. 반복문

- 같은 작업을 반복하는 부분이 필요한 경우에 사용하는 구문.
- 종류 : `for`, `while` `do-while`

### `for`

```java
for(초기화; 조건식; 증감식) {
  //조건식이 참(true)가 되는 경우, 반복 수행할 구문
}
```

<br>

### `for-each`

- Iterable interface를 구현한 class에 대해서 사용가능한 for문

  (ex. Collectiion : 모든 collection은 Iterable을 상속받고 있다. )

- 장점 : 코드가 직관적이다.

```java
for(type var : iterate) {
	//ierate를 순회하면서 수행할 구문  
}

//예시
Elememt[] array = new Element[10];
List<Element> elementList = Arrays.asList(array);

for(Element e : elementList) {
  System.out.println(e); // elementList 내부의 원소들의 정보 출력
}
```

<br>

### `while`

```java
while(condition) {
  //조건식이 참(true)가 되는 경우, 반복 수행할 구문
}
```

<br>

### `do-while`

```java
do {
  	//일반 한번 실행하고 조건(condition)을 확인한다.
    //조건식이 참(true)가 되는 경우, 반복 수행할 구문
} while(condition);
```

<br><br>



## 3. 분기문

### `break`

- 현재 위치의 가장 가까운 반복문을 탈출할 때 사용하는 분기문.

- 주의! 이중 반복문에서 내부 반복문 탈출 시, 전체 탈출X

  ```java
  for(int i = 0; i < 10; i++) {
    if(i == 5) {
      break;
    }
    System.out.print(i);
  }
  
  //결과 : 1234
  ```

  ```java
  loop:
  for(int i = 0; i < 10; i++) {
    for(int j = 0; j < 10; j++) {
      if(j == 5) {
        break loop;
      }
    }
  }
  ```

<br>

### `continue`

- 반복문 진행 시, continue 이후 문장들을 수행하지 않고 넘어감.

- `break` 와 차이점

  - `break` : 반복문을 탈출한다.
  - `contine` : continue 아래 지점만 수행하지 않고 다음 구문은 실행

  ```java
  for(int i = 0; i < 10; i++) {
    if(i == 5) {
      contnue;
    }
    System.out.print(i);
  }
  
  //결과 : 12346789
  ```

<br>



## Queue 구현

[Queue Sample](https://github.com/pbg0205/summary-for-algorithm/tree/main/cooper-summary/src/algorithm_library/queue)

