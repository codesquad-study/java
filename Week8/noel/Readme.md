# 8주자 과제: 인터페이스

열: 2021년 5월 30일 오전 1:17
태그: 백기선스터디

💾 관련이슈: [https://github.com/whiteship/live-study/issues/](https://github.com/whiteship/live-study/issues/8)8

🎞️ 유튜브:  [https://www.youtube.com/watch?v=v2BXL6SYaBc&list=PLfI752FpVCS96fSsQe2E3HzYTgdmbz6LU&index=16](https://www.youtube.com/watch?v=v2BXL6SYaBc&list=PLfI752FpVCS96fSsQe2E3HzYTgdmbz6LU&index=16)

```bash
📣 개인 학습용도로 여러 블로그 본문을 스크랩 및 인용하였습니다. (출처표기)
```

# 프레임워크 vs 라이브러리 vs API

## 프레임워크

```java
- 개발할 때에 빈번히 쓰여지는 범용 기능을 한꺼번에 제공해 개발 효율의 향상을 목표하는 소프트웨어 환경
- 프로그램에 기본이 되는 뼈대나 틀'
ex) 평면도, 건물 기본 골격

특징)

- 공통적인 개발환경을 제공한다. (개발편의성)
- 개발할 수 있는 범위가 정해져있다.
    - 원하는 기능을 만들고자 하면 많은 비용이 들 수도 있음
- 제어의 역전이 발생한다.

종류)

- 스프링 프레임워크, 파이썬 장고, 노드JS 등..
```

## 라이브러리

```java
- 라이브러리는 개발자가 사용할 수 있는 API들을 종류나 목적에 따라서 나누어 정의한 API 묶음
- 재사용 가능한 코드의 집합
- EX) 집짓기의 에어컨, 침대, 욕조와 같은 도구 ( 이미 잘만들어진 거 쓰기 )
- import java.util.Random;
- Math...
- 라이브러리의 메서드 == API (Math.max(), Random.nextInt() )

특징)

- 개발하는데 필요한 것들을 모아둔 일종의 저장소
- 필요할 때 호출해서 사용할 수 있음.
    - 주어진 틀에서 사용하는 것이 아닌 목적에 맞는 라이브러리를 호출해 사용할 수 있다.
- (프레임워크와 달리) 어플리케이션의 흐름을 제어하게 된다.
- 개발의 편의성을 제공하는 점은 라이브러리와 공통점임.
```

## API (Application Programming Interface)

```java
- 다른 프로그램이 제공하는 기능을 제어하는 것을 뜻함.
- EX) 조명, 가전제품 등을 제어할 수 있는 리모컨이 존재해야한다.
- 프로그램(라이브러리)을 제어하는 일종의 리모컨(API)
- 운영체제나 프로그램과 이어주는 다리역할로 볼 수 있음.

특징)

- 다른 프로그램과 연결 해주는 다리 역할
- 구현이 아닌 제어를 담당한다.
- API를 조합해 원하는 프로그램을 만들 수 있다.
    - EX) 버스 시간 알리미 등..
```

# 인터페이스

- 느슨한 결합과 협업을 위해 사용

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/_2021-01-06__11.13.14.png)

[https://docs.oracle.com/javase/8/docs/api/java/util/List.html](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)

# 자바 인터페이스 종류

```java
1. normal interfaces
2. annotation types.
 - 일반 인터페이스 선언과 구별하기 위해 키워드 interface앞에 ( @) 추가
 - 애노테이션 학습때 정리
```

# 인터페이스 정의하는 방법

- 인터페이스의 종류는 `2가지`다.
- 인터페이스는 인스턴스화 할 수 없으므로 생성자가 필요 없습니다.

```java
// 1. 일반 인터페이스 정의:

{인터페이스 제어자} interface 식별자 [타입파라미터] [인터페이스 상속] {

 인터페이스 바디

}

// 2. 애노테이션 타입 인터페이스 정의:

{인터페이스 제어자} @ interface 식별자 {

 애노테이션타입 바디

}
```

## 인터페이스 제어자

```java

(one of)
- Annotation public, protected, private
- abstract, static, strictfp
```

## 인터페이스 바디

```java
인터페이스 바디:
{ 
	{인터페이스 멤버 정의} 
}

인터페이스 멤버 정의:

- 상수 정의
  - 초기화 안돼있으면 컴파일 타임에서 오류

- 인터페이스 메소드 정의

- 클래스 정의

- 인터페이스 정의
```

```java
public interface Human{
	String getName();
  int getAge();
}
```

# 인터페이스 구현하는 방법

- 클래스 상속과 키워드가 다르게 `implements` 를 사용한다.

```java
public class Sanhee implements Human{
	@Override
  public String getName(){
		 return "박산희";
  }
  @Override
  public int getAge(){
     return 27;
  }
}

public class Noel implements Human{
	@Override
  public String getName(){
		 return "노을";
  }
  @Override
  public int getAge(){
     return 27;
  }
}

```

# 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

- 객체지향의 다형성 특징을 리마인드하면, 부모 타입의 참조변수로 자식 타입의 인스턴스를 참조할 수 있는 것을 인터페이스에서도 가능하다.
- 메서드의 `파라미터`, `리턴값`으로 인터페이스를 지정할 수도 있음.

```java
public void printInfo(Human human){
		System.out.println(human.getName());
    System.out.println(human.getAge());
}

Human sanhee = new Sanhee();
Human noel = new Noel();

printInfo(sanhee);
printInfo(noel);
```

# 인터페이스 상속

- `extends` 키워드를 사용한다.
- `,`  키워드로 `다중상속` 이 가능하다.
- 상속은 재사용성의 이유가 아닌, `다형성` 이  주된이유!
- 인터페이스는 처음 정의할 때 부터 모든 가능성을 염두에 두고 정의 해야합니다.
- 인터페이스에 메서드를 추가해서 잘 동작하던 클래스를 컴파일 에러가 발생하지 않게하는 것이 효과적이고, 또한 필요없는 구현을 할 필요도 없습니다.
- 이를 SOLID 원칙 중 ISP(인터페이스 분리 원칙)이라고도 합니다.

```java
ISP ( Interface Segregation Principal )

인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다는 원칙이다.[1] 

"인터페이스 분리 원칙"은 큰 덩어리의 인터페이스들을 "구체적"이고 "작은 단위"들로 분리시킴으로써 클라이언트들이 "꼭 필요한 메서드"들만 이용할 수 있게 한다. 

이와 같은 작은 단위들을 역할 인터페이스라고도 부른다.[2] 

인터페이스 분리 원칙을 통해 시스템의 "내부 의존성을 약화"시켜 리팩토링, 수정, 재배포를 쉽게 할 수 있다. 
```

### 다중상속이 불가능한 경우

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/Untitled.png)

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/Untitled%201.png)

