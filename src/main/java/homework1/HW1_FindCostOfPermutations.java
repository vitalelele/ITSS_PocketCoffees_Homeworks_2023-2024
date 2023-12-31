package main.java.homework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.customException.*;

/**
 This program receives as input an array of elements, the size of the array it takes as input can be a maximum of ten elements.
 The program calculates all possible permutations, furthermore the user provides as input an integer representing a cost.
 The program returns all the permutations whose cost is equal to what the user provided as input.
 */
public class HW1_FindCostOfPermutations {
    /**
     * This inner class associates a permutation with its cost.
     */
    private static class CostSum {
        int[] sequenceOfNumbers;
        int costOfArray;

        public CostSum(int[] sequenceOfNumbers, int costOfArray) {
            this.sequenceOfNumbers = sequenceOfNumbers;
            this.costOfArray = costOfArray;
        }

        @Override
        public boolean equals(Object obj) {
            return Arrays.equals(this.sequenceOfNumbers,((CostSum) obj).sequenceOfNumbers);
        }
    }

    /**
     * This method generates all possible permutations of the array
     * and calculates the cost of all permutations, where the cost of
     * a permutation is given by the sum of all the sums cumulative of the current permutation.
     *
     * @param inputNumbers array of integers to be permuted
     * @param costRequested the cost to look for in various permutations
     * @return ArrayList of CostSum where the permutations have the requested cost, if there are no permutations
     * whose cost is equal to the requested one, the ArrayList is empty
     * @throws NullNumberException is thrown if the numbers array is null
     * @throws IllegalNumberException is thrown if the array size is 0
     */
    public static ArrayList<int[]> costOfSums(int[] inputNumbers, int costRequested) throws NullNumberException, IllegalNumberException{

        if (inputNumbers != null) {
            if (inputNumbers.length != 0 && inputNumbers.length <= 10) {
                    int size = inputNumbers.length;
                    List<int[]> numberPermutations = generatePermutations(inputNumbers);
                    ArrayList<CostSum> sumsOfPermutations = new ArrayList<>();

                    for (int[] numberPermutation : numberPermutations) {
                        int sum = numberPermutation[0];
                        int cost = numberPermutation.length == 1 ? sum : 0;
                        for (int j = 1; j < size; j++) {
                            sum += numberPermutation[j];
                            cost += sum;
                        }
                        CostSum newElement = new CostSum(numberPermutation.clone(), cost);
                        if(!sumsOfPermutations.contains(newElement)){
                            sumsOfPermutations.add(newElement);
                        }
                    }

                    ArrayList<int[]> correctPermutations = new ArrayList<>();
                    for (CostSum sumsOfPermutation : sumsOfPermutations) {
                        if ((sumsOfPermutation.costOfArray) == costRequested) {
                            correctPermutations.add(sumsOfPermutation.sequenceOfNumbers);
                        }
                    }

                    return correctPermutations;
            } else {
                throw new IllegalNumberException("The size of the array must be greater than 0 and minor than 10.");
            }
        } else {
            throw new NullNumberException("The array cannot be null ");
        }
    }

    /**
     * Generates all possible permutations of the array of integers given in input.
     *
     * @param arrayToPermutate array of integers to be permuted
     * @return List of permuted arrays
     */
    public static List<int[]> generatePermutations(int[] arrayToPermutate) {
        List<int[]> result = new ArrayList<>();
        permute(arrayToPermutate, 0, result);
        return result;
    }

    /**
     * Recursive method to generate permutations for the integer array.
     *
     * @param arrayForPermutation array of integers for which to generate permutations
     * @param start  current index of the permutation
     * @param result list of integer arrays that will contain all generated permutations
     */
    private static void permute(int[] arrayForPermutation, int start, List<int[]> result) {
        if (start == arrayForPermutation.length - 1) {
            result.add(Arrays.copyOf(arrayForPermutation, arrayForPermutation.length));
            return;
        }

        for (int i = start; i < arrayForPermutation.length; i++) {
            swap(arrayForPermutation, start, i);
            permute(arrayForPermutation, start + 1, result);
            swap(arrayForPermutation, start, i); // backtrack
        }
    }

    /**
     * Method that performs the swap between two values in an array.
     *
     * @param array array of integers in which to swap elements
     * @param firstToSwap index of the first element to swap
     * @param secondToSwap index of the second element to swap
     */
    private static void swap(int[] array, int firstToSwap, int secondToSwap) {
        int temp = array[firstToSwap];
        array[firstToSwap] = array[secondToSwap];
        array[secondToSwap] = temp;
    }
}