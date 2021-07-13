# 15 함수형 인터페이스


- 불변성 (Immutable) 값이나 변수를 적극 활용할 수 있다.
- 함수가 참조 투명성을 지키고, 부작용을 줄일 수 있도록 구현할 수 있다.
- 순수함수 (Pure Function) 로 구현할 수 있다.



### 함수형 인터페이스(Funtional Interface)

- 추상 메소드를 딱 하나만 가지고 있는 인터페이스
- SAM(Single Abstract Method) 인터페이스
- @FunctionalInterface 애노테이션을 가지고 있는 인터페이스

 

### 람다 표현식(Lambda Expressions)

- 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
- 코드를 줄일 수 있다.
- 메소드 매개변수, 리턴타입, 변수로 만들어 사용할 수 있다.

 

### 함수형 프로그래밍
- 함수를 이용한 프로그래밍으로, 함수를 인자값으로 사용하거나 리턴값으로 사용할 수 있으며, 순수 함수를 만들어 모듈화 수준을 높이는 프로그래밍
- 람다식(Lambda Exp) : 메서드(함수)를 하나의 식으로 표현한 것, 메서드(함수)를 간략하면서도 명확한 식으로 표현
  - 메서드를 람다식으로 표현하면 메서드명(이름)과 반환값(return)이 사라지게 되는데
  - 이런 람다식으로 표현된 메서드를 익명함수라고 한다.
- 함수를 First-class object로 사용할 수 있어야 한다
  - 여기서 (First-class) 요소(Object)라는건데 : class가 자바클래스가 아니고 Object가 자바의 객체랑은 다르다 
  - First-class 요소는 몇가지 조건/특징이 있는데
    - 변수에 할당할 수 있는 요소
    - 메서드에 파라미터로 넣어서 전달해줄수 있는 요소
    - 리턴값으로 받아볼수 있는 요소
  - 이 모든 요소의 조건들이 함수(혹은 메서드)가 충족시킬때 : 함수가 First-class 라고 부를수 있다
- 순수함수(Pure function)
  - 순수함수는 side-effect가 없다. 즉 함수 외부에 영향을 미치지 못함
  - 상태가 없다. 즉 입력이 동일하면 출력도 무조건 동일하다
  - HTTP / RESTful-API 에서 말하는 멱등성과 유사하다
  - 외부에 정의되어 있는 람다식 내부에서 변경하려는 경우 Pure function이 아니며
    - 자바에서는 컴파일 에러를 발생시킨다(컨셉 유지를 위해)
- 고차 함수(High-Order Fucntion)
  - 함수가 함수를 매개변수로 받을 수 있는 경우
  - 함수가 함수를 리턴할 수 있는 경우
- 함수형 프로그래밍 패러다임을 지키기 위해서는 
  - input과 output과 동일
  - 외부환경으로부터 철저히 독립적 : 외부에 어떤데이터를 읽지도 쓰지도  참조하지도 않음
  - 순수함수 : 같은 input은 무조건 같은 output이 나온다. (사이드 이펙트가 없다)
  - 외부변수 오류가 생기지 않음., 부작용(부수효과)에 의한 상태가 없어서 주목받음, 
  - 입력대비 출력이 정해져있으므로, 멀티프로세싱과 궁합이 좋다
  - 함수를 특별하게
    - 함수는 1등 시민이 된다
    - 함수를 타입으로 지정할수 있다
    - 함수를 입력파라미터로 넣어줄 수 있다
    - 함수를 리턴으로 받을 수 있다
- FP 공부법
  - 선언형 프로그래밍이다 : 이전에는 명령형 사고
  - 함수도 변수처럼 생각, 값으로 바라보고 생각
  - 메서드에서 함수로의 사고와 관점의 전환이 필요
    - 함수는 클래스에 독립적이고 더 제네럴한 표현
    - 메서드는 클래스에 종속적이고 객체지향에 적합

## FP 쓰면 좋은이유,
- 테스트하기 용이하다  : 좋은 코드일 수 밖에 없다
- Side-Effect 가 없거나 적다, Side-Effect 가 일어나게 한다면 FP라고 말하기에는 곤란
- 멀티쓰레드에 안전하다, 멀티코어에 적합한 프로그래밍 패러다임


## 자바에서의 람다식 표현
- 기본형
 `(int a, int b) -> { return a > b ? a : b; }`

- 블록 생략
  `(int a, int b) -> a > b ? a : b `
  - return 생략
  - 단순식일경우 생략 가능
  - 한줄(single statement)일때는 ;(세미콜론), 블록({}=브레이스)도 생략가능
  `- (int i) -> System.out.println(i)`

