# 애노테이션

- `JDK 5` 부터 추가된 기능
- 소스코드에 추가적인 정보를 제공하는 `메타 데이터`
- 비즈니스 로직에 직접적인 영향을 주지 않음.
- 메타데이터 정보에 따라서 실행 흐름을 변경할 수 있는 코딩이 가능해 깔끔한 코딩이 가능해질 수 있음.  
  - ex) FunctionalInterface, Lombock, Butterknife
# 애노테이션 용도
1. 컴파일러를 위한 정보 제공 
   - ex) @Override..
2. 런타임에 리플렉션을 이용해 기능을 추가❓❓
   - ex) 스프링 프레임워크의 @Controller..
3. 컴파일 과정에서 어노테이션 정보로 부터 코드를 생성
   - ex) Lombok의 @Getter/@Setter


# 애노테이션 정의하는 방법
-  `@` 을 제외한 인터페이스 정의와 동일
- 애노테이션은 상속이 불가능함

```java

@메타 애노테이션
public @interface 애노테이션 이름{
 Type 요소이름();

}

```

# 메타 애노테이션
- 어노테이션에 대한 부가적인 기능이나 제약조건을 정의하기 위한 어노테이션
- @Target, @Retention, @Inherited, @Documented, @Native, @Repeatable...

# 애노테이션의 요소
- 메서드 형태로 선언
- 기본형, String, 배열, Enum, 다른 애노테이션, 클래스들이 올 수 있음.
- 반환값이 있고, 매개변수가 없는 추상 메서드의 형태


# @Retention
- 애노테이션이 유지되는 기간을 지정하는데 사용

| 유지 정책 |                             의미                             |
| :-------: | :----------------------------------------------------------: |
|  SOURCE   | 소스 파일에만 존재하고 클래스 파일에는 없음<br /><br />ex) @Override, @SuppressWarnings |
|   CLASS   | 클래스 파일에만 존재하고, 실행시에는 사용 불가. <br />(@Retention을 지정하지 않을 경우, 기본 값 ) |
|  RUNTIME  | 클래스 파일에 존재하고, 실행 시점에도 존재함.<br /><br />ex) @FunctionalInterface, @Deprecated |

- 유지 정책을 RUNTIME으로 하면, 애플리케이션이 실행 중일 때, 리플렉션을 이용해 애노테이션의 정보를 읽어서 처리해줄 수 있음.


# @Target
- 애노테이션이 적용가능한 대상을 지정하는데 사용
- 사용예시
```java
@Target({METHOD, PARAMETER})
```

|    대상 타입    |              의미               |
| :-------------: | :-----------------------------: |
| ANNOTATION_TYPE |           애너테이션            |
|   CONSTRUCTOR   |             생성자              |
|      FIELD      |    필드(멤버변수, enum 상수)    |
| LOCAL_VARIABLE  |            지역변수             |
|     METHOD      |             메서드              |
|     PACKAGE     |             패키지              |
|    PARAMETER    |            매개변수             |
|      TYPE       | 타입(클래스, 인터페이스, enum)  |
| TYPE_PRARMETER  |      타입 매개변수(JDK1.8)      |
|    TYPE_USE     | 타입이 사용되는 모든 곳(JDK1.8) |


