package core.less15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.less4.Circle;
import core.less4.Geometric;
import core.less4.Rectangle;



public class Main {

	public static void main(String[] args) {
		MyArrayList<String> list = new MyArrayList<>();
		list.add("Ivan");
		list.add("Mykola");
		list.add("Petro");
		System.out.println(list);
		for (String object : list) {
			System.out.println(object);
		}
		List<Geometric> list2 = new ArrayList<>(Arrays.asList(new Rectangle(4, 5), new Circle(5)));
		print(list2);
	}
	
	static void print(Iterable<? extends Geometric> list){
		for (Geometric geometric : list) {
			geometric.print();
		}
	}
	
	
}