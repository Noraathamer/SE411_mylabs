package Lab03;

import java.util.*;

public class PrintableList<T> {

    private List<T> list;
    
    public PrintableList(T[] items) {
        list = Arrays.asList(items);
    }
    public List<T> getList() {
        return list;
    }
    public void printList() {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