- return이 포함되어 있으면 중괄호 생략 불가능
  `(o1, o2) -> return (o1.compareTo(o2));`

- 타입 추론이 가능한 경우 타입 생략도 가능
  `(a, b) -> a > b ? a : b`

- 타입이 없고 매개 변수가 하나일 경우 괄호도 생략 가능
  `a -> a * a`

### 변환공식
- 메서드의 이름과 반환타입을 제거
- 함수 블록{} 앞에 화살표(→) 를 추가
- 반환값이 있는경우
    - return 생락가능
    - 마지막에 ; 를 붙이지 않음
- 입력매개변수의 자료형이 생락가능(대부분 가능하며, 추론이 가능해야함)
- 매개변수가 하나인경우, 괄호는 생략 가능
- 블록안의 문장이 하나뿐일 때는 괄호 생략가능
- 하나뿐인 문장이 return문이면 괄호생략불가



## 일급요소 가 뭐냐고..

### 왜 first class citizen 인가? (+뇌피셜 함유)

- 과거에는 진짜 돈많고 잘사는 소위 "귀족"들이 대통령을 지들끼리 쑥떡쑥떡해서 뽑아서 1등시민(귀족), 2등시민(백인), 3등시민(나머지) 요런 개념이 대놓고 있었는데, 요즘에는 괜히 이런말 꺼내면 혼나는 시대가 왔다
- 미국 정치제도에서 대통령 선출 방법은 아직도 직접선거제가 아니라, 대의원들의 투표를 통한 간접선거제인데요
  - 물론 각State별 대의원수, 득표율, 승자독식제 어쩌고가 있는데 이런개념은 다음 미국대선때 티비에서 엄청 설명해줄테니까 패스
