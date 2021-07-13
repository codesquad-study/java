# Week14.제네릭

# 학습할 것 (필수)

- 제네릭 사용법
- 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
- 제네릭 메소드 만들기
- Erasure

<br><br>

## 1. 제네릭 사용법

- JDK 1.5 에서 처음 도입한 개념이며 파라미터화된 타입을 의미한다. 즉, 타입(type)이 메서드, 클래스, 인터페이스의 매개변수가 될 수 있다는 의미이다. 제네릭을 사용함으로써, `다른 데이터 타입의 역할할 수 있는 클래스를 생성`할 수 있다는 의미이다.

- `generic entity` : 파라미터화한 타입으로 작동하는 클래스, 인터페이스를 일컫는 말이다.

```java
class Box<T> {
  private T item;
  
  public Box(T item) {
    this.item = item;
  }
  
  // toString override method
}

class Main {
    public static void main(String[] args) {
      	/**
      	 * 1. generic 사용 예시
      	 */	
      
        //item은 데이터 타입이 String이면서, String 역할을 한다.
      	Box<String> stringBox = new Box<String>("test1");
        System.out.println(stringBox);

      	//item은 데이터 타입 integer이면서, Integer 역할을 한다.
        Box<Integer> integerBox = new Box<Integer>(1); 
        System.out.println(integerBox);     	
    }
}

//결과
//Box{item=test1}
//Box{item=1}
```

<br>

### (1) 제네릭 명칭

``` java
class Box<T> {...}
```

- Box<T> : 제네릭 클래스 (Generic class)
- Box : 원시 타입(Primitive Type)
- <T> : 타입 변수 또는 타입 매개변수(Type parameter)

<br>

### (2) 제네릭의 장점

1. `type-safety` 

   - 의도하지 않은 타입 객체의 저장을 막고 객체 조회 시, 다른 타입으로 형변환하는 것을 막는다.

   - **Object의 경우**, 모든 클래스의 조상 클래스이기 때문에 모든 객체를 참조할 수 있어 <u>**type-safe하지 않다**</u>.

2. `code-reuse`
   - `Compile 단계`에서 Generic type을 이용해서 **타입체크**, **형변환**이 이루어지므로 타입체크, 형변환과 관련된 코드를 중복해서 처리할 필요가 없다.

```java
class GenericBox<T> {
  private T item;
  
  public Box(T item) {
    this.item = item;
  }
  // setter method + toString override method
}

class ObjectBox {
    private Object item;

    public ObjectBox (Object item) {
        this.item = item;
    }
  
  	// setter method + toString override method  
}

class Main {
    public static void main(String[] args) {
      	/**
      	 * 1. type-safety 예시
      	 */	
        ObjectBox objectBox = new ObjectBox(new Object());
        objectBox.setItem(new Object());

        GenericBox<String> stringGenericBox = new GenericBox<String>("");
      	//'setItem(java.lang.String)' in 'GenericBox' cannot be applied to '(java.lang.Object)'
        //stringGenericBox.setItem(new Object());
    }
}

//결과
//Box{item=test1}
//Box{item=1}
```

<br><br>

### (3) 제네릭스의 제한(Bounded Type)

- 목적 : 인스턴스별로 다르게 동작하도록 하기 위한 방법.

- 규칙

  1. static 멤버는 타입 변수 T를 사용할 수 없다.

     (static은 대입된 타입의 종류에 관계없이 동일한 것이어야 하기 때문에)

  2. 제네릭 타입의 배열 생성을 허용하지 않는다. -> 사용하고 싶은 경우, `Reflection API의 newInstance()`사용

     (`new 연산자`는 컴파일 시점에 **타입 변수T**가 정확히 무엇인지를 알아야 한다.)

<br>

### (4) 제네릭 클래스의 객체 생성과 사용

[**전제 : Fruit(조상) <- Apple, Grape(자손)**]

1. 객체 생성 시, 타입 변수는 일치해야 한다.

   ```java
   Box<Fruit> box = new Box<Fruit>();
   Box<Fruit> box = new Box<>();//JDK 1.7부터 생략 가능
   ```

   <br>

2. `상속 관계`일 경우, `하위 클래스로 일치`해서 선언해야 한다.

   ```java
   Box<Fruit> box = new Box<Apple>(); //(X)
   Box<Apple> box = new Box<Apple>(); //(O)
   ```

   <br>

3. 타입T가 `조상 클래스(Fruit)`인 경우,  요소 추가 가능

   ```Java
   Box<Fruit> fruitBox = new Box<fruit>();
   
   //void add(T item);
   fruitBox.add(new Fruit()); //(O)
   fruitBox.add(new Apple()); //(O)
   ```

   <br>

<br>

## 2. 제네릭 주요 개념 (바운디드 타입, 와일드 카드)

