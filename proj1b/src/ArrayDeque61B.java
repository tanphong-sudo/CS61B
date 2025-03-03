import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B <T> implements Deque61B<T> {

    private int first;
    private int last;
    private int size;
    private T[] items;

    ArrayDeque61B() {
        first = 0;
        last = -1;
        size = 8;
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
    }

    @Override
    public void addLast(T x) {
        if (size() == items.length) {
            resize(size * 2);
        }
        int l = Math.floorMod(last + 1, items.length);
        items[l] = x;
        last = last + 1;
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
        return (first == 0 && last == -1);
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        return last - first + 1;
    }

    @Override
    public T removeFirst() {
        T temp = items[Math.floorMod(first, size)];
        first++;
        return temp;
    }

    @Override
    public T removeLast() {
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
}
