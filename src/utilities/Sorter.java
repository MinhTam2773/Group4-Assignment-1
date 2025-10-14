package utilities;

import java.util.Comparator;
import shapes.Shape;

public class Sorter {

    // Bubble sort for Shape[] using Comparator
    public static void bubbleSort(Shape[] array, Comparator<Shape> comp) {
        int n = array.length;
        boolean swap;

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Compare using the comparator
                if (comp.compare(array[j], array[j + 1]) < 0) { // descending order
                    Shape temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    public static void insertionSort(Shape[] array, Comparator<Shape> comp) {
    	// implement later
    }

    public static void selectionSort(Shape[] array, Comparator<Shape> comp) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Use comparator to determine order
                if (comp.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap array[i] and array[minIndex] if needed
            if (minIndex != i) {
                Shape temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }


    public static void mergeSort(Shape[] array, Comparator<Shape> comp) {
    	//implement later
    }

    public static void quickSort(Shape[] array, Comparator<Shape> comp) {
    	//implement later
    }

    public static void heapSort(Shape[] array, Comparator<Shape> comp) {
        buildMinHeap(array, comp);

        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            minHeapify(array, i, 0, comp);
        }
    }

    private static void buildMinHeap(Shape[] array, Comparator<Shape> comp) {
        int n = array.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            minHeapify(array, n, i, comp);
        }
    }

    private static void minHeapify(Shape[] array, int size, int i, Comparator<Shape> comp) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && comp.compare(array[smallest], array[left]) < 0) {
            smallest = left;
        }
        if (right < size && comp.compare(array[smallest], array[right]) < 0) {
            smallest = right;
        }

        if (i != smallest) {
            swap(array, i, smallest);
            minHeapify(array, size, smallest, comp);
        }
    }

    private static void swap(Shape[] array, int i, int j) {
        Shape temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
