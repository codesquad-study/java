package study;


class ListNode {
    int data;
    ListNode next;

    public ListNode(int data) {
        this.data = data;
    }
}

class ListIterator {
    private ListNode current;


    public ListIterator(ListNode head) {
        this.current = head;
    }

    public boolean atEnd() {
        return current == null;
    }

    public int getData() {
        return current.data;
    }

    public void next() {
        if (!atEnd()) {
            current = current.next;
        }
    }
}

public class LinkedList {
    ListNode head;
    private int size;

    public ListIterator getListIterator() {
        return new ListIterator(head);
    }

    public void print() {
        for (ListIterator iter = getListIterator(); !iter.atEnd(); iter.next()) {
            System.out.print(iter.getData() + " ");
        }
    }

    public ListNode add(ListNode head, ListNode nodeToAdd, int position) {
        this.head = head;
        ListNode current = head;
        if (position == 0) {
            this.head = nodeToAdd;
            nodeToAdd.next = head;
        }
        if (position > size) {
            position = size;
        }

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        nodeToAdd.next = current.next;
        current.next = nodeToAdd;
        size++;
        return nodeToAdd;
    }

    public ListNode remove(ListNode head, int positionToRemove) {
        this.head = head;
        ListNode current = head;
        if (positionToRemove == 0) {
            this.head = head.next;
        }

        if (positionToRemove > size) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < positionToRemove - 1; i++) {
            current = current.next;
        }
        ListNode ret = current.next;
        current.next = current.next.next;
        size--;
        return ret;
    }

    public boolean contains(ListNode head, ListNode nodeToCheck) {
        for (ListIterator iter = getListIterator(); !iter.atEnd(); iter.next()) {
            if (iter.equals(nodeToCheck)) {
                return true;
            }
        }
        return false;
    }
}
