package src.utils;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Custom data structure largely inspired by code from Finn Eggers. https://github.com/Luecx
 */
public class RandomHashSet<T> {
    
    HashSet<T> set;
    ArrayList<T> data;

    public RandomHashSet() {
        set = new HashSet<>();
        data = new ArrayList<>();
    }

    public boolean contains(T object) {
        return set.contains(object);
    }

    public T getRandomElement(){
        if(set.size() > 0){
            return data.get((int)(Math.random() * data.size()));
        }
        return null;
    }

    public void add(T object){
        if(!set.contains(object)){
            set.add(object);
            data.add(object);
        }
    }

    public void clear(){
        set.clear();
        data.clear();
    }

    public T get(int index){
        if(index < 0 || index >= data.size()) return null;
        return data.get(index);
    }

    public void remove(int index){
        if(index < 0 || index >= data.size()) return;
        set.remove(data.get(index));
        data.remove(index);
    }

    public void remove(T object) {
        set.remove(object);
        data.remove(object);
    }
}
