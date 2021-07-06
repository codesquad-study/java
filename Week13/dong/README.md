# 자바 13주차 I/O

<br>
<hr>
<br>






## I/O Stream

### (In/Out)Stream 공통

- 데이터를 전달하는 통로이며 물의흐름(stream) 처럼 데이터가 문자열로 계속 나오는 형태
- FIFO(First In First Out) : 먼저 들어가면 먼저 나옴
- One-Way(단방향) : 스트림은 입력(send), 출력(recv)가 정해지며 반대뱡향으로는 통신이 불가능
  - 입력 스트림과 출력 스트림을 별도(2개) 사용해야 양방향 통신이 가능
  - 입력과 출력(send, recv)모두 구현시 두개를 묶음으로 사용해야한다
- Blocking(블록킹) : 연속된 데이터의 흐름으로 입출력 진행시 다른 작업을 할 수 없는 Blocking 상태가 된다.
- 입출력 대상 변경이 가능하다
  - 입출력 대상 변경 : 파일 / 소켓 / 하드웨어 : 모두 스트림으로 읽기 가능
  - 리눅스에서 하드웨어, 소켓 모두 파일로 추상화되어서 제공된다!(다들 기억나시죠??)
  - 결론은  동일한 프로그램 구조를 유지할 수 있다.
- Stream은 IO 기반 : IO는 정확하게 java.io 패키지를 의미하며 하위 클래스로 Stream의 두가지 종류가 존재
  - InputStream : 읽어들이기 자바속으로
  - OutputStream : 자바에서 밖으로 내보내는 출력


### java.util.stream 은 전혀 다르다

- java.util.stream : 반복문을 돌리기 위한것
  - Interface Stream<T>

- java.io.(Out/In)putStream : 파일이나 통신 등 입출력 처리하는 클래스가 정의된 패키지
  - java.io.OutputStream class
    - 구현 된 인터페이스 : Closeable, Flushable,AutoCloseable
    - 하위 클래스 : ByteArrayOutputStream, FileOutputStream, FilterOutputStream, ObjectOutputStream,PipedOutputStream
  - java.io.InputStream class
    - Implemented Interfaces : Closeable, AutoCloseable
    - Direct Subclasses: AudioInputStream, ByteArrayInputStream, FileInputStream, FilterInputStream, ObjectInputStream, PipedInputStream, SequenceInputStream, StringBufferInputStream

<br>
<hr>
<br>


## 처리 데이터의 단위 : byte / char 

- byte와 char 두 종류뿐
- 텍스트 위주의 데이터 입출력시 char로 처리하며
- 텍스트가 아닌 이미지, 동영상 등의 파일들은 다 byte로
- 한글은? 

### 처리 구현

- byte로 처리하는 클래스의 최상위 클래스는 InputStream과 OutputStream이다. 
- 항상 입력과 출력은 쌍으로 제공되며 두 클래스는 추상클래스이기 때문에 실제 구현은 하위 일반클래스가 담당한다. 
- byte로 처리하는 하위클래스들의 이름을 보면 다 stream으로 끝난다는 특징이 있다.
- char로 처리하는 클래스의 최상위클래스는 입력을 담당하는 Reader와 출력을 담당하는 Writer가 있다. 이 두 개 역시 추상클래스이다. 하위클래스의 이름이 reader 또는 writer로 끝난다는 걸 알 수 있다.

### 바이트 스트림(Byte Stream)

- binary 데이터를 입출력하는 스트림
- 데이터는 1바이트 단위로 처리 / 데이터를 Byte 단위로 주고 받는 것을 의미
- 이미지, 동영상 등을 송수신 할 때 주로 사용
- binary 데이터를 입출력하는 스트림
- 이미지, 동영상등 모든 종류의 데이터들을 송수신할 떄 주로 사용
- 대표적인 바이트 스트림에는 데이터 입력의 InputStream과 데이터 출력의 OutputStream이 있고 이 두 추상 클래스는 byte기반 stream의 최고 조상이다

### 문자 스트림 (Character Stream)

