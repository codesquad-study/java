# 자바 11주차 - 열거형(스터디 할래)

## **목표**
- 자바의 열거형(Enum)에 대한 학습
- enum 정의하는 방법
- java.lang.Enum
- EnumSet

<br><hr><br>

## 목차

- Enum개요와 필요성
- 열거형의 prototype, declaration, usage
- 열거형에 멤버 추가하기
- 열거형의 이해
- java.lang.Enum 과 EnumSet

<br><hr><br>

## Enum개요와 필요성
- 열거형(Enum) : 한정되고 불연속적인 값만을 갖는 상수의 집합
  - 예시로 방향(동, 서, 남, 북), 국가(한국, 미국 등등.. 약 200개국), 금융기관(국민은행, 기업은행 등등.. 20개 남짓)
  - 상수라서 연산이 불가능하고
  - 집합이라서 중복이 허용되지 않는다
- Enum을 사용하는 이유는
  - 코드가 단순하고 가독성이 좋습니다 : 과거에 문자열이나 정수로 ENUM을 대체할때보다 훨씬
  - 자바의 ENUM은 버그의 가능성을 줄여줍니다 : 
  - enum선언(prototype)시에 Enum키워드를 사용하므로 구현의도가 명확하게 표현 가능
  - 인스턴스 생성과 상속을 방지합니다 
    - 자바 Enum 의 생성자는 Private이라서 인스턴스를 생성할 수 없습니다
    - 자바 Enum은 상속이 불기능합니다
### 예제코드

```java

enum Gogi {PORK, BEEF, CHICKEN, TURKEY}

public class Launcher111 {
    public static void main(String[] args) {

        Gogi pok = Gogi.PORK; // 가장 흔하게 쓰이는 방식 Enum 에 .(dot)연산으로 열거형의 원소에 접근합니다

        Gogi kfc = Gogi.valueOf("CHICKEN"); // 이렇게 문자열에서 Enum으로 변경하며 대입도 가능합니다 1

        Gogi sirloin = Enum.valueOf(Gogi.class, "BEEF"); // 이렇게 문자열에서 Enum으로 변경하며 대입도 가능합니다2

        Gogi samgyeobsal = Gogi.PORK; // 예를들어 "삼겹살"이라는 변수이름에 고기 이넘을 넣어줄 수 있습니다.


        //들어가 있는 값 확인
        System.out.println("inserted : pok = " + pok);
        System.out.println("inserted : kfc = " + kfc);
        System.out.println("inserted : Sirloin = " + sirloin);
        System.out.println("");

        //이넘끼리 동등비교도 됩니다.
        System.out.println("compare : pok == kfc? : " + (pok == kfc));
        System.out.println("compare : pok == Sirloin? : " + (pok == sirloin));
        System.out.println("compare : pok == samgyeobsal? : " + (pok == samgyeobsal));
        System.out.println("compare : Sirloin == beef? : " + (sirloin == Gogi.BEEF));
        System.out.println("");

        //equals 메서드 사용도 가능합니다
        System.out.println("pok equals Sirloin : " + pok.equals(sirloin));
        System.out.println("pok equals samgyeobsal : " + pok.equals(samgyeobsal));

        //compareTo 메서드 사용도 가능합니다.
        System.out.println("pok comp kfc : " + (pok.compareTo(kfc)));
        System.out.println("pok.comp Gogi.Beef : " + (pok.compareTo(Gogi.BEEF)));

        //열겨형으로 반복문을 돌리면 이렇게 됩니다.
        System.out.println("\n for loop Enum.values() method");
        Gogi[] gogis = Gogi.values();

        for (Gogi gogi : gogis) {
            System.out.printf("%s = %d%n", gogi.name(), gogi.ordinal());
        }

        System.out.println( Gogi.BEEF.name()); //BEEF 출력됨
    }
}
}
```

### 정적타입 vs 동적타입 구분 기준
- 변수의 Type이 결정되는 시점에 따라서 두가지로 나눠집니다 
  - 컴파일타임에 컴파일러에 의해 변수의 타입이 결정되면 : Statically typed (정적타입)
    : C/C++ , Java, TS(살짝 다르지만..)
    - 컴파일단계에서 대다수 변수들의 Type이 결정되어 있습니다.
    - 당연하게도 소스코드레벨에서부터 Type이 결정되어 있는게 대부분입니다.
  - 런타임(코드 실행중)에 실행환경에 의해 타입이 결정되면 : Dayamically typed, 동적타입
    : Python, JS
    - 컴파일단계에서는 변수의 Type이 대부분 결정되지 않은 상태입니다.
