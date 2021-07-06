# I/O

컴퓨터 내부 또는 외부의 장치와 프로그램 간의 데이터를 주고받는 것

### 스트림 (Stream)

- 데이터를 운반하는데 사용되는 연결 통로

- 단방향 통신만 가능 &rarr; 입출력을 한 번에 처리할 수 없음
- 동기적 / 블로킹 방식

- 선입선출 구조
- 바이트 단위로 데이터를 전송

### 버퍼 (Buffer)

- byte, char, int 등 기본 데이터 타입을 저장할 수 있는 저장소

- 입출력 전송 속도 차이에 대한 성능을 보완
- 채널을 통해서 소켓, 파일 등에 데이터를 전송할 때나 읽어올 때 가비지량을 최소화
  - GC 회수 감소 &rarr; 서버 전체 처리량 증가

### NIO 

- 자바 1.4 버전에 `java.nio` 패키지가 추가됨
- 자바 1.7 버전에 NIO.2가 `java.nio.file`로 묶여 추가됨
- Buffer, CharsetDecoder, Channel, Selector 지원

- 양방향 입출력 가능 &rarr; 입력과 출력 스트림을 따로 생성하지 않아도 된다.

| 구분                         | IO                                            | NIO                                                 |
| ---------------------------- | --------------------------------------------- | --------------------------------------------------- |
| 입출력 방식                  | Stream                                        | Channel                                             |
| 버퍼 방식                    | Non-Buffer                                    | Buffer                                              |
| 비동기 방식                  | X                                             | O                                                   |
| Blocking / Non-Blocking 방식 | Blocking Only                                 | Both                                                |
| Use Case                     | 연결 클라이언트가 적고, IO가 큰 경우 (대용량) | 연결 클라이언트가 많고 IO 처리가 작은 경우 (저용량) |

### InputStream과 OutputStream

#### InputStream 메서드

| **메서드 명**                        | **설 명**                                                    |
| ------------------------------------ | ------------------------------------------------------------ |
| int available()                      | 스트림으로부터 읽어 올 수 있는 데이터의 크기를 반환한다.     |
| void close()                         | 스트림을 닫음으로써 사용하고 있던 자원을 반환한다.           |
| void mark(int readlimit)             | 현재 위치를 표시해 놓는다. 후에 reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다. readlimit: 되돌아갈 수 있는 byte의 수 |
| boolean markSupported()              | mark()와 reset()을 지원하는 지를 알려 준다. 이 메서드로 먼저 확인을 해야한다. |
| abstract int read()                  | 1 byte를 읽어온다. 더 이상 읽을 데이터가 없다면 -1을 반환한다. |
| int read(byte[] b)                   | 배열 b의 크기만큼 읽어서 배열을 채우고 읽어 온 데이터의 수를 반환한다. |
| int read(byte[] b, int off, int len) | 최대 len 개의 byte를 읽어서 배열 b의 위치off부터 저장한다.   |
| void reset()                         | 스트림에서 위치를 마지막으로 mark()이 호출되었던 위치로 되돌린다. |
| long skip(long n)                    | 스트림에서 주어진 길이n만큼 건너뛴다.                        |

#### OutputStream 메서드

| **메서드 명**                          | **설 명**                                                    |
| -------------------------------------- | ------------------------------------------------------------ |
| void close()                           | 입력소스를 닫음으로써 사용하고 있던 자원을 반환한다.         |
| void flush()                           | 스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다.            |
| abstract void write(int b)             | 주어진 값을 출력소스에 쓴다.                                 |
| void write(byte[] b)                   | 주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다.          |
| void write(byte[] b, int off, int len) | 주어진 배열 b에 저장된 내용 중에서 off번째 부터 len개 만큼만을 읽어서 출력소스에 쓴다. |



### Byte와 Character 스트림

#### Byte Stream

- binary 데이터를 입출력하는 스트림
- 데이터는 1바이트 단위로 처리
- 이미지, 동영상 등을 송수신 할 때 주로 사용

### 바이트 배열 읽기

