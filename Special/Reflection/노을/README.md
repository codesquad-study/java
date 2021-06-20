# Java Reflect란?

- 동적으로 이름(String)을 이용하여 Java class를 다루는 방법.
- Java class의 이름을 이용하여 class를 메모리에 불러와서 class의 객체를 생성
- class의 method나 field를 이름과 signiture를 이용하여 획득한 다음, 함수 호출 또는 값의 설정 및 획득.

# Reflection과 Annotation Processor

![](https://images.velog.io/images/san/post/a2830ae5-ede5-49f6-a08e-6dd626c0e4be/image.png)
- 사진출처: https://www.youtube.com/watch?v=7pZq35Cg3D4

- 내가 이해한 건 간단하게 Reflection은 런타임에 클래스 조작 Annotation Processor은 컴파일 타임에 클래스 파일 생성/추가인데 정확히 모르겠다.


# Reflection 관련 메소드

### Class class
- java의 class 파일을 나타내는 class로 Class.forName()과 같은 함수를 이용하여 Class를 얻어옴.
- Class의 newInstance() 함수를 이용해 객체를 생성.
  - 생성자를 이용하여 생성하고자 하는 경우, getConstructor()를 이용하여 Constructor를 얻어온 다음, Constructor의 newInstance()함수를 이용하여 객체를 생성
### Method class
- class에 정의된 함수를 나타내는 class로서 Class 객체의 getMethod()를 호출하여 획득.
- getDeclaredMethod()로 protected나 private method도 얻어올 수 있음.
- invoke()로 함수 호출.

### Field class
- class에 정의된 filed를 나타내는 class로서 Class 객체의 getField()를 호출하여 획득.
- get(), set() 함수를 이용하여 값을 설정하거나 얻어올 수 있음.

### Class 획득
  - Class clazz = Class.forName("org.codesqaud.javastudy");

### 객체의 생성
- Object obj = clazz.newInstance();
- 생성자로 생성하고 싶다면
  - Constructor c = clazz.getConstructor(String.class, Integer.class);
    - Object obj = c.newInstance("test",1);

### Annotation
 - `@이름` 으로 정의돼 class, method, field 등에 meta data를 정의하여 사용하는 개념
 - ❓Annotation을 상속 받아서 정의한다.
 - Class의 getAnnotation()으로 정의된 Annotation을 얻어올 수 있음.

### Method의 획득
- `Public 특정 메서드` 
  - Method method = clazz.getMethod("myMethod",String.class, Integer.class);
- `Private 특정 메서드`
  - Method method = clazz.getDeclaredMethod("myPrivateMethod", String.class, Integer.class);
- `모든 메서드`
  - Method[] methods = clazz.getMethods();
  - Method[] methods = clazz.getDeclaredMethod();
  
  
### Method의 호출
 - method.invoke(myClassObject, "param1", 10); // instance 

### method의 호출
 - method.invoke(null, "param1", 10); // static method 호출
 - private method 호출.
    - method.setAccessible(true);
      - method.invoke(myClassObject,"param1",10);

### Method의 정보
- getName() - method의 이름
- getModifiers() - public, private, static, final 등의 정보
- getParameterTypes() - parameter의 class[]
- getReturnType() - return 값의 class


### Annotation
 - getAnnotation()
 - getParameterAnnotations()  
   - 파라미터에 정의된 애노테이션 가져오기?❓


# 의존성 주입 예제
- https://blog.naver.com/swoh1227/222229853664

## 프로젝트 구조
![](https://images.velog.io/images/san/post/788461a9-401b-47a4-9dd2-d156585941a8/image.png)


## @DepandancyInjection

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DependancyInjection {
    // 자동 생성할 클래스 타입. 기본 타입은 Object
    public Class<?> value() default Object.class;
}

```

## Node.java
```java
public class Node {
    public Node() {
    }

    public void print() {
        System.out.println("Hello world");
    }
}

```


## Abstract.java

```java
public class Abstract {
    public Abstract() {
        try {
            // 멤버 변수를 취득
            for (Field field : AnnotationExample.class.getDeclaredFields()) {
                // DependancyInjection 어노테이션을 취득
                DependancyInjection anno = field.getDeclaredAnnotation(DependancyInjection.class);

                if (anno != null) {
                    // 접근 제한자 무시
                    field.setAccessible(true);
                    // value 함수 값 취득
                    Class<?> clz = anno.value();
                    Constructor<?> constructor;

                    if (clz == Object.class) {
                        // 멤버 변수의 타입을 취득
                        clz = field.getType();
                    }

                    constructor = clz.getConstructor();
                    // 값에 넣는다.
                    field.set(this, constructor.newInstance());
                }

            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
```

## AnnotationExample.java


```java
public class AnnotationExample extends Abstract {

    // 의존성 주입 어노테이션을 멤버 변수에 설정
    @DependancyInjection
    private Node node1;

    // 의존성 주입 어노테이션이 없는 멤버 변수
    private Node node2;

    // 출력 함수
    public void print() {
        if (this.node1 != null) {
            this.node1.print();
        } else {
            System.out.println("nodel1 null");
        }

        if (this.node2 != null) {
            this.node1.print();
        } else {
            System.out.println("node2 null");
        }

    }

    // 실행 함수
    public static void main(String[] args) {
        // Example 인스턴스 생성
        AnnotationExample ex = new AnnotationExample();
        // 함수 호출
        ex.print();

    }


}

```

![](https://images.velog.io/images/san/post/d141677e-4149-42c3-bc1a-9b21de5d28a3/image.png)




## Reference

- https://blog.naver.com/swoh1227/222229853664
- https://www.youtube.com/watch?v=7pZq35Cg3D4