- 막 그렇게 유쾌한 출처는 아니라 다들 묵념+봉인 하는게 아닐까.. 하는생각! 아무튼 1등시민은 나라의 동작과 운영에 참여할수 있고 앞뒤로 나서서 대표할수 있는(parameter, 변수에 담아서 전달) 그런 느낌이 아닐까 추론.. 
- [출처링크](http://cefia.aks.ac.kr:84/index.php?title=4%EA%B3%BC_%EC%A0%95%EC%B9%98%EA%B6%8C%EC%9D%98_%EB%B0%98%EC%9D%91)

### First-class 설명

- 변수에 할당할수 있어야 하고
  - var variable = first-class-Something;
- 메서드에 파라미터로 넣어서 전달
  - Method( first-class_1, first-class_2 );
- 리턴으로 받을수 있다
  - var variable = Method( first-class_1, first-class_2 );

- 과거의 자바는 객체지향언어라서
  - java8 이전까지 First-Class는 객체와 값(프리미티브 타입)
  - 함수를 변수에 담을수 없었다

### C/C++ 에서는

- 함수포인터 등을 활용해서 함수를 일급요소(Object)로 사용할수 있다

- ```c++
  #include <stdio.h>
  
  void hello() // parameter(입력매개변수)와 return 이 없는 함수
  {
      printf("Hello, world!\n");
  }
  
  int sum(int a, int b) // 반환값과 매개변수가 없음
  {	
      printf("hello sum\n");
      return a+b;
  }
  
  int main()
  {
    // function pointer 사용을 위해서는 함수 시그니쳐가 동일해야 함
    // 시그니쳐 = return(반환값) + parameter(입력매개변수의 타입 && 갯수 && 순서)
    
    void (*fp_type1)();   // 반환값과 매개변수가 없는 함수 포인터 변수 선언
    fp_type1 = hello;     // hello 함수의 메모리 주소를 함수 포인터 fp에 저장
    fp_type1();           // Hello, world!: 함수 포인터로 hello 함수 호출
  
   	int (*fp_type2)(int, int) // 함수포인터 변수 선언
    fp_type2 = sum; // sum 함수의 메모리 주소를 함수 포인터 변수에 저장
    fp_type2(); // 함수 포인터로 변수에 담겨있는 함수 호출
  
    void * vp; // 타입 멈춰!
    vp = sum;
    vp = hello;
    vp = main;
    
    return 0;
  }
  ```

- 당연히 입력 파라미터로 함수(정확히는 함수포인터)를 사용 가능

- ```c++
  #include <stdio.h>
  
  int sum(int a, int b) { return a + b; }
  
  int mul(int a, int b) { return a * b; }
  
  int cha(int a, int b) { return a - b; }
  
  int div(int a, int b) { return a / b; }
  
  void executer(int (*fp)(int, int), int front, int back)    
  {
    // 함수 포인터를 매개변수로 지정, 함수(함수포인터)도 다른 자료형처럼 입출력 Type으로 사용
    printf("%d\n", fp(front, back)); // 매개변수로 함수 호출하고, 결과값을 출력
  }
  
  int main()
  {
    executer(sum,1,2);    // executer를 호출할 때 함수의 메모리 주소를 전달
  	executer(mul,3,4);
  	executer(cha,5,6);
  	executer(div,7,8);
    return 0;
  }
  ```

- 이런걸 배웠었죠... 

### Java의 람다는 함수를 First-class요소로 다룰수 있도록 도와줌

- 자바8부터 함수를(함수같이 보이는 무언가를)변수에 선언하거나, 매서드에 전달하거나 하는 등의 행위가 가능

```java
String [] names = {"DONG","SAN","JANE","COOP","WOODY"};

Comparator<String> stringComparator = ((o1, o2) -> o1.compareTo(o2));
Comparator<String> sc2 = String::compareTo;

Arrays.sort(names,stringComparator);
Arrays.sort(names,sc2);
Arrays.sort(names,Comparator.reverseOrder());
Arrays.sort(names,Comparator.naturalOrder());
Arrays.sort(names,(s1, s2) -> s1.length()+s2.length());
//type만 맞춰준 형태일뿐 아무런 의미없는 코드

System.out.println(Arrays.toString(names));
```

- 이 한줄에서 reverseOrder메서드를 따라가보면
```java
Arrays.sort(names,Comparator.reverseOrder());
```

- 요기부분 따라가보면
```java
public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
    return Collections.reverseOrder();
}
```
- 리턴타입이 (Comparator<T>) 함수인, 그런 메서드이고
```java
public static <T> Comparator<T> reverseOrder() {
    return (Comparator<T>) ReverseComparator.REVERSE_ORDER;
}
```
- Comparator<T> 를 따라가보면 결국 
```java
@FunctionalInterface
public interface Comparator<T> {
```
- 결국 "@FunctionalInterface 어노테이션이 붙은" 인터페이스의 "메서드 하나"라는걸 알수 있다



## 자바에서 제공하는 @FunctionalInterface 어노테이션

### 예제코드

```java
public class Launcher152 {
    public static void main(String[] args) {
        int testNum = 20;

        System.out.println("1 - old java way");
        Something runSomething = new Something() {
            @Override
            public int justDoIt(int number) {
                //testNum++; 사이드 이펙트를 만들 수 없다.(함수 밖에 있는 값을 변경하지 못한다.)
                return number + 10;
            }
        };
        System.out.println(runSomething.justDoIt(10));
        // 익명 내부 클래스 방식이 사용되던 과거




        System.out.println("2 - after Java8, use Lambda");
        Something runSomeLambda = (number) -> {
            //testNum++; // 변경안됨 1
            return number + 20 + testNum;
        };
        //System.out.println(testNum++); // 람다식 내부에 쓰인 변수들은 수정할수 없는 effectively final 상태가 된다
        System.out.println(runSomeLambda.justDoIt(10));
    }
}

// testNum++ 주석처리한 이유 : 컴파일 에러 발생
// 람다식 내부에서 side-effect 만들 수 없도록 컴파일 에러 발생(Variable used in lambda expression should be final or effectively final)
// 함수 밖에 있는 값을 변경하지 못한다 = make atomic !!


@FunctionalInterface // 자바에서 지원하는 어노테이션
interface Something {
    int justDoIt(int number);
}
```

- 위 예제코드로 알수있는 사실 
  - 자바 Lambda가 나름 순수함수로 동작할 수 있도록 제한을 걸어둠(effectively final)
    - 외부에서 정의된 값을 변경할수 없도록 컴파일 에러 발생시킴
  - 과거에도 비스무리한 "익명내부클래스" 같은 방식을 사용해서 람다 비슷한걸 만들어 썻는데
    - 이름없는 클래스를 바로 만들어서 매서드 하나만 생성해서 동작하도록
  - 자바 Lambda 도 원리는 동일한데
    - Interface 에 단하나의 유일한 메서드만 만들고 호출해서 사용하는 방식
    - 물론 따로  Interface를 생성하지 않고 코드 in-line에서도 사용 가능
- 람다 도입의 효과 in after Java8
  - 과거에 사용하던 "익명 클래스"로 만들어 쓰는 대신 대신 "람다식"을 사용할 수 있게 됨
  - 덕분에 고차함수를 간결하게 작성하고 가독성도 좋아짐
  - 함수형 프로그래밍 패러다임을 객체지향 언어 자바에 잘 녹여낼수 있었다
- 람다식을 익명 함수라고 부르지만 사실 익명 객체라고 볼 수 있다
  - 이름없는(익명)객체인 이유는 메서드 하나만을 갖는 객체
  - 다시말해서 "진짜 함수만을" 1급객체로 다루는게 아니라, 메서드를 "이름없는 객체"로 한번 감싸준것이 자바가 람다식을 다루는 방법이니까!
  - 사실 근본 람다식은 익명함수 이지만,
  - 자바에서는 익명함수가 아니라 익명클래스의 익명객체, 객체의 생성과 선언을 동시에해주는 특별한 어떤 문법적 요소
- 람다와 스트림(Lambda && Stream)
  - Java8의 람다는 기존 컬렉션 프레임워크의 체계를 뒤흔들기보다는, 람다를 도입하면서 콤보로 같이쓰기에 좋은 인터페이스인 Stream을 같이 도입 
  - Stream 이야기는 뭐 다음주에 하던지..?

## 미리 정의해 놓은 Functional Inteferface
- Java JDK개발자들이 만들어놓은 함수형 인터페이스가 준비되어 있다
- 같은 코드를 개발자들이 반복해서 생성하지 않도록 자주 사용하는 함수형 인터페이스를 미리 정의해둔곳이
  - java.lang.funcation 에 가보면 있다 : 링크 
  - 뭘 좋아할지 몰라서 다 준비해봤어!
  - Runnable을 제외하고 java.util.function 패키지 안에 있음
  - DRY규칙 (Don't repeat yourself) 기억하십시오 휴먼

### 사용법1 인터페이스로 선언해놓고 사용하는경우
  - 결국 자바 람다식의 본질은 인터페이스에 메서드 하나
  - 과거에 익명내부클래스 쓰던거랑 바이트코드상 100% 일치하지는 않음
  - 자바에서 람다식을 특수한 오브젝트의 하나로 취급함!!!!!

### 사용법2 코드 인라인 방식으로 사용하는 경우
  - 동작코드가 한줄만 있으면 브레이스{} 생략가능
  - 두줄이상이면 브레이스{} 해줘야함 (if문의 그것과 유사)
  - 메서드에 객체를 한번 덧씌워주는데
    - 과거에는 익명내부클래스라는 문법요소로 메서드를 덧씌워줬다면
    - 람다 공식지원버전(Java8) 이후로는 람다 전용의 객체로 덧씌워주는 방식임
  - 예시는 기억안나면 위로가서 보고오세요.....

## Java가 기본으로 제공하는 함수형 인터페이스

- 자바 람다식의 표준 레퍼런스 구현체
- [java.lang.funcation 패키지](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) 참고하면 훨씬~ 더 많은 함수형 인터페이스들이 있다
  - 코드 링크  : 준비해둠
- 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
  - Function<T, R>
  - BiFunction<T, U, R>
  - Consumer<T>
  - Supplier<T>
  - Predicate<T>
  - UnaryOperator<T>
  - BinaryOperator<T>

 

Function<T, R>

- T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t)
- 함수 조합용 메소드
  - andThen
  - compose

 

BiFunction<T, U, R>

- 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t, U u)

 

