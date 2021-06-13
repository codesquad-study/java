# 멀티쓰레드 프로그래밍

- Thread 클래스와 Runnable 인터페이스
- 쓰레드의 상태
- 쓰레드의 우선순위
- Main 쓰레드
- 동기화
- 데드락



## Thread 클래스와 Runnable 인터페이스

### Process

실행중인 프로그램을 의미 (a program in execution)
- 프로그램을 실행하면 OS로부터 실행에 필요한 자원(메모리)를 할당받아 프로세스가 된다.
- 프로세스는 자원(프로그램을 수행하는 데 필요한 데이터와 메모리)과 쓰레드로 구성된다.
  - 모든 프로세스에는 최소 1개 이상의 쓰레드가 존재한다.
   - 프로세스가 둘 이상의 쓰레드를 가질 경우 multi-threaded process라고 부른다. 
  > **싱글쓰레드** = 자원 + 쓰레드
  > **멀티쓰레드** = 자원 + 쓰레드 + 쓰레드 + ...



### Thread

- 프로세스의 자원을 이용해서 실제로 작업을 수행
※ 경량 프로세스(LWP, light-weight process)라고 부르기도 한다.
- 한 번 종료된 쓰레드는 다시 실행할 수 없다. 
  &rarr; 하나의 쓰레드에 대해 start()를 두 번  이상 호출하면 IllegalThreadStateException이 발생한다. 
- 모든 쓰레드는 독립적인 작업을 수행하기 위한 자신만의 호출스택을 필요로 한다. 
  &rarr; call stack은 thread가 생성될 때마다 생성되고, 종료시 함께 소멸된다.
  &rarr; 생성된 call stack들은 스케줄러가 정한 순서에 의해 번갈아 가며 실행된다. 
  &rarr; 실행중인 user thread가 하나도 없다면 프로그램은 종료된다. 
> 📌 java에서 main 메서드를 실행하기 위해서도 main 쓰레드가 필요하다. 프로그램이 실행되면 main 쓰레드가 기본적으로 생성되고, main 쓰레드가 main 메서드를 호출함으로써 작업이 수행될 수 있다.

<br/>

### Runnable 인터페이스

- Thread는 Thread 클래스를 상속 받거나 Runnable인터페이스를 implement 함으로써 구현할 수 있다.

- Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에 일반적으로 Runnable 인터페이스를 구현한다.

**Thread 클래스의 상속**

```java
class MyThread extends Thread { public void run() {...} }
```

**Runnable 인터페이스 구현**

```java
class MyThread implements Runnable { public void run() {...} }
```

- Runnable 인터페이스를 사용하면 재사용성이 높고 코드의 일관성을 유지할 수 있다.

  > Runnable 인터페이스: 추상메서드인 run()만 정의되어 있는 인터페이스이다.
  >  `public interface Runnable { public abstract void run(); }`
  
- Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성하고, 그 인스턴스를 Thread 클래스 생성자의 매개변수로 제공해야 한다. 
  
  > `Thread thread = new Thread(new ThreadExample());`

```java
public class ThreadExample implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            // static Thread currentThread(): 현재 실행중인 쓰레드의 참조를 반환
            // String getName(): 쓰레드의 이름을 반환
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Main {
    public static void main (String[] args) {
        Thread threadA =new Thread(new ThreadExample());
        Thread threadB =new Thread(new ThreadExample());
        Thread threadC =new Thread(new ThreadExample());
        threadA.start(); // start(): 쓰레드 실행
        threadB.start();
        threadC.start();
    }
}
```

### 실행결과