- 언어는 저마다 변수의 Type이 결정되는 "시기"에 따라서 동적이냐 정적이나로 구별할 수 있습니다.
- 아무리 Java와 같이 Statically typed(정적타입) 프로그래밍 언어라도 컴파일타임에 결정할 수 없는 경우가 종종 있는데, 대표적으로 "타입 변환" 동작코드에서는 런타임에 변수의 타입이 결정된다.
  ```java
    Object jackson = new Object();
    Person person = (Person)jackson
  ```
  - 그래서 결국 Statically typed 언어를 사용할지라도 일정부분은 불가피하게 Dayamically typed하는 부분이 발생할수 있고 **이 부분에서 논리적 에러가 발생할 가능성이 존재합니다 !**
- 소스코드에는 Type이 결정되지 않고 컴파일타임에 결정되는 문법적인 요소가 있는데 이를 Generic(자바 제네릭) 이라고 합니다.
  - 제네릭 링크 : [추가예정]

<br><hr><br>

## Enum의 prototype, declaration, usage
- 열거형을 하나씩 분리해서 살펴보면 
  - prototype : 열거형 선언 
  - declaration : 열거형 변수 선언 + 값 대입

### prototype
- 아래처럼 열거타입을 선언할 수 있습니다
- 열거형 Enum의 값인 열거상수들의 이름은 관례적으로 모두 대문자로 작성
```java
enum Gogi { PORK, BEEF, CHICKEN, TURKEY }
```
- 결합단어인 경우 관례적으로 밑줄(_)로 연결해 사용
```java
enum Direction {
  SOUTH, WEST, EAST, NORTH,
  NORTH_EAST, NORTH_WEST, 
  SOUTH_EAST, SOUTH_WEST
}
```
- 접근제한자 public 이 enum에 쓰이는 경우 별도의 파일로 생성해줘야합니다
  - 아래 코드의 경우 Season.Java 라는 파일명을 사용해야 하고, 파일이름이 다른경우 컴파일 에러가 발생
```java
public enum Season { WINTER, SPRING, SUMMER, FALL }
```

### declaration
- 열거형 변수를 선언하고, 열거 상수값을 저장할 수 있습니다
- 열거형 변수도 레퍼런스 타입이므로, null값을 저장할 수 있습니다.
```java
Gogi pok = Gogi.PORK;
Gogi kfc = Gogi.valueOf("CHICKEN");
Gogi Sirloin = Enum.valueOf(Gogi.class, "BEEF");
Gogi samgyeobsal = Gogi.PORK;
Gogi vegiterian = null;
```

- 추가로  for문도 돌릴수 있는데

```java
Gogi[] gogis = Gogi.values();

for (Gogi gogi : gogis) {
  System.out.printf("%s = %d%n", gogi.name(), gogi.ordinal());
}
```

<br><hr><br>

## Enum의 내부 구조
- Enum상수는 열거 객체를 참조하는데요
  - 열거 객체는 Heap 영역에 생성된다
  -  변수는 메소드 영역에 포인터로 존재하고
  - 메소드 영역에 포인터형식의 열거형 상수(혹은 변수)는 Heap영역의 열거객체를 참조한다 (중요!)
  - ``바이트``코드로 확인
  ```java
  Direction myDirection = Direction.EAST;
  Integer integer = 112;
  ```
  - 소스코드, 코드상에서는 비슷해보이는 레퍼런스 타입 변수이지만
  ```java
  L0
    LINENUMBER 15 L0
    GETSTATIC chp11_enum/ex2/Direction.EAST : Lchp11_enum/ex2/Direction;
    ASTORE 1
  L1
    LINENUMBER 16 L1
    BIPUSH 112
    INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
    ASTORE 2
  ```
  - ``바이트 코드`` 상에서는 완전히 다른데
    - enum 변수는 GETSTATIC, 즉 heap공간에 위치한 static한 인스턴스를 가르키고 있고
    - Integer 변수는 BIPUSH, 그냥 숫자값을 대입함을 알 수 있다!
