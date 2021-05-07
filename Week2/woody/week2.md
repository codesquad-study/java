

## [Week2]  자바 데이터 타입, 변수 그리고 배열 



### 학습 내용

- 프리미티브 타입 종류와 값의 범위 그리고 기본 값
- 프리미티브 타입과 레퍼런스 타입
- 리터럴
- 변수 선언 및 초기화하는 방법
- 변수의 스코프와 라이프타임
- 타입 변환, 캐스팅 그리고 타입 프로모션
- 1차 및 2차 배열 선언하기
- 타입 추론, var



### 프리미티브 타입 종류와 값의 범위 그리고 기본값

The values of the `boolean` type encode the truth values `true` and `false`(1 bit), and the default value is `false`.

The integral types:

- `byte`, 8-bit(1 byte) , default value zero

  값의 범위 : -128 ~ 127

- `short` 16-bit(2 bytes) , default value zero

  값의 범위 : -2^15 ~ 2^15 - 1

- `int`, 32-bit(4 bytes) , default value zero

  값의 범위 : -2^31 ~ 2^31 - 1

- `long`, 64-bit(8 bytes) , default value zero

  값의 범위 : -2^63 ~ 2^63 - 1

- `char, 16-bit (2 bytes) unsigned integers representing Unicode code, default value = null code point (`'\\u0000'`)

  값의 범위 : \u0000' to ‘\uffff'

The floating-point types:

- `float`, 4 bytes, default value = positive zero

  값의 범위 : single-precision 32-bit

- `double`, 8 bytes, default value = positive zero

  값의 범위 : double-precision 64-bit

  

### 프리미티브 타입과 레퍼런스 타입

1. Primitive types:

   값을 변수에 대입해서 사용한다.

   - Numeric types:
     - `byte` (8-bit 2's complement)
     - `short` (16-bit 2's complement)
     - `int` (32-bit 2's complement)
     - `long` (64-bit 2's complement)
     - `char` (16-bit unsigned Unicode)
     - `float` (32-bit IEEE 754 single precision FP)
     - `double` (64-bit IEEE 754 double precision FP)
   - `boolean` type
   - `returnAddress`: pointer to instruction

2. Reference types:

   heap 영역에 할당된 주소 값만을 가지고 있고 실제 값을 가지지 않는다.

   - 클래스
   - 배열
   - 인터페이스
   - 열거형
   - String

   

### 리터럴

자바가 다루는 실제 데이터를 말한다.

```java
int number = 20;
```

여기서는 변수에 넣는 20이라는 변하지 않는 데이터를 리터럴이라고 한다.

리터럴의 종류는 아래와 같다.

1. Integer literals
2. Floating literals
3. Boolean literals
4. Character literals
5. String literals
6. Null literal - null 값을 나타내는 하나의 리터럴로 취급



### 변수 선언 및 초기화하는 방법

멤버변수의 경우 초기화 하지 않아도 자동으로 변수 타입에 맞는 기본 값으로 초기화가 이루어 진다.

반면 지역 변수의 경우에는 반드시 초기화를 해주어야 한다.

```java
class A {
    private int a; // 기본값 0으로 자동 초기화
		private int b = 3; // 명시적으로 초기화

