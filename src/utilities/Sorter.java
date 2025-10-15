package utilities;

import java.util.Comparator;

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
public class Sorter<T> {
    // Comparator used to define the sorting criteria (height, area, or volume)
    private Comparator<T> comparator;

    // Constructor accepts a ShapeComparator to determine how array will be compared
    public Sorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Selects and executes the sorting algorithm based on the user's input.
     * 
     * @param sortType The character code representing the sorting algorithm (b, i, s, m, q, or h)
     * @param array   The array of Shape objects to sort
     */
    public void sortUsing(String sortType, T[] array) {
        switch (sortType.toLowerCase()) {
            case "b":
                bubbleSort(array);
                break;
            case "i":
                insertionSort(array);
                break;
            case "s":
                selectionSort(array);
                break;
            case "m":
                mergeSort(array);
                break;
            case "q":
                quickSort(array);
                break;
            case "h":
                heapSort(array);
                break;
            default:
                System.out.println("Please choose a valid sort type");
                return;
        }
    }

    // ------------------- Sorting Algorithm Implementations -------------------

    // Bubble Sort (descending): repeatedly swaps adjacent elements if they are out of order.
    private void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if current shape < next shape
                if (comparator.compare(array[j], array[j + 1]) < 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            // Optimization: stop if no swaps occurred
            if (!swapped) break;
        }
    }

    // Insertion Sort (descending): builds the sorted list one element at a time.
    private void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            // Move the current element backwards until it's in the correct spot
            while (j > 0 && comparator.compare(array[j - 1], array[j]) < 0) {
                swap(array, j - 1, j);
                j--;
            }
        }
    }

    // Selection Sort (descending): repeatedly selects the largest remaining element.
    private void selectionSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(array[j], array[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) swap(array, i, maxIndex);
        }
    }

    // Merge Sort (descending): recursively divides the array and merges sorted halves.
    public void mergeSort(T[] array) {
        if (array == null || array.length < 2) return;
        mergeSortRecursive(array, 0, array.length - 1);
    }

    // Recursive helper for Merge Sort
    private void mergeSortRecursive(T[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(array, left, mid);
            mergeSortRecursive(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    // Merges two sorted subarrays into a single sorted array (descending)
    private void merge(T[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] leftArr = (T[]) new Object[n1];
        T[] rightArr = (T[]) new Object[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = array[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArr[i], rightArr[j]) > 0) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }
        while (i < n1) array[k++] = leftArr[i++];
        while (j < n2) array[k++] = rightArr[j++];
    }

    // Quick Sort (descending): partitions array around a pivot and recursively sorts subarrays.
    private void quickSort(T[] array) {
        if (array == null || array.length < 2) return;
        quickSortRecursive(array, 0, array.length - 1);
    }

    private void quickSortRecursive(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortRecursive(array, low, pivotIndex - 1);
            quickSortRecursive(array, pivotIndex + 1, high);
        }
    }

    // Rearranges elements so that elements larger than the pivot appear before it
    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) > 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    // Heap Sort (descending): builds a min-heap and repeatedly extracts the smallest element.
    private void heapSort(T[] array) {
        buildMinHeap(array);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            minHeapify(array, i, 0);
        }
    }

    // Converts the array into a min-heap structure
    private void buildMinHeap(T[] array) {
        int n = array.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            minHeapify(array, n, i);
        }
    }

    // Maintains the heap property for a given node
    private void minHeapify(T[] array, int size, int i) {
        int smallest = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < size && comparator.compare(array[smallest], array[left]) > 0)
            smallest = left;
        if (right < size && comparator.compare(array[smallest], array[right]) > 0)
            smallest = right;

        if (i != smallest) {
            swap(array, smallest, i);
            minHeapify(array, size, smallest);
        }
    }

    // Utility method: swaps two elements in the array array
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