![](https://images.velog.io/images/janeljs/post/6f8c528f-0b92-4473-8ae4-9bea859e616e/image.png)

위의 코드에서 start()가 아닌 **run()**을 호출한다면, 생성된 쓰레드가 실행되는  것이 아니라 클래스에 선언된 메서드가 호출된다.

![](https://images.velog.io/images/janeljs/post/cd55a31e-4cf3-4b06-b51c-974078159917/image.png)

따라서 새로운 쓰레드가 작업하는데 필요한 call stack을 생성한 다음에 run()을 호출하기 위해서는 쓰레드를 실행시키는 명령어인 **start()**를 사용해야 한다.



## 쓰레드의 상태

| 상태      | 열거 상수     | 설명                                                         |
| --------- | ------------- | ------------------------------------------------------------ |
| 객체 생성 | NEW           | 스레드 객체가 생성, 아직 start() 메소드가 호출되지 않은 상태 |
| 실행 대기 | RUNNABLE      | 실행 상태로 언제든지 갈 수 있는 상태                         |
| 일시 정지 | WAITING       | 다른 스레드가 통지할 때까지 기다리는 상태                    |
|           | TIMED_WAITING | 주어진 시간 동안 기다리는 상태                               |
|           | BLOCKED       | 사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태        |
| 종료      | TERMINATED    | 실행을 마친 상태                                             |

![Life Cycle of a Thread in Java | Baeldung](https://www.baeldung.com/wp-content/uploads/2018/02/Life_cycle_of_a_Thread_in_Java.jpg)



## 쓰레드의 우선순위

- getPriority(), setPriority() 메서드를 통해 우선순위 반환, 변경 가능
- 우선 순위 범위는 1~10까지 (숫자가 높을 수록 우선순위가 높다.)

|             필드             |                       설명                        |
| :--------------------------: | :-----------------------------------------------: |
| static int MAX_PRIORITY (10) |   스레드가 가질 수 있는 최대 우선순위를 명시함.   |
| static int MIN_PRIORITY (1)  |   스레드가 가질 수 있는 최소 우선순위를 명시함.   |
| static int NORM_PRIORITY (5) | 스레드가 생성될 때 가지는 기본 우선순위를 명시함. |

```
public class JavaSetPriorityExp2 extends Thread
{  
    public void run()
    {  
        System.out.println("Priority of thread is: "+Thread.currentThread().getPriority());  
    }  
    public static void main(String args[])
    {  
        // creating one thread 
        JavaSetPriorityExp2 t1=new JavaSetPriorityExp2();  
        // print the minimum priority of this thread
        t1.setPriority(Thread.MIN_PRIORITY);  
        // This will call the run() method
        t1.start();  
    }  
}
```

```
Priority of thread is: 1
```

- 쓰레드 우선순위가 10을 넘어가면 예외 발생

  ```
  Exception in thread "main" java.lang.IllegalArgumentException
  	at java.lang.Thread.setPriority(Thread.java:1089)
  	at JavaSetPriorityExp5.main(JavaSetPriorityExp5.java:13)
  ```

  

## Main 쓰레드

- main() 메서드가 실행되는 쓰레드
- single-thread application: main() 쓰레드만 있는 어플리케이션

- Daemon thread: 메인쓰레드의 작업을 돕는 쓰레드, main 쓰레드가 종료되면 데몬 쓰레드는 강제 종료된다.
  - 일정 시간마다 자동으로 수행되는 저장 및 화면 갱신 등에 이용



## 동기화

- 한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것
- thread-safe

### 동기화 방법

- synchronized

  - 클래스의 인스턴스 당 1개의 쓰레드만 해당 메서드를 실행할 수 있다.

  - 메서드 앞에 synchronized가 붙어있으면 Monitor Lock(intrinsic lock, 모든 객체가 갖고있는 고유한 락)을 획득할 수 있다.
  - Monitor Lock은 wait() 메서드를 만나거나 메서드 실행이 종료되기 전가지 유지된다.

```
public synchronized void synchronisedCalculate() {
    setSum(getSum() + 1);
}
```



## 데드락

- 교착상태
- 둘 이상의 쓰레드가 락을 획득하기 위해 대기하는데, 해당 락을 잡고있는 쓰레드도 다른 락을 기다리고 있어 block 상태에 놓이는 것
- T1은 T2가 갖고있는 lock2를 기다리고 있고, T2는 T1이 갖고있는 lock1을 대기하는 상황

```
public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        lock1.lock();
        print("lock1 acquired, waiting to acquire lock2.");
        sleep(50);

        lock2.lock();
        print("lock2 acquired");

        print("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        print("lock2 acquired, waiting to acquire lock1.");
        sleep(50);

        lock1.lock();
        print("lock1 acquired");

        print("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }

    // helper methods

}
```

```
Thread T1: lock1 acquired, waiting to acquire lock2.
Thread T2: lock2 acquired, waiting to acquire lock1.
```





---

***Source***

- 모던 자바 인 액션
- 자바의 정석
- https://sujl95.tistory.com/63

-  https://widevery.tistory.com/27

- http://tcpschool.com/java/java_thread_concept

- https://www.geeksforgeeks.org/java-thread-priority-multithreading/

- https://www.baeldung.com/java-thread-lifecycle

- https://www.baeldung.com/java-synchronized

- http://happinessoncode.com/2017/10/04/java-intrinsic-lock/

- https://www.baeldung.com/java-deadlock-livelock#:~:text=A%20deadlock%20occurs%20when%20two,the%20deadlocked%20threads%20cannot%20progress.