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

    public static int[] getSubset (int[][] arr, int fromRow, int toRow, int fromCol, int toCol) {
        int resultSize = ((toRow + 1) - (fromRow + 1)) * ((toCol + 1) - (fromCol + 1));
        int[] result = new int[resultSize];
        int resultCount = 0;

        for (int i = fromRow; i <= toRow; i++) {
            for (int j = fromCol; i <= toCol; j++) {
                result[resultCount] = arr[i][j];
                resultCount++;
            }
        }
        return result;
    }
}