Consumer<T>

- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
  - void Accept(T t)
- 함수 조합용 메소드
  - andThen

 

Supplier<T>

- T 타입의 값을 제공하는 함수 인터페이스
  - T get()

 

Predicate<T>

- T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
  - boolean test(T t)
- 함수 조합용 메소드
  - And
  - Or
  - Negate



UnaryOperator<T>

- Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스

 

BinaryOperator<T>

- BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수 인터페이스



// todo 위에꺼 표로 정리


## 함수를 객체로 바라보는 연습예제(by 자바정석)


```java
@FunctionalInterface
interface Myfunc{
    void mymethod();//public abstract void mymethod()
}
```

```java
Myfunc f = () -> {};
Object obj = (Myfunc) (() -> {});
String s = ((Object) (Myfunc) (() -> {}) ).toString();

System.out.println(f);
System.out.println(obj);
System.out.println(s);
//System.out.println(()->());
System.out.println((Myfunc)(() -> {}));
//System.out.println((Myfunc)(() -> {}).toString;
System.out.println(( (Object) (Myfunc)(() -> {})).toString());
System.out.println(( (Object) (Myfunc)(() -> {})).toString());
```

### 위 예제의 해석

- 함수형 인터페이스로 람다식을 "참조"할 수만 있다.
- 람다식의 타입이 함수형 인터페이스 타입과 일치하는것은 아니다
- 람다식은 익명 객체이고, 타입이 없다(있긴 있는데, 컴파일러가 임의로 이름을 지정해버리그 때문에 사용자코드에서는 이름이 없고, 그래서 익명이다)
- 대입 연산자는 양변(L-val과 R-val)의 Type이 같야아 한다 그래서 아래 코드와 같은 형변환이 필요한데

