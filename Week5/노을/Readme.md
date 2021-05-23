# 5주차 과제: 클래스

URL: https://github.com/whiteship/live-study/issues/5
열: 2021년 5월 22일 오후 5:22
태그: 백기선스터디



### 클래스 정의하는 방법

- 분류. 집합. 같은 속성과 기능을 가진 객체를 총칭하는 개념
- 관습적으로 대문자로 시작한다.
- 클래스는 객체의 속성을 표현하는 필드와 행위를 표현하는 메소드로 구성됨
- 자바는 한 파일에 하나의 public 클래스만 허용한다.
- 클래스는 객체를 생성해 주는 도구, 레시피, 혹은 청사진(blueprint)이라고 할 수 있다.
- 모든 클래스에는 반드시 하나 이상의 생성자가 정의 돼있어야함.

### 일반 클래스

```java
public class Human{
	// 필드
  // 메소드
}
```

- c언어의 구조체 + 함수같은 느낌

### **클래스 vs. 객체 = 붕어빵틀 vs. 붕어빵**

- 클래스를 쉽게 설명하려다 생긴 오류

```java
붕어빵틀 붕어빵 = new 붕어빵틀(); ⛔

또는

금형기계 붕어빵틀 = new 금형기계(); ⛔ '금형기계를 하나 만들었더니 붕어빵틀이 되었다'
```

**붕어빵틀을 새로운 만든다고 "붕어빵"이 될순 없다**

- 붕어빵틀은 그저 붕어빵을 만드는 팩토리일 뿐이다.

### 클래스와 객체의 구분 방법

주어진 단어에 나이를 물어보자. 만약 대답할 수 있다면 객체이고 대답할 수 없다면 클래스일 것이다.

```java
**사람 -> 클래스
김연아 -> 객체**
```

> 클래스는 분류에 대한 개념! 객체는 실체화한 것!