- 문자 단위로 인코딩 처리를 하는 스트림
- 텍스트 파일등을 송수신할 떄 주로 사용
text 데이터를 입출력하는 스트림
데이터는 2바이트 단위로 처리
일반적인 텍스트 및 JSON, HTML 등을 송수신할 때 주로 사용

### 스트림 상속관계 사진  : https://bingbingpa.github.io/java/whiteship-live-study-week13/

<br>
<hr>
<br>


### 


### InputStream

- 바이트 기반 입력 스트림의 최상위 추상 클래스
- 모든 바이트 기반 입력 스트림은 이 클래스를 상속 받아서 만들어 진다.
- 버퍼, 파일, 네트워크 단에서 입력되는 데이터를 읽어오는 기능을 수행한다.

### OutputStream

- 바이트 기반 출력 스트림의 최상위 추상 클래스
- 모든 바이트 기반 출력 스트림은 이 클래스를 상속 받아서 만들어 진다.
- 버퍼, 파일, 네트워크 단으로 데이터를 내보내는 기능을 수행한다.


<br>
<hr>
<br>




## IO vs NIO

### IO 와 NIO 비교

- 둘은 전혀 다른 자바 클래스 패키지임
- jdk11기준 java src 폴더에서 ```tree``` 명령어 사용시 화면

```txt
└─java
    ├─io
    ├─lang
    │  ├─...
    │  └─reflect
    ├─math
    ├─net
    ├─nio
    │  ├─channels
    │  │  └─spi
    │  ├─charset
    │  │  └─spi
    │  └─file
    │      ├─attribute
    │      └─spi
    ....
```

- java.io패키지속에 들어있는 클래스들과 java.nio 패키지속 들어있는 클래스가 전혀 다른 자바 클래스들의 패키지입니다!!
  - 하는일이 워낙 비슷해서(파일/스트림 읽어들이는) 비교를 자주 당하고
  - 비교는 자주 하는데, 정리가 안되 혼란스러워서 정리해봅니다!

### IO (IO Stream = InputStream + OutputStream)

- java.io 패키지(줄여서 IO)는 자바의 시작과 함께한 ```C언어의 File Discripter 혹은 (FILE *)/(파일포인터)``` 와 1:1 대응되는 자바 클래스가 바로
  - io.InputStream
  - io.OutputStream
- 아래 코드는 InputStream.java파일인데, ```@since   1.0``` 이다 무려..

```java
 /**
 * This abstract class is the superclass of all classes representing
 * an input stream of bytes.
 *
 * <p> Applications that need to define a subclass of <code>InputStream</code>
 * must always provide a method that returns the next byte of input.
 .... <중략>....
 * @since   1.0
 */
public abstract class InputStream implements Closeable {
```

- 앞으로 나오게될 이런저런 ```Input``` 혹은 ```Stream``` 키워드들이 들어간 클래스는 모두 InputStream 를 상속받아 특정한 용도로 쓰기 편하게 작성한 클래스니까 필요할때 배워두는걸로 하고 여기서는 전체적인 흐름만 알아가봐요
- IO Stream의 특징
  - Blocking : 데이터가 출력되기전까지, 스레드는 멈춰서 컨텍스트가 정지한 상태(=Blocking)
  - 들어올땐 마음대로였겠지만 나갈때는 아니란다 : 블로킹을 빠져나오려면 스트림을 닫는 방법뿐
    - Blocking 상태에서는 작업이 끝날때까지 기다려야 하며, 그 이전에는 해당 IO 스레드는 사용할 수 없게 되고, 인터럽트도 불가능하다
  - IO Stream 사용시 발생하는 일
    - read() 호출 : 데이터를 읽기를 시도한다
    - write() 호출이 : 데이터 쓰기를 시도
      - 파일 : 디스크에서 정보를 Read하도록 OS 에게 System Call
      - 소켓(통신) : 소켓통신을 Read할 때까지 대기(원하는 버퍼 크기만큼, 소켓프로그래밍 공부해보셨으면 아시겠지만.. 헬)
        - 소켓 공부 따로 하실꺼죠? 그래서 맛뵈기로 실습 준비해놨습니다 ^^;
      - 하드웨어 : 하드웨어가 통신신호를 보내줄때까지 해당쓰레드가 Blocking되서 무한정 대기