```java
MyFunc f = (MyFunc)(() -> {});
```

- 람다식은 MyFunc 인터페이스를 구현하지 않았지만, 해당 인터페이스를 구현한 클래스객체와 완전히 동일한 객체이기 때문에 형변환이 허용된다
- 위 형변환은 생략가능
- 자바에서의 람다식은 이름이 없을뿐 객체인데, 자바 최상위 Object 타입으로의 형 변환이 불가능하다
- 람다식은 오직 함수형인터페이스로만 형 변환이 가능하다

```java
Object obj = (Myfunc) (() -> {});
```

- 굳이 꼭 Object 타입으로의 형 변환하려면, 먼저 함수형 인터페이스로 변환후 가능하다

```java
Object obj = (Myfunc) (() -> {});
String s = ((Object) (Myfunc) (() -> {}) ).toString();
```


## 쉐도잉

- ㅇㅏ 어렵다 쉐도잉

- 사실상 람다는 람드를 감싸는 스콥과 같다
- 람다는 쉐도잉이 일어나지 않는다
- 씨계열에서 지역성 가까운거부터 변수 스코프 /라이프타임으로 잠깐 배우고 넘어거ㅏ미

- 람다는 다르다
  - 같은 스코프 내에 동일한 변수이름 정의 불가능
  - 람다는 같은 스코프이다!

## 보충필요 todo



https://rlawls1991.tistory.com/entry/%EB%9E%8C%EB%8B%A4-%ED%91%9C%ED%98%84%EC%8B%9D?category=878314



개발자의 공부이야기 참고


### 메소드 레퍼런스

- 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.

## 메소드 참조 && 생성자 참조 
## Todo : 표좀 정리해라

| 스태틱 메소드 참조               | 타입::스태틱 메소드            |
| -------------------------------- | ------------------------------ |
| 특정 객체의 인스턴스 메소드 참조 | 객체 레퍼런스::인스턴스 메소드 |
| 임의 객체의 인스턴스 메소드 참조 | 타입::인스턴스 메소드          |
| 생성자 참조                      | 타입::new                      |
 
  - static 메서드 : 매개변수 객체의 인스턴스 메서드(함수형 인터페이스를 구현한 인스턴스)
 - 인스턴스 메서드 : 지금 만든 그 함수
  - 생성자 : 
  
 
- 람다식의 축약형 표현
  - 람다 표현식에서 단 하나의 메소드만을 호출하는 경우에만 사용가능
  - 메소드 또는 생성자의 매개변수로 람다의 입력값을 받고
  - 리턴값 (또는 생성한 객체는 람다의 리턴값)이 없을때 사용
  - 메서드 참조는 메서드명 앞에 구분자 "::" 콜론 두개를 붙이는 방식으로 메서드 참조를 활용
  - 예를들어, 람다가 메서드명을 직접 참조하는 것
- 생성자도 참조 가능
  - 생성자는 메서드가 아니지만 특별한 함수비슷한 무언가니까..

```java
(x) -> System.out.println(x)
System.out::println
```
- 이 왜 인텔리제이에서 노란줄 그어주는거 Optional에서 돌아오는거
```java
public Member getMember(Long id) {
    return memberRepository.findById(id).orElseThrow(NoSuchMemberException::new);
}
```
- 위 코드에서 사실 `NoSuchMemberException::new` 인텔리제이가 노란줄 그어줘서 알았지만 메소드 참조 기능을 우리는 쓰고있었다!









## TMI : Java에서 Lambda를 도입하게 된 스토리와 배경
- 출처 : https://velog.io/@kwj1270/Lambda
Lambda 등장 배경

하나의 CPU 안에 다수의 코어를 삽입하는 멀터 코어 프로세서들이 등장하면서      
일반 프로그래머에게도 병렬화 프로그램이에 대한 필요성이 생기기 시작했다.        

이러한 추세에 대응하기 위해 
자바8 에서는 병렬화를 위한 컬렉션(배열, List, Set, Map)을 강화했고,    
이러한 컬렉션을 더 효율적으로 사용하기 위해 스트림이 추가되었고   
또 스트림을 효율적으로 사용하기 위해 함수형 프로그램이,    
다시 함수형 프로그래밍을 위해 람다가,   
또 람다를 위해 인터페이스의 변화가수반되었다.   
람다를 지원하기 위한 인터페이스를 함수형 인터페이스라고 한다.  
이를 정리하면 아래와 같다.

