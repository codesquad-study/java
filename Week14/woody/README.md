# 14주차 과제: 제네릭

- 제네릭 사용법
- 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
- 제네릭 메소드 만들기
- Erasure



## 제네릭 (Generics)

컴파일 시의 타입 체크(compile-time type check)를 해주는 기능을 제공한다. 

1. 객체의 타입 안정성

2. 형변환 생략 가능



## 제네릭 사용법

클래스 선언에 타입 매개변수를 추가한 형태로 제네릭 클래스 선언이 가능하다.

```java
class name<T1, T2, ..., Tn> { /* ... */ }
```



### 제네릭 선언

```java
class Box<T>
```

- Box<T> : Generic class

- T : 타입 변수 혹은 타입 매개변수

- Box : 원시 타입 (raw type)

  

### 제너릭 타입

매개변수 형태로 타입이 지정된 클래스 혹은 인터페이스를 말한다.



### 타입 파라미터 

- E - Element (used extensively by the Java Collections Framework)
- K - Key
- N - Number
- T - Type
- V - Value
- S,U,V etc. - 2nd, 3rd, 4th types

**Naming Conventions**

- single, uppercase letters

- 일반 클래스 혹은 인터페이스 이름이나 타입 변수와 명확히 구분짓기위해서 사용하는 컨벤션이므로 지켜주는 것이 좋다.



### 왜 필요할까?

예를 들어 Box라는 클래스가 아래와 같이 있다.

```java
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```

Box 안에는 어떤 타입이든 넣을 수 있다. 하지만 한 번 A라는 객체를 Box안에 넣게되면 getter나 setter 메소드는 A라는 객체만 핸들할  수 있어야 한다.

이를 위해서 일반 클래스를 제너릭 클래스로 만들어준다!! 

```java
public class Box<T> {
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```

- Object 타입을 타입 변수 T로 치환해준다
- T는 non-primitive 타입 (class, interface, array, 다른 타입 변수 등)



### 그 밖의 주의사항

1. **Type Parameter != Type Arugment**

   - **Type Parameter** : T in Foo<T>

   - **Type Arugment** : Integer in Foo<Integer>
     - 이렇게 타입 변수자리에 실제로 대입된 타입을 *parameterized type* 이라고도 한다. (여기서는 Integer)

2. **제네릭 설정 레벨**
   - 클래스 혹은 인터페이스
     - 클래스 레벨에서 제너릭 설정이 되어있다면 static 메소드에서 제너릭 사용 불가능
     - 타입 변수 T가 인스턴스 변수로 간주되기 때문
   - 메소드
     - 파라미터 혹은 리턴 타입 파라미터
     - static 혹은 instance 메소드에 설정되어도 상관없다. 
       - 인스턴스를 생성할 때, type arugment를 받아오기 때문에 타입을 알 수 있다.



## 제네릭 주요 개념

### 바운드 타입 매개변수 (Bounded Type Parameter)

타입 매개변수를 특정 타입의 하위 타입으로 제한한다.

```java
class DelayQueue<E extends Delayed> implements BockingQueue<E>
```

- java.util.concurrent.Delayed 의 하위타입만  받는다는 뜻.

- DelayQueue와 DelayQueue를 사용하는 다른 클래스들은 DelayQueue 원소에서 형변환 혹은 런타임 오류 걱정 없이 Delayed 클래스의 메서드를 호출할 수 있음이 보장된다.

  

**🚨 주의할 점**

- <T>의 경우, extends는 사용가능하지만 super는 사용할 수 없다

  

**Multiple Bounds**

타입 파라미터가 멀티플 바운드를 가질 수도 있다.

```java
Class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

class D <T extends A & B & C> { /* ... */ }
class D <T extends B & A & C> { /* ... */ }  // compile-time error
```



**바운드 타입 매개변수 예시**

```java
class A {}
class B extends A {}
interface C {}
class Bound<T extends A> {}
class Bound<T extends C> {} // 인터페이스를 구현해야한다는 제약을 넣어줄 때도 'extends' 사용

Bound<B> beb = new Bound<B>(new B());
Bound<A> bea = new Bound<A>(new A());
```



## 와일드 카드 (WildCard)

- 물음표 마크(`?`)를 와일드 카드로 사용

- 파라미터, 필드 , 지역변수 혹은 리턴 타입으로 사용

- 제너릭 메소드 호출 시 Type Arugment, 제너릭 클래스에서 인스턴스 생성에는 쓰이지 않는다.

  - ```java
    public static <E> void append(List<E> list) {
        E elem = new E();  // compile-time error
        list.add(elem);
    }
    ```

  - ```java
    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }
    ```

    

### Bounded Wildcards(Upper Bouned & Lower Bounded)

**선언 방법**

```java
<? extends T> T와 하위 클래스만 가능
<? super T> T와 상위 클래스만 가능
```



**왜 사용하나?**

- 제네릭으로 구현된 메소드는 선언된 타입으로만 매개변수를 입력해야 한다.

- 즉, 상속받은 클래스 혹은 부모클래스를 매개변수로 사용하고 싶어도 불가능

  ```java
  ArrayList<ParentClass> list = new ArrayList<ChildClass>(); // 제네릭 타입 불일치
  ```



**bounded 와일드 카드 사용 기본 원칙**

- 펙스(PECS) : producer-extends, consumer-super

  - 매개변수화 타입 T이 생산자일 때는 extends

  - 매개변수화 타입 T이 소비자일 때는 super

```java
public class Stack<E> {
  public void push(E e);
  public E pop();
  public boolean isEmpty();
}
```

