# Week10. 멀티쓰레드 프로그래밍

## 학습할 것 (필수)

- Thread 클래스와 Runnable 인터페이스
- 쓰레드의 상태
- 쓰레드의 우선순위
- Main 쓰레드
- 동기화
- 데드락

<br><br>

# 1. Thread클래스와 Runnable 인터페이스

## [1] 프로세스와 쓰레드

### (1) 프로세스(Process)

- 단순히 실행 중인 프로그램이라고 볼 수 있다.
- 사용자가 작성한 프로그램을 `운영체제(OS)로 부터 메모리 공간을 할당받아 실행 중인 프로그램`을 말한다.

### (2) 쓰레드(Thread)

- 프로세스 내 `작업을 수행하는 주체`를 말한다.

- 모든 `프로세스는 최소 1개 이상의 쓰레드가 존재`하며 작업을 수행한다.

- 멀티 쓰레드 프로세스

  는 쓰레드 두 개 이상을 가지는 프로세스를 말한다.

  - 멀티 쓰레드들은 `code`,  `data`, `heap` 영역을 공유한다.
    - code : 실행할 코드를 저장하는 영역
    - data : 전역변수, 정적변수, 배열, 구조체 등이 저장되는 공간
    - heap : 사용자(프로그래머)가 직접 관리해야하는 메모리 영역

## [2] 쓰레드(Thread) 사용법

1. `Thread`를 상속(Extends) 받아서 사용한다.
2. `Runnable`을 구현(Implements) 해서 사용한다.

### (1) Thread class

1. Thread를 상속(Extends)받은 객체를 생성하고,

2. start() 메서드를 호출(call)해서 쓰레드를 실행한다.

   - start()메서드
     1. 기능 : Thread Object의 run 메서드를 끌어온다(invoke).
     2. 사용 목적 : 쓰레드를 위한 별도의 `call stack을 분리`하기 위함.

   ```java
   // Java code for thread creation by extending
   // the Thread class
   class MultithreadingDemo extends Thread {
   	public void run()
   	{
   		try {
   			// Displaying the thread that is running
   			System.out.println(
   				"Thread " + Thread.currentThread().getId()
   				+ " is running");
   		}
   		catch (Exception e) {
   			// Throwing an exception
   			System.out.println("Exception is caught");
   		}
   	}
   }
   
   // Main Class
   public class Multithread {
   	public static void main(String[] args)
   	{
   		int n = 8; // Number of threads
   		for (int i = 0; i < n; i++) {
   			MultithreadingDemo object
   				= new MultithreadingDemo();
   			object.start();
   		}
   	}
   }
   ```

   <br>

### (2) Runnable interface

1. java.lang.Runnable interface를 구현(Implements)한 클래스를 생성하고,
2. run() method를 재정의(override)한다.
3. 그리고 Thread 객체를 생성하고 start method를 호출한다.

```java
class MultithreadingDemo implements Runnable {
	public void run()
	{
		try {
			// Displaying the thread that is running
			System.out.println(
				"Thread " + Thread.currentThread().getId()
				+ " is running");
		}
		catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}
}

// Main Class
class Multithread {
	public static void main(String[] args)
	{
		int n = 8; // Number of threads
		for (int i = 0; i < n; i++) {
			Thread object
				= new Thread(new MultithreadingDemo());
			object.start();
		}
	}
}
```

<br>

### [3] Thread vs Runnable

1. 상속여부

   - Thread는 class이기 때문에 상속을 받을 수 없다.
   - Runnable는 interface이므로 상속이 가능하다.

2. Thread를 상속하면 `yield()`, `interrupt()`와 같은 inbuilt method를 사용할 수 있다.

   (Runnable interface는 사용이 불가능하다.)

   - `yield()` : 다른 스레드에게 실행을 양보하는 메서드

   - `interrupt()` :  스레드가 일시 정지 상태(sleep) 시, InterruptedException 예외를 발생시킨다.

     (실행 상태에 사용 시, 스레드가 sleep상태가 되면 InterruptedException 발생하며 종료.)