빅데이터 지원 -> 병렬화 강화 -> 컬렉션 강화 -> 스트림 강화 -> 
람다 도입 -> 인터페이스 명세 변경 -> 함수형 인터페이스 도입



## TMI 키워드 정리

```java
메서드와 함수의 차이
1급객체
순수함수
Funtion 은 First Class Citizen이다!
Colosure(**람다 또는 클로저 closure)
고차함수(고계함수)
프로그래밍 패러다임(객체지향과 함수형 패러다임의 공통점과 차이점)**
```


### 메서드와 함수의 차이

- 메서드랑 함수랑 같은건데 다르다.
- 뭔말이냐면 관점과 패러다임은 다르지만, 의미하는 바는 같다.
    - 메서드 : 객체지향(oop)에서 객체의 행위나 동작을 표현, 반드시 클래스에 소속됨
    - 함수 : 하나의 특별한 목적의 작업을 수행하기 위해 독립적으로 설계된 코드의 집합, 
    : oop 패러다임 이전에 C/C++ 같은 절차지향적 언어들에서는 "단위 작업을 캡슐화" 하는데 사용
- 자바 8버전 이후에 람다식을 통해 매서드 하나로 뭔가를 할수 있게 되서... 아무튼 대부분 비슷한 용어로 쓰는데 문맥이나 맥락상 통하는 단어고, 두 단어를 크게 구분짓는다고 좋을것도 없고 혼동될꺼도 없을듯 하다..

### 1급 객체

- 1급 함수라고도 하며 자바에서는 함수형 인터페이스를 통해 **구현** 이 가능
- 보통 자바스크립트를 배울때 많이 나오는 내용
- 변수나 데이터 구조안에 넣을 수 있다.
- 파라미터로 전달 할 수 있다.
- 동적으로 프로퍼티 할당이 가능
- 리턴값으로 사용할 수 있다.

### 순수함수(Pure Function)

- 입력값이 동일하면 결과가 동일하게 리턴되는 함수. (Test 할때 정확도)
- 부작용 (Side-effect)가 없는 함수
- 함수의 실행이 외부의 상태를 변경시키지 않는 함수
- 함수에서 외부 인자를 변경하지 않고, 입력이 같다면 출력은 무조건 같다
    - 오직 입력에 의해서만 출력이 정해지고, 환경이나 상태에 영향을 받아서는 안된다
- 순수한 함수는 멀티 쓰레드 환경에서도 안전하고, 병렬처리 및 계산이 가능
- 객체지향에서는 객체들간의 상호작용이 중요한것에 대비됨

### 함수와 메서드의 차이
- 자바에서는 함수의 개념이 없다. 오직 클래스 내부의 매서드 뿐
- 메소드 
  - 함수 중에서 오브젝트/클래스에 종속적인 함수를 메서드라고 한다
  - 자바의 메소드는 일급 요소가 아니므로, 다른 메소드로 전달할 수 없다.
  - 자바에는 모든 것이 객체다. 메소드는 객체의 행위를 정의하고 객체의 상태를 변경한다
  - 이런 이유로 기존의 자바 언어 체계에서는 함수형 언어를 언어 차원에서 지원하지는 못하였다.
  - 함수형 프로그래밍의 조건을 만족하도록 구현하지 못했었는데... (8버전 이전에는)
- 함수
  - 오브젝트/클래스에 종속적이면 메서드, 독립적이면 함수라고 칭함

### 그래서 Java8에서는 함수형 인터페이스(Funtional interface, 클래스 앞에 @붙여서 표현해주면 됨) 개념을 도입했어요

- 함수형 인터페이스의 경우, 람다식으로 표현이 가능하도록 자바에서 새로 지원하게 됨
- 그래서 함수형 인터페이스라는 개념과 람다식 표현을 통해 입력에 의해서만 출력이 결정되도록 ‘순수한 함수’를 표현할 수 있게 됨!
    - 람다식으로 표현함으로써 ‘익명 함수’를 정의할 수 있게 되었고
    - 함수형 인터페이스의 메소드에서 또다른 함수형 인터페이스를 인자로 받을 수 있도록 하여 ‘고계 함수’를 정의할 수 있게 되었다.
- 함수형 프로그래밍 패러다임을 언어차원에서 새로 도입! (멀티패러다임이 따로 없군요!)

- 순수함수 예제코드

```java
public interface Functional1 {
  boolean accept();
}

public interface Functional2 {
  boolean accept();
  default boolean reject() { return !accept(); }
}

@FunctionalInterface
public interface Functional3 {
  boolean accept();
}

public interface NotFunctional {
  boolean accept();
  boolean reject();
}
```

