package utilities;

import comparators.ShapeComparator;
import shapes.Shape;

public class Sorter {
	private ShapeComparator comparator;
	private long start;
	private long end;
	
	public Sorter(ShapeComparator comparator) {
		this.comparator = comparator;
	}
	
	public void sortUsing(String sortType, Shape[] shapes) {
		switch(sortType) {
		case "b": 
			start = System.currentTimeMillis();
			bubbleSort(shapes);
			end = System.currentTimeMillis();
			break;
		case "i": 
			start = System.currentTimeMillis();
//			insertionSort();
			end = System.currentTimeMillis();
			break;
		case "s":
			start = System.currentTimeMillis();
			selectionSort(shapes);
			end = System.currentTimeMillis();
			break;
		case "m":
			start = System.currentTimeMillis();
			mergeSort(shapes);
			end = System.currentTimeMillis();
			break;
		case "q":
			start = System.currentTimeMillis();
//			quickSort();
			end = System.currentTimeMillis();
			break;
		case "h":
			start = System.currentTimeMillis();
			heapSort(shapes);
			end = System.currentTimeMillis();
			break;
		default:
			System.out.println("Please choose a valid sort type");
		}
		System.out.println("⏱️ Sorting completed in " + (end - start) + " ms\n");
	}
		
	private void bubbleSort(Shape[] shapes) {
		//creates shape length and bool called swap to check whether it was
		int n = shapes.length;
		boolean swap;
		//counts how many times it has been through the array
		for (int i = 0; i < n-1; i++) {
			swap = false;
			//this loop sorts out the array (descending wise) by subtracting the array that was already sorted leaving only the non sorted arrays
			for (int j = 0; j< n-i-1; j++) {
				//if an array is less than the array after it it switches the spot of the array
				if (comparator.compare(shapes[j], shapes[j + 1]) < 0) {
					Shape temp = shapes[j];
					shapes[j] = shapes[j+1];
					shapes[j+1] = temp;
					//checks whether if there was a swap
					swap = true;
				}
			}
			//if the swaps end than swap detects it and breaks the for loop
			if (!swap) {
				break;
			}
		}
		
	}
	
	private void insertionSort() {
		
	}
	
	private void selectionSort(Shape[] shapes) {
	    int n = shapes.length;

	    for (int i = 0; i < n - 1; i++) {
	        int minIndex = i;

	        for (int j = i + 1; j < n; j++) {
	            // Use instance comparator
	            if (comparator.compare(shapes[j], shapes[minIndex]) < 0) {
	                minIndex = j;
	            }
	        }

	        // Swap shapes[i] and shapes[minIndex] if needed
	        if (minIndex != i) {
	            Shape temp = shapes[i];
	            shapes[i] = shapes[minIndex];
	            shapes[minIndex] = temp;
	        }
	    }
	}
	
	public void mergeSort(Shape[] shapes) {
		if(shapes == null || shapes.length < 2) {
			return;
		}
		mergeSortRecursive(shapes, 0, shapes.length -1);
		
	}
	private void mergeSortRecursive(Shape[] shapes, int left, int right) {
		if(left < right) {
			int mid = (left+right)/2;
			mergeSortRecursive(shapes, left, mid);
			mergeSortRecursive(shapes, mid+1, right);
			merge(shapes, left, mid, right);
		}
		
	}
	
	private void merge(Shape[] shapes, int left, int mid, int right) {
		int n1 = mid - left + 1;
	    int n2 = right - mid;
	    
	    Shape[] leftArr = new Shape[n1];
	    Shape[] rightArr = new Shape[n2];
	    
	    for (int i = 0; i < n1; i++) {
	        leftArr[i] = shapes[left + i];
	    }
	    for (int j = 0; j < n2; j++) {
	        rightArr[j] = shapes[mid + 1 + j];
	    }
	    
	    int i = 0, j = 0, k = left;
	    
	    try {
	    	while (i < n1 && j < n2) {
	            if (comparator.compare(leftArr[i], rightArr[j]) < 0) {
	                shapes[k] = leftArr[i];
	                i++;
	            } else {
	                shapes[k] = rightArr[j];
	                j++;
	            }
	            k++;
	        }

	        while (i < n1) {
	            shapes[k++] = leftArr[i++];
	        }

	        while (j < n2) {
	            shapes[k++] = rightArr[j++];
	        }
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
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