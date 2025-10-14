package utilities;

import comparators.ShapeComparator;
import shapes.Shape;

public class Sorter {
    private ShapeComparator comparator;

    public Sorter(ShapeComparator comparator) {
        this.comparator = comparator;
    }

    public void sortUsing(String sortType, Shape[] shapes) {
        switch (sortType.toLowerCase()) {
            case "b":
                bubbleSort(shapes);
                break;
            case "i":
                insertionSort(shapes);
                break;
            case "s":
                selectionSort(shapes);
                break;
            case "m":
                mergeSort(shapes);
                break;
            case "q":
                quickSort(shapes);
                break;
            case "h":
                heapSort(shapes);
                break;
            default:
                System.out.println("Please choose a valid sort type");
                return;
        }
    }

    // Bubble Sort (descending)
    private void bubbleSort(Shape[] shapes) {
        int n = shapes.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(shapes[j], shapes[j + 1]) < 0) {
                    swap(shapes, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Insertion Sort (descending)
    private void insertionSort(Shape[] shapes) {
        for (int i = 1; i < shapes.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(shapes[j - 1], shapes[j]) < 0) {
                swap(shapes, j - 1, j);
                j--;
            }
        }
    }

    // Selection Sort (descending)
    private void selectionSort(Shape[] shapes) {
        int n = shapes.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(shapes[j], shapes[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) swap(shapes, i, maxIndex);
        }
    }

    // Merge Sort (descending)
    public void mergeSort(Shape[] shapes) {
        if (shapes == null || shapes.length < 2) return;
        mergeSortRecursive(shapes, 0, shapes.length - 1);
    }

    private void mergeSortRecursive(Shape[] shapes, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(shapes, left, mid);
            mergeSortRecursive(shapes, mid + 1, right);
            merge(shapes, left, mid, right);
        }
    }

    private void merge(Shape[] shapes, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Shape[] leftArr = new Shape[n1];
        Shape[] rightArr = new Shape[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = shapes[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = shapes[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArr[i], rightArr[j]) > 0) {
                shapes[k++] = leftArr[i++];
            } else {
                shapes[k++] = rightArr[j++];
            }
        }
        while (i < n1) shapes[k++] = leftArr[i++];
        while (j < n2) shapes[k++] = rightArr[j++];
    }

    // Quick Sort (descending)
    private void quickSort(Shape[] shapes) {
        if (shapes == null || shapes.length < 2) return;
        quickSortRecursive(shapes, 0, shapes.length - 1);
    }

    private void quickSortRecursive(Shape[] shapes, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(shapes, low, high);
            quickSortRecursive(shapes, low, pivotIndex - 1);
            quickSortRecursive(shapes, pivotIndex + 1, high);
        }
    }

    private int partition(Shape[] shapes, int low, int high) {
        Shape pivot = shapes[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(shapes[j], pivot) > 0) {
                i++;
                swap(shapes, i, j);
            }
        }
        swap(shapes, i + 1, high);
        return i + 1;
    }

    // Heap Sort (descending)
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
		
		if (left < size && comparator.compare(shapes[smallest], shapes[left]) > 0 )
			smallest = left;
		if (right < size && comparator.compare(shapes[smallest], shapes[right]) > 0)
			smallest = right;
		
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

    // Sort name helper
    public String getSortName(String sortType) {
        if (sortType == null) return "Unknown Sort";
        switch (sortType.toLowerCase()) {
            case "b": return "Bubble Sort";
            case "i": return "Insertion Sort";
            case "s": return "Selection Sort";
            case "m": return "Merge Sort";
            case "q": return "Quick Sort";
            case "h": return "Heap Sort";
            default: return "Unknown Sort";
        }
    }
}