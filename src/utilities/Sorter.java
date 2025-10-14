package utilities;

import comparators.ShapeComparator;
import shapes.Shape;

public class Sorter {
	private ShapeComparator comparator;
	
	public Sorter(ShapeComparator comparator) {
		this.comparator = comparator;
	}
	
	public void sortUsing(String sortType, Shape[] shapes) {
		switch(sortType) {
		case "b": 
//			bubbleSort(shapes);
			break;
		case "i": 
//			insertionSort();
			break;
		case "s":
//			selectionSort();
			break;
		case "m":
//			mergeSort();
			break;
		case "q":
//			quickSort();
			break;
		case "h":
			heapSort(shapes);
			break;
		default:
			System.out.println("Please choose a valid sort type");
		}
	}
		
	private void bubbleSort(Double[] array) {
		
		int n = array.length;
		boolean swap;
		
		for (int i = 0; i < n-1; i++) {
			swap = false;
			for (int j = 0; j< n-i-1; j++) {
				if (array[j] < array[j+1]) {
					double temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					
					swap = true;
				}
			}
			if (!swap) {
				break;
			}
		}
		
	}
	
	private void insertionSort() {
		
	}
	
	private void selectionSort() {
		
	}
	
	public void mergeSort() {
		
	}
	
	private void quickSort() {
		
	}
	
	private void heapSort(Shape[] shapes) {
		buildMinHeap(shapes);
		
		for (int i = shapes.length - 1; i >= 0; i--) {
			swap(shapes, 0, i);
			minHeapify(shapes, i, 0);
		}
	}
	private void buildMinHeap(Shape[] shapes) {
		int n = shapes.length;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			minHeapify(shapes, n, i);
		}
	}
	private void minHeapify(Shape[] shapes, int size, int i) {
		int smallest = i;
		int left = i * 2 + 1;
		int right = i * 2 + 2;
		
		if (left < size && comparator.compare(shapes[smallest], shapes[left]) > 0 ) {
			smallest = left;
		}
		if (right < size && comparator.compare(shapes[smallest], shapes[right]) > 0) {
			smallest = right;
		}
		
		if (i != smallest) {
			swap(shapes, smallest, i);
			minHeapify(shapes, size, smallest);
		}
	}
	private void swap(Shape[] shapes, int i, int j) {
		Shape temp = shapes[i];
		shapes[i] = shapes[j];
		shapes[j] = temp;
	}
}
