# 13주차 과제: I/O



# 스트림 (Stream)

- 데이터를 운반하는데 사용되는 `단방향` 연결 통로
- 📻 카세트 테이프를 생각하면 이해하기 쉽다.
- 입력(`Rec`)과 출력(`Play`)을 동시에 처리할 수 없음
- 스트림은 FIFO 구조와 같이, 먼저 보낸 데이터를 먼저 받게 돼있음.
- 종류
    - 바이트기반 스트림 - InputStream, OutputStream
    - 보조 스트림
        - 스트림의 기능을 보완하기 위해 제공
        - 자체적으로 입출력을 수행 ❌
        - 기반 스트림을 먼저 생성한 다음 이를 이용해 보조 스트림을 생성 해야함.
    - 문자 기반 스트림 - Reader, Writer
        - 바이트 기반은 입출력의 단위가 `1 바이트` 이다.
        - C언어와 달리 Java의 char형은 `2 바이트` 이기 때문에, 문자를 처리하는 데 어려움이 있음.
        - 이러한 점을 보완하기 위해 문자 기반 스트림 제공
        - `인코딩 변환` 제공

# 버퍼 (Buffer)

- `NIO`에서 제공하는 Buffer클래스
- 커널에 의해 관리되는 시스템 메모리를 직접 사용할 수 있는 Buffer 클래스
- `채널`을 통해 접근
- 채우기, 비우기, 뒤집기, 뒤로가기 등의 다양한 조작 연산을 제공

# 채널 (Channel) 기반의 I/O

- 읽기 쓰기 둘 다 가능한 `양방향식` 입출력 클래스
- 효율적인 IO처리 (시스템 콜 수 줄이기, 모아서 처리하기)

# NIO (New Input/Output)

- 자바는 직접적인 메모리 접근과, 시스템 콜을 사용하지 못해 IO 작업이 느리다.
- 이러한 속도를 개선하는 방법은 메모리를 직접 접근하는 듯!하게 사용하고 시스템 콜을 직접 콜하는 듯!하게 하는 방법이다. + 동기/비동기 제어
- 위 방법을 사용해서 I/O의 성능 문제를 개선하는 것이 바로 NIO
- 자바 4부터 추가된 새로운 입출력

| 구분                   | IO                 | NIO                              |
| ---------------------- | ------------------ | -------------------------------- |
| 입출력 방식            | 스트림 방식        | 채널 방식                        |
| 버퍼 방식              | 넌버퍼             | 버퍼                             |
| 비동기 방식            | 지원 안함          | 지원                             |
| 블로킹 / 넌블로킹 방식 | 블로킹 방식만 지원 | 블로킹 / 넌블로킹 방식 모두 지원 |

> `IO`에서는 `출력 스트림`이 1바이트를 쓰면 `입력 스트림`이 1바이트를 읽는다. 
이것보다는 `버퍼`를 사용해서 복수 개의 바이트를 한꺼번에 입력 받고 출력하는 것이 빠른 성능을 낸다. 
그래서 `IO`는 `버퍼`를 제공해 주는 `보조 스트림`인 `BufferedInputStream`, `BufferedOutputStream`을 연결해서 사용하기도 한다. 

`NIO`는 기본적으로 `버퍼`를 사용해서 입출력을 하므로 IO보다는 성능이 좋다. 
`채널`은 `버퍼` 에 저장된 데이터를 출력하고, 입력된 데이터를 버퍼에 저장한다.

[https://brunch.co.kr/@myner/47](https://brunch.co.kr/@myner/47)

하지만, NIO가 만능은 아니고, 대용량 데이터를 처리하는 데 있어서는 IO 보다 불리할 수 있다.



# InputStream와 OutputStream

- 모든 `바이트기반의 스트림`의 조상

- `InputStream`은 소스로부터 데이터를 읽어들이는 용도

- `OutputStream`은 어떤 대상에 데이터를 쓰는 용도

  

# 표준 스트림 (System.in, System.out, System.err)

| 표준 입출력 |                             설명                             |
| :---------: | :----------------------------------------------------------: |
|  System.in  | 콘솔로부터 데이터를 입력받는데 사용<br />System.in, InputStream 인스턴스 |
| System.out  | 콘솔로 데이터를 출력하는데 사용<br />System.out, PrintStream 인스턴스 |
| System.err  | 콘솔로 데이터를 출력하는데 사용<br />System.out, PrintStream 인스턴스<br />OS에 의해 버퍼링되지 않고 즉시 출력 |



# 파일 읽고 쓰기

### ChannelDemo.java

- [https://www.tutorialspoint.com/java_nio/java_nio_channels.htm](https://www.tutorialspoint.com/java_nio/java_nio_channels.htm)



## 파일 입출력 스트림

- 텍스트 파일 복사 예제

```java
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        try(FileInputStream in = new FileInputStream("src/noeul.txt");
            FileOutputStream out = new FileOutputStream("src/copy_noeul.txt")){
            
            int cursor;
            
            while((cursor = in.read()) != -1){
                out.write(cursor);
            }
        }
    }

```



## 파일 입출력 스트림 - 보조 스트림

- 텍스트 파일 복사 예제
- 위 예제와 로직 상 큰 차이 없음

```java
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/noeul.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("src/copy_noeul.txt"))){

            int cursor;

            while((cursor = in.read()) != -1){
                out.write(cursor);
            }

        }
    }
```



## 문자 기반 스트림 - 보조 스트림

- 텍스트 파일 복사 예제
- 라인 종료문자 (\r\n, \r, \n) 단위로 문자를 읽는다.

```java
public class CopyLine {

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader("src/noeul.txt"));
             PrintWriter out = new PrintWriter(new FileWriter("src/copy_noeul.txt"))) {

            String line;

            while ((line = in.readLine()) != null) {
                out.print(line);
            }
        }
    }
}
```

---



# 참고

- [https://velog.io/@honux/백기선-자바-스터디-13-IO](https://velog.io/@honux/%EB%B0%B1%EA%B8%B0%EC%84%A0-%EC%9E%90%EB%B0%94-%EC%8A%A4%ED%84%B0%EB%94%94-13-IO)
- [https://velog.io/@dion/백기선님-온라인-스터디-13주차-IO#스트림-stream--버퍼-buffer--채널-channel-기반의-io](https://velog.io/@dion/%EB%B0%B1%EA%B8%B0%EC%84%A0%EB%8B%98-%EC%98%A8%EB%9D%BC%EC%9D%B8-%EC%8A%A4%ED%84%B0%EB%94%94-13%EC%A3%BC%EC%B0%A8-IO#%EC%8A%A4%ED%8A%B8%EB%A6%BC-stream--%EB%B2%84%ED%8D%BC-buffer--%EC%B1%84%EB%84%90-channel-%EA%B8%B0%EB%B0%98%EC%9D%98-io)
- [https://brunch.co.kr/@myner/47](https://brunch.co.kr/@myner/47)
- [https://jeong-pro.tistory.com/145](https://jeong-pro.tistory.com/145)
- 자바의 정석