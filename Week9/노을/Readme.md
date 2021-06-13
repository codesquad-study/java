> 💾 관련이슈: [https://github.com/whiteship/live-study/issues/](https://github.com/whiteship/live-study/issues/8)9

# 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

<br>


- 기본 예외처리

```java

try(){
 // 예외가 발생할 가능성이 있는 코드
}catch(예외클래스1 e){
 // 예외 발생시 처리 로직
}catch(예외클래스2 e2){
 // 예외 발생시 처리 로직
}finally{
 // 무조건 실행되는 로직으로 try() 구문에서 return이 있어도 실행✨
}

```

- throw
  - 인위적으로 예외를 발생시킬 때 사용
```java

if(input == null){
	new throw NullPointException();
}


```

- thorws
  - 예외가 발생한 메소드를 호출한 곳으로 예외 객체를 넘기는 방법
  - 예외가 발생했을 때 해당 로직에서 `try-catch` 블록을 이용해서 처리하지 않고, 호출한 지점에 예외를 전달하는 방법.
  - 목적은 메소드 수행 중 발생할 수 있는 예외이니 호출하는 측에서 이를 대비하라는 것
```java

public class ExceptionEx16 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try{
            String fileName = scanner.nextLine();
            createNewFile(fileName);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("잘못되었습니다, 다시 입력하세요.");
        }
        scanner.close();
    }

    private static void createNewFile(String fileName) throws Exception {
        if(fileName == null || fileName.equals("")) throw new Exception("파일이름이 유효하지 않습니다.");
        File f = new File(fileName);
        f.createNewFile();
    }
}

```

- try-with-resources

  - **try에 자원 객체를 전달하면, try 코드 블록이 끝나면 자동으로 자원을 종료해주는 기능**
  - 괄호()안에 객체를 생성하는 문장을 넣으면, 이 객체는 따로 close()를 호출하지 않아도 try를 벗어나는 순간 자동적으로 close()가 호출된다.
  - try-with-resources문에 의해 자동으로 객체의 close()가 호출될 수 있으려면, 클래스가 `AutoCloseable` 이라는 인터페이스를 구현한 것이어야 한다.
  - BufferedInputStream, FileInputStream은 이미 `AutoCloseable`  의 구현체로 추가적으로 인터페이스를 구현할 필요가 없다.
  - [InputStream](https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html)은 AutoCloseable를 상속받은 Closeable을 구현함


<br>


# 자바가 제공하는 예외 계층 구조

![](https://images.velog.io/images/san/post/3bf30d85-e8ee-44cc-9c96-ce10a68d8c94/image.png)


|   구분    |                     Checked Exception                     |              Unchecked Exception              |
| :-------: | :-------------------------------------------------------: | :-------------------------------------------: |
| 확인 시점 |                        컴파일 시점                        |                  런타임 시점                  |
| 처리 여부 |                반드시 예외처리를 해야한다.                |         명시적으로 하지 않아도 된다.          |
|   종류    | Exception<br />IOException<br />ClassNotFoundException 등 | RuntimeException<br />NullPointerException 등 |


- 이미지 출처: https://madplay.github.io/post/java-checked-unchecked-exceptions

# 예외 처리 기준
   - 아직 잘모르겠다. (나중에 정리ㅠ)
   - https://velog.io/@janeljs/codesquad-tech-talk-1


# 예외 처리 일반적인 방법 3가지
   - 예외 복구
     - 다른 작업 흐름으로 유도
     - 예외가 발생해도 애플리케이션은 정상 흐름으로 진행
     - ex) 네트워크 딜레이
   - 예외처리 회피
     - 처리하지 않고 호출한 쪽으로 예외 전달
     - ex) throws
   - 예외전환
     - 명확한 의미의 예외로 전환 후 예외 전달
     - ex) checked exception -> uncehcked excpetion전환

# 예외처리 비용
- 비싸다. 💸💸
- try-catch 블록의 검사로직
- Throwable 생성자의 fillInStackTrace() 메서드 (주원인!)
   - 메모리 영역의 Stack 에 쌓여있는 Stack Frame 들을 pop 하여 출력해주기 때문임.
 - 분기 또는 리턴 로직으로 해결할 수 있는 경우에는 Exception을 사용하지 않는 것 이좋다.


# Exception과 Error의 차이는?

- 오류
  - 시스템 레벨에서 발생하기 때문에 심각한 수준의 오류
  - 개발자가 미리 예측하여 처리할 수 없음
- 예외
  - 개발자가 구현한 로직에서 발생
  - 발생 상황을 미리 예측하여 처리할 수 있음.


# RuntimeException과 RE가 아닌 것의 차이는?

- checked excpetion? 
- 잘모르겠음


# 커스텀한 예외 만드는 방법
 - Exception 또는 ReuntimeException을 상속 받아서 구현

```java
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
```

# 커스텀 예외를 만들때 참고해야할 4가지

1. 자바의 표준 예외들에 포함되지 않는 정보나 기능을 제공
2. Exception으로 끝나는 예외 클래스 네이밍 컨벤션 따르기
3. 예외가 발생할 수도 있는 상황과 예외의 일반적인 의미를 Javadoc 으로 문서화
4. 예외의 원인을 예외 생성자에게 제공


<br>
<br>


# Reference.

- https://www.notion.so/3565a9689f714638af34125cbb8abbe8
- https://sujl95.tistory.com/62
- https://wisdom-and-record.tistory.com/46
- https://madplay.github.io/post/java-checked-unchecked-exceptions
- https://blog.naver.com/sthwin/221144722072