### (1) 제한된 제네릭 클래스

1. <u>**타입 변수 <T>만 사용했을 때의 문제점**</u>

   - 타입 변수 <T>만 사용할 경우, 한가지 변수만 할당할 수 있지만, 다른 타입 변수를 받아올 수 있다.

     (원하는 연관성 있는 데이터만 받아올 수 있도록 제한할 수 없었다...)

   <br>

2. <u>**extends를 사용하면 특정 클래스만 대입이 가능**</u>

   - **`extends`**를 사용하면 **특정 타입의 자손들만 대입**할 수 있도록 제한할 수 있다.

     ```java
     <T extends superClassName>
     ```

     <br>

   - **`interface`**를 사용해도 implements가 아닌 **extends를 사용**한다.

     <br>

   - `class를 상속받고 interface를 구현한 클래스`여야 한다면, **`&`**을 붙이도록 하자!

     ```java
     <T extends superClassName & Interface>
     ```

     <br>

   ```java
   /**
    * 1. 타입 변수 <T>만 사용했을 때의 문제점.
    */
   
   //과일박스에 장난감을 넣겠다고 선언?
   FruitBox<Toy> fruitBox = new FruitBox<Toy>();
   
   //과일박스에 장난감이 들어가는 불상사가 발생.
   fruitBox.add(new Toy());
   ```

   

   ```java
   /**
    * 2. extends를 사용하면 특정 타입의 자손들만 대입 가능
    */
   
   // [T extends Fruit] : Fruit의 하위 클래스만 대입할 수 있다.
   class FruitBox<T extends Fruit> {
     private List<T> List = new ArrayList<>();
   }
   
   //하위 클래스는 대입이 가능.
   fruitBox.add(new Apple());// (O)
   fruitBox.add(new Graph());// (O)
   
   //연관된 하위 클래스가 아니면 대입 불가능.
   fruitBox.add(new Toy());// (X)
   ```

   ```java
   /**
    * 3. & : class를 상속받고 interface를 구현한 클래스에 부여하기
    */
   class FruitBox<T extends Fruit & Eatable> {...}
   ```

   <br>

### (2) 와일드카드(Wild Card)

1. **와일드카드?**

   - `?` : unknown type을 의미한다. 즉, 어떤 값이 들어와도 괜찮다는 의미이다. 

     ```
     1. <? extends T> : T와 그 자손이면 누구든 가능 (와일드 카드의 상한 제한)
     2. <? super T> : T와 그 조상이면 누구든 가능 (와일드 카드의 하한 제한)
     3. <?> : 제한 없음, 모든 타입이 가능
     ```

   - 매개변수(parameter), 필드(field), 지역변수(local variable), 반환 값(return type)와 같은 다양한 상황에서 사용됨.

     <br>

2. **도입 이유?**

   - 제네릭으로 구현된 메서드의 경우, 선언된 타입으로만 매개변수를 입력해야 했다.

   - 자손 클래스 혹은 부모 클래스, 어떤 타입이 상관없는 경우와 같이 다양한 상황에 대응하지 못하였다.

     <br>

3. **와일드카드의 이점**

   - static메서드에 타입 매개변수 사용이 가능

     ```java
     public Juice makeJuice (FruitBox <? extends Fruit> box) {
       	String temp = "";
       	
       	for(Fruit f : box.getList()) {
           	tmp += f + " ";
         }
       	return new Juice(temp);
     }
     ```

     <br>

4. 사용 예시

   - static 옆에 있는 <T> 메서드에 선언된 제네릭 타입

     ```java
     static <T> void sort (List<T> list, Comparator <? super T> c) {...}
     ```

     - <T> : 메서드에 선언된 Generic Type.
     - <? super T> : 하한 제한을 두지 않으면, 하위의 Comparator를 작성

   <br>

   - <? extends T> : Upper Bounded Wildcard

     ```java
     import java.util.Arrays;
     import java.util.List;
       
     class WildcardDemo
     {
         public static void main(String[] args)
         {
               
             //Upper Bounded Integer List
             List<Integer> list1= Arrays.asList(4,5,6,7);
               
             //printing the sum of elements in list
             System.out.println("Total sum is:"+sum(list1));
       
             //Double list
             List<Double> list2=Arrays.asList(4.1,5.1,6.1);
               
             //printing the sum of elements in list
             System.out.print("Total sum is:"+sum(list2));
         }
       
         private static double sum(List<? extends Number> list) 
         {
             double sum=0.0;
             for (Number i: list)
             {
                 sum+=i.doubleValue();
             }
       
             return sum;
         }
     }
     ```

     <br>

   - < ? super T>  : Lower Bounded Wildcard

     ```java
     import java.util.Arrays;
     import java.util.List;
       
     class WildcardDemo
     {
         public static void main(String[] args)
         {
             //Lower Bounded Integer List
             List<Integer> list1= Arrays.asList(4,5,6,7);
               
             //Integer list object is being passed
             printOnlyIntegerClassorSuperClass(list1);
       
             //Number list
             List<Number> list2= Arrays.asList(4,5,6,7);
               
             //Integer list object is being passed
             printOnlyIntegerClassorSuperClass(list2);
         }
       
         public static void printOnlyIntegerClassorSuperClass(List<? super Integer> list)
         {
             System.out.println(list);
         }
     }
     ```

     <br>

