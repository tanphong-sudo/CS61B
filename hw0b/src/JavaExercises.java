import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class JavaExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: Fill in this function.
        int dice[] = {1, 2, 3, 4, 5, 6};
        List<Integer> dices = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            dices.add(i + 1);

        return dice;
    }

    /** Returns the order depending on the customer.
     *  If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customer) {
        // TODO: Fill in this function.
        if (customer.equals("Ergun"))
            return new String[]{"beyti", "pizza", "hamburger", "tea"};
        else if (customer.equals("Erik"))
            return new String[]{"sushi", "pasta", "avocado", "coffee"};
        return new String[3];
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            min = Integer.min(min, num);
            max = Integer.max(max, num);
        }
        return max - min;
    }

    /**
      * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
      * Hailstone sequence is described as:
      *    - Pick a positive integer n as the start
      *        - If n is even, divide n by 2
      *        - If n is odd, multiply n by 3 and add 1
      *    - Continue this process until n is 1
      */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: Fill in this function.

        list.add(x);
        if (x == 1) return list;
        if (x % 2 == 1) {
            x = x * 3 + 1;
            return hailstoneHelper(x, list);
        }
        else {
            x /= 2;
            return hailstoneHelper(x, list);
        }
    }

}