- 출처:  [https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6](https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6)

- 상위 인터페이스에 있는 메서드 중에서 **메서드 명과 파라미터 형식은 같지만 리턴 타입이 다른** 메서드가 있다면, 둘중 어떤 것을 상속받느냐에 따라 **규칙이 달라지기 때문에 다중 상속이 불가능**하다.

## (스크랩) 여러 메서드가 같은 시그니처를 가지면 충돌❗❗

1. `클래스나, 슈퍼클래스에 정의된 메서드`가 default 메서드보다 `우선`합니다. 이외의 상황에서는 default 메서드가 선택됩니다.
2. default 메서드의 시그니처가 같고, 상속관계로도 충돌을 해결할 수 없는 경우, default 메서드를 사용하는 클래스에서 오버라이드해서 어떤 쪽의 디폴트 메서드를 호출할지 명시적으로 결정해주어야 합니다.

- [https://velog.io/@dion/백기선님-온라인-스터디-8주차-인터페이스#인터페이스의-기본-메소드default-method-자바-8](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-8%EC%A3%BC%EC%B0%A8-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4#%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EC%9D%98-%EA%B8%B0%EB%B3%B8-%EB%A9%94%EC%86%8C%EB%93%9Cdefault-method-%EC%9E%90%EB%B0%94-8)

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/Untitled%202.png)

- 중복되는 인터페이스의 추상 메소드를 재정의 하여 사용
- **`구현할 인터페이스.super.중복되는 메소드`**

```java
public class Snake implements PrintableAnimal, Printer {

    // ...

    @Override
    public void print() {
        PrintableAnimal.super.print(); // PrintableAnimal의 print() 메소드 호출
        Printer.super.print(); // Printer의 print() 메소드 호출

        // 혹은 직접 구현...
    }
}
```