### NIO(New I/O)

- java.nio 패키지(줄여서 NIO)는 1.4 버전부터 추가된 파일 입출력 클래스인데
- interoperation method(인터럽트 가능한 메서드) 지원을 위해서 만들어짐
- 이 NIO 패키지는 channel클래스, Buffer클래스를 사용해서 만들어졌고
- 내부적으로는 ```java.io.InputStream```, ```java.io.OutputStream``` 을 사용해서 NIO를 만든다

```java
package java.nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
...중략...

/**
 * Utility methods for channels and streams.
 *
 * <p> This class defines static methods that support the interoperation of the
 * stream classes of the {@link java.io} package with the channel classes
 * of this package.  </p>
...중략...
 * @since 1.4
 */

public final class Channels {
```

- NIO의 특징은
  - 넌블로킹(Non-blocking) : 논블록킹 처리가 가능
  - 체널 기반 : 스트림이 아닌 채널(Channel)을 사용
  - 블로킹 : 블록킹 상태도 지원하며, 블록킹 상태에서는 Interrupt 를 이용하여 빠져나올 수 있다.
    - 반면에 IO는 블로킹을 빠져나오려면 스트림을 닫는 방법 밖에 없다.

### 채널이란?

- 쌍방향 : 데이터가 통과하는 쌍방향 통로이며
- 버퍼를 내부적으로 사용 : 채널에서 데이터를 주고 받을 때 사용 되는 것이 버퍼이다.
- 채널의 종류들
  - 소켓과 연결된 SocketChannel
  - 파일과 연결된 FileChannel
  - 파이프와 연결된 Pipe.SinkChannel 과 Pipe.SourceChannel
  - 서버소켓과 연결된 ServerSocketChannel
- 참고로 버퍼는 기본(프리미티브) 데이터 타입(byte, char, int 등..)의 배열인데, 자세한건 뒤에서 다룰께요

### 스트림과 채널 (IO-Stream vs NIO-Channel) 비교

- **IO는 스트림(Stream) 기반**
  - 스트림은 단방향이고, 입력용과 출력용이 구분되어 있다
    - 데이터를 읽기 위해서는 입력 스트림을 생성해야 하고
    - 데이터를 출력하기 위해서는 출력 스트림을 따로 또 생성해야 함
  - 
- **NIO는 채널(Channel) 기반**
  - 채널은 스트림과 달리 양방향으로 입력과 출력이 가능하다.
  - 입력과 출력을 위한 별도의 채널을 만들 필요가 없다.
  - 사실 NIO 도 내부는 Stream으로 만들었지만, 귀찮고 복잡한 일들을 처리해주기 때문에 쓰기 편하다

<br>
<hr>
<br>

## 표준스트림(표준입출력)

### **표준 스트림 = (System.in  +  System.out  +  System.err)**

- System 클래스는 실행시간 환경과 관련된 속성과 메소드를 가지고 있다.
- System 클래스에서 제공되는 out과 in을 이용한 표준 입력, 출력, 에러 출력에 관한 클래스 변수, 외부적으로 정의된 프로퍼티 및 환경 변수의 접근, 파일 및 라이브러리의 로딩 방법, 객체를 복사해주는 메소드와 프로그램을 작성할 때 사용할 수 있는 유용한 메소드

### java **System.in**

- System.in : 표준입력으로 키보드에서 데이터 읽어들일 때 사용, 변수 in의 데이터형은 InputStream(변수 타입이 InputStream 형태로 지정됨)
- 위에서 언급했지만 InputStream은 최상위 클래스이면서 추상 클래스이기 때문에 InputStream은 객체를 생성할 수 없는 클래스
- System.in 을 통해서 접근하는 객체는 JVM이 메모리로 올라오면서 미리 인스턴스화(=실체화/객체생성) 하는 대표적인 객체
 자료형이 InputStream이기 떄문에 바이트 단위로만 입출력
