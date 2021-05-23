# 6주차 과제: 상속

URL: https://github.com/whiteship/live-study/issues/56
열: 2021년 5월 23일 오전 01:00
태그: 백기선스터디

# 자바 상속의 특징

- 상속은 기존의 클래스를 재사용하여, 보다 적은양의 코드로 클래스를 작성할 수 있다.
- 공통적으로 관리할 수 있어, 코드의 추가 및 변경이 용이하다.
- 상속은 한방향으로만 연속적으로 생길 수 있다.
- Object 클래스를 제외하고, 모든 클래스는 단 하나의 조상 클래스를 가질 수 있다.
- 조상의 멤버만 상속이 된다.
- 생성자와 초기화 블럭은 상속되지 않는다.
- 자바의 경우 단일 상속만 허용한다.

## 클래스간의 - 포함관계( Compostion )

- 상속이외에 클래스를 재사용하는 또 다른 방법
- 클래스의 멤버변수로, 다른 클래스 타입의 참조변수를 선언

```java
class Circle{
  Point c = new Point();
  int r;
}
```

```java
상속관계 : ~은 ~이다. (is-a)

포함관계 : ~은 ~을 가지고 있다. (has-a)
```

상속

- 캡슐화 저해
- 리스코프 치환원칙 위배
- 조상 클래스의 파급효과가 높음