- Enum 클래스의 메서드
  - Enum변수(열거형 변수)는 인스턴스 내부에 Enum상수의 문자열을 내부 데이터로 갖는다
    - name() : 열거형 상수의 이름을 반환하는 메서드
  - Enum타입은 컴파일시에 java.lang.Enum 클래스를 자동상속
  - java.lang.Enum 클래스의 메서드를 사용할 수 있다!
  ```java
  public enum Season { WINTER, SPRING, SUMMER, FALL }
  System.out.println( Season.WINTER.name()); //WINTER 출력됨
  ```
    - name() 메서드 말고도 다양한 메서드들이 존재하므로 아래에서 다루겠습니다

<br><hr><br>

## java.lang.Enum 클래스 

- 모든 열거형의 조상(최상위) 클래스이며, 자바에서 Enum 을 사용한다면 컴파일타임에 Enum의 prototype부분이 java.lang.Enum 을 자동으로 상속
  - JDK 를 살펴보면
  ```java
  public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {
  ```
  - abstract class 가 사용되고, Comparable과 Serializable 인터페이스를 구현합니다
  ```java
  protected Enum(String name, int ordinal) {
      this.name = name;
      this.ordinal = ordinal;
  }
  ```

### Enum의 인스턴스화(실체화) new 키워드로 사용 불가능

- Enum 클래스의 생성자의 접근제한이 protected 인데요, new 키워드를 사용해서 외부에서 객체를 생성할 수 없습니다
  - 위 코드가 Enum 클래스 유일한 생성자이며 프로그래머는 이 생성자를 직접 호출할 수 없고, 열거형 선언(enum 키워드 사용)에 대한 응답으로 컴파일러에서 내보낸 코드에 사용됨
- 생성자를 통해서 Enum상수에 다른 값을 넣는 행위를 윈천 차단(동적으로 할당할 수 없도록 강제)
  - 실제로 ``바이트``코드 상에서 ```GETSTATIC``` 이라는 JVM명령어가 사용됨


- Enum 클래스가 제공하는 메서드 리스트(JDK에서 확인 가능)

### 원조클래스가 제공하는 메서드 설명

  - ```finalize()``` : 해당 Enum클래스가 final 메서드를 가질 수 없게 합니다.
  - ```getDeclaring()``` : 열거형 상수의 열거형 타입에 해당하는 Class 객체를 반환합니다.
  - ```name()``` : 열거형 상수의 이름을 반환합니다.
  - ```ordinal()``` : 이 열거형 상수가 정의된 순를 반환합니다.
    ```java
    public enum Season { WINTER, SPRING, SUMMER, FALL }
    int ordiZero = Season.WINTER.ordinal();
    int ordiOne = Season.SPRING.ordinal();
    int ordiTwo = Season.SUMMER.ordinal();
    int ordiThree = Season.FALL.ordinal();
    ```
      - ```ordinal``` 메서드로 로직을 만드는게 엄청난 ***```안티패턴```*** (아래에서 설명)
  - ```values()``` : 열거형의 모든 상수를 배열에 담아 반환합니다.
	  - Direction[] arr = Direction.values();
  - ```valueOf(String name)``` : 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해줍니다.
	  - Direction.WEST == Direction.valueOf("WEST"); // true 반환
    - Enum클래스는 VO 같은 개념이라 객체 중복생성으로 인한 동등비교 문제가 없다
  - Object class 상속
    - compareTo(E o) : ordinal을 기준으로 지정된 객체와 비교합니다.크면 양수 작으면 음수 같으면 0을 반환
      - 순서는 ordinal() 메서드의 값을 기준으로 순서가 비교되며, Sort도 가능하다
    - eqauls(Object other) : 지정된 객체(other)가 열거형 상수와 같으면 true를 반환합니다.
    - toString() : 열거형 상수의 이름을 반환
    - hashCode() : 열거형 상수의 해시 코드를 반환합니다.


## Enum에 인스턴스 변수를 추가해서 유용하게 사용하는법