# @Documented
- 해당 어노테이션을 `javadoc`에 포함시킵니다.
- [참고링크1: Javadoc 만들기 (작성법)](http://www.devkuma.com/books/pages/1232)
- [참고링크2: 인텔리제이에서 Javadoc 생성하기](https://log-laboratory.tistory.com/306) 


# 애노테이션 프로세서

- 자바 컴파일러 플러그인의 일종으로 어노테이션에 대한 코드베이스를 검사, 수정, 생성하는 역할을 가지는 플러그인

>- `컴파일 타임`에 애노테이션을 스캔하고 처리하기 위해 `javac`에서 확장해서 사용하는 도구라고 볼 수 있음.
- 특정 애노테이션을 위한 자신만의 애노테이션 프로세스를 등록할 수 있음.
-  Java코드 혹은 컴파일된 Java Bytecode를 입력으로 받아서, 파일을(대개는 .java 파일) 출력으로 생성
- 즉, 자바 코드를 생성해낼 수 있고, `.java` 파일에 담기게 됨.
	- 메서드를 추가하기 위해, 이미 존재하는 자바 클래스파일을 조작X
- 애노테이션 프로세서는 ~~기존 파일을 변경하는 것이 아니라~~, `새로운 파일을 생성`
- 애노테이션 프로세서의 사용예
  - QueryDSL, JPA, Lombok, MapStruct
  
- 본문 출처: https://velog.io/@dion/백기선님-온라인-스터디-12주차-Annotation

# 애노테이션 프로세서 API

>- 애노테이션 프로세서는 여러 라운드로 수행
> - 컴파일러가 소스파일에서 애노테이션 검색
> - 애노테이션에 적합한 애노테이션 프로세서 선택
> - 각각의 애노테이션 프로세스는 그것과 일치하는 애노테이션이 발견되었을 때 호출.
> - 각 라운드에서 만들어진 .java 파일을 입력으로 해서 새로운 라운드가 시작됨.
> - (새로운 파일이 더 이상 생겨나지 않을 때까지 애노테이션 프로세서 수행)
> <BR>
>- 애노테이션 프로세서 API는 javax.annotation.processing 패키지에 있으며, 우리가 구현해야 할 주요 인터페이스는 Processor 인터페이스이며, 이를 부분적으로 구현한 추상 클래스인 AbstractProcessor 클래스

- 본문 출처: https://velog.io/@dion/백기선님-온라인-스터디-12주차-Annotation


# 애노테이션 프로세서 만들기 실습
 - 출처: https://www.youtube.com/watch?v=FPoRoSvJJNs

### 1. 먼저 아래와 같은 구조로 프로젝트 생성
 ![](https://images.velog.io/images/san/post/ebae2b06-19af-4dbe-96fc-3a17abca3581/image.png)
### 2. `javax.annotation.processing.Processor` 텍스트 파일에 
  애노테이션 프로세스의 `FQCN`인 `todo.TodoProcessor` 작성
### 3. TodoProcessor.java에 아래 코드 삽입
```java
@SupportedAnnotationTypes("todo.Todo")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TodoProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();

        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                Todo todo = element.getAnnotation(Todo.class);
                messager.printMessage(Diagnostic.Kind.WARNING, "Task : " + todo.value() + "at "+ element);
            }
        }
        return true;
    }
}
```

### 4. Todo 애노테이션 작성
```java
public @interface Todo {
    String value() default "default";
}
```

### 5. jar 파일 생성

![](https://images.velog.io/images/san/post/c7b4f352-a85a-4e57-8441-1466a28a7cb6/image.png)
![](https://images.velog.io/images/san/post/fe2e150b-68e3-4b6e-b8c8-2dbfef2aa7a9/image.png)
![](https://images.velog.io/images/san/post/2fd5396f-6025-4fda-923a-44115b0c9b5a/image.png)

- 리소스 선택
![](https://images.velog.io/images/san/post/29f77f67-0b59-44b3-a5a6-c5a06c381532/image.png)

![](https://images.velog.io/images/san/post/db6b0e28-8f27-49f5-abbf-4449570b16a2/image.png)

옵셔널한 경우지만 소스파일도 추가할 수 있음.

![](https://images.velog.io/images/san/post/8e4eccd6-6ecd-4cac-a78c-cdb9f6b6a346/image.png)


![](https://images.velog.io/images/san/post/2a9b680e-6711-4c2e-980b-cee6856d42cf/image.png)


### 7. jar 파일찾기
 - `find . -name "*.jar"`
![](https://images.velog.io/images/san/post/05d9b4a2-0cef-4959-88ab-7b957e471756/image.png)



## 커스텀 애노테이션 프로세서 사용

### 1. 새로운 프로젝트 생성
![](https://images.velog.io/images/san/post/0aa40829-0fe7-4dd6-aa53-62945e03950d/image.png)

### 2. `프로젝트 폴더/lib`로 이전에 생성한 jar파일 복사

![](https://images.velog.io/images/san/post/e0b2ab25-452b-4765-b0c7-ef3ee9d0db68/image.png)


### 3. TodoTest.java 작성
```java
@Todo("Need urgent implementation")
public class TodoTest {

    @Todo
    public void myMethod(){

    }
}

```

### 4. build gradle 수정
   - lib 안에 있는 jar 파일 등록 과정

![](https://images.velog.io/images/san/post/4f360d1b-ecc5-4d69-8de1-614c90c71dbd/image.png)

### 5. Build
- Todo 애노테이션이 따로 없지만

![](https://images.velog.io/images/san/post/37b7ed53-2c44-4ff9-8061-2a7eda82a3e1/image.png)

### 6. ByteCode
- 새로운 프로젝트에서 정의하지 않은 @Todo 애노테이션이 생성된 것을 확인할 수 있음.
  ![](https://images.velog.io/images/san/post/6601069b-1efb-4edc-b6d1-b2a869b37ce6/image.png)
  
  

## Reference

- https://www.notion.so/37d183f38389426d9700453f00253532
- https://github.com/kwj1270/TIL_JAVA/blob/master/12%20%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98.md
- https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-12%EC%A3%BC%EC%B0%A8-Annotation
  
- https://blog.naver.com/swoh1227/222229853664
- https://www.youtube.com/watch?v=FPoRoSvJJNs