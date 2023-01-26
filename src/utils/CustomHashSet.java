package src.utils;

public class CustomHashSet<T> {
    private T[] array = (T[]) new Object[1];

    private int size = 0;
    public void add(T element){
        array[size] = element;
        size++;
        if(size == array.length){
            T[] biggerArray = (T[]) new Object[size * 2 + 1];
            for (int i = 0; i < array.length; i++) {
                biggerArray[i] = array[i]; 
            }
            array = biggerArray;
        }
    }

    public void remove(int index){
        if(!validIndex(index)) return;
        for(int i = index; i < size -1; i++){
            array[i] = array[i + 1];
        }
        size--;
    }

    public int getSize() {
        return size;
    }

    public T get(int index){
        return validIndex(index) ? array[index] : null;
    }

    private boolean validIndex(int index){
        return (index >= 0 && index < size);
    }
}