### 인스턴스 변수를 추가하기
- Enum으로 인프런 백기선님의 강의Enum, 가격, 한글설명을 포함한다면 아래와 같은 코드 작성이 가능
```java

public class Launcher113 {

  public static void main(String[] args) {

    // 간단한 이넘 필드변수의 사용 예시
    WhiteshipLectureList myWish = WhiteshipLectureList.REST_API;
    System.out.printf("제가 원하는 강의 코드는 %s이고 가격은 %d 입니다 \n(설명 : %s)\n\n", myWish.name(), myWish.getPrice(), myWish.getKoreanDescription());
    WhiteshipLectureList seven = WhiteshipLectureList.SPRING_SECURITY;

    //Enum의 생성자가 호출되는 시점을 알아내기 위해, 강의 가격별 조회
    int maxi = 120000; //12만원
    int mini = 70000; //7만원
    
    WhiteshipLectureList[] lectureArray = WhiteshipLectureList.values();
    for (WhiteshipLectureList lec : lectureArray) {
        int curPrice = lec.getPrice();
        System.out.printf("가격 %d 짜리인 강의의의 주제는 %s 입니다\n\n", lec.getPrice(), lec.getKoreanDescription());
    }
  }
}

enum WhiteshipLectureList {
    THE_JAVA_JAVA_8(55000, "더 자바, Java8"),
    THE_JAVA_CODE_MANIPULATION(49500, "더 자바, 코드를 조작하는 다양한 방법"),
    THE_JAVA_APPLICATION_TEST(66000, "더 자바, 애플리케이션을 테스트하는 다양한 방법"),
    SPRING_FRAMEWORK_INTRODUCTION(0, "스프링 프레임워크 입문"),
    SPRING_FRAMEWORK_INTRODUCTION_REVISED_EDITION(0, "예제로 배우는 스프링 입문(개정판)"),
    SPRING_FRAMEWORK_CORE(55000, "스프링 프레임워크 핵심 기술"),
    SPRING_FRAMEWORK_WEB_MVC(110000, "스프링 웹 MVC"),
    SPRING_BOOT(110000, "스프링 부트 개념과 활용"),
    SPRING_BOOT_UPDATED(66000, "스프링 부트 업데이트"),
    SPRING_AND_JPA_BASED_WEB_APPLICATION_DEVELOPMENT(330000, "스프링과 JPA 기반 웹 애플리케이션 개발"),
    SPRING_SECURITY(88000, "스프링 시큐리티"),
    REST_API(99000, "스프링 기반 REST API 개발"),
    SPRING_DATA_JPA(88000, "스프링 데이터 JPA"),
    INTERVIEW_GUIDE_SOFTWARE_DEVELOPMENT_ENGINEER(220000, "더 개발자, 인터뷰 가이드");


    private int price;
    private String koreanDescription;
    //private final int price;
    //final 키워드로 수정을 막을 수 있다.

    // 파라미터 두개짜리 생성자로 Enum 생성
    WhiteshipLectureList(int price, String koreanDescription) {
        System.out.printf("enum constructor > price: %d , desc:%s\n", price, koreanDescription);  // 생성자가 언제 호출되는지 알아보기 위해서
        this.price = price;
        this.koreanDescription = koreanDescription;
    }

    // 하나짜리 생성자도 가능
    privte WhiteshipLectureList(int price) {
        this.price = price;
    }

    WhiteshipLectureList(String koreanDescription) {
        this.koreanDescription = koreanDescription;
    }


    //getter && setter ====================
}
//출처: https://xxxelppa.tistory.com/204?category=858435 [한칸짜리책상서랍]
```
### 결론
- enum은 static이라서 프로그램 시작과 동시에 생성자가 호출된다
- private 생성자를 통해서 new로 인스턴스를 생성하는 것은 불가능하지만 인자가 있는 enum을 위한 생성자를 정의할 수 있다. 
- enum의 기본 필드 (거론되진 않지만 내부적으로는 name이라는 이름의 필드)와 별개로 다른 필드변수들을 선언
- enum 생성자의 입력파라미터로 전달함, Enum.name()메소드와의 구분되는 시그니쳐가 필요함
  - 그러니까 ```new WhiteshipLectureList.REST_API()``` 이런 기본 생성자는 사용이 불가능하다
- 필드변수들(price, koreanDescription)의 getter와 setter메서드들을 public 으로 만들어 사용
- enum은 상수로 사용되기 때문에 필드변수들을 final로 선언해 생성 후에 변경되는 것을 방지할수도 있다

### 열거형에 멤버(인스턴스) 변수를 추가하기
- 추가해야 하는 이유는 위에서 언급한 ```ordinal() 메서드```가 가지고 있는 ***```치명적인 부작용```*** 때문에!

