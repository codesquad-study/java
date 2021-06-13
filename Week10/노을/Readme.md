> 💾 관련이슈: [https://github.com/whiteship/live-study/issues/](https://github.com/whiteship/live-study/issues/10)


# 프로세스와 스레드

## 프로세스
![](https://images.velog.io/images/san/post/449a36fd-ffdf-44b8-93e8-f78568d5a076/image.png)
 - 이미지 출처: https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html

 <br>

 - 운영체제(자바는 JVM)에 의해 메모리 공간을 할당 받아 실행중인 프로그램
 - 기본적으로 프로세스당 최소 1개의 스레드(메인 스레드)를 가지고 있음.
 - 한 프로세스는 다른 프로세스의 변수나 자료구조에 쉽게 접근할 수 없음
   - 프로세스 간의 통신(IPC)를 사용해야 통신 가능
 - **하지만, 자바에는 프로세스가 없음**

  - 프로세스 구성
     1. 프로그램에 사용되는 데이터와 메모리 등의 `자원`
     2. **스레드**
## 스레드
![](https://images.velog.io/images/san/post/3321b63f-8ffe-47a9-b4dd-2211b254096a/image.png)
 - 이미지 출처: https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html
 <br>

  - 프로세스가 할당받은 자원을 이용하는 실행의 단위
  - “프로세스 내에서 실행되는 여러 흐름의 단위”
  - 스레드란 프로그램의 실행을 `추상화`한 것
    - 구체적으로, 프로그램 카운터(PC)와 실행 컨텍스트 일부를 보기 좋게 추상화 한 것
  - 스레드가 가져야 하는 것
    - 프로그램 카운터
      - 프로그램이 어디까지 실행됐는지 알아야하므로, 필요
      - 따라서, 스레드는 `실행의 흐름`이다. 
    - 스택
      - 스택이 있다는 것은 스레드가 `메소드 단위`로 실행 할 수 있다는 것을 의미
      - 스레드는 `메소드 단위`로 흐름 제어


### 다수의 스레드가 공유 데이터에 대한 경쟁 상태가 발생하면 잘못된 결과가 발생하므로 스레드 동기화를 프로그램에 구현하여야 한다.

# Thread 클래스와 Runnable 인터페이스

- 스레드를 만드는 방법 2가지
  1. Thread 상속
  2. Runnable 함수형 인터페이스  구현
- start() 호출 



# 쓰레드의 상태
![](https://images.velog.io/images/san/post/3dfe1dc5-481b-4398-8690-a6983177c214/image.png)

|         상태          |                             설명                             |
| :-------------------: | :----------------------------------------------------------: |
|          NEW          |     쓰레드가 생성되고 아직 start()가 호출되지 않은 상태      |
|       RUNNABLE        |                실행 중 또는 실행 가능한 상태                 |
|        BLOCKED        | 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태) |
| WATING, TIMED_WAITING | 쓰레드의 작업이 종료되지는 않았지만 실행 가능하지 않은 일시정지 상태, TIMED_WATING은 일시정지 시간이 지정된 경우를 말함 |
|      TERMINATED       |                 쓰레드의 작업이 종료된 상태                  |



# 쓰레드의 우선순위

- 쓰레드가 가질 수 있는 우선순위 `범위 1~10` ( 숫자▲, 우선순위▲)
- 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받음.
- 쓰레드를 `실행하기 전`에만 우선 순위를 `변경`할 수 있다.
 > 멀티코어에서는 쓰레드의 우선순위에 따른 차이가 전혀 없었다.
 >  따라서 쓰레드에 높은 우선 순위를 준다고, 더 많은 실행시간과 실행기회를 갖게 될 것이라고  기대할 수 없다.차라리 쓰레드에 우선순위를 부여하는 대신, 작업에 우선순위를 두어 PriorityQueue에 저장해 놓고, 우선순위가 높은 작업이 먼저 처리되도록 하는 것이 나을 수 있다.
 >
 > <자바의 정석>



# Main 쓰레드
- 프로그램이 시작하면 가장 먼저 실행되는 쓰레드
- 모든 쓰레드는 메인 쓰레드로부터 생성
- 다른 쓰레드를 생성해서 실행하지 않으면, 메인 쓰레드가 종료되는 순간 프로그램도 종료
- 여러 쓰레드를 실행하면, 메인 쓰레드가 종료되더라도 다른 쓰레드가 작업을 마칠 때까지 종료하지 않음



# 자바는 언제나 멀티 쓰레드
- main 쓰레드를 실행하면 , 일반 쓰레드를 생성하지 않았더라도 GC(데몬 스레드)를 포함한 여러 쓰레드들이 백그라운드에서 실행되고있음.

```java
public class Task extends Thread{

    @Override
    public void run() {
        this.setName("CustomThreadName");
        System.out.printf("I am thread %s (%d)\n",this.getName(),this.getId());
    }

    public static void main(String[] args) {
        System.out.println("Main start!");
        // 메인 스레드에서 이름을 알아내는 법
        Thread mainThread = Thread.currentThread();
        System.out.printf("I am thread %s (%d)\n",mainThread.getName(),mainThread.getId());

        Thread t = new Task();
        t.run();
        System.out.println("Main thread end");
    }
}
-------------
Main start!
I am thread main (1)
I am thread CustomThreadName (12) ✋ 한 개 만들었지만 스레드 번호가 2가 아님
Main thread end
```


# 쓰레드의 실행제어
- 효율적인 멀티쓰레드 프로그램을 만들기 위해서는 보다 정교한 스케줄링을 통해, 프로세스에게 주어진 자원과 시간을 여러 쓰레드가 낭비없이 잘사용 하도록 프로그래밍 해야한다.
- 쓰레드 프로그래밍 어려운 이유는 동기화(Synchronization)와 스케줄링(Scheduling) 떄문이다.
- 쓰레드 스케줄링을 잘하기 위해서는 쓰레드의 상태와 관련된 메서드를 잘 알아야함.
> slee(), join() , interrupt(), yeild()


|               메서드               |                             설명                             |
| :--------------------------------: | :----------------------------------------------------------: |
|   static void sleep(long millis)   |          지정된 시간동안 쓰레드를 일시정지 시킨다.           |
| void join() void join(long millis) | 지정된 시간동안 쓰레드가 실행되도록 한다. join()을 호출한 쓰레드는 그동안 일시정지 상태가 된다. |
|          void interrupt()          | sleep()이나 join()에 의해 일시정지 상태인 쓰레드를 깨워서 실행대기 상태로 만든다. 해당 쓰레드에서는 InterruptedException이 발생함으로써 일시정지 상태를 벗어나게 된다. |
|        static void yield()         | 실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보하고 자신은 실행대기 상태가 된다. |


# 동기화 문제

- 멀티스레드를 이용해 공유 변수의 접근할 경우 동기화 문제가 발생할 수 있음
- 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 `쓰레드의 동기화`

- 동기화 방법
  - synchronized 메소드 또는 블록
    - synchronized method는 자기 자신(this)에 대해 lock을 걸고 수행함
    - **공유 객체에 대해서만 동작**하고, primitive에 대해서는 동작하지 않으므로 정상 동작을 항상 점검
    - 관련 Object 메소드: wait(), notify()
  - volatile
    - Java 변수를 Main Memory에 저장하여 공유
  - atomic
    - lock 없이 동기화 처리

# 데드락

- 교착상태
  - 한 자원을 여러 시스템이 사용하려고 할 때 발생
- 교착상태시 동시에 발생하는 4가지 조건

 1) 상호 배제 (Mutual exclusion)
 2) 점유 대기 (Hold and wait)
 3) 비선점 (No preemption)
 4) 순환 대기 (Circular wait)
  - 하나라도 성립하지 않는다면 교착상태 X 

----

# Thread가 여러 개 일때 구분할 방법이 필요하다면?
- Thread 객체 한테 매게변수를 어떻게 전달해줄까?
- `public void run()` 메소드는 반환값과 매개변수가 존재하지 않는다.

- 스레드 클래스에 생성자 함수를 만든다.

```java

public class Task2 extends Thread{

    private int customId;
    private int size;
    public Task2(int customID, int size){
        this.customId = customID;
        this.size = size;
    }
    @Override
    public void run(){
        for(int i =0; i< size;i++){
            if(customId % 2 == 0){
                System.out.println("*****");
            }else{
                System.out.println("......");
            }

        }
    }

```

# 스레드를 늘려도 IO작업은 분산이 안된다.
- **운영체제가 `시스템콜`이란 것으로 IO를 대신해주기 때문이다.**
- ex) 표준출력(`println`)



---
# Reference

- https://sujl95.tistory.com/63
- https://wisdom-and-record.tistory.com/48
- https://parkadd.tistory.com/48
- https://www.notion.so/Thread-5fdb5d603a6a473186bf2738f482cedc
- https://www.notion.so/1be14f916be0476d8da2405203b740db