3. Runnable을 사용시, 여러 thread를 공유할 수 있는 객체를 얻을 수 있다.

<br>

### [4] 쓰레드 메서드

1. sleep() : 해당 쓰레드(클래스에 속한 thread?)를 매개 변수로 넘어온 시간(milliseconds)만큼 대기한다.

   - sleep메서드는 try-catch문으로 감싸주어야 한다.
   - 기본적으로 catch에는 `InterruptException`으로 예외처리를 해야 한다.

   ```java
   public class SleepTest {
       public static void main(String[] args) {
           long start = System.currentTimeMillis();
   
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
   
           long end = System.currentTimeMillis();
   
           System.out.println(end - start);
       }
   }
   
   //결과 : 1003
   ```

   <br>

2. join()

   - 지정된 시간동안 쓰레드가 실행되도록 한다.

   - join()을 호출한 쓰레드는 그동안 일시정지 상태가 된다.

   - 일정 시간이 지나거나 작업이 종료되면 join()을 호출한 쓰레드로 다시 돌아와 실행을 계속한다.

     ```java
     public class MyThread5 extends Thread{
             public void run(){
                 for(int i = 0; i < 5; i++){
                     System.out.println("MyThread5 : "+ i);
                     try {
                         Thread.sleep(500);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
     		        }
     		    } // run
     }
     
     public class JoinExam { 
             public static void main(String[] args) {
                 MyThread5 thread = new MyThread5();
                 // Thread 시작 
                 thread.start(); 
                 System.out.println("Thread가 종료될때까지 기다립니다.");
                 try {
                     // 해당 쓰레드가 멈출때까지 멈춤
                     thread.join();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("Thread가 종료되었습니다."); 
             }   
         }
     
     //결과
     /*
     Thread가 종료될때까지 기다립니다.
             MyThread5 : 0
             MyThread5 : 1
             MyThread5 : 2
             MyThread5 : 3
             MyThread5 : 4
             Thread가 종료되었습니다.
     */
     ```

     <br>

3. interrupt()

   - 스레드가 일시 정지 상태에 있을 때 InterruptException 예외를 발생시키는 역할.
   - 해당 쓰레드는 InterrupException을 발생함으로써 일시정지 상태를 벗어나게 된다.

   ```java
   public class PrintThread extends Thread {
       @Override
       public void run() {
           try {
               while (true) {
                   System.out.println("실행중");
                   Thread.sleep(100);
               }
           } catch (InterruptedException e) {
           }
           System.out.println("자원 정리");
           System.out.println("실행 종료");
       }
   }
   
   public class InterruptExample {
       public static void main(String[] args) {
           Thread thread = new PrintThread();
           thread.start();
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
           }
           thread.interrupt();
       }
   }
   
   /*
   	실행중
   	실행중
   	실행중
   	실행중
   	실행중
   	실행중
   	자원 정리
   	실행 종료
   */
   ```

   <br>

4. Thread 상태 확인 메서드

   - __void checkAcess()__ : 현재 수행중인 쓰레드가 해당 쓰레드를 수정할 수 있는 권한이 있는지를 확인.
   - __boolean isAlive()__ : 쓰레드가 살아 있는지를 확인한다.
   - __boolean isInterrupted()__ : run() 메서드가 정상적으로 종료되지 않고 interrupt() 메서드의 호출을 통해서 종료되었는지를 확인하는데 사용
   - __static boolean interrupted()__ : 현재 쓰레드가 중지되었는지를 확인.

<br><br>

# 2. 쓰레드 상태(Thread.State)