[https://www.youtube.com/watch?v=YJ4JJsGy8rY](https://www.youtube.com/watch?v=YJ4JJsGy8rY)

[https://www.youtube.com/watch?v=clbpnp2xYOQ](https://www.youtube.com/watch?v=clbpnp2xYOQ)

# super 키워드

## super

- 자손 클래스에서 조상 클래스로부터 상속 받은 멤버를 참조하는데 사용하는 참조변수
- super 대신 this를 사용할 수 있다.
    - 조상 클래스로부터 상속받은 멤버도 자손 클래스 자신의 멤버이기 때문이다.
- 상속받은 멤버와 자신의 클래스에 정의된 멤버의 이름이 겹칠때 super를 통해 구분할 수 있다.
- this와 마찬가지로 static메서드에서 사용이 불가능하고, 인스턴스 메서드에서만 가능하다.

## super()

- 조상 클래스의 생성자
- this()는 같은 클래스의 다른 생성자를 호출하므로, 서로 다르다.
- 자손 클래스의 인스턴스를 생성하면,
    - 자손 멤버 + 조상 멤버되어 하나의 인스턴스가 생성된다.
    - 이떄, 조상 클래스 멤버의 초기화 작업이 수행되어야 하기 때문에 자손 크래스의 생성자에서 조상 클래스의 생성자가 호출돼야한다.
- 조상 클래스의 생성자 호출은 클래스의 상속관계를 거슬러 올라가면서 계속 반복됨.
    - 마지막으로 모든 클래스의 최고 조상인 Object 클래스의 생성자인 Object()까지 가서 끝남.
- Object 클래스를 제외한 모든 클래스의 생성자 첫 줄에 `this()` 또는 `super()`를 호출해야한다.
    - 그렇지 않으면 `컴파일러`가 자동으로 `super();`를 생성자의 첫줄에 삽입해준다.

# 메소드 오버라이딩

조상 클래스로부터 상속 받은 메서드의 내용을 변경하는 것

ex) toString()

```java
@Override
    public String toString() {
        return "VarArgsEx{" +
                "test=" + test +
                '}';
    }
```

## 오버라이딩 조건

- 이름이 같아야한다.
- 매개변수가 같아야 한다.
- 반환타입이 같아야 한다.

## Static Method의 Hiding

- `static method`의 경우 `자식 클래스`에서 동일한 메서드를 재정의하게되면 `조상 클래스` 메서드를 가리게됨
- 따라서 `static method`는 하위 혹은 상위 클래스의 호출에 영향을 받음

```java
public class Human{
	public static void print(){
    print("나는 사람");
  }
}

public class SanHee extends Human{
  @Override
  public static void print(){
    print("나는 산희");
  }
}

public class Park extends SanHee{...}
public class Psh extends Human {...}

---------
output

Human.print() -> "나는 사람"
SanHee.print() -> "나는 산희"
Park.print() -> "나는 산희"
Psh.print() -> "나는 사람"
```

- [https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week](https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week)

## 오버로딩

- 기존에 없는 새로운 메서드 정의
- 메서드명과 리턴값은 같고, 매개변수의 형식이나 개수가 달라질 수 있음.

![Untitled](/img/Untitled.png)

# 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)

- 자바는 `오버라이딩`된 메소드를 호출할 때 어떤 메소드를 호출할지 `런타임`에 결정하는데 이것을 다이나믹 디스패치라고 한다.
- 다이나믹 디스패치를 통해 실행하는 메소드나 함수를 virtual function(method)라고 부른다.
- 만약 메서드의 호출이 `컴파일 시점`에 모두 결정된다면, 자식 클래스를 조상 클래스의 참조변수로 표현할 시, 재정의된 메서드를 호출 할 수 없게됩니다.

```java
Human h = new Hero();
h.hello();  // Heror의 hello() 호출
```

### **Double Dispatch**

**`Double Dispatch`란 호출하고자 하는 메서드가 런타임 시점에 호출하는 객체의 타입과 argument의 타입으로 인해 정해지는 것을 의미**

- 자바에서는 지원을 안하지만 visitor 패턴을 통해 흉내낼 수 있음.
- [https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week](https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week)

# 추상 클래스

- 공통된 구현부와 미완성 메서드를 포함하고 있는 클래스
- 멤버변수와 메서드를 가질 수 있다.
- 추상 클래스는 그 자체로 생성할 수 없고, 상속받은 자식 클래스가 미완성 부분을 구현해야만 사용 가능
- 자식 클래스에게 추상 메서드를 반드시 구현하도록 강요하기 위해 사용
- 인터페이스에 `default` 제어자가 생긴뒤로 추상 클래스의 입지가 많이 흔들리고 있다고 함.

# final 키워드

- 제어자중 하나로 거의 모든 대상에 사용될 수 있다.
- `final 변수`  값을 변경할 수 없는 상수
- `fianl 메서드` 오버라이딩 금지
- `final class` 상속 금지 → 확장될 수 없는 클래스

# Object 클래스

- 모든 클래스의 최고 조상
- 모든 클래스에서 Object 클래스의 멤버를 바로 사용할 수 있다.

### clone() 메서드

- 사용하기 위해서 구현 해야하는 Cloneable 인터페이스 안에 아무것도 없다.

```
   	public interface Cloneable {}
```

- 자바 개발자 Josh Bloch의 인터뷰에 따르면 설계에 결함이 있어 사용을 지양한다고 함.

> Bill Venners: In your book you recommend using a copy constructor instead of implementing Cloneable and writing clone. Could you elaborate on that?

Josh Bloch: If you've read the item about cloning in my book, especially if you read between the lines, you will know that I think clone is deeply broken. There are a few design flaws, the biggest of which is that the Cloneable interface does not have a clone method. And that means it simply doesn't work
...
[https://www.artima.com/intv/bloch.html#part13](https://www.artima.com/intv/bloch.html#part13)

- 얇은 복사
    - ex) clone()..
    - 단순히 인스턴스의 변수 값만을 복사함, 참조가 가리키는 객체의 인스턴스는 복사되지 않음.
    - 원본과 복제본이 같은 객체르 공유하게 됨.
- 깊은 복사
    - clone()카테고리는 아니지만, ***깊은 복사를 구현할 수 있는 종류가 3가지*** 있다.
    1. **직접 값을 넣어서 객체 새로 생성**
    2. **복사 생성자 또는 복사 팩토리 메서드**
    3. **Clonable 인터페이스를 구현하여 clone() 활용**
        - 기본 clone()은 얇은 복사이므로, 약간의 커스텀이 필요하다.

```java
 @Override
    public Circle clone(){ //m 깊은 복사, 공변  반환타입 ( Object -> Circle )
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Circle c = (Circle)obj;
        c.p = new Point(this.p.x, this.p.y);
        return c; // 공변  반환타입
    }
```

- 이해가 안돼서 해본 코딩

```java
public class CloneHashCodeCheck {
     static class Point implements Cloneable{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + '}';
        }
        @Override
        public Object clone(){
            Object obj = null;
            try{
                obj = super.clone();
            }
            catch (CloneNotSupportedException e){
                e.printStackTrace();
            }
            return obj;
        }
    }
    public static void main(String[] args) {

        Point original = new Point(3, 5);
        Point copy = (Point)original.clone(); // 복제
        System.out.println(original);
        System.out.println(copy);

        System.out.println("==================");

        System.out.println("original.hashCode(): "+ original.hashCode());
        System.out.println("copy.hashCode(): "+copy.hashCode());

    }

}

================== OUTPUT ==================

Point{x=3, y=5}
Point{x=3, y=5}
==================
original.hashCode(): 460141958
copy.hashCode(): 1163157884 // 다름을 확인할 수 있음.
```

---

## 참고

자바의 정석

[https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week](https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week)

[https://velog.io/@honux/백기선-자바-라이브-스터디6-상속](https://velog.io/@honux/%EB%B0%B1%EA%B8%B0%EC%84%A0-%EC%9E%90%EB%B0%94-%EB%9D%BC%EC%9D%B4%EB%B8%8C-%EC%8A%A4%ED%84%B0%EB%94%946-%EC%83%81%EC%86%8D)