- 키보드에서 입력하는 자료는 때에 따라서 두 바이트가 합쳐져야 의미를 가지는 경우가 있는데(한글이 2바이트)
  - 그래서 System.in을 통해서 읽을 때는 영문과 한글의 처리를 분리해서 구성해야하는데, 어차피 내부적으로 알아서 다 잘해주고
  - 요즘에는 인코딩을 UTF-8로 하면 짱짱굿이다

### java **System.out**

- System.out : 표준출력으로 PrintStream이 out의 데이터형. 이 클래스의 출력메소드로 print와 println 오버로딩 메소드가 제공됨(System.out 변수는 표준 출력 장치 객체)
- 대표적으로 System.out.printXX() 메서드 호출할때 사용
- System.out은 PrintStream 타입
  - PrintStream이란 OutputStream 클래스의 후손 클래스로 Exception을 안전하게 처리할 메소드로만 구성이 된 클래스
  - 굳이 try-catch 문 같이 따로 처리를 해주지 않아도 사용가능

### java **System.err**

- System.err 객체는 표준 에러 출력 장치를 의미한다. 오류가 발생하게 되면 System.err로 알려줘야 하는 내용이 나온다고 생각하면 된다.
- System.err 는 PrintStream 클래스 타입으로 System.out을 사용하는 방법과 같다.

### 참고 : 유닉스 계열 표준입출력

- 유닉스계열(리눅스,맥 포함) 프로세스가 shell로부터 stdin, stdout, stderr을 상속받는데요(from 운영체제, 시스템프로그래밍 에서 배움)
  - 여기서 stdin, stdout, stderr은 stream이라고 불립니다
  - Shell과 3개의 file stream(stdin, stdout, stderr)을 상속받아서 프로세스가 실행을 시작하는데요
  - 모든 프로세스는 그래서 프로그램 시작과 동시에 3개의 file stream을 가지고 있습니다(from C언어에서 배움)
- Unix stdin(표준입력) : 키보드에서 입력받은 문자 데이터
- Unix stdout(표준출력) : 터미널로 출력(터미널의 문자를 모니터로 확인)하는 문자, 표준에러와의 차이는 호눅스가 예전에 쉘에서 설명해주심
- Unix stderr(표준에러) : 에러메시지 출력하는 전용의 터미널장치(일반적으로 모니터나 콘솔창에 출력됨)
- 프로그램이 하나 실행되면 파일이 3개가 자동으로 열리는데(From C언어), 그 3개의 파일이 표준입출력에러 3종셋트이다
  - 표준입력/표준출력/표준에러
- 자바에서 이에 대응하는 표준 입출력은 java.lang.System 클래스가 담당한다
- 이게 왜 표준입출력이냐면, 키보드로 입력해서 터미널로 보는게 지금은 당연하지만 과거에는 아니여서 
  - 라떼는 말야 텔레타이프(tty)나 시리얼통신(RS-232)을 입출력 체널로 설정하는 경우도 많고,
  - 지금도 일부 임베디드 장비에서 여전히 소켓통신(UDP)/시리얼통신(RS-232) 등으로 표준입출력이 연결되어 있다
    - 왜 이런짓거리를 하냐면, 모니터가 없게 당연한 장비들이라서!
- 개인적인 생각이지만 약간의 컴퓨터라는 물건을 만든 설계자들이 리스코프 치환원칙을 잘 지켜서 컴퓨터를 설계하셔서 91년생 자바에서조차 stream과 표준입출력이라는 개념을 사용하는거 같다!


<br>
<hr>
<br>


## 보조스트림

- 스트림의 기능을 보완하기 위해 보조스트림이라는 것이 제공된다.
- 보조스트림은 *실제 데이터를 주고받는 스트림이 아니기 때문에 데이터를 입출력할 수 있는 기능은 없지만*, 스트림의 **`기능을 향상`**시키거나 `**새로운 기능을 추가**`할 수 있다.
- 즉, 스트림을 먼저 생성한 다음에 이를 이용해 보조스트림을 생성해서 활용한다.