```java
public void pushAll(Iterable<? extends E> src) {
  	for (E e : src) {
      push(e);
    }
}
// src 매개변수는 Stack가 사용할 E 인스턴스를 생성한다
```

```java
public void popAll(Collection<? super E> dst) {
  	while (!isEmpty()){
      dst.add(pop());
    }
}
// dst 매개변수는 Stack으로부터 E 인스턴스를 소비한다
```



🚨 **주의할 점**

- 반환 타입에는 bounded type parameter(한정적 와일드 카드 타입)을 사용하면 안된다.



### Unbound Wildcards

**선언 방법**

```java
<?> 모든 타입이 가능 (== <? extends Object>)
```



**언제 사용하나?**

- Object 클래스에 있는 기능을 사용해 메소드 로직을 작성하는 경우 
  - <? extends Object> 와 동일한 역할을 하기 때문

- 제너릭 클래스에서 사용되는 메소드가 클래스의 타입 파라미터에 의존적이지 않을 때
  - 메소드 로직이 Class<T> 의 T에 의존적이지 않을 때



**Class<Object>와 Class<?> 차이는?**

```java
public class UnBoundWildcard {

  // Object 제너릭 타입 파라미터를 가진 제너릭 메소드
    public static void printObjectList(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
    }
  // 와일드카드 제너릭 타입 파라미터를 가진 제너릭 메소드
    public static void printWildcardList(List<?> list) {
        for (Object elem: list)
            System.out.print(elem + " ");
    }

}
```



- List<Integer>와 List<String>는 List<Object>의 하위 클래스가 아니기 때문에 아래 예시에서 컴파일 에러 발생
  - 매개변수화 타입은 **무공변(invariant)**
  - 무공변(invariant) : 상속 관계에 상관없이, 자기 타입만 허용한다. 서로 다른 타입(상속 관계일 수도 있다) Type1과 Type2가 있을 때 List<Type1>과 List<Type2>는 서로의 하위 타입도 상위 타입도 아니다.
- 제너릭 메소드 사용을 위해서는 List<?>를 사용해주어야 한다.

```java
@Test
    void printObjectList() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");
//        unBoundWildcard.printObjectList(li); 컴파일 에러
//        unBoundWildcard.printObjectList(ls); 컴파일 에러
    }

    @Test
    void printWildcardList() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");
        unBoundWildcard.printWildcardList(li);
        unBoundWildcard.printWildcardList(ls);

    }
```







## 제네릭 메소드 만들기

제네릭 메소드란 메소드 선언부에 제네릭 타입이 선언된 메소드를 말한다. generic type(클래스 혹은 인터페이스 레벨)과 비슷하지만 type parameter의 범위가 메서드에 한정적으로 적용된다는 점에서 차이가 있다.

- static과 non-static 제너릭 메소드 둘 다 가능



### 제너릭 메소드 사용 예시

```java
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
}
```

- Util 클래스에 제너릭 메소드로 compare()가 정의되었고, Util 클래스 레벨에서는 타입 파라미터 K, V가 따로 정의되어있지 않다.

```java
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public K getKey()   { return key; }
    public V getValue() { return value; }
}
```



```java
Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(2, "pear");
boolean same = Util.compare(p1, p2); // 타입 추론을 통해 타입 생략!! 원래 코드는 Util.<Integer, String>compare(p1, p2);
```





## Erasure

제네릭은 **타입의 정보가 런타임에는 소거** 된다

컴파일러는 제네릭 타입을 통해 소스파일의 타입을 체크하고 형변환을 진행한다. 그 뒤에는 제네릭 타입을 제거해준다!

때문에 클래스 파일에는 제네릭에 대한 정보가 없다!

```java
// 컴파일 이전
public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
    // ...
}
```



```java
// 컴파일 이후 - 제네릭 정보 소거
public class Node {

    private Comparable data;
    private Node next;

    public Node(Comparable data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Comparable getData() { return data; }
    // ...
}

```

- 제네릭이 도입되기 전(jdk 1.5 전)의 코드와 호환성을 맞추기 위한 작업
- BUT, 로 타입은 사용하지 말자. 최대한 컴파일 타임에 예외를 잡아낼 수 있도록 하자



### 그밖의 팁

타입 추론

```java
List<Integer> list = new ArrayList<Integer>(); : ~ java6
List<Integer> list = new ArrayList<>(); : java7 ~
```



 java7부터 인스턴스 생성할 때 타입 인자를 2번 주지않아도 된다.



### Quiz - [참고](https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-answers.html)

1. 아래 코드는 컴파일이 될까요?

```java
public final class Algorithm {
    public static <T> T max(T x, T y) {
        return x > y ? x : y;
    }
}
```



2. 아래 코드는 컴파일이 될까요?

```java
public class Singleton<T> {

    public static T getInstance() {
        if (instance == null)
            instance = new Singleton<T>();

        return instance;
    }

    private static T instance = null;
}
```

3. 아래와 같은 클래스들이 정의되었을 때 뒤의 코드들은 컴파일이 될까요?

```java
class Shape { /* ... */ }
class Circle extends Shape { /* ... */ }
class Rectangle extends Shape { /* ... */ }

class Node<T> { /* ... */ }
```

```java
Node<Circle> nc = new Node<>();
Node<Shape>  ns = nc;
```

4. 둘 다 컴파일이 될까요?

```java
public class GenericClass<T> {
		public static void method1(T t) {
			doSomething...
		}
}
public class GeneralClass {
		public static void <T> method2(T t) {
			doSomething...
		}
}
```



## Reference

- Oracle Java document
  - https://docs.oracle.com/javase/tutorial/java/generics/index.html

- Effective Java

- https://vvshinevv.tistory.com/55 [왜 모르는가?]
