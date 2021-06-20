# Reflection

- 런타임 시 클래스, 인터페이스, 필드, 메서드 등의 속성을 검사하거나 조작할 수 있는 자바 프로그래밍 언어의 기능

  - 주로 컴파일 타임에 이름을 모를 때 사용하기 좋다.
  - 라이브러리 추가 없이 사용할 수 있다. 
    - 리플렉션 API는 ` java.lang.reflect` 패키지에 있는 클래스와 와 `java.lang.Class` 클래스에 있는 메서드를 사용하여 구현할 수 있다.
      - java.lang.Class의 메서드: `getName(), getSuperclass(), getInterfaces(), getModifiers()`
  - 리플렉션을 사용하여 객체 초기화, 메서드 호출, 필드 값 설정 등을 할 수 있다.
    - 클래스의 private 멤버도 조작할 수 있다.
  - 성능 저하, 보안 취약성 등의 오버헤드가 발생할 수 있다.

  ![reflection](https://media.geeksforgeeks.org/wp-content/cdn-uploads/reflection.png)



## Example

아래와 같은 Person 클래스가 있다고 하자.

```java
public class Person {
    private String name;
    private int age;
}
```

리플렉션을 이용하면 Object 타입을 참조형으로 Person 클래스를 선언하더라도 `person.getClass().getDeclaredFields()` 메서드를 호출하여 Person 클래스의 필드를 가져올 수 있다.

```java
@Test
public void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
    Object person = new Person();
    Field[] fields = person.getClass().getDeclaredFields();

    List<String> actualFieldNames = getFieldNames(fields);

    assertTrue(Arrays.asList("name", "age")
      .containsAll(actualFieldNames));
}

private static List<String> getFieldNames(Field[] fields) {
    List<String> fieldNames = new ArrayList<>();
    for (Field field : fields)
      fieldNames.add(field.getName());
    return fieldNames;
}
```









---



***Source***

- https://www.oracle.com/technical-resources/articles/java/javareflection.html

- https://www.baeldung.com/java-reflection

- https://www.geeksforgeeks.org/reflection-in-java/

- https://www.guru99.com/java-reflection-api.html