- Functional1, 2, 3는 모두 함수형 인터페이스를 만족
- Functional3 인터페이스의 경우, @FunctionalInterface 어노테이션이 있는데, 컴파일러에게 명시적으로 Functional Interface 임을 알려주고 규칙 위반(하나의 인터페이스에 오직 하나의 함수만) 시 컴파일 에러를 뿜어준다

### Funtion 은 First Class Citizen이다!

- 함수형 언어에서는 함수도 하나의 값으로 취급하고, 함수의 인자로 함수를 전달할 수 있는 특성이 있다. 이러한 함수를 일급 객체 (a.k.a 일급 함수)라 칭함
- First Class Citizen이 되기 위한 3가지의 특징
    - parameter로 함수를 받을수있고
    - retrurn으로 함수를 돌려줄수 있고
    - variable / data Structure로도 함수를 사용한다
- First Class Citizen을 위한 구현방법 : Anonymous Funtion(자바는 Method)
    - 자바에서는 Lamda Expresstion 이라고 함

### Colosure(**람다 또는 클로저 closure)**

- 클로저 closure는 람다계산식(lamda Calculus) 구현체
- 이름 없는 함수(anonymous function)로 리터럴하게 작성가능
- 선언된 범위(scope)에서 접근 가능한 변수를 캡처해서 저장하고 닫힘
- Java(8이후) 클로저는 캡처한 변수를 참조(reference)한다
- 자바에서도 람다를 활용할 수 있다.
    - (JS나 Swift에서는 함수형 프로그래밍 클로저를 쉽게 만들 수 있고)

- 클로져 예제코드

```

```

### **고차함수(고계함수, Higher-order Function)**

- 함수를 더 추상화하면 차원이 높아지는 고차함수로 표현할 수 있다.
- 함수를 다루는 상위의 함수
- 
- 주 사용 용도는
    - 콜랙션을 탐색하고, 비교하고, 찾아서 정리하는 기능 (여러 값이 들어있는)
- 고차함수 예제코드

```java

```

### 익명함수(Annonymous Function)

