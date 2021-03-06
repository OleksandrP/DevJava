package core.less15;

import java.io.Serializable;
import java.util.Iterator;

public class MyArrayList<T extends Serializable> implements Iterable<T>, Serializable{
	
	private static final long serialVersionUID = 2475886426511675688L;

	private Object[] array = new Object[4];

	private int size = 0;

	public void add(T e) {
		if(array.length-1==size) increase();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = e;
				size++;
				break;
			}
		}
	}
	
	private void increase(){
		Object[] tmp = array;
		array = new Object[array.length*3/2+1];
		for (int i = 0; i < tmp.length; i++) {
			array[i] = tmp[i];
		}
	}
	
	public String toString(){
		String str = "[";
		for (int i = 0; i < size; i++) {
			str += array[i];
			if(i!=size-1) str+=", ";
		}
		return str+"]";
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private int index = 0;
			
			@Override
			public boolean hasNext() {
				return index<size;
			}

			@Override
			public T next() {
				return (T) array[index++];
			}
		};
	}
}