- 위에서 ordinal()은 안티패턴이니 사용하지 않는것을 추천드렸는데 그 이유는 아래 코드로 설명하겠습니다.
```java
public enum City {
  서울, 성남, 인천, 수원, 대전, 부산, 춘천
  //0,  1,   2,   3,  4,   5,   6 //ordinal()값
}
```
- 상수가 enum에서 몇 번째 위치인지 판단하려고 ordinal()을 사용하는데요
- 유지보수나 기능추가의 이유로 ```서울```과 ```성남``` 사이에 ```강릉```이라는 도시를 집어넣어야 하는 경우
```java
public enum City {
  서울, 강릉, 성남, 인천, 수원, 대전, 부산, 춘천
  //0,  1,   2,   3,  4,   5,   6,   7
}
```
- 성남부터 끝까지의 ordinal() 번호가 하나씩 밀리는 모습을 확인할수 있는데, 이걸 다 일일이 찾아서 고치려면.. ㅠㅠ..
  - 의도한 상수 순서가 만약에 유지보수하면서 바뀐다면? ordinal()를 호출했던 로직들은 모두 깨진다

### 그래서 해결책

- enum에 인스턴스 변수를 추가하는 방법으로 처리해야 하고
- 인스턴스 변수를 연속적이지 않은 중간에 하나씩 띄엄띄엄 배치해야 한다
```java
public enum City {
  서울(0), 강릉(3), 성남(4), 인천(5), 수원(7), 대전(8), 부산(11), 춘천(19)
  //0,  1,   2,   3,  4,   5,   6,   7
}
```
- 여기서 중요한 뽀인트는 서울-강릉 사이 0 -> 3으로 넘어간 부분인데요
  - 나중에 서울과 강릉사이에 "가평" 등을 추가할 수 있도록 숫자를 중간중간에 비워놓는 꿀팁!
  - 백기선님께서 수업시간에 언급해주신 부분인데 나중에 인강좀 듣고 보완할예정 @Todo

### Enum에 멤버 추가하기
- enum 상수와 연관된 데이터를 상수 자체에 포함시켜 용이하게 관리하기 위해서 Enum에 멤버를 추가함

```java
public enum SearchSite {
	NAVER("https://www.naver.com"),
	DAUM("https://www.daum.net"),
	GOOGLE("https://www.google.com"),
	BING("https://www.bing.com");
 
	private final String url; // 인스턴스 필드 추가
 
	// 생성자 추가
	SearchSite(String url) { this.url = url; }
 
	// 인스턴스 필드 get메서드 추가
	public String getUrl() { return url; }
}
```
- 위 코드처럼 이넘에 인스턴스 필드를 추기하려면 enum 상수의 이름 옆에 원하는 값을 괄호()와 함께 적어주세요

```java
public class Site {
  static final SearchSite NAVER = new SearchSite("NAVER", "https://www.naver.com");

  private final String name;
  private final String url;

  SearchSite(String name, String url) {
      this.name = name;
      this.url = url;
  }
```

### 마지막으로 ordinal() 메서드의 도움말도 살펴보면

```
/**
* Returns the ordinal of this enumeration constant (its position
* in its enum declaration, where the initial constant is assigned
* an ordinal of zero).
*
* Most programmers will have no use for this method.  It is
* designed for use by sophisticated enum-based data structures, such
* as {@link java.util.EnumSet} and {@link java.util.EnumMap}.
*
* @return the ordinal of this enumeration constant
*/
public final int ordinal() {
```
- 후.. 니들은 담배같은거 피지 마라 : ```Most programmers will have no use for this method```

<br><hr><br>

## JPA에서 필드변수(컬럼) 으로 Enum 활용시 주의사항
- JPA에서 필드변수(컬럼)으로 Enum을 사용하면 기본으로 Ordinal로 저장한다
- 실서버로 Enum에 Ordinal로 저장하다 나중에 데이터가 쌓인 상태에서 enum에 값을 추가하면.. 큰일난다
  - 데이터가 하나씩 밀려서 다 엉켜버림..
