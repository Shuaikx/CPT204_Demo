//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int binarySearch(int[] list, int key){
        int low =0;
        int high = list.length - 1;
        while (high>= low){
            int mid =(low + high)/2;
            if(key<list[mid]){
                high = mid-1;
            }
            else if(key == list[mid]){
                return mid;
            }
            else{
                low = mid+1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7};
        int result = binarySearch(list, 9);
        System.out.println(result);
    }


}