## 애노테이션 정의하는 방법

```java
@interface 애너테이션 이름 {
    타입 요소 이름(); // 애너테이션의 요소 선언
}
```

### 애너테이션 요소의 규칙

- 요소의 타입은 기본형, String, enum, 애너테이션, Class만 허용된다. (타입 매개변수 사용 불가)
- ()안에 매개변수를 선언할 수 없다.
- 예외를 선언할 수 없다.

### 커스텀 애너테이션

```java
import java.lang.annotation.*;

enum TestType {FIRST, FINAL}

@Retention(RetentionPolicy.RUNTIME)
        // 실행 시에 사용가능하도록 지정
@interface TestInfo {
    int count() default 1;

    String testedBy();

    String[] testTools() default "JaneUnit";

    TestType testType() default TestType.FIRST;

    Date testDate();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Date {
    String yymmdd();
}

@TestInfo(testedBy = "Jane", testDate = @Date(yymmdd = "210620"))
class AnnotationStudy {
    public static void main(String args[]) {
        Class<AnnotationStudy> annotationStudyClass = AnnotationStudy.class;

        TestInfo annotation = annotationStudyClass.getAnnotation(TestInfo.class);
        System.out.println("testedBy=" + annotation.testedBy());
        System.out.println("testDate=" + annotation.testDate().yymmdd());

        for (String str : annotation.testTools())
            System.out.println("testTools=" + str);

        for (Annotation a : annotationStudyClass.getAnnotations())
            System.out.println(annotation);
    }
}

```

```java
testedBy()=Jane
testDate().yymmdd()=210620
testTools=JaneUnit

@TestInfo(count=1, testType=FIRST, testTools=[JaneUnit], testedBy=Jane, testDate=@Date(yymmdd=210620))
```



## 마커 애너테이션

- 애너테이션 요소를 정의하지 않은 애너테이션

```java
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(status = STABLE, since = "5.0")
@Testable
public @interface Test {
}
```



## 메타 애너테이션

- 애너테이션을 위한 애너테이션
- Service 위에 붙어있는 애너테이션들은 다 메타에너테이션, ANNOTATION_TYPE으로 선언되어있거나 ANNOTATION_TYPE을 포함하는 TYPE으로 선언되어 있음

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```



## @Retention

- 애너테이션이 유지되는 주기를 나타낸다. 
- `@Retention`이 없다면 기본 유지정책은 CLASS로 설정된다.
- 유지 정책 (Retention Policy)

| 유지 정책 | 의미                                              |
| --------- | ------------------------------------------------- |
| SOURCE    | 소스 파일에만 존재한다. (클래스파일 x)            |
| CLASS     | 클래스 파일에 존재하며 실행시에는 사용할 수 없다. |
| RUNTIME   | 클래스 파일에 존재하며 실행시에 사용할 수 있다.   |

```java
package java.lang.annotation;

/**
 * Indicates how long annotations with the annotated type are to
 * be retained.  If no Retention annotation is present on
 * an annotation type declaration, the retention policy defaults to
 * {@code RetentionPolicy.CLASS}.
 *
 * <p>A Retention meta-annotation has effect only if the
 * meta-annotated type is used directly for annotation.  It has no
 * effect if the meta-annotated type is used as a member type in
 * another annotation type.
 *
 * @author  Joshua Bloch
 * @since 1.5
 * @jls 9.6.4.2 @Retention
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy value();
}
```

### SOURCE

- @Override: 컴파일러에게 메서드를 오버라이딩하는 것이라고 알린다.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

- @SuppressWarnings: 컴파일러가 보여주는 경고 메시지를 억제
  - unchecked: 지네릭스로 타입을 지정하지 않았을 때 발생
  - rawtypes: 지네릭스를 사용하지 않을 때 발생
  - varargs: 가변인자 타입이 지네릭 타입일 때 발생

```
@SuppressWarnings({"deprecation", "unchecked", "rawtypes", "varargs"})
```

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    /**
     * The set of warnings that are to be suppressed by the compiler in the
     * annotated element.  Duplicate names are permitted.  The second and
     * successive occurrences of a name are ignored.  The presence of
     * unrecognized warning names is <i>not</i> an error: Compilers must
     * ignore any warning names they do not recognize.  They are, however,
     * free to emit a warning if an annotation contains an unrecognized
     * warning name.
     *
     * <p> The string {@code "unchecked"} is used to suppress
     * unchecked warnings. Compiler vendors should document the
     * additional warning names they support in conjunction with this
     * annotation type. They are encouraged to cooperate to ensure
     * that the same names work across multiple compilers.
     * @return the set of warnings to be suppressed
     */
    String[] value();
}
```



### CLASS

- 클래스 파일에 애너테이션의 정보가 저장되지만, 클래스 파일이 JVM에 로딩될 때는 애너테이션에 대한 정보가 무시됨

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface NonNull {
}
```



### RUNTIME

- 실행 시 리플렉션을 통해 클래스 파일에 저장된 애너테이션의 정보를 처리할 수 있다.
- 지역 변수에 붙은 애너테이션은 컴파일러만 인식할 수 있기 때문에 런타임 애너테이션을 지역 변수에 붙이면 실행 시 인식할 수 없다.
- @FunctionalInterface: 컴파일러가 함수형 인터페이스를 올바르게 선언했는지 확인

```java
package java.lang;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {}
```



## @Target

- 어떤 대상에 애너테이션을 적용할 수 있는지 알려준다.

```java
package java.lang.annotation;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    /**
     * Returns an array of the kinds of elements an annotation type
     * can be applied to.
     * @return an array of the kinds of elements an annotation type
     * can be applied to
     */
    ElementType[] value();
}
```

