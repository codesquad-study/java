> ### 목표
자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.


## 프리미티브 타입 종류와 값의 범위 그리고 기본 값
![](https://images.velog.io/images/janeljs/post/8820fd0a-6311-4c6e-9065-9cae0bc86e54/image.png)
### 기본값
- byte, short, int: 0
- long: 0L
- float: 0.0f
- double: 0.0d
  - float과 double은 정밀한 값을 요구하는 곳에는 사용 X
- boolean: false
- char:  `u0000` (null 문자)

## 프리미티브 타입과 레퍼런스 타입
- primitive type
  - 값을 할당할 때 변수의 주소값에 값이 그 자체로 저장되는 데이터 타입
  - Runtime Data Area 중 Stack 영역에 값이 저장된다.
  - null 할당 불가
  
  
- Reference Type
  - primitive type을 제외한 모든 타입
  - Runtime Data Area 중 Heap 영역에 존재하는 객체의 주소가 저장된다.
  - null 할당 가능
  - class type, array type, interface type(인터페이스를 구현하는 클래스의 객체 참조)이 있음

### 리터럴
- 변수나 상수에 저장되는 값 그 자체, 즉 고정된 값(연산이 필요없는 값)을 소스 코드에 표현하는 것이다.
- 컴파일 타임에 프로그램 안에 정의된다.




## 변수 선언 및 초기화하는 방법
```
int number;
```
- 변수 선언 = 저장 공간 확보
- number이라는 이름의 4byte의 저장공간을 확보했다는 의미

```
number = 316;
```
- 초기화 = 저장공간에 값을 저장하는 것
- 모든 변수는 선언 후에 초기화를 해주어야 컴파일 시 오류가 나지 않음

## 변수의 스코프와 라이프타임
###  변수의 스코프
- 변수를 사용할 수 있는 영역의 범위

### 변수의 라이프 타임
- 변수가 메모리에 언제까지 살아있는지를 의미
- 클래스 변수
   - 클래스 영역에 선언
   - 클래스가 메모리에 올라갈 때 생성됨
   - 한 클래스의 모든 인스턴스들이 공통된 값을 유지해야 하는 속성인 경우 클래스 변수로 선언해야 한다. 
- 인스턴스 변수
  - 클래스 영역에 선언
  - static 블록을 제외한 부분 (쿠퍼짱)
  - 인스턴스 생성 시 생성됨
 >  illustrating the default initialization of npoints(static variable), which occurs when the class Point is prepared (§12.3.2), and the default initialization of x, y, and root, which occurs when a new Point is instantiated. See §12 for a full description of all aspects of loading, linking, and initialization of classes and interfaces, plus a description of the instantiation of classes to make new class instances. (Woody ZZANG)
- 지역 변수
  - 메서드, 생성자, 초기화 블럭 내부에 선언
  - 변수가 선언되었을 때 생성됨
  - 지역 변수가 선언된 블럭({}) 내에서만 사용할 수 있고, 블럭을 벗어나면 소멸된다. 

### 추가로 공부해야 할 것
- pass by value
- pass by address/reference

## 타입 변환, 캐스팅 그리고 타입 프로모션
### 자동 형변환
- byte -> short -> int -> long -> float -> double
- 더 작은 데이터 타입의 값을 더 큰 범위의 타입에 할당할 때만 동작

### 캐스팅
- `(target-type) <value>or<variable>`와 같은 형태로 변환 가능


### 타입 프로모션
- 피연산자의 범위를 초과할 것에 대비하여 자동으로 값이 승격되는 것
- byte, short, char &rarr; int
- long, float, double &rarr; long, float, double

## 1차 및 2차 배열 선언하기
### 선언
- 1차원 배열
```java
String[] weeks = new String[7];
weeks[0] = "Monday";
```
- 2차원 배열
```java
String[][] names = {
    { "Mr.", "Mrs.", "Ms." },
    { "Smith", "Jones" }
};

String[][] names2 = new String[10][];

String[][] names3 = new String[10][10];
```

### 가변 배열(dynamic array) (쿠퍼짱, 비추천)
자바에서는 2차원 배열을 생성할 때 열의 길이를 명시하지 않음으로써, 행마다 다른 길이의 배열을 요소로 저장할 수 있습니다.
이렇게 행마다 다른 길이의 배열을 저장할 수 있는 배열을 가변 배열(dynamic array)이라고 합니다.
```java
int[][] arr = new int[3][];
arr[0] = new int[2];
arr[1] = new int[4];
arr[2] = new int[1];
```

### 타입 추론, var
- 타입추론
   - 데이터 타입을 소스코드에 명시하지 않아도, 컴파일 단계에서 컴파일러가 타입을 유추해 정해주는 것
   - Generic이나 lambda에 사용됨
- var
  - 자바 10 에서 var 이라는 Local Variable Type-Inference가 추가됨