- 이름이 없는 함수(자바의 메서드)를 정의할 수 있어야 한다.
- 이러한 익명 함수는 대부분의 프로그래밍 언어에서 ‘람다식’으로 표현하고 있다
- 이론적인 근거는 람다 대수(람다 계산, 람다 계산법)에 있다.

    : 추상화와 함수 적용 등의 논리 연산을 다루는 형식 체계이다

    : [https://ko.wikipedia.org/wiki/람다_대수](https://ko.wikipedia.org/wiki/%EB%9E%8C%EB%8B%A4_%EB%8C%80%EC%88%98)

- 람다식으로 표현된 메서드를 익명함수라고 한다.
- 메서드를 람다식으로 표현하면 메서드명(이름)과 반환값(return)이 사라지게 되는데
- 람다식(Lambda Exp) : 메서드(함수)를 하나의 식으로 표현한 것, 메서드(함수)를 간략하면서도 명확한 식으로 표현

### **프로그래밍 패러다임**
- **객체지향 패러다임 VS 함수형 패러다임 차이가 뭔지**
- 객체지향 : 객체를 모델링, 상호작용, 흐름제어가 중요해
- FP는 흐름 하나, 스테이지 하나 입력에 대한 출력만 중요해
- 실제 객체를 모델링해서 객체들과의 상호작용 같은걸 생각하면 객체지향이 편한데
어떤 일을 순서대로 처리한다는 느낌으로는 함수형이 좋은거같아요
- FP 가 처음 접하는 진입점에서는 어색함을 인정하고 그냥 받아들이자


## TMI 인터페이스

- 펑서녈 인터페이스 어노테이션에다가 업스트랙트, 스태틱 매서드 있어도 에러안나는거 하나 보여줘

## TMI 람다식이랑 스트림 무슨사이야?
- https://www.notion.so/15-757106032d85452cbc60cf1808d53978




## TMI 람다 내부 동작 분석 
## invokedynamic
- Java SE7 부터 등장한 새로운 바이트 코드 셋이 invokedynamic

- 기존에 invoke 시리즈는 4가지만 존재했었습니다.
  - invokevirtual - instance 메소드를 디스패치 하기 위한 명령어.
  - invokestatic - static 메소드를 디스패치 하기 위한 명령어.
  - invokeinterface - 인터페이스를 통해서 method를 디스패치 하기 위한 명령어.
  - invokespecial - 생성자, 수퍼클래스, private method 등 invoke-virtual이 아닌 메소드들을 디스패치하기 위한 명령어.
  - 이사람은 미쳤어.. https://www.notion.so/15-757106032d85452cbc60cf1808d53978
 
 

## FP+Stream 해커랭크 문제에서 입력데이터 가공할때(문자열 -> 배열)
- 코드보고가라

```java
String input =
                "3 3 " +
                "1 3 4 " +
                "2 2 3 " +
                "1 2 4 ";

String[] firstMultipleInput = input.replaceAll("\\s+$", "").split(" ");
int H = Integer.parseInt(firstMultipleInput[0]);
int W = Integer.parseInt(firstMultipleInput[1]);
List<List<Integer>> A = new ArrayList<>();

IntStream.range(0, H).forEach(i -> {
    A.add(
            Stream.of(input.replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList()));
    int result = Result.surfaceArea(A);
});
```
- 문자열을 가공하는 우아한 방법


## 마지막으로..
### 리뷰가 중요해
- 기선님 유툽영상 다 보기 힘들다면 야돈님의 피드백이라도 보는거 츄천 : https://yadon079.github.io/2021/java%20study%20halle/week-15

### 더 공부해야할 키워드
- 자바 리액티브,
- 스코프,
- call-by-name,
- 모나드
- 커링(Curry, Curring)
- 함수 역할 나누기 연습 잘하기!!!!!!!!!!!!!!!!!!!!!(개중요)
    - 레고블럭 하나가 없는데 어떻게
    - 함수 역할 정의를 잘 하기
    - 함수를 순수하게 만들기



## 참조 && 출처
- https://velog.io/@honux/%EB%B0%B1%EA%B8%B0%EC%84%A0-%EC%9E%90%EB%B0%94-%EC%8A%A4%ED%84%B0%EB%94%94-15-%EB%9E%8C%EB%8B%A4
- https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
- [https://www.oracle.com/technical-resources/articles/java/architect-lambdas-part1.html](https://www.oracle.com/technical-resources/articles/java/architect-lambdas-part1.html) : 오라클 공식 FP 문서
- [https://dinfree.com/lecture/language/112_java_9.html](https://dinfree.com/lecture/language/112_java_9.html)
- [https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)
- [https://medium.com/@la.place/higher-order-function-이란-무엇인가-1c61e0bea79](https://medium.com/@la.place/higher-order-function-%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-1c61e0bea79)
- [https://youtu.be/jVG5jvOzu9Y](https://youtu.be/jVG5jvOzu9Y)  : 코딩사전님 FP를 초보자용으로 쉽게 설명
- [https://skyoo2003.github.io/post/2016/11/09/java8-lambda-expression](https://skyoo2003.github.io/post/2016/11/09/java8-lambda-expression) : 정리가 잘되어있는데 나는 어렵다.,..


## 자바 스트림 예고


- stream 에서 reduce?

```java
return factors.stream().reduce(0,Integer::sum);
```

- 상속받을때 super 키워드 (리뷰)

      - 부모생성자 호츨해서 초기화

- 함수형의 불변성 : protected final int

- 함수에서 public static final

- 매서드에서 final : 재정의안됨 오버라이딩 X

- Intstream 이란 도대체 뭘까


- 포이치, 맵, 필터, 이런거 잘 쓰는게 중요해
    - 포이치 foreach
    - 맵map
    - 썸과 에브리(some & every)
    - 리듀스
    - 필터 : filter

### **자바 Java Stream 인터페이스**

`스트림stream`은 자바 8 API에 새로 추가한 기능이다. 스트림을 이용해서 선언형으로 콜렉션 데이터를 처리하도록 구현할 수 있다.

### **filter**

요구사항은 파일 문자 중 길이가 12보다 큰 문자의 수를 구한다.

```java
// next.fp.StreamStudy countWords method

String contents = new String(Files.readAllBytes(
  Paths.get("../ war-and-peace.txt")), StandardCharsets.UTF_8);
List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

long count = 0;
for (String w : words) {
  if (w.length() > 12) count++;  
}
코드복사
```

---

### **filter 활용해 구현**

```java
String contents = new String(Files.readAllBytes(
  Paths.get("../alice.txt")), StandardCharsets.UTF_8);
List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

long count = 
  words.stream().filter(w -> w.length() > 12).count();
코드복사
```

---

### **map**

List에 담긴 모든 숫자 값을 2배한 결과 List를 생성한다.

```java
// next.fp.StreamStudy 클래스의 doubleNumbers method 참고
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> dobuleNumbers =
  numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
코드복사
```

---

### **reduce**

List에 담긴 모든 숫자의 합을 구한다.

```java
// next.fp.StreamStudy 클래스의 sumAll method 참고

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

public int sumAll(List<Integer> numbers) {
    return numbers.stream().reduce(0, (x, y) -> x + y);
}
코드복사
```

