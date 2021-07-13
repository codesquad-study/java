### 들어가기전에

- JDK1.5 이전에는 다양한 종류의 타입을 다루는 메서드의 매개변수나 리턴타입으로 `Object 타입`의 `참조변수`를 많이 사용했다.
- 그로 인해 `형변환`이 불가했다. 



## 제네릭

- 다룰 객체의 타입을 `미리 명시`함으로써 번거로운 `형변환`을 줄여준다.

- 컴파일 시에 체크하기 때문에 객체의 `타입 안정성`을 높인다.
  - 에러를 사전에 방지할 수 있음

    

## 제네릭 사용법

```java
public class MyGenerics<T> {
    private final T element;

    public MyGenerics(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        MyGenerics<String> myGenerics= new MyGenerics<>("안녕? 제네릭?");
        MyGenerics<Integer> myGenerics2= new MyGenerics<>(155830573); // 오토박싱
    }
}
```



- 제네릭을 사용하지 않고, 다양한 종류의 타입을 다룬다면 `MyGenerics<T>`를 `MyGenerics`로 변경하고, 각 필드, 파라미터, 리턴형식에 있는 `T`를 `Object`로 변경하고, 값을 꺼낼 때 원하는 형식으로 `형변환`을 해줘야 할 것이다.

- `MyGenerics<T>`에서 `T`를 type variable라고 하며, 다른 문자를 사용해도 상관은 없다.

  

## 제네릭스의 제한

- `static` 멤버의 `타입 변수 T`를 사용할 수 없음

  - `T`는 `인스턴스 변수`로 간주되기 때문이다.
  - 대입된 타입의 종류에 관계없이 동일한 것이어야 함.

- 제네릭 타입의 `배열`을 생성하는 것도 허용되지 않음. (<u>불가능은 아님</u>)

  - 제네릭 배열 타입의 참조변수를 선언하는 것은 가능

  - `new 연산자` 는 `컴파일` 시점에 `타입 T`가 무엇인지 정확히 알아야 한다.

  - `new` 대신`Reflection API`의 `newInstance()`활용

    ```java
    T[] tempArr = new T[itemArr.length]; // 에러, 지네릭 배열 생성불가
    ```

    

## 제네릭 주요 개념



### 바운디드 타입(bounded type parameter)

- 제한된 지네릭 클래스
- 타입 매개 변수 `T` 에 지정할 수 있는 `타입의 종류`를 제한 하는 방법

```java
public class MyGenerics<T extends SanHee> {
    private final T element;

    public MyGenerics(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }
}
```

```java
...
    
MyGenerics<SanHee> myGenerics= new MyGenerics<>(new SanHee());
// MyGenerics<Integer> myGenerics2= new MyGenerics<>(155830573); // ⛔ 컴파일 오류
```



### 와일드 카드

- 지네릭 타입이 다른 것으로는 `오버로딩` 이 성립하지 않아,  `메서드 중복 정의`로 컴파일 에러가 발생한다.
- 위와 같은 상황을 해결하기 위해 고안 된것이 `와일드 카드` 이다.

- 와일드 카드는 기호 `?` 로 표현하고, 어떠한 타입도 될 수 있다.
- 와일드 카드는 파라미터 변수, 필드 또는 지역변수의 타입 등 다양한 상황(때때로 리턴 타입에도 사용할 수 있음.)에서 사용할 수 있다
- 와일드 카드는 제네릭 메서드 호출, 제네릭 클래스 인스턴스 생성 또는 super 타입의 `타입 인자`로는 사용될 수 없다.

| 와일드 카드 예시 |                             설명                             |
| :--------------: | :----------------------------------------------------------: |
|  <? extends T>   |      와일드 카드의 `상한 제한`, T와 그 `자손`들만 가능       |
|   <? super T>    |      와일드 카드의 `하한 제한`, T와 그 `조상`들만 가능       |
|       <?>        | 제한 없음, 모든 타입이 가능<br /><? extends Object> 와 동일하다. |



## 제네릭 메소드 만들기

- 메서드의 선언부에 지네릭 타입이 선언된 메서드를 지네릭 메서드라 함.

```java
- Collections.class

...
    
@SuppressWarnings("unchecked")
public static <T extends Comparable<? super T>> void sort(List<T> list) {
    list.sort(null);
}
```

- **static 멤버에 타입 매개 변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능**
  - ❓ 이 부분 아직 완벽히 이해가 안됨
- 🛑 지네릭 메서드를 호출 할 때, 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략 할 수 없다.

- ( 📣 예제 추가할 수 있으면, 추가하기 )



## Erasure(지네릭 타입의 제거)

- 컴파일 상태에만 제약 조건을 적용하고, 런타임에는 타입에 대한 정보를 소거하는 프로세스
- 컴파일러는 제네릭 타입을 이용해서 소스파일을 체크하고, 필요한 곳에 `형변환`을 넣어준뒤 `제네릭 타입`을 제거한다.
  - 즉, 컴파일 된 파일(*.class)에는 `제네릭 타입`에 대한 정보가 없음.
- 이렇게 하는 이유?
  - 제네릭이 도입되기 이전의 소스 코드와의 호환성을 유지하기 위해서임.



---

#### Reference

- https://sujl95.tistory.com/73
- https://rockintuna.tistory.com/102
- 자바의 정석