- FilterInputStream 과 FilterOutputStream (+ 상속받는 클래스들) : 기본 스트림과 결합하여 특정 상황에서 보다 편리하게 사용할 수 있다.
- BufferedInputStream/BufferedOutputStream : 버퍼를 사용해 입출력 효율과 편의를 위해 사용
- BufferedReader/BufferedWriter : 라인단위의 입출력이 편리함
- InputStreamReader/OutputStreamReader : 바이트 스트림을 문자 스트림처럼 쓸 수 있도록하며 문자 인코딩 변환을 지원
- DataInputStream/DataOutputStream : 자바 원시자료형 데이터 처리에 적합

<br>
<hr>
<br>

## 자바에서 파일읽고쓰기 기본

- 텍스트 파일인 경우 문자 스트림 클래스들을 사용하면 되고
- 바이너리 파일인 경우 바이트 스트림을 기본적으로 사용다.
  - 입출력 효율을 위해 Buffered 계열의 보조 스트림을 함께 사용하는 것이 좋다.
- 텍스트 파일인 경우(asci문자)

```java
BufferedReader br = new BufferedReader(new FileReader("a.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));
String s;
while ((s = br.readLine()) != null) {
    bw.write(s + "\n");
}
```

- 바이너리 파일인 경우(텍스트 빼고 다)

```java
BufferedInputStream is = new BufferedInputStream(new FileInputStream("a.jpg"));
BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("b.jpg"));
byte[] buffer = new byte[16384];
while (is.read(buffer) != -1) {
    os.write(buffer);
}
```

<br>
<hr>
<br>

## 버퍼

- 버퍼는 기본(프리미티브) 데이터 타입(byte, char, int 등..)의 배열자료구조의 저장소
- byte, char, int 로 대표되는 premitive type (기본 데이터 타입)을 저장할 수 있는 임시 저장소로서
- 배열 자료 구조로 제한된 크기(capacity) 에 순서대로 데이터를 저장한다.
- 실제로는 채널을 통해서 데이터를 주고 받을 때도 쓰임 (원래 버퍼는 데이터를 저장하기 위함이지만)
- 채널을 통해서 소켓, 파일 등에 데이터를 전송할 때나 읽어올 때 버퍼를 사용하게 됨으로써 가비지량을 최소화 시킬 수 있다
  - 가바지 콜렉션 회수를 줄임으로써 서버의 전체 처리량을 증가시켜준다.
- 고속의 장치와 저속의 장치 간의 속도 차이로 인해 발생하는 비효율성 제거를 위한 "데이터를 임시 저장공간"
- 저속의 장치가 작업 진행시간 동안 고속의 장치가 기다려야하는 현상을 줄여주는 기술
- 배열과 마찬가지로 제한된 크기(capacity) 에 순서대로 데이터를 저장
- 실제로 버퍼가 사용되는 것는 채널을 통해서 데이터를 주고 받을 때 사용
- **채널을 통해서 소켓, 파일 등에 데이터를 전송할 때나 읽어올 때 버퍼를 사용하게 됨으로써 가비지량을 최소화 시킬 수 있게 되며, 이는 가바지 콜렉션 회수를 줄임으로써 서버의 전체 처리량을 증가시켜준다.**

### 추가 - Buffer 를 사용하면 좋은이유에 대한 근본적인 이유를 고민해야 한다.

- 면접질문 : Buffer 를 사용하면 좋은 이유, 없을때의 차이점과 성능상의 장점이 발생하는 원인을 말씀해주세요
  - 왜 **`속도가 왜 빨라질까?`** 가 가장 중요한 질문임!

- 왜 모아서 보내면 왜 빨라질까?
- 한 바이트씩 바로바로 보내는 것이 아니라 버퍼에 담았다가 한번에 모아서 보내는 방법인데 왜 이렇게 하는 것이
- 입출력 횟수가 포인트 이다.
- 단순히 모아서 보낸다고 이점이 있는 것이 아니다 → *시스템 콜의 횟수가 줄어들었기 때문에 성능상 이점이 생기는 것이다*
- **`OS 레벨에 있는 시스템 콜의 횟수 자체를 줄이기 때문에 성능이 빨라지는 것이다.`**

**물을 떠와라 → 물을 한 모금 씩 떠와라**

- 매번 한모금 먹고 주방 갔다오고 또 먹고 갔다오고 반복..