- 그래서 JPA에서 필드변수(컬럼) 으로 Enum 을 사용할때는 꼭 아래 어노테이션을 사용해야한다
```java
@Enumerated(EnumType.STRING)
private EnumType typeData
```
  - 이렇게 안해주면 EnumType.ORDINAL이 기본값 
    - JPA가 공부 안하고 하이버네이트를 쓰는 사람들을 위해 만들어놓은 지뢰, 그것도 데이터가 쌓여야 발생하는

<br><hr><br>



<br><hr><br>


## EnumSet

[목차로]()

: 열거형 타입과 함께 사용하기 위한 특별한 Set 구현체

- 특징
    - 동기화되지 않음 (멀티쓰레드에서 사용시 주의)
    - iterator를 활용한 순회 가능(Enum으로 for문을 돌릴수 있다)
    - 생성자가 존재하지 않음
    - Enum의 API 문서를 보면 ordinal()은 "EnumSet, EnumMap 같은 열거 타입 기반의 범용 자료구조에 쓸 목적으로 설계되었다."라고 나와있다.

- 예제코드

```java
import java.util.EnumSet;
import java.util.EnumMap;

public class Enset_EX {
    public static void main(String[] args) {
        EnumSet<Flower> es1 = EnumSet.allOf(Flower.class);
        EnumSet<Flower> es2 = EnumSet.range(Flower.marigold_3, Flower.willow_5);

        for (Flower flo : es1) {
            System.out.print(flo + "  , ");
        }

        System.out.println();

        for (Flower flo : es2) {
            System.out.print(flo + "  , ");
        }
    }
}

enum Flower {
    rose_1, iris_2, marigold_3, primrose_4, willow_5
}
```

- 이넘셋 다른 예제코드
```java
import java.util.EnumSet;
 
public class Exam_007 {
    public static void main(String[] args) {
        EnumSet<MyEnum> enumSet = EnumSet.allOf(MyEnum.class);
    
        System.out.println("================= 전체 출력 =================");
        System.out.println(enumSet);
        System.out.println();
        
        EnumSet newEnumSet = EnumSet.of(MyEnum.MON, MyEnum.TUE, MyEnum.WED, MyEnum.THU, MyEnum.FRI);
        
        System.out.println("============= 특정 상수만 출력 ==============");
        System.out.println(newEnumSet);
        System.out.println();
    
        System.out.println("========== 특정 상수 제외하고 출력 ==========");
        System.out.println(EnumSet.complementOf(newEnumSet));
        System.out.println();
        
        System.out.println("================= 범위 출력 =================");
        System.out.println(EnumSet.range(MyEnum.WED, MyEnum.FRI));
        System.out.println();
    }
}
 
enum MyEnum {
    SUN, MON, TUE, WED, THU, FRI, SAT
}


출처: https://xxxelppa.tistory.com/204?category=858435 [한칸짜리책상서랍]
```

- 이 클래스는 모든 메소드가 static 키워드를 사용하여 정의되어 있기 때문에 객체 생성없이 사용할 수 있다.
  객체 생성 없이 사용할 수 있다고 했지만 사실 객체를 생성할 수 없다.
  api 문서를 찾아보면 이 클래스는 abstract 키워드를 사용한 추상 클래스이기 때문이다.
  이 클래스에 대해 조사하다보니 비트필드에 대한 얘기가 많았다.
  이해는 했는데 어떻게 표현하고 정리해야할지 조심스러워서 잘 정리된 글을 첨부
  - https://jaehun2841.github.io/2019/02/04/effective-java-item36/#%EC%84%9C%EB%A1%A0
- 실행결과

