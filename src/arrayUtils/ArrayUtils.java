package arrayUtils;

import java.util.Arrays;

public class ArrayUtils {
    public static boolean areAllElemsDistinct (int[] arr, int except) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int next = arr[i+1];
            if (current != 0 && next != 0 && current == next) {
                return false;
            }
        }

        return true;
    }
}
