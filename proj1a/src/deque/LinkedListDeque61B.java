package deque;

import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    @Override
    public void addFirst(T x) {
        Node<T> node = new Node<>(x, first, sentinal);
        first.prev = node;
        first = node;
        sentinal.next = first;
        last = sentinal.prev;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node<T> node = new Node<>(x, sentinal, last);
        last.next = node;
        last = node;
        sentinal.prev = node;
        first = sentinal.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> p = first;
        while (p != sentinal) {
            list.add(p.value);
            p = p.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T value = first.value;
        first = first.next;
        first.prev = sentinal;
        sentinal.next = first;
        size--;

        return value;
    }

    @Override
    public T removeLast() {
        T value = last.value;
        last = last.prev;
        last.next = sentinal;
        sentinal.prev = last;
        size--;

        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;

        int start = 0, incre = 1;
        Node<T> p = first;
        if (index > size / 2) {
            start = size - 1;
            incre = -1;
            p = last;
        }

        while (start != index) {
            p = (incre == 1) ? p.next : p.prev;
            start += incre;
        }

        return p.value;
    }

    @Override
    public T getRecursive(int index) {
        return helper(index, first);
    }

    private T helper(int index, Node<T> p) {
        if (index < 0 || index >= size) return null;

        if (index == 0) return p.value;
        return helper(index - 1, p.next);
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value, Node<T> n, Node<T> p) {
            this.value = value;
            next = n;
            prev = p;
        }
    }

    public Node<T> sentinal;
    public Node<T> first;
    public Node<T> last;
    public int size;

    public LinkedListDeque61B() {
        sentinal = new Node<>(null, null, null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;

        first = sentinal;
        last = sentinal;
        size = 0;
    }

    public static void main (String args[]) {
        Deque61B<Integer> dq = new LinkedListDeque61B<>();

        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(0);
        dq.addLast(-1);
        /*List<Integer> list = dq.toList();
        for (int num : list) {
            System.out.println(num);
        } */

        System.out.println(dq.removeFirst());
        System.out.println(dq.removeLast());

        List<Integer> list = dq.toList();
        for (int num : list) {
            System.out.println(num);
        }
    }
}
