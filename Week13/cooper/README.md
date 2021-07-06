# 	Week13. I/O



## 학습할 것 (필수)

- 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O
- InputStream과 OutputStream
- Byte와 Character 스트림
- 표준 스트림 (System.in, System.out, System.err)
- 파일 읽고 쓰기

<br><br>

## 1. 스트림(Stream) / 버퍼(Buffer) / 채널 (Channel) 기반의 I/O

### > I/O (Input/Output)

- 입출력은 컴퓨터 내부 또는 외부 장치와 `프로그램 간에 데이터를 주고 받는 것`을 말한다.
  - ex) `Input` : 키보드로 데이터를 입력받아 / `Output` : System.out.println()으로 화면에 출력한다.

<br>

### (1) 스트림

![image-20210705154002579](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/da1ae210-b280-4c62-a55a-3450e05ed354/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210705%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210705T064128Z&X-Amz-Expires=86400&X-Amz-Signature=2074ae1cda050a6771504301247f4de79149eb14809992a7905a002e18d2d544&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

```java
import java.io.*;

public class IoTest {
    public static void main(String[] args) throws FileNotFoundException {
        try (
                InputStream inputStream = new FileInputStream("src/io_test/abc.txt");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)
        ) {
            char readChar;
            while((readChar = (char)bufferedInputStream.read()) > 90) {
                System.out.println(readChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

- `데이터를 운반하는데 사용되는 연결 통로`이다.
- 스트림은 `단방향통신`만 가능하다. 
  - 하나의 스트림으로 입출력을 동시에 하는 것은 어렵다. = 입출력을 동시에 하기 위해서 `입력스트림, 출력스트림`이 별도로 필요하다.

- 스트림은 먼저 전송한 데이터를 먼저 반는다. 즉, `FIFO(First In, First Out)`구조를 가지고 있다.
- 중간에 건너뜀없이 `연속적`으로 데이터를 주고 받는다.

<br>

### (+ 보조스트림)

- 목적 : 스트림 기능을 보완하기 위함.

- 데이터를 입출력할 수 있는 기능은 없다. 하지만, 스트림의 `기능 향상, 추가`할 수 있다.

- 즉, 스트림 생성하고 그 다음에 보조 스트림을 생성해서 활용한다.

- Java.io package는 `Decoration pattern`을 통해 구현되었음.

  - `Decoration pattern` :  A클래스에서 B클래스를 생성자로 받아와서, B클래스에 추가적인 기능을 덧붙여서 제공하는 패턴이다.

    (즉, BufferedReader에 Reader를 받아와서 `Reader클래스`에 `Buffer기능을 덧붙여서 제공`하는 패턴.)

    ```java
    //BufferedReader class
    
    public class BufferedReader extends Reader {
        private Reader in;
      
        public BufferedReader(Reader in, int sz) {
            super(in);
            if (sz <= 0)
                throw new IllegalArgumentException("Buffer size <= 0");
            this.in = in;
            cb = new char[sz];
            nextChar = nChars = 0;
        }
    
      public BufferedReader(Reader in) {
            this(in, defaultCharBufferSize);
        }
      
    }
    
    ```

    

### (2) 버퍼(Buffer)

- byte, char, int 등 기본 데이터 타입을 저장할 수 있는 저장소로, 배열과 마찬가지로 제한된 크기(capacity) 에 순서대로 데이터를 저장한다.
- 채널을 통해서 소켓, 파일 등에 데이터를 전송할 때나 읽어올 때 버퍼를 사용하게 됨으로써 가비지량을 최소화 시킬 수 있게 되며, 이는 가바지 콜렉션 회수를 줄임으로써 서버의 전체 처리량을 증가시켜준다.
- 데이터를 전송하는 상호 간의 장치에서 고속의 장치와 저속의 장치 간의 속도 차이로 인해 저속의 장치가 작업을 추리하는 동안, 고속의 장치가 기다려야하는 현상을 줄여주는 기술. ([kyu09님의 요약정리](https://github.com/kyu9/WS_study/blob/master/week13.md))
- 버퍼를 사용할 때의 장점 : OS 레벨의 `시스템 콜의 횟수가 줄어들면`, 입출력 횟수가 줄어들고 `성능이 빨라진다.`

<br>

### (3) 채널(Channel)

- 스트림과 달리 `양방향`으로 입출력이 가능한 형태.(스트림과 달리 입출력을 위한 별도의 채널을 만들 필요가 없다.)
- 채널에서 데이터를 주고 받을 때 버퍼(buffer)가 사용된다.

<br>

### (4) IO / NIO

- `java.io` package는 stream 기반 I/O를 지원하고, `java.nio` package 는 channel 기반 I/O를 지원한다.

- **java.io** : `blocking` I/O를 기반으로 함. 입출력을 위한 메서드 호출 시,데이터를 읽거나 쓰기 전까지 해당 thread는 blocking 상태이다.

- `java.nio` : `non-blocking' I/O를 기반으로 함. java.io 클래스에서도 buffer를 사용할 수 있지만 java.nio 경우, ByteBuffer 클래스의 allocateDirect()메서드를 사용하면 커널 버퍼를 사용할 수 있다는 장점이 있다.

  (kernel buffer : OS 의 버퍼에서 JVM 내부의 버퍼로 데이터를 옮기는 작업이 필요없는 버퍼)

<br>



## 2. InputStream & OutputStream (Byte & Character Stream)

<img src="http://www.btechsmartclass.com/java/java_images/java-Byte-Stream.png" width="700" height="500">

<br>

### (2) InputStream

- 바이트 기반 입력 스트림의 최상위 추상 클래스
- 모든 바이트 기반 입력 클래스는 해당 클래스를 상속받아 사용한다.
- 버퍼, 파일, 네트워크 단에서 입력되는 데이터를 읽어오는 기능을 수행한다.

| 메서드                               | 설명                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| int available()                      | 스트림으로부터 읽어올 수 있는 데이터 크기를 반환             |
| void close()                         | 닫음으로써 사용하고 있던 자원을 반환                         |
| void mark(int readlimit)             | 현재 위치를 표시해 놓는다.<br /> 후에 reset()에 의해서 표시해놓은 위치로 다시 돌아갈 수 있다.<br />readlimit는 되돌아갈 수 있는 byte수이다. |
| abstract int read()                  | 1byte를 읽어온다.(0 ~ 255사이의 값)<br />더 이상 읽어올 값이 없으면 -1을 반환한다.<br />abstract 추상 메서드임으로 InputStream의 자손들은 자신의 상황에 알맞게 구현해야 한다. |
| int read(byte[] b, int off, int len) | 최대 len개의 byte를 읽어, 배열 b의 지정 위치(off)부터 저장한다.<br />실제로 읽어올 수 있는 데이터가 len보다 적을 수도 있다. |
| void reset()                         | 스트림에서의 위치를 마지막으로 mark()이 호출되었던 취치로 되돌린다. |
| long skip(long n)                    | 주어진 길이(n)만큼 건너뛴다.                                 |

<br>

### (3) OutputStream

- 바이트 기반 출력 스트림의 최상위 추상 클래스
- 모든 바이트 기반 출력 스트림은 이 클래스를 상속받아 사용한다.
- 버퍼, 파일, 네트워크 단으로 데이터를 내보내는 기능을 수행한다.

| 메서드                                 | 설명                                                         |
| -------------------------------------- | ------------------------------------------------------------ |
| void close()                           | 입력 소스를 닫음으로써 사용하고 있던 자원을 반납             |
| void flush()                           | 스트림의 버퍼에 있는 모든 내용을 출력 소스에 쓴다.           |
| abstract void write(int b)             | 주어진 값을 출력 소스에 쓴다.                                |
| void write(byte[] b)                   | 주어진 배열 b에 저장된 모든 내용을 출력 소스에 쓴다.         |
| void write(byte[] b, int off, int len) | 주어진 배열 b에 저장된 내용 중에서 off번째 부터 len개 만큼만을 읽어서 출력 소스에 쓴다. |



### (4) Reader & Writer

<img src="http://www.btechsmartclass.com/java/java_images/java-Character-Stream.png" width="700" height="500">



<br>

### (5) Reader

- 문자 기반 입력 스트림의 최상위 추상 클래스
- 모든 문자 기반 스트림에서는 이 클래스를 상속받아 사용한다.
- 바이트기반의 경우, byte[]를 받는 반면, 문자 기반은 `char[]` 배열을 사용한다.
- 자바의 경우 `유니코드(UTF-16)`으로 자동 처리해준다.

| 메서드                                        | 설명                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| abstract void close()                         | 입력 스트림을 닫음으로써 사용하고 있던 자원을 반납한다.      |
| void mark(int readlimit)                      | 현재 위치를 표시해놓는다. <br />후에 reset()에 으해 표시해 놓은 위치로 다시 돌아갈 수 있다. |
| boolean markSupported()                       | mark()와 reset()을 지원하는지 알려준다.                      |
| int read()                                    | 하나의 문자를 읽어온다. <br />char의 범위인 0~65535범위의 정수를 반환하며, 입력 스트림의 마지막 데이터에 도달하면, -1을 반환한다. |
| int read(char[] c)                            | 입력 소스로 부터 매개변수로 주어진 배열 c의 크기만큼 읽어서 배열 c에 저장한다.<br /> 읽어온 데이터의 개수 또는 -1을 반환한다. |
| abstract int read(char[] c, int off, int len) | 입력 소스로 부터 최대 len개의 문자를 읽어서, 배열 c의 저장된 위치(off)부터 읽은 다음 저장한다.<br />읽어온 데이터 개수 또는 -1을 반환한다. |
| int read(CharBuffer target)                   | 입력 소스로부터 읽어서 문자버퍼(target)에 저장한다.          |
| boolean ready()                               | 입력 소스로부터 데이터를 읽을 준비가 되어있는지 알려준다.    |
| void reset()                                  | 입력 소스에서의 위치를 마지막으로 mark()가 호출되었던 위치로 되돌린다. |
| long skip(long n)                             | 현재 위치에서 주어진 문자 수(n)만큼을 건너뛴다.              |



<br>

### (6) Writer

- 문자 기반 출력 스트림의 최상위 추상 클래스
- 모든 문자 기반 스트림에서는 이 클래스를 상속받아 사용한다.
- 바이트기반의 경우, byte[]를 받는 반면, 문자 기반은 `char[]` 배열을 사용한다.
- 자바의 경우 `유니코드(UTF-16)`으로 자동 처리해준다.

| 메서드                                            | 설명                                                         |
| ------------------------------------------------- | ------------------------------------------------------------ |
| Writer append(char c)                             | 지정된 문자를 출력소스에 출력한다.                           |
| Writer append(charSequence c)                     | 지정된 문자열(CharSequence)을 출력소스에 출력한다.           |
| Writer append(CharSequence c, int start, int end) | 지정된 문자열(CharSequence)의 일부를 출력소스에 출력(CharBuffer, String, StringBuffer가 CharSequence를 구현) |
| abstract void close()                             | 출력 스트림을 닫음으로써 사용하고 있던 자원을 반환한다.      |
| abstract void flush()                             | 스트림 버퍼에 있는 모든 내용을 출력 소스에 쓴다.             |
| void write(int b)                                 | 주어진 값을 출력 소스에 쓴다.                                |
| void write(char[] c)                              | 주어진 값을 출력 소스에 쓴다.                                |
| abstract void write(char[] c, int off, inf len)   | 주어진 배열 c에 저장된 내용 중에서 off번째부터 len길이 만큼만 출력 소스에 쓴다. |
| void write(String str)                            | 주어진 문자열(str)을 출력 소스에 쓴다.                       |
| void write(String str, int off, int len)          | 주어진 문자열(str)의 일부를 출력 소스에 쓴다.<br />(off번째 문자부터 len개만큼의 문자열)\ |

<br><br>

## 3. 표준 스트림(System.in, System.out, System.err)

```java
public final class System {
  public static final InputStream in;
  public static final PrintStream out;
  public static final PrintStream err;
}
```

- System class : 실행 시간, 환경과 관련된 속성과 메소드를 가지고 있다

  - `System.in` : 콘솔로 입력을 받는 표준 스트림을 가리키는 상수.

    - `영어`를 입력하고 싶은 경으, System.in.read()를 사용해도 가능하다. 

      ```Java
      int input = System.in.read();
      System.out.println(input);
      //입력 : i -> 출력 : i
      //입력 : 쿠 -> 출력 : ì 
      ```

      

    - 하지만, `한글`을 입력하고 싶은 경우`, InputStreamReader`를 사용하도록 하자!

      ```java
      InputStreamReader inputStreamReader = new InputStreamReader(System.in);
      int input = inputStreamReader.read();
      System.out.println(input); //입력 : 쿠퍼 -> 출력 : 쿠퍼
      ```

      

  - `System.out` : 콘솔로 출력하는 표준 스트림을 가리키는 상수.

    - ex) `System.out.println();`

  - `System.err` : 표준 에러 출력 장치를 가리키는 상수

<br><br>

## 4. 파읽 읽고 쓰기

- 파일 읽어서 콘솔에 출력

  ```java
  import java.io.*;
  
  public class FileIOTest {
      public static void main(String[] args) {
          String line;
  
          try (BufferedReader br = new BufferedReader(new FileReader("src/io_test/abc.txt"))) {
              while ((line = br.readLine()) != null) {
                  System.out.println(line);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```

  <br>

- .txt파일 생성 및 내용 작성하기!

  ```java
  import java.io.*;
  
  public class FileIOTest {
      public static void main(String[] args) {
          File file = new File("src/io_test/cooper.txt");
  
          try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
              bw.write("Hi, Cooper");
              bw.newLine();
              bw.write("Please go to home...");
              bw.newLine();
              bw.flush();
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("똑바로 쓰자. 진짜.");
          }
      }
  }
  
  ```

<br><br>

## Reference

- ssonsh님의 I/O정리 : https://www.notion.so/I-O-af9b3036338c43a8bf9fa6a521cda242

- Java Tutorials - Byte Stream : http://www.btechsmartclass.com/java/java-byte-stream.html

- Java Tutorials - Character Stream : http://www.btechsmartclass.com/java/java-character-stream.html

  