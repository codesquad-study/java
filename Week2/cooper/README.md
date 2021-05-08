## 1. 프리미티브 타입 종류와 값의 범위 그리고 기본 값

자바에서 기본 데이터 타입이란 **정수, 실수, 문자, 논리 리터럴을 직접 저장하는 타입**을 말한다.

정수 타입에는 byte, char, short, int, long이 있고, 실수 타입에는 float, double이 있으며, 논리 타입에는 boolean이 존재한다.

| 값의 종류 | 기본 타입 | 메모리 사용 크기 | 저장하는 값의 범위                                       |
| --------- | --------- | ---------------- | -------------------------------------------------------- |
| 정수      | byte      | 1byte = 8bit     | -2^7 ~ (2^7 - 1)                                         |
|           | char      | 2byte = 16bit    | 0 ~ (2^16 -1)                                            |
|           | short     | 2byte = 16bit    | -2^15 ~(2^15 - 1)                                        |
|           | int       | 4byte = 32bit    | -2^31 ~ (2^31 - 1)                                       |
| 실수      | long      | 8byte = 64bit    | -2^63 ~ (2^63 - 1)                                       |
|           | float     | 4byte = 32bit    | 1.4E-45 ~ 3.4028235E38                                   |
|           | double    | 8byte = 64bit    | ±(4.94065645841246544e-324d ~ 1.79769313486231570e+308d) |
| 논리      | boolean   | 1byte = 8bit     | true, false                                              |

**비트(bit)**는 **메모리의 최소 기억 단위**이며, 각 비트는 0 또는 1을 저장합니다. 그리고 **8개의 비트**가 합쳐 **바이트(byte)** 단위로 묶습니다. 기본 타입은 바이트 크기를 기준으로 저장하기 때문에 **바이트 크기에 따라 표현하는 값의 범위 또한 비례**합니다. 또한 바이트 크기에 따라 값의 범위를 알 수도 있습니다. 정수 타입의 경우 4byte 단위로 저장하고 보수를 가지기 때문에 값의 범위는 -2^(31) ~ 2^(31)-1입니다.

<br><br>

## 2. primitive type과 reference type

자바 타입은 크게 primitive type과 reference type으로 구분합니다. Reference type은 primitive type을 제외한 모든 타입들이 해당합니다. Reference type은 primitive type와 다르게 변수 값을 저장하는 것이 아니라 힙영역에 존재하는 객체의 주소 번지를 저장합니다. 

### Reference type 종류

- class types : 클래스의 객체를 가리킨다.
- array types : 배열을 가리킨다.
- Interface types : 인터페이스를 구현하는 클래스의 객체

<br><br>

## 3. 리터럴

 고정된 값을 소스코드에서 표현하는 것을 뜻한다. 리터럴은 별도의 연산없이 표현한다.

### 정수 리터럴

정수 리터럴(byte, int, long, short)는 10진수, 16진수, 8진수, 2지수 체계로 표현할 수 있다.

```java
int decimal = 100; // 100(10) = 100
int octal = 0133; // 133(8) = 91
int hexa = 0x64; // 64(16) = 100
int binary = 0b0101; //101(2) = 5
```

<br>

### 실수 리터럴

실수 리터럴(float, double)에 해당하는 값은 주로 소수점이나 지수를 표현할 때 사용한다.

- 실수 타입 리터럴은 double 타입으로 컴파일 된다.

```code
double f = 0.1234;
double g = 1234E-4; // (1234 * 10^(-4) = 0.1234)
```

- 숫자 뒤에 f혹은 d를 명시적으로 붙이기도 한다. (float의 경우, 명시적으로 표시하지만, double은 생략 가능하다.)

```java
float h = 0.1234f;
double i = 0.1234d; //0.1234 표현 가능
```

<br>

### 문자 및 문자열 리터럴

문자 리터럴의 경우, single quotation]('')를 이용해서 표현할 수 있고,

```java
char a = 'H';
char b = "한";
```

문자열 리터럴의 경우, double quotation("")을 이용해서 표현하며, 모든 유니코드 무자를 포함할 수 있다.

```java
String a = "Hello World";
String b = "code squad";

char c = '\u0001';
String d = "\u0001";
```

<br><br>

### 불린 리터럴

불린 리터럴은 논리값을 나타내는 표현입니다. ```true```, ```false```로 나타낼 수 있습니다.

<br><br>

## 4. 변수 선언 및 초기화하는 방법

자바의 변수는 다음과 같은 종류로 구분할 수 있습니다.

1. 인스턴스 변수 : 클래스 선언 시, ```static``` 키워드없이 선언된 필드로 가각 인스턴스의 고유 값들을 저장하기 때문에 인스턴스 변수라고 부릅니다.

2.  클래스 변수 : 클래스 선언 시, ```static``` 키워드를 함께 선언한 필드입니다. 클래스가 인스턴스화된 횟수와 관계없이 오직 하나만 존재합니다.

   그리고 해당 필드는 해당 클래스의 모든 인스턴스에 적용이 가능하기 때문에 정적 정적 필드라고도 말합니다.