- 출처:  [https://blog.baesangwoo.dev/posts/java-livestudy-8week/](https://blog.baesangwoo.dev/posts/java-livestudy-8week/)

# 인터페이스의 기본 메소드 (Default Method), 자바 8

- 과거 인터페이스는 `기능` 에 대한 선언만 가능했음.
- 자바8부터 추상 클래스와 같이 로직을 구현한 메소드를 추가할 수 있게 됨.
- 구현체 입장에서 내부로직을 모르므로, 올바르지 않게 사용할 리스크가 존재한다.
    - 문서화 필요
- 인터페이스의 구현체가 default method를 `재정의` 할 수 있다.
- **기본 메소드가 있는 인터페이스를 상속받은 인터페이스에서 다시 추상 메소드로 변경할 수 있다.**

## 사용법

```bash
- default 키워드를 사용하여 기본 메소드를 구현할 수 있음
- 기본 메소드는 구현체에서 필수로 구현할 필요가 없음
```

## Default Method 등장배경

```java
...(중략)... 바로 "하위 호환성"때문이다. 예를 들어 설명하자면, 여러분들이 만약 오픈 소스코드를 만들었다고 가정하자. 그 오픈소스가 엄청 유명해져서 전 세계 사람들이 다 사용하고 있는데, 인터페이스에 새로운 메소드를 만들어야 하는 상황이 발생했다. 자칫 잘못하면 내가 만든 오픈소스를 사용한 사람들은 전부 오류가 발생하고 수정을 해야 하는 일이 발생할 수도 있다. 이럴 때 사용하는 것이 바로 default 메소드다. (자바의 신 2권)

https://dev-coco.tistory.com/13
```

## (스크랩) 상속에서 자유로워지게 됐다.

- 아직 완벽하게 이해 못함. 스터디때 물어봐야지

### 과거 인터페이스의 기본 메소드가 제공되지 않았던 시절

아래와 같은 형태로 개발을 진행하기도 하였다.

- 인터페이스의 여러가지 메소드들 중 한가지 메소드만 사용하는 구현체가 있을 때
- 중간에 추상 클래스를 만들어서, 이 추상 클래스를 확장하는 구현체에서는 필요한 메소드만 구현할 수 있도록하는 일종의 편의제공성으로 개발되었다.

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/IMG_0080.jpg)

### 하지만, 자바 8 이상에서 인터페이스의 기본 메소드가 제공됨에 따라

위와 같이 추가적인 추상 클래스가 필요없이도 원하는대로 개발할 수 있게 되었다.

