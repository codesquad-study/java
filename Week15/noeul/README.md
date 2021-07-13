## 들어가기전에

> JAVA의 Method는 First Class Citizen(1급 객체)가 아니다.

<br>

1. 파라미터로 전달 할 수 있다. (**X**)

```java
public String getName(){
			 ...
}

findByName(getName); // getName()으로 return이 아닌 메소드 자체
```

2. return값으로 method를 받을 수 있다. (**X**)

```java
public doSomething(){
	return getName; // 메소드 자체 
}
```

3. 변수나 데이터 구조안에 담을 수 있다. (**X**)

```java
List<> list = Arrays.asList(getName) // 메소드 자체
```



> JAVA 8 이후 부터 함수형 프로그래밍을 지원하게 됨.
> Functional Feature를 추가하게 된 이유는 First Class Function을 가지기 위함.



##### 자바에서 First Class Function 를 표현하기 위한 방법

- Anonymous Method (익명 메소드)
- Lambda Expression `method(i->i+1)`



<br>



# 람다식(=익명함수)

- 함수형 프로그래밍 기법(`y=f(x)`)
  - 메서드를 하나의 식(expression)으로 표현한 것

- 상황에 따라 다르지만, 코드가 매우 `간결`해진다.
- 컬렉션 요소(대용량 데이터)를 `필터링` 또는 `맵핑` 해서 쉽게 `집계` 할 수 있다.

- 데이터를 매개값으로 전달하고 결과를 받는 코드로 구성

- `람다식`은 `하나의 메소드`를 정의함.

- `람다식`은 익명 클래스와 동작방식이 다르다.

  - 익명 클래스는 매 번 새 클래스를 정의하고 객체를 생성하는 문제점이있음.

  - 따라서, 람다는 위 문제점을 해결하는 방식으로 구현됨.

  - 람다식은 컴파일 타임에 클래스가 정의되지 않고, `런타임`에` JVM`이 하도록 떠넘긴다고 함.

  - 💫 자세한 내용: https://dreamchaser3.tistory.com/5

    

## 람다식 사용법

- 🔆 자바는 `람다식`을 `함수형 인터페이스`의 `익명 구현 객체`로 취급
  - `함수형 인터페이스`는 메소드 한 개만 가지고 있는 인터페이스의 형태를 말한다.
  - 즉, 아무 클래스, 인터페이스 등에 람다식을 사용할 수 없다.
- `익명 함수`답게 `메서드`에서 `이름`과 `반환타입`을 제거하고 매개변수 선언부와 몸통`{}` 사이에 `->` 를 추가

```java
반환타입 메서드이름(매개변수 선언){...}
```

람다식 변환 🔽

```
(매개변수 선언) -> {...}

( int a ) -> { System.out.println(a); }
```



#### 람다식의 코드 간결성

```java

// 1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9 : 10 을 만드시오.
// <조건> 마지막 문자열 10 뒤에는 ":" 문자가 없어야 함.

// case 1
// 너무 복잡함.
final String separtor = " : ";
for(Integer number : numbers){
	StringBuilder.append(number).append(separator);
}
final int stringLength = strignBuilder.length();
if(stringLength > 0){
	stringBuilder.delete(stringLength - separator.length(), stringLength);
}
System.out.println(stringBuilder.toString());


// case 2
final String result = numbers.stream()
    						 .map(String::valueof)
                             .collect(joining(" : "));

```



##### 람다식 예시)

- `어떤 인터페이스`를 구현할지는 대입되는 인터페이스에 달려있다.
- 람다식은 익명 클래스의 객체와 거의 `동등`하다.

```java
# 람다식

Runnable runnable = ()->{...};  

# 익명 클래스 

Runnable runnable = new Runnable(){
	public void run() {...}	 // 함수형 인터페이스(Runnable)은 메소드 한 개
}
```



#### 람다식 약식 타입

- 매개 타입은 런타임시에 대입값에 따라 자동으로 인식하기 때문에 생략 가능

  ```java
  (a) -> {System.out.println(a);}
  ```

- 매개변수가 `없다면` 괄호 `()` 를 생략할 수 없음

  ```
  () -> {실행문; ...}
  ```

- 하나의 매개변수만 있을 경우에는

  ```
  a -> {System.out.println(a);}
  ```

- 리턴값이 있는 경우, `return` 문 사용

  ```
  (x,y) -> { return x+y; };
  ```

- 하나의 실행문만 있다면 중괄호 `{}` 생략 가능
  ```
  a -> System.out.println(a)
  ```

- 중괄호 `{}`에 `return`문만 있을 경우, `중괄호`와 `return` 생략 가능

  ```
  (x,y) -> x+y
  ```

  

## 함수형 인터페이스

- 하나의 추상 메소드만 선언된 인터페이스를 말함.

- 반면에, `static` 메서드와 `default` 메서드의 개수에는 제약이 없음.

- `@FunctionalInterface`

  - 하나의 추상 메소드만을 가지는지 컴파일러가 체크하도록 함.

  - 두 개 이상의 추상 메소드가 선언되어 있으면 컴파일 오류가 발생

  - 함수형 인터페이스에 꼭 붙여 줘야하는 것은 아니고, 실수를 줄이기 위해 사용

    