3. 지역 변수 : 메서드 내부에 임시로 저장하는 변수를 일컫습니다.

4. 매개 변수 : 메서드 선언 시, 메서드의 인자로 전달하는 변수를 말합니다.

   <br>

```java
class VariableInitialization {
  int instanceVariable; //인스턴스 변수
  // 인스턴스 변수는 default value로 초기화된다.
  // Linking(prepare 단계)에서 값을 설정한다.
  static int classVariable; // 클래스 변수 : VariableInitialization class 인스턴스들은 모두 사용가능하다!
  
  int initializationOfInstanceVariable = 10;		// 인스턴스 변수 초기화
  static int initializationOfClassVariable = 10; // 클래스 변수 초기화
  
  
  void methodName(int parameterVariable) { //매개변수
    int localVariable; //지역변수 : 메서드의 내부에 선언된 변수
    // 지역변수는 default value로 초기화되지 않는다. -> 개발자가 직접 설정해야 한다.
  }
}
```

<br><br>

## 5.변수의 스코프와 라이프타임

- 스코프(scope) : 변수가 유효한 범위

  | 변수 타입         | 스코프                                             | 라이프타임                                   |
  | ----------------- | -------------------------------------------------- | -------------------------------------------- |
  | Instance Variable | (static 블록과 static 메서드를 제외한) 클래스 전체 | 객체 생성되고 메모리 할당이 유지될때 까지    |
  | Class Variable    | 클래스 전체                                        | 클래스가 초기화되고 프로그래밍이 끝날 때까지 |
  | Local Variable    | 변수가 선언된 블록 내부                            | 변수 선언 이후부터 블록을 벗어날 때까지      |

<br><br>

## 6. 타입 변환, 캐스팅 그리고 타입 프로모션

 타입 변환은 하나의 타입을 다른 타입으로 바꾸는 것입니다. 타입 변환은 크게 묵시적 타입 변환(자동 타입 변환)과 명시적 타입 변환(강제 타입 변환)으로 구분지을 수 있습니다.

### 1. 묵시적 타입 변환(자동 타입 변환) - Promotion

```java
double num1 = 10; //결과 10.0
//int num2 = 3.14 //컴파일 에러
double num2 = 7.0f + 3.14; //결과 10.14

int number = 2;
double result = 1.0 + number;
```

묵시적 타입 변환(Implicit Conversion)의 경우, 컴파일러가 자동적으로 수행하는 타입 변환을 말합니다. 

하지만 아래와 같은 조건이 만족할 경우 자동 형변환이 가능합니다.

- 두 데이터 타입이 호환 가능해야 한다.
- 더 작은 데티어 타입의 값이 더 큰 범위의 타입에 할당해야한다.

<br>

 숫자 타입의 경우, 바이트 크기가 작은 타입에서 큰 타입으로 타입이 변환합니다.

```
<<자동 형변환 되는 순서>>
byte ➡️ short ➡️ int ➡️ long ➡️ float ➡️ double
         char ↗️
```



### 2. 명시적 타입 변환(강제 타입 변환) - Casting

 명시적 타입 변환이란 사용자가 타입 캐스트 연산자(())를 사용하여 강제적으로 수행하는 타입 변환을 가리킵니다.

```java
int num1 = 1;
int num2 = 4;

double result = num1 / num2; // 결과 : 0
double result2 = num1 / num2; //결과 : 0.25
```



<br><br>



## 7. 1차 및 2차 배열 선언하기

### [1] 1차원 배열 선언 및 초기화

```java
//1. 1차원 배열 선언
int[] intArray1;
int []intArray2;
int intArray3[];

//2. 1차원 배열 선언
int[] intArray1 = new int[10];

//3. 배열의 인덱스 초기화
intArray1[0] = 100;
System.out.println(intArray1[0]); // 100
```



### [2] 2차원 배열 선언 및 초기화

```java
class SecondaryDimensionalArrayTest {
  publlic static void main(String[] args) {
    int rowSize = 3;
    int columnSize = 5;
    
    int[][] arr = new int[rowSize][columnSize];
    //int arr[][] = new int[rowSize][columnSize];
    
    for (int row = 0; row < rowSize; row++) {
      for(int column = 0; column < columnSize; column++) {
        System.out.printf("[%d, %d] ", row, col);
      }
      	System.out.pritln();
    }
}
```

**결과**

```
[0, 0] [0, 1] [0, 2] [0, 3] [0, 4] 
[1, 0] [1, 1] [1, 2] [1, 3] [1, 4] 
[2, 0] [2, 1] [2, 2] [2, 3] [2, 4] 
```





### Reference

- https://kingpodo.tistory.com/54
- 자바 리터럴 : https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
- 오라클 변수 내용 : https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html
- Variable scope & life cycle : https://www.learningjournal.guru/article/programming-in-java/scope-and-lifetime-of-a-variable/