		public int plusFour(int c){
			int b = 4; // 지역 변수는 반드시 초기화를 시켜주어야 한다.(기본값으로 자동초기화 X)
			return c + b;
 	  }
}
```

**멤버 변수 초기화**

1. 명시적 초기화

   - 변수 선언과 동시에 초기화 값을 명시적으로 할당하는 것

2. 생성자

   - 생성자 내부에서 멤버변수에 값을 할당하는 것

3. 초기화 블럭 [[참고](https://stackoverflow.com/a/3987586)]

   - instance initialization blocks
   - static initialization blocks

   ```java
   public class Test {
   
       static int staticVariable;
       int nonStaticVariable;        
   
       // 클래스 생성시 초기화
       static {
           staticVariable = 5;
       }
   
       // 인스턴스 생성시 초기화
       {
           nonStaticVariable = 7;
       }
   }
   ```



### 변수의 스코프와 라이프 타임

변수의 스코프 = 변수를 사용할 수 있는 범위

변수의 라이프 타임 = 변수가 메모리에서 살아있는(?) 시간

**Instance Variables**

- 스코프 : static method를 제외한 클래스 전체 영역
- 라이프 타임 : 해당 인스턴스가 메모리에서 사라지기 전까지

**Class** **Variables**

- 스코프 : 클래스 전체 영역
- 라이프 타임 : 프로그램 실행 내내

**Local** **Variables**

- 스코프 : 지역 변수가 선언된 메소드, 생성자 등 블럭 내부
- 라이프 타임 : 지역변수가 선언된 블럭이 control flow 내부에 존재할 때 (해당 블럭을 벗어나기 전까지)

### 

### 타입 변환, 캐스팅 그리고 타입 프로모션

**타입 변환(Type Conversion)**

말 그대로 하나의 타입을 다른 타입으로 변환시키는 것을 타입 변환이라고 한다. 타입 변환에는 컴파일러가 자동으로 타입 변환을 수행하는 자동 타입 변환과 타입 캐스트 연산자()를 통해 타입 변환을 강제하는 강제 타입 변환, 두 가지 종류가 있다.



**자동 타입 변환(Type Promotion = Widening or Automatic Type Conversion = Implicit type conversion )**

아래와 같은 순서로 자동 형변환이 이루어진다. 사이즈가 작은 타입이 큰 타입으로 변환될 경우 발생하기 때문에 데이터 손실이 발생하지 않는다.

byte ➡️ short ➡️ int ➡️ long ➡️ float ➡️ double 



**강제 타입 변환(Explicit type casting or narrowing)**

반대로 사이즈가 큰 타입에서 작은 타입으로 변환되어야 할 경우 타입 캐스트 연산자를 통해 강제로 형변환을 해주기도 한다. 숫자형 데이터 타입을 타입 캐스팅해줄 경우 데이터 손실이 발생할 수도 있다.

byte ⬅️ short ⬅️ int ⬅️ long ⬅️ float ⬅️ double 

(+) 모든 Numeric 기본형 타입들은 호환이 가능하지만 Non-numeric에서 Numeric 기본형으로 혹은 반대로의 전환은 불가능하다.



**클래스 간의 타입 캐스팅**

상속관계의 클래스타입(Non-numeric)끼리는 타입 캐스팅이 가능하다. Numeric 기본형간의 형변환과 달리 상하관계가 존재한다.

1. 업캐스팅(up casting) : 자식 -> 부모

   명시적인 타입 캐스팅이 따로 필요없이 자식 클래스가 부모 클래스로 형변환이 가능하다.

   ```java
   // Upcasting
   Parent p = new Child();
   ```

2. 다운 캐스팅(down casting) 부모 -> 자식

   반대로 부모 클래스로 선언되어있던 변수를 다시 자식 클래스로 변환해주기 위해서는 명시적으로 타입 캐스팅을 해주어야 한다.(이때, 변수가 참조하고 있는 실제 데이터 타입은 Child 타입 혹은 이보다 더 하위의 클래스 타입이어야 한다)

```java
// Trying to Downcasting Implicitly
// Child c = new Parent(); - > compile time error
  
// Downcasting Explicitly
Child c = (Child)p;
```



### 1차 및 2차 배열 선언하기

배열은 하나의 변수로 동일한 타입의 데이터를 연속된 메모리 공간에 저장하기 위한 자료구조다.

- 캐시 지역성(cache locality)을 높일 수 있는 수단이 된다. 특히 for문으로 배열을 순회할 때, 높은 캐시 hit을 보인다. (* 캐시 지역성 = 메모리 상 인접한 데이터의 재사용률이 높음)
- 배열 선언 시 변수에 첫 원소에 대한 주소값을 저장하기 때문에, index를 사용하여 빠르게 접근이 가능하다.
- 초기화 없이 배열의 크기만 할당했을 경우, primitive type의 경우 초기값으로 채워지게 되고 reference type의 경우 null로 채워진다.

1. 1차 배열

```java
int[] arr; // 초기화 없이 변수만 선언
int[] arr = new int[3]; // 선언과 함께 배열의 크기까지 할당, 초기값으로 채워짐
int[] arr = {1,2,3,4,5}; // 선언 + 크기 지정 + 초기화
```

1. 2차 배열

```java
int[][] arr;
int[][] arr = new int[4][3];
int[][] arr = { {1,2},{3,4},{5,6}};
```



### 타입 추론, var

타입 추론이란 데이터 타입을 명시하지 않더라도, 컴파일 단계에서 컴파일러가 타입을 유추하는 기능을 말한다.

Java 10에 var이라는 키워드가 추가되면서 어떤 데이터 타입이든 var 키워드로 선언이 가능해졌다. (단, 멤버 변수나 정적 변수에는 사용이 불가능하다, 반드시 초기화를 해주어야 한다)



## 참조

프리미티브 타입 종류와 값의 범위 그리고 기본값

https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html#jvms-2.3

프리미티브 타입과 레퍼런스 타입

https://dzone.com/articles/introduction-to-java-bytecode

레퍼런스 타입

https://jinbroing.tistory.com/3

변수의 초기화

https://doublesprogramming.tistory.com/73

변수의 스코프와 라이프 타임

https://www.learningjournal.guru/article/programming-in-java/scope-and-lifetime-of-a-variable/

타입 변환

http://www.tcpschool.com/java/java_datatype_typeConversion

https://www.geeksforgeeks.org/type-conversion-java-examples/#:~:text=Type promotion in Expressions&text=Some conditions for type promotion,long%2C float or double respectively.

https://www.examtray.com/java/last-minute-java-type-casting-or-type-conversion-or-type-promotions-tutorial

클래스 간의 타입 캐스팅

https://kamang-it.tistory.com/entry/Java-19타입형-변환Type-Casting

https://www.geeksforgeeks.org/upcasting-vs-downcasting-in-java/

배열

https://seducinghyeok.tistory.com/18

타입 추론

https://www.geeksforgeeks.org/var-keyword-in-java/

우와...

https://catsbi.oopy.io/6541026f-1e19-4117-8fef-aea145e4fc1b