- 링크 : 더 알아보기 about Enumset

    : EnumSet에 new 연산자를 사용하지 않는 이유, EnumSet은 생성자를 사용자가 호출불가이유

    - [https://parkadd.tistory.com/50](https://parkadd.tistory.com/50)





## EnumMap이 있는데, 
- Map을 사용한 Enum 항목 캐싱하는 용도로 사용된다
  - 필드를 사용한 Enum항목을 찾을 때 매번 전체 Enum을 순회하는 것이 비효율적인 경우 항목들을 캐싱할 수 있다. 
  - 다음과 같이 class가 로딩될 때 static final Map을 사용하여 캐싱한다.
```java
public enum Element {
  // ...
  private static final Map<String, Element> BY_LABEL = new HashMap<>();
  static {
    for (Element e : values()) {
        BY_LABEL.put(e.label, e);
      }
  }
  //...
  public Element valueOfLabel(String label) {
    return BY_LABEL.get(label);
  }
```
- - 먼저 비효율적인 코드인데, 매번 for문을 순회함을 알수 있다
  - 다음으로 여러 필드를 이용한 캐싱 코드를 살펴보면
```java
public enum Element {
    H("Hydrogen", 1, 1.008f),
    HE("Helium", 2, 4.0026f),
    // ...
    NE("Neon", 10, 20.180f);
 
    private static final Map<String, Element> BY_LABEL = new HashMap<>();
    private static final Map<Integer, Element> BY_ATOMIC_NUMBER = new HashMap<>();
    private static final Map<Float, Element> BY_ATOMIC_WEIGHT = new HashMap<>();
     
    static {
        for (Element e : values()) {
            BY_LABEL.put(e.label, e);
            BY_ATOMIC_NUMBER.put(e.atomicNumber, e);
            BY_ATOMIC_WEIGHT.put(e.atomicWeight, e);
        }
    }
 
    public final String label;
    public final int atomicNumber;
    public final float atomicWeight;
 
    private Element(String label, int atomicNumber, float atomicWeight) {
        this.label = label;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
    }
 
    public static Element valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
 
    public static Element valueOfAtomicNumber(int number) {
        return BY_ATOMIC_NUMBER.get(number);
    }
 
    public static Element valueOfAtomicWeight(float weight) {
        return BY_ATOMIC_WEIGHT.get(weight);
    }
}

```
- 출처 
  - https://www.baeldung.com/java-enum-values
  - https://hilucky.tistory.com/304
  - https://effortguy.tistory.com/24

<br><hr><br>


### TMI : 자바의 열거형이 C/C++열거형보다 우월한 이유
- Java 프로그래밍 언어 열거 형은 다른 언어의 열거 형보다 훨씬 강력하다(Type Strong)
- C/C++에서 제공하던 enum과 Java언어의 그것과는 내부 원리나 사용측면에서 완전히 다르다(근데 기능이랑 역할은 비슷하단 말이지)
  - Java의 enum에서는 열거형의 값 뿐만 아니라 타입까지 관리한다. 이를 **엄격한 타입 정의(strogly typed)**라고 한다
  - C/C++ enum에서는 열거형의 값만을 관리한다. 그러니까 C++의 Enum은 그냥 int형 정수에 껍데기만 이쁘게 덮어놓은것 
    - 아래 두 if문은 완전히 동일한 조건 입니다 (수도코드지만 C++과 매우 유사)
    ```cpp
    enum Season {
        WINTER, SPRING, SUMMER, FALL
    }
    //Direction mySeason : 위에서 사용되는 변수라고 가정
    if(mySeason == WINTER) {
        ...
    }
    
    if(myDir == 0) {// c++ 언어에서는 여기서 에러가 발생하지 않음, Java는 여기서 컴파일 에러 발생
        ...
    }
    ```
    - C++언어에서의 Enum을 굳이 Java로 표현하면 아래와 같습니다.
    ```java
    public static final int SEASON_WINTER = 0;
    public static final int SEASON_SPRING = 1;
    public static final int SEASON_SUMMER = 2;
    public static final int SEASON_FALL   = 3;
    ```


- 프로그래밍 언어차원에서 항상 모든 타입오류를 발견해 낼 수 있는 경우를, "엄격한 타입 정의 언어(strogly typed Lang)" 이라고 부릅니다(by 프로그래밍 언어론)
  - Java가 대표적 ㅎㅎ

:반대의 경우 cpp에서는

enum type과 실제 정수값을 동일시하고, 

공용체(union)등의 요소는 type과 bit단위의 해석을 오로지 프로그래머에게 전적으로 위임하는 방식이기에 에러의 발생 가능성이 높다.

심지어 동적할당을 위해 쓰이는 malloc함수는 (void *) 같은걸 리턴해준다.....

: 용어가지고 깐깐하게 구는 교수님을 엄격한 타입정의라고 생각해도 좋다. (예를들어 국, 찌개, 전골, 탕 간의 차이를 구분하지 못하면 까였다) 


- 잡소리가 길었지만, 언어별로 Enum을 대하는 관점이 다르다
  - 자바는 서로 관련이 없는 Class Type 으로 대하는가 하면
  - C/C++은 내부적으로 정수형으로 관리한다
# 끝