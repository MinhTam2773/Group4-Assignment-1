package utilities;

import comparators.ShapeComparator;
import shapes.Shape;

/**
 * The Sorter class provides multiple sorting algorithms to order Shape objects.
 * 
 * It supports several classic algorithms — Bubble, Insertion, Selection, Merge,
 * Quick, and Heap Sort — all designed to work in *descending* order using a
 * given ShapeComparator (which defines whether to compare by height, volume, or area).
 * 
 * Each sorting method operates directly on the given array of Shape objects,
 * modifying it in place. The class is used by the main program to dynamically
 * select and execute the user's chosen sort method.
 */
public class Sorter {
    // Comparator used to define the sorting criteria (height, area, or volume)
    private ShapeComparator comparator;

    // Constructor accepts a ShapeComparator to determine how shapes will be compared
    public Sorter(ShapeComparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Selects and executes the sorting algorithm based on the user's input.
     * 
     * @param sortType The character code representing the sorting algorithm (b, i, s, m, q, or h)
     * @param shapes   The array of Shape objects to sort
     */
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

    // ------------------- Sorting Algorithm Implementations -------------------

    // Bubble Sort (descending): repeatedly swaps adjacent elements if they are out of order.
    private void bubbleSort(Shape[] shapes) {
        int n = shapes.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if current shape < next shape
                if (comparator.compare(shapes[j], shapes[j + 1]) < 0) {
                    swap(shapes, j, j + 1);
                    swapped = true;
                }
            }
            // Optimization: stop if no swaps occurred
            if (!swapped) break;
        }
    }

    // Insertion Sort (descending): builds the sorted list one element at a time.
    private void insertionSort(Shape[] shapes) {
        for (int i = 1; i < shapes.length; i++) {
            int j = i;
            // Move the current element backwards until it's in the correct spot
            while (j > 0 && comparator.compare(shapes[j - 1], shapes[j]) < 0) {
                swap(shapes, j - 1, j);
                j--;
            }
        }
    }

    // Selection Sort (descending): repeatedly selects the largest remaining element.
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

    // Merge Sort (descending): recursively divides the array and merges sorted halves.
    public void mergeSort(Shape[] shapes) {
        if (shapes == null || shapes.length < 2) return;
        mergeSortRecursive(shapes, 0, shapes.length - 1);
    }

    // Recursive helper for Merge Sort
    private void mergeSortRecursive(Shape[] shapes, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(shapes, left, mid);
            mergeSortRecursive(shapes, mid + 1, right);
            merge(shapes, left, mid, right);
        }
    }

    // Merges two sorted subarrays into a single sorted array (descending)
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

    // Quick Sort (descending): partitions array around a pivot and recursively sorts subarrays.
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

    // Rearranges elements so that elements larger than the pivot appear before it
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

    // Heap Sort (descending): builds a min-heap and repeatedly extracts the smallest element.
    private void heapSort(Shape[] shapes) {
        buildMinHeap(shapes);
        for (int i = shapes.length - 1; i >= 0; i--) {
            swap(shapes, 0, i);
            minHeapify(shapes, i, 0);
        }
    }

    // Converts the array into a min-heap structure
    private void buildMinHeap(Shape[] shapes) {
        int n = shapes.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            minHeapify(shapes, n, i);
        }
    }

    // Maintains the heap property for a given node
    private void minHeapify(Shape[] shapes, int size, int i) {
        int smallest = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < size && comparator.compare(shapes[smallest], shapes[left]) > 0)
            smallest = left;
        if (right < size && comparator.compare(shapes[smallest], shapes[right]) > 0)
            smallest = right;

        if (i != smallest) {
            swap(shapes, smallest, i);
            minHeapify(shapes, size, smallest);
        }
    }

    // Utility method: swaps two elements in the shapes array
    private void swap(Shape[] shapes, int i, int j) {
        Shape temp = shapes[i];
        shapes[i] = shapes[j];
        shapes[j] = temp;
    }

    /**
     * Helper method that returns the human-readable name of a sorting algorithm.
     *
     * @param sortType A character indicating the sorting type
     * @return The name of the sort type as a string
     */
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