```java
import java.io.ByteArrayInputStream;
import java.io.IOException;
public class ReadDataByteArray {
  public static void main(String[] args) throws IOException {
    byte[] b = {20,30,40,50,60};
    ByteArrayInputStream by = new ByteArrayInputStream(b);
    byte[] buf = new byte[3];
    int n = by.read(buf);
    
    System.out.println("Number of bytes read: " + n);
    
    for(byte c : buf) {
      System.out.println(c);
    }
  }
}
```

```java
Number of bytes read: 3
20
30
40
```

### available()

```java
import java.io.ByteArrayInputStream;
public class AvailableData {
  public static void main(String[] args) {
    byte[] b = {20,30,40,50,60,70,80,90,100};
    ByteArrayInputStream by = new ByteArrayInputStream(b);
    
    System.out.println("Available number of bytes to read: " + by.available());
    System.out.println(by.read());
    System.out.println(by.read());
    System.out.println(by.read());
    System.out.println("Available number of bytes to read: " + by.available());
  }
}
```

```jav
Available number of bytes to read: 9
20
30
40
Available number of bytes to read: 6
```

### Character Stream

- text 데이터를 입출력하는 스트림

- 데이터는 2바이트 단위로 처리

- 일반적인 텍스트 및 JSON, HTML 등을 송수신할 때 주로 사용

  

### 표준 스트림 (System.in, System.out, System.err)

- 자바 어플리케이션 실행과 동시에 자동적으로 생성됨

- System.in - 콘솔로부터 데이터를 입력받는데 사용

- System.out, System.err - 콘솔로부터 데이터를 출력하는데 사용

  

### 파일 읽고 쓰기

### 메서드

| Method              | Type     | Description                      |
| :------------------ | :------- | :------------------------------- |
| `canRead()`         | Boolean  | 읽기가 가능한 파일인지 테스트    |
| `canWrite()`        | Boolean  | 쓰기가 가능한 파일인지 테스트    |
| `createNewFile()`   | Boolean  | 비어있는 파일 생성               |
| `delete()`          | Boolean  | 파일 삭제                        |
| `exists()`          | Boolean  | 파일이 존재하는지 테스트         |
| `getName()`         | String   | 파일 이름 변환                   |
| `getAbsolutePath()` | String   | 파일의 절대 경로 반환            |
| `length()`          | Long     | 파일의 용량을 바이트 단위로 반환 |
| `list()`            | String[] | 디렉토리에 있는 파일의 배열 반환 |
| `mkdir()`           | Boolean  | 디렉토리 생성                    |

### 파일 정보 출력

```java
import java.io.File; 

public class GetFileInfo {  
  public static void main(String[] args) {  
    File myObj = new File("filename.txt");
    if (myObj.exists()) {
      System.out.println("File name: " + myObj.getName()); 
      System.out.println("Absolute path: " + myObj.getAbsolutePath()); 
      System.out.println("Writeable: " + myObj.canWrite()); 
      System.out.println("Readable: " + myObj.canRead()); 
      System.out.println("File size in bytes: " + myObj.length());
    } else {
      System.out.println("The file does not exist.");
    }
  }  
} 

```

```java
File name: filename.txt
Absolute path: C:\Users\MyName\filename.txt
Writeable: true
Readable: true
File size in bytes: 0
```

### 읽기

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {  
  public static void main(String[] args) {  
    try {
      File myObj = new File("filename.txt");
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
  }  
} 
```

### 쓰기

```java
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {  
  public static void main(String[] args) {  
    try {  
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
  }  
} 
```



---

***Source***

- 자바의 정석 Chapter 15
- 열혈 Java 프로그래밍 Chapter 33

- https://javanitto.tistory.com/11

- https://www.baeldung.com/java-io-vs-nio

- https://www.w3schools.com/java/java_files_read.asp

- https://www.baeldung.com/java-inputstream-to-outputstream

- https://ohjongsung.io/2019/09/07/java-nio-%ED%86%BA%EC%95%84%EB%B3%B4%EA%B8%B0

- https://coding-factory.tistory.com/281

- https://www.tutorialcup.com/ko/java/bytearrayinputstream.htm

- https://www.tutorialcup.com/ko/java/bytearrayinputstream.htm

- https://bingbingpa.github.io/java/whiteship-live-study-week13/

- https://www.geeksforgeeks.org/character-stream-vs-byte-stream-java/