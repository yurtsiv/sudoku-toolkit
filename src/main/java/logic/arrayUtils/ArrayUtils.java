package logic.arrayUtils;

import java.util.Arrays;

public class ArrayUtils {
    public static boolean areAllElemsDistinct (int[] arr, int except) {
        int[] copiedArr = arr.clone();
        Arrays.sort(copiedArr);
        for (int i = 0; i < copiedArr.length - 1; i++) {
            int current = copiedArr[i];
            int next = copiedArr[i+1];
            if (current != except && next != except && current == next) {
                return false;
            }
        }
        
        return true;
    }

    public static int[] getSubset (int[][] arr, int fromRow, int toRow, int fromCol, int toCol) {
        int resultSize = ((toRow + 1) - (fromRow )) * ((toCol + 1) - (fromCol));
        int[] result = new int[resultSize];
        int resultCount = 0;

        for (int i = fromRow; i <= toRow; i++) {
            for (int j = fromCol; j <= toCol; j++) {
                result[resultCount] = arr[i][j];
                resultCount++;
            }
        }

        return result;
    }

    public static boolean contains (int[] arr, int elemToFind) {
        for (int elem : arr) {
            if (elem == elemToFind) {
                return true;
            }
        }

        return false;
    }
}