- `NEW` : 쓰레드 객체는 생성되었지만, 시작하지 않은 상태
- `RUNNABLE` : 쓰레드가 실행중인 상태
- `BLOCKED` : 쓰레드가 실행 중지 상태이며, 모니터 락(monitor lock)이 푸리기를 기다리는 상태
- `WAITING` : 쓰레드가 대기중인 상태
- `TIMED_WAITING` : 특정 시간만큼 쓰레드가 대기중인 상태
- `TERMINATED` : 쓰레드가 종료된 상태

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/eba7425b-853e-42f4-a9ad-e2b6ec4879ef/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210612%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210612T124609Z&X-Amz-Expires=86400&X-Amz-Signature=9550cbfc09696d0c42d8436c77b696d2269339b1ca071ce05d168599085ef678&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

<br><br>

# 3. 쓰레드 우선순위

- 멀티 쓰레드 환경에서 쓰레드 스케줄러는 우선순위를 기반해서 스레드에 프로세서를 할당한다.

- 우선 순위는 thread가 생성되는 동안 JVM에 의해 할당되거나,

  프로그래머가 명시적으로 표시한다.

  <br>

### [1] 쓰레드 우선 순위 변수

- public static int MIN_PRIORITY : 가장 낮은 우선 순위(value = 1)

- public static int NORM_PRIORITY : 가장 기본적인 우선 순위(value = 5)

- public static int MAX_PRIORITY : 가장 높은 우선 순위(value = 10)

  <br>

### [2] 우선 순위 관련 메서드

- public final int getPriority()

  - thread에 부여된 우선 순위를 반환.

- public final void setPriority(int new Priority)

  - 새로운 우선 순위 변수를 설정.

  - 1(minimum) ≤ newPriority ≤ 10(maximum)

    (범위 외 값을 적용할 경우 IllegalArgumentException 발생)

```java
import java.lang.*;

class ThreadDemo extends Thread {
	public void run()
	{
		System.out.println("Inside run method");
	}

	public static void main(String[] args) {
		
		ThreadDemo t1 = new ThreadDemo();
		ThreadDemo t2 = new ThreadDemo();
		ThreadDemo t3 = new ThreadDemo();

		// Default 5
		System.out.println("t1 thread priority : " + t1.getPriority());
		
		// Default 5
		System.out.println("t2 thread priority : " + t2.getPriority());
		
		// Default 5
		System.out.println("t3 thread priority : " + t3.getPriority());

		t1.setPriority(2);
		t2.setPriority(5);
		t3.setPriority(8);
		
		// 2
		System.out.println("t1 thread priority : " + t1.getPriority());
		
		// 5
		System.out.println("t2 thread priority : " + t2.getPriority());
		
		// 8
		System.out.println("t3 thread priority : " + t3.getPriority());

		System.out.println("Currently Executing Thread : " + 
				Thread.currentThread().getName());
		
		System.out.println(
			"Main thread priority : " + Thread.currentThread().getPriority());

		Thread.currentThread().setPriority(10);
		
		System.out.println( "Main thread priority : " +
			 Thread.currentThread().getPriority());
	}
}
```

<br><br>

# 4. 동기화(Synchronized)

### [1] 쓰레드와 공유객체

- 하나의 객체를 여러개의 Thread가 사용한다는 것을 의미.

