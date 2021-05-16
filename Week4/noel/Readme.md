# 4주차 과제: 제어문

열: 2021년 5월 14일 오전 3:17
태그: 백기선스터디

# **목표**

자바가 제공하는 제어문을 학습하세요.

# **학습할 것 (필수)**

## 선택문

- if, if-else, switch

```java
if ( score >= 90 ){
	grade = 'A';
} else if (score >= 80){ // 첫번쨰 조건이 거짓이므로, 관련 조건 생략
	grade = 'B';
}

==

if ( score >= 90 ){
	grade = 'A';
}
if ( score >= 80 && score < 90 ){
	grade = 'B'
}
```

```java
# switch 중첩

switch(gender){
	case '1' : case '3':
			switch(gender){
					case '1':
						break;
					case '3':
						break;
			}
}
```

## 반복문

- for, while, do-while

- for문 변수 한 개 이상

```java

for(int i=1, j=10; j<=10; j++, j--){..}
```

- 처음보는 for문 예제 ㅋㅋㅋ

```java
# 피보나치 수열

for(int cnt = 0, bf = 0, af = 1; cnt++ <30; System.out.print(cnt == 1 ? "1\t" : (af += bf) + (cnt %10 == 0 ? "\n" : "\t")), bf == cnt == 1 ? bf : af-bf;)

그만 알아보자.. 벌써 어지럽...
```

### 이름 붙인 반복문

- break문은 근접한 단 하나의 반복문만 벗어날 수 있다.
- 여러 개의 반복문이 중첩된 경우에는 중첩 반복문 앞에 이름을 붙이고, break문과 continue문에 이름을 지정하면 해결가능

```java
Loop1 : for(int i=2; i<=9; i++){
		for(int j=1; j<=9; j++){
			if(j==5){
				break Loop1;
			}
		}
}
```

- 그동안 중첩 반복문 break 할 때 방법을 몰라서 메소드로 분리해서 return 하는 방식을 했는데, 급할때 쓰면 좋을 것 같다.

### for-each

- 배열과 컬렉션의 요소를 탐색할 때 사용
- 반복자와 인덱스 변수를 사용하지 않아 코드가 깔끔하고 잘못된 변수 사용으로 오류가 발생할 일도 없습니다.

```java
String[] names = {...};
for(String name : neames){
	 ...
}
```

- for-each를 사용할 수 없는 상황
    - 컬렉션을 순회하면서 선택된 엘리먼트를 제거
    - .. 변형

---

# **과제 (옵션)**

## **과제 0. JUnit 5 학습하세요.**

- 인텔리J, 이클립스, VS Code에서 JUnit 5로 테스트 코드 작성하는 방법에 익숙해 질 것.

- 이미 JUnit 알고 계신분들은 다른 것 아무거나!

- [더 자바, 테스트](https://www.inflearn.com/course/the-java-application-test?inst=86d1fbb8) 강의도 있으니 참고하세요~

  

- 저장소: https://github.com/sanhee/github-dashboard



## **과제 1. live-study 대시 보드를 만드는 코드를 작성하세요.**

- 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
- 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
- [Github 자바 라이브러리](https://github-api.kohsuke.org/)를 사용하면 편리합니다.
- 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.

## **과제 2. LinkedList를 구현하세요.**

- LinkedList에 대해 공부하세요.
- 정수를 저장하는 ListNode 클래스를 구현하세요.
- ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
- ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
- boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.

## **과제 3. Stack을 구현하세요.**

- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.

## **과제 4. 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.**

- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.

- void push(int data)를 구현하세요.

- int pop()을 구현하세요.

  

```java
public class Node<T> {
    private T data;  // 데이터 저장 변수
    public Node link;  // 다른 노드를 참조할 링크 노드

    public Node(){
        this.data = null;
        this.link = null;
    }

    public Node(T data){
        this.data = data;
        this.link = null;
    }

    public Node(T data, Node link){
        this.data = data;
        this.link = link;
    }

    public T getData() {
        return data;
    }
}
```



```java
interface MyStack {
    boolean isEmpty();
    boolean isFull();
    void push(String data);
    void pop() throws Exception;
}
```



```java
public class LinkedList implements MyStack {

    private Node head;
    private Node top;
    private int stackSize;

    public LinkedList(int stackSize) {
        this.head = null;
        this.top = null;
        this.stackSize = stackSize;
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public boolean isFull() {
        if(isEmpty()){
            return false;
        }
        else{
            int nodeCount = 0;
            Node tempNode = head;

            while(tempNode.link != null){
                ++nodeCount;
                tempNode = tempNode.link;   // 다음 노드 참조
            }

            return (this.stackSize-1 == nodeCount);
        }
    }

    @Override
    public void push(String data) {
        Node newNode = new Node(data);

        if(isFull()){
            System.err.println("스택이 가득찼습니다.");
            return;
        }
        else if(isEmpty()){
            this.head = newNode;
            this.top = this.head;
        } else{ // 스택이 비어있지 않다면

            Node tempNode = top;

            while(tempNode.link != null){
                tempNode = tempNode.link;
            }

            tempNode.link = newNode;
        }

    }

    @Override
    public void pop() throws Exception {
        Node preNode;
        Node tempNode;

        if(isEmpty()){
            throw new Exception("스택이 비어있습니다.");
        }

        if(top.link == null){
            head = null;
            top = null;
        }else{
            preNode = top;
            tempNode = top.link;

            while(tempNode.link != null){
                preNode = tempNode;
                tempNode = tempNode.link;
            }

            preNode.link = null;
        }
    }

    public void printListStack() throws Exception {
        if (isEmpty()) {
            throw new Exception("스택이 비어있습니다.");
        } else {
            Node tempNode = this.top;

            while (tempNode != null) {
                System.out.print(tempNode.getData() + " ");
                tempNode = tempNode.link;
            }
            System.out.println();
        }
    }

}
```





## **과제 5. Queue를 구현하세요.**

- 배열을 사용해서 한번
- ListNode를 사용해서 한번.

---

## 참고

- 자바의 정석 - 저자: 남궁성

[[이펙티브 자바 3판] 아이템 58. 전통적인 for 문보다는 for-each 문을 사용하라](https://madplay.github.io/post/prefer-foreach-loops-to-traditional-for-loops)