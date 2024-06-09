import java.lang.reflect.Array;
import java.util.Arrays;

public class Sorting {
    //------------------------------------------------------------------------------------------------------------------
    public static double[] selectionSort(double[] list){
        for (int i=0; i< list.length; i++){
            double currentMin = list[i];
            int currentMinIndex = i;
            for (int j = i+1; j< list.length; j++){
                if(currentMin>list[j]){
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            if(currentMinIndex!=i){
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
        return list;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static double[] insertionSort(double[] list){
        for(int i =0; i< list.length; i++){
            double currentElement = list[i];
            int k;
            for (k=i-1; k >= 0 && list[k] > currentElement; k--)
                list[k+1] = list[k];
            list[k+1] = currentElement;
        }
        return list;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static double[] bubbleSort(double[] list){ // Best case: O(n), Worst case: O(n^2)
        boolean needNextPass = true;
        for(int k =1; k < list.length && needNextPass; k++){
            needNextPass = false;
            for (int i = 0; i< list.length - k; i ++){
                if(list[i] > list[i+1]){ // swap
                    double temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    needNextPass = true;
                }
            }
        }
        return list;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static double[] mergeSort(double[] list){ // divide and conquer O(n logn)
        if(list.length > 1){
            double[] firstHalf = new double[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, firstHalf.length);
            mergeSort(firstHalf); // Get firstHalf

            int secondHalfLength = list.length - firstHalf.length;
            double[] secondHalf = new double[secondHalfLength];
            System.arraycopy(list, list.length/2, secondHalf, 0, secondHalf.length);
            mergeSort(secondHalf); // Get secondHalf

            return merge(firstHalf, secondHalf, list);
        }
        return list; // if list.length == 1, the smallest element in the list.
    }
    public static double[] merge(double[] list1, double[] list2, double[] temp){ // combine two ascending list into one ascending list
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        while (current1 < list1.length && current2 < list2.length){
            if(list1[current1] < list2[current2]) temp[current3++] = list1[current1++];
            else temp[current3++] = list2[current2++];
        }
        while (current1 < list1.length){
            temp[current3++] = list1[current1++];
        }
        while (current2 < list2.length){
            temp[current3++] = list2[current2++];
        }
        System.out.println(Arrays.toString(temp));
        return temp;
    }

    //------------------------------------------------------------------------------------------------------------------
    // divide and conquer O(n logn) with worst O(n^2),
    // but does not need additional array compared to Merge Sort, more space efficient
    public static double[] quickSort(double[] list){
        return quickSort(list, 0, list.length - 1);
    }
    public static double[] quickSort(double[] list, int first, int last){
        if(last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first,pivotIndex - 1);
            return quickSort(list, pivotIndex + 1, last);
        }
        return list;
    }
    public static int partition(double[] list, int first, int last){
        double pivot =  list[first];
        int low = first + 1;
        int high =  last;
        while(high > low){
            while (low <= high && list[low] <= pivot){ // search forward from left
                low++;
            }
            while (low <= high && list[high] > pivot){ // search backward from right
                high--;
            }
            if(high > low){ // swap two elements
                double temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot) // account for duplicated elements
            high--;
        if (pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else return first;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static double[] randomInitiate(double[] a){
        for (int i =0; i< a.length; i++){
            a[i] = Math.random() * a.length;
        }
        return a;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Heap Sort is also O(n logn), but requires no additional array space, so it's more space efficient
    public static <E extends Comparable<E>> E[] heapSort(E[] list) {
        // Create a Heap of E
        Heap<E> heap = new Heap<E>();

        // Add elements to the heap
        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        // Remove the highest elements from the heap
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
        return list;
    }
    public static void main(String[] args) {
        // double[] list = new double[20];
        // list = randomInitiate(list);
        // double[] list = {2.0, 3.0, 2, 5, 6, 1, -2, 3, 14, 12};
        Double[] list = {2.0, 3.0, 2.0, 5.0, 6.0, 1.0, -2.0, 3.0, 14.0, 12.0};

        long statTime = System.currentTimeMillis();
        // double[] result = selectionSort(list);
        // double[] result = insertionSort(list);
        // double[] result = bubbleSort(list);
        // double[] result = mergeSort(list);
        // double[] result = quickSort(list);
        Double[] result = heapSort(list);
        long endTime = System.currentTimeMillis();
        System.out.println("The sorted list is: " + Arrays.toString(result));
        System.out.println("Cost time: " + (endTime - statTime) + "ms");
    }
}
