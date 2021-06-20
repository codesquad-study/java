

# Special : Reflection



Reflection은 실행 중인 자바 프로그램이 

1. 클래스 속성 값에 대한 분석 및 검사를 진행할 수 있도록 한다.

2. 런타임에 동적으로 클래스 정보에 접근해서 메소드, 필드 등 프로그램의 내부 속성에 접근하고 사용할 수 있도록 한다

3. 확장 가능한 인스턴스를 만들어 외부 사용자 정의 클래스를 작성 할 수 있도록 한다



### Reflection 사용 방법

String 클래스에 정의된 메소드 정보를 가져오고 싶다고 가정했을 때 

1. Retrieving Class Object

   - `java.lang.Class`  객체 가져오기 -> Class 객체에서 제공하는 메소드를 통해 클래스 정보에 접근할 수 있다.
     - .class syntax
     - Object::getName
     - Class::getClass

   ```java
   Class c = Class.forName("java.lang.String");
   ```

2.  Discovering Class members

   - `getDeclaredMethods`  메소드를 호출해서 클래스에 의해 정의된 메소드 객체를 추출.

   ```java
   Method m[] = c.getDeclaredMethods();
   ```

3. Reflection API를 사용해서 얻어온 정보들을 사용한다.

   ```java
   System.out.println(m[0].toString());
   ```



### Inspecting a Class

```java
Class woodyClass = woody.getClass(); // Class를 받아왔다는 것에 의의를.. woody가 어디서 왔는지는 중요 ㄴㄴ

// class name - 클래스 이름
String name = woodyClass.getName();

// class modifier - 제어자
int m = woodyClass.getModifiers();
bool isPublic = Modifier.isPublic(m);
bool isAbstract = Modifier.isAbstract(m);
bool isFinal = Modifier.isFinal(m);

// is interface - 인터페이스 여부
bool isInterface = woodyClass.isInterface();

// get interface implemented by this class - 우디 클래스가 상속받은 인터페이스
Class[] interfaces = woodyClass.getInterfaces();

// superclass - 부모 클래스 
Class superclass = woodyClass.getSuperClass();

```



### Member를 상속하는 대표적인 클래스들과 메소드들

- Oracle - [Members](http://www-inf.it-sudparis.eu/cours/java/javatutorial/reflect/member/index.html)

| Member      | Class API                                           | Inherited Members | Private Members |
| ----------- | --------------------------------------------------- | ----------------- | --------------- |
| Field       | getDeclaredField(), getDeclaredFields()             | NO                | YES             |
|             | getField(), getFields()                             | YES               | NO              |
| Method      | getDeclaredMethod(), getDeclaredMethods()           | NO                | YES             |
|             | getMethod(), getMethods()                           | YES               | NO              |
| Constructor | getDeclaredConstructor(), getDeclaredConstructors() | NO                | YES             |
|             | getConstructor(), getConstructors()                 | YES               | NO              |



뿐만아니라 by `getDeclaredFields()`), 어노테이션 정보(by `getDeclaredAnnotatio()`) 등 다양한 메소드들을 통해 클래스 속성 값들을 추출해올 수 있다.



### Reflection 사용

- 예시 작성 시 많은 참고가 되었던 [GeeksForGeeks](https://www.geeksforgeeks.org/reflection-in-java/) 👏👏👏

```java
class RelfectionTest
{
    // private field!!
    private String s;

    public RelfectionTest()  {  s = "This is a default value"; }

    public void printString()  {
        System.out.println(s);
    }
}
```



```java
public class CustomReflectionTester
{
    public static void main(String args[]) throws Exception
    {
        RelfectionTest obj = new RelfectionTest();

        // getting class object
        Class woodyClass = obj.getClass();
        System.out.println("The name of class is " +
                woodyClass.getName());

        // getting constructor
        Constructor constructor = woodyClass.getConstructor();
        System.out.println("The name of constructor is " +
                constructor.getName());


        // getting methods
        Method[] methods = woodyClass.getMethods();

        for (Method method:methods)
            System.out.println("The name of method is " + method.getName());


        Method printString = woodyClass.getDeclaredMethod("printString");

        // invokes the method at runtime
        printString.invoke(obj);

       // getting private field 👀
        Field field = woodyClass.getDeclaredField("s");

        field.setAccessible(true);
      
				// trying to reset the value
        field.set(obj, "reset the private field value");

        // invokes the method at runtime
        printString.invoke(obj);

    }
}
```



#### OUTPUT

```java
=== Class ===
The name of class is RelfectionTest
=== Constructor ===
The name of constructor is RelfectionTest
=== Method ===
The name of method is printString
The name of method is wait
The name of method is wait
The name of method is wait
The name of method is equals
The name of method is toString
The name of method is hashCode
The name of method is getClass
The name of method is notify
The name of method is notifyAll
=== Field ===
This is a default value
reset the private field value <- reflection을 통해 private 필드에 값을 reset!
```



### Reflecction 단점

1. **Performance Overhead**
   - reflection은 동적으로 수행이 되기 때문에 아무래도 reflection을 수행하지 않은 프로그램 보단 상대적으로 JVM의 퍼포먼스를 떨어트릴 수 있다(JVM 최적화가 힘들다고 한다). 때문에 performance-sensitive 한 프로그램에서는 빈번하게 사용하는 것은 좋지 않을 수 있다.
2. **Security Restrictions**
   - reflection은 엄격한 보안 시스템 아래서는 허용하지 않는 런타임 접근 권한을 요구하기 때문에 유의해야 한다. 
3. **Exposure of Internals**
   - reflection을 통해 직접 접근하기 힘든 private 필드 혹은 메소드에 접근이 가능해지면서 예상치 못한 사이드 이펙트가 발생 할 수 있다. 
   - 이 과정에서 추상화를 깨는 경우가 발생할 수도 있다 - data encapsulation
     - https://stackoverflow.com/a/16635211



### 어디에 사용되는거지? 🤔

intellij의 자동완성, jackson 라이브러리, Hibernate 등등 많은 프레임워크나 라이브러리에서 Reflection을 사용한다. 

디버깅 혹은 테스트 도구에서도 reflection을 통해 클래스를 탐색하고 private 멤버 혹은 클래스에 접근해서 사용자에게 편리함을 제공한다.



**Debuggers and Test Tools**

우리가 아는 Spring을 예로 들어보면, Spring Container의 BeanFactory가 reflection을 사용한다.

 Bean은 애플리케이션이 실행한 후 런타임에 객체가 호출될 때 동적으로 객체의 인스턴스를 생성하는데 이때 Spring Container의 BeanFactory에서 리플렉션을 사용한다.

**Spring Data JPA 에서 Entity에 기본 생성자가 필요한 이유**

Spring Data JPA 에서 Entity에 기본 생성자가 필요한 이유는 *동적으로 객체를 생성 할 때 Reflection을 활용하기 때문!*

Reflection API로 생성자의 인자 정보는 가져올 수가 없다. 때문에 기본 생성자가 반드시 있어야 객체를 생성할 수 있다. 기본 생성자로 객체를 생성을 한 뒤 Reflection을 통해 필드 값을 넣어준다. (setter가 없어도 값을 넣을 수 있는 이유!)



### Reference

https://docs.oracle.com/javase/tutorial/reflect/

https://slidetodoc.com/reflection-bibliografie-sun-the-java-tutorials-the-reflection/

https://woowacourse.github.io/javable/post/2020-07-16-reflection-api/