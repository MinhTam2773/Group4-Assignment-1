package utilities;

public class Sorter {
	public static void bubbleSort(Double[] array) {
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
	
	public static void insertionSort() {
		
	}
	
	public static void selectionSort() {
		
	}
	
	public static void mergeSort() {
		
	}
	
	public static void quickSort() {
		
	}
	
	public static void heapSort() {
		
	}
}
