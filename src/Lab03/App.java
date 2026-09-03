package Lab03;
import java.util.*;

public class App {
	public static void main(String[] args) {
		//Exercise 1
		  String[] names = {"Norah", "Thamer", "Aldeeb"};
		  PrintableList<String> stringList = new PrintableList<>(names);
	        stringList.printList();

	        Integer[] numbers = {1, 2, 3};
	        PrintableList<Integer> integerList = new PrintableList<>(numbers);
	        integerList.printList();
	    
	//Exercise 2
	NumberBox<Integer> intBox = new NumberBox<>();
	intBox.setItem(5);

	System.out.println(intBox.getItem());
	System.out.println(intBox.add(5));

	NumberBox<Double> doubleBox = new NumberBox<>();
	doubleBox.setItem(5.5);

	System.out.println(doubleBox.getItem());
	System.out.println(doubleBox.add(1.0));
	
	//Exercise 3
	   List<String> namesList =
               Arrays.asList("Norah", "Thamer", "Aldeeb");

       printList(namesList);

       List<Integer> numbersList =
               Arrays.asList(10, 20, 30);

       System.out.println(sumNumbers(numbersList));
   }
	
	//Exercise 3

	public static void printList(java.util.List<?> list) {
	    for (Object item : list) {
	        System.out.println(item);
	    }
	}

	public static double sumNumbers(java.util.List<? extends Number> list) {
	    double sum = 0;

	    for (Number num : list) {
	        sum += num.doubleValue();
	    }

	    return sum;
	}
	
}