```java
@FunctionalInterface
public interface MyFunctionalInterface{
	public int method(int x, int y);
}

public static void main(String[] args){
	MyFunctionalInterface fi;

	fi = (x, y) -> sum(x, y);

	System.out.println(fi.method(2,5));
}

public static int sum(int x, int y){
	return x+y;
}

------
output

> 7
```



## 기본 함수형 인터페이스

- 자바에서 기본적으로 제공하는 함수형 인터페이스

>  Function`과 `Operator`는 큰 차이가 없어 보이는데 무슨 용도로 구분할까❓❓

##### `Supplier` 함수형 인터페이스류

- 매개값은 없고 리턴값만 있는 추상 메소드를 가지고 있음.

##### `Consumer` 함수형 인터페이스류

- 데이터를 소비만하고 기능 제공
- 매개값만 있고 리턴값이 없는 추상 메소드를 가지고 있음.

##### `Function` 함수형 인터페이스류

- 매개값과 리턴값 모두 있는 추상 메소드를 가지고 있다.
- 주로 `매개값`을 `리턴값`으로 `매핑(타입변환)`할 경우에 사용

##### `Operator` 함수형 인터페이스류

- 매개값과 리턴값이 모두 있는 추상 메소드를 가지고 있다.
- 주로 `매개값`을 `연산`하고 그 `결과`를 `리턴`할 경우에 사용

##### `Predicate` 함수형 인터페이스류

- 매개값을 조사해서 true 또는 false를 리턴할 때 사용

<br>

- https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html



## Closure

```java
void method()
{
	int i = 100;

	someMethod(x->x*2+i)  // x: 파라미터,  i: non-local variable
}
```

- i (== non-local variable)는 `Lambda Expression` 함수 `외부`에 존재함 

- 익명 함수가 non-local variable에 접근을 하면 이런 함수를 `Closure`라고 부름

  

### Closure라고 부르는 이유

- 람다 함수 자체 스코프를 외부로 확장해서 `Close Over` 한다해서 Closure



## Effectively Final 

- 람다식에서 사용하는 외부 로컬 변수는 `final` 특성을 갖는다.

- effectively final 변수는 final 키워드가 붙어있지 않지만 fianl 키워드를 붙인 것과 동일하게 컴파일에서 처리

- 명시적으로 final을 붙이지 않더라도, 묵시적으로 final이 적용됨.

- 아래 예제에서 `int number`는 `final`이 아니지만 수정하게 될 경우 에러가 발생함.

  - 묵시적으로 final이 적용된 것임, 이것을 `Effectively Final`이라고 부른다.

- `Effectively Final`인 이유는 **멀티 스레딩**에서 `Race Condition`이 발생할 수 있어서 금지 시킨 것

  

```java
public class ClosureExample {

    public static void main(String[] args) {
        int number = 100;  // final 이 묵시적으로 적용됨.
        
        final Runnable runnable = new Runnable() { //m 익명함수
            @Override
            public void run() {
				//number = 99;   // ⛔ 에러 발생!
                System.out.println(number);
            }
        };
        runnable.run();
        
        Runnable runnable2 = ()-> System.out.println(number);  //m 람다 표현식
        runnable2.run();

    }

}
```



> - 왜 이런 제약조건이 있는걸까?
>   - JVM 메모리 구조를 되짚어보자.
>     - 지역 변수는 **쓰레드끼리 공유가 안 된다.**
>     - JVM에서 인스턴스 변수는 힙 영역에 생성된다.
>     - 인스턴스 변수는 **쓰레드끼리 공유가 가능하다**
> - 결론적으로
>   - 지역 변수가 스택에 저장되기 때문에 람다식에서 값을 바로 참조하는 것에 제약이 있어 복사된 값을 사용하는데 이때 멀티 쓰레드 환경에서 변경이 되면 동시성에 대한 이슈를 대응하기가 힘들다.
>
> - 출처: https://sujl95.tistory.com/76



## Variable Capture

- 자바의 람다 표현식은 `외부 변수(free variable)` 를 참조 하지 않고, 외부 변수의 `value`를 캡쳐한다.
- 이러한 이유(Race Conditions ...등)로 변수의 값이 바뀌면 안되고 `final` or `Effectively final` 이어야함.



## 메소드 참조

```java
Function<String, Integer> f = (String s) -> Integer.parseInt(s);

Function<String, Integer> f = Integer::parseInt;  // 메서드 참조
```



### 생성자의 메서드 참조

```java
Supplier<MyClass> s = () -> new MyClass();  // 람다식
Supplier<MyClass> s = MyClass::new;    // 메서드 참조
```



- 하나의 메서드만 호출하는 람다식은 `클래스이름::메서드이름` 또는 `참조변수::메서드이름` 으로 바꿀 수 있다.



----



### Reference.

- https://codechacha.com/ko/java8-functional-interface/

- https://github.com/Kevin-Lee/modern-java-untold
- https://sujl95.tistory.com/76
- 이것이 자바다
- 자바의 정석