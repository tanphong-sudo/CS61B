import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B <T> implements Deque61B<T> {

    private int first;
    private int last;
    private int size;
    private int capacity;
    private T[] items;

    ArrayDeque61B() {
        first = 0;
        last = -1;
        size = 8;
        capacity = 0;
        items = (T[]) new Object[8];
    }

    private void resize(int i) {
        T[] temp = (T[]) new Object[i];
        for (int j = 0; j < size(); j++) {
            temp[j] = this.get(j);
        }
        items = temp;
        first = 0;
        last = size() - 1;
        size = i;
    }

    @Override
    public void addFirst(T x) {
        if (size() == items.length) {
            resize(size * 2);
        }
        int s = Math.floorMod(first - 1, items.length);
        items[s] = x;
        first = first - 1;
        capacity++;
    }

    @Override
    public void addLast(T x) {
        if (size() == items.length) {
            resize(size * 2);
        }
        int l = Math.floorMod(last + 1, items.length);
        items[l] = x;
        last = last + 1;
        capacity++;
    }

    @Override
    public List toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            list.add(this.get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public T removeFirst() {
        if (size() < items.length / 4) {
            resize(size / 2);
        }
        capacity--;
        T temp = items[Math.floorMod(first, size)];
        first++;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size() < items.length / 4) {
            resize(size / 2);
        }
        capacity--;
        T temp = items[Math.floorMod(last, size)];
        last--;
        return temp;
    }

    @Override
    public T get(int index) {
        return items[Math.floorMod(first + index, size)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new myArrayIterator();
    }

    private class myArrayIterator implements Iterator<T> {
        private int index;

        public myArrayIterator() {index = 0;}

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            T item = get(index);
            index++;
            return item;
        }
    }

    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }
        if (this == b) {
            return true;
        }
        if (b instanceof ArrayDeque61B) {
            ArrayDeque61B<T> other = (ArrayDeque61B<T>) b;
            if (this.size() != other.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != other.get(i))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