- 예시 : [[프로그래머스\] 쓰레드와 공유객체](https://programmers.co.kr/learn/courses/9/lessons/273)

  <br>

### [2] Synchronized keyword

- 공유객체가 가진 메서드를 동시에 호출되지 않도록 하는 방법

- 사용방법 : `동기화 메서드`와 `동기화 블록`

  - 메소드 앞에 synchronized를 붙힌다.

  - 여러개의 Thread들이 공유 객체의 메소드를 사용할 때, 메소드에 synchorinized가 붙여 있을 경우 호출한 메서드가 사용권(`Monitor Lock`)을 얻는다.

  ```java
  public synchronized void playMusicA(){
          for(int i = 0; i < 10; i ++){
              System.out.println("신나는 음악!!!");
              try {
                  Thread.sleep((int)(Math.random() * 1000));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          } // for        
      } //playMusicA
  ```

  - 해당 모니터링 락은 메소드 실행이 종료되거나, wait()와 같은 메소드를 만나기 전까지 유지된다.

  - 다른 쓰레드들이 synchronized 메소드를 실행하면서 모니터링 락을 획득했더라도, 다른 synchronized가 없는 메서드를 만난다면 상관없이 실행시킨다.
  - synchronized를 메소드에 붙혀서 사용할 경우, 메소드 코드가 길어지면, `마지막에 대기하는 쓰레드가 너무 오래 기다리는 것을 막기 위해` synchorinized를 붙이지 않고, 문`제 있을 것 같은 부분만 synchronized 블록`을 사용한다.

  ```java
  public void playMusicB() {
  	for(int i = 0; i < 10; i++) {
  		synchronized(this) {
  			System.out.println("슬픈 음악!!!");
  		}
  	}
  
  	try {
  	  Thread.sleep((int)(Math.random() * 1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  ```

  <br><br>

  # 5. 데드락(교착상태, Deadlock)

  ### [1] 데드락이란?

  - 두 쓰레드가 서로의 lock을 획득하기 위해 대기하고 있는 상태

    - Process1(ResourceA를 점유한 상태)가 ResourceB를 점유하기를 위해 대기하고 있는 상태
    - Process2(ResourceA를 점유한 상태)가 ResourceA의 점유하기를 위해 대기하고 있는 상태

    ![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/553d35f6-9ffc-4067-8f04-ee7c3c46d9e1/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210612%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210612T124927Z&X-Amz-Expires=86400&X-Amz-Signature=af4c5cfa3c8ea13dafeb520c0af332a94971175b8fd627315831cb498cd92802&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

    <br>

  ### **[2] Dead Lock 해결 방법**

  1. **상호배제(Mutual Exclusion)** : 자원은 한 번에 한 프로세스만 사용할 수 있어야 한다.
  2. **점유 대기 (Hold and wait)** : 최소한 하나의 자원을 점유하고 있으면서 다른 프로세스에 할당되어 사용하고 있는 자원을 추가로 점유하기 위해 대기하는 프로세스가 있어야 한다.
  3. **비선점(No preemption)** : 다른 프로세스에 할당된 자원은 사용이 끝날때까지 강제로 빼앗을 수 없어야 한다.
  4. **순환 대기(Circular wait)** : 프로세스의 집합{P0, P1, ... Pn}에서 P0는 P1이 점유한 자원을 대기하고 P1은 P2가 점유한 자원을 대기하고 P2...Pn-1은 Pn이 점유한 자원을 대기하며 Pn은 P0가 점유한 자원을 요구해야 한다.

  출처 : https://jwprogramming.tistory.com/12

<br><br>

## Reference  

- 메모리 구조 - TCP School

  http://tcpschool.com/c/c_memory_structure

- 10주차 과제 : 멀티쓰레드 프로그래밍(하트 2개)

  https://sujl95.tistory.com/63

- Multithreading in java (geeksforgeeks)

  https://www.geeksforgeeks.org/multithreading-in-java/?ref=lbp

- Java Thread Priority in Multithreading(geeksforgeeks)

  https://www.geeksforgeeks.org/java-thread-priority-multithreading/?ref=rp

- [java]interrupt() 메서드를 이용하며 스레드 정지시키기

  https://cornswrold.tistory.com/190

- Deadlock-in-java-multithreading

  https://www.geeksforgeeks.org/java-thread-priority-multithreading/?ref=rp

- [프로그래머스] 쓰레드와 공유객체

  https://programmers.co.kr/learn/courses/9/lessons/273

- [프로그래머스] 동기화 메서드와 동기화 블록

  https://programmers.co.kr/learn/courses/9/lessons/274

