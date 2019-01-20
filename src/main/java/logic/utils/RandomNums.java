package logic.utils;

public class RandomNums {
    public static int randomInt(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from >= to");
        }

        return from + (int)(Math.random() * ((to - from) + 1));
    }

    public static int[] getIntPair(int from, int to) {
        int firstNum = RandomNums.randomInt(from, to);
        int secondNum = RandomNums.randomInt(from, to);

        if (firstNum == secondNum) {
            if (firstNum == to) {
                firstNum -= 1;
            } else {
                firstNum += 1;
            }
        }

        return new int[] { firstNum, secondNum };

    }
}