![](https://github.com/codesquad-study/java/blob/main/Week8/noel/img/IMG_0081.jpg)

- 위 본문 및 사진 출처 : [https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6](https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6)

# 추상클래스의 의미가 없어진걸까?

```bash
아직 완벽하게 대체는 불가능하다.

1. default, static의 경우 JAVA 8 이상부터 가능하다.

2. 추상 클래스는 mutable한 상태를 가질 수 있음

	2-1. 추상 클래스처럼 인스턴스 변수를 정의할 수 없다. (상수만 가능)

	2-2. 추상 클래스처럼 private 제어자를 사용하는 변수를 정의할 수 없다. ❓❓ 검증필요

* 따라서, 아직 추상 클래스에서만 할 수 있는 것들이 존재함. 위치가 애매하긴 하지만 효용가치는 아직 존재
```

- 출처: [https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6](https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6)

# 인터페이스의 static 메소드, 자바 8

- 클래스에서와 동일하게 사용 가능하며 기본적으로 public으로 간주
- `static` 메소드 이므로, 상속이 불가능하다.

# 인터페이스의 private 메소드, 자바 9

- Java8에서 기본 메소드 사용이 가능해졌기 때문에 로직을 분리하기 위해 사용
- `private` 메소드 이기 때문에 인터페이스에서 구현돼 있어야함
- 추상 메소드일 수 없음
- static 메소드도 `private` 이 가능하다.

```java
public interface PrintableAnimal extends Animal, Comparable<PrintableAnimal> {

    static String getDescription() {
        return "출력기능이 있는 동물 인터페이스";
    }

    // 자신의 메소드를 기본 메소드로 구현
    // private 메소드 사용
    default void print() {
        printName();
        printLegs();
    }
    
    // private 메소드로 기본 메소드에서 사용할 로직 분리 1
    private void printName() {
        System.out.println("이름 : " + getName());
    }
    
    // private 메소드로 기본 메소드에서 사용할 로직 분리 2
    private void printLegs() {
        System.out.println("다리개수 : " + getLegs());
    }

    // Comparable 인터페이스의 메소드를 상속하여 기본 메소드 구현
    @Override
    default int compareTo(PrintableAnimal o) {
        return getLegs() - o.getLegs();
    }

    default void otherDefaultMethod() {
        System.out.println("other default method");
    }
}
```

- 출처 : [https://blog.baesangwoo.dev/posts/java-livestudy-8week/](https://blog.baesangwoo.dev/posts/java-livestudy-8week/)

---

## Reference.

[https://www.youtube.com/watch?v=_j4u4ftWwhQ](https://www.youtube.com/watch?v=_j4u4ftWwhQ)

[https://docs.oracle.com/javase/specs/jls/se9/html/jls-9.html](https://docs.oracle.com/javase/specs/jls/se9/html/jls-9.html)

[https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6](https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6)

[https://blog.baesangwoo.dev/posts/java-livestudy-8week/](https://blog.baesangwoo.dev/posts/java-livestudy-8week/)

https://dev-coco.tistory.com/13

[https://velog.io/@dion/백기선님-온라인-스터디-8주차-인터페이스#인터페이스의-기본-메소드default-method-자바-8](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-8%EC%A3%BC%EC%B0%A8-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4#%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EC%9D%98-%EA%B8%B0%EB%B3%B8-%EB%A9%94%EC%86%8C%EB%93%9Cdefault-method-%EC%9E%90%EB%B0%94-8)

---

# ❤ 받은 게시글 스크랩

```bash
8주차 과제 제출합니다. :)

https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6

(whiteship) 추상 클래스 사용하던 예제 중에 하나를 잘 보여주셨네요. 감사합니다.
```

```bash
https://blog.baesangwoo.dev/posts/java-livestudy-8week/
8주차 과제 제출합니다~! 감사합니다 👍

(whiteship) 기본 메소드 다중 상속 예제 감사합니다.
```

```bash
8주차 과제입니다.
https://dev-coco.tistory.com/13
감사합니다

(whiteship) 기본 메소드 등장 배경에 대해 잘 설명해 주셨네요. 감사합니다.
```

```bash
8주차 과제 제출합니다 :3
https://yadon079.github.io/2021/java%20study%20halle/week-08

(whiteship) 미포 바텀 예제 좋군요 ㅋㅋ
```

```bash
8주차 과제 제출
https://k3068.tistory.com/34

(whiteship) is-a , has-a 설명 감사합니다.
```

```bash
https://www.notion.so/Java-8-0cc8c251d5374ac882a4f22fa07c4e6a
8주차 과제 제출합니다.

(whiteship) 인터페이스에 사용할 수 있는 디폴트 메소드와 스태틱 메소드 다양한 예제가 좋네요. 감사합니다.
```

## 유튜브

```bash
https://github.com/inhalin/whiteship-live-study/blob/main/week-08.md

과제 마지막 부분의 private 메소드 부분 공부하면서 참고한 코드가 잘 이해가 안돼서요.. 혹시 라이브 수업때 조금 설명을 해주실 수 있을까요?

(whitesihp) 네 유튜브 라이브 방송에서 다루겠습니다.
```

## 추상클래스가 필요없지 않을까? 유튜브 확인

```bash
과제 제출합니다.
https://velog.io/@ljs0429777/8주차-과제-인터페이스

이번 과제를 공부하다 보니 깊게 공부했다기보단 의식의 흐름이 더 큰 거 같습니다..
자바 8버전에서 지원하는 static, default, function을 공부하면서 느낀거지만 이제는 추상 클래스가 필요 없지 않을까요?? 이 부분에 대해서 선장님에 대한 생각도 궁금합니다!

(whiteship) 좋은 질문 감사합니다. 유튜브 라이브 방송에서 다루겠습니다.
```

```bash
8주차 과제 제출합니다!

https://ahnyezi.github.io/java/javastudy-8-interface/

과제: 자바의 인터페이스
(whiteship) 강한 결합, 느슨한 결합!! 감사합니다.
```

```bash
8주차 과제 제출합니다 ~
8주차 과제
https://github.com/yeGenieee/java-live-study/blob/main/%5B8%5DJava%20Live%20Study.md

(whiteship) 정리 꼼꼼하게 잘하셨네요. 귿!
```

### **구현 클래스에서 인터페이스 추상 메소드들에 대한 실체 메소드 작성 시 주의할 점**

- 인터페이스의 모든 메소드는 기본적으로 `public` 접근 제한을 가짐
    - 따라서, 구현 클래스의 실체 메소드들은 `public` 보다 **낮은 접근 제한자를 가질 수 없음**
        - `public` 생략 시
            - *`Cannot reduce the visibility of the inherited method`* : **컴파일 에러** 발생
- 만약, 인터페이스에 선언된 추상 메소드에 대응하는 **실체 메소드를 구현 클래스가 작성하지 않으면**
    - 구현 클래스는 자동적으로 추상 클래스가 됨

```bash
안녕하세요? 선장님과 선원님들 모두 감사합니다.

참 저희는 익명 설문에 sli.do 쓰는데 수업기간 동안 링크를 재공유할 필요가 없어서 편해요. 한 번 검토해 보세요1

8주차 스터디 과제 인터페이스 링크

https://velog.io/@honux/%EB%B0%B1%EA%B8%B0%EC%84%A0-%EC%9E%90%EB%B0%94-%EB%9D%BC%EC%9D%B4%EB%B8%8C-%EC%8A%A4%ED%84%B0%EB%94%948-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4

(whiteship) 오 감사합니다. 써보겠습니다.
```

```bash

"디온"

https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-8%EC%A3%BC%EC%B0%A8-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4
```