**물을 떠와라 → 물을 한 컵씩 떠와라**

- 한 컵이 다 마실 때 까지 물을 마실 수 있지,

*동일한 양의 물을 마신다고 했을 때 한모금 씩 떠와서 마시는 것과 한 컵씩 떠와서 마시는 것의 차이는??? 시간이 줄어들 것이다.*

### 버퍼를 사용해 얻는 장점

- 운영체제의 API 호출 횟수를 줄여서 입출력 성능을 개선할 수 있다.
- 자바 문자열 입력시 (알고리즘 문제) 풀때
    - 입력 : Scanner 보다는 >> BufferedReader방식이 빠르고
    - 출력 : System.out.println 보다 >> BufferedWrite 방식이 시간을 줄일수 있다

### 버퍼 사용법

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// BufferedReader를 사용하기 위해서는 throws IOException을 해 주어야 함.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 선언

		int N = Integer.parseInt(br.readLine()); // readLine으로 받은 입력 데이터 String임.
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		br.close();
	}

}
```

```java
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		// BufferedWriter를 사용하기 위해서는 throws IOException을 해 주어야 함.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
		bw.write("Hello World");
		bw.flush(); // write로 담은 내용 출력 후, 버퍼를 비움.
		bw.close(); 
	}

}
```



### 다이렉트버퍼, 논다이렉트 버퍼

- 버퍼가 사용하는 메모리 위치에 따라서 넌다이렉트(non-direct) 버퍼와 다이렉트(direct) 버퍼로 분류된다.
  - **넌다이렉트 버퍼**는 **`JVM이 관리하는 힙 메모리 공간`**을 이용하는 버퍼이고,
  - **다이렉트 버퍼는** `**운영체제가 관리하는 메모리 공간**`을 이용하는 버퍼이다.

| 사용하는 메모리 공간 | JVM의 힙 메모리     | 운영체제의 메모리               |
| -------------------- | ------------------- | ------------------------------- |
| 버퍼 생성 시간       | 버퍼 생성이 빠르다. | 버퍼 생성이 느리다.             |
| 버퍼의 크기          | 작다                | 크다. (큰 데이터 처리에 유리)   |
| 입출력 성능          | 낮다                | 높다. (입출력이 빈번할 때 유리) |

- **넌다이렉트 버퍼**는 **JVM 힙 메모리**를 사용하므로 **생성 시간이 빠르지만**,
  - **넌다이렉트 버퍼**는 JVM의 **제한된 힙 메모리**를 사용하므로 **버퍼의 크기를 크게 잡을 수 없다** (하지만 수백메가바이트 단위)
- **다이렉트 버퍼**는 **운영체제의 메모리**를 할당받기 위해 운영체제의 네이티브(native) C함수를 호출이 필요
  - 여러가지 잡다한 처리를 해야하므로 **상대적으로 생성이 느림**
  - 그래서 **다이렉트 버퍼**는 **자주 생성하기보단 한 번 생성해놓고 재사용**하는 것이 유리
  - **다이렉트 버퍼**는 운영체제가 관리하는 메모리를 사용하므로 **운영체제가 허용하는 범위 내에서 대용량 버퍼를 생성시킬 수 있다.**

<br>
<hr>
<br>


## 인메모리 데이터 그리드
https://www.samsungsds.com/kr/insights/In-Memory-Data-Grid.html
- 정리해봐


<br>
<hr>
<br>


## 직렬화

- 출처 : https://techblog.woowahan.com/2550/

## 자바 직렬화란?

- 자바 직렬화란 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술
  - 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)도 포함
- 자바 시스템적으로 좀더 구체화시키면
  - JVM의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술
  - 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태를 같이 이야기합니다.
- `**직렬화(serialization)**`란 객체를 문자열(스트림)으로 만들고, 문자열을 다시 객체로 복원해내는 기술
- `**역직렬화(deserialization)**` 는 반대로 스트림(문자열)으로부터 데이터를 읽어서 객체를 만드는 기술

***`객체`에 대해 다시 짚고 넘어가보자.***

객체는 클래스에 정의된 인스턴스변수의 집합이다.

객체에는 클래스변수나 메서드가 포함되지 않는다. 객체는 오직 인스턴스 변수들로만 구성되어 있다.

***객체를 저장한다는 것은 바로 객체의 모든 인스턴스변수의 값을 저장한다는 것과 같은 의미이다.***

어떤 객체를 저장하고자 한다면, 현재 객체의 모든 인스턴스변수의 값을 저장하기만 하면된다. 그리고 저장했던 객체를 다시 생성하려면, 객체를 생성한 후에 저장했던 값을 읽어서 생성한 객체의 인스턴스변수에 저장하면 되는 것이다.

### 자바 직렬화는 어떻게(how) 사용할 수 있나요?
- 자바 직렬화 조건
- 자바 기본(primitive) 타입과 java.io.Serializable, 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건을 가집니다.
```java
public class Member implements Serializable {
    private final String name;
    private final String email;
    private final int age;

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    } 
    // Getter 생략 
    @Override public String toString() { 
        return String.format("Member", name, email, age); 
    } 
}

