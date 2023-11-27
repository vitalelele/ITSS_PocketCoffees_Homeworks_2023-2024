package main.java.homework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.customException.*;

/**
 * This class calculates all permutations of an array of integers and calculates
 * the cost of each permutation sequence based on a certain rule.
 */
public class FindCostOfPermutations {
    /**
     * This inner class associates a permutation with its cost.
     */
    private static class CostSum {
        int[] sequence;
        int cost;

        public CostSum(int[] sequence, int cost) {
            this.sequence = sequence;
            this.cost = cost;
        }
    }

    /**
     * This method generates all possible permutations of the array
     * and calculates the cost of all permutations, where the cost of
     * a permutation is defined as the cumulative sum of the
     * sequence of possible numbers.
     *
     * @param numbers array of integers to be permuted
     * @param costMax the cost to look for in various permutations
     * @return ArrayList of CostSum where the permutations have the requested cost, if there are no permutations
     * whose cost is equal to the requested one, the ArrayList is empty
     * @throws NullNumberException is thrown if the numbers array is null
     * @throws IllegalNumberException is thrown if the array size is 0
     * @throws IllegalCostException is thrown if the cost to look for is less than 0
     */
    public static ArrayList<CostSum> costOfSums(int[] numbers, int costMax) throws NullNumberException, IllegalNumberException, IllegalCostException {
        if (numbers != null) {
            if (numbers.length != 0) {
                if (costMax > 0) {
                    int size = numbers.length;
                    List<int[]> numberPermutations = generatePermutations(numbers);
                    ArrayList<CostSum> sumsOfPermutations = new ArrayList<>();

                    int sum;
                    int cost;
                    for (int i = 0; i < numberPermutations.size(); i++) {
                        sum = (numberPermutations.get(i))[0] + (numberPermutations.get(i))[1];
                        cost = sum;
                        for (int j = 2; j < size; j++) {
                            sum += numberPermutations.get(i)[j];
                            cost += sum;
                        }
                        sumsOfPermutations.add(new CostSum(numberPermutations.get(i).clone(), cost));
                    }

                    ArrayList<CostSum> correctPermutations = new ArrayList<>();
                    int sizePerm = size * size;
                    for (int i = 0; i < sumsOfPermutations.size(); i++) {
                        if ((sumsOfPermutations.get(i).cost) == costMax) {
                            correctPermutations.add(sumsOfPermutations.get(i));
                        }
                    }

                    return correctPermutations;
                } else {
                    throw new IllegalCostException("The cost to look for in the permutations must be greater than 0");
                }
            } else {
                throw new IllegalNumberException("The size of the array must be greater than 0");
            }
        } else {
            throw new NullNumberException("The array cannot be null ");
        }
    }

    /**
     * Generates all possible permutations of the array of integers.
     *
     * @param array array of integers to be permuted
     * @return List of permuted arrays
     */
    public static List<int[]> generatePermutations(int[] array) {
        List<int[]> result = new ArrayList<>();
        permute(array, 0, result);
        return result;
    }

    /**
     * Recursive method to generate permutations for the integer array.
     *
     * @param array  array of integers for which to generate permutations
     * @param start  current index of the permutation
     * @param result list of integer arrays that will contain all generated permutations
     */
    private static void permute(int[] array, int start, List<int[]> result) {
        if (start == array.length - 1) {
            result.add(Arrays.copyOf(array, array.length));
            return;
        }

        for (int i = start; i < array.length; i++) {
            swap(array, start, i);
            permute(array, start + 1, result);
            swap(array, start, i); // backtrack
        }
    }

    /**
     * Method that performs the swap between two values in an array.
     *
     * @param array array of integers in which to swap elements
     * @param i     index of the first element to swap
     * @param j     index of the second element to swap
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) throws NullNumberException, IllegalNumberException, IllegalCostException {
        int[] numbers = new int[]{2, 1, 3};
        List<CostSum> result = costOfSums(numbers, 11);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Cost: " + result.get(i).cost + " Array: ");
            for (int j = 0; j < result.get(i).sequence.length; j++) {
                System.out.println(result.get(i).sequence[j]);
            }

        }
    }
}