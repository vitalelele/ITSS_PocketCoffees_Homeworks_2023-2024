package main.java.homework1;

import main.java.customException.IllegalNumberException;
import main.java.customException.NullNumberException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;


import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HW1_FindCostOfPermutationTest {

    @Test
    @Order(0)
    void testEsplorativo(){

        Assertions.assertAll(
                () -> assertArrayEquals(new int [][]{new int[]{1, 4, 2}, new int[]{4, 1, 2}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 12).toArray()),

                () -> assertArrayEquals(new int [][]{new int[]{-2, 3, 1}, new int[]{3, -2, 1}},
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, -2, 3}, 3).toArray()),

                () -> assertArrayEquals(new int [][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{1, 4, 2}, 11).toArray()),

                () -> assertThrows(NullNumberException.class ,
                        () -> HW1_FindCostOfPermutations.costOfSums(null, 11).toArray())
        );
    }

    @Nested
    @DisplayName("Casi eccezionali")
    class exceptionalCases{
        @DisplayName("T1: inputNumbers è null")
        @ParameterizedTest
        @NullSource
        @Order(1)
        void inputNumbersIsNull(int[] inputNumbers) {
            assertThrows(NullNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(inputNumbers, 5).toArray());
        }

        @DisplayName("T2: inputNumbers è empty")
        @ParameterizedTest
        @EmptySource
        @Order(2)
        void inputNumbersIsEmpty(int[] inputNumbers) {
            assertThrows(IllegalNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(inputNumbers, 5).toArray());
        }

        @DisplayName("T3: inputNumbers ha una lunghezza maggiore di 10")
        @Test
        @Order(3)
        void inputNumbersIsLongerThan10() {
            assertThrows(IllegalNumberException.class, () -> HW1_FindCostOfPermutations.costOfSums(new int[]{1,2,3,4,5,6,7,8,9,10,11}, 5).toArray());
        }
    }

    @Nested
    @DisplayName("inputNumbers con lughezza uguale a 1")
    class inputNumbersLength1{
        @DisplayName("T4: costRequested è uguale a l’unico elemento di inputNumbers")
        @Test
        @Order(4)
        void costRequestedIsEqualToInputNumbers() throws NullNumberException, IllegalNumberException{
            assertArrayEquals(new int [][]{new int[]{1}},
                    HW1_FindCostOfPermutations.costOfSums(new int[]{1}, 1).toArray());
        }

        @DisplayName("T5: costRequested è diverso dall’unico elemento di inputNumbers")
        @Test
        @Order(5)
        void costRequestedIsDifferentFromInputNumbers() throws NullNumberException, IllegalNumberException{
            assertArrayEquals(new int [][]{},
                    HW1_FindCostOfPermutations.costOfSums(new int[]{1}, 2).toArray());
        }


    }

    @Nested
    @DisplayName("Combinazioni di input")
    class inputCombinations{
        @Nested
        @DisplayName("inputNumbers con numeri positivi e costRequested > 0")
        class primaCombinazione{
            @DisplayName("T6: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(6)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                int[ ][ ] expecteds = new int[][]{new int[]{3, 0, 7}, new int[]{0, 3, 7}};
                assertThat(HW1_FindCostOfPermutations.costOfSums(new int[]{7, 0, 3}, 13), containsInAnyOrder(expecteds));
            }

            @DisplayName("T7: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(6)
            void costRequestedNonUgualeAdNessunUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{7, 0, 3}, 11).toArray());
            }
        }

        @Nested
        @DisplayName("inputNumbers con tutti numeri negativi e  costRequested < 0")
        class secondaCombinazione{
            @DisplayName("T8: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(8)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                int[ ][ ] expecteds = new int[][]{new int[]{-2, -4, -3}, new int[]{-4, -2, -3}};
                assertThat(HW1_FindCostOfPermutations.costOfSums(new int[]{-3, -2, -4}, -15), containsInAnyOrder(expecteds));
            }

            @DisplayName("T9: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(9)
            void costRequestedNonUgualeANessunUnCostoDellePermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{-3, -2, -4}, -13).toArray());
            }
        }

        @Nested
        @DisplayName("inputNumbers con numeri positivi e negativi e  costRequested > 0")
        class terzaCombinazione{
            @DisplayName("T10: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(10)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                int[ ][ ] expecteds = new int[][]{new int[]{3, 2, -3, -4}, new int[]{2, 3,-3, -4}};
                assertThat(HW1_FindCostOfPermutations.costOfSums(new int[]{2, -4, -3, 3}, 5), containsInAnyOrder(expecteds));
            }

            @DisplayName("T11: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(11)
            void costRequestedNonUgualeANessunUnCostoDellePermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{2, -4, -3, 3}, 2).toArray());
            }
        }

        @Nested
        @DisplayName("inputNumbers con numeri positivi e negativi e  costRequested < 0")
        class quartaCombinazione{
            @DisplayName("T12: costRequested corrisponde ad almeno un costo delle permutazioni di inputNumbers")
            @Test
            @Order(12)
            void costRequestedUgualeAdAlmenoUnCostoDellepermutazioni() throws NullNumberException, IllegalNumberException{
                int[ ][ ] expecteds = new int[][]{new int[]{-4, 2, -3, 3}, new int[]{2, -4, -3, 3}};
                assertThat(HW1_FindCostOfPermutations.costOfSums(new int[]{2, -4, -3, 3}, -9), containsInAnyOrder(expecteds));
            }

            @DisplayName("T13: costRequested non corrisponde a nessun un costo delle permutazioni di inputNumbers")
            @Test
            @Order(13)
            void costRequestedNonUgualeANessunUnCostoDellePermutazioni() throws NullNumberException, IllegalNumberException{
                assertArrayEquals(new int[][]{ },
                        HW1_FindCostOfPermutations.costOfSums(new int[]{2, -4, -3, 3}, -5).toArray());
            }
        }
    }

    @Nested
    @DisplayName("Boundary Cases")
    class boundaryCases{
        @DisplayName("T14: costRequested è minore del costo minimo delle permutazioni di inputNumbers")
        @ParameterizedTest
        @MethodSource("streamCostiMinori")
        void costRequestedMinoreDelCostoMinimoDellePermutaziioni(int[] inputNumbers, int costRequested) throws NullNumberException, IllegalNumberException {
            assertArrayEquals(new int[][]{ },
                    HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested).toArray());
        }

        static Stream<Arguments> streamCostiMinori(){
            ArrayList<Integer> costiMinimi = new ArrayList<>();
            ArrayList<int[]> arrays = new ArrayList<>();
            for(int lunghezzaArray=2; lunghezzaArray<10; lunghezzaArray++){
                //array di lunghezza lunghezzaArray con numeri casuali
                int[] array = new int[lunghezzaArray];
                for(int i=0; i<lunghezzaArray; i++){
                    //riempio l'array con numeri casuali anche negativi da -15 a 15
                    array[i] = (int) (Math.random() * 31) - 15;

                }
                Arrays.sort(array);
                int sommaCumulative = 0;
                int costoMinimo = 0;
                for (int numeri : array) {
                    sommaCumulative += numeri;
                    costoMinimo += sommaCumulative;
                }
                arrays.add(array);
                costiMinimi.add(costoMinimo);
            }

            return Stream.of(
                    Arguments.of(arrays.get(0), costiMinimi.get(0)-1),
                    Arguments.of(arrays.get(1), costiMinimi.get(1)-1),
                    Arguments.of(arrays.get(2), costiMinimi.get(2)-1),
                    Arguments.of(arrays.get(3), costiMinimi.get(3)-1),
                    Arguments.of(arrays.get(4), costiMinimi.get(4)-1),
                    Arguments.of(arrays.get(5), costiMinimi.get(5)-1),
                    Arguments.of(arrays.get(6), costiMinimi.get(6)-1),
                    Arguments.of(arrays.get(7), costiMinimi.get(7)-1)
            );
        }

        @DisplayName("T15: costRequested è maggiore del costo massimo delle permutazioni di inputNumbers")
        @ParameterizedTest
        @MethodSource("streamCostiMaggiori")
        void costRequestedMaggioreDelCostoMassimoDellePermutazioni(int[] inputNumbers, int costRequested) throws NullNumberException, IllegalNumberException {
            assertArrayEquals(new int[][]{ },
                    HW1_FindCostOfPermutations.costOfSums(inputNumbers, costRequested).toArray());
        }

        static Stream<Arguments> streamCostiMaggiori(){
            ArrayList<Integer> costiMassimi = new ArrayList<>();
            ArrayList<int[]> arrays = new ArrayList<>();
            for(int lunghezzaArray=2; lunghezzaArray<10; lunghezzaArray++){
                //array di lunghezza lunghezzaArray con numeri casuali
                int[] array = new int[lunghezzaArray];
                for(int i=0; i<lunghezzaArray; i++){
                    //riempio l'array con numeri casuali anche negativi da -15 a 15
                    array[i] = (int) (Math.random() * 31) - 15;

                }

                //ordina l'array in ordine decrescente
                Arrays.sort(array);
                int n = array.length;
                for (int i = 0; i < n / 2; i++) {
                    int temp = array[i];
                    array[i] = array[n - i - 1];
                    array[n - i - 1] = temp;
                }

                int sommaCumulative = 0;
                int costoMassimo = 0;
                for (int numeri : array) {
                    sommaCumulative += numeri;
                    costoMassimo += sommaCumulative;
                }
                arrays.add(array);
                costiMassimi.add(costoMassimo);
            }

            return Stream.of(
                    Arguments.of(arrays.get(0), costiMassimi.get(0)+1),
                    Arguments.of(arrays.get(1), costiMassimi.get(1)+1),
                    Arguments.of(arrays.get(2), costiMassimi.get(2)+1),
                    Arguments.of(arrays.get(3), costiMassimi.get(3)+1),
                    Arguments.of(arrays.get(4), costiMassimi.get(4)+1),
                    Arguments.of(arrays.get(5), costiMassimi.get(5)+1),
                    Arguments.of(arrays.get(6), costiMassimi.get(6)+1),
                    Arguments.of(arrays.get(7), costiMassimi.get(7)+1)
            );
        }
    }


}