## 3. 제네릭 메서드

- 정의 : 메서드 선언부에 제네릭 타입이 선언된 메서드

  (Ex) Collections.sort

  ```java
  public static <T extends Comparable <? super T>> void sort (List<T> list) {...}
  ```
  <br>

- 주의! : `제네릭 클래스의 타입 매개변수`와 `제네릭 메서드에 선언된 타입 매개변수`는 별개이다!

  ```java
  class FruitBox <T> {
    static <T> void sort (List<T> list, Comparator<? super T> C){...}
  }
  ```

  <br>

- static 메서드에 제네릭 타입을 선언하고 사용하는 것 가능! (static 멤버에는 타입 매개변수 사용 불가능!)

  ```java
  static Juice makeJuice (FruitBox <? extends Fruit> box) {...}
  
  //제네릭 메서드
  static <T extends Fruit> Juice makeJuice (FruitBox <T> box) {...}
  ```

  <br>

- 클래스 변수 혹은 참조 변수일 때는 생략 금지!
  ```java
  this.<Fruit>makeJuice(fruitBox);
  Juicer.<Fruit>makeJuice(fruitBox);
  ```

  <br>

- 한번 내용 파악을 해보자!

  ```java
  public static <T extends Comparable <? super T>> void sort (List<T> list) {...}
  ```

  1. `List<T> list` : 타입 변수 T를 요소로 하는  List 매개변수로 허용한다.

  2. `T extends Comparable` : 타입 변수 T는 Comparable을 구현하는 클래스여야 하며,

  3. `<? super T>` : T 또는 그 조상을 비교하는 Comparable이어야 한다.

     ex) Person(부모) <- Student(자손)이라면, <? super T>는 Student, Person, Object이다.

<br><br>

## 4. Erasure

`제네릭의 타입 소거(Generics Type Erasure)`

- `Erasure` : 원소 타입을 컴파일 타임에서만 검사하고, 런타임에는 해당 타입 정보를 알 수가 없다.

  (즉, 컴파일 상태에만 제약 조건을 적용, 런타임에는 타입에 대한 정보를 소거하는 프로세스이다.)

<br>

**제네릭 타입의 제거 과정**

1. 제네릭 타입의 경계(bound)를 제거한다.

   - 제네릭 타입이 <T extends Fruit>라면 <u>T는 Fruit로 치환</u>된다.

   - <T>인 경우, <u>T는 Object로 치환</u>된다.

     - Object로 변경하는 경우, unbounded된 경우를 뜻한다.

       **<Before>**

       ```java
       class Box<T extends Fruit> {
         void add(T t) {
           ...
         }
       }
       ```

       **<After>**

       ```java
       class Box {
         	void add(Fruit t) {
             ...
           }
       }
       ```

       <br>

   2. 제네릭 타입을 제거한 타입이 일치하지 않으면, 형변환을 추가한다.

      - List의 get()은 Obejct 타입을 반환하므로 형변환이 필요하다.

        **<Before>**

        ```java
        T get(int i) {
          return list.get(i);
        }
        ```

        **<After>**

        ```java
        Fruit get(int i){
        	return (Fruit)list.get(i);
        }
        ```

        <br>

      - `와일드카 카드`가 포함된 경우, **적절한 타입으로 형변환이 추가**된다.

        **<Before>**

        ```java
        static Juice makeJuice(FruitBox<? extends Fruit> box){
        	String tmp = "";
        	for(Fruit f : box.getList()) temp += f + " ";
        	return new Juice(temp);
        }
        ```

        **<After>**

        ```java
        static Juice makeJuice(FruitBox box){
        	String tmp = "";
        	Iterator it = box.getList().iterator();
        	while(it.hasNext()){
        		tmp += (Fruit)it.next() + " " ;
        	}
        	return new Juice(temp);
        }
        ```

        <br><br>

## Reference

- [자바의 정석] chapter12. 지네릭스
- [geeksforgeeks] Generic in Java : https://www.geeksforgeeks.org/generics-in-java/
- [geeksforgeeks] Bounded types with generics in Java : https://www.geeksforgeeks.org/bounded-types-generics-java/?ref=rp
- [geeksforgeeks] Wildcards in Java : https://www.geeksforgeeks.org/wildcards-in-java/

