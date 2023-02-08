package utils;

import java.util.Iterator;

public class CustomHashSet<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator(){
        return customIterator;
    }

    private CustomIterator<T> customIterator = new CustomIterator<T>(this);

    private T[] array = (T[]) new Object[1];

    private int size = 0;

    public void add(T element) {
        if (contains(element)) {
            return;
        }
        array[size] = element;
        size++;
        if (size == array.length) {
            T[] biggerArray = (T[]) new Object[size * 2 + 1];
            for (int i = 0; i < array.length; i++) {
                biggerArray[i] = array[i];
            }
            array = biggerArray;
        }
    }

    public void remove(int index) {
        if (!validIndex(index))
            return;
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                remove(i);
                return;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public T getElement(int index) {
        return validIndex(index) ? array[index] : null;
    }

    public T getRandomElement() {
        if (size == 0)
            return null;
        return array[(int) Math.random() * size];
    }

    public int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        array = (T[]) new Object[1];
    }

    private boolean validIndex(int index) {
        return (index >= 0 && index < size);
    }
}

class CustomIterator<T> implements Iterator<T> {

    int current;
    CustomHashSet<T> data;

    public CustomIterator(CustomHashSet<T> structure){
        current = 0;
        data = structure;
    }

    @Override
    public boolean hasNext() {
        return (data.getSize() != 0 && current < data.getSize());
    }

    @Override
    public T next() {
        T toReturn = data.getElement(current);
        current++;
        return toReturn;
    }

}
