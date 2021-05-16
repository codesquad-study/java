# 3주차 과제: 연산자

열: 2021년 5월 14일 오전 3:17
태그: 백기선스터디

# **목표**

자바가 제공하는 다양한 연산자를 학습하세요.

---

# **학습할 것**

## 산술 연산자

- 사칙 연산자 + - * /

```java
byte a = 10;
byte b = 20;

byte c = a + b;   // 컴파일 에러, 명시적으로 형변환 필요 byte c = (byte)(a+b);
```

- a와 b는 모두 `int형`보다 작은 `byte형`이기 때문에 연산자 `+` 는 이 두 개의 피연산자들의 자료형을 `int형` 으로 변환한 다음 연산(덧셈)을 수행한다.

- 나머지 연산자 %
    - 짝수, 홀수, 소수찾기, 배수 검사등에 주로 사용됨.

## 비트 연산자

- `&`, `|` , `^` , `~`, `<<` , `>>`
- 비트 연산자는 피연산자를 비트단위로 논리 연산한다.
- 피연산자는 정수(문자포함)만 허용된다.

- `^ (XOR연산자)`
    - 피연산자의 값이 서로 다를 때만 1
- `~ (비트 전환 연산자)`  == `1의 보수 연산자`
    - 피연산자를 이진수로 표현했을 때, 0은 1로, 1은 0으로 변환
    - 부호있는 타입의 피연산자는 부호가 변경된다, 즉 `1의 보수`를 얻는다.
        - `TMI`
            - 음수 = 1의 보수 + 1
    - 논리부정연산자 `!` 과 유사

 

- `<<, >> (쉬프트 연산자)`
    - 이진수로 표현한 피연산자의 각 자리를 이동시키는 연산자
    - 왼쪽 자리이동으로 자료형의 범위를 벗어난 값들은 버려지고, 빈자리는 0으로 채워짐.
    - $x<<n == x * 2^n$
    - $x >> n == x / 2^n$
    - $x:10진수$

> 입력값이 5였다고 가정하면 이 값은 이진수로 `0101`인데 이 값을 10진수로 바꾸는 공식은 다음과 같다.
$5 = 0*2^3+1*2^2+0*2^1+0*2^0$

비트를 왼쪽으로 한칸 이동시키면 모든 자리수의 지수가 1씩 오르기 때문에 2배

쉬프트결과: `1010`

* 단, 자료형식의 한계로 값이 잘릴 수 있음.

### 쉬프트 연산자를 제공하는 이유

- 사칙연산보다 연산속도가 빠르다.

### 쉬프트 연산자 주의할 점

- 자료형의 크기를 벗어나면 버려진다.
- 부호가 있는 변수에 쉬프트 연산( `나눗셈 >>` )을 하면 연산특성이 달라진다.
    - 음수를 다룰 경우에는 사칙연산을 사용하는 것이 좋음.

## 관계 연산자

- `==`, `≤` , `≥` , `≠`
- 두 피연산자의 관계를 따져서 진릿값(참, 거짓)을 얻는 연산

## 논리 연산자

- `&&` , `||`, `!`

- 특징
    - 효율적인 연산(Short Circuit Evaluation)
        - OR 연산의 경우, 두 피연산자 중 어느 한 쪽만 `참` 이면, 우측 피연산자의 값은 평가 X
        - AND 연산도 마찬가지로, 한쪽이 `거짓`이라면 우측 피연산자 평가 X
        - 즉, 피연산자의 위치에 따라 연산 속도가 달라질 수 있음.

## instanceOf

- 검사한 타입으로 형변환이 가능한지 검사
- 참조변수가 참조하고 있는 인스턴스의 `실제 타입` 을 검사
    - 형식: `[참조변수] instanceOf [타입(클래스명]`
- 조상타입의 참조변수로 자손타입의 인스턴스를 참조할 수 있기 때문에, 참조 변수의 타입과 인스턴스의 타입이 항상 일치하지 않는다.

## assignment(=) operator

- 변수에 값을 할당할 때 이 연산자를 사용할 수 있으며 메모리에 값을 저장하거나 할당
- Primitive 타입은 값 자체를 저장
- Reference 타입은 참조하는 주소를 저장

- 복합 대입 연산자
    - 대입 연산자는 다른 연산자와 결합할 수 있음 `ex) i += 3;`
    - 주의할 점
        - $i　*= 10+j 　　 == 　　i = i *(10+j);$

## 화살표(->) 연산자

> Java 8에서 새로 등장한 feature, lambda function.
화살표 연산자는 lambda 함수를 정의하기 위해 사용된다.

`(Lambda parameters) -> Lambda Body`

- Lambda Parameters : 람다 함수로 넘겨지는 파라미터
- Lambda Body : a code block or an expression

```java
@FunctionalInterface
public interface MyFuntionalInterface{
	public void method();
}

MyFunctionalInterface fi = () -> System.out.println("method call2");

fi.method();

------
output

> method call1
```

```java
# 람다식

Runnable runnable = ()->{...};  

# 익명 클래스 

Runnable runnable = new Runnable(){

		public void run() {...}	 // 함수적 인터페이스 (Runnable)은 메소드를 한 개 가지고 있음.

}
```

## 3항 연산자

- `조건식 ? 식1 : 식2`
- 여러 번 중첩하면 코드가 간략해지긴 하지만, 가독성이 떨어지므로 유의

## 연산자 우선 순위

- 단항 ← 산술 ← 비교 ← 논리 ← 삼항 ← 대입

## (optional) Java 13. switch 연산자

- 변경된 점
    - break → yeild
    - 단 하나의 조건식 → 멀티 조건식
    - 화살표(→) 연산자 추가로 break 생략 가능
    - 리턴값으로 스위치문 사용 가능

    ```java
    public class Java13SwitchCaseBreak {
     
        public static void main(String[] args) {
            getGrade('A');
            getGrade('C');
            getGrade('D');
            getGrade('E');
            getGrade('X');
        }
     
        public static void getGrade(char grade) {
            System.out.print(switch (grade) {
                case 'A': yield "Excellent";
                case 'B': yield "Good";
                case 'C': yield "Standard";
                case 'D': yield "Low";
                case 'E': yield "Very Low";
                default: yield "Invalid";
            });
     
            System.out.println(getResult(grade));
        }
     
        public static String getResult(char grade) {
            return switch (grade) {
                case 'A', 'B', 'C' -> "::Success";
                case 'D', 'E' -> "::Fail";
                default -> "::No result";
            };
        }
    }
    ```

    ---

    ## 참고

    - 자바의 정석  저자 : 남궁성

    [joowankim/whiteship-live-study](https://github.com/joowankim/whiteship-live-study/blob/master/3rd-week/3rd-week.md)

    [Java 13 - Switch Expressions (JEP 354) | Dariawan](https://www.dariawan.com/tutorials/java/java-13-switch-expressions-jep-354/)