- 적용 대상 종류

| 대상 타입       | 의미                              |
| --------------- | --------------------------------- |
| ANNOTATION_TYPE | 애너테이션                        |
| CONSTRUCTOR     | 생성자                            |
| FIELD           | 필드(멤버변수, enum상수)          |
| LOCAL_VARIABLE  | 지역변수                          |
| METHOD          | 메서드                            |
| PACKAGE         | 패키지                            |
| PARAMETER       | 매개변수                          |
| TYPE            | 타입(클래스, 인터페이스, enum)    |
| TYPE_PARAMETER  | 타입 매개변수 (JDK 1.8)           |
| TYPE_USE        | 타입이 사용되는 모든 곳 (JDK 1.8) |

```java
package java.lang.annotation;

public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE,

    /**
     * Module declaration.
     *
     * @since 9
     */
    MODULE
}

```



## @Documented

- 애너테이션에 대한 정보를 javadoc으로 작성한 문서에 포함시킴
- 기본 애너테이션 중 `@Override`와 `@SuppressWarning`을 제외하고는 모두 `@Documented`가 붙어있음

```java
package java.lang.annotation;

/**
 * If the annotation {@code @Documented} is present on the declaration
 * of an annotation type <i>A</i>, then any {@code @A} annotation on
 * an element is considered part of the element's public contract.
 *
 * In more detail, when an annotation type <i>A</i> is annotated with
 * {@code Documented}, the presence and value of annotations of type
 * <i>A</i> are a part of the public contract of the elements <i>A</i>
 * annotates.
 *
 * Conversely, if an annotation type <i>B</i> is <em>not</em>
 * annotated with {@code Documented}, the presence and value of
 * <i>B</i> annotations are <em>not</em> part of the public contract
 * of the elements <i>B</i> annotates.
 *
 * Concretely, if an annotation type is annotated with {@code
 * Documented}, by default a tool like javadoc will display
 * annotations of that type in its output while annotations of
 * annotation types without {@code Documented} will not be displayed.
 *
 * @author  Joshua Bloch
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
```



## 애노테이션 프로세서

- 컴파일 타임에 애노테이션의 정보를 참고하여 새로운 소스코드, 메타데이터, 다큐멘테이션 등을 만들어낼 수 있다.
- 등록 방법
  - META-INF > services > javax.annotation.processing.Processor 안에 AbstractProcessor 클래스를 상속받는 클래스의 FQCN(Full Qualified Class Name)을 작성하면 된다. 

### 롬복 뜯어보기

- `javax.annotation.processing.Processor`에 들어가면 아래와 같은 내용이 존재한다.

```java
lombok.launch.AnnotationProcessorHider$AnnotationProcessor
lombok.launch.AnnotationProcessorHider$ClaimingProcessor
```

- `AnnotationProcessHider` 안에 가보면 AnnotationProcessor 클래스와 ClaimingProcess 클래스가 AbstractProcessor 클래스를 상속받고있다는 것을 알 수 있다.

```java
import javax.lang.model.element.TypeElement;

import sun.misc.Unsafe;

class AnnotationProcessorHider {

	public static class AstModificationNotifierData {
		public volatile static boolean lombokInvoked = false;
	}
	
	public static class AnnotationProcessor extends AbstractProcessor {
		private final AbstractProcessor instance = createWrappedInstance();
		
		@Override public Set<String> getSupportedOptions() {
			return instance.getSupportedOptions();
		}
       (...)
	}
	
	@SupportedAnnotationTypes("lombok.*")
	public static class ClaimingProcessor extends AbstractProcessor {
		(...)
	}
}
```

- AbstractProcessor는 구체적인 애너테이션 프로세서들을 편리하게 작성하기 위해 고안된 수퍼 클래스이다.

```java
public abstract class AbstractProcessor implements Processor {

    protected ProcessingEnvironment processingEnv;
    private boolean initialized = false;
    
    protected AbstractProcessor() {}

    /**
     * If the processor class is annotated with {@link
     * SupportedOptions}, return an unmodifiable set with the same set
     * of strings as the annotation.  If the class is not so
     * annotated, an empty set is returned.
     *
     * @return the options recognized by this processor, or an empty
     * set if none
     */
    public Set<String> getSupportedOptions() {
        SupportedOptions so = this.getClass().getAnnotation(SupportedOptions.class);
        if  (so == null)
            return Collections.emptySet();
        else
            return arrayToSet(so.value(), false);
    }
}
```

- @Builder

```java
package lombok;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(SOURCE)
public @interface Builder {
	/**
	 * The field annotated with {@code @Default} must have an initializing expression; that expression is taken as the default to be used if not explicitly set during building.
	 */
	@Target(FIELD)
	@Retention(SOURCE)
	public @interface Default {}

	/** @return Name of the method that creates a new builder instance. Default: {@code builder}. If the empty string, suppress generating the {@code builder} method. */
	String builderMethodName() default "builder";
	
	/** @return Name of the method in the builder class that creates an instance of your {@code @Builder}-annotated class. */
	String buildMethodName() default "build";
	
    (...)
}
```



---

***Source***

- 자바의 정석
- https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/ElementType.html

- https://ahnyezi.github.io/java/javastudy-12-annotation/#4-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%ED%83%80%EC%9E%85-%EC%A0%95%EC%9D%98%EB%B0%A9%EB%B2%95
- https://xxxelppa.tistory.com/205?category=858435

- https://www.baeldung.com/java-annotation-processing-builder

- https://catsbi.oopy.io/78cee801-bb9c-44af-ad1f-dffc5a541101