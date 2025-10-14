package utilities;

import comparators.ShapeComparator;
import shapes.Shape;

public class Sorter {
	private static ShapeComparator comparator;
	
	public Sorter(ShapeComparator comparator) {
		Sorter.comparator = comparator;
	}
		
	private static void bubbleSort(Double[] array) {
		
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
	
	private static void insertionSort() {
		
	}
	
	private static void selectionSort() {
		
	}
	
	public static void mergeSort() {
		
	}
	
	private static void quickSort() {
		
	}
	
	private static void heapSort(int[] arr) {
		buildMinHeap(arr);
		
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			minHeapify(arr, i, 0);
		}
	}
	private static void buildMinHeap(int[] arr) {
		int n = arr.length;
		for (int i = (n / 2) - 1; i >= 0; i--) {
			minHeapify(arr, n, i);
		}
	}
	private static void minHeapify(int[] arr, int size, int i) {
		int smallest = i;
		int left = i * 2 + 1;
		int right = i * 2 + 2;
		
		if (left < size && arr[smallest] > arr[left]) {
			smallest = left;
		}
		if (right < size && arr[smallest] > arr[right]) {
			smallest = right;
		}
		
		if (i != smallest) {
			swap(arr, smallest, i);
			minHeapify(arr, size, smallest);
		}
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