```



### 직렬화 방법
자바 직렬화는 방법은 java.io.ObjectOutputStream 객체를 이용합니다.


위 예제에서 객체를 직렬 화하여 바이트 배열(byte []) 형태로 변환하였습니다.

역직렬화 조건
직렬화 대상이 된 객체의 클래스가 클래스 패스에 존재해야 하며 import 되어 있어야 합니다.
중요한 점은 직렬화와 역직렬화를 진행하는 시스템이 서로 다를 수 있다는 것을 반드시 고려해야 합니다. (같은 시스템 내부이라도 소스 버전이 다를 수 있습니다. 이 부분은 다시 자세히 이야기하겠습니다.)
자바 직렬화 대상 객체는 동일한 serialVersionUID 를 가지고 있어야 합니다.
private static final long serialVersionUID = 1L;
자바 직렬화를 아시는 분은 위에서 기술한 예제에서 사용되는 자바 직렬화 대상의 Member 클래스가 serialVersionUID 상수가 없어서 의아하신 분도 계실 겁니다. 사실 반드시 기술해야 되는 필수는 아니기 때문에 빼둔 것입니다. 하지만 상당히 중요한 부분이라서 따로 설명하려고 합니다. 이곳에서는 넘어가도록 하겠습니다.


### 자바의 직렬화 왜(why) 사용되나요?

### 직렬화의 두가지 방법

- 문자열 직렬화 방법
  - 직접 데이터를 문자열 형태로 확인 가능한 직렬화 방법
  - 범용적인 API나 데이터를 변환하여 추출할 때 사용
  - 표형태의 다량의 데이터를 직렬화시 CSV
  - 구조적인 데이터는 이전에는 XML을 많이 사용
  - 최근에는 JSON형태를 많이 사용

- 이진 직렬화 방법
  - 데이터 변환 및 전송 속도에 최적화하여 별도의 직렬화 방법을 제시하는 구조
  - 이진 직렬화는 직렬화뿐만 아니라 전송 방법에 대한 부분도 이야기 해야 함
  - 여기서는 직렬화 부분만 이야기하겠습니다. 
  - 종류로는 
    - Protocol Buffer(이하 프로토콜버퍼) 
    - Apache Avro 등이 있습니다
  - 기타 지면 관계상 프로토콜 버퍼만

### 문자열 직렬화 CSV
데이터를 표현하는 가장 많이 사용되는 방법 중 하나로 콤마(,) 기준으로 데이터를 구분하는 방법입니다.
김배민,deliverykim@baemin.com
자바에서 사용방법
- Member member = new Member("김배민", "deliverykim@baemin.com", 25); //
- member객체를 csv로 변환 
- String csv = String.format("%s,%s,%d",member.getName(), member.getEmail(), member.getAge()); System.out.println(csv);
예제에서는 문자열로 단순히 변경했습니다. 자바에서는 Apache Commons CSV, opencsv 등의 라이브러리 등을 이용할 수 있습니다.

### 문자열 직렬회 JSON
최근에 가장 많이 사용하는 포맷으로 자바스크립트(ECMAScript)에서 쉽게 사용 가능하고, 다른 데이터 포맷 방식에 비해 오버헤드가 적기 때문에 때문에 인기가 많습니다.
{ name: "김배민", email: "deliverykim@baemin.com", age: 25 }
자바에서 사용방법
Member member = new Member("김배민", "deliverykim@baemin.com", 25); // member객체를 json으로 변환 String json = String.format( "", member.getName(), member.getEmail(), member.getAge()); System.out.println(json);
JSON도 물론 이렇게 직접 문자열을 만들일은 거의 없습니다. 자바에서는 Jackson, GSON 등의 라이브러리를 이용해서 변환할 수 있습니다.

### 직렬화 사용하는 곳
- 영속화
  - JVM의 메모리에서만 상주되어있는 객체 데이터를 그대로 영속화(Persistence)가 필요할 때 사용됩니다. 시스템이 종료되더라도 없어지지 않는 장점을 가지며 영속화된 데이터이기 때문에 네트워크로 전송도 가능합니다.
- 서블릿 세션
  - Servlet Session) 서블릿 기반의 WAS(톰캣, 웹로직 등)들은 대부분 세션의 자바 직렬화를 지원하고 있습니다. 물론 단순히 세션을 서블릿 메모리 위에서 운용한다면 직렬화를 필요로 하지 않지만, 파일로 저장하거나 세션 클러스터링, DB를 저장하는 옵션 등을 선택하게 되면 세션 자체가 직렬화가 되어 저장되어 전달됩니다. (그래서 세션에 필요한 객체는 java.io.Serializable 인터페이스를 구현(implements) 해두는 것을 추천합니다.) 참고로 위 내용은 서블릿 스펙에서는 직접 기술한 내용이 아니기 때문에 구현한 WAS 마다 동작은 달라질 수 있
- 캐시
  - Cache) 자바 시스템에서 퍼포먼스를 위해 캐시(Ehcache, Redis, Memcached, ...) 라이브러리를 시스템을 많이 이용하게 됩니다. 자바 시스템을 개발하다 보면 상당수의 클래스가 만들어지게 됩니다. 예를 들면 DB를 조회한 후 가져온 데이터 객체 같은 경우 실시간 형태로 요구하는 데이터가 아니라면 메모리, 외부 저장소, 파일 등을 저장소를 이용해서 데이터 객체를 저장한 후 동일한 요청이 오면 DB를 다시 요청하는 것이 아니라 저장된 객체를 찾아서 응답하게 하는 형태를 보통 캐시를 사용한다고 합니다. 캐시를 이용하면 DB에 대한 리소스를 절약할 수 있기 때문에 많은 시스템에서 자주 활용됩니다. (사실 이렇게 간단하진 않습니다만 간단하게 설명했습니다.)
  - 이렇게 캐시 할 부분을 자바 직렬화된 데이터를 저장해서 사용됩니다. 물론 자바 직렬 화만 이용해서만 캐시를 저장하지 않지만 가장 간편하기 때문에 많이 사용됩니다.



<br>
<hr>
<br>


## 데코레이터 패턴
### **java.io - 데코레이터 패턴**

자기자신의 타입을 감싸는 패턴이라고 보면 된다.

**데코레이터 패턴**
 객체에 추가적인 요건을 동적으로 첨가한다. 데코레이터는 서브클래스를 만드는 것을 통해서 기능을 유연하게 확장할 수 있는 방법을 제공한다.

- java.io 패키지는 데코레이터 패턴으로 만들어졌다.
- 데코레이터 패턴이란, A 클래스에서 B 클래스를 생성자로 받아와서, B 클래스에 추가적인 기능을 덧붙여서 제공하는 패턴이다.
- 자바에서 이부분이 데코레이터 패턴의 대표적인 예시
```java
// BufferReader class

```

<br>
<hr>
<br>






- 참고 [https://github.com/kyu9/WS_study/blob/master/week13.md](https://github.com/kyu9/WS_study/blob/master/week13.md)

- 많이 참고한 노션 : https://www.notion.so/I-O-af9b3036338c43a8bf9fa6a521cda242
- 