[](https://yunanp.tistory.com/42)

[클래스 vs 객체 = 붕어빵틀 vs 붕어빵?](https://smujihoon.tistory.com/225)



##### 제어자

|    이름    |                             종류                             |
| :--------: | :----------------------------------------------------------: |
| 접근제어자 |             public, protected, default, private              |
|   그 외    | static, final, abstract, native, transient, synchronized, volatile, stricfp |



##### 접근 제어자

| 이름      | 같은 클래스 | 같은 패키지 | 같은 자손 | 전체 |
| --------- | ----------- | ----------- | --------- | ---- |
| public    | ✅           | ✅           | ✅         | ✅    |
| protected | ✅           | ✅           | ✅         |      |
| default   | ✅           | ✅           |           |      |
| private   | ✅           |             |           |      |



##### 대상에 따라 사용할 수 있는 제어자

|   대상   |             사용가능한 제어자             |
| :------: | :---------------------------------------: |
|  클래스  |    public, (default), final, abstract     |
|  메서드  | 모든 접근 제어자, final, abstract, static |
| 멤버변수 |      모든 접근 제어자, final, static      |
| 지역변수 |                   final                   |



### 객체 만드는 방법 (new 키워드 이해하기)

- 연산자 new에 의해 클래스의 인스턴스가 `힙 메모리`의 빈 공간에 생성됨.
- 대입연산자(=)에 의해 생성된 인스턴스의 주소값이 참조변수에 저장된다.
- 저장된 객체는 참조변수를 통해 접근할 수 있다.
- 참조변수를 통해 접근할 수 없는 객체는 `GC`에 의해 정리된다.

### 메소드 정의하는 방법

- 메소드? 함수?

### 함수

- c언어 함수형? 프로그래밍 예제

```c
void each(int *a, int size, void (*apply)(int){ // void (*apply)(int) :: 매게변수가 int
    for(int i=0; i<size;i++){
        apply(a[i]);
    }
}
void foo(int x){
    printf("x: %d \n", x);
}
int main(){ 
    int a[] = {1,2,3};
    foo(a[0]);
    printf("P: %p\n", foo);  // 코드영역에 존재함.
    each(a, 3, foo); // 
    return 0;
}
```

- 함수는 특정 작업을 수행하는 코드 블럭이자, `독립된 기능`을 수행하는 단위
- 함수의 이름을 `호출` 하여 정해진 작업을 수행

> 일부 저자들의 경우 함수가 '일급 객체'가 되기 위한 조건으로 
런타임에 함수 생성 가능 여부를 드는데, 
이 조건에 의하면 C와 같은 언어에서의 함수는 일급 객체가 아니다. 
C의 함수와 같은 객체들은 경우에 따라서 이급 객체로 불리기도 하는데, 
비록 일급 객체의 속성을 모두 갖추지는 못했다 하더라도 그에 상응하는 방식으로 다뤄질 수 있기 때문이다.

[https://ko.wikipedia.org/wiki/일급_객체](https://ko.wikipedia.org/wiki/%EC%9D%BC%EA%B8%89_%EA%B0%9D%EC%B2%B4)

### 메소드

- 자바 메소드 예제
- 클래스, 구조체, 열거형에 `종속`되어 구현 돼있는 "함수"

```java
public class human{
   ...
  public void eat(){
    ...
  }
}
```

- 자바의 메소드는 [1급 객체](https://ko.wikipedia.org/wiki/일급_객체)가 아니어서, 메소드를 리턴값이나 파라미터로 사용할 수 없다.
    - 단, Java 8부터 추상 메서드가 하나인 `함수형 인터페이스`를 통해 흉내를 낼 수 있다.
    
- `System.out.println`

    ![Untitled](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled.png)

    ![Untitled 1](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%201.png)

    ![Untitled 2](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%202.png)
    

### 가변인자와 오버로딩

- JDK1.5부터 메서드의 매개변수를 동적으로 지정할 수 있음.
- `타입... 변수명` 과 같은 형식으로 가변인자를 선언하면 된다.
- 가변인자는 내부적으로 `배열` 을 이용하여, 선언된 메서드를 호출 할 때마다 배열이 새로 생성된다. (상황에 따라 비효율적일 수 있음)
- 대표적인 예) PrintStream 클래스의 printf()

![Untitled 3](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%203.png)

```java
public class VarArgsEx {
    public static void main(String[] args) {
        String[] cocoa = new String[]{"노을","쿠퍼"};
        print(cocoa);
        print(); // 인자가 없어도 됨.
        print("우디","제인");
        print("우디","쿠퍼","제인","노을");
    }
    public static void print(String ... strings){
        for(String str : strings){
            System.out.print(str+" ");
        }
        System.out.println();
    }
}

---
output >

노을 쿠퍼 

우디 제인 
우디 쿠퍼 제인 노을
```

### JAVA는 pass by value

![Untitled 4](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%204.png)

![Untitled 5](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%205.png)

![Untitled 6](https://github.com/codesquad-study/java/blob/main/Week5/%EB%85%B8%EC%9D%84/img/Untitled%206.png)

- 이미지  출처 : [https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value)

```java
class Company{
    private String name;
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

public class copyEx2{
    public static void main(String[] args){
	Company com1 = new Company();
        com1.setName("Google");
        show(com1);
        System.out.println("main(): "+com1.getName());
    }
    public static void show(Company com){
        com = new Comapny(); // 🐔 주목!
        com.setName("Yahoo");
        System.out.println("show(): "+com.getName());
    }
}

------
output >

> show():yahoo
> main():Google

> 💥 자바가 정말로 call by reference 였다면 main():yahoo 이어야 함.
```

### 생성자 정의하는 방법

- 인스턴스가 생성될 때 호출되는 '인스턴스 초기화 메서드'
- 리턴타입이 없음
- 생성자가 없을 경우 매개변수 없는 public 생성자가 하나 자동으로 생긴다.
- 생성자의 접근제어자를 private으로 설정하면, 생성자 호출로 객체를 생성할 수 없다.
    - `싱글톤 패턴`
- 메소드처럼 이름으로 직접 호출이 불가능하다. ( `this()` 로 호출 가능 )
- 생성자를 정의하지 않으면, 컴파일러가 클래스의 접근제어자와 동일한 형식의 기본 생성자를 추가해준다.
    - 단, 정의된 생성자가 하나라도 있다면 컴파일러는 기본 생성자를 추가해주지 않는다.

- 기본생성자 X

```java
class Animal{
  public static void mian(String[] args){
     Animal dog = new Animal(); 
  }
}

```

- 컴파일러가 기본생성자가 자동으로 추가함.

```java
Compiled from "new.java"
class Animal {
  public Animal();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void mian(java.lang.String[]);
    Code:
       0: new           #2        // 인스턴스 heap에 생성, 참조는 오퍼랜드 스택에 push
       3: dup             // 생성자를 호출하면 해당 객체의 참조자가 스택에서 제거된다. 
													// 따라서 앞에서 생성한 인스턴스 참조자 복사
       4: invokespecial #3                  // 생성자 호출
       7: astore_1        // 참조변수 dog에 오퍼랜드 스택에 있는 참조값 삽입
       8: return
}
```

### this 키워드 이해하기

### this()

- 생성자, 같은 클래스의 다른 생성자를 호출할 때 사용
- 한 생성자에서 다른 생성자를 호출할 때는 반드시 `첫 줄` 에서만 호출이 가능하다.
    - 생성자 내에서 초기화 작업 도중에 다른 생성자를 호출하게 되면 호출하기 이전의 초기화 작업이 무의미해질 수 있기 때문에 이런 제약이 있음.

### this

- 인스턴스 자기자신을 가리키는 참조변수
- this를 사용할 수 있는 것은 인스턴스 멤버뿐이다.
    - static 메서드가 호출된 시점에 인스턴스가 존재하지 않을 수 있기 때문임.
- 생성자나 메소드안에서 현재 객체의 참조로 사용할 수 있음.

---

## 값과 객체의 차이

- 식별자 유무

- 값이 같은지 여부는 `상태` 를 비교해 `동등성`을 판단한다.
    - 가능한 이유는 `값`은 상태가 변하지 않는 "불변상태"이기 때문임.
- 객체는 `식별자`를 기반으로 `동일성` 을 판단함.
    - 객체는 시간에 따라 변경되는 상태를 포함하며, 행동을 통해 상태를 변경한다. 
      따라서 객체는 "가변상태"를 가진다고 말한다.
        - ex) 6살의 나 == 현재의 나
        - 시간에 따라 상태가 변했지만, 여전히 유일하게 `나`로 식별이 가능함.
- 객체지향의 사실과 오해 - 조영호

---

# **과제 (Optional)**

- int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
- int value, Node left, right를 가지고 있어야 합니다.
- BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
- DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.https://velog.io/@honux/%EB%B0%B1%EA%B8%B0%EC%84%A0-%EC%9E%90%EB%B0%94-%EB%9D%BC%EC%9D%B4%ED%8A%B8-%EC%8A%A4%ED%84%B0%EB%94%94-5)

---



[https://www.youtube.com/watch?v=SZEHjcDSEdE](https://www.youtube.com/watch?v=SZEHjcDSEdE)

1. 커뮤니케이션의 문제
    1. 문제를 푸는데 지나치게 집중하면 저지르게 되는 실수
    2. 같이 일하게 될 팀원으로서 가장 중요하게 요구되는 덕목
    3. 안좋은 점수 받을 태도
        1. 면접관의 피드백을 경청하지 않는 자세
        2. [Disagree and commit](https://en.wikipedia.org/wiki/Disagree_and_commit) 를 지키지 못하는 것
            - 내가 동의하지 않는 내용이라도, 팀원의 한사람으로서 동의했다라면, 반드시 따라야한다는 덕목
            - 면접관과 어떠한 솔루션을 구현하겠다고 합의한 이후에는 구현도중에 면접관과 상의없이 나만의 방향을 가면 안됨.
            - 면접관이 내준 문제를 풀 때, 자신이 무슨생각을 하고 있는지 계속 알려야함.
2. 면접관에게 주는 인상
    - 내가 지금 이 포지션을 얼마나 중요하게 생각 하는지?
        - 복장, 말투, 시선, 커뮤니케이션의 태도
            - 얼마나 진지하게 생각하고 있는지를 표출해야함.
        - 세션 마지막에 주는 질문기회를 어떻게 활용하는지에 따라 좌우될 수 있음.
            - 지원한 포지션에 대해 아무것도 알아보지 않는 것 x
            - 기대되는 직무역량이나 지식의 수준 팀 분위기에 대해서 적극적인 호기심을 보이는 것이 도움이됨.

- 면접장에서 당부드릴 것
    - 절대로 준비되지 않았다는 인상을 주지 말 것.
    - 이력서를 보면 어떤 상태에서 포지션을 찾고있는지 알 수 있음.
    - 화이트보드 코딩 등...
    - 열정만으로는 안되고, 그에 맞는 준비된 자세(퍼포먼스 탑재하고)로 면접장에 올 것



---

## Reference

[함수(Function) VS 메소드(Method)](https://zeddios.tistory.com/233)

[백기선 자바 라이브 스터디 5: 클래스](
